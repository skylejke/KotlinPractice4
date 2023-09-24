package com.example.kotlinpractice4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backBtn: Button = view.findViewById(R.id.backBtn)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        getProductsFromDB(recyclerView)

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getProductsFromDB(recyclerView: RecyclerView) {
        val productDatabase = ProductDatabase.getDataBase(requireContext())
        productDatabase.getProductDao().getAllProducts().asLiveData().observe(viewLifecycleOwner) {
            val productsList: List<Product> = it
            val productsListAdapter = ProductsListAdapter(productsList)
            recyclerView.adapter = productsListAdapter
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
        }
    }
}