<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>
<%@ page import = "org.proBiblio.dao.UsuarioImpl" %>
<%@ page import = "org.proBiblio.entities.Usuario" %>

<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>

<!-- abro bloque de declaracion java -->
<%!
    UsuarioImpl usuarioImpl = new UsuarioImpl();
    Usuario usuario = new Usuario();
    List<Usuario> listaUsuarios = usuarioImpl.getAll();
%>



<h2> LISTADO DE USUARIOS </h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>TELEFONO</th>
        <th>CONTRASEÑA</th>
        <th>ROL</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    </thead>

    <tbody>
    <% for(Usuario a : listaUsuarios) { %>
        <tr>
           <td> <%=a.getIdUsuario() %> </td>
           <td> <%=a.getNombre() %> </td>
           <td> <%=a.getApellido() %> </td>
           <td> <%=a.getTelefono() %> </td>
           <td> <%=a.getContrasenia() %> </td>
           <td> <%=a.getRol() %> </td>
           <td> <a href="formUsuarios.jsp?operacion=editar&id=<%=a.getIdUsuario()%>"> Editar </a></td>
           <td> <a href="UsuarioServlet?operacion=eliminar&id=<%=a.getIdUsuario()%>"> Eliminar </a></td>
        </tr>
    <% } %>
    </tbody>
</table>
<%@ include file ="footer.jsp" %>