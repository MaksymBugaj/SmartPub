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

    override suspend fun fetchDataFromServer(): FirebaseStatus {
            val dataFromServer =  firebaseRepository.getDataFromFirestore()
            if(dataFromServer != null) {
                for (course in dataFromServer){
                    insertCourseFromServer(course)
                }
                return FirebaseStatus.LOADED
            } else return FirebaseStatus.NOT_LOADED
    }

    private fun insertCourseFromServer(course: Course){
        courseDao.insertCourse(course)
    }
}

enum class FirebaseStatus{
    NO_INTERNET, LOADED, NOT_LOADED
}