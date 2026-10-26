package org.example.backend.service.impl;


import org.example.backend.common.StringCommon;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.backend.service.ImportCSV;
import org.example.context.DepartmentContext;
import org.example.entity.Department;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DepartmentServiceImpl implements IDepartmentService, ImportCSV<Department, DepartmentContext> {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public String importCSV(String path) {
        String pathErr = "C:\\Users\\PNV\\Desktop\\rw102\\Java Core\\csv\\error_department.csv";
        List<Department> deps = repository.findAll();
        Map<String, Department> mapByName = new HashMap<>();// mapByName theo username
        for (Department department : deps) {
            mapByName.put(department.getName(), department);
        }
        DepartmentContext context = new DepartmentContext(mapByName);


        return importCSV(path, pathErr, context);
    }

    @Override
    public void saveAll(List<Department> entities) {
        repository.createAll(entities);
    }

    @Override
    public void exportFile(String header, List<String> dataErrors, String pathErr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathErr))) {
            bw.write(header + "," + "error_messages");
            bw.newLine();
            for (String error : dataErrors) {
                bw.write(error);
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validation(String line, List<Department> entities, List<String> dataErrors, DepartmentContext context) {
        List<String> errors = new ArrayList<>();

        String[] arrs = line.split(",", -1);
        String departmentName = arrs[0];
        // validation   5-50 kí tự,   ko chứa kí tự đặc biệt, unique
        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()
                || departmentName.trim().length() < 5 || departmentName.trim().length() > 50) {
            errors.add("Tên phòng ban phải từ 5-50 kí tự");
        }

        // chỉ dc nhập số hoặc chữ (0-9  a-z  A-Z)
        if (!departmentName.trim().matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
            errors.add("Tên phòng ban chỉ được nhập số hoặc chữ");
        }

        // unique(trùng trong file, trùng trong DB)
        if (context.getMapByName().get(departmentName) != null) {
            errors.add("Tên phòng ban đã tồn tại");
        }

        if (errors.isEmpty()) {  // chưa có ptu nào  = row đó ko có lỗi
            Department department = new Department(departmentName);
            entities.add(department);
            context.getMapByName().put(departmentName, department);// thêm vào map để hỗ trợ validation
        } else {
            String rowError = departmentName + "," + String.join("-", errors);
            dataErrors.add(rowError);
        }
    }

//    @Override
//    public String importCSV(String path) {
//        // lay du lieu tu file theo đường dẫn ở path
//        // kierm tra xem file nay có tồn tại ko,   đúng định dạng ko
//        File file = new File(path);
//        if (!path.endsWith(".csv")) {
//            return "File ko đúng định dạng !";
//        }
//        if (!file.exists()) {
//            return "File ko tồn tại !";
//        }
//
//        // đọc file
//        String header = null;
//        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line;
//            List<Department> departments = new ArrayList<>();
//            header = br.readLine();//doc dòng đầu tiêu header (buổi sau dùng)
//
//            List<String> dataErrors = new ArrayList<>();
//            List<Department> deps = repository.findAll();
//            // username
//            Map<String, Department> mapByName = new HashMap<>();// mapByName theo username
//            for (Department department : deps) {
//                mapByName.put(department.getName(), department);
//            }
//            while ((line = br.readLine()) != null) {
//                List<String> errors = new ArrayList<>();
//                String[] arrs = line.split(",");
//                String departmentName = arrs[0];
//                // validation   5-50 kí tự,   ko chứa kí tự đặc biệt, unique
//                if(Objects.isNull(departmentName) || departmentName.trim().isEmpty()
//                        || departmentName.trim().length() <5 || departmentName.trim().length() > 50) {
//                    errors.add("Tên phòng ban phải từ 5-50 kí tự");
//                }
//
//                // chỉ dc nhập số hoặc chữ (0-9  a-z  A-Z)
//                if (!departmentName.trim().matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
//                    errors.add("Tên phòng ban chỉ được nhập số hoặc chữ");
//                }
//
//                // unique(trùng trong file, trùng trong DB)
//                if (mapByName.get(departmentName) != null) {
//                    errors.add("Tên phòng ban đã tồn tại");
//                }
//
//                if (errors.isEmpty()) {  // chưa có ptu nào  = row đó ko có lỗi
//                    Department department = new Department(departmentName);
//                    departments.add(department);
//                    mapByName.put(departmentName, department);// thêm vào map để hỗ trợ validation
//                } else {
//                    String rowError = departmentName + "," + String.join("-", errors);
//                    dataErrors.add(rowError);
//                }
//            }
//
//            if (!departments.isEmpty()) {// phải có dữ liệu thfi mới gọi DB để lưu
//                repository.createAll(departments);
//            }
//
//            // xuất ra file lỗi
//            if (!dataErrors.isEmpty()) {// có ds lỗi thì mới xuất file lỗi
//                // địa chỉ xuất l
//                String pathErr = "C:\\Users\\Admin\\Desktop\\rw102\\Java Core\\csv\\error_department.csv";
//                try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathErr))) {
//                    bw.write(header +"," + "error_messages");
//                    bw.newLine();
//                    for (String error : dataErrors) {
//                        bw.write(error);
//                        bw.newLine();
//                    }
//                    bw.flush();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return String.format("Import thành công %d, import thất bại %d, %s"
//                    , departments.size(), dataErrors.size(), !dataErrors.isEmpty() ?"Đã xuat ra file lỗi error_department.csv" :"");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Import thất bại";
//        }
//    }

}