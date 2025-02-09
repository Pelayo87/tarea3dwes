🌱 Tarea 3 DWES - Vivero
Proyecto para la Tarea 3 de Desarrollo Web en Entorno Servidor (DWES). En esta práctica, se implementa un sistema de gestión para un vivero, aplicando arquitectura en capas y buenas prácticas de desarrollo en Spring Web.

📌 Tecnologías utilizadas
Lenguaje: Java
Base de datos: MySQL
HTML y CSS para la parte visual
Apache (servidor local con XAMPP)

📂 Estructura del proyecto
tarea3dwes/
├── src/main/java/
│   ├── com.pelayora.tarea3dwes.configuracion/   # Clases de configuración del proyecto.
│   │   └── I18nConfiguration.java               # Configuración para internacionalización (i18n).  
│   ├── com.pelayora.tarea3dwes.controlador/     # Controladores que gestionan las peticiones.
│   ├── com.pelayora.tarea3dwes.modelo/          # Clases del modelo que representan la lógica de negocio.
│   ├── com.pelayora.tarea3dwes.principal/       # Clase principal que inicia la aplicación.
│   ├── com.pelayora.tarea3dwes.repositorios/    # Repositorios para la gestión de datos.
│   ├── com.pelayora.tarea3dwes.servicios/       # Interfaces de los servicios de negocio.
│   ├── com.pelayora.tarea3dwes.serviciosImpl/   # Implementaciones de los servicios de negocio.
│   ├── com.pelayora.tarea3dwes.util/            # Utilidades y funciones de apoyo.
├── src/main/resources/
│   ├── static/                                  # Recursos estáticos (CSS, JS, imágenes, etc.).
│   ├── templates/                               # Plantillas HTML para vistas.
│   ├── application.properties                   # Configuración principal del proyecto.
│   ├── datos.sql                                # Script SQL para la base de datos.
│   ├── messages.properties                      # Mensajes de idioma predeterminado.
│   ├── messages_ast.properties                  # Mensajes en asturiano.
│   ├── messages_en.properties                   # Mensajes en inglés.
│   ├── messages_zh.properties                   # Mensajes en chino.
       

✍️ Autor
Pelayo Rodríguez Álvarez - 2º DAW - DWES
