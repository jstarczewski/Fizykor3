package com.clakestudio.pc.fizykor.flashcards

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.clakestudio.pc.fizykor.data.FlashCard
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider

class FlashCardsViewModel(context: Application, private val equationsRepository: EquationsRepository) : AndroidViewModel(context) {


    // Observables

    var title: ObservableField<String> = ObservableField()
    var equation: ObservableField<String> = ObservableField()
    private var flashcards: ArrayList<FlashCard> = arrayListOf()
    private var isDataLoaded: Boolean = false

    fun start() {

        //if (isDataLoaded) loadData()
    }

    private fun loadData() {


        // add error handling
        var disposable = equationsRepository.getAllFlashCards()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe {
                    loadFlashCards(it)
                }

    }

    private fun loadFlashCards(flashCards: List<FlashCard>) {
        this.flashcards.clear()
        this.flashcards.addAll(flashCards)
        isDataLoaded = true
        prepareFlashCard()
    }

    private fun prepareFlashCard() {
        title.set(flashcards[0].title)
        equation.set(flashcards[0].equation)
    }

    private fun testDataInjection() {
        

    }
}
