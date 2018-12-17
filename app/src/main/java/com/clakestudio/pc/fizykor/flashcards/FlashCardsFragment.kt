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
                animateFlashCardEvent.observe(this@FlashCardsFragment, Observer {
                    mathView ->
                })
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

        viewFragmentBinding.viewmodel?.defineAnimationType(e1!!.x, e2!!.x)
        viewFragmentBinding.mvFlashcard.visibility=View.INVISIBLE
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
        switchMathViewVisibility(viewFragmentBinding.mvFlashcard)
    }

    private fun switchMathViewVisibility(mathView: MathView) = if (mathView.visibility == View.VISIBLE) mathView.visibility = View.INVISIBLE else mathView.visibility = View.VISIBLE


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

    companion object {
        fun newInstance() = FlashCardsFragment()
    }

}
