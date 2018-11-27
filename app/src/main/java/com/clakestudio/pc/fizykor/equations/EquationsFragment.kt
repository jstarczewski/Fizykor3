package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.clakestudio.pc.fizykor.R

class EquationsFragment : Fragment() {

    companion object {
        fun newInstance() = EquationsFragment()
    }

    private lateinit var viewModel: EquationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.equations_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EquationsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
