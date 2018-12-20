package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GestureDetectorCompat
import android.view.*
import android.view.animation.AnimationUtils
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentFlashCardsBinding
import kotlinx.android.synthetic.main.fragment_flash_cards.view.*

class FlashCardsFragment : Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener {

    private lateinit var viewFragmentBinding: FragmentFlashCardsBinding
    private lateinit var gestureDetectorCompat: GestureDetectorCompat


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Layout inflater because <layout></layout> makes no need for R.layout.fragment_flash_cards
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
    }

    override fun onResume() {
        super.onResume()
        viewFragmentBinding.viewmodel?.start()
    }

    private fun setupGestureDetector() {

        // Setting up gestureDetector
        gestureDetectorCompat = GestureDetectorCompat(this.activity, this)

        /**
         * Setting up needed animations and animations listeners
         * */
        viewFragmentBinding.root.cvFlashCard.setOnTouchListener(this)

    }

    private fun setupCheckBox() {

        viewFragmentBinding.cbMode.setOnClickListener { viewFragmentBinding.viewmodel?.setMaturaMode(viewFragmentBinding.cbMode.isChecked) }

    }

    /**
     * Gesture detectore methods
     * */

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
