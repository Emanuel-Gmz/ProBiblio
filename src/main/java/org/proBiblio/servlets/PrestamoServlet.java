package org.proBiblio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proBiblio.dao.LibroImpl;
import org.proBiblio.dao.PrestamoImpl;
import org.proBiblio.entities.Categoria;
import org.proBiblio.entities.Libro;
import org.proBiblio.entities.Prestamo;
import org.proBiblio.entities.Usuario;

import java.io.IOException;

public class PrestamoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String fechaEntre = "";
        String fechaDevo = "";
        String usuario = null;
        String libro = null;
        String operacion = "nuevo";
        int id = -1;

        operacion = req.getParameter("operacion");

        if ("editar".equals(operacion) || "nuevo".equals(operacion)) {
            fechaEntre = req.getParameter("txtFEntre");
            fechaDevo = req.getParameter("txtFDebo");
            usuario = req.getParameter("txtUsuario");
            libro = req.getParameter("txtLibro");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        PrestamoImpl prestamoImpl = new PrestamoImpl();
        if ("nuevo".equals(operacion)) {
            Prestamo prestamoNuevo = new Prestamo(fechaEntre, fechaDevo, usuario, libro);
            prestamoImpl.insert(prestamoNuevo);
        }

        if ("editar".equals(operacion)) {
            Prestamo prestamoEditar = prestamoImpl.getById(id);
            prestamoEditar.setFechaEntre(fechaEntre);
            prestamoEditar.setFechaDevo(fechaDevo);
            prestamoEditar.setUsuario(Integer.parseInt(usuario));
            prestamoEditar.setLibro(Integer.parseInt(libro));
            prestamoImpl.update(prestamoEditar);
        }

        if ("eliminar".equals(operacion)) {
            prestamoImpl.delete(id);
        }

        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        rd.forward(req, res);

    }

  /* Supuestamente para actualizar la BD se usa esto
  @Override
  protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }

   */
}
