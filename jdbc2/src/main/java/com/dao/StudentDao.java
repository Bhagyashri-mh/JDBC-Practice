package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.student;

public class StudentDao {
	public int insertStudent(student s) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			String sql = "insert into student(sname, scity,spercentage)values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getSname());
			pst.setString(2, s.getScity());
			pst.setDouble(3, s.getSpercentage());
			check = pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	public int deleteStudentBySid(int sid) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			String sql = "Delete from student where sid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return check;
	}

	public student findStudentBySid(int sid) {
		student s = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			String sql = "Select sid,sname, scity,spercentage from student where sid= ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			while (rs.next()) {
				s = new student();
				s.setSid((int) rs.getObject("sid"));
				s.setSname((String) rs.getObject("sname"));
				s.setScity((String) rs.getObject("scity"));
				s.setSpercentage((double) rs.getDouble("spercentage"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return s;
	}

	public List<student> findAllStudent()
	{
		List<student> list = new ArrayList();
		Connection con= null;
		PreparedStatement pst =null;
		ResultSet rs= null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			String sql= "Select sid,sname,scity,spercentage from student";
			pst=con.prepareStatement(sql);
			rs= pst.executeQuery();
			while(rs.next())
			{
				student s= new student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
				
					try {
						rs.close();
						pst.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
		return list;
		}
		
	public int updateStudent(student s)
	{
		Connection con= null;
		PreparedStatement pst= null;
		int check=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			String sql= "update Student set sname=?,scity=?,spercentage=? where sid=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, s.getSname());
			pst.setString(2, s.getScity());
			pst.setDouble(3, s.getSpercentage());
			pst.setInt(4,s.getSid());
			check=pst.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
		return check;
	}
	}


