package sp.smart.admin.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import sp.smart.admin.db.CourseDao
import sp.smart.admin.db.entity.Course

class SmartRepositoryImpl(
    private val courseDao: CourseDao,
    private val firebaseRepository: FirebaseRepository
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

    override suspend fun getCourseByName(name: String): Course {
        return courseDao.getCoursesByName(name)
    }

    override suspend fun deleteCourse(course: Course) {
        courseDao.delete(course)
    }

    override suspend fun saveDataInFirestore(course: Course): Boolean {
        return firebaseRepository.saveDataInFirestore(course)
    }

    override suspend fun fetchDataFromServer(): Boolean {
        return try {
            val dataFromServer =  firebaseRepository.getDataFromFirestore()
            if(dataFromServer != null) {
                for (course in dataFromServer){
                    insertCourseFromServer(course)
                }
                true
            } else false
        } catch (e: Exception){
            Log.d("NOPE","There is a problem in fetch")
            false
        }
    }

    private fun insertCourseFromServer(course: Course){
        courseDao.insertCourse(course)
    }
}