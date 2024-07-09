package com.example.druzhininaj200.servlet;

import com.example.druzhininaj200.dto.ClientsDTO;
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
import java.sql.Date;

@WebServlet(name = "creatServlet", value = "/creatServlet")
public class CreatServlet extends HttpServlet {
    @EJB
    ClientsService clientsService;
    @EJB
    UniControl uniControl;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<title>Создание клиента</title>");
        out.println("<h1>Создание клиента</h1>");
        out.println("<html><head>");
        out.println("<html><body>");
        out.println("<form action=\"creatServlet\" method=\"post\" >");
        out.println("<p>" + "Имя клиента"
                +"<input type=\"text\" " +
                "pattern=\"[а-яёА-ЯЁ, .\\-]{1,100}\"" +
                "name=\"client_name\" " +
                "required >" +"</p>");
        out.println("<p>" + "Тип клиента" +
                "<label for=\"type\">Тип клиента:</label>\n" +
                "  <select id=\"type\" name=\"type\">\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" +
                "  </select>"+"</p>");
        out.println("<p>Дата создания клиента \n" +
                "<input type=\"date\" " +
                "list=\"dateList\"" +
                "name=\"date\"" +
                "required/>"+"</p>");
        out.println("<p>" + "IP " +
                "<input type=\"text\"" +
                " pattern=\"^([0-1][\\d][\\d].|2[0-4][\\d].|25[0-5].){3,3}([0-1][\\d][\\d]|2[0-4][\\d]|25[0-5])\" " +
                "placeholder= \"255.255.255.255\"" +
                "name=\"IP\"" +
                "required/>"+"</p>");
        out.println("<p>" + "MAC " +
                "<input type=\"text\" " +
                " pattern=\"([\\dA-Za-z]{1,2}-){5,5}([\\dA-Za-z]{1,2})\" " +
                "placeholder= \"1A-1B-2C-4E-8H-9\"" +
                "name=\"MAC\"" +
                "required/>"+"</p>");
        out.println("<p>" + "Модель устройства"
                +"<input type=\"text\" " +
                "pattern=\".{1,100}\"" +
                "name=\"model\" " +
                "required >" +"</p>");
        out.println("<p>" + "Адрес местонахождения устройства"
                +"<input type=\"text\" " +
                "pattern=\".{1,200}\"" +
                "name=\"address\" " +
                "required >" +"</p>");
        out.println("<p>"+"<input type=\"submit\" name=\"signin\" value=\"Ввести данные\" /><br>"+"</p>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        String client_name = request.getParameter("client_name");
        String type = request.getParameter("type");
        Date date = Date.valueOf(request.getParameter("date"));
        String IP = request.getParameter("IP");
        String mac = request.getParameter("MAC");
        String model = request.getParameter("model");
        String address = request.getParameter("address");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        if (!uniControl.CreatControl(client_name,IP,mac)) {
            
            doGet(request, response);

        } else {
            if (uniControl.clientUniControl(client_name, type, clientsService.findAll()) != 0L) {
                out.println("<html><body>");
                out.println("<h1>Клиент существует</h1>");
                out.println("<h1>" + uniControl.clientUniControl(client_name, type, clientsService.findAll()) + "</h1>");
                request.getSession().setAttribute("clientID", uniControl.clientUniControl(client_name, type, clientsService.findAll()));
                out.println("<a href=\"UpdateClientServlet\">Изменить клиента</a>");
                out.println("<a href=\"creatServlet\">Создать клиента</a>");
                out.println("<a href=\"viewclients\">Список клиентов</a>");
                return;
            } else {
                clientsService.create(ClientsDTO.builder()
                        .clientName(client_name)
                        .typeclient(type)
                        .datecreatclient(date)
                        .build());

                request.setAttribute("client_name", client_name);
                request.setAttribute("type", type);
                request.setAttribute("IP", IP);
                request.setAttribute("MAC", mac);
                request.setAttribute("model", model);
                request.setAttribute("address", address);
                request.getServletContext().getRequestDispatcher("/creatAddressServlet").forward(request, response);
            }
        }
    }
    }

