package sp.smart.admin.ui.add

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sp.smart.admin.db.entity.Course
import sp.smart.admin.repository.SmartRepository
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    private val smartRepository: SmartRepository
): ViewModel() {

    val name = ObservableField<String>()
    val price = ObservableField<String>()
    val category = ObservableField<String>()

    private val _coursesState = MutableLiveData<CourseState>()
    val coursesState: LiveData<CourseState> = _coursesState

    fun onAddClick(){
        viewModelScope.launch(Dispatchers.IO) {
            if(category.get() == "Main courses") category.set("main")
            val course = Course(name = name.get()!!,price = price.get()!!,category = category.get()!!)
            smartRepository.insertCourse(course)
            val courseToFirestore = smartRepository.getCourseByName(course.name)
            val inserted = smartRepository.saveDataInFirestore(courseToFirestore)
            if(inserted) {
                Log.d("NOPE","INSERTED")
                _coursesState.postValue(CourseAdded)
            }
        }
    }
}

sealed class CourseState

object CourseAdded : CourseState()