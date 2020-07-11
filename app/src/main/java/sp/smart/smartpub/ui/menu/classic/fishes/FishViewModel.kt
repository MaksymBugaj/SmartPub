package sp.smart.smartpub.ui.menu.classic.fishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.data.repository.SmartRepository
import javax.inject.Inject

class FishViewModel @Inject constructor(
    private val smartRepository: SmartRepository
) : ViewModel() {

    fun getCategorizesCourses(category: String): LiveData<List<Course>> {
        return smartRepository.getCoursesByCategory(category)
    }
}