/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hillel.javapro.ewallet_servlet1.persistence;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Controller extends HttpServlet {

    private Integer count = 1;

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write("<HTML><HEAD>"
                    + "<TITLE> Servlet counter </TITLE>"
                    + "</HEAD><BODY>");
            writer.write("Counter (common for all users) = " + (count++) + "</br>"); // counter would be same for all users
            writer.write("Counter (individual by session) = " + counterOnSession(request) + "</br>");
            writer.write("<a href=\"/\">Home</a>");
            writer.write("</BODY></HTML>");
        }
    }

    private Integer counterOnSession(HttpServletRequest request) {
        Integer count = (Integer) Optional.ofNullable( request.getSession().getAttribute("counter")).orElse(0);
        count++;
        request.getSession().setAttribute("counter", count);
        return count;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }


}
