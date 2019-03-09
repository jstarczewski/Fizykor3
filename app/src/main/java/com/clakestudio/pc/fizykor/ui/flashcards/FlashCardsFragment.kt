package com.clakestudio.pc.fizykor.ui.flashcards

import android.os.Bundle
import androidx.core.view.GestureDetectorCompat
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.databinding.FragmentFlashCardsBinding
import com.clakestudio.pc.fizykor.di.Injectable
import com.clakestudio.pc.fizykor.util.SharedPreferencesProvider
import com.clakestudio.pc.fizykor.util.SharedPreferencesProvider.set
import com.clakestudio.pc.fizykor.util.SharedPreferencesProvider.get
import kotlinx.android.synthetic.main.fragment_flash_cards.*
import kotlinx.android.synthetic.main.fragment_flash_cards.view.*
import javax.inject.Inject

class FlashCardsFragment : androidx.fragment.app.Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewFragmentBinding: FragmentFlashCardsBinding
    private lateinit var gestureDetectorCompat: GestureDetectorCompat
    private lateinit var flashCardsViewModel : FlashCardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        flashCardsViewModel = ViewModelProviders.of(this, viewModelFactory).get(FlashCardsViewModel::class.java)

        viewFragmentBinding = FragmentFlashCardsBinding.inflate(inflater, container, false).apply {
            viewmodel = flashCardsViewModel
        }

                /*.apply {
            viewmodel = (activity as FlashCardsActivity).obtainViewModel().apply {
                animatePreviousFlashCardEvent.observe(this@FlashCardsFragment, Observer { animatePrevious() })
                animateNewFlashCardEvent.observe(this@FlashCardsFragment, Observer { animateNext() })
            }

        }
*/        return viewFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupGestureDetector()
        setupCheckBox()

        val prefs = SharedPreferencesProvider.getDefaultSharedPreferences(context!!)
        if (prefs[getString(R.string.toast), false]!!) {
            showNavigationToast()
            prefs[getString(R.string.toast)] = true
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

    private fun animateNext() {
        cvFlashCard.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_view_transition_out_to_right))
    }

    private fun animatePrevious() {
        cvFlashCard.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_view_transition_out_to_left))
    }

    private fun setupCheckBox() {
        viewFragmentBinding.cbMode.setOnClickListener { viewFragmentBinding.viewmodel?.isMaturaMode = viewFragmentBinding.cbMode.isChecked }
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
        viewFragmentBinding.viewmodel?.switchMathViewVisibility()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetectorCompat.onTouchEvent(event)
    }


    companion object {
        fun newInstance() = FlashCardsFragment()
    }

}


