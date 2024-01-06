package com.example.function

import com.fasterxml.jackson.databind.ObjectMapper
import fuel.Fuel
import fuel.get
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FunctionApplication {

    class Payload {
        lateinit var url: String
    }


    @Bean
    fun batchCaller(): (String) -> (String) {
        return { payloadAsString ->
            val payload = ObjectMapper().readValue(payloadAsString, Payload::class.java)

            runBlocking {
                val response = Fuel.get(payload.url)
                response.statusCode.toString()
            }

        }
    }

}

fun main(args: Array<String>) {
    runApplication<FunctionApplication>(*args)
}

