package com.example.mymail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val header = navView.getHeaderView(0)
        val botonMenu: ImageView = findViewById(R.id.AbrirMenu)

        //Poner pantalla default
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Home())
            .commit()

        botonMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START) // abre el menú lateral
        }

        navView.setNavigationItemSelectedListener { item ->
            item.isChecked = true

            drawerLayout.closeDrawer(GravityCompat.START)

            when (item.itemId) {
                R.id.Home -> {
                    cambiarFragment(Home())
                }
                R.id.Gallery -> {
                    cambiarFragment(Gallery())
                }
                R.id.Slideshow -> {
                    cambiarFragment(Slideshow())
                }
            }
            true  // importante, indica que el click fue manejado
        }
    }

    // Función para cambiar fragment desde Activity
    fun cambiarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}