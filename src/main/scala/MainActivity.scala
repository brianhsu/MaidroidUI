package idv.brianhsu.maidroid.ui

import idv.brianhsu.maidroid.ui.model._

import android.app.Activity
import android.os.Bundle

class MainActivity extends Activity with TypedViewHolder
{
  private lazy val dialogFrame = findView(TR.dialogFrame)

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    dialogFrame.setMessages(
      Message(MaidMaro.Half.Happy, "QQQQQ") :: 
      Message(MaidMaro.Half.Angry, "SSSS") :: 
      Message(MaidMaro.Half.Angry, "SSSS") :: 
      Message(MaidMaro.Half.Happy, "SSSS") :: 
      Message(MaidMaro.Half.Angry, "SSSS") :: 
      Message(MaidMaro.Half.Panic, "SSSS") :: 
      Message(MaidMaro.Half.Normal, "SSSS") :: 
      Nil)
  }



}
