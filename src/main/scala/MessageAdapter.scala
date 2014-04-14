package idv.brianhsu.maidroid.ui

import android.support.v4.view.PagerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private class MessageAdapter(views: List[View]) extends PagerAdapter 
{
  override def getCount = views.size
  override def instantiateItem(container: ViewGroup, position: Int): Object = {
    container.addView(views(position))
    views(position)
  }
  override def isViewFromObject(view: View, keyObject: Any): Boolean = view == keyObject
  override def destroyItem(container: ViewGroup, position: Int, keyObject: Any) {
    container.removeView(views(position))
  }
}

