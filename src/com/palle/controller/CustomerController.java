package com.palle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.palle.dao.AdminDao;
import com.palle.dao.CustomerDao;
import com.palle.model.customer;

@WebServlet("/")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		switch (path) {
		case "/delete":
			deleteCustomer(request, response);
			break;
		case "/edit":
			updatecustomer(request, response);
			break;
		case "/editform":
			getEditForm(request, response);
			break;
		case "/insertForm":
			getInsertform(request, response);
			break;
		case "/add":
			addCustomer(request, response);
			break;
		case "/list":
			validateAdmin(request, response);
			break;
		case"/table":
			getcustomerlistpage(request,response);
			break;
		default:
			getStartUpPage(request, response);
			break;
		}

	}
	private void getcustomerlistpage(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
			ArrayList<customer> alcustomer = CustomerDao.getAllcustomer();
			RequestDispatcher rd = request.getRequestDispatcher("customer_list.jsp");
			request.setAttribute("al", alcustomer);

			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private void validateAdmin(HttpServletRequest request, HttpServletResponse response) {
		// Read the username and password
		
		String u = request.getParameter("tbuser");
		String p = request.getParameter("tbpass");

		// call the dao method to validate admin
		
		boolean res = AdminDao.validateAdmin(u, p);

		// condition to redirect admin to list page
		if (res) 
		{
			getcustomerlistpage(request,response);

		} else {
			try {
				response.sendRedirect(request.getContextPath()+"/default");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
		int i = Integer.parseInt(request.getParameter("id"));
		CustomerDao.deleteCustomer(i);
		
		// redirect user to customer list page

		getcustomerlistpage(request,response);

	}

	private void updatecustomer(HttpServletRequest request, HttpServletResponse response) {
		int i = Integer.parseInt(request.getParameter("tbId"));
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));

		customer c = new customer(i, n, e, m);
		CustomerDao.updatecustomer(c);
		// redirect user to customer list page
		getcustomerlistpage(request,response);

	}

	private void getEditForm(HttpServletRequest request, HttpServletResponse response) {
		// fetch the id from url:

		int i = Integer.parseInt(request.getParameter("id"));

		customer c = CustomerDao.editCustomer(i);

		try {

			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			request.setAttribute("customers", c);
			rd.forward(request, response);
		}

		catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getInsertform(HttpServletRequest request, HttpServletResponse response) {

		try {
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) {
		// Reading data from customer form page
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));

		// Store the Admin given data into model/object
		customer c = new customer(n, e, m);

		// Insert customer Data to DB
		CustomerDao.addcustomer(c);

		// Redirect Admin to HomePage
		getcustomerlistpage(request,response);
		
		

	}

	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) {

		try {

			RequestDispatcher rd = request.getRequestDispatcher("admin-login.jsp");
			rd.forward(request, response);
			

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
