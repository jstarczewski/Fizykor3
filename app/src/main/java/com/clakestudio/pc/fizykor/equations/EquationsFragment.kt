package com.clakestudio.pc.fizykor.equations

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding

class EquationsFragment : Fragment() {

    private lateinit var equationsAdapter: EquationsAdapter
    private lateinit var viewDataBinding: FragmentEquationsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        viewDataBinding = FragmentEquationsBinding.inflate(inflater, container, false).apply{
            viewmodel = (activity as EquationsActivity).obtainViewModel()
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel

        var equations = ArrayList<Equation>()
        equations.add(Equation("Elo", "Elooo", "1"))

        val viewModel = viewDataBinding.viewmodel
        if (viewModel!=null) {
            equationsAdapter = EquationsAdapter(equations)
            viewDataBinding.rvEquations.apply{
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                viewDataBinding.rvEquations.adapter = equationsAdapter
            }
        }


    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.start()
    }

    private fun setupFab() {

    }

    private fun setupRecyclerViewAdapter() {

    }

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

