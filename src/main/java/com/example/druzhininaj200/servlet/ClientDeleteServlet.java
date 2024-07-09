package com.example.druzhininaj200.servlet;

import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.service.ClientsService;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "clientDeleteServlet", value = "/clientDeleteServlet")
public class ClientDeleteServlet extends HttpServlet {
    @EJB
    private ClientsService clientsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("button1"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (id != 0) {
            clientsService.remove(ClientsDTO.builder().clientid(id).build());
            out.println("<h1>Удаление произошло</h1>");
            out.println("<h1>" + id + "</h1>");
            out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("button1"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        clientsService.remove(ClientsDTO.builder().clientid(id).build());
        out.println("<h1>Удаление произошло</h1>");
        out.println("<h1>" + id + "</h1>");
        out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
        out.println("</body></html>");
    }
}