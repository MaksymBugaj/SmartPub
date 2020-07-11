package sp.smart.smartpub.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import sp.smart.smartpub.data.db.entity.Category
import sp.smart.smartpub.data.db.entity.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    @Query("select * from course_table where category=:category")
    fun getCoursesForCategory(category: String): LiveData<List<Course>>

    @Query("select * from course_table where name=:name")
    fun getCoursesByName(name: String): Course

    @Query("select * from course_table")
    fun getAll(): List<Course>

    @Delete
    fun delete(course: Course)

    @Query("DELETE FROM course_table")
    fun dropCourseTable()


}