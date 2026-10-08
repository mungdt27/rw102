package frontend;

import backend.controller.CanBoController;
import entity.CanBo;
import entity.CongNhan;
import entity.GioiTinh;
import entity.KySu;
import entity.Loai;
import entity.NhanVien;

import java.util.List;
import java.util.Scanner;

public class Function {
    private CanBoController canBoController;
    private Scanner sc;

    public Function() {
        this.canBoController = new CanBoController();
        this.sc = new Scanner(System.in);
    }

    // them mới
    public void themMoi() {
        System.out.println("==== THÊM MỚI CÁN BỘ ====");
        // nhập dữ liệu chung
        System.out.print("Nhập họ tên: ");
        String hoTen;
        while (true) {
            hoTen = sc.nextLine();
            // check độ dài
            if (hoTen.length() < 5 || hoTen.length() > 50) {
                System.err.println("Họ tên từ 5 đến 50 kí tự! Nhập lại");
                continue;
            }
            // check xem họ tên này đã tồn tại chưa
            boolean check = canBoController.existByName(hoTen);
            if (check) {
                System.err.println("Họ tên này đã tồn tại! Nhập lại");
                continue;
            }
            break;
        }

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
                } if (tuoi > 150) {
                    System.err.println("Vui lòng nhập tuổi nhỏ hơn 150!");
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
        String diaChi;
        while (true) {
            diaChi = sc.nextLine();
            if (diaChi.length() < 5 || diaChi.length() > 100) {
                System.err.println("Địa chỉ từ 5 đến 100 kí tự! Nhập lại");
                continue;
            }
            break;
        }
        // chọn loai cán bộ
        String congViec = null;
        int bac = 0;
        String nganh = null;
        CanBo canBo = null;
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
                canBo = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                break;
            case "2":
                System.out.print("Nhập ngành đào tạo: ");
                while (true) {
                    nganh = sc.nextLine();
                    if (nganh.length() < 5 || nganh.length() > 50) {
                        System.err.println("Ngành đào tạo từ 5 đến 50 kí tự! Nhập lại");
                        continue;
                    }
                    break;
                }
                canBo = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nganh);
                break;
            default:
                System.out.print("Nhập công việc: ");
                while (true) {
                    congViec = sc.nextLine();
                    if (congViec.length() < 5 || congViec.length() > 50) {
                        System.err.println("Công việc từ 5 đến 50 kí tự! Nhập lại");
                        continue;
                    }
                    break;
                }
                canBo = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
        }
        // sau khi nhap cac thông tin thì sẽ dc 1 canBo
        boolean check = canBoController.save(canBo);
        if (check) {
            System.out.println("Thêm mới thành công!");
        } else {
            System.out.println("Thêm mới thất bại!");
        }

    }

    // hien thi
    public void hienThiToanBo() {
        // yêu cầu controller trả ra 1 ds các cán bộ
        List<CanBo> canBos = canBoController.findAll();

        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");

    }

    // tim kiem
    public void timKiem(){
        System.out.println("==== TÌM KIẾM CÁN BỘ ====");
        System.out.println("Nhập họ tên cần tìm: ");
        String ten = sc.nextLine();
        List<CanBo> canBos = canBoController.findByName(ten);

        if (canBos.isEmpty()) {//canBos.size() == 0
            System.out.println("Không có kết quả tương ứng!");
        } else {
            System.out.println("+-------------------------+-----+----------+--------------------+");
            System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
            System.out.println("+-------------------------+-----+----------+--------------------+");
            for (CanBo cb : canBos) {
                System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
            }
            System.out.println("+-------------------------+-----+----------+--------------------+");
        }

    }

    // xoa
    public void deleteByName() {
        System.out.println("==== XÓA CÁN BỘ ====");
        System.out.println("Nhập họ tên cần xóa: ");// like 'abc'
        String ten = sc.nextLine();
        boolean check = canBoController.deleteByName(ten);

        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa không thành công");
        }
    }


    // update
    public void updateByName() {
        System.out.println("==== UPDATE ĐỊA CHỈ THEO TÊN ====");
        System.out.println("Nhập họ tên cần update: ");// like 'abc'
        String ten = sc.nextLine();

        System.out.println("Nhập địa chỉ cần update: ");
        String diaChi = sc.nextLine();

        boolean check = canBoController.updateByName(ten, diaChi);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update không thành công");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới cán bộ.");
            System.out.println("2. Tìm kiếm theo họ tên.");//
            System.out.println("3. Hiển thị toàn bộ các cán bộ.");//
            System.out.println("4. Nhập vào tên của cán bộ và xóa cán bộ đó.");// nhập đúng tên
            System.out.println("5. Update địa chỉ theo tên(nhập đúng).");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    this.themMoi();
                    break;
                case "2":
                    this.timKiem();
                    break;
                case "3":
                    this.hienThiToanBo();
                    break;
                case "4":
                    this.deleteByName();
                    break;
                case "5":
                    this.updateByName();
                    break;
                case "6":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, Chọn lại!");
            }
        }

    }
}