package scalay.io

import java.io.{File => JFile, FileInputStream, InputStreamReader, BufferedReader}
import java.io.Closeable

/**
 * Created with IntelliJ IDEA.
 * User: mizushima
 * Date: 2012/09/10
 * Time: 6:15
 * To change this template use File | Settings | File Templates.
 */
class FileResource (path: String) {
  private[this] val file: JFile = new JFile(path)

  def isDirectory: Boolean = file.isDirectory()
  def isFile: Boolean = file.isFile()
  def isAbsolute: Boolean = file.isAbsolute()
  def isHidden: Boolean = file.isHidden()

  def writeBytes (bytes: Array[Byte]): Unit = sys.error("not implemented yet")
  def writeBytes (byte: Byte): Unit = writeBytes(Array(byte))
  def write(string: String): Unit = writeBytes(string.getBytes())
  def write(string: String, encoding: String): Unit = writeBytes(string.getBytes(encoding))

  def readBytes(): Array[Byte] = sys.error("not implemented yet")
  def read(): String = new String(readBytes())
  def read(encoding: String): Unit = new String(readBytes(), encoding)

  protected def open[A <: Closeable, B](resource: => A)(block: A=> B): B = try {
    block(resource)
  } finally {
    resource.close()
  }

  override def toString(): String = file.toString()
}

object FileResource {
  def apply (path: String): FileResource = new FileResource(path)
}
