package ali.hrhera.dindinntestapp.view

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.databinding.ActivityMainBinding
import ali.hrhera.dindinntestapp.view.fragmnets.home.HomeFragment
import ali.hrhera.dindinntestapp.view.fragmnets.home.SliderFragment
import ali.hrhera.dindinntestapp.view.fragmnets.order.OrderFragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.orderSize.visibility = View.GONE

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.collapsingToolbarLayout.setContentScrimColor(Color.TRANSPARENT)
        binding.fab.setOnClickListener {
            attachHomeFragment(OrderFragment())

        }


        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                binding.sliderFragmentContainer.visibility = View.GONE
                return@OnOffsetChangedListener
            }
            binding.sliderFragmentContainer.visibility = View.VISIBLE

        })


        attachHomeFragment(HomeFragment())
        attachSliderFragment()


    }


    private fun attachHomeFragment(fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
    }

    private fun attachSliderFragment() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.slider_fragment_container, SliderFragment()).commit()
    }


    fun setFabCounter(countOrder: Int) {
        binding.orderSize.visibility = View.GONE
        if (countOrder > 0) {
            binding.orderSize.visibility = View.VISIBLE
            binding.orderSize.text = countOrder.toString()

        }
    }


    fun showSlider() {
        if (isChange) {
            val appBarLayoutHeight = resources.getDimension(R.dimen.slider_height).toInt()
            binding.appBar.setExpanded(true)
            val params: CoordinatorLayout.LayoutParams = binding.appBar.layoutParams as CoordinatorLayout.LayoutParams
            params.height = appBarLayoutHeight
            binding.appBar.layoutParams = params
            attachSliderFragment()
            isChange = false
        }
    }

    private var isChange = false
    fun hidSlider() {
        isChange = true
        val params: CoordinatorLayout.LayoutParams = binding.appBar.layoutParams as CoordinatorLayout.LayoutParams
        params.height = 0
        binding.appBar.layoutParams = params
        binding.appBar.setExpanded(false)
    }


    override fun onBackPressed() {
        if (supportFragmentManager.popBackStackImmediate()) {
            return
        }

        super.onBackPressed()
    }

}

