package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.clakestudio.pc.fizykor.R

class FlashCardsFragment : Fragment() {

    companion object {
        fun newInstance() = FlashCardsFragment()
    }

    private lateinit var viewModel: FlashCardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flash_cards, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FlashCardsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
