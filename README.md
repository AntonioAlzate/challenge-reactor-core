# Instrucciones

## Para la preparación de la base de datos
1. Configurar el application.properties con la información se servidor de base de datos MongoDb
2. tener en su servidor una base de datos con el nombre que usted especifique en el application.properties con una colección llamada jugadores.
3. importar el archivo data.csv que se encuentra en el directorio raíz del proyecto.

## Para Utilizar los servicios
1. Para obtener los jugadores mayores a 34 años correspondientes a un club:
   http://localhost:8080/api/v1/player/obtener/mayores34/{club} {club} --> el nombre del club por el cual se desea filtrar puede ir con espacios ej: "FC Barcelona"
2. Para obtener los jugadores con mayor número de victorias de cada país:
   http://localhost:8080/api/v1/player/obtener/ranking-por-pais

### Gracias por leer :v:

======================================

# Challenge reactor-core

Dentro del código se encutra un ejemplo para aplicar reactividad con Spring Boot, se cuenta con una herramienta utilitaria para leer archivos CSV, lo que se buscar es aplicar los operadores reactivas para leer este archivo, y se reta para hacer este procedimiento en mongodb.

Leer las pruebas unitarias.


# Step by Step

- Transformar un CSV una lista de Stream de Java (Usar operadores basicos)
- En vez de usar Java Stream aplicamos reactividad (Reactor Core) con trasnformaciones (Usar operadores basicos)
- Optimizar las consultas y aplicar un servicio web para hacer estos filtros
- Hacer un proceso de migración en donde tomemos los datos del archivo y lo llevemos a una base datos (MangoDB)
- Trabajo directamente los desde una base de datos reactiva y objserva la rendimiento
- Prueba de concurencia con JMeter, donde vamos a colocar 100 hilos de ejeucución para observar los comportamientos de cada servicio
