package com.example.druzhininaj200.servlet;

import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.service.AddressesService;
import com.example.druzhininaj200.service.ClientsService;
import com.example.druzhininaj200.service.UniControl;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "creatAddressServlet", value = "/creatAddressServlet")
public class CreatAddressServlet extends HttpServlet {
    @EJB
    AddressesService addressesService;
    @EJB
    UniControl uniControl;
    @EJB
    ClientsService clientsService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = Objects.toString(request.getAttribute("type"));
        String client_name = Objects.toString(request.getAttribute("client_name"));
        String IP = Objects.toString(request.getAttribute("IP"));
        String MAC = Objects.toString(request.getAttribute("MAC"));
        String model = Objects.toString(request.getAttribute("model"));
        String address = Objects.toString(request.getAttribute("address"));
        long clientID = uniControl.clientUniControl(client_name, type,clientsService.findAll());
        addressesService.create(AddressesDTO.builder()
                .client(clientID)
                .ip(IP)
                .mac(MAC)
                .model(model)
                .address(address)
                .build());
        PrintWriter out = response.getWriter();
        out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0.1;url=viewclients\">");
    }
}
