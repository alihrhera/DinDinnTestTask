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

class SuchiViewModel(state: ItemsState, repository: Repository) :
        MvRxViewModel<ItemsState>(state) {

    init {
        withState {
            val observable: Single<List<OneItem>> =
                    (  repository.getItems() as Single<List<OneItem>>)
            observable.subscribeOn(Schedulers.io()).subscribe{list->setState { copy(listOfItems = Success(list)) }  }
        }
    }


    fun addToOrder(item: OneItem) {
        orderRepository?.addItemToOrder(item)
        Log.e("SuchiViewModel","Click")

    }



    companion object : MavericksViewModelFactory<SuchiViewModel, ItemsState> {
        override fun create(viewModelContext: ViewModelContext, state: ItemsState):
                SuchiViewModel {
            val repository = viewModelContext.app<App>().sushiRepository

            return SuchiViewModel(state, repository)
        }
    }
}