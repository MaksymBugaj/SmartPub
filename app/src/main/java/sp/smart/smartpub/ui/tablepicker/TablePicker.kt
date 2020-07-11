package sp.smart.smartpub.ui.tablepicker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sp.smart.smartpub.R

class TablePicker : Fragment() {

    companion object {
        fun newInstance() = TablePicker()
    }

    private lateinit var viewModel: TablePickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.table_picker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TablePickerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}