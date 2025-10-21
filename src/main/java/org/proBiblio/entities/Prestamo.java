package org.proBiblio.entities;

import java.util.Date;
import java.util.Objects;

public class Prestamo {
    private int idPrestamo;
    private Date fechaEntre;
    private Usuario usuario;
    private Libro libro;
    private Date fechaDevo;

    public Prestamo(int idPrestamo) {
        this.idPrestamo = -1;
    }

    public Prestamo(int idPrestamo, Date fechaEntre, Usuario usuario, Libro libro, Date fechaDevo) {
        this.idPrestamo = idPrestamo;
        this.fechaEntre = fechaEntre;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaDevo = fechaDevo;
    }

    public Prestamo() {

    }

    public Prestamo(String fechaEntre, String fechaDevo, String usuario, String libro) {
    }

    public int getIdPrestamo() {return idPrestamo;}

    public void setIdPrestamo(int idPrestamo) {this.idPrestamo = idPrestamo;}

    public Date getFechaEntre() {return fechaEntre;}

    public void setFechaEntre(Date fechaEntre) {this.fechaEntre = fechaEntre;}

    public Usuario getUsuario() {return usuario;}

    public void setUsuario() {this.usuario = usuario;}

    public Libro getLibro() {return libro;}

    public void setLibro(Libro libro) {this.libro = libro;}

    public Date getFechaDevo() {return fechaDevo;}

    public void setFechaDevo(Date fechaDevo) {this.fechaDevo = fechaDevo;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return idPrestamo == prestamo.idPrestamo && Objects.equals(fechaEntre, prestamo.fechaEntre) && Objects.equals(usuario, prestamo.usuario) && Objects.equals(libro, prestamo.libro) && Objects.equals(fechaDevo, prestamo.fechaDevo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, fechaEntre, usuario, libro, fechaDevo);
    }

    @Override
    public String toString() {
        return "Prestamos{" +
                "idPrestamo=" + idPrestamo +
                ", fechaEntre=" + fechaEntre +
                ", usuario=" + usuario +
                ", libro=" + libro +
                ", fechaDevo=" + fechaDevo +
                '}';
    }

    public void setFechaEntre(String fechaEntre) {
    }

    public void setUsuario(int nombre) {

    }

    public void setLibro(int idLibro) {

    }

    public void setFechaDevo(String fechaDevo) {
    }

}
