package utilsCalculations

import java.security.MessageDigest
import java.io.FileInputStream
import java.io.InputStream

class ChecksumMdFive {

	def md5SumString(fis : String) : String = {
			
			val md5 : String = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis)
		    return md5
	}
}