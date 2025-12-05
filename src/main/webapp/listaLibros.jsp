<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="libroImpl" class="org.proBiblio.dao.LibroImpl" scope="page" />
<c:set var="listaLibros" value="${libroImpl.getAll()}" />

<div class="container mt-5">

    <c:if test="${not empty mensajeError}">
        <div class="alert alert-danger" role="alert">${mensajeError}</div>
    </c:if>
    <c:if test="${not empty mensajeExito}">
        <div class="alert alert-success" role="alert">${mensajeExito}</div>
    </c:if>
    <c:if test="${not empty param.mensajeError}">
        <div class="alert alert-danger" role="alert">${param.mensajeError}</div>
    </c:if>

    <h2 class="mb-4">CATÁLOGO DE LIBROS</h2>

    <c:if test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
        <a href="${pageContext.request.contextPath}/formLibros.jsp?operacion=nuevo" class="btn btn-primary mb-4">
            <i class="fas fa-book-medical"></i> Nuevo Libro
        </a>
    </c:if>

    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead>
                <tr class="table-dark">
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Descripción</th>
                    <th>Categoría</th>

                    <c:if test="${not empty sessionScope.usuarioLogueado}">
                        <th class="text-center" colspan="2">Acciones</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="libro" items="${listaLibros}">
                    <tr>
                        <td><c:out value="${libro.idLibro}" /></td>
                        <td><c:out value="${libro.ISBN}" /></td>
                        <td><c:out value="${libro.nombre}" /></td>
                        <td><c:out value="${libro.autor}" /></td>
                        <td><c:out value="${libro.descripcion}" /></td>
                        <td><c:out value="${libro.categoria}" /></td>

                        <c:if test="${not empty sessionScope.usuarioLogueado}">

                            <c:if test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/formLibros.jsp?operacion=editar&id=${libro.idLibro}"
                                       class="btn btn-sm btn-warning me-2">Editar</a>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/LibroServlet?operacion=eliminar&id=${libro.idLibro}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Está seguro de eliminar el libro ${libro.nombre}?');">
                                        Eliminar
                                    </a>
                                </td>
                            </c:if>

                            <c:if test="${sessionScope.rol == 'USUARIO'}">
                                <td class="text-center" colspan="2">
                                    <a href="${pageContext.request.contextPath}/PrestamoServlet?operacion=solicitar&idLibro=${libro.idLibro}"
                                       class="btn btn-sm btn-success"
                                       onclick="return confirm('¿Desea solicitar el préstamo de: ${libro.nombre}?');">
                                        Solicitar Préstamo
                                    </a>
                                </td>
                            </c:if>

                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file ="footer.jsp" %>