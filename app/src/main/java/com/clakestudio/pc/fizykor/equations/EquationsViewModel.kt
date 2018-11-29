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
import io.reactivex.disposables.CompositeDisposable

class EquationsViewModel(
        context: Application,
        private val equationsRepository: EquationsRepository
) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    private val context: Context = context.applicationContext
    internal val openFlashCardsEvent = SingleLiveEvent<String>()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var equations: ObservableArrayList<Equation> = ObservableArrayList()

    fun start() {

        val disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .observeOn(AppSchedulersProvider.uiScheduler())
                .subscribe(
                        {
                            this.equations.clear()
                            this.equations.addAll(it)
                        },
                        { error -> showToast("Error retriving data occured") }
                )
    //equationsRepository.saveEquation(Equation("Kinematyka", "Prędkość", "$\\{v_0}=s/t$"))
    //equationsRepository.saveEquation(Equation("Kinematyka", "Droga", "$\\s=v_0*t$"))
    //equationsRepository.saveEquation(Equation("Kinematyka", "Czas", "$\\t=s/{v_0}$"))


    }

    private fun showToast(errorMessage: String) = Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}
