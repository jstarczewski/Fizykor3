package com.clakestudio.pc.fizykor.di

import android.app.Application
import androidx.room.Room
import com.clakestudio.pc.fizykor.FizykorApp
import com.clakestudio.pc.fizykor.data.source.local.EquationDatabase
import com.clakestudio.pc.fizykor.data.source.local.equation.EquationDao
import com.clakestudio.pc.fizykor.data.source.local.flashcard.FlashCardDao
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


}