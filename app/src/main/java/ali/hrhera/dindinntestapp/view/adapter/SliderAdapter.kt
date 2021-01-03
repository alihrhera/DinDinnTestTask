package ali.hrhera.dindinntestapp.view.adapter

import ali.hrhera.dindinntestapp.R
import ali.hrhera.dindinntestapp.data.models.OneItem
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.*


class SliderAdapter : PagerAdapter() {
    private var listOfDiscounts: List<*> = ArrayList<Any>()

    fun setListOfDiscounts(listOfDiscounts: List<*>?) {
        if (listOfDiscounts != null) {
            this.listOfDiscounts = listOfDiscounts
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listOfDiscounts.size
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(collection.context)
        val view: View = inflater.inflate(R.layout.row_slider, collection, false)
        val image: ImageView = view.findViewById(R.id.slider_image)
        if (listOfDiscounts.isNotEmpty() && listOfDiscounts[0] is OneItem) {
            val item = (listOfDiscounts[position] as OneItem)
            Picasso.get().load(item.imageUrl).centerCrop().fit()
                    .error(R.drawable.temp_food)
                    .into(image)
        }
        collection.addView(view);
        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, view: Any) {
        parent.removeView(view as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return obj === view
    }
}
