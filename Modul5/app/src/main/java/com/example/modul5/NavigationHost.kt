package com.example.modul5

import androidx.fragment.app.Fragment


interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean)
}
