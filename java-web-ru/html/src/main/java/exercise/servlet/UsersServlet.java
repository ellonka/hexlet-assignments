package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws IOException {
        // BEGIN
        String filePath = "src/main/resources/users.json";
        String data = Files.readString(Paths.get(filePath).toAbsolutePath());

        JsonFactory factory = new JsonFactory();
        TypeReference<List<Map<String, String>>> token = new TypeReference<>() {
        };
        ObjectMapper mapper = new ObjectMapper(factory);
        return mapper.readValue(data, token);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder pageWithTable = new StringBuilder();
        pageWithTable.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <style>
                    table, th, td {
                      border: 1px solid black;
                    }
                    </style>
                    <body>
                    <div class=\"container\">
                        <table style="width:50%">
                            <tr>
                                <th>id</th>
                                <th>full name</th>
                            </tr>
                """);
        for (Map user: users) {
            pageWithTable.append("<tr>"
                    + "<td>" + user.get("id") + "</td>"
                    + "<td>" + "<a href=\"/users/" + user.get("id") + "\">"
                    + user.get("firstName") + " " + user.get("lastName") + "</a></td>"
                    + "</tr>");
        }
        pageWithTable.append("""
                            </table>
                        </div>
                    </body>
                </html>
                """);
        PrintWriter out = response.getWriter();
        out.println(pageWithTable);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {
        // BEGIN
        Map<String, String> user = getUser(getUsers(), id);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder pageWithUser = new StringBuilder();
        pageWithUser.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <style>
                    table, th, td {
                      border: 1px solid black;
                    }
                    </style>
                    <body>
                    <div class=\"container\">
                        <table style="width:50%">
                            <tr>
                                <th>id</th>
                                <th>first name</th>
                                <th>last name</th>
                                <th>email</th>
                            </tr>
                """);
        pageWithUser.append("<tr>"
                + "<td>" + user.get("id") + "</td>"
                + "<td>" + user.get("firstName") + "</td>"
                + "<td>" + user.get("lastName") + "</td>"
                + "<td>" + "<a href=\"mailto:" + user.get("email") + "\">" + user.get("email") + "</a></td>"
                + "</tr>");
        pageWithUser.append("""
                            </table>
                        </div>
                    </body>
                </html>
                """);
        PrintWriter out = response.getWriter();
        out.println(pageWithUser);
        // END
    }

    private Map<String, String> getUser(List<Map<String, String>> users, String id) {
        Map<String, String> user = users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);

        return user;
    }
}
