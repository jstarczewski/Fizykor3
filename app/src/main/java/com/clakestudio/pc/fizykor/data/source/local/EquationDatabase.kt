package com.clakestudio.pc.fizykor.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.local.equation.EquationDao


@Database(entities = arrayOf(Equation::class), version = 1)
abstract class EquationDatabase : RoomDatabase() {


    abstract fun equationDao(): EquationDao

    companion object {

        private var INSTANCE: EquationDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): EquationDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, EquationDatabase::class.java, "equations.db").build()
                }
                return INSTANCE!!
            }

        }
    }


}
