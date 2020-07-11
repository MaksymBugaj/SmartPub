package sp.smart.smartpub.ui.menu.classic.all

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.data.repository.SmartRepository
import javax.inject.Inject

class AllViewModel @Inject constructor(
    private val smartRepository: SmartRepository
): ViewModel() {


    init {
        getAllCourses()
    }

    private val _courses = MutableLiveData<List<Course>>()
    val courses : LiveData<List<Course>> = _courses
    private fun getAllCourses(){
        viewModelScope.launch(Dispatchers.IO) {
            val coursesFromDb = smartRepository.getAllCourses()
            _courses.postValue(coursesFromDb)
        }
    }
}