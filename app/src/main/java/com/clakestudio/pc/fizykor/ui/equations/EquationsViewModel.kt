package com.clakestudio.pc.fizykor.ui.equations

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableArrayList
import com.clakestudio.pc.fizykor.DrawerLiveEvent
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class EquationsViewModel @Inject constructor(var equationsRepository: EquationsRepository
) : ViewModel() {

    @Inject
    lateinit var drawerLiveEvent: DrawerLiveEvent<String>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val equations: ObservableArrayList<Equation> = ObservableArrayList()
    private val rawEquations: ArrayList<Equation> = ArrayList()
    private var isDataLoaded = false
    private var currentFiltering: String = "Kinematyka"
    private val errorMessage: String = "Wystąpił problem z załadowaniem danych "


    fun start() {
        if (!isDataLoaded)
            loadData()

        drawerLiveEvent.observe(androidx.lifecycle.Observer<String> {
            filterEquations(it)
        })
    }

    private fun loadData() {
        val disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe({ addEquations(it) },
                        { addEquations(listOf(Equation(currentFiltering, errorMessage + it.localizedMessage, ""))) })
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


}
