package com.clakestudio.pc.fizykor

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(
        private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        /*
        with (modelClass) {


            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

        } as T
        */
        return super.create(modelClass)
    }

    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) = INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {

                    INSTANCE ?: ViewModelFactory(application).also { INSTANCE = it }

                }

    }
}