val Http4sVersion = "0.23.18"

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "run",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.2.1",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-ember-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "com.monovore" %% "decline-effect" % "2.4.1",
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
      "ch.qos.logback" % "logback-classic" % "1.4.5",
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
