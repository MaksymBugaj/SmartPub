package sp.smart.smartpub.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String
) {
    constructor() : this(0, "", "", "", "")
}