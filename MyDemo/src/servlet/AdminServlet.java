package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import com.dao.IAdminDao;
import com.dao.IRecordDao;
import com.dao.IStaffDao;
import com.dao.imp.AdminDao;
import com.dao.imp.RecordDao;
import com.dao.imp.StaffDao;
import com.entity.Admin;
import com.entity.Record;
import com.entity.Staff;
import com.util.DateJsonValueProcessor;

public class AdminServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminServlet() {
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
		if(para.equals("logout"))
		{
			logout(request,response);
		}else if(para.equals("searchstaff")){
			searchstaff(request,response);
		}else if(para.equals("changeSt")){
			changeSt(request,response);
		}else if(para.equals("deleteSt")){
			deleteSt(request,response);
		}else if(para.equals("addSt")){
			addSt(request,response);
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}
	
	public void searchstaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchValue = request.getParameter("searchvalue");
		ArrayList<Staff> result = null;
		int up = Integer.parseInt(request.getParameter("up"));
//		int next = Integer.parseInt(request.getParameter("next"));
		IStaffDao staffDao = new StaffDao();
		result = staffDao.searchByNo(searchValue,up);
		
		//json日期转换
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
		          new DateJsonValueProcessor("yyyy-MM-dd"));
//		JSONObject json = JSONObject.fromObject(o, jsonConfig);
		
//设置过滤json格式:空值去除
		
        //JsonConfig jsonConfig = new JsonConfig();
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
		
		JSONArray jsonarray = JSONArray.fromObject(result,jsonConfig);
		//删除json指定键
		JSONArray jsonArray1 = new JSONArray();
		for (int i = 0; i < jsonarray.size(); i++) {
		JSONObject jsonObject = (JSONObject) jsonarray.get(i);
		JSONObject jsonObject2 = jsonObject.discard("passWord");
		JSONObject jsonObject3 = jsonObject2.discard("salary");
		JSONObject jsonObject4 = jsonObject2.discard("allsalary");
		jsonArray1.add(jsonObject4);
		}
		//System.out.println(jsonarray.get(2));
		
		out.print(jsonarray);
		
			
	}

	
	public void changeSt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
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
		IStaffDao staffDao = new StaffDao();
		Staff staff = new Staff();
		staff.setUserName((String)jsonobj.get("userName"));
		staff.setSex((String)jsonobj.get("sex"));
		staff.setSeniority(Integer.parseInt((String)jsonobj.get("seniority")));
		staff.setBirth(java.sql.Date.valueOf(jsonobj.get("birth").toString()));
		staff.setPost((String)jsonobj.get("post"));
		staff.setDepid((String)jsonobj.get("depid"));
		staff.setPhone((String)jsonobj.get("phone"));
		staff.setStaffid(Integer.parseInt(jsonobj.get("staffid").toString()));
		if(staffDao.update(staff)){
			out.print("ok");
		}else{
			out.print("no");
		}
//		System.out.println(jsonobj.get("userName"));
		//向数据库添加一条记录
		addRecord(request,response,jsonobj.get("staffid").toString(),"修改资料");
		
		
	}
	
	
	public void deleteSt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String staffid = request.getParameter("staffid");
		IStaffDao staffDao = new StaffDao();
		if(staffDao.deleteById(staffid)){
			out.print("ok");
		}else{
			out.print("no");
		}
		
		//向数据库添加一条记录
		addRecord(request,response,staffid,"删除员工");
		
		
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
		IStaffDao staffDao = new StaffDao();
		Staff staff = new Staff();
		int seniority = Integer.parseInt((String)jsonobj.get("seniority"));
		String post = (String)jsonobj.get("post");
		
		staff.setUserName((String)jsonobj.get("userName"));
		staff.setPassWord((String)jsonobj.get("passWord"));
		staff.setSex((String)jsonobj.get("sex"));
		staff.setSeniority(seniority);
		staff.setBirth(java.sql.Date.valueOf(jsonobj.get("birth").toString()));
		staff.setPost(post);
		staff.setDepid((String)jsonobj.get("depid"));
		staff.setPhone((String)jsonobj.get("phone"));
		//staff.setStaffid((String)jsonobj.get("staffid"));
		
		//获得员工工龄和岗位并计算基本工资
		int salary = 0;
		if(post.equals("研发")){
			salary = 6000 + (seniority*300);
		}else if(post.equals("设计")){
			salary = 4000 + (seniority*300);
		}else if(post.equals("产品经理")){
			salary = 9000 + (seniority*300);
		}else if(post.equals("策划")){
			salary = 5000 + (seniority*300);
		}else if(post.equals("运营")){
			salary = 5300 + (seniority*300);
		}else if(post.equals("编辑")){
			salary = 4200 + (seniority*300);
		}
		
		
		
		if(staffDao.insert(staff,salary)){
			out.print("ok");
		}else{
			out.print("no");
		}
		
		//向数据库添加一条记录
		addRecord(request,response,"0","添加员工");
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
