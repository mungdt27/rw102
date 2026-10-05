package backend;

import entity.CanBo;
import entity.CongNhan;
import entity.GioiTinh;
import entity.KySu;
import entity.Loai;
import entity.NhanVien;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void themMoi() {
        Connection conn = null;
        PreparedStatement statement = null;

        System.out.println("==== THÊM MỚI CÁN BỘ ====");
        // nhập dữ liệu chung
        System.out.print("Nhập họ tên: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        int tuoi = 0;
        while (true) {
            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("Vui lòng nhập số nguyên dương!");
            } else {
                tuoi = sc.nextInt();
                sc.nextLine();
                if (tuoi <= 0) {
                    System.err.println("Vui lòng nhập số nguyên dương!");
                } else {
                    break;
                }
            }
        }
        System.out.print("Nhập giới tính: 1. NAM     2.NU     khác. KHAC ");
        String gt = sc.nextLine();
        GioiTinh gioiTinh;
        switch (gt) {
            case "1":
                gioiTinh = GioiTinh.NAM;
                break;
            case "2":
                gioiTinh = GioiTinh.NU;
                break;
            default:
                gioiTinh = GioiTinh.KHAC;
        }
        System.out.print("Nhập địa chỉ: ");
        String diaChi = sc.nextLine();
        // chọn loai cán bộ
        String column = null;
        String value = null;
        String congViec = null;
        int bac = 0;
        String nganh = null;
        System.out.print("Nhập loại cán bộ: 1. Công nhân     2.Kỹ sư     khác. Nhân viên ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Nhập bậc: ");
                while (true) {
                    if (!sc.hasNextInt()) {
                        sc.nextLine();
                        System.err.println("Vui lòng nhập số nguyên dương!");
                    } else {
                        bac = sc.nextInt();
                        sc.nextLine();
                        if (bac < 1 || bac > 10) {
                            System.err.println("Vui lòng nhập số >= 1 và <= 10!");
                        } else {
                            break;
                        }
                    }
                }
                column = "bac";
                value = "'CN', " + bac;
                break;
            case "2":
                System.out.print("Nhập ngành đào tạo: ");
                nganh = sc.nextLine();
                column = "nganh";
                value = "'KS', " + nganh;
                break;
            default:
                System.out.print("Nhập công việc: ");
                congViec = sc.nextLine();
                column = "cong_viec";
                value = "'NV', " + congViec;
        }
        String sql = String.format("INSERT INTO can_bo (ho_ten, tuoi, gioi_tinh, dia_chi, loai, %s) VALUES (?, ?, ?, ?, %s)", column, value);
        try {
            conn = JDBCUtils.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, hoTen);
            statement.setInt(2, tuoi);
            statement.setString(3, gioiTinh.name());// gioiTinh.name() chuyeern tuwf enum -> string
            statement.setString(4, diaChi);

            int c = statement.executeUpdate();// c: số row thay đổi trong DB
            if (c > 0) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }

    }

    @Override
    public void timKiemTheoTen() {
        System.out.println("==== TÌM KIẾM CÁN BỘ ====");
        System.out.println("Nhập họ tên cần tìm: ");
        String ten = sc.nextLine();
        List<CanBo> canBos = new ArrayList<>();
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo where ho_ten like ?";// ? là tham số
            // Statement   hỗ trợ cau sql tĩnh(ko có tham só)
            PreparedStatement statement = connection.prepareStatement(sql); // hỗ trợ cau sql động( có tham só)
            statement.setString(1, "%" + ten + "%");//  truyền giá trị cho tham số

            ResultSet resultSet = statement.executeQuery();// thư thi câu query(select) sau

            while (resultSet.next()) {// chuyển từ  resultSet thành list cán bộ
                String hoTen = resultSet.getString("ho_ten");// lấy dữ lieju theo tên cột hoặc vtri
                int tuoi = resultSet.getInt("tuoi");
                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);// chuyển từ String thành enum
                String diaChi = resultSet.getString("dia_chi");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);// chuyển từ String thành enum
                if (loai == Loai.CN) {
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBos.add(cn);
                } else if (loai == Loai.KS) {
                    String nghanhDaoTao = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nghanhDaoTao);
                    canBos.add(ks);
                } else if (loai == Loai.NV) {
                    String congViec = resultSet.getString("cong_viec");
                    CanBo nv = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
                    canBos.add(nv);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        // b2 hiển thị
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");
    }

    @Override
    public void hienThiToanBo() {
        List<CanBo> canBos = new ArrayList<>();
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// thư thi câu query(select) sau
            while (resultSet.next()) {// chuyển từ  resultSet thành list cán bộ
                String hoTen = resultSet.getString("ho_ten");// lấy dữ lieju theo tên cột hoặc vtri
                int tuoi = resultSet.getInt("tuoi");
                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);// chuyển từ String thành enum
                String diaChi = resultSet.getString("dia_chi");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);// chuyển từ String thành enum
                if (loai == Loai.CN) {
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBos.add(cn);
                } else if (loai == Loai.KS) {
                    String nghanhDaoTao = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nghanhDaoTao);
                    canBos.add(ks);
                } else if (loai == Loai.NV) {
                    String congViec = resultSet.getString("cong_viec");
                    CanBo nv = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
                    canBos.add(nv);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        //b2: hiển thi dữ liệu
        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");
    }

    @Override
    public void xoaTheoTen() {
        System.out.println("==== XÓA CÁN BỘ ====");
        System.out.println("Nhập họ tên cần xóa: ");// like 'abc'
        String ten = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM can_bo WHERE ho_ten like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa không thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public void updateDiaChiTheoTen() {
        System.out.println("==== UPDATE ĐỊA CHỈ THEO TÊN ====");
        System.out.println("Nhập họ tên cần update: ");// like 'abc'
        String ten = sc.nextLine();

        System.out.println("Nhập địa chỉ cần update: ");
        String diaChi = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update can_bo SET dia_chi = ? where ho_ten = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, diaChi);
            preparedStatement.setString(2, ten);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Update thông tin thành công!");
            } else {
                System.out.println("Update thông tin không thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();// hiển thị ra lỗi
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
    }
}