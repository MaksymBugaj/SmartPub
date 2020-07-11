package sp.smart.smartpub.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import sp.smart.smartpub.data.db.entity.Course

interface FirebaseRepository {

    suspend fun getDataFromFirestore(): List<Course>?
}