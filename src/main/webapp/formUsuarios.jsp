<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test = "${param.operacion == 'editar'}">
        <c:if test="${empty sessionScope.usuarioLogueado}">
            <c:set var="mensajeError" value="Debe iniciar sesión como Admin para editar usuarios." scope="request" />
            <jsp:forward page="login.jsp" />
        </c:if>
        <c:if test="${sessionScope.rol != 'ADMIN'}">
            <c:set var="mensajeError" value="No tiene permisos para administrar usuarios." scope="request" />
            <jsp:forward page="index.jsp" />
        </c:if>
    </c:when>

    <c:otherwise>
        <c:if test="${sessionScope.rol == 'USUARIO' || sessionScope.rol == 'BIBLIOTECARIO'}">
            <c:redirect url="index.jsp" />
        </c:if>
    </c:otherwise>
</c:choose>

<%@ include file ="header.jsp" %>

<jsp:useBean id="usuarioImpl" class="org.proBiblio.dao.UsuarioImpl" scope="page" />

<c:if test = "${param.operacion == 'editar'}">
    <c:set var = "idUsuario" value = "${Integer.parseInt(param.id)}" />
    <c:set var = "usuarioEditar" value = "${usuarioImpl.getById(idUsuario)}" />
</c:if>

<c:set var="modoOperacion" value="${param.operacion == 'editar' ? 'editar' : 'nuevo'}" />

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-lg">

                <div class="card-header bg-success text-white">
                    <h4 class="mb-0">
                        <c:choose>
                            <c:when test = "${modoOperacion == 'editar'}"> EDITAR USUARIO </c:when>
                            <c:otherwise> REGISTRAR NUEVO USUARIO </c:otherwise>
                        </c:choose>
                    </h4>
                </div>

                <div class="card-body">
                    <form action = "${pageContext.request.contextPath}/UsuarioServlet" method = "POST">

                        <input type = "hidden" name = "txtId"
                            value = "${not empty usuarioEditar.idUsuario ? usuarioEditar.idUsuario : -1}"
                        />
                        <input type = "hidden" name = "operacion" id = "operacion"
                            value = "${modoOperacion}"
                        />

                        <div class="mb-3">
                            <label for ="txtNombre" class="form-label">Nombre de Usuario</label>
                            <input type = "text" name = "txtNombre" id = "txtNombre"
                                class="form-control" placeholder = "Nombre de usuario (para login)"
                                value = "${not empty usuarioEditar.nombre ? usuarioEditar.nombre : ''}"
                            required />
                        </div>

                        <div class="mb-3">
                            <label for = "txtApellido" class="form-label">Apellido</label>
                            <input type = "text" name = "txtApellido" id = "txtApellido"
                                class="form-control" placeholder = "Apellido"
                                value = "${not empty usuarioEditar.apellido ? usuarioEditar.apellido : ''}"
                            required />
                        </div>

                        <div class="mb-3">
                            <label for ="txtEmail" class="form-label">Correo Electrónico</label>
                            <input type = "email" name = "txtEmail" id = "txtEmail"
                                class="form-control" placeholder = "ejemplo@correo.com"
                                value = "${not empty usuarioEditar.email ? usuarioEditar.email : ''}"
                            required />
                        </div>


                        <div class="mb-3">
                            <label for ="txtTelefono" class="form-label">Teléfono</label>
                            <input type = "text" name = "txtTelefono" id = "txtTelefono"
                                class="form-control" placeholder = "Teléfono"
                                value = "${not empty usuarioEditar.telefono ? usuarioEditar.telefono : ''}"
                            required />
                        </div>

                        <div class="mb-3">
                            <label for ="txtContrasenia" class="form-label">Contraseña</label>
                            <input type = "password" name = "txtContrasenia" id = "txtContrasenia"
                                class="form-control" placeholder = "Contraseña"
                                value = ""
                                <c:if test="${modoOperacion == 'nuevo'}">required</c:if>
                            />
                            <c:if test="${modoOperacion == 'editar'}">
                                <small class="form-text text-muted">Deja vacío para mantener la contraseña actual.</small>
                            </c:if>
                        </div>

                        <c:if test="${sessionScope.rol == 'ADMIN'}">
                            <div class="mb-3">
                                <label for ="txtRol" class="form-label">Rol</label>
                                <select name="txtRol" id="txtRol" class="form-select" required>
                                    <option value="" disabled <c:if test="${empty usuarioEditar.rol}">selected</c:if>>Seleccione un Rol</option>
                                    <option value="ADMIN" <c:if test="${usuarioEditar.rol == 'ADMIN'}">selected</c:if>>ADMIN</option>
                                    <option value="USUARIO" <c:if test="${usuarioEditar.rol == 'USUARIO'}">selected</c:if>>USUARIO</option>
                                    <option value="BIBLIOTECARIO" <c:if test="${usuarioEditar.rol == 'BIBLIOTECARIO'}">selected</c:if>>BIBLIOTECARIO</option>
                                </select>
                            </div>
                        </c:if>

                        <div class="d-grid gap-2 mt-4">
                            <input type = "submit" class="btn btn-success"
                                value = "${modoOperacion == 'editar' ? 'Actualizar Usuario' : 'Registrar Usuario'}"
                            />
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>