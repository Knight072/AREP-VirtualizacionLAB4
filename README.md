# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

## Introduccón

Este proyecto presenta un la aplicación web APP-LB-RoundRobin utiliza diversas tecnologías para garantizar eficiencia y escalabilidad. MongoDB, ejecutado en un contenedor Docker dentro de una máquina virtual EC2 en AWS, proporciona una base de datos robusta. El LogService, implementado como un servicio REST, recibe y almacena cadenas de texto, devolviendo las últimas 10 almacenadas en formato JSON. Este servicio se distribuye en tres instancias, lo que permite balancear la carga utilizando un algoritmo Round Robin, asegurando un procesamiento eficiente y respuestas rápidas al cliente web.

## Comenzando

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Construido con:

* [Git](https://git-scm.com) - Control de versiones
* [Maven](https://maven.apache.org/download.cgi) -  Manejador de dependencias
* [java](https://www.oracle.com/java/technologies/downloads/#java22) - Lenguaje de programación

### Requisitos:

#### ⚠️ Importante

Es necesario tener instalado Git, Maven 3.9.9 y Java 17 para poder ejecutar el proyecto.

## Arquitectura de la Aplicación
La arquitectura de la aplicación web APP-LB-RoundRobin está diseñada para ser escalable y eficiente, utilizando una combinación de tecnologías modernas. El sistema incluye una base de datos MongoDB alojada en un contenedor Docker dentro de una máquina virtual EC2 en AWS, proporcionando un almacenamiento flexible y fiable. El servicio LogService, implementado como una API REST, permite recibir mensajes de texto, almacenarlos, y devolver un historial de los últimos 10 mensajes en formato JSON. Este servicio se ejecuta en tres instancias independientes, cada una en un puerto diferente, para equilibrar la carga mediante el algoritmo de balanceo Round Robin, distribuyendo las solicitudes de manera equitativa. La aplicación web permite a los usuarios enviar mensajes desde un cliente web, garantizando que las solicitudes se procesen de manera eficiente y las respuestas sean rápidas.

## Diagrama de la Arquitectura

### Cliente Servidor

![!image01](https://github.com/user-attachments/assets/3478f8e6-f897-447c-a7df-93e554f5956b)

El diagrama presenta una arquitectura de microservicios desplegada en una instancia de **Amazon Elastic Compute Cloud (EC2)**. La aplicación se encuentra encapsulada dentro de un contenedor **Docker**, lo que permite un aislamiento y portabilidad eficientes.

## Componentes y Funcionalidades

### Web Bootstrap:
- **Interfaz de usuario**: Presenta un campo de texto y un botón.
- **Funcionalidad**: Al ingresar un texto y hacer clic en el botón, envía una solicitud HTTP al servicio de balanceo de carga.

### APP-LB-RoundRobin:
- **Balanceador de carga**: Implementa un algoritmo de Round Robin para distribuir las solicitudes de manera equitativa entre las instancias de LogService.
- **Servicio REST**: Recibe las solicitudes de la interfaz web, las enruta a una instancia de LogService específica y devuelve la respuesta al cliente.

### LogService:
- **Microservicio REST**:
   - **Recepción de datos**: Recibe cadenas de texto desde el balanceador de carga.
   - **Almacenamiento**: Almacena las cadenas en la base de datos MongoDB.
   - **Respuesta**: Devuelve un objeto JSON con las últimas 10 cadenas almacenadas y sus respectivas fechas.
- **Instancias múltiples**: El diagrama muestra tres instancias de LogService, lo que permite escalar horizontalmente la aplicación y mejorar la disponibilidad.

### MongoDB:
- **Base de datos NoSQL**: Almacena las cadenas de texto enviadas por los servicios LogService.
- **Contenedor Docker**: Se ejecuta dentro de un contenedor Docker para facilitar su gestión y despliegue.

### Docker Engine:
- **Motor de contenedores**: Gestiona los contenedores Docker que ejecutan los servicios de la aplicación.

### AWS EC2:
- **Máquina virtual**: Proporciona la infraestructura subyacente para ejecutar los contenedores Docker.

### Security Group:
- **Grupo de seguridad**: Define las reglas de firewall para controlar el tráfico entrante y saliente de la instancia EC2.

## Flujo de la Aplicación

1. El usuario ingresa un texto en la interfaz web y hace clic en el botón.
2. La solicitud HTTP se envía al balanceador de carga **APP-LB-RoundRobin**.
3. El balanceador de carga selecciona una instancia de **LogService** de acuerdo con el algoritmo **Round Robin**.
4. La instancia de **LogService** seleccionada recibe la solicitud, almacena la cadena en **MongoDB** y devuelve un objeto JSON con los últimos 10 registros.
5. El balanceador de carga reenvía la respuesta al cliente web.
6. La interfaz web actualiza la pantalla con la información recibida.

## Beneficios de esta Arquitectura

- **Escalabilidad**: La arquitectura basada en microservicios permite escalar horizontalmente cada servicio de forma independiente, lo que facilita adaptarse a cambios en la carga de trabajo.
- **Resiliencia**: La replicación de los servicios **LogService** y el uso de un balanceador de carga aumentan la disponibilidad de la aplicación.
- **Flexibilidad**: El uso de contenedores **Docker** facilita el despliegue y la gestión de los servicios.
- **Desacoplado**: Cada servicio tiene una responsabilidad específica, lo que facilita el desarrollo, mantenimiento y actualización de la aplicación.

## Consideraciones Adicionales

- **Persistencia de datos**: **MongoDB** proporciona una alta disponibilidad y escalabilidad para almacenar los datos de la aplicación.
- **Seguridad**: El grupo de seguridad configurado en la instancia **EC2** ayuda a proteger la aplicación de accesos no autorizados.
- **Monitoreo**: Es importante implementar herramientas de monitoreo para supervisar el rendimiento y la salud de los servicios.
- **Balanceo de carga**: El algoritmo **Round Robin** puede ser reemplazado por otros algoritmos más sofisticados según las necesidades de la aplicación.

Esta arquitectura proporciona una base sólida para construir aplicaciones web escalables y resilientes. Sin embargo, la implementación específica puede variar dependiendo de los requisitos y las tecnologías utilizadas en cada proyecto.

## Cómo comprender el funcionamiento de las funcionalidades requeridas

### LogService
Esta parte de la aplicación permite recibir las peticiones del round robin haciendo uso de las anotaciones `@GetMapping` y `@RequestParam`, a su vez guarda el mensaje en la base de datos y devuelve el historial de los últimos 10 mensajes :
```bash
   @GetMapping("/message")
    public ResponseEntity<?> getMessage(@RequestParam String message) {
        Message newMessage = new Message(message);
        messageService.saveMessage(newMessage);
        try{
            return new ResponseEntity<>(messageService.getlastTenMessage(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>("{\"error\":\"Error al obtener los últimos 10 mensajes\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  ```
![image02](https://github.com/user-attachments/assets/79235114-4f59-4718-93e9-daec68561ce8)

### RoundRobin
Esta parte de la aplicación permite hacer peticiones al logService haciendo uso de las anotaciones `@GetMapping` y `@RequestParam`, gestionando las solicitudes de manera equitativa entre las instancias de LogService:  :
```bash
   @GetMapping("/round-robin")
    public ResponseEntity<String> sendMessage(@RequestParam String message) throws IOException {

        int currentIndex = index.getAndUpdate(i -> (i + 1) % logServiceUrls.size());
        String serviceUrl = logServiceUrls.get(currentIndex);

        String urlWithParams = serviceUrl + "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8.toString());

        URL url = new URL(urlWithParams);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        .........
  ```
## Instalación y ejecución

Para instalar y ejecutar esta aplicación, sigue los siguientes pasos:

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/AndresArias02/AREP-Taller4.git
   cd AREP-taller4
   ```

2. **Compilar y ejecutar:**

    ```bash
   mvn clean compile
   docker-compose up --build
   ```

3. **Abrir la aplicación en un navegador web:**

   Navega a http://localhost:8080/index.html para interactuar con la aplicación.

## Ejecutando las pruebas

Para ejecutar las pruebas, ejecute el siguiente comando:

```bash
mvn test
```
![image03](https://github.com/user-attachments/assets/6c98d119-3435-4a1e-bac1-e3a9a079e003)
## versionamiento

![AREP LAB 04](https://img.shields.io/badge/AREP_LAB_04-v1.0.0-blue)

## Autores

- Andrés Arias - [AndresArias02](https://github.com/AndresArias02)

## Licencia

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este proyecto está bajo la Licencia (MIT) - ver el archivo [LICENSE](LICENSE.md) para ver más detalles.

## Agradecimientos

- Al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co) por compartir sus conocimientos.
    
