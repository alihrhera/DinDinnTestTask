package ali.hrhera.dindinntestapp.data.repository

import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.util.intfaces.Repository
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData


class OrderRepository : Repository() {

    private val orderList: MutableList<OneItem> = ArrayList()
    private val mutableLiveDataOrderList = MutableLiveData<List<OneItem>>()
    override fun getItems()=mutableLiveDataOrderList

    @SuppressLint("CheckResult")
    override fun addItemToOrder(item: OneItem) {
        orderList.add(item)
        mutableLiveDataOrderList.postValue(orderList)
    }

    fun removeFromOrder(item: OneItem){
        orderList.remove(item)
        mutableLiveDataOrderList.postValue(orderList)
    }

}