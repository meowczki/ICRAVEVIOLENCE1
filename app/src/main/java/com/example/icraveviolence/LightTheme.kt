package com.example.icraveviolence

import android.content.Context
import androidx.core.content.ContextCompat

class LightTheme : MyAppTheme {

    override fun id(): Int { // set unique iD for each theme
        return 0
    }

    override fun firstActivityBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.background_light)
    }

    override fun firstActivityTextColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.text_light)
    }

    override fun firstActivityIconColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.icon_light)
    }


}

class NightTheme : MyAppTheme {
    override fun firstActivityBackgroundColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun firstActivityTextColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun firstActivityIconColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun id(): Int {
        TODO("Not yet implemented")
    }
}
class PinkTheme : MyAppTheme {
    override fun firstActivityBackgroundColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun firstActivityTextColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun firstActivityIconColor(context: Context): Int {
        TODO("Not yet implemented")
    }

    override fun id(): Int {
        TODO("Not yet implemented")
    }
}