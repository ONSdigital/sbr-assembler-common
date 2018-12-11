import org.apache.curator.test.TestingServer
import org.scalatest._
import common.sequence.SequenceGenerator

class TestSequenceGenerator extends FunSuite with Matchers {

  val zkServer = new TestingServer(2181, true)

  test("Generate next Sequence Number") {
    val hostName = "localhost:2181"
    val service: SequenceGenerator = SequenceGenerator.fromHost(hostName = hostName, sessionTimeoutSec = 1, connectionTimeoutSec = 1)
    val lastSequence: Long = service.currentSequence.toLong + 1
    val nextSequence: Long = service.nextSequence.toLong
    assert(lastSequence === nextSequence)
    Console.out.println(s"Next Sequence Number: $nextSequence")
  }


  test("Performance figures") {
    val service: SequenceGenerator = SequenceGenerator.fromHost()
    testSpeed(service, 15000)
  }

  def testSpeed(service: SequenceGenerator, loop: Int = 10000): Unit = {
    val res = timeCall {
      for (i <- 1 to loop) service.nextSequence
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
