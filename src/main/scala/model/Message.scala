package idv.brianhsu.maidroid.ui.model

import idv.brianhsu.maidroid.ui.R

object MaidMaro {
  val Normal = R.drawable.maro_normal
  val Smile = R.drawable.maro_smile
  val Panic = R.drawable.maro_panic
  val Angry = R.drawable.maro_angry
  val Happy = R.drawable.maro_happy

  object Half {
    val Normal = R.drawable.maro_normal_half
    val Smile = R.drawable.maro_smile_half
    val Panic = R.drawable.maro_panic_half
    val Angry = R.drawable.maro_angry_half
    val Happy = R.drawable.maro_happy_half
  }
}

case class Message(sprite: DrawableResourceID, message: String, voice: Option[VoiceResourceID])

object Message {
  def apply(sprite: DrawableResourceID, message: String) = new Message(sprite, message, None)
}




