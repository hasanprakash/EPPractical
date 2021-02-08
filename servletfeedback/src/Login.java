

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		Bean b = new Bean();
		b.setEmail(email);
		b.setPass(pass);
		DAO dao = new DAO();
		HttpSession session=request.getSession();  
        session.setAttribute("email", email);
		try {
			String i = dao.login(b);
			PrintWriter printWriter = response.getWriter();
			if(i == "") {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
				printWriter.println("<h3 class='green'>&lt;you didn't registered&gt;</h3>");
			}
			else {
				  
				RequestDispatcher rd = request.getRequestDispatcher("home.html");
				rd.include(request, response);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
