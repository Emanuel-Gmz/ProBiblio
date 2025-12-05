<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="prestamoDAO" class="org.proBiblio.dao.PrestamoImpl" scope="page" />


<c:choose>
    <c:when test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
        <c:set var="listaPrestamos" value="${prestamoDAO.getAll()}" />
        <c:set var="tituloPagina" value="Gestión de Préstamos" />
    </c:when>
    <c:otherwise>
        <c:if test="${not empty sessionScope.usuarioLogueado}">
            <c:set var="listaPrestamos" value="${prestamoDAO.getByUsuario(sessionScope.usuarioLogueado.idUsuario)}" />
            <c:set var="tituloPagina" value="Mis Préstamos" />
        </c:if>
        <c:if test="${empty sessionScope.usuarioLogueado}">
             <c:redirect url="login.jsp" />
        </c:if>
    </c:otherwise>
</c:choose>

<div class="container mt-5">

    <c:if test="${not empty param.mensajeExito}">
        <div class="alert alert-success">${param.mensajeExito}</div>
    </c:if>
     <c:if test="${not empty param.mensajeError}">
        <div class="alert alert-danger">${param.mensajeError}</div>
    </c:if>

    <h2 class="mb-4">${tituloPagina}</h2>

    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Libro</th>
                    <th>Usuario</th>
                    <th>Fecha Solicitud</th>
                    <th>Fecha Devolución</th>
                    <th>Estado</th>
                    <c:if test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
                        <th>Acción</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${listaPrestamos}">
                    <tr>
                        <td>
                            <strong>${p.libro.nombre}</strong><br>
                            <small class="text-muted">ISBN: ${p.libro.ISBN}</small>
                        </td>
                        <td>
                            ${p.usuario.nombre} ${p.usuario.apellido}
                        </td>
                        <td>${p.fechaPrestamo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty p.fechaDevolucion}">${p.fechaDevolucion}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.estado == 'ACTIVO'}">
                                    <span class="badge bg-warning text-dark">En Curso</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-success">Devuelto</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <c:if test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
                            <td>
                                <c:if test="${p.estado == 'ACTIVO'}">
                                    <a href="${pageContext.request.contextPath}/PrestamoServlet?operacion=devolver&idPrestamo=${p.idPrestamo}"
                                       class="btn btn-sm btn-outline-success"
                                       onclick="return confirm('¿Confirmar que el libro ha sido devuelto?');">
                                        Registrar Devolución
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>

                <c:if test="${empty listaPrestamos}">
                    <tr>
                         <c:set var="colSpanVal" value="5" />
                         <c:if test="${sessionScope.rol == 'ADMIN' || sessionScope.rol == 'BIBLIOTECARIO'}">
                            <c:set var="colSpanVal" value="6" />
                         </c:if>
                        <td colspan="${colSpanVal}" class="text-center">No hay préstamos registrados.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <div class="mt-3">
        <a href="listaLibros.jsp" class="btn btn-primary">Ir al Catálogo para Solicitar</a>
    </div>
</div>

<%@ include file ="footer.jsp" %>