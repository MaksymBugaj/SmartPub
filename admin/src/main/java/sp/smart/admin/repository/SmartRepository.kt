package sp.smart.admin.repository

import androidx.lifecycle.LiveData
import sp.smart.admin.db.entity.Course

interface SmartRepository {

    suspend fun insertCourse(course: Course)

    fun getCoursesByCategory(category: String): LiveData<List<Course>>

    suspend fun getCourseByName(name: String): Course

    suspend fun getAllCourses(): List<Course>

    suspend fun deleteCourse(course: Course)

    suspend fun saveDataInFirestore(course: Course): Boolean

    suspend fun fetchDataFromServer(): Boolean
}