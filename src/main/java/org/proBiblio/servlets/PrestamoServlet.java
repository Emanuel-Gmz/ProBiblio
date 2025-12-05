package org.proBiblio.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proBiblio.dao.PrestamoImpl;
import org.proBiblio.entities.Libro;
import org.proBiblio.entities.Prestamo;
import org.proBiblio.entities.Usuario;

import java.io.IOException;
import java.sql.Date;

public class PrestamoServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    procesar(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    procesar(req, resp);
  }

  private void procesar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String operacion = req.getParameter("operacion");
    PrestamoImpl prestamoDAO = new PrestamoImpl();

    HttpSession session = req.getSession();
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuarioLogueado == null) {
      resp.sendRedirect(req.getContextPath() + "/login.jsp");
      return;
    }

    if ("solicitar".equals(operacion)) {
      try {
        String idLibroStr = req.getParameter("idLibro");
        if (idLibroStr != null) {
          int idLibro = Integer.parseInt(idLibroStr);
          int idUsuario = usuarioLogueado.getIdUsuario();

          Prestamo p = new Prestamo();
          p.setFechaPrestamo(new Date(System.currentTimeMillis()));
          p.setEstado("ACTIVO");

          Usuario u = new Usuario();
          u.setIdUsuario(idUsuario);
          p.setUsuario(u);

          Libro l = new Libro();
          l.setIdLibro(idLibro);
          p.setLibro(l);

          prestamoDAO.registrarPrestamo(p);

          resp.sendRedirect(req.getContextPath() + "/listaPrestamos.jsp?mensajeExito=Prestamo solicitado correctamente");
        }
      } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect(req.getContextPath() + "/listaLibros.jsp?mensajeError=Error al solicitar prestamo");
      }

    } else if ("devolver".equals(operacion)) {


      String rol = (String) session.getAttribute("rol");

      if ("ADMIN".equals(rol) || "BIBLIOTECARIO".equals(rol)) {
        try {
          int idPrestamo = Integer.parseInt(req.getParameter("idPrestamo"));
          prestamoDAO.registrarDevolucion(idPrestamo);

          resp.sendRedirect(req.getContextPath() + "/listaPrestamos.jsp?mensajeExito=Devolucion registrada correctamente");
        } catch (Exception e) {
          e.printStackTrace();
          resp.sendRedirect(req.getContextPath() + "/listaPrestamos.jsp?mensajeError=Error al registrar devolucion");
        }
      } else {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
      }

    } else {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }
}