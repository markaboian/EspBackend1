## EspBackend1
# Repositorio para parcial y final de la especialización de backend 1

# Para el Examen final, en el apartado Resiliencia - Resilence4j se pidió:
* Se debe seleccionar uno de los servicios (preferentemente el que consideres que será más utilizado) y adaptarlo para que el mismo sea tolerante a fallos.
Para lo anterior deberás:
○ Definir esquema de resiliencia, implementar retry, un método fallback y configurar las reglas del circuito.
○ Describir la solución de dicha implementación, planteando un supuesto escenario que requiera implementar este patrón, justificándolo en un comentario o en el Readme del repositorio perteneciente al proyecto.

# Justificación

Entendemos que el microservicio Catalog será el más utilizado, por ello, estratégicamente lo adaptaremos para que este sea tolerante a fallos. 
Independientemente de que hemos visto en clase Circuit breaker, las ventajas de este patrón nos aportará en Catalog lo siguiente:
* En situaciones en las que los microservicios de películas y series (movie-service y serie-service) experimenten fallas o sobrecargas, el Circuit Breaker se activará, evitando llamadas continuas y permitiendo que el servicio catalog se recupere y continúe funcionando correctamente. 
* Al definir el umbral de tasa de fallos (failureRateThreshold) en un valor razonable (en este caso, 50%), el Circuit Breaker se activará cuando la tasa de errores supere este límite.
* Resilience4j ofrece una configuración personalizable.
 
