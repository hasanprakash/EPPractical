package com.klu.main;

import java.sql.SQLException;

import com.db.StudentBean.Student;
import com.db.insert.StudentInsertion;

public class StudentMain {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Student s = new Student();
		s.setReg(13);
		s.setName("temp");
		s.setEmail("temp@gmail.com");
		StudentInsertion si = new StudentInsertion();
		int i = si.StudentInsert(s);
		if(i > 0) {
			System.out.println("Insertion is successful");
		}
		else
			System.out.println("Insertion failed");
			
	}

}
