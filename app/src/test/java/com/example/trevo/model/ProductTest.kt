package com.example.trevo.model

import org.junit.Assert.*

import org.junit.Test

class ProductTest {
    var array: Array<String> = arrayOf("produto")

    var product = Product(1, "produto", "produto" , "123" ,"produto", "produto", array)
    var productNull = Product(null,null,null,null,null,null,null)

    @Test
    fun getIdProduto_returnId() {
        product
        assertEquals(1, product.idProduto)
    }

    @Test
    fun getIdProdutoValueNull_returnNull() {
        productNull
        assertEquals(null, productNull.idProduto)
    }

    @Test
    fun getNome_returnName() {
        product
        assertEquals("produto", product.nome)
    }

    @Test
    fun getNomeNull_returnNull() {
        productNull
        assertEquals(null, productNull.nome)
    }

    @Test
    fun getDescricao_returnDescription() {
        product
        assertEquals("produto", product.descricao)
    }

    @Test
    fun getDescricaoNull_returnNull() {
        productNull
        assertEquals(null, productNull.descricao)
    }


    @Test
    fun getArea_returnArea() {
        product
        assertEquals("123", product.area)
    }

    @Test
    fun getAreaNull_returnNull() {
        productNull
        assertEquals(null, productNull.area)
    }

    @Test
    fun getImagem_returnImg() {
        product
        assertEquals("produto", product.imagem)
    }


    @Test
    fun getImagemNull_returnImgNull() {
        productNull
        assertEquals(null, productNull.imagem)
    }


    @Test
    fun getCapa_returnCapa() {
        product
        assertEquals("produto", product.capa)
    }

    @Test
    fun getCapaNull_returnNull() {
        productNull
        assertEquals(null, productNull.capa)
    }


    @Test
    fun getCulturas_returnCultures() {
        product
        assertEquals(array, product.culturas)
    }

    @Test
    fun getCulturasNull_returnNull() {
        productNull
        assertEquals(null, productNull.culturas)
    }

    @Test
    fun testEquals_SameInstance_ReturnsTrue() {
        val result = product.equals(product)
        assertEquals(true, result)
    }

    @Test
    fun testEquals_DifferentClass_ReturnsFalse() {
        val result = product.equals("Não existente")

        assertEquals(false, result)
    }

    @Test
    fun testEquals_DifferentName_ReturnsFalse() {
        var product2 = Product(1, "outro", "produto" , "123" ,"produto", "produto", array)

        val result = product.equals(product2)

        assertEquals(false, result)
    }

    @Test
    fun testEquals_SameName_ReturnsTrue() {
        val product2 = product

        val result = product.equals(product2)

        assertEquals(true, result)
    }


    @Test
    fun testHashCode_SameName_ReturnsSameValue() {
        val product1 = product
        val product2 = Product(2, "produto", "produto" , "123" ,"produto", "produto", array)

        val hashCode1 = product1.hashCode()
        val hashCode2 = product2.hashCode()

        assertEquals(hashCode1, hashCode2)
    }

    @Test
    fun testHashCode_DifferentName_ReturnsDifferentValue() {
        val product1 = product
        val product2 = Product(2, "outro", "produto" , "123" ,"produto", "produto", array)

        val hashCode1 = product1.hashCode()
        val hashCode2 = product2.hashCode()

        assertEquals(false, hashCode1 == hashCode2)
    }
}