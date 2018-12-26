package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.FlashCard
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.random.Random

class FlashCardsViewModel(private val equationsRepository: EquationsRepository) : ViewModel() {


    // Observables

    var title: ObservableField<String> = ObservableField()
    var equation: ObservableField<String> = ObservableField()

    var flashCardVisibilityEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    var animateCardViewEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    private val invisible = 0x00000004
    private val visible = 0x00000000

    private val minDistance: Double = 200.0

    private var flashCards: ArrayList<FlashCard> = ArrayList()
    private var rawFlashCards: ArrayList<FlashCard> = ArrayList()
    private var isDataLoaded: Boolean = false
    private var isLastOperationPush = false
    private var isMaturaMode = false

    var startupFiltering = "Kinematyka"

    private val flashCardsBackStack = Stack<FlashCard>()

    fun start() {
        // testDataInjection()

//        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Droga w ruchu zmiennym z wyk . czasu i prędkości końcowej", "$\\{s} = {v_0 + v_k}/2 t$"))
        if (!isDataLoaded) loadData()
    }

    fun filterFlashCards(filtering: String) {
        flashCards.clear()
        flashCards.addAll(rawFlashCards.filter { flashCard -> flashCard.section == filtering })

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
        this.rawFlashCards.clear()
        this.rawFlashCards.addAll(flashCards)
        isDataLoaded = true
        filterFlashCards(startupFiltering)
        setNewFlashCard()
    }

    fun switchMathViewVisibility(visibility: Int) = if (visibility == visible) flashCardVisibilityEvent.value = invisible else flashCardVisibilityEvent.value = visible

    private fun getRandomFlashCardIndex(): Int = if (isMaturaMode) Random.nextInt(flashCards.filter { it.isMatura }.size) else Random.nextInt(flashCards.size)

    private fun setData(index: Int) {
        if (isMaturaMode) {
            this.title.set(flashCards.filter { it.isMatura }[index].title)
            this.equation.set(flashCards.filter { it.isMatura }[index].equation)
        } else {
            this.title.set(flashCards[index].title)
            this.equation.set(flashCards[index].equation)
        }
    }

    private fun setData(flashCard: FlashCard) {
        this.title.set(flashCard.title)
        this.equation.set(flashCard.equation)
    }

    private fun setNewFlashCard() {
        val index = getRandomFlashCardIndex()
        setData(index)
        addToFlashCardsBackStack(index)
        isLastOperationPush = true
    }

    private fun addToFlashCardsBackStack(index: Int) = if (!isMaturaMode) flashCardsBackStack.push(flashCards[index]) else flashCardsBackStack.push(flashCards.filter { it.isMatura }[index])

    private fun setPreviousFlashCard() {
        if (!flashCardsBackStack.isEmpty()) {
            var previousFlashCard = flashCardsBackStack.pop()
            if (isLastOperationPush && !flashCardsBackStack.isEmpty())
                previousFlashCard = flashCardsBackStack.pop()
            setData(previousFlashCard)
            isLastOperationPush = false
        } else setNewFlashCard()
    }

    fun setMaturaMode(checked: Boolean) {
        isMaturaMode = checked
    }

    fun determineAnimation(x1: Float, x2: Float, animationNewFlashCards: Int, animationOldPreviousFlashCard: Int) {

        // Switching MathView visibility to invisible for better user experience while animating

        /**
         * Basic fling logic that is simple and also does not need some special testing
         *
         *  |---------------|
         *  |-x1<-delta->x2-|
         *  |---------------|
         *  |-----SCREEN----|
         *  |---------------|
         *  |---------------|
         * */
        val delta = x2 - x1
        if (x2 > x1 && delta > minDistance) {
            setNewFlashCard()
            animateCardViewEvent.value = animationNewFlashCards
            switchMathViewVisibility(visible)
        } else if (x1 > x2 && abs(delta) > minDistance) {
            setPreviousFlashCard()
            animateCardViewEvent.value = animationOldPreviousFlashCard
            switchMathViewVisibility(visible)
        }
    }

    private fun testDataInjection() {

        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu jednostajnym", "$\\{v↖{→}} = { ∆s }/t[m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu zmiennym", "$\\{v↖{→}}={∆s}/t [m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Przyśpieszenie w ruchu zmiennym", "$\\{a↖{ → }} = { ∆v } / t[m / s^2]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Droga w ruchu zmiennym", "$\\{s = v_0t +↙{-} {at^2}/2$ $[m/s]}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Droga w ruchu zmiennym z wyk . czasu i prędkości końcowej", "$\\{s} = {v_0 + v_k}/2 t$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Droga w ruchu zmiennym z wyk . przyspieszenia i predkości koncowej", "$\\{s = {v_k^2-v_0^2}/{2a}}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Stosunki dróg przebytych przez ciało w ruchu jednostajnie przyspieszonym bez predkości początkowej", "$\\{s_1:s_2:s_3:..s_n = 1:3:5..n}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Równanie ruchu v(t)", "$\\{v↖{→}(t)}=v_0+at$ $[m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Równanie ruchu x(t)", "$\\{x(t)}=x_0+s$ $[m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Wysokość od czasu w spadku swobodnym h(t)", "$\\{h(t)}=x_0+v_0↖{→}t+s$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Zasięg w rzucie poziomym", "$\\{Z=v↖{→}}_ot$ $[m/s]$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Prędkość końcowa pionowej składowej prędkości", "$\\{v↖{→}}_{ky}=gt$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Tangens kąta alfa w rzucie poziomym", "$\\{tgα={v_y}}/{v_x}={gt}/v_o$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Prędkośc calłkowita w rzucie poziomym", "$\\{v↖{→}}_c=√{v↖{→}_x^2+v↖{→}_y^2}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Prędkosć początkowa pozioma w rzucie ukośnym", "$\\{v↖{→}}_{0x}=v_0cosα$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Prędkość początkowa pionowa w rzucie ukośnym", "$\\{v↖{→}}_{0y}=v_0sinα$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Czas wznoszenia w rzucie ukośnym", "$\\{t_{wz}=}{v_0sinα}/g$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Czas całkowity w rzucie ukośnym", "$\\{t_{cal}}={2v_0sinα}/g$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Maksymalna wysokość w rzucie ukośnym", "$\\{h_{max}}={v_0^2sin^2α}/{2g}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Zasięg w rzucie ukośnym", "$\\{Z={v↖{→}}_0^2sin2α}/g$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Prędkość liniowa ciała w ruchu po okregu", "$\\{v↖{→}}= s/t= {2πr}/T = {2πfr}$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Przyśpieszenie dośrodkowe", "$\\{a_d↖{→}}=v^2/r$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Szybkość średnia", "$\\{v_{sr}}={v_o+v_k}/2$"))
        equationsRepository.saveFlashCard(FlashCard("Kinematyka", true, "Częstotliwość", "$\\{f=1/T}$"))

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

        //  equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu jednostajnym", "$\\{v↖{→}} = { ∆s }/t[m/s]$"))
        // equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Prędkość w ruchu zmiennym", "$\\{v↖{→}}={∆s}/t [m/s]$"))
        //equationsRepository.saveFlashCard(FlashCard("Kinematyka", false, "Przyśpieszenie w ruchu zmiennym", "$\\{a↖{ → }} = { ∆v } / t[m / s^2]$"))


    }
}
