package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.IAdminDao;
import com.dao.IDepartmentDao;
import com.dao.IStaffDao;
import com.dao.imp.AdminDao;
import com.dao.imp.DepartmentDao;
import com.dao.imp.StaffDao;
import com.entity.Admin;
import com.entity.Department;
import com.entity.Staff;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

		doPost(request, response);
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
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");//编码格式已在过滤器中过滤
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String code = request.getParameter("code");
		String rand = (String) session.getAttribute("rand");
		if(!code.equals(rand))
		{
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher("../index.jsp").forward(request,
					response);
		}
		int user =Integer.parseInt(request.getParameter("usertype"));
		switch (user) {
		case 0:
			IDepartmentDao departmentDao = new DepartmentDao();
			Department department = departmentDao.login(username, password);
			if(department!=null)
			{
				session.setAttribute("department", department);
				//response.sendRedirect(request.getContextPath()+"/login_seccess.jsp");
				out.println("this si d");
				return;
			}else{
				request.setAttribute("message", "账号或者密码错误！");
			}
			break;
		case 1:
			IStaffDao staffDao = new StaffDao();
			Staff staff = staffDao.login(username, password);
			if(staff!=null)
			{
				session.setAttribute("staff", staff);
				//response.sendRedirect(request.getContextPath()+"/login_seccess.jsp");
				return;
			}else{
				request.setAttribute("message", "账号或者密码错误！");
			}
			break;
		case 2:
			IAdminDao adminDao = new AdminDao();
			Admin admin = adminDao.login(username,password);
			if(admin!=null)
			{
				session.setAttribute("admin", admin);
				session.setAttribute("username",username);//将用户名放入username
				response.sendRedirect(request.getContextPath()+"/admin/welcome.jsp");
				return;
			}else{
				request.setAttribute("message", "账号或者密码错误！");
			}
			break;
		default:
			break;
		}
		request.getRequestDispatcher("../index.jsp").forward(request,
				response);
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
