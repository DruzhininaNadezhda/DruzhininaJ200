package com.example.druzhininaj200.servlet;

import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.service.AddressesService;
import com.example.druzhininaj200.service.ClientsService;
import com.example.druzhininaj200.service.UniControl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "updateAddressServlet", value = "/updateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {

    @EJB
    private ClientsService clientsService;
    @EJB
    private AddressesService addressesService;
    @EJB
    UniControl uniControl;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        Long clientID =0l;
        if (request.getParameter("button5")!=null) {
            clientID = Long.parseLong(request.getParameter("button5"));
        }else {
            clientID = Long.parseLong(Objects.toString(request.getSession().getAttribute("clientID")));
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<table cellspacing=\"3\" cellpadding=\"3\" border=\"1\">");
        out.println("<tr>");
        out.println("<th>IP</th>");
        out.println("<th>MAC</th>");
        out.println("<th>Модель</th>");
        out.println("<th>Адрес</th>");
        out.println("</tr>");
        //for (ClientsDTO client : clientsService.findAll()) {
            // if (!client.getIp().isEmpty()) {
            for (AddressesDTO address1 : addressesService.findAll()) {
                if (address1.getClient() == clientID) {
                    out.println("<td>" + address1.getIp() + "</td>");
                    out.println("<td>" + address1.getMac() + "</td>");
                    out.println("<td>" + address1.getModel() + "</td>");
                    out.println("<td>" + address1.getAddress() + "</td>");
                    out.println("<p><form action=\"addressDeleteServlet\" method=\"post\"><p>" +
                            "<p><input type=\"hidden\" name=\"button3\" value=\"" + clientID + "\"size =\"5\"</p>");
                    out.println("<td><p><input type=\"hidden\" name=\"button4\" value=\"" + address1.getAddressid() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" id=\" disable_button \" value=\"Удалить адрес\"</p></form>" + "</td>");
                    out.println("</tr>");
                }
                //    }
          //  }
        }
        out.println("<form action=\"updateAddressServlet\" method=\"post\" >");
        out.println("<p>" + "ID клиента" +
                "<input type=\"number\" " + "value=\"" +clientID + "\"" +
                "name=\"clientID\" " +
                "required readonly/>" + "</p>");
        out.println("<p>" + "IP " +
                "<input type=\"text\"" +
                " pattern=\"^([0-1][\\d][\\d].|2[0-4][\\d].|25[0-5].){3,3}([0-1][\\d][\\d]|2[0-4][\\d]|25[0-5])\" " +
                "placeholder= \"255.255.255.255\"" +
                "name=\"IP\"" +
                "required/>" + "</p>");
        out.println("<p>" + "MAC " +
                "<input type=\"text\" " +
                " pattern=\"([\\dA-Za-z]{1,2}-){5,5}([\\dA-Za-z]{1,2})\" " +
                "placeholder= \"1A-1B-2C-4E-8H-9\"" +
                "name=\"MAC\"" +
                "required/>" + "</p>");
        out.println("<p>" + "Модель устройства"
                + "<input type=\"text\" " +
                "pattern=\".{1,100}\"" +
                "name=\"model\" " +
                "required >" + "</p>");
        out.println("<p>" + "Адрес местонахождения устройства"
                + "<input type=\"text\" " +
                "pattern=\".{1,200}\"" +
                "name=\"address\" " +
                "required >" + "</p>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Добавить/изменить адрес\" /><br>" + "</p>");
        out.println("<a href=\"UpdateClientServlet\">Внести изменения</a>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        Long clientID = Long.parseLong(request.getParameter("clientID"));
        String IP = request.getParameter("IP");
        String mac = request.getParameter("MAC");
        String model = request.getParameter("model");
        String address = request.getParameter("address");
        //Long ID;
        if (uniControl.addressUniControl(IP, clientID, addressesService.findAll())== 0L) {
            addressesService.create(AddressesDTO.builder()
                    .client(clientID)
                    .ip(IP)
                    .mac(mac)
                    .model(model)
                    .address(address)
                    .build());
            doGet(request, response);
        } else {
            addressesService.update(AddressesDTO.builder()
                    .addressid(uniControl.addressUniControl(IP, clientID, addressesService.findAll()))
                    .client(clientID)
                    .ip(IP)
                    .mac(mac)
                    .model(model)
                    .address(address)
                   .build());
            doGet(request, response);
        }
    }
}

