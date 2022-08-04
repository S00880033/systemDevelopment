package jp.ac.isc.cloud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Connection users = null;
		try {
			//request.setCharacterEncoding("utf-8");
			users = DBConnection.openConnection();
			String id = request.getParameter("updateId");
			String name = request.getParameter("updateName");
			String picture = request.getParameter("updatePicture");
			Statement state = users.createStatement();
			if(name.length() !=0) {
				state.executeUpdate("UPDATE user_table SET name='"+ name +"' WHERE id='" + id + "' ");
			}
			if(picture.length() !=0) {
				state.executeUpdate("UPDATE user_table SET picture='"+ picture +"' WHERE id='" + id + "' ");
			}
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select"); // UserSectSevletを呼び出す
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
