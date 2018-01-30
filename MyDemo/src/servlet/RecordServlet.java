package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
import com.dao.IStaffDao;
import com.dao.imp.AttDao;
import com.dao.imp.RecordDao;
import com.dao.imp.SalaryDao;
import com.dao.imp.StaffDao;
import com.entity.Record;
import com.entity.Staff;
import com.util.DateJsonValueProcessor;

public class RecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RecordServlet() {
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
		ArrayList<Record> result = null;
		int up = Integer.parseInt(request.getParameter("up"));
//		int next = Integer.parseInt(request.getParameter("next"));
		IRecordDao recordDao = new RecordDao();
		result = recordDao.searchByNo(searchValue,up); 
		//设置过滤json格式:空值去除
		
        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.registerJsonValueProcessor(Date.class,
		          new DateJsonValueProcessor("yyyy-MM-dd"));
		
		JSONArray jsonarray = JSONArray.fromObject(result,jsonConfig);

		System.out.print(jsonarray);
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
