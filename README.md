# 📘 Tarea 3 – Desarrollo de Aplicaciones Móviles Nativas
Este proyecto corresponde a la Tarea 3 de la asignatura "Desarrollo de Aplicaciones Móviles Nativas". Consiste en el desarrollo de una API REST (en este caso utilizando Spring Boot) y una aplicación Android que consume dicha API, implementada con Jetpack Compose y arquitectura MVVM.

## 📁 Estructura del Repositorio
El repositorio está organizado en las siguientes carpetas principales:
```
Tarea3DAMN/
├── apiconsumption/    # Aplicación Android (Jetpack Compose)
└── restapi/           # Backend Spring Boot (API REST)
```

## 🔧 Backend – API REST con Spring Boot
📌 Tecnologías utilizadas
* Spring Boot 3.5.0

* Kotlin DSL

* Gradle como sistema de construcción

* Java 21

* Empaquetado: JAR

## 📁 Estructura del proyecto
```
restapi/
├── build.gradle.kts
├── settings.gradle.kts
└── src/
    ├── main/
    │   ├── kotlin/
    │   │   └── com/
    │   │       └── aria/
    │   │           └── basic/
    │   │               └── restapi/
    │   │                   ├── RestapiApplication.kt
    │   │                   └── controller/
    │   │                       └── HelloController.kt
    │   └── resources/
    │       └── application.yml
    └── test/
        └── kotlin/
            └── com/
                └── aria/
                    └── basic/
                        └── restapi/
                            └── controller/
                                └── HelloControllerTest.kt
```
## 📄 Endpoint implementado
GET /api/saludo: Retorna un mensaje de saludo en formato JSON.

Ejemplo de respuesta:

```json
{
  "mensaje": "Hola Mundo desde Spring Boot"
}
```
## 🧪 Pruebas unitarias
Se ha implementado una prueba unitaria para el endpoint `/api/saludo` utilizando MockMvc en el archivo HelloControllerTest.kt.

## 🚀 Instrucciones para ejecutar el backend
Navega a la carpeta restapi:
``` bash
cd restapi
```

Ejecuta la aplicación:
``` bash
./gradlew bootRun
```

Verifica que el endpoint esté disponible accediendo a:
``` bash
http://localhost:8080/api/saludo
```
![image](https://github.com/user-attachments/assets/d95d84b9-c7fe-4f77-9806-2b9c9b947a61)


La consola debe de ser capaz de mostrar el siguiente mensaje en cuanto se ejecute correctamente la inicialización del backend.
![image](https://github.com/user-attachments/assets/b5c9aa00-115d-490a-b56a-287b50826be1)


# 📱 Aplicación Android – Consumo de la API REST
## 📌 Tecnologías utilizadas
* Android Studio 2024.2.2

* Jetpack Compose

* Kotlin

* Retrofit para consumo de API

* Arquitectura MVVM

* API mínima: 26 (Android 8.0)

* API objetivo: 35 (Android 14)

## 📁 Estructura del proyecto
```
apiconsumption/
├── build.gradle.kts
├── settings.gradle.kts
└── src/
    ├── main/
    │   ├── AndroidManifest.xml
    │   ├── kotlin/
    │   │   └── com/
    │   │       └── aria/
    │   │           └── apiconsumption/
    │   │               ├── MainActivity.kt
    │   │               ├── data/
    │   │               │   ├── model/
    │   │               │   │   └── SaludoResponse.kt
    │   │               │   ├── remote/
    │   │               │   │   ├── ApiService.kt
    │   │               │   │   └── RetrofitClient.kt
    │   │               │   └── repository/
    │   │               │       └── SaludoRepository.kt
    │   │               ├── ui/
    │   │               │   ├── screen/
    │   │               │   │   └── MainScreen.kt
    │   │               │   └── viewmodel/
    │   │               │       └── SaludoViewModel.kt
    │   │               └── ui/
    │   │                   └── theme/
    │   │                       └── ApiConsumptionTheme.kt
    │   └── res/
    │       └── xml/
    │           └── network_security_config.xml
    └── test/
        └── kotlin/
            └── com/
                └── aria/
                    └── apiconsumption/
                        └── ExampleUnitTest.kt
```
## 🧩 Funcionalidades implementadas
* [X] Consumo del endpoint /api/saludo utilizando Retrofit.

* [X] Visualización del mensaje en la interfaz de usuario con Jetpack Compose.

* [X] Manejo de estados: carga, éxito y error.

* [X] Botón de reintento en caso de error.

* [X] Arquitectura MVVM implementada:

* [X] SaludoRepository.kt: Manejo de la lógica de negocio.

* [X] SaludoViewModel.kt: Gestión del estado de la UI.

* [X] MainScreen.kt: Interfaz de usuario.

## ⚙️ Configuración de red
Para permitir conexiones HTTP desde el emulador al backend local, se ha configurado el archivo network_security_config.xml y se ha referenciado correctamente en el AndroidManifest.xml.

## 🚀 Instrucciones para ejecutar la aplicación Android
Asegúrate de que el backend esté en ejecución y accesible desde el emulador en http://10.0.2.2:8080/api/saludo.

Abre el proyecto en Android Studio.

Ejecuta la aplicación en un emulador o dispositivo físico.

Verifica que se muestre el mensaje de saludo en la pantalla principal.
![image](https://github.com/user-attachments/assets/1fd39e90-aca7-4f8a-ac8f-3e1f018ce952)
