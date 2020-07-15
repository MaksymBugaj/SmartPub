package sp.smart.smartpub.ui.menu.classic.soups

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appetizers_fragment.*
import kotlinx.android.synthetic.main.soups_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class SoupsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val soupsViewModel: SoupsViewModel by viewModels {
        viewModelProviderFactory
    }

    private lateinit var soupsRecyclerView: RecyclerView
    private lateinit var listOfSoups : List<Course>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.soups_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupSoupsRecycler()
        setupSoupsClickListener()
        setupCourse()
    }

    private fun setupSoupsRecycler(){
        soupsRecyclerView = soupsFragment_soups
        soupsRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        soupsRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupSoupsClickListener(){
        (soupsRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click soups")
                sp.smart.smartpub.util.showDialog(listOfSoups[position], requireContext())
            }

            override fun onItemSelected(position: Int) {
                Log.d("NOPE","check soups")
            }

            override fun onItemDeselected(position: Int) {
                Log.d("NOPE","check soups")
            }
        })


    }
    private fun setupCourse(){
        soupsViewModel.getCategorizesCourses("Soups").observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer

            if(it.isNotEmpty()) {
                Log.d("NOPE","Visibility change ${it.size}")
                soupsFragment_group_loading.visibility = View.GONE
                listOfSoups = it
                (soupsRecyclerView.adapter as CoursesAdapter).setItems(listOfSoups)
            }

        })
    }

}