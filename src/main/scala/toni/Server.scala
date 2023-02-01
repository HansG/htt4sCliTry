//> using scala "3.2.1"
//> using lib "org.http4s::http4s-ember-server::0.23.18"
//> using lib "org.http4s::http4s-dsl::0.23.18"
//> using lib "com.monovore::decline-effect::2.4.1"
//> using lib "ch.qos.logback:logback-classic:1.4.5"


package toni


import cats.effect.{ExitCode, IO}
import cats.syntax.all.*
import com.monovore.decline.Opts
import com.monovore.decline.effect.CommandIOApp
import org.http4s.Uri

//https://toniogela.dev/http4s-on-fly-io/
object Server extends CommandIOApp("helloServer", "Greets you in HTML") {

  val titleOpt: Opts[String] =
    Opts.env[String]("TITLE", "Page title").withDefault("Hello")

  val baseUrlOpt: Opts[Uri] = Opts
    .env[String]("BASE_URL", "The base url").withDefault("http://localhost:8080/hello/HG")
    .mapValidated(
      Uri
        .fromString(_)
        .leftMap(_.message)
        .ensure("base url must be absolute")(_.path.addEndsWithSlash.absolute)
        .map(uri => uri.withPath(uri.path.dropEndsWithSlash))
        .toValidatedNel
    )

  def main: Opts[IO[ExitCode]] = (baseUrlOpt, titleOpt).mapN((baseUrl, title) =>
    IO.println(s"$baseUrl $title").as(ExitCode.Success)
  )
}