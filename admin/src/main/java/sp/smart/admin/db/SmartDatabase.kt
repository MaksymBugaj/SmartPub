package sp.smart.admin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sp.smart.admin.db.entity.Course

@Database(
    entities = [
    Course::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SmartDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
}