package com.clakestudio.pc.fizykor.equations

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.clakestudio.pc.fizykor.data.Equation

object EquationsRecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("app:equations")
    fun setEquations(recyclerView: RecyclerView, equations: ArrayList<Equation>) {
        Log.e("eqt adapter ->", equations.toString())
        with(recyclerView.adapter as EquationsAdapter) {
            replaceData(equations)
        }
    }

}