package org.proBiblio.entities;

import java.util.Objects;

public class Libro{
    private int idLibro;
    private String ISBN;
    private String nombre;
    private String autor;
    private String descrip;


    public Libro(){idLibro = -1;}

    public Libro(String ISBN, String autor, String nombre,String descrip) {
        super();
        this.ISBN = ISBN;
        this.autor = autor;
        this.nombre = nombre;
        this.descrip = descrip;
    }

    public String getDescrip() {return descrip;}

    public void setDescrip(String descrip) {this.descrip = descrip;}

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
                ", descrip='" + descrip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro && Objects.equals(ISBN, libro.ISBN) && Objects.equals(nombre, libro.nombre) && Objects.equals(autor, libro.autor) && Objects.equals(descrip, libro.descrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, ISBN, nombre, autor, descrip);
    }
}
