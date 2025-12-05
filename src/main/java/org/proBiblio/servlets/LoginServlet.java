package org.proBiblio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proBiblio.dao.UsuarioImpl;
import org.proBiblio.entities.Usuario;
import org.proBiblio.util.PasswordUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginServlet extends HttpServlet {
  private UsuarioImpl usuarioImpl = new UsuarioImpl();

  private static Map<String, Usuario> usuariosActivos = new ConcurrentHashMap<>();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    String email = req.getParameter("txtEmail");
    String contrasenia = req.getParameter("txtContrasenia");

    if (email == null || contrasenia == null || email.trim().isEmpty() || contrasenia.trim().isEmpty()) {
      req.setAttribute("mensajeError", "Email y contraseña son obligatorios.");
      req.getRequestDispatcher("login.jsp").forward(req, res);
      return;
    }

    email = email.trim();

    if (usuariosActivos.containsKey(email)) {
      req.setAttribute("mensajeError", "Ya hay una sesión activa con este email. Cierre la otra sesión primero.");
      req.getRequestDispatcher("login.jsp").forward(req, res);
      return;
    }

    Usuario usuario = usuarioImpl.getByEmail(email);

    boolean loginValido = false;

    if (usuario != null) {
      try {
        if (PasswordUtil.verifyPassword(contrasenia, usuario.getContrasenia())) {
          loginValido = true;
        }
      } catch (Exception e) {
        System.out.println("Error al verificar la contraseña: " + e.getMessage());
        req.setAttribute("mensajeError", "Error en el sistema de autenticación.");
        req.getRequestDispatcher("login.jsp").forward(req, res);
        return;
      }
    }

    if (loginValido) {
      usuariosActivos.put(email, usuario);

      HttpSession session = req.getSession();
      session.setAttribute("usuarioLogueado", usuario);
      session.setAttribute("rol", usuario.getRol().toString());

      String rol = usuario.getRol().toString();
      String urlDestino = "/index.jsp";

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
    String cerrarSesionParam = req.getParameter("cerrarSesion");

    if ("true".equals(cerrarSesionParam)) {
      cerrarSesion(req, res);
    } else {
      res.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }

  private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session != null) {
      Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
      if (usuario != null) {
        usuariosActivos.remove(usuario.getEmail());
      }
      session.invalidate();
    }

    request.setAttribute("mensajeExito", "Se ha cerrado la sesión correctamente");
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request, response);
  }
}