package ali.hrhera.dindinntestapp.util.intfaces;

import ali.hrhera.dindinntestapp.data.models.OneItem
import io.reactivex.Single

/*
public open class Repository {
      open fun getItems(): Any {return Any()}
}*/
abstract class Repository {

    abstract fun getItems() : Any

     open fun addItemToOrder(item: OneItem){}
}
