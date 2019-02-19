package com.clakestudio.pc.fizykor.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesProvider {

    fun write(context: Context, predicate: (SharedPreferences.Editor) -> (SharedPreferences.Editor))  {

        context.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()

    }

    fun


}