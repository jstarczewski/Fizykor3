package com.clakestudio.pc.fizykor.equations

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.clakestudio.pc.fizykor.data.Equation

object EquationsRecyclerViewBinding {

    var a = 1

    @JvmStatic
    @BindingAdapter("app:equations")
    fun setEquations(recyclerView: RecyclerView, equations: ArrayList<Equation>) {
        Log.e("eqt adapter ->", equations.toString())
        with(recyclerView.adapter as EquationsAdapter) {

            if (!equations.isEmpty()) {
                val smallerLists: List<List<Equation>> = equations.chunked(4)
                while (smallerLists[smallerLists.size - 1].size < 4)
                    (smallerLists[smallerLists.size - 1] as ArrayList<Equation>).add(Equation("", "", ""))
                replaceData(smallerLists as ArrayList<List<Equation>>)
            }

/*
            var multiEquations = arrayListOf<MultiEquation>()
            var loops = equations.size / 4
            var extra = equations.size % 4
            var start = 0
            if (!equations.isEmpty()) {
                for (x in 0..loops) {
                    multiEquations.add(MultiEquation(equations[start * x], equations[start * x + 1], equations[start * x + 2], equations[start * x + 3]))
                }
                replaceData(multiEquations)
            }


            var multiListOfSmallerLists = equations.chunked(4)
            for (smallList : List<Equation> in multiListOfSmallerLists) {
                multiEquations.add()
            }

*/

/*
            if (a < 3) {
                var s = equations.size % 4
                for (x in 0..s)
                    equations.add(Equation("", "", ""))


                var multiEquations = arrayListOf<MultiEquation>()
                for (x in 3..(equations.size - 1) step 4)
                    multiEquations.add(MultiEquation(equations[x - 3], equations[x - 2], equations[x - 1], equations[x]))

                Log.e("Equations", multiEquations.toString())

                replaceData(multiEquations)
                a++
            }
*/
        }
    }
}