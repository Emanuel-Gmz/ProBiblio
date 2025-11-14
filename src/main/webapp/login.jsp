<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-5">

            <div class="card shadow-lg border-0">

                <div class="card-header bg-dark text-white text-center">
                    <h4 class="mb-0">INICIAR SESIÓN</h4>
                </div>

                <div class="card-body p-4">


                    <c:if test="${not empty mensajeExito}">
                        <div class="alert alert-success" role="alert">
                            ${mensajeExito}
                        </div>
                    </c:if>


                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger" role="alert">
                            ${mensajeError}
                        </div>
                    </c:if>


                    <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">


                        <div class="mb-3">
                            <label for="txtIdentificador" class="form-label">Usuario</label>
                            <input type="text"
                                   name="txtIdentificador"
                                   id="txtIdentificador"
                                   class="form-control"
                                   placeholder="Ingrese su nombre de usuario"
                                   required />
                        </div>


                        <div class="mb-4">
                            <label for="txtContrasenia" class="form-label">Contraseña</label>
                            <input type="password"
                                   name="txtContrasenia"
                                   id="txtContrasenia"
                                   class="form-control"
                                   placeholder="Ingrese su contraseña"
                                   required />
                        </div>


                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-lg">Entrar</button>
                        </div>

                    </form>
                </div>

                <div class="card-footer text-center">
                    <p class="mb-0">¿Aún no tienes cuenta?
                        <a href="${pageContext.request.contextPath}/formUsuarios.jsp" class="text-primary fw-bold">Regístrate aquí</a>
                    </p>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file ="footer.jsp" %>