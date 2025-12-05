package org.proBiblio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proBiblio.dao.LibroImpl;
import org.proBiblio.entities.Categoria;
import org.proBiblio.entities.Libro;

import java.io.IOException;

public class LibroServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    procesarSolicitud(req, res);
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String operacion = req.getParameter("operacion");
    if ("eliminar".equals(operacion)) {
      procesarSolicitud(req, res);
    } else {
      RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
      rd.forward(req, res);
    }
  }


  private void procesarSolicitud(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String ISBN = "";
    String nombre = "";
    Categoria categoria = null;
    String autor = "";
    String descripcion = "";
    String operacion = "nuevo";
    int id = -1;

    operacion = req.getParameter("operacion");

    LibroImpl libroImpl = new LibroImpl();

    if (operacion.equals("editar") || operacion.equals("nuevo")) {
      ISBN = req.getParameter("txtISBN");
      nombre = req.getParameter("txtNombre");
      autor = req.getParameter("txtAutor");
      descripcion = req.getParameter("txtDescripcion");
      String categoriaStr = req.getParameter("txtCategoria");
      if (categoriaStr != null && !categoriaStr.isEmpty()) {
        categoria = Categoria.valueOf(categoriaStr);
      }
    }

    if (operacion.equals("nuevo")) {
      if (ISBN != null && nombre != null) {
        Libro libroNuevo = new Libro(ISBN, nombre, autor, descripcion, categoria);
        libroImpl.insert(libroNuevo);
      }
    }

    if (operacion.equals("editar")) {
      String idStr = req.getParameter("txtId");
      if (idStr != null) {
        try {
          id = Integer.parseInt(idStr);
          Libro libroEditar = libroImpl.getById(id);
          if (libroEditar != null) {
            libroEditar.setISBN(ISBN);
            libroEditar.setNombre(nombre);
            libroEditar.setAutor(autor);
            libroEditar.setDescripcion(descripcion);
            libroEditar.setCategoria(categoria);
            libroImpl.update(libroEditar);
          }
        } catch (NumberFormatException e) {
          System.out.println("Error de formato en ID al editar: " + idStr);
        }
      }
    }


    if (operacion.equals("eliminar")) {
      String idStr = req.getParameter("id");
      if (idStr == null) {
        idStr = req.getParameter("txtId");
      }

      if (idStr != null) {
        try {
          id = Integer.parseInt(idStr);
          libroImpl.delete(id);
        } catch (NumberFormatException e) {;
        }
      }
    }

    res.sendRedirect(req.getContextPath() + "/index.jsp");
  }
}