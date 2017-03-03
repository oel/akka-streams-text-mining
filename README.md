## Akka Streams Text Mining

---

This is a Scala-based application for extracting n-grams from a stream of text messages using Akka Streams.  For a quick walk-through of the application, please visit [Genuine Blog](http://blog.genuine.com/2017/03/akka-streams-text-mining/).

##### To run the application with sbt, simply proceed as follows:

> Git-clone the repo to a local disk
>
> Open up a command line terminal
>
> Go to the project root subdirectory (e.g. ~/apps/akka-streams-text-mining/)
>
> Run the application as follows:
>> /path/to/sbt "runMain ngrams.NgramStream [n] [numMessages]"
>>
>> e.g. The following command will extract tri-grams from 100 generated text messages:
>> sbt "runMain ngrams.NgramStream 3 100"

---
