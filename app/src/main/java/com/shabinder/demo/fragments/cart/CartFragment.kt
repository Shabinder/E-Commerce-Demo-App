/*
 *
 *  * Copyright (C)  2020  Shabinder Singh
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.shabinder.demo.fragments.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.shabinder.demo.R
import com.shabinder.demo.SharedViewModel
import com.shabinder.demo.databinding.CartFragmentBinding
import com.shabinder.demo.databinding.CartItemBinding
import com.shabinder.demo.loadImage
import com.shabinder.demo.models.ProductCategories

class CartFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding : CartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.cart_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        //Just For Demo Process
        viewModel.productQuantity1.observe(viewLifecycleOwner, {
            binding.cartItem1.quantityText.text = it.toString()
        })
        viewModel.productQuantity2.observe(viewLifecycleOwner, {
            binding.cartItem2.quantityText.text = it.toString()
        })

        binding.checkoutButton.setOnClickListener {
            it.findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
        }

        loadCartItem(binding.cartItem1,ProductCategories.Oil.availableProducts.entries.toList()[0],viewModel.productQuantity1)
        loadCartItem(binding.cartItem2,ProductCategories.Cookies.availableProducts.entries.toList()[0],viewModel.productQuantity2)
        return binding.root
    }
    private fun loadCartItem(it: CartItemBinding,item:Map.Entry<String,Int>,productQuantity:MutableLiveData<Int>){
        it.productName.text = item.key
        it.productPrice.text = (49..199).random().toString()
        loadImage(item.value,it.productImage)
        it.minusButton.setOnClickListener {
            if (productQuantity.value!! > 0) {
                productQuantity.value = productQuantity.value!! - 1
            }
        }
        it.plusButton.setOnClickListener {
            productQuantity.value = productQuantity.value!! + 1
        }
    }
}