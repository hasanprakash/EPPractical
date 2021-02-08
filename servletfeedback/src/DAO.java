import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

public class DAO {
	
	public int Insertion(Bean b) throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.DBConnection();
		PreparedStatement ps = con.prepareStatement("insert into servletregister values(?, ?, ?, ?, ?, ?)");
		ps.setInt(1, b.getRegno());
		ps.setString(2, b.getName());
		ps.setString(3, b.getEmail());
		ps.setString(4, b.getPass());
		ps.setInt(5, b.getYear());
		ps.setInt(6, b.getSec());
		if(b.getPass().length() < 8) {
			return 0;
		}
		int i = ps.executeUpdate();
		return i;
	}
	
	public String login(Bean b) throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.DBConnection();
		PreparedStatement ps = con.prepareStatement("select email, password from servletregister where email=? and password=?");
		ps.setString(1, b.getEmail());
		ps.setString(2, b.getPass());
		ResultSet i = (ResultSet) ps.executeQuery();
//		System.out.println(i.getString(1));
//		System.out.println(i.getString(2));
		String email = "", pass = "";
		while(i.next()) {
			email = i.getString(1);
			pass = i.getString(2);
		}
		return email;
	}
	
	public int insertFeedback(Bean b) throws ClassNotFoundException, SQLException {
		
		Connection con = DBUtil.DBConnection();
		PreparedStatement ps = con.prepareStatement("insert into feedbacks values(?, ?)");
		ps.setString(1, b.getEmail());
		ps.setString(2, b.getFeedback());
		if(b.getEmail().length()<1) {
			return 0;
		}
		return ps.executeUpdate();
		
	}

}
