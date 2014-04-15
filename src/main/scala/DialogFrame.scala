package idv.brianhsu.maidroid.ui

import idv.brianhsu.maidroid.ui.model._

import android.support.v4.view.ViewPager.SimpleOnPageChangeListener

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.LayoutInflater
import android.widget.LinearLayout

import TypedResource._

class DialogFrame(context: Context, attrs: AttributeSet) extends LinearLayout(context, attrs) 
{
  private var adapterHolder: Option[MessageAdapter] = None
  private var messages: Vector[Message] = Vector.empty
  private var spriteCache: Map[DrawableResourceID, Bitmap] = Map.empty

  private lazy val imageView = this.findView(TR.dialogFrameImageView)
  private lazy val viewPager = this.findView(TR.dialogFrameViewPager)
  private lazy val pagerIndicator = this.findView(TR.dialogFrameViewPagerIndicator)
  private lazy val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).
                              asInstanceOf[LayoutInflater]

  private lazy val pageChangeListener = new SimpleOnPageChangeListener() {

    private var previousSpriteID = 0

    override def onPageSelected(position: Int) {
      val spriteDrawableID = messages(position).sprite
      val spriteBitmapHolder = spriteCache.get(spriteDrawableID)
      val isSameSprite = previousSpriteID == spriteDrawableID

      if (!isSameSprite) {
        spriteBitmapHolder.foreach { crossFadeImage }
      }

      previousSpriteID = spriteDrawableID
    }

    private def crossFadeImage(newImage: Bitmap) {
      val animationOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
      val animationIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)

      animationIn.setDuration(250)
      animationOut.setDuration(250)

      animationOut.setAnimationListener(new AnimationListener() {
        override def onAnimationRepeat(animation: Animation) {}
        override def onAnimationStart(animation: Animation) {}
        override def onAnimationEnd(animation: Animation) {
          imageView.setImageBitmap(newImage)
          imageView.startAnimation(animationIn)
        }
      })

      imageView.startAnimation(animationOut)
    }
  }

  private def createViewList(messages: List[Message]) = {

    messages.map { message =>
      val view = inflater.inflate(R.layout.maidroid_ui_dialog_item, null)
      val textView = view.findView(TR.dialogItemTextView)
      textView.setText(message.message)
      view
    }
  }

  private def createSpriteCache(messages: List[Message]): Map[DrawableResourceID, Bitmap] = {
    val drawableIDs = messages.map(_.sprite).toSet
    drawableIDs.map { drawableID =>
      drawableID -> BitmapFactory.decodeResource(context.getResources(), drawableID)
    }.toMap
  }

  def setMessages(messages: List[Message]) {
    this.messages = messages.toVector
    this.spriteCache = createSpriteCache(messages)
    this.adapterHolder = Some(new MessageAdapter(createViewList(messages)))
    this.adapterHolder.foreach { viewPager.setAdapter }
    this.pagerIndicator.setViewPager(viewPager)
    this.pagerIndicator.setOnPageChangeListener(pageChangeListener)

    if (!messages.isEmpty) {
      imageView.setImageBitmap(spriteCache(messages(0).sprite))
    }
  }

  inflater.inflate(R.layout.maidroid_ui_dialog_frame, this, true)
}

