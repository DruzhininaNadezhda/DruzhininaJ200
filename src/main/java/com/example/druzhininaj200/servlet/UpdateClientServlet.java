package com.example.druzhininaj200.servlet;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.service.ClientsService;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Objects;

@WebServlet(name = "UpdateClientServlet", value = "/UpdateClientServlet")
public class UpdateClientServlet extends HttpServlet {
    @EJB
    ClientsService clientsService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        Long clientID =0l;
        if (request.getParameter("button2")!=null) {
            clientID = Long.parseLong(request.getParameter("button2"));
        }else {
            clientID = Long.parseLong(Objects.toString(request.getSession().getAttribute("clientID")));
        }
        ClientsDTO client=clientsService.findClientsDtoById(clientID);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(" ");
        out.println("<form action=\"UpdateClientServlet\" method=\"post\" >");
        out.println("<h1>Обновление клиента</h1>");
        out.println("<p>" + "Имя клиента"
                + "<input type=\"text\" value=\"" + client.getClientName() + "\"" +
                "pattern=\"[а-яёА-ЯЁ, .\\-]{1,100}\"" +
                "name=\"client_name\" " +
                "required >" + "</p>");
        out.println("<p>" + "ID " +
                "<input type=\"number\" " + "value=\"" + client.getClientid() + "\"" +
                "name=\"clientID\" " +
                "required readonly/>" + "</p>");
        out.println("<p>" + "Тип клиента" + client.getTypeclient() +
                "<label for=\"type\"></label>\n" +
                "  <select id=\"type\" name=\"type\">\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" + "value=\"" + client.getTypeclient() + "\"" +
                "  </select>" + "</p>");
        out.println("<p>Дата создания клиента \n" +
                "<input type=\"date\" " + "value=\"" + client.getDatecreatclient() + "\"" +
                "list=\"dateList\"" +
                "name=\"date\"" +
                "required/>" + "</p>");
        request.getSession().setAttribute("clientID", clientID);
        out.println("<a href=\"updateAddressServlet\">Просмотр и добавление адресов</a>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Внести изменения\" /><br>" + "</p>");
        out.println("</form>");
        out.println("<a href=\"viewclients\">Список клиентов</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        Long clientID = Long.parseLong(request.getParameter("clientID"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        String client_name = request.getParameter("client_name");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
                clientsService.update(ClientsDTO.builder()
                        .clientid(clientID)
                        .clientName(client_name)
                        .datecreatclient(Date.valueOf(date))
                        .typeclient(type).build());
                out.println("<html><body>");
                out.println("<h1>Обновление произошло</h1>");
                out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
                out.println("</body></html>");

        }
    }