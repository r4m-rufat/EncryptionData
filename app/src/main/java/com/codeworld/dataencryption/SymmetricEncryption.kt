package com.codeworld.dataencryption

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class SymmetricEncryption {

    private var secretKey: SecretKey

    init {

        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256)

        secretKey = keyGenerator.generateKey()

    }

    fun encryptOrDecrypt(message: ByteArray, cipherMode: Int): ByteArray? {

        return try {

            val cipher = Cipher.getInstance("AES")
            cipher.init(cipherMode, secretKey)
            cipher.doFinal(message)

        } catch (ignore: Exception) {
            null
        }

    }

}