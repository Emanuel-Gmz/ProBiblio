<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file ="header.jsp" %>

<div class="container-fluid p-0">

    <div class="p-5 text-center bg-light"
         style="background-image: url('${pageContext.request.contextPath}/imagenes/biblioteca_bg.jpg'); background-size: cover; background-position: center; color: white;">

        <div class="mask p-5" style="background-color: rgba(0, 0, 0, 0.6);">
            <h1 class="display-3 fw-bold">Bienvenido a ProBiblio</h1>
            <p class="col-md-8 mx-auto fs-5">
                Tu portal digital para explorar miles de historias, conocimientos e investigaciones. Encuentra, solicita y gestiona tus libros favoritos desde aquí.
            </p>
            <hr class="my-4 border-light">
            <c:choose>
                <c:when test="${not empty sessionScope.usuarioLogueado}">
                    <a class="btn btn-warning btn-lg me-2" href="${pageContext.request.contextPath}/listaLibros.jsp" role="button">
                        Ir al Catálogo
                    </a>
                    <span class="text-white">¡Bienvenido de nuevo, ${sessionScope.usuarioLogueado.nombre}!</span>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-primary btn-lg me-2" href="${pageContext.request.contextPath}/login.jsp" role="button">
                        Iniciar Sesión
                    </a>
                    <a class="btn btn-outline-light btn-lg" href="${pageContext.request.contextPath}/formUsuarios.jsp?operacion=nuevo" role="button">
                        Registrarse
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<div class="container my-5">

    <h2 class="text-center mb-5">Nuestros Servicios</h2>

    <div class="row text-center">

        <div class="col-lg-4 mb-4">
            <div class="card h-100 shadow">
                <div class="card-body">
                    <h3 class="card-title text-dark"> Visita el Catálogo</h3>
                    <p class="card-text">Descubre nuevos títulos en diferentes categorías.</p>
                    <a href="${pageContext.request.contextPath}/listaLibros.jsp" class="btn btn-sm btn-outline-primary">Ver Libros</a>
                </div>
            </div>
        </div>

        <div class="col-lg-4 mb-4">
            <div class="card h-100 shadow">
                <div class="card-body">
                    <h3 class="card-title text-success"> Préstamos</h3>
                    <p class="card-text">Solicita préstamos Rápido y fácil.</p>
                    <a href="${pageContext.request.contextPath}/listaPrestamos.jsp" class="btn btn-sm btn-outline-success">Ver Préstamos</a>
                </div>
            </div>
        </div>


        <div class="col-lg-4 mb-4">
            <div class="card h-100 shadow">
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty sessionScope.usuarioLogueado}">
                            <h3 class="card-title text-info"> Perfil</h3>
                            <p class="card-text">Crea tu perfil de usuario.</p>
                            <a href="${pageContext.request.contextPath}/formUsuarios.jsp?operacion=nuevo" class="btn btn-sm btn-outline-info">Regístrate</a>
                        </c:when>
                        <c:when test="${sessionScope.rol == 'ADMIN'}">
                            <h3 class="card-title text-danger"> Perfil (Admin)</h3>
                            <p class="card-text">Administrar todos los usuarios del sistema.</p>
                            <a href="${pageContext.request.contextPath}/listaUsuarios.jsp" class="btn btn-sm btn-outline-danger">Ver Usuarios</a>
                        </c:when>
                        <c:otherwise>
                             <h3 class="card-title text-info"> Mi Perfil</h3>
                            <p class="card-text">Revisa el historial y estado de tus préstamos.</p>
                            <a href="${pageContext.request.contextPath}/listaPrestamos.jsp" class="btn btn-sm btn-outline-info">Mis Préstamos</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    </div>

    <h2 class="text-center mt-5 mb-4">Recomendaciones</h2>

    <div id="bookCarousel" class="carousel slide border shadow-lg" data-bs-ride="carousel">
      <div class="carousel-inner">

        <div class="carousel-item active">
          <img src="${pageContext.request.contextPath}/imagenes/cienciaficcion.jpg" class="d-block w-100" alt="Libro de Ciencia Ficcion" style="height: 400px; object-fit: cover;">
          <div class="carousel-caption d-none d-md-block bg-dark opacity-75">
            <h5>Ciencia Ficción </h5>
            <p>Descubre los libros de ciencia ficcion mas locos.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="${pageContext.request.contextPath}/imagenes/Novela.jpg" class="d-block w-100" alt="Libro de Historia" style="height: 400px; object-fit: cover;">
          <div class="carousel-caption d-none d-md-block bg-dark opacity-75">
            <h5>Libros de Historia</h5>
            <p>Enterate de tu historia con estos libros.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="${pageContext.request.contextPath}/imagenes/librojava.jpg" class="d-block w-100" alt="Libro de Java 17" style="height: 400px; object-fit: cover;">
          <div class="carousel-caption d-none d-md-block bg-dark opacity-75">
            <h5>Recién Añadidos</h5>
            <p>Lo mas nuevo en ProBiblio.</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#bookCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Anterior</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#bookCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Siguiente</span>
      </button>
    </div>
</div>

<%@ include file ="footer.jsp" %>