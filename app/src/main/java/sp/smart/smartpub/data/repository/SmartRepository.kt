package sp.smart.smartpub.data.repository

import androidx.lifecycle.LiveData
import sp.smart.smartpub.data.db.entity.Category
import sp.smart.smartpub.data.db.entity.Course

interface SmartRepository {

    suspend fun insertCourse(course: Course)

    fun getCoursesByCategory(category: String): LiveData<List<Course>>

    suspend fun getAllCourses(): List<Course>

    suspend fun deleteCourse(course: Course)
}