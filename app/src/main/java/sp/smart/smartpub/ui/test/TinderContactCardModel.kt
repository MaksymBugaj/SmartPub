package sp.smart.smartpub.ui.test

import androidx.annotation.ColorInt


data class TinderContactCardModel(
    val name: String,
    val age: Int,
    val description: String,
    @ColorInt val backgroundColor: Int
)