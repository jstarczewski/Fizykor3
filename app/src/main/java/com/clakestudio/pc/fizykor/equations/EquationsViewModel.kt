package com.clakestudio.pc.fizykor.equations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.util.Log
import android.widget.Toast
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class EquationsViewModel(
        context: Application,
        private val equationsRepository: EquationsRepository
) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    private val context: Context = context.applicationContext
    internal val openFlashCardsEvent = SingleLiveEvent<String>()
    private var wasDataLoaded: Boolean = false


    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var equations: ObservableArrayList<Equation> = ObservableArrayList()
    var flashCardsEvent: SingleLiveEvent<Void> = SingleLiveEvent()


    fun start() {
        loadData()
    }

    private fun loadData() {
        var disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe(
                        {
                            addEquations(it)
                        },
                        { showToast("Error retriving data occured") }
                )
        compositeDisposable.add(disposable)
        //equationsRepository.saveEquation(Equation("Kinematyka", "Prędkość", "$\\{v_0}=s/t$"))
        //equationsRepository.saveEquation(Equation("Kinematyka", "Droga", "$\\s=v_0*t$"))
        //equationsRe
    }

    private fun addEquations(equations: List<Equation>) {
        this.equations.clear()
        this.equations.addAll(equations)
    }

    fun openFlashCards() {
        flashCardsEvent.call()
    }



    private fun showToast(errorMessage: String) = Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
