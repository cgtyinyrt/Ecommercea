package com.cagatayinyurt.ecommercea.util

import android.view.View
import androidx.fragment.app.Fragment
import com.cagatayinyurt.ecommercea.R
import com.cagatayinyurt.ecommercea.view.activity.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNav() {
    val bottomNavigationView = (activity as ShoppingActivity)
        .findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView.visibility = View.GONE
}

fun Fragment.showBottomNav() {
    val bottomNavigationView = (activity as ShoppingActivity)
        .findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView.visibility = View.VISIBLE
}