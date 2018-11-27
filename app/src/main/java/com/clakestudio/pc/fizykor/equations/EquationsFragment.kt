package com.clakestudio.pc.fizykor.equations

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val viewModel = viewDataBinding.viewmodel
        if (viewModel!=null) {
            viewDataBinding.rvEquations.apply{
                equationsAdapter = EquationsAdapter(ArrayList(0))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                viewDataBinding.rvEquations.adapter = equationsAdapter
            }
        }


    }

    private fun setupFab() {

    }

    private fun setupRecyclerViewAdapter() {

    }

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

