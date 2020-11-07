package com.lucas.historygreatests

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lucas.historygreatests.utils.AnalyticsSender
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val bottomNavigationScreensIds = arrayOf(
        R.id.navigation_home,
        R.id.navigation_trending,
        R.id.navigation_library,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        toolbar.setupWithNavController(navController)
        bottom_nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            navController.previousBackStackEntry?.let {
                if(it.destination.label.toString() != getString(R.string.screen_name_main)){
                    AnalyticsSender.trackNavigation(
                        from = it.destination.label.toString(),
                        to = destination.label.toString()
                    )
                }
            }

            //AnalyticsSender.trackNavigation()
            if(bottomNavigationScreensIds.contains(destination.id)) {
                bottom_nav_view.visibility = View.VISIBLE
                setToolbarVisibility(false)

            } else {
                setToolbarVisibility(true)
                bottom_nav_view.visibility = View.GONE
            }
        }
    }

    fun setToolbarVisibility(visible: Boolean) {
        toolbar.visibility = if(visible) View.VISIBLE else View.GONE
    }

}