package sp.smart.smartpub.ui.menu.swipe

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.swipe_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.ui.test.TinderContactModel
import sp.smart.smartpub.ui.test.TinderContactViewModel

class SwipeFragment : Fragment() {

    //todo for fun and later consideration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.swipe_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders
            .of(this)
            .get(TinderContactViewModel::class.java)

        viewModel
            .modelStream
            .observe(this, Observer {
                bindCard(it)
            })

        motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenUnlike,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.start, R.id.detail)
                        viewModel.swipe()
                    }
                }
            }
        })

        likeFloating.setOnClickListener {
            motionLayout.transitionToState(R.id.like)
        }

        unlikeFloating.setOnClickListener {
            motionLayout.transitionToState(R.id.unlike)
        }
    }

    private fun bindCard(model: TinderContactModel) {
        containerCardOne.setBackgroundColor(model.cardTop.backgroundColor)
        name.text = "${model.cardTop.name}, ${model.cardTop.age}"
        description.text = model.cardTop.description
        containerCardTwo.setBackgroundColor(model.cardBottom.backgroundColor)
    }
}