package com.mindera.spacex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindera.spacex.data.repositories.MainRepository

class ViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompanyViewModel(mainRepository) as T
    }
}