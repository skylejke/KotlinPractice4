package com.example.kotlinpractice4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GetProductsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_products, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = view.findViewById(R.id.textView)
        val toProductsButton: Button = view.findViewById(R.id.toProductsBtn)
        val getProductsButton: Button = view.findViewById(R.id.getProductsBtn)
        val productDatabase = ProductDatabase.getDataBase(requireContext())

        getProductsButton.setOnClickListener {
            getProducts(productDatabase)
            textView.text = "Receiving products..."
        }

        productDatabase.getProductDao().getAllProducts().asLiveData().observe(viewLifecycleOwner) {
            if (it.size >= 9) {
                getProductsButton.visibility = View.GONE
                textView.text = "Products has been gotten"
                toProductsButton.visibility = View.VISIBLE
            }
        }

        toProductsButton.setOnClickListener {
            findNavController().navigate(R.id.action_getProductsFragment_to_productsFragment)
        }
    }

    private fun getProducts(productDatabase: ProductDatabase) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1 until 10) {
                val product = mainApi.getProductById(i)
                productDatabase.getProductDao().insertProduct(product)
            }
        }
    }
}