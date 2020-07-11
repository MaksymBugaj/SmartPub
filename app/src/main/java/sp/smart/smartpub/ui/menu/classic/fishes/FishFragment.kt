package sp.smart.smartpub.ui.menu.classic.fishes

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
import kotlinx.android.synthetic.main.fish_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class FishFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val fishesViewModel: FishViewModel by viewModels {
        viewModelProviderFactory
    }

    private lateinit var fishessRecyclerView: RecyclerView
    private lateinit var listOfFishes : List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fish_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupFishesRecycler()
        setupFishesClickListener()
        setupCourse()
        Log.d("NOPE","in freagment cointainter views")
    }

    private fun setupFishesRecycler(){
        fishessRecyclerView = fishesFragment_fishes
        fishessRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        fishessRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupFishesClickListener(){
        (fishessRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click fishes")
            }
        })


    }
    private fun setupCourse(){
        fishesViewModel.getCategorizesCourses("Fishes").observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer

            listOfFishes = it
            (fishessRecyclerView.adapter as CoursesAdapter).setItems(listOfFishes)


        })
    }

}