package com.example.druzhininaj200.servlet;

import java.io.*;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.service.AddressesService;
import com.example.druzhininaj200.service.ClientsService;
import com.example.druzhininaj200.service.Filter;
import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "viewclients", value = "viewclients")
public class ViewClientsServlet extends HttpServlet {

    @EJB
    private AddressesService addressService;
    @EJB
    private ClientsService clientsService;
    Filter filter;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        html(request, response);
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        for (ClientsDTO clientsDTO : clientsService.findAll()) {
            out.println("<tr>");
            out.println("<td>" + (clientsDTO.getClientName() != null ? clientsDTO.getClientName() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getClientid() != null ? clientsDTO.getClientid() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getTypeclient() != null ? clientsDTO.getTypeclient() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getDatecreatclient() != null ? clientsDTO.getDatecreatclient() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getIp() != null ? clientsDTO.getIp() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getMac() != null ? clientsDTO.getMac() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getModel() != null ? clientsDTO.getModel() : "") + "</td>");
            out.println("<td>" + (clientsDTO.getAddress() != null ? clientsDTO.getAddress() : "") + "</td>");
            long id = clientsDTO.getClientid() !=null?clientsDTO.getClientid(): 0L;
            out.println("<td>" + "<form action=\"clientDeleteServlet\" method=\"post\">" +
                    "<p><input type=\"hidden\" name=\"button1\" value=\"" + id + "\"size =\"5\"</p>" +
                    "<p><input type=\"submit\" name=\"button11\" value=\"Удалить клиента \n и все его адреса\"</p></form>");
            out.println("<p><form action=\"UpdateClientServlet\" method=\"doGet\"><p>" +
                    "<p><input type=\"hidden\" name=\"button2\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>" +
                    "<p><input type=\"submit\" name=\"button22\" value=\"Обновить клиента\"</p></form>"+ "</td>");
            long adr = clientsDTO.getAddressid() !=null?clientsDTO.getAddressid(): 0L;
            out.println("<td>" + "<p><form action=\"addressDeleteServlet\" method=\"doGet\"><p>" +
                    "<p><input type=\"hidden\" name=\"button3\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>");
                    if(adr!=0){
             out.println("<p><input type=\"hidden\" name=\"button4\" value=\"" + adr + "\"size =\"5\"</p>" +
                    "<p><input type=\"submit\" id=\" disable_button \" value=\"Удалить адрес\"</p></form>");}
                    else {out.println("<p><input type=\"submit\" id=\" disable_button \" value=\"Удалить адрес\"disabled=\"disabled\"</p></form>");}
            out.println("<p><form action=\"updateAddressServlet\" method=\"doGet\"><p>" +
                    "<p><input type=\"hidden\" name=\"button5\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>");
            if (adr != 0) {
                out.println("<p><input type=\"hidden\" name=\"button6\" value=\"" + adr + "\"size =\"5\"</p>" +
                        "<p><input type=\"submit\" id=\" disable_button \" value=\"Обновить адреса клиента\"</p></form>" + "</td>");
            } else {
                out.println("<p><input type=\"submit\" id=\" disable_button \" value=\"Обновить адреса клиента\"disabled=\"disabled\"</p></form>" + "</td>");
            }
                    out.println("</tr>");
        }
            out.println("</table>");
            out.println("</body></html>");
        }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        html(request, response);
        request.setCharacterEncoding("UTF8");
        String typeFilter = request.getParameter("typeFilter");
        String dataFilter = request.getParameter("dataFilter");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        filter = new Filter();
            for (ClientsDTO clientsDTO : filter.filterType(typeFilter, filter.filterName(dataFilter,clientsService.findAll()))) {
                out.println("<tr>");
                out.println("<td>" + (clientsDTO.getClientName() != null ? clientsDTO.getClientName() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getClientid() != null ? clientsDTO.getClientid() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getTypeclient() != null ? clientsDTO.getTypeclient() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getDatecreatclient() != null ? clientsDTO.getDatecreatclient() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getIp() != null ? clientsDTO.getIp() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getMac() != null ? clientsDTO.getMac() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getModel() != null ? clientsDTO.getModel() : "") + "</td>");
                out.println("<td>" + (clientsDTO.getAddress() != null ? clientsDTO.getAddress() : "") + "</td>");
                long id = clientsDTO.getClientid() != null ? clientsDTO.getClientid() : 0L;
                out.println("<td>" + "<form action=\"clientDeleteServlet\" method=\"post\">" +
                        "<p><input type=\"hidden\" name=\"button1\" value=\"" + id + "\"size =\"5\"</p>" +
                        "<p><input type=\"submit\" name=\"button11\" value=\"Удалить клиента \n и все его адреса\"</p></form>");
                out.println("<p><form action=\"UpdateClientServlet\" method=\"doGet\"><p>" +
                        "<p><input type=\"hidden\" name=\"button2\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>" +
                        "<p><input type=\"submit\" name=\"button22\" value=\"Обновить клиента\"</p></form>"+ "</td>");
                long adr = clientsDTO.getAddressid() != null ? clientsDTO.getAddressid() : 0L;
                out.println("<td>" + "<p><form action=\"addressDeleteServlet\" method=\"doGet\"><p>" +
                        "<p><input type=\"hidden\" name=\"button3\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>");
                if (adr != 0) {
                    out.println("<p><input type=\"hidden\" name=\"button4\" value=\"" + adr + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" id=\" disable_button \" value=\"Удалить адрес\"</p></form>");
                } else {
                    out.println("<p><input type=\"submit\" id=\" disable_button \" value=\"Удалить адрес\"disabled=\"disabled\"</p></form>");
                }
                out.println("<p><form action=\"updateAddressServlet\" method=\"doGet\"><p>" +
                        "<p><input type=\"hidden\" name=\"button5\" value=\"" + clientsDTO.getClientid() + "\"size =\"5\"</p>");
                if (adr != 0) {
                    out.println("<p><input type=\"hidden\" name=\"button6\" value=\"" + adr + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" id=\" disable_button \" value=\"Обновить адреса клиента\"</p></form>" + "</td>");
                } else {
                    out.println("<p><input type=\"submit\" id=\" disable_button \" value=\"Обновить адреса клиента\"disabled=\"disabled\"</p></form>" + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        }

    public void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список клиентов</h1>");
        out.println("<form action=\"viewclients\" method=\"post\" >");
        out.println("<h1>Поиск</h1>");
        out.println("<p><label for=\"typeFilter\">Тип клиента:</label>\n" +
                "  <select id=\"typeFilter\" name=\"typeFilter\">\n" +
                "    <option value=\"\"></option>\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" +
                "  </select>" + "</p>");
        out.println("<p>Данные клиента: имя/адрес"
                + "<input type=\"text\"name=\"dataFilter\" " + "</p>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Поиск\" /><br>" + "</p>");
        out.println("</form>");
        out.println("</body></html>");
        out.println("<a href=\"viewclients\">Список всех клиентов</a>");
        out.println("<a href=\"creatServlet\">Добавить клиента</a>");
        out.println("<table cellspacing=\"7\" cellpadding=\"7\">");
        out.println("<style> " +
                "table { width: 70%; border-collapse: collapse; } " +
                "td, th { padding: 2px; " +
                "border: 1px solid #333; } " +
                "th { background: #cadadd; } " +
                 "td {height: 10px;} " +
                "tbody tr:hover {background: #334FFF; color: #000103}" + //цвета выделения мышкой
                "</style>");
        out.println("<tr><th>Имя клиента</th><th>ID</th><th>Тип клиента</th><th>Дата создания клиента</th><th>IP</th><th>MAC</th><th>Модель</th><th>Адрес</th></tr>");
    }
}