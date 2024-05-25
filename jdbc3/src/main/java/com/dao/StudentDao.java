package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Student;
import com.util.MyDatabase;

public class StudentDao {
	
	public int insertStudent(Student s)
	{
		int check=0;
		String sql= "insert into student(sname, scity, spercentage) values(?,?,?)";
		try (Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);){
				pst.setString(1, s.getSname());
				pst.setString(2, s.getScity());
				pst.setDouble(3, s.getSpercentage());
			check= pst.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return check;
	}

	public int deleteStudent(int sid)
		{
			int check=0;
			String sql= "delete from Student where sid=?";
			try (Connection con= MyDatabase.myConnection(); 
					PreparedStatement pst= con.prepareStatement(sql);){
				pst.setInt(1, sid);
				check=pst.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		   return check;
		}
	
	public Student findStudentBySid(int sid)
	{
			Student s= null;
			String sql= "select sid,sname,scity,spercentage from student where sid=?";
			try(Connection con= MyDatabase.myConnection(); 
					PreparedStatement pst= con.prepareStatement(sql);){
				pst.setInt(1,sid);
				ResultSet rs= pst.executeQuery();
				while(rs.next())
				{
					s=new Student();
					s.setSid((int)rs.getObject("sid"));
					s.setSname((String)rs.getObject("sname"));
					s.setScity((String)rs.getObject("scity"));
					s.setSpercentage((Double)rs.getObject("spercentage"));
				}
				rs.close();
			} catch (SQLException e) {e.printStackTrace();}
			return s;
	}
	
	public List<Student> FindAllStudent()
	{
		List<Student> list= new ArrayList();
		Student s= null;
		String sql="Select sid,sname,scity,spercentage from student";
		try(Connection con=MyDatabase.myConnection(); PreparedStatement pst= con.prepareStatement(sql)) {
		ResultSet rs= pst.executeQuery();
		while(rs.next())
		{
			s=new Student();
			s.setSid((int)rs.getObject("sid"));
			s.setSname((String)rs.getObject("sname"));
			s.setScity((String)rs.getObject("scity"));
			s.setSpercentage((Double)rs.getObject("spercentage"));
			list.add(s);
		}
		rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return list;
	}
	
	public int updateStudent(Student s) 
	
	{
		int check=0;
		String sql="Update student set sname=?,scity=?,spercentage=? where sid=?";
		try (Connection con= MyDatabase.myConnection(); PreparedStatement pst=con.prepareStatement(sql);){
			pst.setString(1, s.getSname());
			pst.setString(2, s.getScity());
			pst.setDouble(3, s.getSpercentage());
			pst.setInt(4, s.getSid());
		check= pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//method to find student by name
	public List<Student> findStudentBySname(String sname)
	{
		List<Student> list = new ArrayList();
		String sql= "Select sid, sname, scity, spercentage from student where sname=?";
		try(Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);){
			pst.setString(1, sname);
			ResultSet rs= pst.executeQuery();	
			while(rs.next())
			{
				Student s= new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
			rs.close();
		}catch(SQLException e) {e.printStackTrace();}
			return list;
		}
	
	//method to find student by sname like
	public List<Student> findStudentBySnameLike(String sname)
	{
		List<Student> list= new ArrayList();
		String sql= "Select sid, sname, scity, spercentage from student where sname like '%"+sname+"%'";
		try(Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);
				ResultSet rs= pst.executeQuery();){
			while(rs.next())
			{
				Student s= new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
		}catch(SQLException e) {e.printStackTrace();}
			return list;
		}
	
	//method to find student by sname or city
	public List<Student> findStudentBySnameOrScity(String sname)
	{
		List<Student> list= new ArrayList();
		
		String sql= "Select sid, sname, scity, spercentage from student where sname like '%"+sname+"%' or scity like '%"+sname+"%'";
		try(Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);
				ResultSet rs= pst.executeQuery();){
			while(rs.next())
			{
				Student s= new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
		}catch(SQLException e) {e.printStackTrace();}
			return list;
	}
	
	//method to find student by percentage greater than
	public List<Student> findStudentBySpercentageGreaterThan(Double spercentage)
	{
		List<Student> list= new ArrayList();
		
		String sql= "Select sid, sname, scity, spercentage from student where spercentage>=?";
		try(Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);
				){
			pst.setDouble(1, spercentage);
			ResultSet rs= pst.executeQuery();
			while(rs.next())
			{
				Student s= new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
			rs.close();
		}catch(SQLException e) {e.printStackTrace();}
			return list;
	}
	
	//method to find student by spercentage between
	public List<Student> findStudentBySpercentageBetween(Double low, Double high)
	{
		List<Student> list= new ArrayList();
		
		String sql= "Select sid, sname, scity, spercentage from student where spercentage between ? and ?";
		try(Connection con= MyDatabase.myConnection();
				PreparedStatement pst= con.prepareStatement(sql);
				){
			pst.setDouble(1, low);
			pst.setDouble(2, high);
			ResultSet rs= pst.executeQuery();
			while(rs.next())
			{
				Student s= new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
			rs.close();
		}catch(SQLException e) {e.printStackTrace();}
			return list;
	}
	
	//method to find student by any
	public List<Student> findStudentByAny(String value)
	{
		List<Student> list= new ArrayList();
		
		String sql= "Select sid, sname, scity, spercentage from student where sname like '%"+value+"%' or scity like '%"+value+"'";
		boolean b= false;
		for(int i=0;i<value.length(); i++)
			if(Character.isAlphabetic(value.charAt(i)))
			{
				b=true;
				break;
			}
		if(!b)
			sql+=" or sid="+value+" or spercentage="+value;
		try(Connection con=MyDatabase.myConnection(); PreparedStatement pst= con.prepareStatement(sql)) {
			ResultSet rs= pst.executeQuery();
			while(rs.next())
			{
				Student s=new Student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("scity"));
				s.setSpercentage((Double)rs.getObject("spercentage"));
				list.add(s);
			}
			
			} catch (SQLException e) {e.printStackTrace();}
			return list;
		}

	//find sname of student
	public List<String> findSnameOfStudent()
	{
		List<String> list = new ArrayList();
		String sql="Select sname from Student";
		try(Connection con=MyDatabase.myConnection(); PreparedStatement pst= con.prepareStatement(sql); ResultSet rs= pst.executeQuery();)
		{
			while(rs.next())
			{
				list.add((String)rs.getObject("Sname"));
			}
		}catch(SQLException e) {e.printStackTrace();}
		return list;
		}
		
	//find sname and scity of student
		public Map<String,Collection> findSnameAndCityOfStudent()
		{
			Map<String,Collection> m = new HashMap();
			String sql="Select sname,scity from Student";
			List<String> name =new ArrayList();
			List<String> city =new ArrayList();
			try(Connection con=MyDatabase.myConnection(); 
					PreparedStatement pst= con.prepareStatement(sql); 
					ResultSet rs= pst.executeQuery();)
			{
				while(rs.next())
				{
					name.add((String)rs.getObject("Sname"));
					city.add((String)rs.getObject("scity"));
				}
				m.put("names", name);
				m.put("cities", city);
			}catch(SQLException e) {e.printStackTrace();}
			return m;
			}
	}

	
	
