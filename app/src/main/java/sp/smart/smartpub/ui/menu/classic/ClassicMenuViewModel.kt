package sp.smart.smartpub.ui.menu.classic

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sp.smart.smartpub.data.repository.SmartRepository
import sp.smart.smartpub.data.db.entity.Course
import javax.inject.Inject

class ClassicMenuViewModel @Inject constructor(
    private val smartRepository: SmartRepository
) : ViewModel() {

    val name = ObservableField<String>()
    val price = ObservableField<String>()
    val category = ObservableField<String>()

    fun insert(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            smartRepository.insertCourse(course)
        }
    }

    private val _coursesState = MutableLiveData<CourseState>()
    val coursesState: LiveData<CourseState> = _coursesState

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            val courses = smartRepository.getAllCourses()

        }
    }

    fun onAddClick(){
        viewModelScope.launch(Dispatchers.IO) {
            if(category.get() == "Main courses") category.set("main")
            smartRepository.insertCourse(Course( name = name.get()!!,price = price.get()!!,category = category.get()!!))
            _coursesState.postValue(CourseAdded)
        }
    }
}

sealed class CourseState

object CourseAdded : CourseState()