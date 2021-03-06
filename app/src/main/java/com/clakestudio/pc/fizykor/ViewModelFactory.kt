package com.clakestudio.pc.fizykor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.ui.equations.EquationsViewModel
import com.clakestudio.pc.fizykor.ui.flashcards.FlashCardsViewModel

class ViewModelFactory private constructor(
        private val equationsRepository: EquationsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(EquationsViewModel::class.java) ->
                        EquationsViewModel(equationsRepository)
                    isAssignableFrom(FlashCardsViewModel::class.java) ->
                        FlashCardsViewModel(equationsRepository)

                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
                // Unchecked cast
            } as T

/*
    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) = INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {

                    INSTANCE
                            ?: ViewModelFactory(Injection.provideEquationsRepository(application.applicationContext)).also { INSTANCE = it }

                }

    }*/
}