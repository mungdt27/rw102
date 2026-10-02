package entity;

import java.time.LocalDate;

public class Bao extends TaiLieu {
    private LocalDate ngayPhatHanh;

    public Bao(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, Loai loai, LocalDate ngayPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loai);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public LocalDate getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
