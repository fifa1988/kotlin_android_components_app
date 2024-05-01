package com.example.app.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_home -> replaceFragment(HomeFragment())
                R.id.item_account -> replaceFragment(AccountFragment())
                R.id.item_search -> replaceFragment(SearchFragment())
//                R.id.item_logout -> replaceFragment(LogoutFragment())
                R.id.item_logout -> finish()
                else -> {
                    replaceFragment(HomeFragment())
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager= supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}