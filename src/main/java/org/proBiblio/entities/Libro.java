package org.proBiblio.entities;

import java.util.Objects;

public class Libro{
    private int idLibro;
    private String ISBN;
    private String nombre;
    private String autor;
    private String descripcion;
    private Categoria categoria;


    public Libro(){idLibro = -1;}

    public Libro(String ISBN, String nombre, String autor, String descripcion, Categoria categoria) {
        this.ISBN = ISBN;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

  public Categoria getCategoria() {return categoria;}

  public void setCategoria(Categoria categoria) {this.categoria = categoria;}

  public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public int getIdLibro() {return idLibro;}

    public void setIdLibro(int idLibro) {this.idLibro = idLibro;}

    public String getISBN() {return ISBN;}

    public void setISBN(String ISBN) {this.ISBN = ISBN;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getAutor() {return autor;}

    public void setAutor(String autor) {this.autor = autor;}

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", ISBN='" + ISBN + '\'' +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro && Objects.equals(ISBN, libro.ISBN) && Objects.equals(nombre, libro.nombre) && Objects.equals(autor, libro.autor) && Objects.equals(descripcion, libro.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, ISBN, nombre, autor, descripcion);
    }
}
