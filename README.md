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

---

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
    │   │               │   ├── remote/
    │   │               │   └── repository/
    │   │               ├── ui/
    │   │               │   ├── openlibrary/
    │   │               │   ├── screen/
    │   │               │   ├── navigation/
    │   │               │   └── viewmodel/
    │   │               └── ui/
    │   │                   └── theme/
    │   └── res/
    │       └── xml/
    │           └── network_security_config.xml
```

---

## 🧩 Funcionalidades implementadas – Ejercicio 1
- [x] Consumo del endpoint `/api/saludo` utilizando Retrofit.
- [x] Visualización del mensaje desde backend Spring Boot.
- [x] Estado de carga y errores controlado con Jetpack Compose.
- [x] Retry automático con botón.
- [x] Arquitectura MVVM implementada (Repository, ViewModel, UI).

---

## 📘 Funcionalidades implementadas – Ejercicio 2 (Open Library)

### 🔍 Funcionalidad principal
- [x] Búsqueda de libros por texto (`search.json?q=`).
- [x] Navegación a vista de detalles del libro (`/works/{key}.json`).
- [x] Visualización de portada, título, autor y descripción del libro.

### 🖼️ Visualización
- [x] Lista vertical (`LazyColumn`) o cuadrícula (`LazyVerticalGrid`).
- [x] Alternancia entre vistas desde el menú hamburguesa.
- [x] Imágenes cargadas con Coil.
- [x] Placeholder "Sin imagen" en resultados sin portada.

### 🔁 Experiencia de usuario
- [x] Indicadores de carga (`CircularProgressIndicator`).
- [x] Manejo de errores con mensaje descriptivo.
- [x] Opción de reintentar consulta en caso de error.
- [x] Pull-to-refresh con Accompanist SwipeRefresh.
- [x] Cache en memoria para no repetir peticiones con mismos términos.
- [x] Mensaje de "No se encontraron resultados…" si la búsqueda fue vacía.

---

## 🎮 Navegación de Pantallas
```
Inicio (HomeScreen)
│
├── /saludo               → Consume API REST local (Spring Boot)
└── /public               → Consume API pública Open Library
       └── /detail/...    → Pantalla de detalle de libro
```

---

## ⚙️ Configuración de red
Para permitir conexiones HTTP desde el emulador al backend local, se ha configurado el archivo network_security_config.xml y se ha referenciado correctamente en el AndroidManifest.xml.

## 🚀 Instrucciones para ejecutar la aplicación Android
Asegúrate de que el backend esté en ejecución y accesible desde el emulador en http://10.0.2.2:8080/api/saludo.

Abre el proyecto en Android Studio.

Ejecuta la aplicación en un emulador o dispositivo físico.

Verifica que se muestre el mensaje de saludo en la pantalla principal.

## 📷 Evidencias – Capturas de pantalla

### 🔹 Ejecución del backend
![image](https://github.com/user-attachments/assets/b5c9aa00-115d-490a-b56a-287b50826be1)

![image](https://github.com/user-attachments/assets/d95d84b9-c7fe-4f77-9806-2b9c9b947a61)

### 🔹 Pantalla de saludo con consumo de REST API
![image](https://github.com/user-attachments/assets/1fd39e90-aca7-4f8a-ac8f-3e1f018ce952)

### 🔹 Pantalla principal Open Library (búsqueda)
![image](https://github.com/user-attachments/assets/7e29129f-5fb8-471a-b76d-ee4b47e87fd2)

### 🔹 Grid/List alternable desde menú
![image](https://github.com/user-attachments/assets/766617ee-65a4-4c0d-8da7-d0efe5b34fea)

![image](https://github.com/user-attachments/assets/9b166fbb-b244-47f3-bbf9-384bbb7f95ab)

### 🔹 Vista de detalle con sinopsis cargada
![image](https://github.com/user-attachments/assets/57c5d83b-0bac-4637-b383-e8f60d629202)

### 🔹 Pull to refresh
![image](https://github.com/user-attachments/assets/750d2333-62f8-4af9-9d4d-ebca697bf023)

### 🔹 Resultado sin coincidencias
![image](https://github.com/user-attachments/assets/90e3a9aa-2561-4439-8460-9a637c9b1544)
