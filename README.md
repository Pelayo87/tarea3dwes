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
│   ├── com.pelayora.tarea3dwes.configuracion/
│   │   └── I18nConfiguration.java          # Configuración para internacionalización (i18n).
│   ├── com.pelayora.tarea3dwes.controlador/
│   │   ├── EjemplarController.java         # Controlador para gestionar ejemplares.
│   │   ├── PlantaController.java           # Controlador para gestionar plantas.
│   │   ├── SesionController.java           # Controlador para la gestión de sesiones.
│   │   └── ...                             # Otros controladores.
│   ├── com.pelayora.tarea3dwes.modelo/
│   │   ├── Cliente.java                    # Entidad Cliente.
│   │   ├── Planta.java                     # Entidad Planta.
│   │   └── ...                             # Otras entidades del modelo.
│   ├── com.pelayora.tarea3dwes.principal/
│   │   └── Tarea3dwesApplication.java      # Clase principal para iniciar la aplicación.
│   ├── com.pelayora.tarea3dwes.repositorios/
│   │   ├── ClienteRepository.java          # Repositorio para la entidad Cliente.
│   │   └── ...                             # Otros repositorios.
│   ├── com.pelayora.tarea3dwes.servicios/
│   │   ├── ServicioPlanta.java             # Interfaz del servicio para plantas.
│   │   └── ...                             # Otros servicios.
│   ├── com.pelayora.tarea3dwes.serviciosImpl/
│   │   ├── ServicioPlantaImpl.java         # Implementación del servicio de plantas.
│   │   └── ...                             # Otras implementaciones.
│   └── com.pelayora.tarea3dwes.util/
│       └── Utilidades.java                 # Métodos utilitarios para el proyecto.
├── src/main/resources/
│   ├── static/                             # Archivos estáticos (CSS, JS, imágenes, etc.).
│   ├── templates/                          # Plantillas HTML para vistas.
│   ├── application.properties              # Configuración de la aplicación.
│   ├── datos.sql                           # Script SQL de inicialización de la base de datos.
│   ├── messages.properties                 # Mensajes en idioma predeterminado.
│   ├── messages_ast.properties             # Traducciones en asturiano.
│   ├── messages_en.properties              # Traducciones en inglés.
│   └── messages_zh.properties              # Traducciones en chino.

       

✍️ Autor
Pelayo Rodríguez Álvarez - 2º DAW - DWES
