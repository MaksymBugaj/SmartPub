package sp.smart.smartpub.ui.menu.classic.maincourses

import android.app.Dialog
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appetizers_fragment.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.main_courses_fragment.*
import kotlinx.android.synthetic.main.swipe_fragment.view.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainCoursesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val mainCoursesViewModel: MainCoursesViewModel by viewModels {
        viewModelProviderFactory
    }
    private lateinit var mainCoursesRecyclerView: RecyclerView
    private lateinit var listOfMainCourses : List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_courses_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMainCoursesRecycler()
        setupMainCoursesClickListener()
        setupCourse()

    }

    private fun setupMainCoursesRecycler(){
        mainCoursesRecyclerView = mainCoursesFragment_mainCourses
        mainCoursesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        mainCoursesRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupMainCoursesClickListener(){
        (mainCoursesRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click mainCourses")
                showDialog(position)
            }
        })


    }
    private fun setupCourse(){
        mainCoursesViewModel.getCategorizesCourses("main").observe(viewLifecycleOwner, Observer {
            Log.d("NOPE","observer mainCourses")
            if(it==null) return@Observer

            if(it.isNotEmpty()) {
                mainCoursesFragment_group_loading.visibility = View.GONE
                listOfMainCourses = it
                (mainCoursesRecyclerView.adapter as CoursesAdapter).setItems(listOfMainCourses)
            }

        })
    }

    fun showDialog(position: Int){
        /*val builder = Dialog(requireContext())
        builder.setContentView(R.layout.dialog_layout)
        //builder.setView(R.layout.dialog_layout)

        builder.item_name.text = "DUPA"
        builder.item_description.text = "EIN"
        builder.item_price.text = "12"
        builder.item_button.setOnClickListener {
            builder.cancel()
        }
        builder.show()*/

        sp.smart.smartpub.util.showDialog(listOfMainCourses[position], requireContext())
    }

}