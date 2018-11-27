package com.clakestudio.pc.fizykor.equations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation

class EquationsViewModel(
        context: Application
) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    private val context: Context = context.applicationContext
    internal val openFlashCardsEvent = SingleLiveEvent<String>()

    @Bindable
    val equations: ArrayList<Equation> = ObservableArrayList()

    fun start() {



    }

    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
