package backend;

public interface IQLDepartment {
    // 1. Hiển thị department
    void hienThiDepartment();
    // 2. Tìm kiếm department theo tên
    void timKiemDepartment();
    // 3. Thêm mới department
    void themMoiDepartment();
    // 4. Xóa department theo id
    void xoaDepartment();
    // 5. Update tên phòng ban theo id
    void updateTenDepartment();
}
