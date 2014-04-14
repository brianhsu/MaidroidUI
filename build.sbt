import android.Keys._

import android.Dependencies.{apklib,aar}

android.Plugin.androidBuild
 
name := "MaidroidUI"

version := "0.0.1"
 
scalaVersion := "2.10.4"

organization := "idv.brianhsu.maidroid.ui"

libraryDependencies += "com.viewpagerindicator" % "library" % "2.4.1"

scalacOptions := Seq("-feature")

platformTarget in Android := "android-19"

proguardScala in Android := true
 
run <<= run in Android
 
install <<= install in Android

(apklibs in Android) <<= (apklibs in Android) map { result =>
  println("Delete duplicate JAR file")
  new java.io.File("target/apklibs/com.viewpagerindicator-library-2.4.1/libs/android-support-v4.jar").delete()
  result
}

