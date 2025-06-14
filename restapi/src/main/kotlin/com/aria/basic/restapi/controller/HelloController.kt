package com.aria.basic.restapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloController {

    @GetMapping("/saludo")
    fun saludo(): Map<String, String> {
        return mapOf("mensaje" to "Hola Mundo desde Spring Boot")
    }
}
