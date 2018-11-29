package com.clakestudio.pc.fizykor.data.source.local

import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsDataSource
import io.reactivex.Flowable

class EquationsLocalDataSource(private val equationDao: EquationDao) : EquationsDataSource {


    override fun getAllEquations(): Flowable<ArrayList<Equation>> {
        return equationDao.getAllEquations()
    }

    override fun getAllEquationsFromSection(section: String): Flowable<ArrayList<Equation>> {
        return equationDao.getAllEquationsFromSection(section)
    }

    override fun saveEquation(equation: Equation) {
        equationDao.insertEquation(equation)
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