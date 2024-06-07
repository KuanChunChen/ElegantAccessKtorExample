package elegant.access.ktor.example

import elegant.access.ktor.example.plugins.configureDatabases
import elegant.access.ktor.example.plugins.configureRouting
import elegant.access.ktor.example.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureRouting()
}
