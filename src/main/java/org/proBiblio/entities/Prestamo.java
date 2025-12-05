package org.proBiblio.entities;

import java.sql.Date;
import java.util.Objects;

public class Prestamo {
  private int idPrestamo;
  private Date fechaPrestamo;
  private Date fechaDevolucion;
  private String estado;


  private Usuario usuario;
  private Libro libro;


  public Prestamo() {
    this.idPrestamo = -1;
  }


  public Prestamo(int idPrestamo, Date fechaPrestamo, Date fechaDevolucion, String estado, Usuario usuario, Libro libro) {
    this.idPrestamo = idPrestamo;
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucion = fechaDevolucion;
    this.estado = estado;
    this.usuario = usuario;
    this.libro = libro;
  }



  public int getIdPrestamo() {
    return idPrestamo;
  }

  public void setIdPrestamo(int idPrestamo) {
    this.idPrestamo = idPrestamo;
  }

  public Date getFechaPrestamo() {
    return fechaPrestamo;
  }

  public void setFechaPrestamo(Date fechaPrestamo) {
    this.fechaPrestamo = fechaPrestamo;
  }

  public Date getFechaDevolucion() {
    return fechaDevolucion;
  }

  public void setFechaDevolucion(Date fechaDevolucion) {
    this.fechaDevolucion = fechaDevolucion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }



  @Override
  public String toString() {
    return "Prestamo{" +
        "idPrestamo=" + idPrestamo +
        ", fechaPrestamo=" + fechaPrestamo +
        ", fechaDevolucion=" + fechaDevolucion +
        ", estado='" + estado + '\'' +
        ", usuario=" + (usuario != null ? usuario.getNombre() : "null") +
        ", libro=" + (libro != null ? libro.getNombre() : "null") +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Prestamo prestamo = (Prestamo) o;
    return idPrestamo == prestamo.idPrestamo &&
        Objects.equals(fechaPrestamo, prestamo.fechaPrestamo) &&
        Objects.equals(fechaDevolucion, prestamo.fechaDevolucion) &&
        Objects.equals(estado, prestamo.estado) &&
        Objects.equals(usuario, prestamo.usuario) &&
        Objects.equals(libro, prestamo.libro);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPrestamo, fechaPrestamo, fechaDevolucion, estado, usuario, libro);
  }
}