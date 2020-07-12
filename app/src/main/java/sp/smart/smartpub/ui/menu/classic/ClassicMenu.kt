package sp.smart.smartpub.ui.menu.classic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.classic_menu_fragment.*
import sp.smart.smartpub.R
import sp.smart.smartpub.databinding.ClassicMenuFragmentBinding
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory
import javax.inject.Inject


class ClassicMenu : DaggerFragment() {

    private lateinit var binding: ClassicMenuFragmentBinding

    val TAG = "NOPE"

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private val classicMenuViewModel: ClassicMenuViewModel by viewModels {
        viewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClassicMenuFragmentBinding.inflate(inflater, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewmodel = classicMenuViewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }





}