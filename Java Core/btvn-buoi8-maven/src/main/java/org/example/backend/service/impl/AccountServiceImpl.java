package org.example.backend.service.impl;

import org.example.backend.common.StringCommon;
import org.example.backend.repository.IAccountRepository;
import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl
        implements IAccountService {

    private IAccountRepository repository;

    public AccountServiceImpl() {

        repository =
                new AccountRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Account> findByUsername(
            String username
    ) {

        return repository.findByUsername(
                username
        );
    }

    @Override
    public List<Department> findAllDepartments() {

        return repository.findAllDepartments();
    }

    @Override
    public List<Position> findAllPositions() {

        return repository.findAllPositions();
    }

    @Override
    public boolean existById(int id) {

        return repository.existById(id);
    }

    @Override
    public boolean existByUsername(
            String username
    ) {

        return repository.existByUsername(
                username
        );
    }

    @Override
    public boolean existByEmail(
            String email
    ) {

        return repository.existByEmail(
                email
        );
    }

    @Override
    public boolean save(Account account) {

        return repository.save(account);
    }

    @Override
    public boolean deleteById(int id) {

        return repository.deleteById(id);
    }

    @Override
    public boolean updateUsername(
            int id,
            String username
    ) {

        return repository.updateUsername(
                id,
                username
        );
    }

    @Override
    public String importCSV(String path) {
        File file = new File(path);
        if (!path.toLowerCase().endsWith(".csv") || !file.exists() || !file.isFile()) {
            return "File không đúng định dạng hoặc không tồn tại!";
        }

        List<Account> accounts = new ArrayList<>();
        List<String> invalidRows = new ArrayList<>();
        List<String> existedUsernames = new ArrayList<>();
        List<String> existedEmails = new ArrayList<>();

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
                    if (isHeader(fields)) {
                        continue;
                    }
                }

                if (fields.length < 6) {
                    invalidRows.add("Dòng dữ liệu không hợp lệ. Cần đủ 6 cột: " + line);
                    continue;
                }

                String email = fields[0].trim();
                String username = fields[1].trim();
                String fullName = fields[2].trim();
                String departmentIdText = fields[3].trim();
                String positionIdText = fields[4].trim();
                String createDateText = fields[5].trim();

                if (!email.matches(StringCommon.EMAIL_REGEX)) {
                    invalidRows.add("Email không hợp lệ: " + email);
                    continue;
                }

                if (existedEmails.contains(email.toLowerCase()) || repository.existByEmail(email)) {
                    invalidRows.add("Email đã tồn tại: " + email);
                    continue;
                }
                existedEmails.add(email.toLowerCase());

                if (username.length() < 5 || username.length() > 50 || !username.matches("^[A-Za-z0-9._-]+$")) {
                    invalidRows.add("Username không hợp lệ: " + username);
                    continue;
                }

                if (existedUsernames.contains(username.toLowerCase()) || repository.existByUsername(username)) {
                    invalidRows.add("Username đã tồn tại: " + username);
                    continue;
                }
                existedUsernames.add(username.toLowerCase());

                if (fullName.isEmpty()) {
                    invalidRows.add("Full name không được để trống: " + fullName);
                    continue;
                }

                int departmentId;
                int positionId;

                try {
                    departmentId = Integer.parseInt(departmentIdText);
                    positionId = Integer.parseInt(positionIdText);
                } catch (NumberFormatException e) {
                    invalidRows.add("department_id và position_id phải là số nguyên: " + line);
                    continue;
                }

                Department department = findDepartmentById(departmentId);
                Position position = findPositionById(positionId);

                if (department == null) {
                    invalidRows.add("Department ID không tồn tại: " + departmentId);
                    continue;
                }

                if (position == null) {
                    invalidRows.add("Position ID không tồn tại: " + positionId);
                    continue;
                }

                LocalDate createDate;
                try {
                    createDate = LocalDate.parse(createDateText);
                } catch (Exception e) {
                    invalidRows.add("create_date không đúng định dạng yyyy-MM-dd: " + createDateText);
                    continue;
                }

                Account account = new Account(
                        email,
                        username,
                        fullName,
                        department,
                        position,
                        createDate
                );

                accounts.add(account);
            }

            if (!invalidRows.isEmpty()) {
                exportErrorFile("account_error.csv", invalidRows);
            }

            if (accounts.isEmpty()) {
                return "File CSV không có dữ liệu Account hợp lệ!";
            }

            int count = 0;
            for (Account account : accounts) {
                boolean check = repository.save(account);
                if (!check) {
                    return "Lỗi khi lưu Account: " + account.getEmail();
                }
                count++;
            }

            if (!invalidRows.isEmpty()) {
                return "Import dữ liệu Account thành công: " + count + " dòng. Có " + invalidRows.size() + " dòng lỗi, file lỗi đã được lưu tại data/errors/account_error.csv";
            }

            return "Import dữ liệu Account thành công: " + count + " dòng.";

        } catch (Exception e) {
            return "Lỗi khi đọc file: " + e.getMessage();
        }
    }

    private boolean isHeader(String[] fields) {
        for (String field : fields) {
            String value = field.trim().replace("\uFEFF", "").toLowerCase();
            if (value.equals("email")
                    || value.equals("username")
                    || value.equals("full_name")
                    || value.equals("full name")
                    || value.equals("department_id")
                    || value.equals("position_id")
                    || value.equals("create_date")
                    || value.equals("create date")) {
                return true;
            }
        }

        return false;
    }

    private Department findDepartmentById(int id) {
        for (Department department : repository.findAllDepartments()) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null;
    }

    private Position findPositionById(int id) {
        for (Position position : repository.findAllPositions()) {
            if (position.getId() == id) {
                return position;
            }
        }
        return null;
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
            System.out.println("Không thể xuất file lỗi Account: " + e.getMessage());
        }
    }
}