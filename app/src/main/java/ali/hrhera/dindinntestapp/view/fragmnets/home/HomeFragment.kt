package ali.hrhera.dindinntestapp.view.fragmnets.home

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.data.models.HomeTaps
import ali.hrhera.dindinntestapp.databinding.FragmentHomeBinding
import ali.hrhera.dindinntestapp.view.MainActivity
import ali.hrhera.dindinntestapp.view.adapter.HomePageTapAdapter
import ali.hrhera.dindinntestapp.view.viewmodel.OrderViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState


class HomeFragment : Fragment(), MavericksView {


    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: HomePageTapAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as MainActivity).showSlider()
        adapter = HomePageTapAdapter(childFragmentManager)
        let {
            binding.mainViewPager.adapter = adapter

            adapter.setDataList(listOf(HomeTaps(
                    PizzaFragment(),
                    getString(R.string.pizza)
            ), HomeTaps(
                    SushiFragment(),
                    getString(R.string.sushi)
            ), HomeTaps(
                    DrinksFragment(),
                    getString(R.string.drinks)
            )

            ))

            binding.mainTab.setupWithViewPager(binding.mainViewPager, true)
            setupTabView()


        }

        return binding.root
    }

    private fun setupTabView() {
        for (i in 0 until binding.mainTab.tabCount) {
            binding.mainTab.getTabAt(i)?.setCustomView(R.layout.tab_view)
            val tap = binding.mainTab.getTabAt(i)?.customView
            val tabTitle = tap?.findViewById<TextView>(R.id.tab_title)
            tabTitle?.text = adapter.getPageTitle(i)
        }
    }

    private val model: OrderViewModel by activityViewModel()

    override fun invalidate() = withState(model) {


        model.sizeLiveData.observe(requireActivity(), { list ->

            run {
                (activity as MainActivity).setFabCounter(list.size)
            }
        })

    }


}