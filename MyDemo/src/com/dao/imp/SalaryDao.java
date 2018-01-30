package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.dao.ISalaryDao;
import com.dao.IStaffDao;
import com.entity.Staff;
import com.util.DBUtil;

public class SalaryDao implements ISalaryDao {

	public ArrayList<Staff> searchByNo(String username,int up) {
		// TODO Auto-generated method stub
		ArrayList<Staff> result=new ArrayList<Staff>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		try {
			if(username!=""){			
				 sql="SELECT a.staffid,username,seniority,post,salary,allsalary,lastdate from t_staff as a,t_salary as b WHERE a.staffid=b.staffid and a.staffid like ? ";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, "%"+username+"%");
			}else{
				 sql="SELECT a.staffid,username,seniority,post,salary,allsalary,lastdate from t_staff as a,t_salary as b WHERE a.staffid=b.staffid limit ?,6";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, up);
			}		
			rs=pstmt.executeQuery();
			while(rs.next()){
				Staff staff = new Staff();
				staff.setStaffid(rs.getInt("staffid"));
				staff.setUserName(rs.getString("username"));
				staff.setSeniority(rs.getInt("seniority"));
				staff.setPost(rs.getString("post"));
				staff.setSalary(rs.getInt("salary"));
				staff.setAllsalary(rs.getInt("allsalary"));
				staff.setLastdate(rs.getDate("lastdate"));
				result.add(staff);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}
	
	
	public boolean update(Staff staff) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="update t_salary set allsalary=?,lastdate=? where staffid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, staff.getAllsalary());
			pstmt.setDate(2, java.sql.Date.valueOf(staff.getLastdate().toString()));
			pstmt.setInt(3, staff.getStaffid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
	
	
}

