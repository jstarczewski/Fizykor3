package com.clakestudio.pc.fizykor.equations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
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

    val equations: ObservableArrayList<Equation> = ObservableArrayList()


    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
