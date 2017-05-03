import android.Keys._

import android.Dependencies.{apklib,aar}

android.Plugin.androidBuildAar
 
name := "maidroidui"

version := "0.0.7"
 
scalaVersion := "2.11.6"

organization := "idv.brianhsu.maidroid.ui"

resolvers += "staging" at "https://oss.sonatype.org/content/groups/staging/"

libraryDependencies ++= Seq(
  aar("net.rdrei.android.viewpagerindicator" % "library" % "2.5.0-SNAPSHOT"),
  "com.android.support" % "support-v4" % "19.1.+"
)

dependencyOverrides += "com.android.support" % "support-v4" % "19.1.+"

scalacOptions := Seq("-feature")

platformTarget in Android := "android-19"

libraryProject := true

run <<= run in Android
 
install <<= install in Android

publishTo := Some(
  Resolver.sftp("bone", "bone.twbbs.org.tw", "site/ROOT/ivy/")
)

