package br.com.backupautomacao.desafiombaigtimodulo1.data

import org.junit.Assert.*
import org.junit.Test

class ProductTest {
  @Test
  fun createObject(){
    val newProduct = Product("Teste", 1)
    assertEquals("Teste", newProduct.description)
    assertEquals(1, newProduct.quantity)
  }
}