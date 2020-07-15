package sp.smart.smartpub.util

import android.app.Dialog
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.dialog_layout.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course

fun showDialog(course:Course, context: Context){
    val builder = Dialog(context)
    builder.setContentView(R.layout.dialog_layout)
    builder.item_name.text = course.name
    builder.item_price.text = course.price
    builder.item_description.text = course.description
    builder.item_button.setOnClickListener {
        builder.cancel()
    }
    builder.show()
}