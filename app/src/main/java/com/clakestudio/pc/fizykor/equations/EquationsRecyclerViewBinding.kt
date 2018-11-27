package com.clakestudio.pc.fizykor.equations

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.clakestudio.pc.fizykor.data.Equation

object EquationsRecyclerViewBinding {

    @BindingAdapter("app:equations")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, equations: ArrayList<Equation>) {
        with(recyclerView.adapter as EquationsAdapter) {
            replaceData(equations)
        }
    }

}