<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProBiblio - Biblioteca</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">

    <style>
        .navbar-logo {
            height: 40px; /
            width: auto;
        }
    </style>
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom shadow-sm">
            <div class="container-fluid">


                <a class="navbar-brand" href="index.jsp">

                    <img src="imagenes/LogoBiblioteca.png"
                         alt="Logo ProBiblio"
                         class="navbar-logo me-2">
                    <span class="fs-4 fw-bold">ProBiblio</span>
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNav" aria-controls="navbarNav"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">


                    <ul class="navbar-nav">

                        <li class="nav-item me-2">
                            <a class="btn btn-outline-primary" href="index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item me-2">
                            <a class="btn btn-outline-secondary" href="listaLibros.jsp">Catalogo</a>
                        </li>
                        <li class="nav-item me-2">
                            <a class="btn btn-success text-dark" href="login.jsp">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-info text-dark" href="formUsuarios.jsp">Register</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    </header>