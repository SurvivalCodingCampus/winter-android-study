package com.surivalcoding.winterandroidstudy.day03.presentation.counter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.surivalcoding.winterandroidstudy.day03.AppApplication
import com.surivalcoding.winterandroidstudy.day03.data.repository.NumberRepository

// Factory
class CounterViewModel(
    private val numberRepository: NumberRepository,
) : ViewModel() {

    private val _count: MutableState<Int> = mutableIntStateOf(numberRepository.getNumber())
    val count: State<Int> = _count

    fun increase() {
        numberRepository.increaseNumber()
        _count.value = numberRepository.getNumber()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return CounterViewModel(
                    (application as AppApplication).numberRepository,
                ) as T
            }
        }
    }
}