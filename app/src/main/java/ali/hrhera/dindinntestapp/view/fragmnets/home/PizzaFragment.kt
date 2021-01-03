package ali.hrhera.dindinntestapp.view.fragmnets.home

import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.databinding.FragmentRecyclerFragmentBinding
import ali.hrhera.dindinntestapp.util.intfaces.OnItemClick
import ali.hrhera.dindinntestapp.view.adapter.MenuItemsAdapter
import ali.hrhera.dindinntestapp.view.adapter.OrderItemsAdapter
import ali.hrhera.dindinntestapp.view.viewmodel.PizzaViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState


class PizzaFragment : Fragment(), MavericksView {
    private val model: PizzaViewModel by activityViewModel()

    override fun invalidate() = withState(model) { state ->
        adapter.setDataList(state.listOfItems())

    }


    private val adapter = MenuItemsAdapter()
    private var _binding: FragmentRecyclerFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentRecyclerFragmentBinding.inflate(inflater, container, false)

        binding.itemsRecycler.layoutManager = LinearLayoutManager(context)
        binding.itemsRecycler.adapter = adapter

        adapter.setOnItemClick(object : OnItemClick {
            override fun onClick(item: Any) {

                if (item is OneItem) {
                    model.addToOrder(item)
                }

            }
        })


        return binding.root
    }

}