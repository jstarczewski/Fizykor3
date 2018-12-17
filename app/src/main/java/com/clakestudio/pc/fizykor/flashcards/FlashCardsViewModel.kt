package com.clakestudio.pc.fizykor.flashcards

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.clakestudio.pc.fizykor.data.FlashCard
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import java.util.*
import kotlin.random.Random

class FlashCardsViewModel(private val equationsRepository: EquationsRepository) : ViewModel {


    // Observables

    var title: ObservableField<String> = ObservableField()
    var equation: ObservableField<String> = ObservableField()
    private var flashcards: ArrayList<FlashCard> = arrayListOf()
    private var isDataLoaded: Boolean = false
    private var isLastOperationPush = false

    private val indexStack = Stack<Int>()

    fun start() {
        //testDataInjection()
        if (!isDataLoaded) loadData()
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
        setNewFlashCard()
    }


    fun isNewFlashCard(boolean: Boolean) = if (boolean) setNewFlashCard() else setPreviousFlashCard()

    private fun isDoublePeek(index: Int) = isLastOperationPush && flashcards[index].title == this.title.toString() && flashcards[index].equation == this.equation.toString()

    private fun getRandomFlashCardIndex(): Int = Random.nextInt(flashcards.size)

    private fun setData(index: Int) {
        this.title.set(flashcards[index].title)
        this.equation.set(flashcards[index].equation)
    }

    private fun setNewFlashCard() {
        val index = getRandomFlashCardIndex()

        setData(index)

        // stack push bug
        indexStack.push(index)
        isLastOperationPush = true
    }

    private fun setPreviousFlashCard() {
        var index = indexStack.pop()
        if (isDoublePeek(index))
            index = indexStack.pop()
        if (index == null)
            setNewFlashCard()
        else
            setData(index)
        isLastOperationPush = false
    }


    private fun testDataInjection() {
        /*
        Prędkość w ruchu jednostajnym @ ''$v↖{ → } = { ∆s } / t[m / s]$'
        Prędkość w ruchu zmiennym @ '$v↖{→}={∆s}/t [m/s]$'
        Przyśpieszenie w ruchu zmiennym @ $a↖{ → } = { ∆v } / t[m / s^2]$'
        Droga w ruchu zmiennym @ '$s = v_0t +↙{-} {at^2}/2$ $[m/s]$'
        Droga w ruchu zmiennym z wyk . czasu i prędkości końcowej @ '$s = {v_0 + v_k}/2 t$'
        Droga w ruchu zmiennym z wyk . przyspieszenia i predkości koncowej @ '$s = {v_k^2-v_0^2}/{2a}$'
        Stosunki dróg przebytych przez ciało w ruchu jednostajnie przyspieszonym bez predkości początkowej @ '$s_1:s_2:s_3:..s_n = 1:3:5..n$'
        Równanie ruchu v(t) @ '$v↖{→}(t)=v_0+at$ $[m/s]$'
        Równanie ruchu x(t) @ '$x(t)=x_0+s$ $[m/s]$'
        Wysokość od czasu w spadku swobodnym h(t) @ '$h(t)=x_0+v_0↖{→}t+s$'
        Zasięg w rzucie poziomym @ '$Z=v↖{→}_ot$ $[m/s]$'
        Prędkość końcowa pionowej składowej prędkości @ '$v↖{→}_{ky}=gt$'
        Tangens kąta alfa w rzucie poziomym @ '$tgα={v_y}/{v_x}={gt}/v_o$'
        Prędkośc calłkowita w rzucie poziomym @ '$v↖{→}_c=√{v↖{→}_x^2+v↖{→}_y^2}$'
        Prędkosć początkowa pozioma w rzucie ukośnym @ '$v↖{→}_{0x}=v_0cosα$'
        Prędkość początkowa pionowa w rzucie ukośnym @ '$v↖{→}_{0y}=v_0sinα$'
        Czas wznoszenia w rzucie ukośnym @ '$t_{wz}={v_0sinα}/g$'
        Czas całkowity w rzucie ukośnym @ '$t_{cal}={2v_0sinα}/g$'
        Maksymalna wysokość w rzucie ukośnym @ '$h_{max}={v_0^2sin^2α}/{2g}$'
        Zasięg w rzucie ukośnym @ '$Z={v↖{→}_0^2sin2α}/g$'
        Prędkość liniowa ciała w ruchu po okregu @ '$v↖{→}= s/t= {2πr}/T = {2πfr}$'
        Przyśpieszenie dośrodkowe @ '$a_d↖{→}=v^2/r$'
        Szybkość średnia @ '$v_{sr}={v_o+v_k}/2$'
        Częstotliwość @ '$f=1/T$'
*/

        //equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu jednostajnym", "$\\{v↖{→}} = { ∆s }/t[m/s]$"))

        //equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu jednostajnym", "$\\{v↖{→}} = { ∆s }/t[m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu zmiennym", "$\\{v↖{→}}={∆s}/t [m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Przyśpieszenie w ruchu zmiennym", "$\\{a↖{ → }} = { ∆v } / t[m / s^2]$"))


    }
}
