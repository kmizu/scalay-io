package scalay.io

import java.io.{File => JFile, _}


/**
 * @author Kota Mizushima
 */
class FileResource (path: String) {
  private[this] val file: JFile = new JFile(path)

  def isDirectory: Boolean = file.isDirectory()
  def isFile: Boolean = file.isFile()
  def isAbsolute: Boolean = file.isAbsolute()
  def isHidden: Boolean = file.isHidden()

  def writeBytes (bytes: Array[Byte]): Unit = {
    open(new BufferedOutputStream(new FileOutputStream(file))){stream =>
      stream.write(bytes)
      stream.flush()
    }
  }

  def writeBytes (byte: Byte): Unit = writeBytes(Array(byte))
  def write(string: String): Unit = writeBytes(string.getBytes())
  def write(string: String, encoding: String): Unit = writeBytes(string.getBytes(encoding))

  def readBytes(): Array[Byte] = {
    open(new BufferedInputStream(new FileInputStream(file))){stream =>
      Iterator.continually(stream.read()).takeWhile(_ != -1).map(_.toByte).toArray
    }
  }
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
