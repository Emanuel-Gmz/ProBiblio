<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:choose>
    <c:when test="${empty sessionScope.usuarioLogueado}">
        <c:set var="mensajeError" value="Debe iniciar sesión para acceder a esta página." scope="request" />
        <jsp:forward page="login.jsp" />
    </c:when>

    <c:when test="${sessionScope.rol != 'ADMIN' && sessionScope.rol != 'BIBLIOTECARIO'}">
        <c:set var="mensajeError" value="No tiene permisos para acceder a esta página." scope="request" />
        <jsp:forward page="index.jsp" />
    </c:when>
</c:choose>



<c:set var="usuario" value="${sessionScope.usuarioLogueado}" />


<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="alert alert-info text-center" role="alert">
        <h4 class="alert-heading">Panel de Bibliotecario: ${usuario.nombre}</h4>
        <p>Tu rol es: <strong>${sessionScope.rol}</strong>. Concéntrate en el inventario y préstamos.</p>
    </div>

    <div class="row mt-4">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Préstamos</h5>
                    <p class="card-text">Ver y gestionar el estado de todos los préstamos activos y devoluciones.</p>
                    <a href="${pageContext.request.contextPath}/listaPrestamos.jsp" class="btn btn-info">Ver Préstamos</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Mantenimiento del Catálogo</h5>
                    <p class="card-text">Añadir nuevos libros, editar información y actualizar el inventario.</p>
                    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-secondary">Ver Catálogo</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>