package org.proBiblio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proBiblio.dao.UsuarioImpl;
import org.proBiblio.entities.Rol;
import org.proBiblio.entities.Usuario;

import java.io.IOException;

public class UsuarioServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    String nombre = "";
    String apellido = "";
    String telefono = "";
    String contrasenia = "";
    Rol rol = null;
    String operacion = "nuevo";
    int id = -1;

    operacion = req.getParameter("operacion");

    if ("editar".equals(operacion) || "nuevo".equals(operacion)) {
      nombre = req.getParameter("txtNombre");
      apellido = req.getParameter("txtApellido");
      telefono = req.getParameter("txtTelefono");
      contrasenia = req.getParameter("txtContrasenia");
      rol = Rol.valueOf(req.getParameter("txtRol"));
    } else {
      id = Integer.parseInt(req.getParameter("id"));
    }

    UsuarioImpl usuarioImpl = new UsuarioImpl();
    if ("nuevo".equals(operacion)) {
      Usuario usuarioNuevo = new Usuario(nombre, apellido, telefono, contrasenia, rol);
      usuarioImpl.insert(usuarioNuevo);
    }

    if ("editar".equals(operacion)) {
      Usuario usuarioEditar = usuarioImpl.getById(id);
      usuarioEditar.setNombre(nombre);
      usuarioEditar.setApellido(apellido);
      usuarioEditar.setTelefono(telefono);
      usuarioEditar.setContrasenia(contrasenia);
      usuarioEditar.setRol(rol);
      usuarioImpl.update(usuarioEditar);
    }

    if ("eliminar".equals(operacion)) {
      usuarioImpl.delete(id);
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
