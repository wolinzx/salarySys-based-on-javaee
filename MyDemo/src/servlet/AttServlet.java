package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.dao.IAttDao;
import com.dao.IRecordDao;
import com.dao.ISalaryDao;
import com.dao.imp.AttDao;
import com.dao.imp.RecordDao;
import com.dao.imp.SalaryDao;
import com.entity.Att;
import com.entity.Record;
import com.entity.Staff;
import com.util.DateJsonValueProcessor;

public class AttServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AttServlet() {
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
		}else if(para.equals("addSt")){
			addSt(request,response);
		}
	}
	
	public void searchstaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchValue = request.getParameter("searchvalue");
		ArrayList<Staff> result = null;
		int up = Integer.parseInt(request.getParameter("up"));
//		int next = Integer.parseInt(request.getParameter("next"));
		IAttDao attDao = new AttDao();
		result = attDao.searchByNo(searchValue,up);
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
		
        jsonConfig.registerJsonValueProcessor(Date.class,
		          new DateJsonValueProcessor("yyyy-MM-dd"));
		
		JSONArray jsonarray = JSONArray.fromObject(result,jsonConfig);
		//删除指定json键
		JSONArray jsonArray1 = new JSONArray();
		for (int i = 0; i < jsonarray.size(); i++) {
		JSONObject jsonObject = (JSONObject) jsonarray.get(i);
		JSONObject jsonObject2 = jsonObject.discard("seniority");
		JSONObject jsonObject3 = jsonObject2.discard("salary");
		JSONObject jsonObject4 = jsonObject3.discard("allsalary");
		jsonArray1.add(jsonObject4);
		}
		out.print(jsonarray);
	}

	
	public void changeSt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		ISalaryDao salaryDao = new SalaryDao();
		Staff staff = new Staff();
		int salary = Integer.parseInt(request.getParameter("salary"));
		int staffid =Integer.parseInt(request.getParameter("staffid"));
		staff.setSalary(salary);
		staff.setStaffid(staffid);
		if(salaryDao.update(staff)){
			out.print("ok");
		}else{
			out.print("no");
		}
//		System.out.println(jsonobj.get("userName"));
	}
	
	
	
	public void addSt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");// 注意加上
		PrintWriter out = response.getWriter();
		//获取客户端ajax用post传回来的数据
		InputStream sin = new BufferedInputStream(request.getInputStream());
		ByteArrayOutputStream sout = new ByteArrayOutputStream();
		int b=0;
		while((b=sin.read())!=-1)
		{
		sout.write(b);
		}
		byte[] temp = sout.toByteArray();
		String s_ok = new String(temp,"UTF-8");
		
		//json
		
		JSONObject jsonobj = JSONObject.fromObject(s_ok);
		IAttDao attDao = new AttDao();
		Att att = new Att();
		att.setStaffid(Integer.parseInt(jsonobj.get("staffid").toString()));
		att.setType((String)jsonobj.get("type"));
		att.setAttdate(java.sql.Date.valueOf(jsonobj.get("attdate").toString()));
		
		if(attDao.insert(att)){
			out.print("ok");
		}else{
			out.print("no");
		}
		
		addRecord(request,response,jsonobj.get("staffid").toString(),"登记出勤");
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
			System.out.println("1");
		}else{
			System.out.println("0");
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
