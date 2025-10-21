<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="prestamo" class="org.proBiblio.entities.Prestamo" scope="request" />
<jsp:useBean id="prestamoImpl" class="org.proBiblio.dao.PrestamoImpl" scope="page" />

<c:if test = "${param.operacion == 'editar'}">
    <c:set var = "idPrestamo" value = "${Integer.parseInt(param.id)}" />
    <c:set var = "prestamoEditar" value = "${libroImpl.getById(idPrestamo)}" />
    <c:set var="listaPrestamos" value="${PrestamoImpl.getAll()}" />
</c:if>

<h2>
<c:choose>
    <c:when test = "${param.operacion == 'editar'}"> Editar Prestamo </c:when>
    <c:when test = "${param.operacion == 'eliminar'}"> Eliminar Prestamo </c:when>
    <c:otherwise> Nuevo Prestamo </c:otherwise>
</c:choose>
</h2>

<form action = "PrestamoServlet" method = "GET">

    <label for = "selectPrestamo"> Seleccionar Prestamo </label>
    <select name = "listaPrestamos" id = "listaPrestamo" tabindex = "1">
        <c:forEach var = "a" items = "${listaPrestamo}">
            <option value = "${a.idPrestamo}">
                <c:if test = "${prestamoEditar.idPrestamo == a.idPrestamo}">selected</c:if>
                ${a.categoria}
            </option>
        </c:forEach>
    </select>

    <br>

    <input type = "hidden" name = "txtId"
        value = "${not empty libroEditar.idPrestamo ? libroEditar.idPrestamo : -1}"
    />

    <input type = "hidden" name = "operacion" id = "operacion"
        value = "${param.operacion == 'editar' ? 'editar' : 'nuevo'}"
    />

    <label for ="txtFEntre"> Fecha Entrega </label>
    <input type = "text" name = "txtFEntre" id = "txtFEntre" placeholder = "Fecha de Entrega"
        value = "${not empty prestamoEditar.fechaEntre ? prestamoEditar.fechaEntre : ''}"
    required />

    <br>

    <label for = "txtNombre"> Nombre </label>
    <input type = "text" name = "txtNombre" id = "txtNombre" placeholder = "Nombre"
        value = "${not empty libroEditar.nombre ? libroEditar.nombre : ''}"
    required />

    <br>

    <label for ="txtAutor"> Autor </label>
    <input type = "text" name = "txtAutor" id = "txtAutor" placeholder = "Autor"
        value = "${not empty libroEditar.autor ? libroEditar.autor : ''}"
    required />

    <br>

    <label for ="txtDescripcion"> Descripcion </label>
    <input type = "text" name = "txtDescripcion" id = "txtDescripcion" placeholder = "Descripcion"
        value = "${not empty libroEditar.descripcion ? libroEditar.descripcion : ''}"
    required />

    <br>

        <label for ="txtCategoria"> Descripcion </label>
        <input type = "text" name = "txtCategoria" id = "txtCategoria" placeholder = "Categoria"
            value = "${not empty libroEditar.categoria ? libroEditar.categoria : ''}"
        required />

    <input type = "submit" value = "Enviar" />
</form>

<a href="index.jsp"> Ir a Inicio </a>

<%@ include file ="footer.jsp" %>
</body>
</html>