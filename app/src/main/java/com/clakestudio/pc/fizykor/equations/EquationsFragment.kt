package com.clakestudio.pc.fizykor.equations

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding
import com.clakestudio.pc.fizykor.di.Injectable
import javax.inject.Inject

class EquationsFragment : androidx.fragment.app.Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewDataBinding: FragmentEquationsBinding

    lateinit var equationsViewModel: EquationsViewModel

    private lateinit var equationsAdapter: EquationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding = FragmentEquationsBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupFab()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equationsViewModel = ViewModelProviders.of(this, viewModelFactory).get(EquationsViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
//        equationsViewModel?.start()
        equationsViewModel.start()

    }

    private fun setupFab() {
        activity!!.findViewById<FloatingActionButton>(R.id.fab).run {
            setOnClickListener {
                equationsViewModel.openFlashCards()
            }
            (layoutParams as androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams).anchorId = View.NO_ID
        }
    }


    private fun setupRecyclerView() {
        viewDataBinding.rvEquations.apply {
            equationsAdapter = EquationsAdapter(arrayListOf())
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            viewDataBinding.rvEquations.adapter = equationsAdapter
        }
    }

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

