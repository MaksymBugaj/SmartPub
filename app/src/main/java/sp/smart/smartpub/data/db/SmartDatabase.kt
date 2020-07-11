package sp.smart.smartpub.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sp.smart.smartpub.data.db.entity.Course

@Database(
    entities = [
    Course::class
    ],
    version = 4
)
abstract class SmartDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
}