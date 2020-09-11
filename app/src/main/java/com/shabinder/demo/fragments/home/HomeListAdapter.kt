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

package com.shabinder.demo.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shabinder.demo.R
import com.shabinder.demo.models.ProductCategories

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private var data : Array<ProductCategories>  = ProductCategories.values()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.circular_item, parent, false) as ViewGroup
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Glide.with(holder.itemImage.context)
            .asDrawable()
            .circleCrop()
            .load(item.productImageDrawable)
            .into(holder.itemImage)
        holder.itemName.text = item.name
        ViewCompat.setTransitionName(holder.itemImage, item.name)
        holder.itemView.setOnClickListener {
            val extras = FragmentNavigatorExtras(holder.itemImage to item.name)
            val action = HomeFragmentDirections.actionMainFragmentToListFragment(item.name)
            it.findNavController().navigate(action,extras)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.circular_image)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
    }
}