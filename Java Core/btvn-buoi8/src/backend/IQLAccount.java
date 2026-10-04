package backend;

public interface IQLAccount {
    // 1. Hiển thị toàn bộ account
    void hienThiAccount();
    // 2. Tìm kiếm account theo username
    void timKiemAccount();
    // 3. Thêm mới account
    void themAccount();
    // 4. Xóa account theo username
    void xoaAccount();
    // 5. Update fullname theo username
    void updateFullName();
}
