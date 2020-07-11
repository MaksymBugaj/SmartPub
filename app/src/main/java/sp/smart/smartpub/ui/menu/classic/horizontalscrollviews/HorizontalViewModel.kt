package sp.smart.smartpub.ui.menu.classic.horizontalscrollviews

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.data.repository.SmartRepository
import javax.inject.Inject

class HorizontalViewModel @Inject constructor(
    private val smartRepository: SmartRepository
): ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses : LiveData<List<Course>> = _courses

    private fun getAllCourses(){
        viewModelScope.launch(Dispatchers.IO) {
            val coursesFromDb = smartRepository.getAllCourses()
            _courses.postValue(coursesFromDb)
        }
    }

    fun getCategorizesCourses(category:String): LiveData<List<Course>>{
        return smartRepository.getCoursesByCategory(category)
    }
}