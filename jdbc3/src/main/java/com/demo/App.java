package com.demo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dao.StudentDao;
import com.model.Student;


public class App 
{
    public static void main( String[] args )
    {
    	StudentDao sd= new StudentDao();
       Map<String,Collection> m= sd.findSnameAndCityOfStudent();
      List<String> names =(List<String>) m.get("names");
      List<String> cities =(List<String>) m.get("cities");
       for(int i=0;i<names.size();i++)
    	   	System.out.println(names.get(i)+"\t"+cities.get(i));		
       
       
    }
}
