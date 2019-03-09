package com.clakestudio.pc.fizykor.di

import androidx.lifecycle.ViewModelProvider
import com.clakestudio.pc.fizykor.viewmodel.FizykorViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: FizykorViewModelFactory): ViewModelProvider.Factory
}