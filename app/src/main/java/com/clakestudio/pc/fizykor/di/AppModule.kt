package com.clakestudio.pc.fizykor.di

import android.app.Application
import com.clakestudio.pc.fizykor.data.source.EquationsDataSource
import com.clakestudio.pc.fizykor.data.source.FlashCardsDataSource
import com.clakestudio.pc.fizykor.data.source.local.EquationDatabase
import com.clakestudio.pc.fizykor.data.source.local.equation.EquationDao
import com.clakestudio.pc.fizykor.data.source.local.equation.EquationsLocalDataSource
import com.clakestudio.pc.fizykor.data.source.local.flashcard.FlashCardDao
import com.clakestudio.pc.fizykor.data.source.local.flashcard.FlashCardsLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideDb(app: Application): EquationDatabase {
        return EquationDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideEquationDao(db: EquationDatabase): EquationDao {
        return db.equationDao()
    }

    @Singleton
    @Provides
    fun provideFlashCardDao(db: EquationDatabase): FlashCardDao {
        return db.flashCardDao()
    }

    @Singleton
    @Provides
    fun provideEquationsDataSource(equationDao: EquationDao) : EquationsDataSource {
        return EquationsLocalDataSource(equationDao)
    }

    @Singleton
    @Provides
    fun provideFlashCardsDataSource(flashCardDao: FlashCardDao) : FlashCardsDataSource {
        return FlashCardsLocalDataSource(flashCardDao)
    }

}