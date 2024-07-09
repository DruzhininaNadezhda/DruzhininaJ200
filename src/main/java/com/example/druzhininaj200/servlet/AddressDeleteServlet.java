package com.example.druzhininaj200.servlet;

import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.ClientsEntity;
import com.example.druzhininaj200.service.AddressesService;
import com.example.druzhininaj200.service.ClientsService;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addressDeleteServlet", value = "/addressDeleteServlet")
public class AddressDeleteServlet extends HttpServlet {
    @EJB
    private AddressesService addressesService;
    @EJB
    private ClientsService clientsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("button4"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (id != 0) {
            addressesService.remove(AddressesDTO.builder().addressid(id).build());
            out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0.1;url=viewclients\">");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("button4"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (id != 0) {
            addressesService.remove(AddressesDTO.builder().addressid(id).build());
            out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0.1;url=updateAddressServlet\">");
            out.println("</body></html>");
        }
    }
}