<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="usuarioImpl" class="org.proBiblio.dao.UsuarioImpl" scope="page" />
<c:set var="listaUsuarios" value="${usuarioImpl.getAll()}" />

<div class="container mt-5">

    <h2 class="mb-4">LISTADO DE USUARIOS</h2>


    <a href="formUsuarios.jsp?operacion=nuevo" class="btn btn-primary mb-4">
        <i class="fas fa-user-plus"></i> Nuevo Usuario
    </a>


    <div class="table-responsive">


        <table class="table table-striped table-hover align-middle">
            <thead>
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Contraseña</th>
                    <th>Rol</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${listaUsuarios}">
                    <tr>
                        <td>${usuario.idUsuario}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.apellido}</td>
                        <td>${usuario.telefono}</td>
                        <td>${usuario.contrasenia}</td>
                        <td>${usuario.rol}</td>
                        <td class="text-center">

                            <a href="formUsuarios.jsp?operacion=editar&id=${usuario.idUsuario}"
                               class="btn btn-sm btn-warning me-2">Editar</a>


                            <a href="UsuarioServlet?operacion=eliminar&id=${usuario.idUsuario}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Está seguro de eliminar al usuario ${usuario.nombre}?');">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listaUsuarios}">
                    <tr>
                        <td colspan="7" class="text-center">No hay usuarios registrados.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<%@ include file ="footer.jsp" %>