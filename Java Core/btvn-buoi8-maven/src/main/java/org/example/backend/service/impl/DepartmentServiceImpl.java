package org.example.backend.service.impl;

import org.example.backend.common.StringCommon;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.entity.Department;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl
        implements IDepartmentService {

    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {

        repository =
                new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Department> findByName(
            String name
    ) {

        return repository.findByName(name);
    }

    @Override
    public boolean insert(
            Department department
    ) {

        return repository.insert(
                department
        );
    }

    @Override
    public boolean deleteById(
            int id
    ) {

        return repository.deleteById(id);
    }

    @Override
    public boolean updateName(
            int id,
            String name
    ) {

        return repository.updateName(
                id,
                name
        );
    }
    @Override
    public String importCSV(String path) {
        File file = new File(path);
        if (!path.toLowerCase().endsWith(".csv") || !file.exists() || !file.isFile()) {
            return "File không đúng định dạng hoặc không tồn tại!";
        }

        List<Department> departments = new ArrayList<>();
        List<String> invalidRows = new ArrayList<>();
        List<String> existedNames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
               if (line.trim().isEmpty()) {
                   continue;
               }

               String[] fields = line.split(",", -1);
               if (isFirstLine) {
                   isFirstLine = false;
                   if (fields.length > 0 && isHeader(fields)) {
                       continue;
                   }
               }

               String name = fields[0].trim();
               if (name.isEmpty() || !name.matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
                   invalidRows.add("Tên department không hợp lệ: " + line);
                   continue;
               }

               String normalizedName = name.toLowerCase();
               if (existedNames.contains(normalizedName) || isDepartmentExists(name)) {
                   System.out.println("Bỏ qua Department đã tồn tại: " + name);
                   invalidRows.add("Department đã tồn tại: " + name);
                   continue;
               }

               existedNames.add(normalizedName);
               departments.add(new Department(name));
            }

            if (!invalidRows.isEmpty()) {
               exportErrorFile("department_error.csv", invalidRows);
            }

            if (departments.isEmpty()) {
               return "File CSV không có dữ liệu Department mới để import!";
            }

            int count = 0;
            int nextId = getNextDepartmentId();
            for (Department department : departments) {
               department.setId(nextId);
               boolean check = repository.create(department);
               if (!check) {
                   return "Lỗi khi lưu Department: " + department.getName();
               }
               nextId++;
               count++;
            }

            if (!invalidRows.isEmpty()) {
               return "Import dữ liệu Department thành công: " + count + " dòng. Có " + invalidRows.size() + " dòng lỗi, file lỗi đã được lưu tại data/errors/department_error.csv";
            }

            return "Import dữ liệu Department thành công: " + count + " dòng.";

        } catch (Exception e) {
            return "Lỗi khi đọc file: " + e.getMessage();
        }
    }

    private boolean isDepartmentExists(String name) {
        for (Department department : repository.findAll()) {
            if (department.getName().equalsIgnoreCase(name.trim())) {
               return true;
            }
        }
        return false;
    }

    private boolean isHeader(String[] fields) {
        if (fields.length == 0) {
            return false;
        }

        for (String field : fields) {
            String value = field.trim().replace("\uFEFF", "").toLowerCase();
            if (value.equals("name") || value.equals("department_name") || value.equals("department name")) {
               return true;
            }
        }

        return false;
    }

    private int getNextDepartmentId() {
        int maxId = 0;
        for (Department department : repository.findAll()) {
            if (department.getId() > maxId) {
               maxId = department.getId();
            }
        }
        return maxId + 1;
    }

    private void exportErrorFile(String fileName, List<String> errors) {
        try {
            Path directory = Paths.get("data", "errors");
            Files.createDirectories(directory);

            Path filePath = directory.resolve(fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
               writer.write("error_message");
               writer.newLine();
               for (String error : errors) {
                   writer.write(error);
                   writer.newLine();
               }
            }
        } catch (Exception e) {
            System.out.println("Không thể xuất file lỗi Department: " + e.getMessage());
        }
    }
}