package ali.hrhera.dindinntestapp.view.adapter

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.databinding.RowOneOrderItemBinding
import ali.hrhera.dindinntestapp.util.intfaces.OnItemClick
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class OrderItemsAdapter : RecyclerView.Adapter<OrderItemViewHolder>() {
    private var dataList: List<OneItem> = ArrayList()
    private var onDeleteItem: OnItemClick? = null

    fun setOnDeleteItem(onItemClick: OnItemClick) {
        this.onDeleteItem = onItemClick
    }

    fun setDataList(dataList: List<OneItem>?) {

        if (dataList != null) this.dataList = dataList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        return OrderItemViewHolder(RowOneOrderItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))

    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.deleteItem.setOnClickListener {
            if (onDeleteItem != null) {
                onDeleteItem?.onClick(dataList[position])
            }
        }
    }

    override fun getItemCount() = dataList.size


}

class OrderItemViewHolder(private val item: RowOneOrderItemBinding) : RecyclerView.ViewHolder(item.root) {
    val deleteItem = item.deleteItem
    fun bind(data: OneItem) {
        item.itemName.text = data.name
        Picasso.get().load(data.imageUrl)
                .error(R.drawable.temp_food)
                .centerCrop()
                .fit().into(item.itemImage)
    }
}




