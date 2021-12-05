package com.example.icraveviolence

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.dolatkia.animatedThemeManager.ThemeManager
import com.example.icraveviolence.databinding.ActivityMainBinding

///https://androidrepo.com/repo/imandolatkia-Android-Animated-Theme-Manager#releases
class MainActivity : ThemeActivity()  {
    private lateinit var binder: ActivityMainBinding;
    override fun getStartTheme(): AppTheme {
        return LightTheme()    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binder = ActivityMainBinding.inflate(getLayoutInflater())

        ThemeManager.instance.getCurrentLiveTheme().observe(this) {
            Toast.makeText(this, "on Theme changed to ${it.id()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun syncTheme(appTheme: AppTheme) {
// change ui colors with new appThem here

        val myAppTheme = appTheme as MyAppTheme
        // set background color

        binder.root.setBackgroundColor(myAppTheme.firstActivityBackgroundColor(this))

        //set text color
       // binder.text.setTextColor(myAppTheme.activityTextColor(this))

        // set icons color
        //binder.share.setColorFilter(myAppTheme.firstActivityIconColor(this))
       // binder.gift.setColorFilter(myAppTheme.firstActivityIconColor(this))
    }
}