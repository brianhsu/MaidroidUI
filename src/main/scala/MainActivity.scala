package idv.brianhsu.maidroid.ui

import android.app.Activity
import android.os.Bundle
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater

class MyView(context: Context, attrs: AttributeSet) extends LinearLayout(context, attrs) {
  val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).asInstanceOf[LayoutInflater]
  inflater.inflate(R.layout.my_view, this, true)
}

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class ViewPagerAdapter(views: List[View]) extends PagerAdapter 
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

import TypedResource._

class MainActivity extends Activity with TypedViewHolder
{

  private lazy val viewPager = findView(TR.viewpager)
  private lazy val viewList = createViewList
  private lazy val adapter = new ViewPagerAdapter(viewList)

  private var currentIndex = 0

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    val titleIndicator = findView(TR.titles)
    viewPager.setAdapter(adapter)
    titleIndicator.setViewPager(viewPager)
  }

  def createViewList = {
    val inflater = getLayoutInflater
    val drawables = List(R.drawable.guide1, R.drawable.guide2, R.drawable.guide3)
    drawables.map { guidePic =>
      val view = inflater.inflate(R.layout.item_view, null)
      val image = view.findView(TR.image)
      image.setImageResource(guidePic)
      view
    }
  }

}
