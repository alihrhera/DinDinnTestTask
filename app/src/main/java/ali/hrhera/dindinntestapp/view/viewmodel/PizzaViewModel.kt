package ali.hrhera.dindinntestapp.view.viewmodel

import ali.hrhera.dindinntestapp.App
import ali.hrhera.dindinntestapp.data.models.ItemsState
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.data.repository.OrderRepository
import ali.hrhera.dindinntestapp.util.intfaces.Repository
import ali.hrhera.dindinntestapp.util.MvRxViewModel
import android.util.Log
import com.airbnb.mvrx.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

var orderRepository: OrderRepository? = null

@Suppress("UNCHECKED_CAST")
class PizzaViewModel(state: ItemsState, repo: Repository) :
        MvRxViewModel<ItemsState>(state){
    init {
        withState {

            val observable:Single<List<OneItem>> =
                    (  repo.getItems() as Single<List<OneItem>>)
            observable.subscribeOn(Schedulers.io()).subscribe{list->setState { copy(listOfItems = Success(list)) }  }
        }
    }


    fun addToOrder(item: OneItem) {
        orderRepository?.addItemToOrder(item)
        Log.e("PizzaViewModel","Click")

    }

    companion object : MavericksViewModelFactory<PizzaViewModel, ItemsState> {
        override fun create(viewModelContext: ViewModelContext, state: ItemsState):
                PizzaViewModel {
            val repository = viewModelContext.app<App>().pizzaRepository
            orderRepository = viewModelContext.app<App>().orderRepository

            return PizzaViewModel(state, repository)
        }
    }









}