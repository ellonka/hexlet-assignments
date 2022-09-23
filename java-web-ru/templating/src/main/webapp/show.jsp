<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language = "java" %>
<!DOCTYPE html>
<html lang=\"ru\">
    <head>
    <meta charset=\"UTF-8\">
    <title>Example</title>
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
                <tr>
                    <th>${user.get("id")}</th>
                    <th>${user.get("firstName")}</th>
                    <th>${user.get("lastName")}</th>
                    <th><a href='mailto:${user.get("email")}'>${user.get("email")}</a></th>
                </tr>
            </table>
            <br/>
            <a href='/users/delete?id=${user.get("id")}'>Удалить пользователя?</a>
        </div>
    </body>
</html>
<!-- END -->
