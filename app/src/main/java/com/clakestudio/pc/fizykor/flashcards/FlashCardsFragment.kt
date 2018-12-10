package com.clakestudio.pc.fizykor.flashcards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GestureDetectorCompat
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Toast
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

            }
        }

        gestureDetectorCompat = GestureDetectorCompat(this.activity, this)
        viewFragmentBinding.root.cvFlashCard.setOnTouchListener(this)
        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()

        viewFragmentBinding.viewmodel?.start()


        /// Not working
    }


    override fun onShowPress(e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Toast.makeText(context, "Eloo", Toast.LENGTH_SHORT).show()

        var cvOutAnimation = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_out)
        var cvInAnimation = AnimationUtils.loadAnimation(context, R.anim.card_view_transition_in)

        viewFragmentBinding.cvFlashCard.startAnimation(cvOutAnimation)
        viewFragmentBinding.cvFlashCard.startAnimation(cvInAnimation)
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetectorCompat.onTouchEvent(event)
    }

    companion object {
        fun newInstance() = FlashCardsFragment()
    }


}
