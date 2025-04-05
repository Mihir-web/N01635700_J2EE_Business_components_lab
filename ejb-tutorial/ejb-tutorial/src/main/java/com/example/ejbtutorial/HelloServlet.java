package com.example.ejbtutorial;

import com.example.ejb.CalculatorBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @EJB
    private CalculatorBean calculatorBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sum = calculatorBean.add(10, 5);
        int difference = calculatorBean.subtract(10, 5);
        int product = calculatorBean.multiply(10, 5);
        double quotient = calculatorBean.divide(10, 5);

        response.getWriter().println("10 + 5 = " + sum);
        response.getWriter().println("10 - 5 = " + difference);
        response.getWriter().println("10 * 5 = " + product);
        response.getWriter().println("10 / 5 = " + quotient);
    }
}
