package com.clakestudio.pc.fizykor.equations

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding
import kotlinx.android.synthetic.main.app_bar_equations.view.*

class EquationsFragment : Fragment() {

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

        /**
         * Double query or double injection bug
         * */

        viewDataBinding.viewmodel?.start()

    }

    private fun setupFab() {
        activity!!.findViewById<FloatingActionButton>(R.id.fab).run {
            setOnClickListener {
                viewDataBinding.viewmodel!!.openFlashCards()
            }
        }
        var fab =  activity!!.findViewById<FloatingActionButton>(R.id.fab)
        var p: CoordinatorLayout.LayoutParams = fab.layoutParams as CoordinatorLayout.LayoutParams
        p.anchorId = View.NO_ID
        fab.layoutParams = p


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

