package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentFlashCardsBinding

class FlashCardsFragment : Fragment() {


    private lateinit var viewFragmentBinding : FragmentFlashCardsBinding



    private lateinit var viewModel: FlashCardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Layout inflater because <layout></layout> makes no need for R.layout.fragment_flash_cards
        viewFragmentBinding = FragmentFlashCardsBinding.inflate(inflater, container, false).apply {

            viewFragmentBinding.viewmodel = (activity as FlashCardsActivity).obtainViewModel()


        }

        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }


    companion object {
        fun newInstance() = FlashCardsFragment()
    }
}
