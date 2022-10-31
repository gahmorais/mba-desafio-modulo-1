package br.com.backupautomacao.desafiombaigtimodulo1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.backupautomacao.desafiombaigtimodulo1.data.Product
import br.com.backupautomacao.desafiombaigtimodulo1.databinding.ProductItemListBinding

class ProductsAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private lateinit var binding: ProductItemListBinding
    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Product) {
            binding.apply {
                itemQuantity.text = item.quantity.toString()
                itemDescription.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        binding = ProductItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.isSelected = (selectedPosition == position)
    }

    override fun getItemCount(): Int = list.size

}
