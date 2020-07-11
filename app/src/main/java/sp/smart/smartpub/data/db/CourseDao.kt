package sp.smart.smartpub.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import sp.smart.smartpub.data.db.entity.Category
import sp.smart.smartpub.data.db.entity.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    @Query("select * from course where category=:category")
    fun getCoursesForCategory(category: String): LiveData<List<Course>>

    @Query("select * from course")
    fun getAll(): List<Course>

    @Delete
    fun delete(course: Course)
}