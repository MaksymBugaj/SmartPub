package sp.smart.smartpub.ui.menu.classic

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.appetizers_item.view.*
import kotlinx.android.synthetic.main.drinks_item.view.*
import kotlinx.android.synthetic.main.fishes_item.view.*
import kotlinx.android.synthetic.main.main_courses_item.view.*
import kotlinx.android.synthetic.main.soups_item.view.*
import sp.smart.smartpub.R
import sp.smart.smartpub.data.db.entity.Course

class CoursesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listOfCourses = ArrayList<Course>()
    private lateinit var listener: OnItemClickListener

    private val APPETIZERS = 1
    private val SOUPS = 2
    private val MAINCOURSES = 3
    private val FISHES = 4
    private val DRINKS = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            APPETIZERS -> {
                val layoutInflater =LayoutInflater.from(parent.context).inflate(R.layout.appetizers_item,parent,false)
                AppetizersViewHolder(layoutInflater,listener)
            }
            SOUPS -> {
                val layoutInflater =LayoutInflater.from(parent.context).inflate(R.layout.soups_item,parent,false)
                SoupsViewHolder(layoutInflater,listener)
            }
            MAINCOURSES -> {
                val layoutInflater =LayoutInflater.from(parent.context).inflate(R.layout.main_courses_item,parent,false)
                MainCoursesViewHolder(layoutInflater,listener)
            }
            FISHES -> {
                val layoutInflater =LayoutInflater.from(parent.context).inflate(R.layout.fishes_item,parent,false)
                FishViewHolder(layoutInflater,listener)
            }
            else -> {
                val layoutInflater =LayoutInflater.from(parent.context).inflate(R.layout.drinks_item,parent,false)
                DrinksViewHolder(layoutInflater,listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listOfCourses[position].category) {
            "Appetizers" -> APPETIZERS
            "Soups" -> SOUPS
            "main" -> MAINCOURSES
            "Fishes" -> FISHES
            else -> DRINKS
        }
    }


    override fun getItemCount(): Int {
        return listOfCourses.size
    }

    /* override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.setItems(listOfCourses[position])
     }*/

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when{
            getItemViewType(position) == APPETIZERS -> (holder as AppetizersViewHolder).setItems(listOfCourses[position])
            getItemViewType(position) == SOUPS -> (holder as SoupsViewHolder).setItems(listOfCourses[position])
            getItemViewType(position) == MAINCOURSES -> (holder as MainCoursesViewHolder).setItems(listOfCourses[position])
            getItemViewType(position) == FISHES -> (holder as FishViewHolder).setItems(listOfCourses[position])
            getItemViewType(position) == DRINKS -> (holder as DrinksViewHolder).setItems(listOfCourses[position])
        }
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setItems(homeActivities: List<Course>) {
        listOfCourses.clear()
        listOfCourses = homeActivities as ArrayList<Course>
        Log.d("NOPE", "Adapter size ${listOfCourses.size} ")
        notifyDataSetChanged()
    }



    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }


    internal class SoupsViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val courseItemName: TextView = itemView.soupsCourseItem_itemName
        private val courseItemPrice: TextView = itemView.soupsCourseItem_price
        private val courseItemDescription: TextView = itemView.soupsCourseItem_description
        private val courseItemImage: CardView = itemView.soups_item_root

        init {
            courseItemImage.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(course: Course) {
            val buttonTextAssign = "show"
            courseItemName.text = course.name
            courseItemPrice.text = course.price
            courseItemDescription.text = course.category

        }
    }

    internal class AppetizersViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val courseItemName: TextView = itemView.appetizerCourseItem_itemName
        private val courseItemPrice: TextView = itemView.appetizerCourseItem_price
        private val courseItemDescription: TextView = itemView.appetizerCourseItem_description
        private val courseItemImage: CardView = itemView.appetizer_item_root

        init {
            courseItemImage.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(course: Course) {
            val buttonTextAssign = "show"
            courseItemName.text = course.name
            courseItemPrice.text = course.price
            courseItemDescription.text = course.category

        }
    }

    internal class MainCoursesViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val courseItemName: TextView = itemView.mainCourseItem_itemName
        private val courseItemPrice: TextView = itemView.mainCourseItem_price
        private val courseItemDescription: TextView = itemView.mainCourseItem_description
        private val courseItemImage: CardView = itemView.mainCourse_itemRoot

        init {
            courseItemImage.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(course: Course) {
            val buttonTextAssign = "show"
            courseItemName.text = course.name
            courseItemPrice.text = course.price
            courseItemDescription.text = course.category

        }
    }

    internal class FishViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val courseItemName: TextView = itemView.fishCourseItem_itemName
        private val courseItemPrice: TextView = itemView.fishCourseItem_price
        private val courseItemDescription: TextView = itemView.fishCourseItem_description
        private val courseItemImage: CardView = itemView.fish_item_root

        init {
            courseItemImage.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(course: Course) {
            val buttonTextAssign = "show"
            courseItemName.text = course.name
            courseItemPrice.text = course.price
            courseItemDescription.text = course.category

        }
    }

    internal class DrinksViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val courseItemName: TextView = itemView.drinksCourseItem_itemName
        private val courseItemPrice: TextView = itemView.drinksCourseItem_price
        private val courseItemDescription: TextView = itemView.drinksCourseItem_description
        private val courseItemImage: CardView = itemView.drinks_item_root

        init {
            courseItemImage.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(course: Course) {
            val buttonTextAssign = "show"
            courseItemName.text = course.name
            courseItemPrice.text = course.price
            courseItemDescription.text = course.category

        }
    }


}

