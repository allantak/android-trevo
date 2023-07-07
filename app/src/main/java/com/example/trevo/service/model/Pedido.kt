package com.example.trevo.service.model


data class Pedido(
    val cliente: Cliente,
    val produtos: List<Int>
)

data class Cliente(
    val nome: String?,
    val email: String?,
    val telefone: String?
)


