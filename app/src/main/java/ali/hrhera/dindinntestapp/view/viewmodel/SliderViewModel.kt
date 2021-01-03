package ali.hrhera.dindinntestapp.view.viewmodel

import ali.hrhera.dindinntestapp.App
import ali.hrhera.dindinntestapp.data.models.ItemsState
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.data.repository.SliderRepository
import ali.hrhera.dindinntestapp.util.MvRxViewModel
import com.airbnb.mvrx.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SliderViewModel(state: ItemsState, sliderRepo: SliderRepository) :
        MvRxViewModel<ItemsState>(state) {
    // val itemList:List<OneItem>

    init {
        withState {

            val observable: Single<List<OneItem>> =
                    sliderRepo.getItems().subscribeOn(Schedulers.io())
            observable.subscribe{ list->setState { copy(listOfItems = Success(list)) }  }
        }
    }


    companion object : MavericksViewModelFactory<SliderViewModel, ItemsState> {
        override fun create(viewModelContext: ViewModelContext, state: ItemsState):
                SliderViewModel {
            val repository = viewModelContext.app<App>().sliderRepository

            return SliderViewModel(state, repository)
        }
    }
}