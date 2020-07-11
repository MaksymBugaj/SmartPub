package sp.smart.smartpub.data.repository

import androidx.lifecycle.LiveData
import sp.smart.smartpub.data.db.CourseDao
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.data.repository.SmartRepository

class SmartRepositoryImpl(
    private val courseDao: CourseDao
) : SmartRepository {
    override suspend fun insertCourse(course: Course) {
        courseDao.insertCourse(course)
    }

    override fun getCoursesByCategory(category: String): LiveData<List<Course>> {
        return courseDao.getCoursesForCategory(category)
    }

    override suspend fun getAllCourses(): List<Course> {
        return courseDao.getAll()
    }

    override suspend fun deleteCourse(course: Course) {
        courseDao.delete(course)
    }
}