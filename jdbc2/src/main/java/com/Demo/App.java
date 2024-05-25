package com.Demo;

import java.util.List;
import java.util.Random;

import com.dao.StudentDao;
import com.model.student;


public class App 
{
    public static void main( String[] args )
    {
       
    	
        	StudentDao sd= new StudentDao();
        	student s= sd.findStudentBySid(200);
        		System.out.println(s);
        	s.setSname("shila");
        	s.setScity("Thane");
        	s.setSpercentage(99.12);
        	System.out.println(sd.updateStudent(s)); 
      
    }
}
