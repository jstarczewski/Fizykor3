package com.clakestudio.pc.fizykor.equations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.clakestudio.pc.fizykor.SingleLiveEvent

class EquationsViewModel(context: Application) : AndroidViewModel(context) {
    // TODO: Implement the ViewModel

    private val isDataLoadingError = ObservableBoolean(false)
    private val context: Context = context.applicationContext
    internal val openFlashCardsEvent = SingleLiveEvent<String>()

    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
