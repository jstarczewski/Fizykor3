package com.clakestudio.pc.fizykor.data.source.local.flashcard

import com.clakestudio.pc.fizykor.data.FlashCard
import com.clakestudio.pc.fizykor.data.source.FlashCardsDataSource
import io.reactivex.Flowable

class FlashCardsLocalDataSource(private val flashCardDao: FlashCardDao) : FlashCardsDataSource {

    override fun getAllFlashCards(): Flowable<List<FlashCard>> = flashCardDao.getAllFlashCards()

    override fun saveFlashCard(flashCard: FlashCard) = flashCardDao.saveFlashCard(flashCard)
}