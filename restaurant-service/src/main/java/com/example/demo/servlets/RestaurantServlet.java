package com.example.demo.servlets;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.repos.RestaurantRepository;

import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.ElementNotFoundException;
import com.training.ifaces.CrudRepository;
import com.training.model.Restaurant;


@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RestaurantServlet() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		request.setAttribute("heading", "Restaurants");
		RequestDispatcher dispatcher = request.getRequestDispatcher("addRestaurant.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		String operation=request.getParameter("oper");
		System.out.println(operation);
		
		Connection con = ConnectionFactory.getMySqlConnection();
		CrudRepository<Restaurant> repo = new RestaurantRepository(con);
		int id =Integer.parseInt(request.getParameter("id"));
		
		String restaurantName=request.getParameter("restaurantName");
		
		String strPincode=request.getParameter("pincode");
		Long pincode=Long.parseLong(strPincode);
		
		String cuisine=request.getParameter("cuisine");
		
		Restaurant obj = new Restaurant(id,restaurantName,pincode,cuisine);
		switch (operation) {
		
		case "ADD":
			String add=repo.save(obj);
			request.setAttribute("State", add);
			RequestDispatcher dispat = request.getRequestDispatcher("addRestaurant.jsp");
		    dispat.forward(request, response);
			break;
		case "DELETE":
			try {
				
				String del=repo.delete(obj);
		
				request.setAttribute("State", del);
				RequestDispatcher dispatcher = request.getRequestDispatcher("addRestaurant.jsp");
			    dispatcher.forward(request, response);
				
			} catch (ElementNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW RESTAURANTS":
			List<Restaurant> list=repo.findAll();
			request.setAttribute("list", list);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("showRestaurants.jsp");
		    dispatcher.forward(request, response);
			break;

		default:
			break;
		}       
	
	}

}
