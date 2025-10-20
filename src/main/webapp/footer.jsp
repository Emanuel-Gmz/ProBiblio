<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página con Footer</title>

    <style>
        /* Estilos generales para el layout */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Asegura que el footer se pegue al fondo */
        }

        /* Estilos para el contenido principal (para empujar el footer) */
        main {
            flex-grow: 1;
            padding: 20px;
        }

        /* ------------------------------------------------------------------
           Estilos del Footer
           ------------------------------------------------------------------ */
        .footer {
            background-color: #343a40; /* Fondo oscuro */
            color: #ffffff; /* Texto blanco */
            padding-top: 40px;
            font-size: 14px;
            width: 100%;
        }

        .footer-content {
            display: flex;
            justify-content: space-around; /* Distribuye el espacio entre las columnas */
            padding: 0 50px 20px 50px;
            max-width: 1200px; /* Limita el ancho máximo del contenido */
            margin: 0 auto;
            flex-wrap: wrap;
        }

        .footer-section {
            flex: 1;
            min-width: 200px;
            margin-bottom: 20px;
            padding: 0 15px;
        }

        .footer-section h3 {
            color: #007bff; /* Color azul para los títulos */
            margin-bottom: 15px;
            font-size: 16px;
            border-bottom: 2px solid #007bff;
            padding-bottom: 5px;
        }

        .footer-section p {
            line-height: 1.6;
            margin-bottom: 10px;
        }

        .footer-section ul {
            list-style: none;
            padding: 0;
        }

        .footer-section a {
            color: #ccc; /* Gris claro para los enlaces */
            text-decoration: none;
            transition: color 0.2s;
        }

        .footer-section a:hover {
            color: #ffffff;
        }

        .social-icon {
            display: inline-block;
            margin-right: 10px;
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .footer-bottom {
            background-color: #212529; /* Fondo más oscuro para el copyright */
            color: #aaa;
            text-align: center;
            padding: 10px 0;
            margin-top: 10px;
        }

    </style>

<body>
    <footer class="footer">
        <div class="footer-content">

            <div class="footer-section about">
                <h3>Sobre ProBiblio</h3>
                <p>Tu plataforma bibliotecaria y catalogo en linea.</p>
                <p>Email: contacto@probiblio.com</p>
                <p>Teléfono: +54 9 11 XXXX-XXXX</p>
            </div>

            <div class="footer-section links">
                <h3>Enlaces Rapidos</h3>
                <ul>
                    <li><a href="#">Nosotros</a></li>
                    <li><a href="#">Preguntas Frecuentes</a></li>
                    <li><a href="#">Terminos y Condiciones</a></li>
                </ul>
            </div>

            <div class="footer-section social">
                <h3>Siguenos</h3>
                <a href="#" class="social-icon">Facebook</a>
                <a href="#" class="social-icon">Twitter</a>
                <a href="#" class="social-icon">Instagram</a>
            </div>

        </div>

        <div class="footer-bottom">
            @copyright 2025 - Proyecto Web JSP - Test
        </div>
    </footer>

</body>
</html>