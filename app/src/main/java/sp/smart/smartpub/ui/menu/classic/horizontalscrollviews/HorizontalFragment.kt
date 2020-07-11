package sp.smart.smartpub.ui.menu.classic.horizontalscrollviews

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
import kotlinx.android.synthetic.main.all_fragment.*
import kotlinx.android.synthetic.main.horizontal_fragment.*
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.databinding.HorizontalFragmentBinding
import sp.smart.smartpub.ui.menu.classic.CoursesAdapter
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class HorizontalFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val horizontalViewModel: HorizontalViewModel by viewModels {
        viewModelProviderFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return HorizontalFragmentBinding.inflate(inflater, container,false).apply {
            viewModel = horizontalViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        Log.d("NOPE","in horizonGO")
    }





}