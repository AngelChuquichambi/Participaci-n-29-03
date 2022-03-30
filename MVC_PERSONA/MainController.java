package com.emergentes_modelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String opcion = (op != null) ? op : "view";

        HttpSession sesion = request.getSession();

        if (sesion.getAttribute("listaper") == null) {
            ArrayList<persona> aux = new ArrayList<persona>();
            sesion.setAttribute("listaper", aux);
        }

        ArrayList<persona> lista = (ArrayList<persona>) sesion.getAttribute("listaper");

        persona p = new persona();
        int id, pos;

        switch (opcion) {
            case "nuevo":
                request.setAttribute("mipersona", p);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;

            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = BuscarIndice(request, id);
                p = lista.get(pos);
                request.setAttribute("mipersona", p);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;

            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = BuscarIndice(request, id);
                lista.remove(pos);
                sesion.setAttribute("listaper", lista);
                response.sendRedirect("index.jsp");
                break;

            case "view":
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        ArrayList<persona> lista = (ArrayList<persona>) sesion.getAttribute("listaper");

        persona p = new persona();

        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setNombres(request.getParameter("nombres"));
        p.setApellidos(request.getParameter("apellidos"));
        p.setEdad(Integer.parseInt(request.getParameter("edad")));

        int i = p.getId();

        if (i == 0) {
            int ultId = ultimoId(request);
            p.setId(ultId);
            lista.add(p);
        } else {
            //Edicion
            lista.set(BuscarIndice(request, i), p);
        }

        sesion.setAttribute("listaper", lista);
        response.sendRedirect("index.jsp");

    }

    private int BuscarIndice(HttpServletRequest request, int id) {
        HttpSession sesion = request.getSession();
        ArrayList<persona> lista = (ArrayList<persona>) sesion.getAttribute("listaper");

        int i = 0;

        if (lista.size() > 0) {
            while (i < lista.size()) {
                if (lista.get(i).getId() == id) {
                    break;
                } else {
                    i++;
                }
            }
        }
        return i;
    }

    private int ultimoId(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        ArrayList<persona> lista = (ArrayList<persona>) sesion.getAttribute("listaper");

        int idaux = 0;

        for (persona p : lista) {
            idaux = p.getId();
        }
        return idaux;
    }
}
