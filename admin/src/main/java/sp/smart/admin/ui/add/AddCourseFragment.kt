package sp.smart.admin.ui.add

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.add_course_fragment.*
import sp.smart.admin.R
import sp.smart.admin.databinding.AddCourseFragmentBinding
import sp.smart.admin.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AddCourseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val addCourseViewModel: AddCourseViewModel by viewModels{
        viewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return AddCourseFragmentBinding.inflate(inflater, container, false).apply {
            this.viewmodel = addCourseViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addCourse_spinner.setOnSpinnerItemSelectedListener<String> { position, item ->
            addCourseViewModel.category.set(item)
        }


        addCourseViewModel.coursesState.observe(this, Observer {
            if (it == null) return@Observer

            Log.d("NOPE","COURSE ADDED!!!")
//            when (it) {
//                CourseAdded -> {
//                    findNavController().navigate(R.id.appetizersFragment)
//                }
//            }
        })
    }

}