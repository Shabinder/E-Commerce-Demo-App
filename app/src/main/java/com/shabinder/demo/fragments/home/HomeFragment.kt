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

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.shabinder.demo.MainActivity
import com.shabinder.demo.R
import com.shabinder.demo.databinding.HomeFragmentBinding
import com.shabinder.demo.loadBanner
import com.shabinder.demo.loadImage
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.horizontalCircularList.apply {

            itemAnimator = SlideInLeftAnimator()

            this.adapter = AlphaInAnimationAdapter(HomeListAdapter()).apply {
                setFirstOnly(true)
                setDuration(500)
            }
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        loadBanners()

        loadPreviousItems()//If Functionality Needed, will use Room and Server Side Storage[ex-Firebase] with Recycler View

        binding.menuButton.setOnClickListener {
            (requireActivity() as MainActivity).openDrawer()
        }
        return binding.root
    }




    private fun loadBanners() {
        //Banner1 Loading
        loadBanner(R.drawable.banner3, binding.banner1)
        //Banner2 Loading
        loadBanner(R.drawable.banner2, binding.banner2)
    }

    private fun loadPreviousItems() {
        binding.previousOrder1.let{
            it.itemPrice.text = "99"
            it.itemName.text = "Tide"
            it.itemQuantity.text = "1 Kg"
            loadImage(R.drawable.tide, it.itemImage)
            ViewCompat.setTransitionName(it.itemImage , "Tide")
            it.root.setOnClickListener{ view ->
                val extras = FragmentNavigatorExtras(it.itemImage to "Tide")
                view.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(R.drawable.tide,"Tide",Color.rgb(81, 109, 255)),extras)
            }
        }

        binding.previousOrder2.let{
            it.itemPrice.text = "199"
            it.itemName.text = "Fortune Oil"
            it.itemQuantity.text = "1 L"
            loadImage(R.drawable.fortune_oil, it.itemImage)
            ViewCompat.setTransitionName(it.itemImage , "Fortune Oil")
            it.root.setOnClickListener{ view ->
                val extras = FragmentNavigatorExtras(it.itemImage to "Fortune Oil")
                view.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(R.drawable.fortune_oil,"Fortune Oil",Color.rgb(255, 182, 193)),extras)
            }
        }
    }
}