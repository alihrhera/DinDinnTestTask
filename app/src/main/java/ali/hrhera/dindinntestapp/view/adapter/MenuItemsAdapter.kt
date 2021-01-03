package ali.hrhera.dindinntestapp.view.adapter

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.data.models.OneItem
import ali.hrhera.dindinntestapp.databinding.RowOneMenuItemBinding
import ali.hrhera.dindinntestapp.util.intfaces.OnItemClick
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MenuItemsAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private var dataList: List<OneItem> = ArrayList()
    private var onItemClick: OnItemClick? = null

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    fun setDataList(dataList: List<OneItem>?) {

        if (dataList != null) this.dataList = dataList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RowOneMenuItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.orderButton.setOnClickListener {
            if (onItemClick != null) {
                onItemClick?.onClick(dataList[position])
            }
        }
    }

    override fun getItemCount() = dataList.size


}

class ItemViewHolder(private val item: RowOneMenuItemBinding) : RecyclerView.ViewHolder(item.root) {
    val orderButton = item.orderItem
    fun bind(data: OneItem) {
        item.itemName.text = data.name
        item.itemSize.text = data.size
        item.itemDescribe.text = data.description
        item.orderItem.text = (data.price.toString()+" usd")
        Picasso.get().load(data.imageUrl)
                .error(R.drawable.temp_food)
                .centerCrop().fit()
                .into(item.itemImage)

    }
}




