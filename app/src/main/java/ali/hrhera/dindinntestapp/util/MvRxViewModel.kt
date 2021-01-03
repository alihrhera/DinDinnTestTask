package ali.hrhera.dindinntestapp.util

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

abstract class MvRxViewModel<S : MavericksState>(initialState: S)
    : MavericksViewModel<S>(initialState)