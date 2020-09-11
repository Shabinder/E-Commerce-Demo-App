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

package com.shabinder.demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.shabinder.demo.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        val navController = this.findNavController(R.id.myNavHostFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeBottom -> {
                    // Respond to navigation item 1 click
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.cartBottom -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }
                R.id.wishList -> {
                    showToast("Not Implemented,wasn't asked in Given Task")
                    // Respond to navigation item 2 click
                    true
                }
                R.id.userProfile -> {
                    showToast("Not Implemented,wasn't asked in Given Task")
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    fun goBack(v: View){
        this.onBackPressed()
    }

    fun openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun closeDrawer(v: View){
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
    /*
    * Util Fun
    * */
    private fun showToast(string: String) = Toast.makeText(
        this@MainActivity,
        string,
        Toast.LENGTH_SHORT
    ).show()
    fun notImplemented(view: View) = Toast.makeText(
        view.context,
        "Not Implemented,wasn't asked in Given Task",
        Toast.LENGTH_SHORT
    ).show()
}