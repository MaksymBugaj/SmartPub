package sp.smart.admin.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import sp.smart.admin.db.entity.Course

class FirebaseRepositoryImpl : FirebaseRepository {
    override suspend fun saveDataInFirestore(course: Course): Boolean {
        val firestore = Firebase.firestore
        return try {
            val data = firestore
                .collection("courses")
                .document()
                .set(course)
                .await()
            true
        } catch (e: Exception){
            Log.d("NOPE","There is a problem :)")
            false
        }
    }

    override suspend fun getDataFromFirestore(): List<Course>? {
        val firestore = Firebase.firestore
        Log.d("NOPE","There is test :)")
        return try {
            val data = firestore.collection("courses")
                .get()
                .await()
            val coursesList = data.toObjects(Course::class.java)
            Log.d("NOPE","There is data :) ${coursesList[0].name}")
            coursesList
        } catch (e: Exception){
            Log.d("NOPE","There is a get problem :) ${e.message}")
            null
        }
    }
}