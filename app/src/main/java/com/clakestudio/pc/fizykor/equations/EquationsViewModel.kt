package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EquationsViewModel(
        private val equationsRepository: EquationsRepository
) : ViewModel() {

    private val isDataLoadingError = ObservableBoolean(false)
    internal val openFlashCardsEvent = SingleLiveEvent<String>()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var equations: ObservableArrayList<Equation> = ObservableArrayList()
    private var rawEquations: ArrayList<Equation> = ArrayList()
    private var isDataLoaded = false
    var flashCardsEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    private var currentFiltering: String = "Kinematyka"


    fun start() {
        if (!isDataLoaded)
            loadData()
    }

    private fun loadData() {
        var disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe(
                        {
                            addEquations(it)
                        },
                        {
                            // error here
                        }
                )
    }

    private fun addEquations(equations: List<Equation>) {
        rawEquations.clear()
        rawEquations.addAll(equations)
        isDataLoaded = true
        filterEquations(currentFiltering)
    }

    fun filterEquations(filtering: String) {
        this.equations.clear()
        this.equations.addAll(rawEquations.filter { equation -> equation.section == filtering })
        currentFiltering = filtering
    }

    fun openFlashCards() {
        flashCardsEvent.call()
    }


    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
