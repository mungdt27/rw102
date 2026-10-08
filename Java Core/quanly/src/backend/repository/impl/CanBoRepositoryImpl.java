package backend.repository.impl;

import backend.repository.ICanBoRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanBoRepositoryImpl implements ICanBoRepository {
    @Override
    public List<CanBo> findAll() {
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
        return canBos;
    }

    @Override
    public List<CanBo> findByName(String ten) {
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
        return canBos;
    }

    @Override
    public boolean deleteByName(String ten) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM can_bo WHERE ho_ten like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);

            int c = preparedStatement.executeUpdate();
//            if (c > 0) {
//                return true;
//            } else {
//                return false;
//            }
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateByName(String ten, String diaChi) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update can_bo SET dia_chi = ? where ho_ten = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, diaChi);
            preparedStatement.setString(2, ten);

            int c = preparedStatement.executeUpdate();
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();// hiển thị ra lỗi
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean save(CanBo canBo) {
        String column = null;
        String value = null;
        if (canBo instanceof CongNhan) {
            column = "bac";
            value = "'CN', " + ((CongNhan) canBo).getBac();
        } else if (canBo instanceof KySu) {
            column = "nganh";
            value = "'KS', " + ((KySu) canBo).getNganhDaoTao();
        } else {
            column = "cong_viec";
            value = "'NV', " + ((NhanVien) canBo).getCongViec();
        }
        String sql = String.format("INSERT INTO can_bo (ho_ten, tuoi, gioi_tinh, dia_chi, loai, %s)" +
                " VALUES (?, ?, ?, ?, %s)", column, value);
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, canBo.getHoTen());
            statement.setInt(2, canBo.getTuoi());
            statement.setString(3, canBo.getGioiTinh().name());// gioiTinh.name() chuyeern tuwf enum -> string
            statement.setString(4, canBo.getDiaChi());

            int c = statement.executeUpdate();// c: số row thay đổi trong DB
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existByName(String hoTen) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo where ho_ten like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hoTen);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }
}