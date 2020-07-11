package sp.smart.smartpub.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: String,
    val category: String
)