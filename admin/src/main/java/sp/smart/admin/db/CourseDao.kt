package sp.smart.admin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import sp.smart.admin.db.entity.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    @Query("select * from course where category=:category")
    fun getCoursesForCategory(category: String): LiveData<List<Course>>

    @Query("select * from course where name=:name")
    fun getCoursesByName(name: String): Course

    @Query("select * from course")
    fun getAll(): List<Course>

    @Delete
    fun delete(course: Course)
}