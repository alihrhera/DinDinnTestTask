package ali.hrhera.dindinntestapp.view.viewmodel

import ali.hrhera.dindinntestapp.App
import ali.hrhera.dindinntestapp.data.models.ItemsState
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.util.intfaces.Repository
import ali.hrhera.dindinntestapp.util.MvRxViewModel
import android.util.Log
import com.airbnb.mvrx.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@Suppress("UNCHECKED_CAST")
class DrinksViewModel(state: ItemsState, repo: Repository) :
        MvRxViewModel<ItemsState>(state) {

    init {
        withState {

            val observable: Single<List<OneItem>> =
                    (  repo.getItems() as Single<List<OneItem>>)
            observable.subscribeOn(Schedulers.io()).subscribe{
                list->setState {
                copy(listOfItems = Success(list)) }  }
        }
    }


    fun addToOrder(item: OneItem) {
        Log.e("DrinksViewModel","Click")

        orderRepository?.addItemToOrder(item)

    }


    companion object : MavericksViewModelFactory<DrinksViewModel, ItemsState> {
        override fun create(viewModelContext: ViewModelContext, state: ItemsState):
                DrinksViewModel {
            val repository = viewModelContext.app<App>().drinksRepo

            return DrinksViewModel(state, repository)
        }
    }
}