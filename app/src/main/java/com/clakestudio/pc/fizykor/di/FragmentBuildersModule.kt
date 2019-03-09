package com.clakestudio.pc.fizykor.di

import com.clakestudio.pc.fizykor.equations.EquationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeEquationsFragment() : EquationsFragment

}