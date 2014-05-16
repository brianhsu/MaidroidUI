package idv.brianhsu.maidroid.ui.util

import scala.concurrent._
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.LinkedBlockingQueue

import android.app.Activity

object AsyncUI {
  implicit val exec = ExecutionContext.fromExecutor(
    new ThreadPoolExecutor(100, 100, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue[Runnable])
  )

  implicit class ActivityUIFuture(activity: Activity) {
    def runOnUIThread(callback: => Any) {
      activity.runOnUiThread(
        new Runnable() {
          override def run() { callback }
        }
      )
    }
  }

  implicit class UIFuture[T](future: Future[T]) {

    type FailureCallback = PartialFunction[Throwable, Any]

    def onFailureInUI(callback: FailureCallback)(implicit activity: Activity) = {
      future.onFailure {
        case e: Throwable => 
          android.util.Log.d("MaidroidUI", "====> onFailureInUI.error:" + e.getMessage, e)
          activity.runOnUiThread(new Runnable() {
            override def run() {
              callback(e)
            }
          })
      }
    }

    def onSuccessInUI(callback: T => Any)(implicit activity: Activity) {
      future.onSuccess { 
        case result => 
          activity.runOnUiThread(new Runnable() {
            override def run() {
              callback(result)
            }
          })
      }
    }
  }

}

