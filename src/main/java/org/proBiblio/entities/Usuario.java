package org.proBiblio.entities;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String telefono;

    public Usuario() {
        idUsuario = -1;
    }

    public Usuario(String nombre, String apellido, String telefono) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }


    public int getIdUsuario() {return idUsuario;}

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}


    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(telefono, usuario.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellido, telefono);
    }
}
