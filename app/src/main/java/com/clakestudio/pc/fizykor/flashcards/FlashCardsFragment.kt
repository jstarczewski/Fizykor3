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
import kotlinx.android.synthetic.main.fragment_flash_cards.view.*

class FlashCardsFragment : Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener, Animation.AnimationListener {

    private lateinit var viewFragmentBinding: FragmentFlashCardsBinding
    private lateinit var gestureDetectorCompat: GestureDetectorCompat

    //Animations
    private lateinit var cvOutAnimationToRight: Animation
    private lateinit var cvInAnimationFromLeft: Animation
    private lateinit var cvOutAnimationToLeft: Animation
    private lateinit var cvInAnimationFromRight: Animation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Layout inflater because <layout></layout> makes no need for R.layout.fragment_flash_cards
        viewFragmentBinding = FragmentFlashCardsBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as FlashCardsActivity).obtainViewModel().apply {
                flashCardVisibilityEvent.observe(this@FlashCardsFragment, Observer { v ->
                    viewFragmentBinding.mvFlashcard.visibility = v!!
                })
                animateCardViewEvent.observe(this@FlashCardsFragment, Observer { flag ->
                    animateBasedOnFlagValue(flag!!)
                })
            }

        }

        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAnimations()
        setupCheckBox()
    }

    override fun onResume() {
        super.onResume()
        viewFragmentBinding.viewmodel?.start()
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

    private fun setupCheckBox() {

        viewFragmentBinding.cbMode.setOnClickListener { viewFragmentBinding.viewmodel?.setMaturalneFlashCards(viewFragmentBinding.cbMode.isChecked) }

    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        viewFragmentBinding.viewmodel?.determineAnimation(e1!!.x, e2!!.x)
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

    private fun animateBasedOnFlagValue(flag: Int) = if (flag != 0) viewFragmentBinding.cvFlashCard.startAnimation(cvOutAnimationToRight) else viewFragmentBinding.cvFlashCard.startAnimation(cvOutAnimationToLeft)

    companion object {
        fun newInstance() = FlashCardsFragment()
    }

}
