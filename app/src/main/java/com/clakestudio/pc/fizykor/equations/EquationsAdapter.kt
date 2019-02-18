package com.clakestudio.pc.fizykor.equations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.MultiEquationBinding
import com.clakestudio.pc.fizykor.util.MultiEquation

class EquationsAdapter(private var equations: ArrayList<MultiEquation>) : RecyclerView.Adapter<EquationsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MultiEquationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(equationsList: List<Equation>) {
            binding.tvTitle1.text = equationsList[0].title
            binding.mvEquation1.text = equationsList[0].equation
            binding.tvTitle2.text = equationsList[1].title
            binding.mvEquation2.text = equationsList[1].equation
            binding.tvTitle3.text = equationsList[2].title
            binding.mvEquation3.text = equationsList[2].equation
            binding.tvTitle4.text = equationsList[3].title
            binding.mvEquation4.text = equationsList[3].equation
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
        holder.bind(equations[position])
    }


    fun replaceData(equations: ArrayList<MultiEquation>) = setEquations(equations)

    private fun setEquations(equations: ArrayList<MultiEquation>) {

        this.equations = equations
        notifyDataSetChanged()


    }
}