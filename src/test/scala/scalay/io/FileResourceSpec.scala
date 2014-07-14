package scalay.io

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author Kota Mizushima
 */
class FileResourceSpec extends FlatSpec with Matchers {
  "A FileResource" should "create new file" in {
    FileResource.newTempFile("foo", "bar") should not be(None)
  }
}
