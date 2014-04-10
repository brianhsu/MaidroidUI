import android.Keys._

android.Plugin.androidBuild
 
name := "MaidroidUI"

version := "0.0.1"
 
scalaVersion := "2.10.4"

organization := "idv.brianhsu.maidroid.ui"

scalacOptions := Seq("-feature")

platformTarget in Android := "android-19"

proguardScala in Android := true
 
run <<= run in Android
 
install <<= install in Android

