package org.proBiblio.dao;

import org.proBiblio.entities.Categoria;
import org.proBiblio.entities.Libro;
import org.proBiblio.interfaces.AdmConexion;
import org.proBiblio.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroImpl implements AdmConexion, DAO <Libro, Integer>{
    private Connection conn = null;

    private static String SQL_INSERT =
            "INSERT INTO libros (ISBN, nombre, autor, descripcion, categoria) " +
                    " VALUES (?, ?, ?, ?, ?)";

    private static String SQL_UPDATE =
            "UPDATE libros SET " +
                    " ISBN = ?, " +
                    " nombre = ?, " +
                    " autor = ?, " +
                    " descripcion = ?, " +
                    " categoria = ? " +
                    "WHERE idLibro = ?";

    private static String SQL_DELETE = "DELETE FROM libros WHERE idLibro = ?";
    private static String SQL_GETALL = "SELECT * FROM libros ORDER BY nombre ASC";
    private static String SQL_GETBYID = "SELECT * FROM libros WHERE idLibro = ?";
    private static String SQL_EXISTSBYID = "SELECT * FROM libros WHERE idLibro = ?";


    @Override
    public List getAll() {
      conn = obtenerConexion();

      PreparedStatement pst = null;
      ResultSet rs = null;

      List<Libro> lista = new ArrayList<>();

      try {
        pst = conn.prepareStatement(SQL_GETALL);
        rs = pst.executeQuery();

        while (rs.next()) {
          Libro libro = new Libro();
          libro.setIdLibro(rs.getInt("idLibro"));
          libro.setISBN(rs.getString("ISBN"));
          libro.setNombre(rs.getString("nombre"));
          libro.setAutor(rs.getString("autor"));
          libro.setDescripcion(rs.getString("descripcion"));
          libro.setCategoria(Categoria.valueOf(rs.getString("categoria")));

          lista.add(libro);
        }

        pst.close();
        rs.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("Error al crear el statement.");
        throw new RuntimeException(e);
      }
      return lista;
    }


    @Override
    public void insert(Libro objeto) {
      conn = obtenerConexion();
      PreparedStatement pst = null;
      ResultSet rs = null;

      try {
        pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, objeto.getISBN());
        pst.setString(2, objeto.getNombre());
        pst.setString(3, objeto.getAutor());
        pst.setString(4, objeto.getDescripcion());
        pst.setString(5, objeto.getCategoria().toString());

        int resultado = pst.executeUpdate();

        if (resultado == 1) {
          System.out.println("Libro agregado correctamente.");
        } else {
          System.out.println("No se pudo agregar el libro.");
        }

        rs = pst.getGeneratedKeys();

        if (rs.next()) {
          objeto.setIdLibro(rs.getInt(1));
          System.out.println("El id asignado es: " + objeto.getIdLibro());
        }

        pst.close();
        rs.close();
        conn.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void update(Libro objeto) {
      conn = obtenerConexion();
      if (this.existsById(objeto.getIdLibro())) {
        try {
          PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);

          pst.setString(1, objeto.getISBN());
          pst.setString(2, objeto.getNombre());
          pst.setString(3, objeto.getAutor());
          pst.setString(4, objeto.getDescripcion());
          pst.setString(5, objeto.getCategoria().toString());
          pst.setInt(6, objeto.getIdLibro());

          int resultado = pst.executeUpdate();

          if (resultado == 1) {
            System.out.println("Libro actualizado correctamente");
          } else {
            System.out.println("No se pudo actualizar el usuario");
          }
          pst.close();
          conn.close();
        } catch (SQLException e) {
          System.out.println("Error al crear el statement");
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
          System.out.println("Libro eliminado correctamente");
        } else {
          System.out.println("No se pudo eliminar el usuario");
        }

        pst.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("No se pudo eliminar el libro. Error: " + e.getMessage());
      }

    }

    @Override
    public Libro getById(Integer id) {
      conn = obtenerConexion();
      Libro libro = new Libro();
      PreparedStatement pst = null;
      ResultSet rs = null;

      try {
        pst = conn.prepareStatement(SQL_GETBYID);
        pst.setInt(1, id);
        rs = pst.executeQuery();

        if (rs.next()) {
          libro.setIdLibro(rs.getInt("idLibro"));
          libro.setISBN(rs.getString("ISBN"));
          libro.setNombre(rs.getString("nombre"));
          libro.setAutor(rs.getString("autor"));
          libro.setDescripcion(rs.getString("descripcion"));
          libro.setCategoria(Categoria.valueOf(rs.getString("categoria")));
        }

        pst.close();
        rs.close();

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return libro;
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

        pst.close();
        rs.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return existe;
    }

    @Override
    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }
}
