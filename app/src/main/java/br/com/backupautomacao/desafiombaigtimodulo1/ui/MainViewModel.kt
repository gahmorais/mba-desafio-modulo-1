package br.com.backupautomacao.desafiombaigtimodulo1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.backupautomacao.desafiombaigtimodulo1.data.Product
import br.com.backupautomacao.desafiombaigtimodulo1.data.repository.MemoryRepository

class MainViewModel : ViewModel() {
    private val memoryRepository by lazy {
        MemoryRepository(mutableListOf())
    }
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    init {
        updateProductList()
    }


    fun saveProduct(product: Product) {
        memoryRepository.save(product)
        updateProductList()
    }


    private fun updateProductList() {
        _productList.value = memoryRepository.all()
    }

    fun clearListProducts() {
        memoryRepository.clearList()
        updateProductList()
    }
}