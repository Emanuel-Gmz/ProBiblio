<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="usuario" class="org.proBiblio.entities.Usuario" scope="request" />
<jsp:useBean id="usuarioImpl" class="org.proBiblio.dao.UsuarioImpl" scope="page" />

<c:if test = "${param.operacion == 'editar'}">
    <c:set var = "idUsuario" value = "${Integer.parseInt(param.id)}" />
    <c:set var = "usuarioEditar" value = "${usuarioImpl.getById(idUsuario)}" />
    <c:set var="listaUsuarios" value="${UsuarioImpl.getAll()}" />
</c:if>

<h2>
<c:choose>
    <c:when test = "${param.operacion == 'editar'}"> Editar Usuario </c:when>
    <c:when test = "${param.operacion == 'eliminar'}"> Eliminar Usuario </c:when>
    <c:otherwise> Nuevo Usuario </c:otherwise>
</c:choose>
</h2>

<form action = "UsuarioServlet" method = "GET">

    <label for = "selectUsuario"> Seleccionar Usuario </label>
    <select name = "listaUsuario" id = "listaUsuario" tabindex = "1">
        <c:forEach var = "a" items = "${listaUsuario}">
            <option value = "${a.idUsuario}">
                <c:if test = "${usuarioEditar.idUsuario == a.idUsuario}">selected</c:if>
                ${a.categoria}
            </option>
        </c:forEach>
    </select>

    <br>

    <input type = "hidden" name = "txtId"
        value = "${not empty usuarioEditar.idUsuario ? usuarioEditar.idUsuario : -1}"
    />

    <input type = "hidden" name = "operacion" id = "operacion"
        value = "${param.operacion == 'editar' ? 'editar' : 'nuevo'}"
    />

    <label for ="txtNombre"> Nombre </label>
    <input type = "text" name = "txtNombre" id = "txtNombre" placeholder = "Nombre"
        value = "${not empty usuarioEditar.nombre ? usuarioEditar.nombre : ''}"
    required />

    <br>

    <label for = "txtApellido"> Apellido </label>
    <input type = "text" name = "txtApellido" id = "txtApellido" placeholder = "Apellido"
        value = "${not empty usuarioEditar.apellido ? usuarioEditar.apellido : ''}"
    required />

    <br>

    <label for ="txtTelefono"> Telefono </label>
    <input type = "text" name = "txtTelefono" id = "txtTelefono" placeholder = "Telefono"
        value = "${not empty usuarioEditar.telefono ? usuarioEditar.telefono : ''}"
    required />

    <br>

    <label for ="txtContrasenia"> Contrasenia </label>
    <input type = "text" name = "txtContrasenia" id = "txtContrasenia" placeholder = "Contrasenia"
        value = "${not empty usuarioEditar.contrasenia ? usuarioEditar.contrasenia : ''}"
    required />

    <br>

        <label for ="txtRol"> Rol </label>
        <input type = "text" name = "txtRol" id = "txtRol" placeholder = "Rol"
            value = "${not empty usuarioEditar.rol ? usuarioEditar.rol : ''}"
        required />

    <input type = "submit" value = "Enviar" />
</form>

<a href="index.jsp"> Ir a Inicio </a>

<%@ include file ="footer.jsp" %>
</body>
</html>