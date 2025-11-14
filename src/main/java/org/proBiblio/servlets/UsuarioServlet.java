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
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    procesarSolicitud(req, res);
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String operacion = req.getParameter("operacion");

    if (operacion.equals("eliminar")) {
      procesarSolicitud(req, res);
    } else {
      res.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }


  private void procesarSolicitud(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String nombre = "";
    String apellido = "";
    String telefono = "";
    String contrasenia = "";
    Rol rol = null;
    String operacion = "nuevo";
    int id = -1;

    UsuarioImpl usuarioImpl = new UsuarioImpl();


    operacion = req.getParameter("operacion");

    if ("editar".equals(operacion) || "nuevo".equals(operacion)) {
      nombre = req.getParameter("txtNombre");
      apellido = req.getParameter("txtApellido");
      telefono = req.getParameter("txtTelefono");
      contrasenia = req.getParameter("txtContrasenia");

      String rolStr = req.getParameter("txtRol");
      if (rolStr != null && !rolStr.isEmpty()) {
        try {
          rol = Rol.valueOf(rolStr.toUpperCase());
        } catch (IllegalArgumentException e) {
          System.err.println("Advertencia: El valor de Rol '" + rolStr + "' no es válido.");
        }
      }
    }


    if (operacion.equals("nuevo")) {

      Usuario usuario = new Usuario();
      usuario.setNombre(req.getParameter("txtNombre"));
      usuario.setApellido(req.getParameter("txtApellido"));
      usuario.setTelefono(req.getParameter("txtTelefono"));

      String rolParam = req.getParameter("txtRol");


      String rolAsignado = (rolParam == null) ? "USUARIO" : rolParam;

      usuario.setRol(Rol.valueOf(rolAsignado));



      usuario.setContrasenia(contrasenia);

      usuarioImpl.insert(usuario);


    }


    if ("editar".equals(operacion)) {
      String idStr = req.getParameter("txtId");
      if (idStr != null) {
        try {
          id = Integer.parseInt(idStr);
          Usuario usuarioEditar = usuarioImpl.getById(id);
          if (usuarioEditar != null) {
            usuarioEditar.setNombre(nombre);
            usuarioEditar.setApellido(apellido);
            usuarioEditar.setTelefono(telefono);
            usuarioEditar.setContrasenia(contrasenia);
            usuarioEditar.setRol(rol);
            usuarioImpl.update(usuarioEditar);
          }
        } catch (NumberFormatException e) {
          System.err.println("Error de formato en ID al editar (Usuario): " + idStr);
        }
      }
    }


    if ("eliminar".equals(operacion)) {
      String idStr = req.getParameter("id");
      if (idStr == null) {
        idStr = req.getParameter("txtId");
      }

      if (idStr != null) {
        try {
          id = Integer.parseInt(idStr);
          usuarioImpl.delete(id);
        } catch (NumberFormatException e) {
          System.err.println("Error de formato en ID al eliminar (Usuario): " + idStr);
        }
      }
    }

    res.sendRedirect(req.getContextPath() + "/index.jsp");
  }
}