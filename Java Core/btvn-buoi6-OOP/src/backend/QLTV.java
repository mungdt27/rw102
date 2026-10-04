package backend;

import entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV {

    private List<TaiLieu> taiLieuList;
    private Scanner sc = new Scanner(System.in);

    public QLTV() {
        taiLieuList = new ArrayList<>();

        taiLieuList.add(new Sach("S01", "NXB Kim Dong", 100, Loai.SACH, "Nguyen Nhat Anh", 250));
        taiLieuList.add(new Sach("S02", "NXB Tre", 150, Loai.SACH, "To Hoai", 300));

        taiLieuList.add(new TapChi("TC01", "NXB Thanh Nien", 50, Loai.TAP_CHI, 12, 9));
        taiLieuList.add(new TapChi("TC02", "NXB Lao Dong", 70, Loai.TAP_CHI, 25, 8));

        taiLieuList.add(new Bao("B01", "NXB Ha Noi", 200, Loai.BAO, LocalDate.of(2026, 9, 27)));
    }

    // Question 1: Thêm tài liệu mới
//    @Override
//    public void themTaiLieu() {
//        System.out.println("==== THÊM TÀI LIỆU MỚI ====");
//        // Nhập dữ liệu chung
//        System.out.print("Nhập mã tài liệu: ");
//        String maTaiLieu = sc.nextLine();
//        System.out.print("Nhập tên nhà xuất bản: ");
//        String tenNhaXuatBan = sc.nextLine();
//        System.out.print("Nhập số bản phát hành: ");
//        int soBanPhatHanh = sc.nextInt();
//        sc.nextLine();
//
//        // Chọn loại tài liệu
//        System.out.print("Nhập loại tài liệu: 1. Sách   2. Tạp chí   3. Báo");
//        String loaiTaiLieu = sc.nextLine();
//        TaiLieu taiLieu;
//        switch (loaiTaiLieu) {
//            // 1. SÁCH
//            case "1":
//                System.out.println("==== NHẬP THÔNG TIN SÁCH ====");
//                System.out.print("Nhập tên tác giả: ");
//                String tenTacGia = sc.nextLine();
//                System.out.print("Nhập số trang: ");
//                int soTrang = sc.nextInt();
//                sc.nextLine();
//                taiLieu = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, Loai.SACH, tenTacGia, soTrang);
//                break;
//            // 2. TẠP CHÍ
//            case "2":
//                System.out.println("==== NHẬP THÔNG TIN TẠP CHÍ ====");
//                System.out.print("Nhập số phát hành: ");
//                int soPhatHanh = sc.nextInt();
//                System.out.print("Nhập tháng phát hành: ");
//                int thangPhatHanh = sc.nextInt();
//                sc.nextLine();
//                taiLieu = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, Loai.TAP_CHI, soPhatHanh, thangPhatHanh);
//                break;
//            // 3. BÁO
//            case "3":
//                System.out.println("==== NHẬP THÔNG TIN BÁO ====");
//                System.out.print("Nhập ngày phát hành (dd/MM/yyyy): ");
//                String ngayPhatHanh = sc.nextLine();
//                LocalDate localDate = LocalDate.parse(ngayPhatHanh, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                taiLieu = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, Loai.BAO, localDate);
//                break;
//            // Nhập sai loại
//            default:
//                System.out.println("Loại tài liệu không hợp lệ!");
//                return;
//        }
//
//        // Thêm tài liệu vào danh sách
//        taiLieuList.add(taiLieu);
//        System.out.println("Thêm tài liệu thành công!");
//    }

    // Question 1: Thêm tài liệu mới
    @Override
    public void themTaiLieu() {

        System.out.println("==== THÊM TÀI LIỆU MỚI ====");

        // Nhập dữ liệu chung
        System.out.print("Nhập mã tài liệu: ");
        String maTaiLieu = sc.nextLine();

        System.out.print("Nhập tên nhà xuất bản: ");
        String tenNhaXuatBan = sc.nextLine();

        System.out.print("Nhập số bản phát hành: ");
        int soBanPhatHanh = sc.nextInt();
        sc.nextLine();

        // Chọn loại tài liệu
        System.out.print(
                "Nhập loại tài liệu: "
                        + "1. Sách "
                        + "2. Báo "
                        + "3. Tạp chí: "
        );

        String loaiTaiLieu = sc.nextLine();

        String column;
        String value;

        switch (loaiTaiLieu) {

            // Sách
            case "1":

                System.out.print("Nhập tên tác giả: ");
                String tacGia = sc.nextLine();

                System.out.print("Nhập số trang: ");
                int soTrang = sc.nextInt();
                sc.nextLine();

                column = "ten_tac_gia, so_trang";
                value = "'" + tacGia + "', " + soTrang;

                break;


            // Báo
            case "2":

                System.out.print(
                        "Nhập ngày phát hành (dd/MM/yyyy): "
                );

                String ngayPhatHanh = sc.nextLine();

                LocalDate localDate =
                        LocalDate.parse(
                                ngayPhatHanh,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        );

                column = "ngay_phat_hanh";
                value = "'" + localDate + "'";

                break;


            // Tạp chí
            default:

                System.out.print("Nhập số phát hành: ");
                int soPhatHanh = sc.nextInt();

                System.out.print("Nhập tháng phát hành: ");
                int thangPhatHanh = sc.nextInt();
                sc.nextLine();

                column = "so_phat_hanh, thang_phat_hanh";
                value = soPhatHanh + ", " + thangPhatHanh;

                break;
        }

        // Tạo câu SQL
        String sql = String.format(
                "INSERT INTO tai_lieu " +
                        "(ma_tai_lieu, ten_nha_xuat_ban, so_ban_phat_hanh, loai, %s) " +
                        "VALUES (?, ?, ?, ?, %s)",
                column,
                value
        );

        try {

            // Kết nối database
            String url = "jdbc:mysql://localhost:3306/qltv";
            String username = "root";
            String password = "root";

            Connection conn =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            PreparedStatement statement =
                    conn.prepareStatement(sql);

            // Các thuộc tính chung
            statement.setString(1, maTaiLieu);
            statement.setString(2, tenNhaXuatBan);
            statement.setInt(3, soBanPhatHanh);

            // Loại tài liệu
            String loai;

            if (loaiTaiLieu.equals("1")) {
                loai = "SACH";
            } else if (loaiTaiLieu.equals("2")) {
                loai = "BAO";
            } else {
                loai = "TAP_CHI";
            }

            statement.setString(4, loai);

            // Thực hiện INSERT
            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }

            statement.close();
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    // Question 2: Xóa tài liệu theo mã
    @Override
    public void xoaTheoMa() {

        System.out.println("==== XÓA TÀI LIỆU THEO MÃ ====");

        // Nhập mã tài liệu cần xóa
        System.out.print("Nhập mã tài liệu cần xóa: ");
        String maTaiLieu = sc.nextLine();

        // Thông tin kết nối Database
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "root";

        try {

            // Tạo kết nối đến Database
            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            // Câu SQL xóa theo mã tài liệu
            String sql =
                    "DELETE FROM tai_lieu WHERE ma_tai_lieu = ?";

            // Tạo PreparedStatement
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            // Truyền mã tài liệu vào dấu ?
            preparedStatement.setString(
                    1,
                    maTaiLieu
            );

            // Thực hiện câu DELETE
            int c = preparedStatement.executeUpdate();

            // Kiểm tra kết quả
            if (c > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println(
                        "Không tìm thấy tài liệu có mã: "
                                + maTaiLieu
                );
            }

        } catch (Exception e) {
        }
    }

    // Question 3: Hiển thị thông tin toàn bộ tài liệu
    @Override
    public void hienThiDanhSach() {
        List <TaiLieu> taiLieus = new ArrayList<>();
        try {
            // B1: Lấy dữ liệu từ Database
            String url = "jdbc:mysql://localhost:3306/qltv";
            String username = "root";
            String password = "";

            // B2: Tạo kết nối đến Database
            Connection connection = DriverManager.getConnection(url, username, password);

            // B3: Tạo câu SQL
            String sql = "SELECT * FROM tai_lieu";

            Statement statement = connection.createStatement();

            // B4: Thực hiện câu query
            ResultSet resultSet = statement.executeQuery(sql);

            // B5: Đọc dữ liệu từ ResultSet
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNhaXuatBan = resultSet.getString("ten_nha_xuat_ban");
                int soBanPhatHanh = resultSet.getInt("so_ban_phat_hanh");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);

                // B6: Chuyển dữ liệu từ Database thành object tương ứng

                if (loai == Loai.SACH) {
                    String tenTacGia =
                            resultSet.getString("ten_tac_gia");

                    int soTrang =
                            resultSet.getInt("so_trang");

                    Sach sach =
                            new Sach(
                                    maTaiLieu,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.SACH,
                                    tenTacGia,
                                    soTrang
                            );

                    taiLieus.add(sach);


                } else if (loai == Loai.TAP_CHI) {

                    int soPhatHanh =
                            resultSet.getInt("so_phat_hanh");

                    int thangPhatHanh =
                            resultSet.getInt("thang_phat_hanh");

                    TapChi tapChi =
                            new TapChi(
                                    maTaiLieu,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.TAP_CHI,
                                    soPhatHanh,
                                    thangPhatHanh
                            );

                    taiLieus.add(tapChi);


                } else if (loai == Loai.BAO) {

                    java.sql.Date sqlDate =
                            resultSet.getDate("ngay_phat_hanh");

                    LocalDate ngayPhatHanh =
                            sqlDate.toLocalDate();

                    Bao bao =
                            new Bao(
                                    maTaiLieu,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.BAO,
                                    ngayPhatHanh
                            );

                    taiLieus.add(bao);
                }
            }

            // B7: Đóng kết nối
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

        }

        System.out.println("==== HIỂN THỊ TOÀN BỘ TÀI LIỆU ====");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");

        System.out.printf(
                "|%8s|%20s|%12s|%12s|%32s|\n",
                "Mã TL",
                "Nhà xuất bản",
                "Số bản PH",
                "Loại",
                "Thông tin thêm"
        );

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");

        for (TaiLieu taiLieu : taiLieuList) {
            String loai = "";
            String thongTinThem = "";
            if (taiLieu instanceof Sach) {
                Sach sach = (Sach) taiLieu;
                loai = "Sách";
                thongTinThem = "TG: " + sach.getTacGia() + ", " + sach.getSoTrang() + " trang";

            } else if (taiLieu instanceof TapChi) {
                TapChi tapChi = (TapChi) taiLieu;
                loai = "Tạp chí";
                thongTinThem = "Số PH: " + tapChi.getSoPhatHanh() + ", tháng: " + tapChi.getThangPhatHanh();

            } else if (taiLieu instanceof Bao) {
                Bao bao = (Bao) taiLieu;
                loai = "Báo";
                thongTinThem = "Ngày PH: " + bao.getNgayPhatHanh().format(formatter);
            }

            System.out.printf(
                    "|%8s|%20s|%12d|%12s|%32s|\n",
                    taiLieu.getMaTaiLieu(),
                    taiLieu.getTenNhaXuatBan(),
                    taiLieu.getSoBanPhatHanh(),
                    loai,
                    thongTinThem
            );
        }

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");
    }

    // Question 4: Tìm kiếm tài liệu theo loại

    @Override
    public void timKiemTheoLoai() {
        System.out.println("==== TÌM KIẾM TÀI LIỆU THEO LOẠI ====");
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");
        System.out.print("Nhập loại tài liệu muốn tìm: ");
        String choice = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean found = false;

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");

        System.out.printf(
                "|%8s|%20s|%12s|%12s|%32s|\n",
                "Mã TL",
                "Nhà xuất bản",
                "Số bản PH",
                "Loại",
                "Thông tin thêm"
        );

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");

        for (TaiLieu taiLieu : taiLieuList) {
            String loai = "";
            String thongTinThem = "";
            switch (choice) {
                case "1":
                    if (taiLieu instanceof Sach) {
                        Sach sach = (Sach) taiLieu;
                        loai = "Sách";
                        thongTinThem = "TG: " + sach.getTacGia() + ", " + sach.getSoTrang() + " trang";
                        found = true;
                    }
                    break;

                case "2":
                    if (taiLieu instanceof TapChi) {
                        TapChi tapChi = (TapChi) taiLieu;
                        loai = "Tạp chí";
                        thongTinThem = "Số PH: " + tapChi.getSoPhatHanh() + ", tháng: " + tapChi.getThangPhatHanh();
                        found = true;
                    }
                    break;

                case "3":
                    if (taiLieu instanceof Bao) {
                        Bao bao = (Bao) taiLieu;
                        loai = "Báo";
                        thongTinThem = "Ngày PH: " + bao.getNgayPhatHanh().format(formatter);
                        found = true;
                    }
                    break;

                default:
                    System.out.println("Chọn sai loại tài liệu!");
                    return;
            }

            if (!loai.equals("")) {
                System.out.printf(
                        "|%8s|%20s|%12d|%12s|%32s|\n",
                        taiLieu.getMaTaiLieu(),
                        taiLieu.getTenNhaXuatBan(),
                        taiLieu.getSoBanPhatHanh(),
                        loai,
                        thongTinThem
                );
            }
        }

        System.out.println("+--------+--------------------+------------+------------+--------------------------------+");

        if (!found) {
            System.out.println("Không tìm thấy tài liệu thuộc loại này.");
        }
    }

    // Question 4: Tìm kiếm gần đúng mã tài liệu
    @Override
    public void timKiemGanDungMa() {
        System.out.println("==== TÌM KIẾM GẦN ĐÚNG MÃ TÀI LIỆU ====");
        System.out.print("Nhập mã tài liệu cần tìm: ");
        String maTaiLieu = sc.nextLine();

        // List lưu kết quả tìm kiếm
        List<TaiLieu> taiLieus = new ArrayList<>();

        try {

            // B1: Lấy dữ liệu từ Database
            String url = "jdbc:mysql://localhost:3306/qltv";
            String username = "root";
            String password = "";

            // B2: Tạo kết nối đến Database
            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            // B3: Tạo câu SQL
            // Tìm gần đúng mã tài liệu
            String sql =
                    "SELECT * FROM tai_lieu WHERE ma_tai_lieu LIKE ?";

            // B4: Tạo PreparedStatement
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            // Truyền giá trị cần tìm vào dấu ?
            statement.setString(
                    1,
                    "%" + maTaiLieu + "%"
            );

            // B5: Thực hiện câu query
            ResultSet resultSet =
                    statement.executeQuery();

            // B6: Đọc dữ liệu từ ResultSet
            while (resultSet.next()) {

                // -------------------------
                // Lấy thông tin chung
                // -------------------------

                String ma =
                        resultSet.getString("ma_tai_lieu");

                String tenNhaXuatBan =
                        resultSet.getString("ten_nha_xuat_ban");

                int soBanPhatHanh =
                        resultSet.getInt("so_ban_phat_hanh");

                String loai =
                        resultSet.getString("loai");


                // -------------------------
                // Nếu là Sách
                // -------------------------

                if (loai.equals("SACH")) {

                    String tacGia =
                            resultSet.getString("ten_tac_gia");

                    int soTrang =
                            resultSet.getInt("so_trang");

                    Sach sach =
                            new Sach(
                                    ma,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.SACH,
                                    tacGia,
                                    soTrang
                            );

                    taiLieus.add(sach);
                }


                // -------------------------
                // Nếu là Tạp chí
                // -------------------------

                else if (loai.equals("TAP_CHI")) {

                    int soPhatHanh =
                            resultSet.getInt("so_phat_hanh");

                    int thangPhatHanh =
                            resultSet.getInt("thang_phat_hanh");

                    TapChi tapChi =
                            new TapChi(
                                    ma,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.TAP_CHI,
                                    soPhatHanh,
                                    thangPhatHanh
                            );

                    taiLieus.add(tapChi);
                }


                // -------------------------
                // Nếu là Báo
                // -------------------------

                else if (loai.equals("BAO")) {

                    java.sql.Date sqlDate =
                            resultSet.getDate(
                                    "ngay_phat_hanh"
                            );

                    LocalDate ngayPhatHanh =
                            sqlDate.toLocalDate();

                    Bao bao =
                            new Bao(
                                    ma,
                                    tenNhaXuatBan,
                                    soBanPhatHanh,
                                    Loai.BAO,
                                    ngayPhatHanh
                            );

                    taiLieus.add(bao);
                }
            }

            // B7: Đóng kết nối
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // ==================================================
        // Hiển thị kết quả
        // ==================================================

        if (taiLieus.isEmpty()) {

            System.out.println(
                    "Không tìm thấy tài liệu phù hợp."
            );

            return;
        }


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println(
                "+--------+--------------------+------------+------------+--------------------------------+"
        );

        System.out.printf(
                "|%8s|%20s|%12s|%12s|%32s|\n",
                "Mã TL",
                "Nhà xuất bản",
                "Số bản PH",
                "Loại",
                "Thông tin thêm"
        );

        System.out.println(
                "+--------+--------------------+------------+------------+--------------------------------+"
        );


        for (TaiLieu taiLieu : taiLieus) {

            String loai = "";
            String thongTinThem = "";


            // Sách
            if (taiLieu instanceof Sach) {

                Sach sach = (Sach) taiLieu;

                loai = "Sách";

                thongTinThem =
                        "TG: "
                                + sach.getTacGia()
                                + ", "
                                + sach.getSoTrang()
                                + " trang";
            }


            // Tạp chí
            else if (taiLieu instanceof TapChi) {

                TapChi tapChi = (TapChi) taiLieu;

                loai = "Tạp chí";

                thongTinThem =
                        "Số PH: "
                                + tapChi.getSoPhatHanh()
                                + ", tháng: "
                                + tapChi.getThangPhatHanh();
            }


            // Báo
            else if (taiLieu instanceof Bao) {

                Bao bao = (Bao) taiLieu;

                loai = "Báo";

                thongTinThem =
                        "Ngày PH: "
                                + bao.getNgayPhatHanh()
                                .format(formatter);
            }


            System.out.printf(
                    "|%8s|%20s|%12d|%12s|%32s|\n",
                    taiLieu.getMaTaiLieu(),
                    taiLieu.getTenNhaXuatBan(),
                    taiLieu.getSoBanPhatHanh(),
                    loai,
                    thongTinThem
            );
        }


        System.out.println(
                "+--------+--------------------+------------+------------+--------------------------------+"
        );
    }

    // Question 5: Update tên nhà xuất bản của tài liệu theo mã
    @Override
    public void updateTenNhaXuatBan() {

        System.out.println("==== UPDATE TÊN NHÀ XUẤT BẢN ====");

        System.out.print("Nhập mã tài liệu cần update: ");
        String maTaiLieu = sc.nextLine();

        System.out.print("Nhập tên nhà xuất bản mới: ");
        String tenNhaXuatBan = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "";

        try {

            // Tạo kết nối đến Database
            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            // Tạo câu SQL
            String sql =
                    "UPDATE tai_lieu " +
                            "SET ten_nha_xuat_ban = ? " +
                            "WHERE ma_tai_lieu = ?";

            // Tạo PreparedStatement
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            // Truyền dữ liệu vào dấu ?
            preparedStatement.setString(
                    1,
                    tenNhaXuatBan
            );

            preparedStatement.setString(
                    2,
                    maTaiLieu
            );

            // Thực hiện update
            int c =
                    preparedStatement.executeUpdate();

            // Kiểm tra kết quả
            if (c > 0) {

                System.out.println(
                        "Update thông tin thành công!"
                );

            } else {

                System.out.println(
                        "Update thông tin không thành công!"
                );
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}