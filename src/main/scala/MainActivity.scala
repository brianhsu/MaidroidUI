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
import idv.brianhsu.maidroid.ui.model._


class DialogFrame(context: Context, attrs: AttributeSet) extends LinearLayout(context, attrs) {

  private lazy val viewPager = this.findView(TR.dialogFrameViewPager)
  private lazy val pagerIndicator = this.findView(TR.dialogFrameViewPagerIndicator)
  private lazy val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).
                              asInstanceOf[LayoutInflater]

  var adapterHolder: Option[MessageAdapter] = None
  var messages: Vector[Message] = Vector.empty

  private def createViewList(messages: List[Message]) = {

    messages.map { message =>
      val view = inflater.inflate(R.layout.dialog_item, null)
      val textView = view.findView(TR.dialogItemTextView)
      textView.setText(message.message)
      view
    }
  }

  def setMessages(messages: List[Message]) {
    this.messages = messages.toVector
    this.adapterHolder = Some(new MessageAdapter(createViewList(messages)))
    this.adapterHolder.foreach { viewPager.setAdapter }
    this.pagerIndicator.setViewPager(this.viewPager)
  }

  inflater.inflate(R.layout.dialog_frame, this, true)
}

import TypedResource._

class MessageAdapter(views: List[View]) extends PagerAdapter 
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
  private lazy val dialogFrame = findView(TR.dialogFrame)

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    dialogFrame.setMessages(Message(MaidMaro.Half.Happy, "QQQQQ") :: Message(MaidMaro.Half.Angry, "SSSS") :: Nil)
  }



}
