package com.thuan.vietlott535sniper
import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.jsoup.Jsoup
import kotlin.concurrent.thread

data class Draw(val date: String, val main: List<Int>, val special: Int)

class MainActivity: AppCompatActivity() {
    private val predicted = mutableListOf<List<Int>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        val tvPredict = findViewById<TextView>(R.id.tvPredict)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        val btnSms = findViewById<Button>(R.id.btnSms)

        fun genPredict(): List<Int> {
            // simple demo: 5 số + đặc biệt
            return listOf(9,14,20,31,35,12)
        }

        btnRefresh.setOnClickListener {
            tvPredict.text = "Đang cào lotto-8.com..."
            thread {
                try {
                    val url = "https://www.lotto-8.com/Vietnam/listltoVM35.asp?indexpage=1"
                    val doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get()
                    val text = doc.text().take(200)
                    runOnUiThread { tvPredict.text = "Đã cào xong: ${genPredict().joinToString(" ")}" }
                } catch (e: Exception) {
                    runOnUiThread { tvPredict.text = "Dự đoán: 09 14 20 31 35 - 12 (offline)" }
                }
            }
        }
        btnSms.setOnClickListener {
            try {
                val smsManager = android.telephony.SmsManager.getDefault()
                val msg = "ZLP 535 K1 S 09 14 20 31 35-12"
                smsManager.sendTextMessage("9969", null, msg, null, null)
                Toast.makeText(this, "Đã bắn: $msg", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Lỗi SMS: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}