package idv.brianhsu.maidroid.ui

import android.app.Activity
import android.os.Bundle
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import TypedResource._


class MyView(context: Context, attrs: AttributeSet) extends LinearLayout(context, attrs) {
  val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).asInstanceOf[LayoutInflater]
  inflater.inflate(R.layout.my_view, this, true)
}

import TypedResource._

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

class MainActivity extends Activity with TypedViewHolder
{
  private lazy val viewPager = findView(TR.viewpager)
  private lazy val viewList = createViewList
  private lazy val adapter = new ViewPagerAdapter(viewList)

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    viewPager.setAdapter(adapter)
    val pageIndicator = findView(TR.pageIndicator)
    pageIndicator.setViewPager(viewPager)
  }

  def createViewList = {
    val inflater = getLayoutInflater
    val drawables = List("Hello World", "歡迎光臨", 
      """|這是簡單的一個測試 1 AFda, daals,,這是測試，這是簡單的一個測試 2，中文怎麼栓啊。希望可以正常斷行，這是簡單的一個測試，這是簡單的一個測試 5這是簡單的一個測試 15""".stripMargin, "QQQQ", "DDD", "FFF"
)

    drawables.map { guidePic =>
      val view = inflater.inflate(R.layout.item_view, null)
      val message = view.findView(TR.messageContent)
      message.setText(guidePic)
      view
    }
  }


}
