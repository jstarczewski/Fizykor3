package com.clakestudio.pc.fizykor.equations

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding

class EquationsFragment : Fragment() {

    private lateinit var equationsAdapter: EquationsAdapter
    private lateinit var viewDataBinding: FragmentEquationsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentEquationsBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EquationsActivity).obtainViewModel()
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // setupRecyclerView()
        setupFab()
        //setMathViewTextZoom()
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.start()

    }

    private fun setupFab() {
        activity!!.findViewById<FloatingActionButton>(R.id.fab).run {
            setOnClickListener {
                viewDataBinding.viewmodel!!.openFlashCards()
            }
        }
    }

    /*
    private fun setupRecyclerView() {
        if (viewDataBinding.viewmodel != null) {
            viewDataBinding.rvEquations.apply {
                equationsAdapter = EquationsAdapter(arrayListOf())
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                viewDataBinding.rvEquations.adapter = equationsAdapter
            }
        }
    }*/
/*
    private fun setMathViewTextZoom() {

        viewDataBinding.apply {
            mvEquation1.textZoom = 75
            mvEquation2.textZoom = 75
            mvEquation3.textZoom = 75
            mvEquation4.textZoom = 75
            mvEquation5.textZoom = 75
            mvEquation6.textZoom = 75
            mvEquation7.textZoom = 75
            mvEquation8.textZoom = 75
            mvEquation9.textZoom = 75
            mvEquation10.textZoom = 75
            mvEquation11.textZoom = 75
            mvEquation12.textZoom = 75
            mvEquation13.textZoom = 75
            mvEquation14.textZoom = 75
            mvEquation15.textZoom = 75
            mvEquation16.textZoom = 75

        }

    }*/

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

