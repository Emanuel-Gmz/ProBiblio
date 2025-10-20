<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca</title>
    <style>
        /* Estilos básicos para el header */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #f8f9fa; /* Color de fondo del header */
            border-bottom: 1px solid #dee2e6; /* Línea divisoria inferior */
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
        }

        .logo a {
            text-decoration: none;
            color: #333;
        }

        .nav-buttons button {
            margin-left: 15px;
            padding: 8px 12px;
            border: 1px solid #007bff; /* Color del borde del botón */
            background-color: #007bff; /* Color de fondo del botón */
            color: white; /* Color del texto del botón */
            cursor: pointer;
            border-radius: 5px; /* Bordes redondeados */
        }

        .nav-buttons button:hover {
            background-color: #0056b3; /* Color al pasar el mouse */
        }
    </style>
</head>
<body>

    <header class="header">
        <div class="logo">
            <a href="index.html">MiLogo</a>
            </div>

        <nav class="nav-buttons">
            <button onclick="window.location.href='index.jsp'">Inicio</button>
            <button onclick="window.location.href='seccion2.html'">Catalogo</button>
            <button onclick="window.location.href='seccion3.html'">Login</button>
            <button onclick="window.location.href='seccion4.html'">Register</button>
        </nav>
    </header>

    </body>
</html>