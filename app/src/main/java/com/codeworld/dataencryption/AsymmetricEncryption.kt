package com.codeworld.dataencryption

import java.security.Key
import java.security.KeyPairGenerator
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

class AsymmetricEncryption {

    private var privateKey: Key
    private var publicKey: Key

    init {

        val keyGenerator = KeyPairGenerator.getInstance("RSA")
        keyGenerator.initialize(2048)
        val kp = keyGenerator.genKeyPair()
        privateKey = kp.private
        publicKey = kp.public

    }

    fun encryptMessage(message: ByteArray): ByteArray? {

        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            cipher.doFinal(message)
        }catch (ignore: Exception){
            null
        }

    }

    fun decryptMessage(message: ByteArray): ByteArray? {

        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.DECRYPT_MODE, privateKey)
            cipher.doFinal(message)
        }catch (ignore: Exception){
            null
        }

    }

}