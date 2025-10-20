<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>
<%@ page import = "org.proBiblio.dao.LibroImpl" %>
<%@ page import = "org.proBiblio.entities.Libro" %>

<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>

<!-- abro bloque de declaracion java -->
<%!
    LibroImpl libroImpl = new LibroImpl();
    Libro libro = new Libro();
    List<Libro> listaLibros = libroImpl.getAll();
%>



<h2> Listado de Libros </h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>ISBN</th>
        <th>NOMBRE</th>
        <th>AUTOR</th>
        <th>DESCRIPCION</th>
        <th>CATEGORIA</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    </thead>

    <tbody>
    <% for(Libro a : listaLibros) { %>
        <tr>
           <td> <%=a.getIdLibro() %> </td>
           <td> <%=a.getISBN() %> </td>
           <td> <%=a.getNombre() %> </td>
           <td> <%=a.getAutor() %> </td>
           <td> <%=a.getDescripcion() %> </td>
           <td> <%=a.getCategoria() %> </td>
           <td> <a href="formLibros.jsp?operacion=editar&id=<%=a.getIdLibro()%>"> Editar </a></td>
           <td> <a href="LibroServlet?operacion=eliminar&id=<%=a.getIdLibro()%>"> Eliminar </a></td>
        </tr>
    <% } %>
    </tbody>
</table>
<%@ include file ="footer.jsp" %>