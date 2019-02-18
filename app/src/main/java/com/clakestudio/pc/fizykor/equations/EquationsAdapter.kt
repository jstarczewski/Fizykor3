package com.clakestudio.pc.fizykor.equations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.MultiEquationBinding
import com.clakestudio.pc.fizykor.util.MultiEquation

class EquationsAdapter(private var equations: ArrayList<MultiEquation>) : RecyclerView.Adapter<EquationsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MultiEquationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(equation1: Equation, equation2: Equation, equation3: Equation, equation4: Equation) {
            binding.tvTitle1.text = equation1.title
            binding.mvEquation1.text = equation1.equation
            binding.tvTitle2.text = equation2.title
            binding.mvEquation2.text = equation2.equation
            binding.tvTitle3.text = equation3.title
            binding.mvEquation3.text = equation3.equation
            binding.tvTitle4.text = equation4.title
            binding.mvEquation4.text = equation4.equation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquationsAdapter.ViewHolder {

        val binding: MultiEquationBinding
        val inflater = LayoutInflater.from(parent.context)
        binding = MultiEquationBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return equations.size
    }

    override fun onBindViewHolder(holder: EquationsAdapter.ViewHolder, position: Int) {
        holder.bind(equations[position].equation1, equations[position].equation2, equations[position].equation3, equations[position].equation4)
    }


    fun replaceData(equations: ArrayList<Equation>) = setEquations(equations)

    private fun setEquations(equations: ArrayList<Equation>) {
        var s = equations.size % 4
        for (x in 0..s)
            equations.add(Equation(equations[x].section, "", ""))


        var multiEquations = arrayListOf<MultiEquation>()
        for (x in 3..equations.size step 4)
            multiEquations.add(MultiEquation(equations[x - 3], equations[x - 2], equations[x - 1], equations[x]))


        this.equations = multiEquations
        notifyDataSetChanged()
    }
}