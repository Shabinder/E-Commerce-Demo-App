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

package com.shabinder.demo.models

import com.shabinder.demo.R

enum class ProductCategories (val productImageDrawable:Int,val availableProducts:Map<String,Int>){

    Shampoo(R.drawable.circular_shampoo, mapOf(
        "Dove" to R.drawable.shampoo_dove,
        "Pantene" to R.drawable.shampoo_pantene,
        "Garnier" to R.drawable.shampoo_garnier,
        "Himalaya" to R.drawable.shampoo_himalaya,
        "Dove Men" to R.drawable.shampoo_dovemen,
        "John Frieza" to R.drawable.shampoo_johnfrieza,
        "Loreal" to R.drawable.shampoo_loreal,
        "Tresseme" to R.drawable.shampoo_tresseme,
        "Vatika" to R.drawable.shampoo_vatika,
    )),

    Oil(R.drawable.circular_oil,
        mapOf(
            "Fortune Oil" to R.drawable.oil_fortune,
            "Bajaj Oil" to R.drawable.oil_bajaj,
            "Sunflower Oil" to R.drawable.oil_sunflower,
            "Parachute Oil" to R.drawable.oil_parachute,
            "Dabur Oil" to R.drawable.oil_dabur,
            "Dalda Oil" to R.drawable.oil_dalda
        )),
    Cookies(R.drawable.circular_cookies,
        mapOf(
            "Fresho Cookies" to R.drawable.cookie_fresho,
            "Bajaj Krack-Jack" to R.drawable.cookie_krackjack,
            "Milk-Bikis" to R.drawable.cookie_milkbikis,
            "Nairn's Cookies" to R.drawable.cookie_nairn,
            "Parle-G" to R.drawable.cookie_parleg,
            "Zing Cookies" to R.drawable.cookie_zing
        )),
    Spices(R.drawable.circular_spices,
        mapOf(
            "Chana Masala" to R.drawable.spice_chanamasala,
            "Chat Masala" to R.drawable.spice_chatmasala,
            "Tikhalal Mirchi" to R.drawable.spice_tikhalal,
            "Chicken Masala" to R.drawable.spice_chickenmasala,
            "Meat Masala" to R.drawable.spice_meatmasala,
            "White Pepper" to R.drawable.spice_whitepepper
        ))

}