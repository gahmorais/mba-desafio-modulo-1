package br.com.backupautomacao.desafiombaigtimodulo1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import br.com.backupautomacao.desafiombaigtimodulo1.data.Product
import br.com.backupautomacao.desafiombaigtimodulo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val retorno =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    if (it.hasExtra(PRODUCT_MESSAGE)) {
                        val newProduct = it.getParcelableExtra<Product>(PRODUCT_MESSAGE)!!
                        mainViewModel.saveProduct(newProduct)
                    }
                }
            }
        }

    companion object {
        const val PRODUCT_MESSAGE = "product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mainViewModel.productList.observe(this) { list ->
            updateList(list)
        }
    }

    private fun updateList(list: List<Product>) {
        if (list.isEmpty()) {
            binding.tvErrorList.visibility = View.VISIBLE
            binding.rvProductList.visibility = View.GONE
            binding.containerTotal.visibility = View.GONE
        } else {
            binding.tvErrorList.visibility = View.GONE
            binding.rvProductList.visibility = View.VISIBLE
            binding.containerTotal.visibility = View.VISIBLE
            binding.rvProductList.adapter = ProductsAdapter(list)
            binding.totalProducts.text = list.size.toString()
        }
    }

    private fun setupListeners() {
        fabButtonAction()
    }

    private fun fabButtonAction() {
        binding
            .fabAddProduct
            .setOnClickListener {
                Intent(this, AddProductActivity::class.java).apply {
                    retorno.launch(this)
                }
            }
        binding.fabAddProduct.setOnLongClickListener {
            Toast.makeText(applicationContext, "Itens removidos", Toast.LENGTH_SHORT).show()
            mainViewModel.clearListProducts()
            it.isLongClickable
        }
    }
}
