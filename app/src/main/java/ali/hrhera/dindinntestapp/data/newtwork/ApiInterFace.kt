package ali.hrhera.dindinntestapp.data.newtwork

import ali.hrhera.dindinntestapp.data.models.OneItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {
    @GET("food.php")
    fun getItems(
            @Query("type") type: String,
            @Query("menuType") menuType: String,
    ): Single<List<OneItem>>


}