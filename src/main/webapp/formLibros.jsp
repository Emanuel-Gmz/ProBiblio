<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<jsp:useBean id="libro" class="org.proBiblio.entities.Libro" scope="request" />
<jsp:useBean id="libroImpl" class="org.proBiblio.dao.LibroImpl" scope="page" />

<c:if test = "${param.operacion == 'editar'}">
    <c:set var = "idLibro" value = "${Integer.parseInt(param.id)}" />
    <c:set var = "libroEditar" value = "${libroImpl.getById(idLibro)}" />
    <c:set var="listaLibros" value="${LibroImpl.getAll()}" />
</c:if>

<h2>
<c:choose>
    <c:when test = "${param.operacion == 'editar'}"> Editar Libro </c:when>
    <c:when test = "${param.operacion == 'eliminar'}"> Eliminar Libro </c:when>
    <c:otherwise> Nuevo Libro </c:otherwise>
</c:choose>
</h2>

<form action = "LibroServlet" method = "GET">

    <label for = "selectLibro"> Seleccionar Libro </label>
    <select name = "listaLibro" id = "listaLibro" tabindex = "1">
        <c:forEach var = "a" items = "${listaLibro}">
            <option value = "${a.idLibro}">
                <c:if test = "${libroEditar.idLibro == a.idLibro}">selected</c:if>
                ${a.categoria}
            </option>
        </c:forEach>
    </select>

    <br>

    <input type = "hidden" name = "txtId"
        value = "${not empty libroEditar.idLibro ? libroEditar.idLibro : -1}"
    />

    <input type = "hidden" name = "operacion" id = "operacion"
        value = "${param.operacion == 'editar' ? 'editar' : 'nuevo'}"
    />

    <label for ="txtISBN"> ISBN </label>
    <input type = "text" name = "txtISBN" id = "txtISBN" placeholder = "ISBN"
        value = "${not empty libroEditar.ISBN ? libroEditar.ISBN : ''}"
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
    <input type = "text" name = "txtDescripcion" id = "txtDescripcion" placeholder = "Ingrese la Descripcion"
        value = "${not empty libroEditar.descripcion ? libroEditar.descripcion : ''}"
    required />

    <br>

        <label for ="txtCategoria"> Categoria </label>
        <input type = "text" name = "txtCategoria" id = "txtCategoria" placeholder = "Ingrese una Categoria"
            value = "${not empty libroEditar.categoria ? libroEditar.categoria : ''}"
        required />

    <input type = "submit" value = "Enviar" />
</form>

<a href="index.jsp"> Ir a Inicio </a>

<%@ include file ="footer.jsp" %>
</body>
</html>