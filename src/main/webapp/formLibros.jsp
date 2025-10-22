<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>


<jsp:useBean id="libroImpl" class="org.proBiblio.dao.LibroImpl" scope="page" />

<c:if test = "${param.operacion == 'editar'}">
    <c:set var = "idLibro" value = "${Integer.parseInt(param.id)}" />
    <c:set var = "libroEditar" value = "${libroImpl.getById(idLibro)}" />
</c:if>

<c:set var="modoOperacion" value="${param.operacion == 'editar' ? 'editar' : 'nuevo'}" />

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-lg">

                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">
                        <c:choose>
                            <c:when test = "${modoOperacion == 'editar'}"> EDITAR LIBRO </c:when>
                            <c:otherwise> REGISTRAR NUEVO LIBRO </c:otherwise>
                        </c:choose>
                    </h4>
                </div>

                <div class="card-body">
                    <form action = "LibroServlet" method = "POST">


                        <input type = "hidden" name = "txtId"
                            value = "${not empty libroEditar.idLibro ? libroEditar.idLibro : -1}"
                        />
                        <input type = "hidden" name = "operacion" id = "operacion"
                            value = "${modoOperacion}"
                        />


                        <div class="mb-3">
                            <label for ="txtISBN" class="form-label">ISBN</label>
                            <input type = "text" name = "txtISBN" id = "txtISBN"
                                class="form-control" placeholder = "ISBN"
                                value = "${not empty libroEditar.ISBN ? libroEditar.ISBN : ''}"
                            required />
                        </div>


                        <div class="mb-3">
                            <label for = "txtNombre" class="form-label">Nombre</label>
                            <input type = "text" name = "txtNombre" id = "txtNombre"
                                class="form-control" placeholder = "Nombre del Libro"
                                value = "${not empty libroEditar.nombre ? libroEditar.nombre : ''}"
                            required />
                        </div>


                        <div class="mb-3">
                            <label for ="txtAutor" class="form-label">Autor</label>
                            <input type = "text" name = "txtAutor" id = "txtAutor"
                                class="form-control" placeholder = "Autor del Libro"
                                value = "${not empty libroEditar.autor ? libroEditar.autor : ''}"
                            required />
                        </div>


                        <div class="mb-3">
                            <label for ="txtDescripcion" class="form-label">Descripción</label>
                            <textarea name = "txtDescripcion" id = "txtDescripcion"
                                class="form-control" rows="3" placeholder = "Ingrese la Descripción"
                            required>${not empty libroEditar.descripcion ? libroEditar.descripcion : ''}</textarea>
                        </div>


                        <div class="mb-3">
                            <label for ="txtCategoria" class="form-label">Categoría</label>
                            <select name="txtCategoria" id="txtCategoria" class="form-select" required>
                                <option value="" disabled <c:if test="${empty libroEditar.categoria}">selected</c:if>>Seleccione una Categoría</option>
                                <option value="EDUCATIVO" <c:if test="${libroEditar.categoria == 'EDUCATIVO'}">selected</c:if>>EDUCATIVO</option>
                                <option value="NOVELA" <c:if test="${libroEditar.categoria == 'NOVELA'}">selected</c:if>>NOVELA</option>
                                <option value="CIENCIA_FICCION" <c:if test="${libroEditar.categoria == 'CIENCIA_FICCION'}">selected</c:if>>CIENCIA FICCIÓN</option>
                                <option value="MATEMATICA" <c:if test="${libroEditar.categoria == 'MATEMATICA'}">selected</c:if>>MATEMÁTICA</option>
                                <option value="FANTASIA" <c:if test="${libroEditar.categoria == 'FANTASIA'}">selected</c:if>>FANTASÍA</option>
                            </select>
                        </div>


                        <div class="d-grid gap-2 mt-4">
                            <input type = "submit" class="btn btn-primary"
                                value = "${modoOperacion == 'editar' ? 'Actualizar Libro' : 'Guardar Libro'}"
                            />
                        </div>
                    </form>
                </div>

                <div class="card-footer text-center">
                    <a href="listaLibros.jsp" class="btn btn-secondary btn-sm">
                        Volver al Catálogo
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>