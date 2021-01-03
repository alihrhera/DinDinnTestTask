package ali.hrhera.dindinntestapp.view.viewmodel

import ali.hrhera.dindinntestapp.App
import ali.hrhera.dindinntestapp.data.models.ItemsState
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.data.repository.OrderRepository
import ali.hrhera.dindinntestapp.util.MvRxViewModel
import ali.hrhera.dindinntestapp.util.intfaces.Repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.ViewModelContext
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@Suppress("UNCHECKED_CAST")
class OrderViewModel(state: ItemsState, repo: Repository) :
        MvRxViewModel<ItemsState>(state) {
    private val size :MutableLiveData<List<OneItem>> = repo.getItems() as MutableLiveData<List<OneItem>>
    val orderRepository:OrderRepository = repo as OrderRepository

    val sizeLiveData: LiveData<List<OneItem>> get() = size
    fun removeItem(item: OneItem){
        orderRepository.removeFromOrder(item)
    }


    companion object : MavericksViewModelFactory<OrderViewModel, ItemsState> {
        override fun create(viewModelContext: ViewModelContext, state: ItemsState):
                OrderViewModel {
            val repository = viewModelContext.app<App>().orderRepository
            return OrderViewModel(state, repository)
        }
    }
}