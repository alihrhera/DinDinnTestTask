package ali.hrhera.dindinntestapp.view.fragmnets.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.view.adapter.SliderAdapter
import ali.hrhera.dindinntestapp.view.viewmodel.SliderViewModel
import androidx.viewpager.widget.ViewPager
import com.airbnb.mvrx.MavericksView
import com.google.android.material.tabs.TabLayout
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState


class SliderFragment : Fragment(), MavericksView {
    val adapter = SliderAdapter()
    val  model :SliderViewModel by activityViewModel ()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_slider, container, false)
        val pager = root.findViewById<ViewPager>(R.id.top_slider)
        pager.adapter = adapter
        val tabLayout = root.findViewById<TabLayout>(R.id.indicator_points)
        tabLayout.setupWithViewPager(pager, true)
        return root
    }

    override fun invalidate() = withState(model) { state ->
            adapter.setListOfDiscounts(state.listOfItems.invoke())
    }


}