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
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    String ISBN = "";
    String nombre = "";
    Categoria categoria = null;
    String autor = "";
    String descripcion = "";
    String operacion = "nuevo";
    int id = -1;

    operacion = req.getParameter("operacion");

    if ("editar".equals(operacion) || "nuevo".equals(operacion)) {
      ISBN = req.getParameter("txtISBN");
      nombre = req.getParameter("txtNombre");
      autor = req.getParameter("txtAutor");
      descripcion = req.getParameter("txtDescripcion");
      categoria = Categoria.valueOf(req.getParameter("txtCategoria"));
    } else {
      id = Integer.parseInt(req.getParameter("id"));
    }

    LibroImpl libroImpl = new LibroImpl();
    if ("nuevo".equals(operacion)) {
      Libro libroNuevo = new Libro(ISBN, nombre, autor, descripcion, categoria);
      libroImpl.insert(libroNuevo);
    }

    if ("editar".equals(operacion)) {
      Libro libroEditar = libroImpl.getById(id);
      libroEditar.setISBN(ISBN);
      libroEditar.setNombre(nombre);
      libroEditar.setAutor(autor);
      libroEditar.setDescripcion(descripcion);
      libroEditar.setCategoria(categoria);
      libroImpl.update(libroEditar);
    }

    if ("eliminar".equals(operacion)) {
      libroImpl.delete(id);
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