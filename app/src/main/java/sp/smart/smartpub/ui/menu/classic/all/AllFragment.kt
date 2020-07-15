package sp.smart.smartpub.ui.menu.classic.all

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
import kotlinx.android.synthetic.main.all_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AllFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val allFragmentViewModel: AllViewModel by viewModels {
        viewModelProviderFactory
    }

    private lateinit var allRecyclerView: RecyclerView
    private lateinit var listOfAllCoures : List<Course>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecycler()
        setupClickListener()
        setupObserverOnRecycler()

    }

    private fun setUpRecycler(){
        allRecyclerView = recycler_view
        allRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        allRecyclerView.adapter = CoursesAdapter()
    }

    private fun setupClickListener(){
        (allRecyclerView.adapter as CoursesAdapter).setOnClickListener(object: CoursesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","click click")
            }

            override fun onItemSelected(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemDeselected(position: Int) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setupObserverOnRecycler(){
        allFragmentViewModel.courses.observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer

            listOfAllCoures = it
            (allRecyclerView.adapter as CoursesAdapter).setItems(listOfAllCoures)


        })
    }

}