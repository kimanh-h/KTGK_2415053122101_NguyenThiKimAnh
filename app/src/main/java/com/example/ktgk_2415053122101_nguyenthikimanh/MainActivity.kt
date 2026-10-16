
package com.example.ktgk_2415053122101_nguyenthikimanh

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    lateinit var ten_101: EditText
    lateinit var tien_101: EditText
    lateinit var ghi_101: EditText
    var k_101 = KhoanChi("KC01", "Nước ép ổi", 18000.0, "Sau giờ học")

    val ketQua_101 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            k_101 = it.data?.getSerializableExtra("k") as? KhoanChi ?: k_101
            hien_101()
        }
    }

    fun hien_101() {
        ten_101.setText(k_101.ten_101)
        tien_101.setText(k_101.tien_101.toString())
        ghi_101.setText(k_101.ghi_101)
    }

    fun luu_101(): Boolean {
        val so_101 = tien_101.text.toString().toDoubleOrNull()
        if (ten_101.text.isBlank() || so_101 == null || so_101 <= 0 || !so_101.isFinite()) {
            Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show()
            return false
        }
        k_101 = KhoanChi("KC01", ten_101.text.toString().trim(),
            so_101, ghi_101.text.toString(), k_101.daTra_101)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolbar))
        actionBar?.title = "Nguyễn Thị Kim Anh - 2415053122101 - Quản lý chi tiêu cá nhân"

        ten_101 = findViewById(R.id.ten)
        tien_101 = findViewById(R.id.tien)
        ghi_101 = findViewById(R.id.ghi)
        hien_101()

        findViewById<Button>(R.id.them).setOnClickListener {
            if (luu_101()) Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.xem).setOnClickListener {
            if (luu_101()) ketQua_101.launch(
                Intent(this, ChiTietChiTieuActivity::class.java).putExtra("k", k_101))
        }
    }
}
