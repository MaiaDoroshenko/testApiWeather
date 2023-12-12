
Autor
Nombre: [Maia Doroshenko Potskhverashvili]
Email : [a@maiadoroshenko.com]
Telefono:542617005879

Descripcion
El objetivo de este microservicio es proporcionar a los usuarios acceso a información 
actualizada sobre el clima. Utiliza las APIs públicas de AccuWeather para obtener
datos precisos y los almacena en una base de datos H2 para un acceso eficiente.

Tecnologías utilizadas
Java
Spring Boot
Spring WebFlux
Spring Data JPA
H2 Database

Estructura del Proyecto
El proyecto está estructurado de la siguiente manera:

src/main/java/com/meteosolutions/weatherapi/: Contiene el código fuente del proyecto.
config/: Clases de configuración.
helper/: Clases de ayuda para interactuar con las APIs de AccuWeather.
service/: Lógica de negocio y servicios del microservicio.
dto/: Clases DTO para transferir datos.
model/: Entidades de datos y repositorios.
src/test/java/com/meteosolutions/weatherapi/: Contiene pruebas unitarias y de integración.

Configuración
Propiedades
Las propiedades de configuración se encuentran en src/main/resources/config/api_application.properties.


¡Claro! Aquí tienes un ejemplo de cómo podrías estructurar un archivo README para tu proyecto:

Proyecto WeatherAPI
Este proyecto contiene un microservicio que proporciona una API REST para obtener información del clima utilizando las APIs públicas de AccuWeather. Los datos recuperados se almacenan en una base de datos H2 para su posterior acceso.

Autor
Nombre: [Tu Nombre Completo]
Email de contacto: [Tu Email]
Descripción
El objetivo de este microservicio es proporcionar a los usuarios acceso a información actualizada sobre el clima. Utiliza las APIs públicas de AccuWeather para obtener datos precisos y los almacena en una base de datos H2 para un acceso eficiente.

Tecnologías utilizadas
Java
Spring Boot
Spring WebFlux
Spring Data JPA
H2 Database
Estructura del Proyecto
El proyecto está estructurado de la siguiente manera:

src/main/java/com/meteosolutions/weatherapi/: Contiene el código fuente del proyecto.
config/: Clases de configuración.
helper/: Clases de ayuda para interactuar con las APIs de AccuWeather.
service/: Lógica de negocio y servicios del microservicio.
dto/: Clases DTO para transferir datos.
model/: Entidades de datos y repositorios.
src/test/java/com/meteosolutions/weatherapi/: Contiene pruebas unitarias y de integración.
Configuración
Propiedades
Las propiedades de configuración se encuentran en src/main/resources/config/api_application.properties.

Dependencias
Spring Boot
Spring WebFlux
Spring Data JPA
H2 Database
JUnit
Mockito


Ejecución del Proyecto
Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

Clona el repositorio desde [URL del repositorio].
Abre el proyecto en tu IDE preferido.
Configura las propiedades necesarias.
Ejecuta la aplicación.
Accede a la API mediante el navegador o herramientas como cURL o Postman.






