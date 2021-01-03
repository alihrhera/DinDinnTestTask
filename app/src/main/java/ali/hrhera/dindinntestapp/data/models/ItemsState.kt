package ali.hrhera.dindinntestapp.data.models

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

 data class ItemsState ( val listOfItems:Async<List<OneItem>> = Uninitialized ): MavericksState{
}


