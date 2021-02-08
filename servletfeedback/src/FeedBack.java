

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter printWriter = response.getWriter();
		HttpSession session=request.getSession(false); 
		session.setAttribute("email", "");
		request.getRequestDispatcher("index.html").include(request, response);
		printWriter.print("You LOGGED OUT");
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String feedback = request.getParameter("subject");
		Bean b = new Bean();
		b.setFeedback(feedback);
		HttpSession session=request.getSession(false);  
        String email=(String)session.getAttribute("email"); 
        PrintWriter printWriter = response.getWriter();
        if(email == null || email == "") {
        	request.getRequestDispatcher("home.html").include(request, response);
        	printWriter.println("You should login first");
        }
        else {
        b.setEmail(email);
		DAO dao = new DAO();
		
		try {
			int i = dao.insertFeedback(b);
			if(i>0) {
				request.getRequestDispatcher("output.html").include(request, response);
				printWriter.println(feedback);
			}
			else if(i==0) {
				request.getRequestDispatcher("home.html").include(request, response);
	        	printWriter.println("You should login first");
			}
			else {
				printWriter.println("Something WentWrong");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        
		
	}

}
