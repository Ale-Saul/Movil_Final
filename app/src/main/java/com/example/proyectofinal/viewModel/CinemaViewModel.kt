package com.example.proyectofinal.viewModel

import androidx.lifecycle.ViewModel
import com.example.model.Cinema
import com.example.repository.CinemaRepository

class CinemaViewModel : ViewModel() {
    private val repository = CinemaRepository()

    val cinemaList: List<Cinema> = repository.getCinemas()

    fun getCinemaById(cinemaId: Int): Cinema? {
        return repository.getCinemaById(cinemaId)
    }
}