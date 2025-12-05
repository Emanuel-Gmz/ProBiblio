package org.proBiblio.dao;

import org.proBiblio.entities.Libro;
import org.proBiblio.entities.Prestamo;
import org.proBiblio.entities.Usuario;
import org.proBiblio.interfaces.AdmConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrestamoImpl implements AdmConexion {

  private static final String SQL_GET_ALL =
      "SELECT p.*, u.nombre as u_nombre, u.apellido as u_apellido, l.nombre as l_titulo, l.ISBN as l_isbn " +
          "FROM prestamos p " +
          "JOIN usuarios u ON p.idUsuario = u.idUsuario " +
          "JOIN libros l ON p.idLibro = l.idLibro " +
          "ORDER BY p.fechaPrestamo DESC";

  private static final String SQL_GET_BY_USUARIO =
      "SELECT p.*, u.nombre as u_nombre, u.apellido as u_apellido, l.nombre as l_titulo, l.ISBN as l_isbn " +
          "FROM prestamos p " +
          "JOIN usuarios u ON p.idUsuario = u.idUsuario " +
          "JOIN libros l ON p.idLibro = l.idLibro " +
          "WHERE p.idUsuario = ? ORDER BY p.fechaPrestamo DESC";

  private static final String SQL_INSERT =
      "INSERT INTO prestamos (fechaPrestamo, estado, idUsuario, idLibro) VALUES (?, 'ACTIVO', ?, ?)";

  private static final String SQL_DEVOLVER =
      "UPDATE prestamos SET fechaDevolucion = ?, estado = 'FINALIZADO' WHERE idPrestamo = ?";


  public List<Prestamo> getAll() {
    return ejecutarConsulta(SQL_GET_ALL, -1);
  }


  public List<Prestamo> getByUsuario(int idUsuario) {
    return ejecutarConsulta(SQL_GET_BY_USUARIO, idUsuario);
  }

  private List<Prestamo> ejecutarConsulta(String sql, int idFiltro) {
    Connection conn = obtenerConexion();
    List<Prestamo> lista = new ArrayList<>();
    try {
      PreparedStatement pst = conn.prepareStatement(sql);
      if (idFiltro != -1) {
        pst.setInt(1, idFiltro);
      }
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        Prestamo p = new Prestamo();
        p.setIdPrestamo(rs.getInt("idPrestamo"));
        p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
        p.setFechaDevolucion(rs.getDate("fechaDevolucion"));
        p.setEstado(rs.getString("estado"));


        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombre(rs.getString("u_nombre"));
        u.setApellido(rs.getString("u_apellido"));
        p.setUsuario(u);

        Libro l = new Libro();
        l.setIdLibro(rs.getInt("idLibro"));
        l.setNombre(rs.getString("l_titulo"));
        l.setISBN(rs.getString("l_isbn"));
        p.setLibro(l);

        lista.add(p);
      }
      pst.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return lista;
  }

  public void registrarPrestamo(Prestamo p) {
    Connection conn = obtenerConexion();
    try {
      PreparedStatement pst = conn.prepareStatement(SQL_INSERT);
      pst.setDate(1, p.getFechaPrestamo());
      pst.setInt(2, p.getUsuario().getIdUsuario());
      pst.setInt(3, p.getLibro().getIdLibro());
      pst.executeUpdate();
      pst.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void registrarDevolucion(int idPrestamo) {
    Connection conn = obtenerConexion();
    try {
      PreparedStatement pst = conn.prepareStatement(SQL_DEVOLVER);
      pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
      pst.setInt(2, idPrestamo);
      pst.executeUpdate();
      pst.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConexion.super.obtenerConexion();
  }
}