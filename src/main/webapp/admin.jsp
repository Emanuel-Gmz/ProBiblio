<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="usuario" value="${sessionScope.usuarioLogueado}" />
<c:set var="rol" value="${sessionScope.rol}" />

<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="alert alert-danger text-center" role="alert">
        <h4 class="alert-heading">Bienvenido, Administrador ${usuario.nombre} ${usuario.apellido}!</h4>
    </div>

    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Usuarios</h5>
                    <p class="card-text">Crea, edita y elimina todas las cuentas de usuario.</p>
                    <a href="${pageContext.request.contextPath}/listaUsuarios.jsp" class="btn btn-danger">Ir a Usuarios</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Libros</h5>
                    <p class="card-text">Acceder al catálogo.</p>
                    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-secondary">Ir a Catálogo</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>