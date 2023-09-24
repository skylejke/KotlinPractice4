package com.example.kotlinpractice4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsListAdapter(private val productsList: List<Product>) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productsList[position]
        holder.idTextView.text = currentItem.id.toString()
        holder.titleTextView.text = currentItem.title
        holder.priceTextView.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.id)
        val titleTextView: TextView = view.findViewById(R.id.title)
        val priceTextView: TextView = view.findViewById(R.id.price)
    }
}
