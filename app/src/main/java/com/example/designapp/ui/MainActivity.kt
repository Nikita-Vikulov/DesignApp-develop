package com.example.designapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fxn.OnBubbleClickListener
import com.example.designapp.R
import com.example.designapp.databinding.ActivityMainBinding
import com.example.designapp.ui.home.HomeFragment
import com.example.designapp.ui.lessons.LessonsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val classesFragment = LessonsFragment()
    private var previousFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.fragments_container, homeFragment)
            .add(R.id.fragments_container, classesFragment).hide(classesFragment).commit()
        with(binding) {
            bottomNavigation.addBubbleListener(object :
                OnBubbleClickListener {
                override fun onBubbleClick(id: Int) {
                    if (id == R.id.home_fragment) {
                        openFragment(homeFragment)
                    }
                    if (id == R.id.lessons_fragment) {
                        openFragment(classesFragment)
                    }
                }
            })
        }
    }

    private fun openFragment(fragment: Fragment) {
        if (previousFragment == fragment) return
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .hide(previousFragment).show(fragment)
            .commit()
        previousFragment = fragment
    }

}