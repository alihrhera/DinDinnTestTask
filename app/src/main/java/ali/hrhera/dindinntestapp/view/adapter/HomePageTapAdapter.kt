package ali.hrhera.dindinntestapp.view.adapter

import ali.hrhera.dindinntestapp.data.models.HomeTaps
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePageTapAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var listOfFragment: List<HomeTaps> = ArrayList()

    fun setDataList(listOfFragment: List<HomeTaps>) {
        this.listOfFragment = listOfFragment;
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return listOfFragment[position].fragment
    }

    override fun getCount() = listOfFragment.size

    override fun getPageTitle(position: Int): CharSequence {
        return listOfFragment[position].title
    }

}