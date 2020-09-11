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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.shabinder.demo.R
import com.shabinder.demo.databinding.ListFragmentBinding
import com.shabinder.demo.loadImage
import com.shabinder.demo.models.ProductCategories
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

class ListFragment : Fragment() {

    private lateinit var binding: ListFragmentBinding
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.list_fragment,container,false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        //RecyclerView Configuration
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(ListSpacingDecoration(50))
            itemAnimator = SlideInLeftAnimator()

            val category = args.category
            val adapter = ListItemAdapter(ProductCategories.valueOf(category).availableProducts)
            this.adapter = ScaleInAnimationAdapter(adapter).apply {
                setFirstOnly(true)
                setDuration(400)
            }

            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        binding.categoryName.text = args.category
        loadImage(ProductCategories.valueOf(args.category).productImageDrawable,binding.imageView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.imageView, args.category)
    }
}