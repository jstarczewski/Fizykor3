package com.clakestudio.pc.fizykor.equations

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.MultiEquationBinding
import com.jstarczewski.pc.mathview.src.MathView

class EquationsAdapter(private var equations: ArrayList<List<Equation>>) : RecyclerView.Adapter<EquationsAdapter.ViewHolder>() {


    class ViewHolder(private val binding: MultiEquationBinding) : RecyclerView.ViewHolder(binding.root) {

        val updateTextView = { view: TextView, text: String -> view.text = text }
        val updateMathView = { view: MathView, text: String -> view.text = text }
        val determineVisibility = { view: CardView, text: String ->
            if (text.isEmpty()) view.visibility == View.GONE else view.visibility == View.VISIBLE
        }
        private val textViews = arrayListOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)
        private val mathViews = arrayListOf(binding.mvEquation1, binding.mvEquation2, binding.mvEquation3, binding.mvEquation4)
        private val cardViews = arrayListOf(binding.cvEquation1, binding.cvEquation2, binding.cvEquation3, binding.cvEquation4)

        fun bind(equationsList: List<Equation>) {

            for (x in 0..3) {
                determineVisibility(cardViews[x], equationsList[x].equation)
                updateTextView(textViews[x], equationsList[x].title)
                updateMathView(mathViews[x], equationsList[x].equation)
            }

            /*

            if (equationsList[0].title == "") {
                binding.cvEquation1.visibility = View.GONE
            } else {
                binding.cvEquation1.visibility = View.VISIBLE
            }
            if (equationsList[1].title == "") {
                binding.cvEquation2.visibility = View.GONE
            } else {
                binding.cvEquation2.visibility = View.VISIBLE
            }
            if (equationsList[2].title == "") {
                binding.cvEquation3.visibility = View.GONE
            } else {
                binding.cvEquation3.visibility = View.VISIBLE
            }
            if (equationsList[3].title == "") {
                binding.cvEquation4.visibility = View.GONE
            } else {
                binding.cvEquation4.visibility = View.VISIBLE
            }
            binding.tvTitle1.text = equationsList[0].title
            binding.mvEquation1.text = equationsList[0].equation
            binding.tvTitle2.text = equationsList[1].title
            binding.mvEquation2.text = equationsList[1].equation
            binding.tvTitle3.text = equationsList[2].title
            binding.mvEquation3.text = equationsList[2].equation
            binding.tvTitle4.text = equationsList[3].title
            binding.mvEquation4.text = equationsList[3].equation
            */
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


    fun replaceData(equations: ArrayList<List<Equation>>) = setEquations(equations)

    private fun setEquations(equations: ArrayList<List<Equation>>) {

        this.equations = equations
        notifyDataSetChanged()


    }

    private fun updateTextView(view: TextView, text: String) = { view.text = text }
    private fun updateMathView(view: MathView, text: String) = { view.text = text }
    private fun determineCardViewVisiblity(view: CardView, text: String) = { if (text.isEmpty()) view.visibility == View.GONE else view.visibility = View.VISIBLE }

}