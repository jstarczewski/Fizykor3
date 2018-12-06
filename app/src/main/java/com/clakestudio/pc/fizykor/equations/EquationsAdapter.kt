package com.clakestudio.pc.fizykor.equations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.EquationBinding

class EquationsAdapter(private var equations: ArrayList<Equation>) : RecyclerView.Adapter<EquationsAdapter.ViewHolder>() {

    class ViewHolder(val binding: EquationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, equation: String) {
            binding.mvEquation.text = equation
            binding.tvTitle.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquationsAdapter.ViewHolder {

        val binding: EquationBinding
        val inflater = LayoutInflater.from(parent.context)
        binding = EquationBinding.inflate(inflater, parent,false)
        binding.mvEquation.textZoom = 70

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return equations.size
    }

    override fun onBindViewHolder(holder: EquationsAdapter.ViewHolder, position: Int) {
        holder.bind(equations[position].title, equations[position].equation)
    }


    fun replaceData(equations: ArrayList<Equation>) = setEquations(equations)

    private fun setEquations(equations: ArrayList<Equation>) {
        this.equations = equations
        notifyDataSetChanged()
    }
}