package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.dao.IAttDao;
import com.dao.IRecordDao;
import com.entity.Record;
import com.entity.Staff;
import com.util.DBUtil;

public class RecordDao implements IRecordDao {

	public Staff login(String username, String password) {
		Staff staff = new Staff();
		DBUtil db=DBUtil.getInstance();
		Connection con=db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from t_staff where username=? and password=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return staff;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.close(con, pstmt, rs);
		}
	}

	public ArrayList<Record> searchByNo(String username,int up) {
		// TODO Auto-generated method stub
		ArrayList<Record> result=new ArrayList<Record>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		try {
			if(username!=""){			
				 sql="select * from t_record where staffid like ? ";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, "%"+username+"%");
			}else{
				 sql="select * from t_record limit ?,6";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, up);
			}		
			rs=pstmt.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setStaffid(rs.getInt("staffid"));
				record.setRecordtype(rs.getString("recordtype"));
				record.setRecorduser(rs.getString("recorduser"));
				record.setRecorddate(rs.getDate("recorddate"));
				result.add(record);
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
		String sql="update t_staff set username=?,sex=?,seniority=?,birth=?,post=?,depid=?,phone=? where staffid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, staff.getUserName());
			pstmt.setString(2, staff.getSex());
			pstmt.setInt(3, staff.getSeniority());
			pstmt.setDate(4,java.sql.Date.valueOf(staff.getBirth().toString()));
			pstmt.setString(5, staff.getPost());
			pstmt.setString(6, staff.getDepid());
			pstmt.setString(7, staff.getPhone());
			pstmt.setInt(8, staff.getStaffid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
	
	public boolean deleteById(String id) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="delete a,b from t_staff a,t_salary b where a.staffid=? and b.staffid=? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
	
	public boolean insert(Record record) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="insert into t_record(staffid,recordtype,recorduser,recorddate) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, record.getStaffid());
			pstmt.setString(2, record.getRecordtype());
			pstmt.setString(3, record.getRecorduser());
			pstmt.setDate(4, java.sql.Date.valueOf(record.getRecorddate().toString()));			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
}

