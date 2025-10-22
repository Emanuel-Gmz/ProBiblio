<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="usuario" value="${sessionScope.usuarioLogueado}" />

<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="alert alert-success text-center" role="alert">
        <h4 class="alert-heading">Bienvenido al Catálogo, ${usuario.nombre}!</h4>
        <p>Explora la colección de libros disponibles y solicita un préstamo.</p>
    </div>

    <h3 class="mb-4">Catálogo Completo</h3>
    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-primary">Ver Libros Disponibles</a>
</div>

<%@ include file ="footer.jsp" %>