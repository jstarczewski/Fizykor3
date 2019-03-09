package com.clakestudio.pc.fizykor.di

import com.clakestudio.pc.fizykor.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {


    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivityModuleModule() : MainActivity

}