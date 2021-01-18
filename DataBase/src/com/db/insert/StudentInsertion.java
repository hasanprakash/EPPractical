package com.db.insert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.db.StudentBean.Student;
import com.db.Connection.DBUtil;

public class StudentInsertion {
	
	public int StudentInsert(Student student) throws SQLException, ClassNotFoundException {
		Connection con = DBUtil.DBConnection();
		Statement statement = con.createStatement();
		int i = statement.executeUpdate("insert into student_table values(student.regno, student.name, student.email)");
		return i;
		
	}

}
