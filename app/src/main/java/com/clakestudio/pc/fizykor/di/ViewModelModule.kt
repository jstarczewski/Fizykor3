package com.clakestudio.pc.fizykor.di

import androidx.lifecycle.ViewModelProvider
import com.clakestudio.pc.fizykor.equations.EquationsViewModel
import com.clakestudio.pc.fizykor.viewmodel.FizykorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EquationsViewModel::class)
    abstract fun bindEuationsViewModel(equationsViewModel: EquationsViewModel): EquationsViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FizykorViewModelFactory): ViewModelProvider.Factory
}