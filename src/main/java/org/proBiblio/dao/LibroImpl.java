package org.proBiblio.dao;

import org.proBiblio.interfaces.AdmConexion;
import org.proBiblio.interfaces.DAO;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class LibroImpl implements AdmConexion, DAO {
    private Connection conn = null;

    private static String SQL_INSERT =
            "INSERT INTO libros (ISBN, nombre, autor, descrip)" +
                    "VALUES (?, ?, ?, ?)";

    private static String SQL_UPDATE =
            "UPDATE autos SET " +
                    "ISBN = ?, " +
                    "nombre = ?, " +
                    "autor = ?, " +
                    "descrip = ?, ";

    private static String SQL_DELETE = "DELETE FROM libros WHERE idLibro = ?";
    private static String SQL_GETALL = "SELECT * FROM libros ORDER BY nombre = ?";
    private static String SQL_GETBYID = "SELECT * FROM libros WHERE idLibro = ?";
    private static String SQL_EXISTSBYID = "SELECT * FROM libros WHERE idLibro = ?";


    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public void insert(Object objeto) {

    }

    @Override
    public void update(Object objeto) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public Object getById(Object id) {
        return null;
    }

    @Override
    public boolean existsById(Object id) {
        return false;
    }

    @Override
    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }
}
