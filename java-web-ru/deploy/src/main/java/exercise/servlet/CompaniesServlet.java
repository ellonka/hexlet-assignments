package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Struct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String valueOfParameter = request.getParameter("search");
        List<String> result = new LinkedList<>();

        if (request.getQueryString() == null || valueOfParameter == null) {
            result = getCompanies();
        } else {
            List<String> companies = (List<String>) getCompanies().stream()
                    .filter(x -> x.toString().contains(valueOfParameter))
                    .collect(Collectors.toList());
            if (companies.size() == 0) {
                out.println("Companies not found");
            } else {
                result = companies;
            }
        }

        for (String company: result) {
            out.println(company);
        }
        // END
    }
}
