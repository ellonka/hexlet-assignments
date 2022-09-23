package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {
    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        int articlesPerPage = 10;
        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();

        int numberOfArticles;
        try {
            String query0 = "SELECT COUNT(*) FROM articles;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query0);
            result.next();
            numberOfArticles = result.getInt("count(*)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String textPage = request.getParameter("page");
        int page;
        if (textPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(textPage);
        }
        if (page <= 0 || page > numberOfArticles / articlesPerPage + 1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String query = "SELECT * FROM articles ORDER BY id LIMIT ? OFFSET ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, articlesPerPage);
            preparedStatement.setInt(2, articlesPerPage * (page - 1));
            ResultSet resultSet = preparedStatement.executeQuery();

            //добыли из бд 10 статей и записываем их в список
            while (resultSet.next()) {
                articles.add(Map.of(
                        "id", resultSet.getString("id"),
                        "title", resultSet.getString("title"),
                        "body", resultSet.getString("body")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", page);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Map<String, String> article = new HashMap<>();
        String id = getId(request);
        String query0 = "SELECT MAX(id) FROM articles;";
        int maxId;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query0);
            result.next();
            maxId = result.getInt("max(id)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Integer.parseInt(id) > maxId) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String query = "SELECT title, body FROM articles WHERE id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            //добыли из бд статью, записываем ее
            while (resultSet.next()) {
                article.put("title", resultSet.getString("title"));
                article.put("body", resultSet.getString("body"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
