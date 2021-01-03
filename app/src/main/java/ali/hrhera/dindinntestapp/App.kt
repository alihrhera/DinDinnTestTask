package ali.hrhera.dindinntestapp

import ali.hrhera.dindinntestapp.data.repository.*
import android.app.Application
import com.airbnb.mvrx.Mavericks

class App : Application() {
    val sliderRepository = SliderRepository()
    val drinksRepo = DrinksMenuItemsRepository()
    val sushiRepository = SuchiMenuItemsRepository()
    val pizzaRepository = PizzaMenuItemsRepository()
    val orderRepository = OrderRepository()

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(true)
    }
}