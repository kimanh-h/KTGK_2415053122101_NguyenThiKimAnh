
package com.example.ktgk_2415053122101_nguyenthikimanh
data class KhoanChi(
    var ma_101: String, var ten_101: String,
    var tien_101: Double, var ghi_101: String,
    var daTra_101: Boolean = false
) : java.io.Serializable {
    fun hopLe_101() = ten_101.isNotBlank() && tien_101 > 0 && tien_101.isFinite()
    fun thongTin_101() = "Mã: $ma_101\nTên: $ten_101\nSố tiền: $tien_101\nGhi chú: $ghi_101\nTrạng thái: " +
            if (daTra_101) "Đã thanh toán" else "Chưa thanh toán"
}
