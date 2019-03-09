package com.clakestudio.pc.fizykor.di

import com.clakestudio.pc.fizykor.equations.EquationsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EquationsActivityModule {


    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeEquationsModuleModule() : EquationsActivity

}