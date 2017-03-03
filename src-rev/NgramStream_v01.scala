package ngrams

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.NotUsed
import scala.concurrent.Future
import scala.util.{Try, Success, Failure}

object NgramStream {

  def main(args: Array[String]): Unit = {
    val arg1: String = if (args.length > 0) args(0) else "3"
    val arg2: String = if (args.length > 1) args(1) else "10"

    // Default value of n to 3 (i.e. tri-gram)
    val nVal = if (Try(arg1.toInt).isSuccess) {
      if (arg1.toInt > 0) arg1.toInt else 3
    } else {
      println("WARNING: Invalid or missing argument for value of n (n-gram)! Resetting to 3 ...")
      3
    }

    // Default numMessages to 10
    val numMessages = if (Try(arg2.toInt).isSuccess) {
      if (arg2.toInt > 0) arg2.toInt else 10
    } else {
      println("WARNING: Invalid or missing argument for numMessages! Resetting to 10 ...")
      10
    }

    implicit val system = ActorSystem("Sys")
    implicit val materializer = ActorMaterializer()

    import system.dispatcher

    val minWordsInText = 8
    val maxWordsInText = 20

    val textSource: Source[String, NotUsed] =
      Source((1 to numMessages).map(_ =>
        TextMessage.genRandText(minWordsInText: Int, maxWordsInText: Int))
      )

    def ngramFlow(n: Int): Flow[String, String, NotUsed] =
      Flow[String].map(text => TextProcessor.genNgrams(text, n))

    val textSink = Sink.foreach[String](println)

    textSource.via(ngramFlow(nVal)).runWith(textSink).
      onComplete {
        case Success(x) =>
          println(s"Success: $x")
          system.terminate()
        case Failure(e) =>
          println(s"Failure: $e")
          system.terminate()
      }

  }
}
