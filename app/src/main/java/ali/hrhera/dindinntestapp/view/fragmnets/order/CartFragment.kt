package ali.hrhera.dindinntestapp.view.fragmnets.order

import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.databinding.FragmentCartBinding
import ali.hrhera.dindinntestapp.util.intfaces.OnItemClick
import ali.hrhera.dindinntestapp.view.MainActivity
import ali.hrhera.dindinntestapp.view.adapter.OrderItemsAdapter
import ali.hrhera.dindinntestapp.view.viewmodel.OrderViewModel
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState

class CartFragment : Fragment(), MavericksView {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val adapter = OrderItemsAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        (activity as MainActivity).hidSlider()

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.orderDetails.layoutManager = LinearLayoutManager(context)
        binding.orderDetails.adapter = adapter

        adapter.setOnDeleteItem(object : OnItemClick {
            override fun onClick(item: Any) {
                model.removeItem(item = item as OneItem)
            }
        })

        return binding.root
    }

    private val model: OrderViewModel by activityViewModel()

    override fun invalidate() = withState(model) {
        model.sizeLiveData.observe(requireActivity(), { list ->
            run {
                adapter.setDataList(list)
                var totalValue = 0f
                for (item in list) {
                    totalValue += item.price
                }




                val strTotal = "<small>value </small><big>$totalValue</big> usd"
                if (Build.VERSION.SDK_INT > 24) {
                    binding.totalValue.text = Html.fromHtml(strTotal, Html.FROM_HTML_MODE_LEGACY)
                    return@run
                }
                binding.totalValue.text = Html.fromHtml(strTotal)


            }

        })

    }

}