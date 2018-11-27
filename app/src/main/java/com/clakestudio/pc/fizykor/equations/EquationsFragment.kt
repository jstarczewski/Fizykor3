package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.EquationsFragmentBinding

class EquationsFragment : Fragment() {

    private lateinit var equationsAdapter: EquationsAdapter
    private lateinit var viewDataBinding: EquationsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding = EquationsFragmentBinding.inflate(inflater, container, false).apply{
            viewmodel = (activity as EquationsActivity).obtainViewModel()
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel




    }

    private fun setupFab() {

    }

    private fun setupRecyclerViewAdapter() {

    }

    companion object {
        fun newInstance() = EquationsFragment()
    }


}

