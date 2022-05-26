package com.example.teamdraw

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.UnsupportedEncodingException
import java.util.*
import javax.mail.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class GmailSender : Authenticator() {
    // 보내는 사람 이메일과 비밀번호
    private val fromEmail = "su86016009@gmail.com"
    private val password = "osanbcnfliygarxt"
    var authenticationCode : String = ""

    override fun getPasswordAuthentication(): PasswordAuthentication {
        Log.d("get authentication ", "$fromEmail $password")
        return PasswordAuthentication(fromEmail, password)
    }

    init {
        authenticationCode = makeAuthenticationCode()
    }

    // 메일 보내기
    fun sendEmail(toEmail: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val props = Properties()
            props.setProperty("mail.transport.protocol", "smtp")
            props.setProperty("mail.host", "smtp.gmail.com")
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.port"] = "465"
            props["mail.smtp.socketFactory.port"] = "465"
            props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            props["mail.smtp.socketFactory.fallback"] = "false"
            props.setProperty("mail.smtp.quitwait", "false")

            val session = Session.getDefaultInstance(props, this@GmailSender)

            val message = MimeMessage(session)
            message.setFrom(InternetAddress(fromEmail)) // 보내는 사람 설정
            message.setRecipient(Message.RecipientType.TO, InternetAddress(toEmail))    // 받는 사람 설정
            message.subject = "TeamDRAW Authentication Code" // 이메일 제목
            message.setText("아래 코드를 입력해주세요 \n $authenticationCode") // 이메일 내용

            // 전송
            Transport.send(message)
        }
    }

    private fun makeAuthenticationCode(): String {
        val str = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        var code = ""
        for (i in 1..6) code += str[(Math.random() * str.size).toInt()]
        return code
    }

}