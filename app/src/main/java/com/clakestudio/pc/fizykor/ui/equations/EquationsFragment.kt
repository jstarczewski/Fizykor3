package com.clakestudio.pc.fizykor.ui.equations

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.adapters.EquationsAdapter
import com.clakestudio.pc.fizykor.databinding.FragmentEquationsBinding
import com.clakestudio.pc.fizykor.di.Injectable
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_equations.*
import kotlinx.android.synthetic.main.app_bar_equations.*
import javax.inject.Inject

class EquationsFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentEquationsBinding

    lateinit var equationsViewModel: EquationsViewModel

    lateinit var fab : FloatingActionButton

    private lateinit var equationsAdapter: EquationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        equationsViewModel = ViewModelProviders.of(this, viewModelFactory).get(EquationsViewModel::class.java)
        viewDataBinding = FragmentEquationsBinding.inflate(inflater, container, false).apply {
            viewmodel = equationsViewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       fab  = activity!!.findViewById<FloatingActionButton>(R.id.fab)
        setUpDrawerContent(activity!!.findViewById(R.id.nav_view))
    }

    override fun onResume() {
        super.onResume()
        equationsViewModel.start()
    }

    private fun setupFab() {
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            (it as FloatingActionButton).hide()
            findNavController().navigate(R.id.action_equationsFragment_to_flashCardsFragment)
        }
    }

    private fun setUpDrawerContent(navigationView: NavigationView) {

        navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.stale -> fab.hide()
                R.id.przedrostki -> fab.hide()
                else -> fab.show()
            }
            equationsViewModel.filterEquations(menuItem.title.toString())
            menuItem.isChecked = true
            toolbar.title = menuItem.title.toString()
            activity?.drawer_layout?.closeDrawers()
            true
        }
        navigationView.menu.findItem(R.id.stale_i_przedrostki).isVisible = true

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

