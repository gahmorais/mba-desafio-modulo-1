package br.com.backupautomacao.desafiombaigtimodulo1.data.repository

import androidx.lifecycle.Transformations.map
import br.com.backupautomacao.desafiombaigtimodulo1.data.Product
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MemoryRepositoryTest {
  private val memoryRepository = MemoryRepository(mutableListOf())

  @Before
  fun setupList() {
    (1..3).map {
      memoryRepository.save(Product("Teste $it", it * 2))
    }
  }

  @Test
  fun checkQuantity() {
    assertEquals(memoryRepository.all().size, 3)
  }

  @Test
  fun getProductTest() {
    val productThird = memoryRepository.all()[2]
    assertEquals("Teste 3", productThird.description)
  }

  @Test
  fun clearListTest() {
    memoryRepository.clearList()
    assertEquals(0, memoryRepository.all().size)
  }

  @Test
  fun addItemTest() {
    memoryRepository.save(Product("Teste 4", 4 * 2))
    assertEquals(memoryRepository.all().size, 4)
  }
}