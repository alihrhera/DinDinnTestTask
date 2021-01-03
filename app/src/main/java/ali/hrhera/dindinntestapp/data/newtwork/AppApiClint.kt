package ali.hrhera.dindinntestapp.data.newtwork

import ali.hrhera.dindinntestapp.data.models.OneItem
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppApiClint private constructor(){
    companion object {
        private var instance: AppApiClint? = null
        fun getInstance(): AppApiClint {
            if (instance == null) {
               instance = AppApiClint()
            }
            return instance!!
        }
    }

    private val baseUrl = "https://era-apps.com/ali/"
    private val apiInterFace: ApiInterFace
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiInterFace = retrofit.create(ApiInterFace::class.java)

    }
    fun getMenuItem(menuItemType: String): Single<List<OneItem>> {
        return apiInterFace.getItems("menu", menuItemType)
    }

    fun getSliderItem(): Single<List<OneItem>> {
        return apiInterFace.getItems("slider", "")
    }

}