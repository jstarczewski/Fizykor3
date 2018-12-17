package com.clakestudio.pc.fizykor.flashcards

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GestureDetectorCompat
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentFlashCardsBinding
import com.jstarczewski.pc.mathview.src.MathView
import kotlinx.android.synthetic.main.fragment_flash_cards.view.*

class FlashCardsFragment : Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener, Animation.AnimationListener {

    private lateinit var viewFragmentBinding: FragmentFlashCardsBinding

    private lateinit var cvOutAnimationToRight: Animation
    private lateinit var cvInAnimationFromLeft: Animation
    private lateinit var cvOutAnimationToLeft: Animation
    private lateinit var cvInAnimationFromRight: Animation

    private lateinit var gestureDetectorCompat: GestureDetectorCompat



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Layout inflater because <layout></layout> makes no need for R.layout.fragment_flash_cards
        viewFragmentBinding = FragmentFlashCardsBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as FlashCardsActivity).obtainViewModel().apply {
              //  switchFlashCardEvent.observe(this@FlashCardsFragment, Observer<MathView> {  } })

            }
        }
        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAnimations()
    }

    override fun onResume() {
        super.onResume()

        viewFragmentBinding.viewmodel?.start()

        /// Not working
    }

    private fun setupAnimations() {

        // Setting up gestureDetector
        gestureDetectorCompat = GestureDetectorCompat(this.activity, this)
        /**
         * Setting up needed animations and animations listeners
         * */

        cvOutAnimationToRight = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_out_to_right)
        cvInAnimationFromLeft = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_in_from_left)
        cvOutAnimationToLeft = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_out_to_left)
        cvInAnimationFromRight = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_in_from_right)

        viewFragmentBinding.root.cvFlashCard.setOnTouchListener(this)
        cvOutAnimationToLeft.setAnimationListener(this)
        cvOutAnimationToRight.setAnimationListener(this)

    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {

        defineAnimationType(e1!!.x, e2!!.x)
        // to small fling bug -> no delta check
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
        switchMathViewVisibility()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetectorCompat.onTouchEvent(event)
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        if (animation.toString() == (cvOutAnimationToRight.toString()))
            viewFragmentBinding.cvFlashCard.startAnimation(cvInAnimationFromLeft)
        else
            viewFragmentBinding.cvFlashCard.startAnimation(cvInAnimationFromRight)
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    private fun defineAnimationType(x1: Float, x2: Float) {

        viewFragmentBinding.viewmodel

    }

    private fun switchMathViewVisibility() {
        /**
         * Simple Visibility switch on longClick()
         * Not working with smth ? a : b
         * */
        if (viewFragmentBinding.mvFlashcard.visibility == View.VISIBLE)
            viewFragmentBinding.mvFlashcard.visibility = View.INVISIBLE
        else
            viewFragmentBinding.mvFlashcard.visibility = View.VISIBLE

    }

    private fun getNextFlashCard() {
       viewFragmentBinding.viewmodel!!.prepareNextFlashCard()
    }

    private fun getPreviousFlashCard() {
        viewFragmentBinding.viewmodel!!.preparePreviousFlashCard()
    }

    companion object {
        fun newInstance() = FlashCardsFragment()
    }

}
