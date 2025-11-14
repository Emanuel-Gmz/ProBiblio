<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:choose>
    <c:when test="${empty sessionScope.usuarioLogueado}">
        <c:set var="mensajeError" value="Debe iniciar sesión como Admin para acceder a esta página." scope="request" />
        <jsp:forward page="login.jsp" />
    </c:when>


    <c:when test="${sessionScope.rol != 'ADMIN'}">
        <c:set var="mensajeError" value="No tiene permisos suficientes para administrar usuarios." scope="request" />
        <%-- Lo mandamos al index, que es una página segura --%>
        <jsp:forward page="index.jsp" />
    </c:when>
</c:choose>



<%@ include file ="header.jsp" %>

<jsp:useBean id="usuarioImpl" class="org.proBiblio.dao.UsuarioImpl" scope="page" />
<c:set var="listaUsuarios" value="${usuarioImpl.getAll()}" />

<div class="container mt-5">


    <c:if test="${not empty mensajeError}">
        <div class="alert alert-danger" role="alert">
            ${mensajeError}
        </div>
    </c:if>
    <c:if test="${not empty mensajeExito}">
        <div class="alert alert-success" role="alert">
            ${mensajeExito}
        </div>
    </c:if>



    <a href="${pageContext.request.contextPath}/formUsuarios.jsp?operacion=nuevo" class="btn btn-primary mb-4">
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
                    <th>Rol</th>
                    <th class="text-center" colspan="2">Acciones</th> <%-- Colspan es 2 --%>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${listaUsuarios}">
                    <tr>
                        <td>${usuario.idUsuario}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.apellido}</td>
                        <td>${usuario.telefono}</td>
                        <td>${usuario.rol}</td>

                        <%-- CAMBIO 4: Enlace corregido --%>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/formUsuarios.jsp?operacion=editar&id=${usuario.idUsuario}"
                               class="btn btn-sm btn-warning me-2">Editar</a>
                        </td>
                        <td class="text-center">
                            <c:if test="${sessionScope.usuarioLogueado.idUsuario != usuario.idUsuario}">

                                <a href="${pageContext.request.contextPath}/UsuarioServlet?operacion=eliminar&id=${usuario.idUsuario}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('¿Está seguro de eliminar al usuario ${usuario.nombre}?');">
                                    Eliminar
                                </a>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listaUsuarios}">
                    <tr>

                        <td colspan="6" class="text-center">No hay usuarios registrados.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<%@ include file ="footer.jsp" %>