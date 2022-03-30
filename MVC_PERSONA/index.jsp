<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes_modelo.persona"%>
<%
    ArrayList<persona> lista = (ArrayList<persona>) session.getAttribute("listaper");

%>
<%
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Listado de Personas</h1>

        <a href="MainController?op=nuevo"><input type ="submit" value="Nuevo Registro"></a><br><br>

        <table border="1" ellpadding="5">
            <tr>
                <th>ID</th><th>NOMBRS</th><th>APELLIDOS</th><th>EDAD</th><th>EDITAR</th><th>ELIMINAR</th>
            </tr>
            <%                if (lista != null) {
                    for (persona p : lista) {

            %>
            <tr>
                <td> <%= p.getId()%></td>
                <td> <%= p.getNombres()%></td>
                <td> <%= p.getApellidos()%></td>
                <td> <%= p.getEdad()%></td>
                <td> <a href="MainController?op=editar&id=<%= p.getId()%>">Editar</a></td>
                <td> <a href="MainController?op=eliminar&id=<%= p.getId()%>" onclick="return confirm('Esta seguro de eliminar el registro ? ');">Eliminar</a></td>
            </tr>
            <%                }
                }
            %>

        </table>


    </body>
</html>
