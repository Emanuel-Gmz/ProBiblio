package org.proBiblio.dao;

import org.proBiblio.entities.Categoria;
import org.proBiblio.entities.Libro;
import org.proBiblio.entities.Prestamo;
import org.proBiblio.entities.Usuario;
import org.proBiblio.interfaces.AdmConexion;
import org.proBiblio.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamoImpl implements AdmConexion, DAO<Prestamo, Integer> {

  //Todavia no funciona esto
    private Connection conn = null;

    private static String SQL_INSERT =
            "INSERT INTO prestamos (fechaEntre, idUsuario, idLibro, fechaDevo)" +
                    "VALUES (?, ?, ?, ?)";

    private static String SQL_UPDATE =
            "UPDATE prestamos SET " +
                    "fechaEntre = ?, " +
                    "idUsuario = ?, " +
                    "idLibro = ?, " +
                    "fechaDevo = ?, " +
                    "WHERE idPrestamo = ?";

    private static String SQL_DELETE = "DELETE FROM prestamos WHERE idPrestamo = ?";
    private static String SQL_GETALL = "SELECT * FROM prestamos ORDER BY fechaEntre";
    private static String SQL_GETBYID = "SELECT * FROM prestamos WHERE idPrestamo = ?";
    private static String SQL_EXISTSBYID = "SELECT * FROM prestamos WHERE idPrestamo = ?";

    @Override
    public List<Prestamo> getAll() {
        conn = obtenerConexion();

        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Prestamo> lista = new ArrayList<>();

        try {
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idLibro"));
                prestamo.setFechaEntre(rs.getString("fechaEntre"));
                prestamo.setUsuario(rs.getInt("idUsuario"));
                prestamo.setLibro(rs.getInt("idLibro"));
                prestamo.setFechaDevo(rs.getString("fechaDevo"));

                lista.add(prestamo);
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
    public void insert(Prestamo objeto) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, String.valueOf(objeto.getFechaEntre()));
            pst.setString(2, String.valueOf(objeto.getUsuario()));
            pst.setString(3, String.valueOf(objeto.getLibro()));
            pst.setString(4, String.valueOf(objeto.getFechaDevo()));

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Prestamo agregado correctamente.");
            } else {
                System.out.println("No se pudo registrar el prestamo.");
            }

            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                objeto.setIdPrestamo(rs.getInt(1));
                System.out.println("El id asignado es: " + objeto.getIdPrestamo());
            }

            pst.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Prestamo objeto) {
        conn = obtenerConexion();
        if (this.existsById(objeto.getIdPrestamo())) {
            try {
                PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);

                pst.setString(1, String.valueOf(objeto.getFechaEntre()));
                pst.setString(2, String.valueOf(objeto.getUsuario()));
                pst.setString(3, String.valueOf(objeto.getLibro()));
                pst.setString(4, String.valueOf(objeto.getFechaDevo()));

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("Prestamo actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el prestamo");
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
                System.out.println("Prestamo eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el prestamo");
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el prestamo. Error: " + e.getMessage());
        }
    }

    @Override
    public Prestamo getById(Integer id) {
        conn = obtenerConexion();
        Prestamo prestamo = new Prestamo();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaEntre(rs.getString("fechaEntre"));
                prestamo.setFechaDevo(rs.getString("fechaDevo"));
                prestamo.setUsuario(Integer.parseInt(rs.getString("idUsuario")));
                prestamo.setLibro(Integer.parseInt(rs.getString("idLibro")));
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prestamo;
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
            conn.close();
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


