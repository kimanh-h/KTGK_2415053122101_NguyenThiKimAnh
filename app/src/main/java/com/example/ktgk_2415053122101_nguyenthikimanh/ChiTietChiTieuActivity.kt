
package com.example.ktgk_2415053122101_nguyenthikimanh

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class ChiTietChiTieuActivity : ComponentActivity() {
    lateinit var k_101: KhoanChi
    lateinit var tv_101: TextView

    val ketQua_101 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            k_101 = it.data?.getSerializableExtra("k") as? KhoanChi ?: k_101
            hien_101()
        }
    }

    fun hien_101() { tv_101.text = k_101.thongTin_101() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet)
        val nhan_101 = intent.getSerializableExtra("k") as? KhoanChi
        if (nhan_101 == null || !nhan_101.hopLe_101()) {
            Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        k_101 = nhan_101
        tv_101 = findViewById(R.id.thongtin)
        hien_101()

        findViewById<Button>(R.id.sua).setOnClickListener {
            ketQua_101.launch(Intent(this, ChinhSuaChiTieuActivity::class.java).putExtra("k", k_101))
        }
        findViewById<Button>(R.id.trangthai).setOnClickListener {
            k_101.daTra_101 = !k_101.daTra_101
            hien_101()
        }
        findViewById<Button>(R.id.quaylai).setOnClickListener { finish() }
    }

    override fun finish() {
        if (::k_101.isInitialized) setResult(RESULT_OK, Intent().putExtra("k", k_101))
        super.finish()
    }
}
