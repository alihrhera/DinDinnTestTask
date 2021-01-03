package ali.hrhera.dindinntestapp.data.repository

import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.data.newtwork.AppApiClint
import ali.hrhera.dindinntestapp.util.intfaces.Repository
import io.reactivex.Single

class DrinksMenuItemsRepository : Repository() {


    override fun getItems(): Single<List<OneItem>> {
        return AppApiClint.getInstance().getMenuItem("Drinks")
    }


}