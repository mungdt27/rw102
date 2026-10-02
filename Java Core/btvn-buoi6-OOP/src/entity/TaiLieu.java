package entity;

public class TaiLieu {
    private String maTaiLieu;
    private String tenNhaXuatBan;
    private int soBanPhatHanh;
    private Loai loai;

    // Constructor
    public TaiLieu() {
    }

    public TaiLieu(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, Loai loai) {
        this.maTaiLieu = maTaiLieu;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.soBanPhatHanh = soBanPhatHanh;
        this.loai = loai;
    }

    // Getters and Setters
    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public int getSoBanPhatHanh() {
        return soBanPhatHanh;
    }

    public void setSoBanPhatHanh(int soBanPhatHanh) {
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public Loai getLoai() {
        return loai;
    }

    public void setLoai(Loai loai){
        this.loai = loai;
    }
}