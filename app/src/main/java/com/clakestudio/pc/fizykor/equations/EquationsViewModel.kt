package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

class EquationsViewModel(
        private val equationsRepository: EquationsRepository
) : ViewModel() {

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
        val disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe({ addEquations(it) },
                        { addEquations(listOf(Equation("Kinematyka", "Wystąpił problem z załadowaniem danych" + it.localizedMessage, ""))) })
        compositeDisposable.add(disposable)
    }

    private fun addEquations(equations: List<Equation>) {
        rawEquations.clear()
        rawEquations.addAll(equations)
        isDataLoaded = true
        filterEquations(currentFiltering)
        compositeDisposable.clear()
    }

    fun filterEquations(filtering: String) {
        this.equations.clear()
        this.equations.addAll(rawEquations.filter { equation -> equation.section == filtering })
        currentFiltering = filtering

    }

    fun openFlashCards() {
        flashCardsEvent.call()
    }


}
