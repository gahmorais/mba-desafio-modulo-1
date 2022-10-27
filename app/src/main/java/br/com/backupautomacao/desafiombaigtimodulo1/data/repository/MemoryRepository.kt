package br.com.backupautomacao.desafiombaigtimodulo1.data.repository

import br.com.backupautomacao.desafiombaigtimodulo1.data.Product

class MemoryRepository(novaLista: MutableList<Product>) {
  private val listDb: MutableList<Product> = novaLista
  fun save(product: Product) {
    listDb.add(product)
  }

  fun clearList() {
    listDb.clear()
  }

  fun all() = listDb.toList()
}