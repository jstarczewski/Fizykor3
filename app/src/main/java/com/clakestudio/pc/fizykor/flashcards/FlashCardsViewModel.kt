package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.FlashCard
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.random.Random

class FlashCardsViewModel(private val equationsRepository: EquationsRepository) : ViewModel() {

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
        if (!isDataLoaded) loadData()
    }

    fun filterFlashCards(filtering: String) {

        flashCards.clear()
        flashCards.addAll(rawFlashCards.filter { flashCard -> flashCard.section == filtering })

    }

    private fun loadData() {

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

}
