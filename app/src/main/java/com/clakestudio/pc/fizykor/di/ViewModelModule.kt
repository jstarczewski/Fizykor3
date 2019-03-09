package com.clakestudio.pc.fizykor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clakestudio.pc.fizykor.ui.flashcards.FlashCardsViewModel
import com.clakestudio.pc.fizykor.ui.equations.EquationsViewModel
import com.clakestudio.pc.fizykor.viewmodel.FizykorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EquationsViewModel::class)
    abstract fun bindEuationsViewModel(equationsViewModel: EquationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlashCardsViewModel::class)
    abstract fun bindFlashCardsViewModel(flashCardsViewModel: FlashCardsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: FizykorViewModelFactory): ViewModelProvider.Factory
}