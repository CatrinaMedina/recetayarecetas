# RecetaYa - Microservicio de Recetas

Microservicio desarrollado con Java 17 y Spring Boot para la Evaluación Parcial 2.

## Funcionalidades

- Crear recetas médicas.
- Consultar todas las recetas o buscar por ID.
- Actualizar recetas y cantidades de medicamentos.
- Eliminar recetas.
- Consultar recetas según su estado.
- Validar datos y cantidades.
- Persistir recetas y sus detalles mediante Spring Data JPA y H2.

## Tecnologías

- Java 17.
- Spring Boot.
- Spring Data JPA e Hibernate.
- Base de datos H2.
- Maven Wrapper.

## Requisitos

- JDK 17 o compatible.
- Git.
- Puerto 8082 disponible.
- Postman para realizar las pruebas.

## Clonar el repositorio

git clone https://github.com/CatrinaMedina/recetayarecetas.git

cd recetayarecetas

## Compilar y generar el JAR

Desde Git Bash:

./mvnw clean package

Al finalizar correctamente aparece BUILD SUCCESS.

El archivo JAR se genera en la carpeta target.

## Ejecutar localmente

./mvnw spring-boot:run

El microservicio funciona en:

http://localhost:8082

## Base de datos

Se utiliza H2 con persistencia en archivo.

Configuracion en application.properties:

spring.datasource.url=jdbc:h2:file:./data/recetasdb

spring.jpa.hibernate.ddl-auto=update

La consola H2 esta habilitada en:

http://localhost:8082/h2-console

## Arquitectura

- model: entidades Receta y DetalleReceta.
- repository: acceso a datos mediante JpaRepository.
- service: interfaz con las operaciones del microservicio.
- RecetaServiceImpl: implementacion de la logica dentro del paquete service.
- controller: endpoints REST del microservicio.

La entidad Receta tiene una relacion OneToMany con DetalleReceta.
Cada detalle tiene una relacion ManyToOne con su receta.

## Endpoints

- POST /recetas
- GET /recetas
- GET /recetas/{id}
- PUT /recetas/{id}
- DELETE /recetas/{id}
- GET /recetas/estado/{estado}

## Ejemplo de creacion

POST http://localhost:8082/recetas

Body JSON:

{
  "medicoId": 1,
  "pacienteId": 1,
  "sucursalId": 1,
  "detalles": [
    {
      "medicamentoId": 1,
      "cantidad": 2
    }
  ]
}

Una receta nueva queda en estado PENDIENTE_RESERVA.

## Validaciones y respuestas

- 201 Created: receta creada.
- 200 OK: consulta o actualizacion correcta.
- 204 No Content: receta eliminada.
- 400 Bad Request: datos invalidos o cantidades incorrectas.
- 404 Not Found: receta no encontrada.

## Integracion con API Gateway

El API Gateway funciona en el puerto 8080.

Mediante el Gateway:
- MEDICO puede crear, consultar, actualizar y eliminar recetas.
- FARMACEUTICO puede consultar recetas.
- Se requiere autenticacion JWT para acceder a estas rutas.

Para probar mediante el Gateway, deben estar ejecutandose
Usuarios, Recetas y API Gateway.

## Proyecto

RecetaYa - Java: Diseno y Construccion de Soluciones Nativas en Nube.
