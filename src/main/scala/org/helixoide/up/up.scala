package org.helixoide.up
import org.helixoide.up.utils._

object Up {
  def main(args: Array[String]) = {
    val hosts:Array[String] = readHosts()
    val user:String = readUser()
    val pass:String = readPassword("Enter Password: ")

    val status:Array[Boolean] = hosts.par.map(checkUpdates(_:String, user, pass)).toArray
    printStatus(hosts,status)
  }
}
