package com.example.trevo.model

class Product(
    val idProduto: Int?,
    val nome: String?,
    val descricao: String?,
    val area: String?,
    val imagem: String?,
    val capa: String?,
    val culturas: Array<String>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        return nome?.hashCode() ?: 0
    }
}
