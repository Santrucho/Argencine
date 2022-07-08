package com.santrucho.argencine


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.santrucho.argencine.databinding.ActivityMainBinding
import com.santrucho.argencine.ui.FavouriteFragment
import com.santrucho.argencine.ui.MoviesFragment


class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieFragment = MoviesFragment()
        val favFragment = FavouriteFragment()

        setCurrentFragment(movieFragment)

        binding.bottomNav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.myMovies -> setCurrentFragment(movieFragment)
                R.id.myFavs -> setCurrentFragment(favFragment)
            }
        }
    }

    private fun setCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }
}



