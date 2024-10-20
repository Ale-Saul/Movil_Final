package com.example.proyectofinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    val star : LiveData<Int>
        get() = _star
    private val _star = MutableLiveData<Int>()

    val icon_favorite : LiveData<Boolean>
        get() = _icon_favorite
    private val _icon_favorite = MutableLiveData<Boolean>()

    fun update(newRating: Int) {
        _star.value = newRating
    }

    fun add_favorite(iconSelected: Boolean){
        _icon_favorite.value = !iconSelected
    }
}