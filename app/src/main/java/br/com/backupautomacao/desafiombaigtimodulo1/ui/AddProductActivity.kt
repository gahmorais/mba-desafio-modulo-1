package br.com.backupautomacao.desafiombaigtimodulo1.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.backupautomacao.desafiombaigtimodulo1.R
import br.com.backupautomacao.desafiombaigtimodulo1.data.Product
import br.com.backupautomacao.desafiombaigtimodulo1.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        handleButtons()
    }

    private fun handleButtons() {
        binding.apply {
            btnCancel.setOnClickListener {
                finish()
            }

            btnSaveProduct.setOnClickListener {
                saveProduct()
            }
        }
    }

    private fun saveProduct() {

        binding.apply {
            val description = descriptionET.text.toString()
            val quantity = quantityET.text.toString()

            descriptionTIL.error = if (description.isEmpty()) {
                getString(R.string.error_empty_description)
            } else {
                null
            }

            quantityTIL.error = if (quantity == "0") {
                getString(R.string.error_quantity)
            } else {
                null
            }

            if (description.isNotEmpty() && quantity.isNotEmpty()) {
                Intent().apply {
                    putExtra(
                        MainActivity.PRODUCT_MESSAGE,
                        Product(description, Integer.parseInt(quantity))
                    )
                    setResult(RESULT_OK, this)
                }
                finish()
            }
        }

    }
}