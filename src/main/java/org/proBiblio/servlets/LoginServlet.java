package org.proBiblio.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proBiblio.dao.UsuarioImpl;
import org.proBiblio.entities.Usuario;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String nombre = req.getParameter("txtIdentificador");
    String contrasenia = req.getParameter("txtContrasenia");

    UsuarioImpl usuarioImpl = new UsuarioImpl();
    Usuario usuario = usuarioImpl.login(nombre, contrasenia);

    if (usuario != null) {
      HttpSession session = req.getSession();
      session.setAttribute("usuarioLogueado", usuario);
      session.setAttribute("rol", usuario.getRol().toString());

      String rol = usuario.getRol().toString();
      String urlDestino ="index.jsp";

      if (rol.equals("ADMIN")) {
        urlDestino = "/admin.jsp";
      } else if (rol.equals("BIBLIOTECARIO")) {
        urlDestino = "/bibliotecario.jsp";
      } else {
        urlDestino = "/listaLibros.jsp";
      }

      res.sendRedirect(req.getContextPath() + urlDestino);

    } else {
      req.setAttribute("mensajeError", "Credenciales incorrectas.");
      req.getRequestDispatcher("login.jsp").forward(req, res);
    }
  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }
}