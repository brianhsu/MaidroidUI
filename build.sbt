import android.Keys._

import android.Dependencies.{apklib,aar}

android.Plugin.androidBuildAar
 
name := "maidroidui"

version := "0.0.1"
 
scalaVersion := "2.10.4"

organization := "idv.brianhsu.maidroid.ui"

resolvers += "populov" at "http://dl.bintray.com/populov/maven"

libraryDependencies ++= Seq(
  aar("com.viewpagerindicator" % "library" % "2.4.1"),
  "com.android.support" % "support-v4" % "19.1.+"
)

scalacOptions := Seq("-feature")

platformTarget in Android := "android-19"

libraryProject := true

run <<= run in Android
 
install <<= install in Android
