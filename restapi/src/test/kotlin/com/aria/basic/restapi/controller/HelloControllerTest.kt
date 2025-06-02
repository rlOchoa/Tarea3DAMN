package com.aria.basic.restapi.controller

import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(HelloController::class)
class HelloControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `debe retornar mensaje Hola Mundo en formato JSON`() {
        mockMvc.get("/api/saludo")
            .andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$.mensaje", equalTo("Hola Mundo desde Spring Boot"))
            }
    }
}