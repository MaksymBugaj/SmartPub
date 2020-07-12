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
import timber.log.Timber
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    private val smartRepository: SmartRepository
): ViewModel() {

    val name = ObservableField<String>()
    val price = ObservableField<String>()
    val category = ObservableField<String>()
    val spinnerVisibility = ObservableField<Boolean>(false)

    private val _coursesState = MutableLiveData<CourseState>()
    val coursesState: LiveData<CourseState> = _coursesState

    fun onAddClick(){
        viewModelScope.launch(Dispatchers.IO) {
            spinnerVisibility.set(true)
            if(category.get() == "Main courses") category.set("main")
            val course = Course(name = name.get()!!,price = price.get()!!,category = category.get()!!)
            smartRepository.insertCourse(course)
            val courseToFirestore = smartRepository.getCourseByName(course.name)
            val inserted = smartRepository.saveDataInFirestore(courseToFirestore)
            if(inserted) {
                Log.d("NOPE","INSERTED")
                Timber.d("We were planted")
                _coursesState.postValue(CourseAdded)
            } else {
                Log.d("NOPE","error")
                Timber.d("We werent planted")
            }
        }
    }
}

sealed class CourseState

object CourseAdded : CourseState()