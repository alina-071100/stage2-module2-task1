package com.example.servlet;


import com.example.User;
import com.example.Warehouse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/add.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        if (firstName.isEmpty() || lastName.equals("")){
            throw new RuntimeException();
        }
        User user = new User(firstName, lastName);
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addUser(user);

        req.setAttribute("userName", firstName);
        req.setAttribute("lastName", lastName);
        doGet(req, resp);
    }
}
