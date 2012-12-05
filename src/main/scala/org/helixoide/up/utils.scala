package org.helixoide.up

import jassh._
import scala.tools.jline.console.ConsoleReader

object utils {
  def readPassword(prompt:String):String = {
    val reader = new ConsoleReader()
    reader.readLine(prompt, '*')
  }

  def readUser():String = {
    val reader = new ConsoleReader()
    reader.readLine("Enter username: ").trim
  }

  def readHosts():Array[String] = {
    val reader = new ConsoleReader()
    val hosts = reader.readLine("Enter ',' seperated hosts: ").split(",")
    hosts.map(str => str.trim)
  }

  def printStatus(hosts:Array[String], results:Array[Boolean]) = {
    val stringResults = results.map(result => {
      result match {
        case true => "has updates"
        case false => "doesn't have update"
      }
    })
    val combined = hosts.zip(stringResults)
    val end = combined.map(host => println(host._1 + " " + host._2))
  }

  def checkUpdates(host:String, user:String, pass:String):Boolean = {
    val status = SSH.once(host, user, pass) {_ executeAndTrimSplit "yum check-update && echo $?"}
    status.last match {
      case "0" => false
      case _ => true
    }
  }
}
