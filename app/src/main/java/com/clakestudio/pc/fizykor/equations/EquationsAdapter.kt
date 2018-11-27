package com.clakestudio.pc.fizykor.equations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.EquationBinding

class EquationsAdapter(private var equations: ArrayList<Equation>) : RecyclerView.Adapter<EquationsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: EquationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, equation: String) {


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquationsAdapter.ViewHolder {

        val binding: EquationBinding

        val inflater = LayoutInflater.from(parent.context)

        binding = EquationBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: EquationsAdapter.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}