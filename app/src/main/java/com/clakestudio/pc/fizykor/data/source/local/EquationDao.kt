package com.clakestudio.pc.fizykor.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.clakestudio.pc.fizykor.data.Equation
import io.reactivex.Flowable

@Dao interface EquationDao {

    /**
     * Books Dao if a place for all the querries needed
     * */

    @Query("SELECT * FROM equations")
    fun getAllEquations() : Flowable<List<Equation>>

    @Query("SELECT * FROM equations WHERE section = :section")
    fun getAllEquationsFromSection(section: String) : Flowable<List<Equation>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertEquation(equation: Equation)




}