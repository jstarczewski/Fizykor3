package com.clakestudio.pc.fizykor.data.source.local

import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsDataSource
import io.reactivex.Flowable

class EquationsLocalDataSource(private val equationDao: EquationDao): EquationsDataSource {
    
    override fun getAllEquations(): Flowable<ArrayList<Equation>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllEquationsFromSection(): Flowable<ArrayList<Equation>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEquation(equation: Equation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}