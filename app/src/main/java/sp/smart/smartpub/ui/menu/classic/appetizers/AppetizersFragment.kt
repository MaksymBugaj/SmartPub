package sp.smart.smartpub.ui.menu.classic.appetizers

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appetizers_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.ui.menu.classic.horizontalscrollviews.HorizontalViewModel
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AppetizersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val appetizersViewModel: AppetizersViewModel by viewModels {
        viewModelProviderFactory
    }

    private lateinit var appetizersRecyclerView: RecyclerView
    private lateinit var listOfAppetizers : List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.appetizers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpAppetizersRecycler()
        setupAppetizersClickListener()
        setupCourse()
        Log.d("NOPE","in freagment cointainter views")
    }

    private fun setUpAppetizersRecycler(){
        appetizersRecyclerView = appetizersFragment_appetizers
        appetizersRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        appetizersRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupAppetizersClickListener(){
        (appetizersRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click appetizersRecyclerView")
            }
        })


    }
    private fun setupCourse(){
        appetizersViewModel.getCategorizesCourses("Appetizers").observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            if(it.isNotEmpty()) {
            appetizersFragment_group_loading.visibility = View.GONE
            listOfAppetizers = it
            (appetizersRecyclerView.adapter as CoursesAdapter).setItems(listOfAppetizers)
        }

        })
    }

}