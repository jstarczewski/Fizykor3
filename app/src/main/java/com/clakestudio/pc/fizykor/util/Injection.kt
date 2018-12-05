package com.clakestudio.pc.fizykor.util

import android.content.Context
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.data.source.local.EquationDatabase
import com.clakestudio.pc.fizykor.data.source.local.equation.EquationsLocalDataSource

object Injection {

    fun provideEquationsEepository(context: Context) : EquationsRepository = EquationsRepository.
            getInstance(EquationsLocalDataSource.
                    getInstance(EquationDatabase.
                            getInstance(context).
                            equationDao()))


}