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

//    http://localhost:8080/ProBiblio/LibroServlet?txtId=4&operacion=editar&txtISBN=AB12-CD45-12&txtNombre=Lazarillo+De+Tornes&txtAutor=Tornes&txtDescripcion=Es+un+libro&txtCategoria=Educativo
    if (operacion.equals("editar") || operacion.equals("nuevo")) {
      ISBN = req.getParameter("txtISBN");
      nombre = req.getParameter("txtNombre");
      autor = req.getParameter("txtAutor");
      descripcion = req.getParameter("txtDescripcion");
      categoria = Categoria.valueOf(req.getParameter("txtCategoria"));
    } else {
    }

    LibroImpl libroImpl = new LibroImpl();
    if (operacion.equals("nuevo")) {
      Libro libroNuevo = new Libro(ISBN, nombre, autor, descripcion, categoria);
      libroImpl.insert(libroNuevo);
    }

    if (operacion.equals("editar")) {
      id = Integer.parseInt(req.getParameter("txtId"));


      Libro libroEditar = libroImpl.getById(id);
      libroEditar.setISBN(ISBN);
      libroEditar.setNombre(nombre);
      libroEditar.setAutor(autor);
      libroEditar.setDescripcion(descripcion);
      libroEditar.setCategoria(categoria);
      libroImpl.update(libroEditar);
    }

    if (operacion.equals("eliminar")) {
      id = Integer.parseInt(req.getParameter("txtId"));
      libroImpl.delete(id);
    }

    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
    rd.forward(req, res);

  }

}