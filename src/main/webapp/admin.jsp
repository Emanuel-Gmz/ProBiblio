<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="usuario" value="${sessionScope.usuarioLogueado}" />
<c:set var="rol" value="${sessionScope.rol}" />

<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="alert alert-danger text-center" role="alert">
        <h4 class="alert-heading">Bienvenido, Administrador ${usuario.nombre} ${usuario.apellido}!</h4>
        <p>Este es tu panel de control central. Tu rol es: <strong>${rol}</strong>.</p>
    </div>

    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Usuarios</h5>
                    <p class="card-text">Permite crear, editar y eliminar todas las cuentas de usuario.</p>
                    <a href="${pageContext.request.contextPath}/listaUsuarios.jsp" class="btn btn-danger">Ir a Usuarios</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Gestión de Libros</h5>
                    <p class="card-text">Acceso completo al catálogo y control total de inventario.</p>
                    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-secondary">Ir a Catálogo</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Configuración del Sistema</h5>
                    <p class="card-text">Acceso a logs y configuraciones avanzadas de la aplicación.</p>
                    <a href="#" class="btn btn-outline-danger disabled">Configuración</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>