package sp.smart.admin.repository

import sp.smart.admin.db.entity.Course

interface FirebaseRepository {

    suspend fun saveDataInFirestore(course: Course): Boolean

    suspend fun getDataFromFirestore(): List<Course>?
}