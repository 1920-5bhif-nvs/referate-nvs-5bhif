package at.htl.demoapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DemoViewModel(application: Application) : AndroidViewModel(application) {

    // TODO viewModelJob
    // TODO viewModelScope
    // TODO database
    // TODO repository

    // TODO init

    // TODO persons

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DemoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}