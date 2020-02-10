package com.sarthak.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set up connection variables
		String user = "sarthak";
		String pass = "database";

		String jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSl=false&serverTimezone=UTC";
		String driver = "com.mysql.jdbc.Driver";

		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			out.println("Hey i am connecting to database: " + jdbcURL);
			Class.forName(driver);
			Connection myConn = DriverManager.getConnection(jdbcURL, user, pass);
			out.println("Connection Sucessful");
			myConn.close();

		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

}
