package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GestureDetectorCompat
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentFlashCardsBinding
import com.clakestudio.pc.fizykor.util.SharedPreferencesProvider
import kotlinx.android.synthetic.main.fragment_flash_cards.view.*

class FlashCardsFragment : Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener {

    private lateinit var viewFragmentBinding: FragmentFlashCardsBinding
    private lateinit var gestureDetectorCompat: GestureDetectorCompat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        viewFragmentBinding = FragmentFlashCardsBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as FlashCardsActivity).obtainViewModel().apply {

                flashCardVisibilityEvent.observe(this@FlashCardsFragment, Observer { v ->
                    viewFragmentBinding.mvFlashcard.visibility = v!!
                })
                animateCardViewEvent.observe(this@FlashCardsFragment, Observer { animation ->
                    cvFlashCard.startAnimation(AnimationUtils.loadAnimation(context, animation!!))
                })
            }

        }

        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupGestureDetector()
        setupCheckBox()
        // not to best way to do it but deadline coming :(


        if (!context!!.getSharedPreferences("prefs", Context.MODE_PRIVATE).getBoolean("toast", false)) {
            showNavigationToast()
            context!!.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().putBoolean("toast", true).apply()
        }

    }

    override fun onResume() {
        super.onResume()
        viewFragmentBinding.viewmodel?.start()
    }

    private fun setupGestureDetector() {

        gestureDetectorCompat = GestureDetectorCompat(this.activity, this)
        viewFragmentBinding.root.cvFlashCard.setOnTouchListener(this)

    }

    private fun showNavigationToast() {
        for (x in 0..3)
            Toast.makeText(context, getString(R.string.flashCards_navigation), Toast.LENGTH_LONG).show()
    }


    private fun setupCheckBox() {

        viewFragmentBinding.cbMode.setOnClickListener { viewFragmentBinding.viewmodel?.setMaturaMode(viewFragmentBinding.cbMode.isChecked) }

    }


    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        viewFragmentBinding.viewmodel?.determineAnimation(e1!!.x, e2!!.x, R.anim.card_view_transition_out_to_right, R.anim.card_view_transition_out_to_left)
        return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        viewFragmentBinding.viewmodel?.switchMathViewVisibility(viewFragmentBinding.mvFlashcard.visibility)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetectorCompat.onTouchEvent(event)
    }


    companion object {
        fun newInstance() = FlashCardsFragment()
    }

}
