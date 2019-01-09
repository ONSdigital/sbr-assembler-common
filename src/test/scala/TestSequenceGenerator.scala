import org.apache.curator.test.TestingServer
import org.scalatest._
import sequence.SequenceGenerator

class TestSequenceGenerator extends FunSuite with Matchers {

  //val zkServer = new TestingServer(2181, true)

  test("Generate next Sequence Number") {
    val lastSequence: Long = SequenceGenerator.currentSequence.toLong + 1
    val nextSequence: Long = SequenceGenerator.nextSequence.toLong
    assert(lastSequence === nextSequence)
    Console.out.println(s"Next Sequence Number: $nextSequence")
  }

  test("Performance figures") {

    val loop: Int = 10000
    val res = timeCall {
      for (i <- 1 to loop) SequenceGenerator.nextSequence
    }
    val seconds = res / 1000000000.0
    val cps = loop / seconds
    Console.out.println(f"Performance Results: Number of calls $loop, Elapsed Time: $seconds%2.2f sec, $cps%2.2f calls/sec")
  }

  def timeCall[R](block: => R): Double = {
    val start = System.nanoTime
    block
    System.nanoTime - start
  }
}
