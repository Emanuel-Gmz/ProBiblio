package org.proBiblio.dao;

import org.proBiblio.entities.Usuario;
import org.proBiblio.interfaces.AdmConexion;
import org.proBiblio.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioImpl implements AdmConexion, DAO<Usuario, Integer> {
    private Connection conn = null;

    private static String SQL_INSERT =
            "INSERT INTO usuarios (nombre, apellido, telefono) " +
                    "VALUES (?, ?, ?)";

    private static String SQL_UPDATE =
            "UPDATE usuarios SET " + "nombre = ?, " + "apellido = ?, " + "telefono = ? " +
                    "WHERE idCliente = ?";

    private static String SQL_DELETE = "DELETE FROM clientes WHERE idUsuario = ?";
    private static String SQL_GETALL = "SELECT * FROM clientes ORDER BY nombre";
    private static String SQL_GETBYID = "SELECT * FROM clientes WHERE idUsuario = ?";
    private static String SQL_EXISTSBYID = "SELECT * FROM clientes WHERE idUsuario = ?";



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

                lista.add(usuario);
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
    public void insert(Usuario objeto) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario usuario = objeto;

        try {
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getTelefono());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Cliente agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el cliente.");
            }

            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                System.out.println("El id asignado es: " + usuario.getIdUsuario());
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
                pst.setInt(4, objeto.getIdUsuario());

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("Cliente actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el cliente");
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
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el cliente");
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el cliente. Error: " + e.getMessage());
        }

    }

    @Override
    public Usuario getById(Integer id) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(SQL_EXISTSBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre((rs.getString("nombre")));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                conn.commit();
            }

            pst.close();
            rs.close();
            conn.close();

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
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;
            }

            rs.close();
            pst.close();
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
