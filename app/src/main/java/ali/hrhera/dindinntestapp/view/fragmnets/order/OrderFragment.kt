package ali.hrhera.dindinntestapp.view.fragmnets.order

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.data.models.HomeTaps
import ali.hrhera.dindinntestapp.databinding.FragmentOrdersBinding
import ali.hrhera.dindinntestapp.view.adapter.HomePageTapAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class OrderFragment : Fragment() {


    private var _binding: FragmentOrdersBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: HomePageTapAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        adapter = HomePageTapAdapter(childFragmentManager)
        let {
            binding.orderViewPager.adapter = adapter

            adapter.setDataList(listOf(HomeTaps(
                    CartFragment(),
                    getString(R.string.Cart)
            ), HomeTaps(
                    InfoFragment(),
                    getString(R.string.Info)

            ), HomeTaps(
                    OrdersFragment(),
                    getString(R.string.Orders)

            )

            ))

            binding.orderTab.setupWithViewPager(binding.orderViewPager, true)
            setupTabView()
        }

        return binding.root
    }

    private fun setupTabView() {
        for (i in 0 until binding.orderTab.tabCount) {
            binding.orderTab.getTabAt(i)?.setCustomView(R.layout.tab_view)
            val tap = binding.orderTab.getTabAt(i)?.customView
            val tabTitle = tap?.findViewById<TextView>(R.id.tab_title)
            tabTitle?.text = adapter.getPageTitle(i)
        }
    }





}