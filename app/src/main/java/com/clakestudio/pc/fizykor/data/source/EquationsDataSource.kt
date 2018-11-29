package com.clakestudio.pc.fizykor.data.source

import com.clakestudio.pc.fizykor.data.Equation
import io.reactivex.Flowable

interface EquationsDataSource {

    fun getAllEquations(): Flowable<ArrayList<Equation>>

    fun getAllEquationsFromSection(section: String): Flowable<ArrayList<Equation>>

    fun saveEquation(equation: Equation)

}
