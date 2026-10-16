
package com.example.ktgk_2415053122101_nguyenthikimanh

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class ChinhSuaChiTieuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua)
        val k_101 = intent.getSerializableExtra("k") as? KhoanChi ?: run { finish(); return }
        val ten_101 = findViewById<EditText>(R.id.ten)
        val tien_101 = findViewById<EditText>(R.id.tien)
        val ghi_101 = findViewById<EditText>(R.id.ghi)

        ten_101.setText(k_101.ten_101)
        tien_101.setText(k_101.tien_101.toString())
        ghi_101.setText(k_101.ghi_101)

        findViewById<Button>(R.id.luu).setOnClickListener {
            val so_101 = tien_101.text.toString().toDoubleOrNull()
            if (ten_101.text.isBlank() || so_101 == null || so_101 <= 0 || !so_101.isFinite())
                Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show()
            else {
                k_101.ten_101 = ten_101.text.toString().trim()
                k_101.tien_101 = so_101
                k_101.ghi_101 = ghi_101.text.toString()
                setResult(RESULT_OK, Intent().putExtra("k", k_101))
                finish()
            }
        }
        findViewById<Button>(R.id.huy).setOnClickListener { finish() }
    }
}
