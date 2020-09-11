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

package com.shabinder.demo.fragments.product

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shabinder.demo.R
import com.shabinder.demo.databinding.ProductFragmentBinding
import com.shabinder.demo.fragments.ProductFragmentArgs

class ProductFragment : Fragment() {

    private lateinit var binding: ProductFragmentBinding
    private val args: ProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setClickHandlers()
        val productName = args.productName
        binding.semicircleLayout.backgroundTintList = ColorStateList.valueOf(args.color)
        binding.productName.text = productName
        binding.imageView.apply {
            Glide.with(this)
                .load(args.drawableId)
                .apply(RequestOptions.fitCenterTransform())
                .into(this)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.imageView, args.productName)
    }

    private fun setClickHandlers() {
        var isBookMarkActive = false
        binding.bookmarkButton.setOnClickListener {
            if (isBookMarkActive) {
                binding.bookmarkButton.setImageResource(R.drawable.ic_notbookmarked)
                binding.bookmarkButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                isBookMarkActive = false
                return@setOnClickListener
            }
            binding.bookmarkButton.setImageResource(R.drawable.ic_bookmarked)
            binding.bookmarkButton.imageTintList = ColorStateList.valueOf(Color.BLACK)
            isBookMarkActive = true
        }

        var isCartActive = false
        binding.cartButton.setOnClickListener {
            if(isCartActive){
                binding.cartButton.setImageResource(R.drawable.ic_empty_shopping_cart)
                binding.cartButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                isCartActive = false
                return@setOnClickListener
            }
            binding.cartButton.setImageResource(R.drawable.ic_shopping_cart)
            binding.cartButton.imageTintList = ColorStateList.valueOf(Color.BLACK)
            isCartActive = true
        }
    }
}