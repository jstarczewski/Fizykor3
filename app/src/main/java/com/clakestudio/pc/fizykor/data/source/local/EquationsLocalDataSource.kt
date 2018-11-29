package com.clakestudio.pc.fizykor.data.source.local

import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsDataSource
import com.clakestudio.pc.fizykor.util.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EquationsLocalDataSource(private val equationDao: EquationDao) : EquationsDataSource {


    override fun getAllEquations(): Flowable<ArrayList<Equation>> {
        return equationDao.getAllEquations()
    }

    override fun getAllEquationsFromSection(section: String): Flowable<ArrayList<Equation>> {
        return equationDao.getAllEquationsFromSection(section)
    }

    override fun saveEquation(equation: Equation) {


        // TODO: Check if this approach is correct

        val disposable = Flowable.fromCallable { equationDao.insertEquation(equation) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        val compositeDisposable: CompositeDisposable = CompositeDisposable()
        compositeDisposable.add(disposable)
        compositeDisposable.clear()
    }

    companion object {

        private var INSTANCE: EquationsLocalDataSource? = null


        fun getInstance(equationDao: EquationDao): EquationsLocalDataSource {

            if (INSTANCE == null) {
                synchronized(EquationsLocalDataSource::class.java) {
                    INSTANCE = EquationsLocalDataSource(equationDao)
                }
            }
            return INSTANCE!!
        }

    }

}