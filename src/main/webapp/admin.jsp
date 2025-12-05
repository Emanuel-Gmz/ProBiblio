<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test="${empty sessionScope.usuarioLogueado}">
        <c:set var="mensajeError" value="Debe iniciar sesión como Admin para acceder a esta página." scope="request" />
        <jsp:forward page="login.jsp" />
    </c:when>
    <c:when test="${sessionScope.rol != 'ADMIN'}">
        <c:set var="mensajeError" value="No tiene permisos para acceder a esta página." scope="request" />
        <jsp:forward page="index.jsp" />
    </c:when>
</c:choose>

<c:set var="usuario" value="${sessionScope.usuarioLogueado}" />

<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="alert alert-info text-center" role="alert">
        <h4 class="alert-heading">Bienvenido, Administrador ${usuario.nombre} ${usuario.apellido}!</h4>
    </div>

    <div class="row mt-4">

        <div class="col-md-4 mb-4">
            <div class="card shadow h-100">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Usuarios</h5>
                    <p class="card-text">Crea, edita y elimina todas las cuentas de usuario del sistema.</p>
                    <a href="${pageContext.request.contextPath}/listaUsuarios.jsp" class="btn btn-danger">Ir a Usuarios</a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-4">
            <div class="card shadow h-100">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Libros</h5>
                    <p class="card-text">Acceder al catálogo para administrar el inventario de la biblioteca.</p>
                    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-secondary">Ir a Catálogo</a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-4">
            <div class="card shadow h-100">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Préstamos</h5>
                    <p class="card-text">Ver solicitudes pendientes, historial de préstamos y registrar devoluciones.</p>
                    <a href="${pageContext.request.contextPath}/listaPrestamos.jsp" class="btn btn-success">Ir a Préstamos</a>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file ="footer.jsp" %>