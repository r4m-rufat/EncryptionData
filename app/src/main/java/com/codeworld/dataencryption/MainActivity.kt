package com.codeworld.dataencryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import javax.crypto.Cipher

class MainActivity : AppCompatActivity() {

    private lateinit var editEncryptMessage: EditText
    private lateinit var textDecryptMessage: TextView

    private val symmetricEncryption by lazy { SymmetricEncryption() }
    private val asymmetricEncryption by lazy { AsymmetricEncryption() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editEncryptMessage = findViewById(R.id.encryptMessage)
        textDecryptMessage = findViewById(R.id.decryptMessage)

        var decryptMessage: ByteArray = byteArrayOf()

        findViewById<Button>(R.id.encrypt).setOnClickListener {

            /* symmetricEncryption.encryptOrDecrypt(
                editEncryptMessage.text.toString().toByteArray(),
                Cipher.ENCRYPT_MODE
            )?.let {
                decryptMessage = it
                textDecryptMessage.text = String(it)
            }
             */

            val runnable = Runnable {
                asymmetricEncryption.encryptMessage(
                    editEncryptMessage.text.toString().toByteArray(),
                )?.let {
                    decryptMessage = it
                    runOnUiThread {
                        textDecryptMessage.text = String(it)
                    }
                }
            }

            Thread(runnable).start()

        }

        findViewById<Button>(R.id.decrypt).setOnClickListener {

            /*symmetricEncryption.encryptOrDecrypt(
                decryptMessage,
                Cipher.DECRYPT_MODE
            )?.let {
                editEncryptMessage.setText(String(it))
            }
             */
            asymmetricEncryption.decryptMessage(
                decryptMessage,
            )?.let {
                editEncryptMessage.setText(String(it))
            }

        }

    }

}
