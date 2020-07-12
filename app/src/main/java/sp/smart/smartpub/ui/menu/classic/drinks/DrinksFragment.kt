package sp.smart.smartpub.ui.menu.classic.drinks

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
import kotlinx.android.synthetic.main.drinks_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class DrinksFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val drinksViewModel: DrinksViewModel by viewModels {
        viewModelProviderFactory
    }

    private lateinit var drinksRecyclerView: RecyclerView
    private lateinit var listOfDrinks : List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drinks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupDrinksRecycler()
        setupDrinksClickListener()
        setupCourse()
    }

    private fun setupDrinksRecycler(){
        drinksRecyclerView = drinksFragment_drinks
        drinksRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        drinksRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupDrinksClickListener(){
        (drinksRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click drinks")
            }
        })


    }
    private fun setupCourse(){
        drinksViewModel.getCategorizesCourses("Drinks").observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer

            if(it.isNotEmpty()) {
                drinksFragment_group_loading.visibility = View.GONE
                listOfDrinks = it
                (drinksRecyclerView.adapter as CoursesAdapter).setItems(listOfDrinks)
            }

        })
    }

}