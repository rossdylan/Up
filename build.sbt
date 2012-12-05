import AssemblyKeys._

assemblySettings

name := "Up"

version := "1.0"

scalaVersion := "2.9.2"

resolvers += "JAnalyse Repository" at "http://www.janalyse.fr/repository/"

libraryDependencies += "fr.janalyse" %% "janalyse-ssh" % "0.9.5-b3" % "compile"

libraryDependencies += "org.scala-lang" % "jline" % "2.9.2"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
	{
		case PathList("org", "fusesource", xs @ _*) => MergeStrategy.first
		case x => old(x)
	}
}
