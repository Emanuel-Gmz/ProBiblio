package org.proBiblio.servlets;

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

    if ("eliminar".equals(operacion)) {
      procesarSolicitud(req, res);
    } else {
      res.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }

  private void procesarSolicitud(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String nombre = "";
    String apellido = "";
    String email = "";
    String telefono = "";
    String contrasenia = "";
    Rol rol = null;
    String operacion = req.getParameter("operacion");
    int id = -1;

    UsuarioImpl usuarioImpl = new UsuarioImpl();

    if ("editar".equals(operacion) || "nuevo".equals(operacion)) {
      nombre = req.getParameter("txtNombre");
      apellido = req.getParameter("txtApellido");
      email = req.getParameter("txtEmail");
      telefono = req.getParameter("txtTelefono");
      contrasenia = req.getParameter("txtContrasenia");

      String rolStr = req.getParameter("txtRol");
      if (rolStr != null && !rolStr.isEmpty()) {
        try {
          rol = Rol.valueOf(rolStr.toUpperCase());
        } catch (IllegalArgumentException e) {
          System.out.println("El valor de Rol '" + rolStr + "' no es válido.");
        }
      }
    }

    if ("nuevo".equals(operacion)) {
      Usuario usuario = new Usuario();
      usuario.setNombre(nombre);
      usuario.setApellido(apellido);
      usuario.setEmail(email);
      usuario.setTelefono(telefono);
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
            usuarioEditar.setEmail(email);
            usuarioEditar.setTelefono(telefono);
            usuarioEditar.setRol(rol);

            usuarioImpl.update(usuarioEditar);

            if (contrasenia != null && !contrasenia.isEmpty()) {
              usuarioImpl.updatePassword(id, contrasenia);
            }
          }
        } catch (NumberFormatException e) {
          System.out.println("Error de formato en ID al editar: " + idStr);
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
          System.out.println("Error de formato en ID al eliminar: " + idStr);
        }
      }
    }

    String rolParam = req.getParameter("txtRol");
    if (rolParam == null && "nuevo".equals(operacion)) {
      res.sendRedirect(req.getContextPath() + "/login.jsp?mensajeExito=Registro exitoso. Inicie sesión.");
    } else {
      res.sendRedirect(req.getContextPath() + "/listaUsuarios.jsp");
    }
  }
}