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

package com.shabinder.demo.fragments.listCategory

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shabinder.demo.R
import kotlinx.android.synthetic.main.card_items.view.*


class ListItemAdapter(private val data: Map<String, Int>) : RecyclerView.Adapter<ListItemAdapter.ViewHolder>(){
    override fun getItemCount(): Int  = data.size

    private val colorsList = listOf(
        Color.rgb(207, 182, 255),
        Color.rgb(81, 109, 255),
        Color.rgb(255, 182, 193),
        Color.rgb(182, 193, 255)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.card_items, parent, false) as ViewGroup
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.entries.toList()[position]
        Glide.with(holder.itemImage.context)
            .asDrawable()
            .load(item.value)
            .into(holder.itemImage)
        holder.itemName.text = item.key
        holder.itemPrice.text = (49..199).random().toString()
        val color = colorsList.random()
        holder.itemView.card_item.setCardBackgroundColor(color)

        ViewCompat.setTransitionName(holder.itemImage, item.key)

        holder.itemView.setOnClickListener {
            val extras = FragmentNavigatorExtras(holder.itemImage to item.key)
            val action = ListFragmentDirections.actionListFragmentToProductFragment(
                item.value,
                item.key,
                color
            )

            it.findNavController().navigate(action, extras)
        }


        var isBookMarkActive = false
        holder.bookmarkButton.setOnClickListener {
            if (isBookMarkActive) {
             holder.bookmarkButton.setImageResource(R.drawable.ic_notbookmarked)
                isBookMarkActive = false
                return@setOnClickListener
            }
            holder.bookmarkButton.setImageResource(R.drawable.ic_bookmarked)
            isBookMarkActive = true
        }

        var isCartActive = false
        holder.cartButton.setOnClickListener {
            if(isCartActive){
                holder.cartButton.setImageResource(R.drawable.ic_empty_shopping_cart)
                isCartActive = false
                return@setOnClickListener
            }
            holder.cartButton.setImageResource(R.drawable.ic_shopping_cart)
            isCartActive = true
        }

        if(position %2 != 0){
            val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
            params.bottomMargin = 150
            holder.itemView.layoutParams = params
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val cartButton: ImageButton = itemView.findViewById(R.id.cart_button)
        val bookmarkButton: ImageButton = itemView.findViewById(R.id.bookmark_button)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
    }
}