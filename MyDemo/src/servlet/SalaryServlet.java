package servlet;


import java.io.IOException;

import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;


import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;


import com.dao.IAttDao;
import com.dao.IRecordDao;
import com.dao.ISalaryDao;

import com.dao.imp.AttDao;
import com.dao.imp.RecordDao;
import com.dao.imp.SalaryDao;


import com.entity.Record;

import com.entity.Staff;
import com.util.DateJsonValueProcessor;
import com.util.TaxCalculation;

public class SalaryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalaryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String para = request.getParameter("para");
		if(para.equals("searchstaff")){
			searchstaff(request,response);
		}else if(para.equals("changeSt")){
			changeSt(request,response);
		}
	}
	
	public void searchstaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchValue = request.getParameter("searchvalue");
		ArrayList<Staff> result = null;
		int up = Integer.parseInt(request.getParameter("up"));
		ISalaryDao salaryDao = new SalaryDao();
		result = salaryDao.searchByNo(searchValue,up);

		//设置过滤json格式:空值去除
		
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
                public boolean apply(Object object, String fieldName, Object fieldValue) {
                if(fieldValue instanceof List){
                    List<Object> list = (List<Object>) fieldValue;
                    if (list.size()==0) {
                        return true;
                    }
                }
                return null == fieldValue || "".equals(fieldValue);
                }
        };
        jsonConfig.setJsonPropertyFilter(filter);
		
      //json日期转换
      		jsonConfig.registerJsonValueProcessor(Date.class,
      		          new DateJsonValueProcessor("yyyy-MM-dd"));
		
		JSONArray jsonarray = JSONArray.fromObject(result,jsonConfig);
		out.print(jsonarray);
		
		
			
	}

	//计算总工资
	public void changeSt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		ISalaryDao salaryDao = new SalaryDao();
		Staff staff = new Staff();
		int staffid =Integer.parseInt(request.getParameter("staffid"));
		String lastdate = request.getParameter("lastdate");
		//查询用户出勤情况
		IAttDao AttDao = new AttDao();
		ArrayList<Staff> attresult=new ArrayList<Staff>();
		ArrayList<Staff> sararesult=new ArrayList<Staff>();
		attresult = AttDao.searchByNo(String.valueOf(staffid), 0);
		sararesult = salaryDao.searchByNo(String.valueOf(staffid), 0);
		//获取基本工资
		int salary = 0;//获取的基本工资
		for(int j = 0 ; j < sararesult.size() ; j++) {
			salary = sararesult.get(j).getSalary();
			}	
		//动态修改工资
		for(int i = 0 ; i < attresult.size() ; i++) {		
			String  type = attresult.get(i).getType();
			if(type.equals("请假")){
				salary=salary - (Integer)(salary/ 30);//扣除一天工资
			}else if(type.equals("迟到")||type.equals("早退")){
				salary-=20;//扣除20
			}				
			}
		//计算税
		double tax = TaxCalculation.TaxCal(salary);

		staff.setStaffid(staffid);
		staff.setAllsalary((int)(salary-tax));
		staff.setLastdate(java.sql.Date.valueOf(lastdate));
		if(salaryDao.update(staff)){
			out.print("ok");
		}else{
			out.print("no");
		}
		
		addRecord(request,response,String.valueOf(staffid),"工资发布");

	}
	
	
	public void addRecord(HttpServletRequest request, HttpServletResponse response,String staffid,String recordtype)
			throws ServletException, IOException {
		//向数据库添加一条记录
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		IRecordDao recordDao = new RecordDao();
		Record record  = new Record();
		record.setStaffid(Integer.parseInt(staffid));
		record.setRecordtype(recordtype);
		record.setRecorduser(session.getAttribute("username").toString());
		record.setRecorddate(java.sql.Date.valueOf(sdf.format(new Date())));// new Date()为获取当前系统时间
		if(recordDao.insert(record)){			
//			System.out.println("1");
		}else{
//			System.out.println("0");
		}
	}
	

	
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *  
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
