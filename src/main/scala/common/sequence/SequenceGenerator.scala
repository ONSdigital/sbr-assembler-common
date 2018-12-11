package common.sequence

import java.lang
import java.net.ConnectException

import org.apache.curator.framework.{CuratorFramework, CuratorFrameworkFactory}
import org.apache.curator.framework.recipes.atomic.{AtomicValue, DistributedAtomicLong}
import org.apache.curator.retry.RetryOneTime
import org.slf4j.LoggerFactory

/**
  * Generates a unique common.sequence number for Hbase/Hive
  *
  * @param hostName             the zookeeper host and port
  * @param resultFormat         the format of the results. The default is 11%07d, which corresponds to the SBR requirement
  * @param path                 the Zookeeper path to store the counter value
  * @param sessionTimeoutSec    session timeout in seconds
  * @param connectionTimeoutSec connection timeout in seconds
  */
class SequenceGenerator(
                         hostName: String,
                         resultFormat: String = "11%07d",
                         path: String = "/ids/enterprise/id",
                         sessionTimeoutSec: Int = 5,
                         connectionTimeoutSec: Int = 5
                       ) {

  private val client: CuratorFramework = CuratorFrameworkFactory.newClient(hostName, sessionTimeoutSec * 1000,
    connectionTimeoutSec * 1000, new RetryOneTime(1))
  private val dal: DistributedAtomicLong = new DistributedAtomicLong(client, path, new RetryOneTime(1))

  private val logger = LoggerFactory.getLogger(classOf[SequenceGenerator])

  logger.info(s"Starting SequenceGenerator: $hostName")

  client.start()
  client.getZookeeperClient.blockUntilConnectedOrTimedOut()

  if (!client.getZookeeperClient.blockUntilConnectedOrTimedOut())
    throw new ConnectException(s"Connection to Zookeeper timed out, host: $hostName")

  def nextSequence: String = nextSequence(1)._1

  def nextSequence(batchSize: Long): (String, String) = {
    val id: AtomicValue[lang.Long] = dal.add(batchSize)

    resultFormat.format(id.preValue + 1) -> resultFormat.format(id.postValue.longValue)
  }

  def currentSequence: String = resultFormat.format(dal.get().postValue().longValue)
}

object SequenceGenerator {
  def fromHost(hostName: String = "localhost:2181", resultFormat: String = "11%07d",
               path: String = "/ids/enterprise/id", sessionTimeoutSec: Int = 5,
               connectionTimeoutSec: Int = 5): SequenceGenerator =
    new SequenceGenerator(hostName, resultFormat, path, sessionTimeoutSec, connectionTimeoutSec)
}
