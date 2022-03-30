<%@page import="com.emergentes_modelo.persona"%>
<%
    persona p = (persona) request.getAttribute("mipersona");
%>
<%%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1><%= (p.getId() == 0) ? "Nuevoo registro" : "Editar registro"%>Nuevo Registro</h1>

        <form action="MainController" method="POST">

            <input type="hidden" name="id" value="<%= p.getId()%>">

            <table>
                <tr>
                    <td>Nombres</td>
                    <td><input type="text" name="nombres" value=" <%= p.getNombres()%>"></td>
                </tr>
                <tr>
                    <td>Apellidos</td>
                    <td><input type="text" name="apellidos" value="<%= p.getApellidos()%>"></td>
                </tr>
                <tr>
                    <td>Edad</td>
                    <td><input type="number" name="edad" value="<%= p.getEdad()%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>

    </body>
</html>
