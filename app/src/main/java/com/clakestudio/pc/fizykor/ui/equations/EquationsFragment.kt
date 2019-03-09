package com.clakestudio.pc.fizykor.ui.equations

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding
import com.clakestudio.pc.fizykor.di.Injectable
import javax.inject.Inject

class EquationsFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentEquationsBinding

    lateinit var equationsViewModel: EquationsViewModel

    private lateinit var equationsAdapter: EquationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        equationsViewModel = ViewModelProviders.of(this, viewModelFactory).get(EquationsViewModel::class.java)
        viewDataBinding = FragmentEquationsBinding.inflate(inflater, container, false).apply {
            viewmodel = equationsViewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
    }

    override fun onResume() {
        super.onResume()
//        equationsViewModel?.start()
        equationsViewModel.start()

    }
    private fun setupFab() {
        var fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener{
            findNavController().navigate(R.id.action_equationsFragment_to_flashCardsFragment)
        }


        //equationsViewModel.openFlashCards()
        //(layoutParams as androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams).anchorId = View.NO_ID
    }


    private fun setupRecyclerView() {
        viewDataBinding.rvEquations.apply {
            equationsAdapter = EquationsAdapter(arrayListOf())
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            viewDataBinding.rvEquations.adapter = equationsAdapter
        }
    }

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

