package org.proBiblio.dao;

import org.proBiblio.entities.Rol;
import org.proBiblio.entities.Usuario;
import org.proBiblio.interfaces.AdmConexion;
import org.proBiblio.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioImpl implements AdmConexion, DAO<Usuario, Integer> {
  private Connection conn = null;

  private static String SQL_INSERT =
      "INSERT INTO usuarios (nombre, apellido, telefono, contrasenia, rol) " +
          "VALUES (?, ?, ?, ?, ?)";


  private static String SQL_UPDATE =
      "UPDATE usuarios SET " +
          "nombre = ?, " +
          "apellido = ?, " +
          "telefono = ?, " +
          "contrasenia = ?, " +
          "rol = ? " +
          "WHERE idUsuario = ?";

  private static String SQL_DELETE = "DELETE FROM usuarios WHERE idUsuario = ?";
  private static String SQL_GETALL = "SELECT * FROM usuarios ORDER BY nombre";
  private static String SQL_GETBYID = "SELECT * FROM usuarios WHERE idUsuario = ?";
  private static String SQL_EXISTSBYID = "SELECT 1 FROM usuarios WHERE idUsuario = ?";
  private static String SQL_LOGIN = "SELECT * FROM usuarios WHERE nombre = ? AND contrasenia = ?";


  @Override
  public List<Usuario> getAll() {
    conn = obtenerConexion();
    PreparedStatement pst = null;
    ResultSet rs = null;
    List<Usuario> lista = new ArrayList<>();

    try {
      pst = conn.prepareStatement(SQL_GETALL);
      rs = pst.executeQuery();

      while (rs.next()) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));

        lista.add(usuario);
      }

      pst.close();
      rs.close();
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error al obtener la lista de usuarios. ");
      throw new RuntimeException(e);
    }
    return lista;
  }


  @Override
  public void insert(Usuario objeto) {
    conn = obtenerConexion();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
      pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

      pst.setString(1, objeto.getNombre());
      pst.setString(2, objeto.getApellido());
      pst.setString(3, objeto.getTelefono());
      pst.setString(4, objeto.getContrasenia());
      pst.setString(5, objeto.getRol().toString());

      int resultado = pst.executeUpdate();


      rs = pst.getGeneratedKeys();
      if (rs.next()) {
        objeto.setIdUsuario(rs.getInt(1));
      }

      pst.close();
      rs.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void update(Usuario objeto) {
    conn = obtenerConexion();
    if (this.existsById(objeto.getIdUsuario())) {
      try {
        PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);


        pst.setString(1, objeto.getNombre());
        pst.setString(2, objeto.getApellido());
        pst.setString(3, objeto.getTelefono());
        pst.setString(4, objeto.getContrasenia());
        pst.setString(5, objeto.getRol().toString());
        pst.setInt(6, objeto.getIdUsuario());

        int resultado = pst.executeUpdate();


        pst.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("Error al actualizar el usuario. " + e.getMessage());
      }
    }
  }


  @Override
  public void delete(Integer id) {
    conn = obtenerConexion();
    try {
      conn.setAutoCommit(false);
      PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
      pst.setInt(1, id);
      int resultado = pst.executeUpdate();

      if (resultado == 1) {
        conn.commit();
        System.out.println("Usuario eliminado correctamente");
      } else {
        System.out.println("No se pudo eliminar el usuario");
      }


      conn.commit();
      pst.close();
      conn.close();
    } catch (SQLException e) {
      try { if(conn != null) conn.close(); } catch (SQLException closeEx) { closeEx.printStackTrace(); }
    }
  }


  @Override
  public Usuario getById(Integer id) {
    conn = obtenerConexion();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Usuario usuario = null;

    try {
      pst = conn.prepareStatement(SQL_GETBYID);
      pst.setInt(1, id);
      rs = pst.executeQuery();

      if (rs.next()) {
        usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre((rs.getString("nombre")));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
      }

      pst.close();
      rs.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return usuario;
  }

  @Override
  public boolean existsById(Integer id) {
    conn = obtenerConexion();
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean existe = false;

    try {
      pst = conn.prepareStatement(SQL_EXISTSBYID);
      pst.setInt(1, id);
      rs = pst.executeQuery();

      if (rs.next()) {
        existe = true;
      }

      rs.close();
      pst.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return existe;
  }

  public Usuario login(String nombre, String contrasenia) {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Usuario usuario = null;

    try {
      conn = obtenerConexion();
      pst = conn.prepareStatement(SQL_LOGIN);


      pst.setString(1, nombre);
      pst.setString(2, contrasenia);

      rs = pst.executeQuery();

      if (rs.next()) {
        usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));

      }
    } catch (SQLException e) {
      System.err.println("Error de login en DAO: " + e.getMessage());
      throw new RuntimeException(e);
    }
    return usuario;
  }


  @Override
  public Connection obtenerConexion() {
    return AdmConexion.super.obtenerConexion();
  }
}