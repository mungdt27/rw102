package org.example.backend.service.impl;

import org.example.backend.common.StringCommon;
import org.example.backend.repository.IAccountRepository;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.IPositionRepository;
import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.repository.impl.PositionRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.backend.service.ImportCSV;
import org.example.context.AccountContext;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountServiceImpl implements IAccountService, ImportCSV<Account, AccountContext> {

    private IAccountRepository accountRepository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.positionRepository = new PositionRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public boolean update(int id, String username) {
        return accountRepository.update(id, username);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean checkUsernameExists(String username, Integer id) {
        return accountRepository.checkUsernameExists(username, id);
    }

    @Override
    public boolean checkEmailExists(String email, Integer id) {
        return accountRepository.checkEmailExists(email, id);
    }

    @Override
    public boolean checkIdExists(int id) {
        return accountRepository.checkIdExists(id);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public String importCSV(String path) {
        String pathErr = "C:\\Users\\PNV\\Desktop\\rw102\\Java Core\\csv\\error_account.csv";
        List<Account> accounts = accountRepository.findAll();
        Map<String, Account> mapByEmail = accounts.stream()
                .collect(Collectors.toMap(acc -> acc.getEmail(), acc -> acc, (oldValue, newValue) -> oldValue));
        Map<String, Account> mapByUsername = accounts.stream()
                .collect(Collectors.toMap(acc -> acc.getUsername(), acc -> acc, (oldValue, newValue) -> oldValue));

        List<Department> departments = departmentRepository.findAll();
        Map<Integer, Department> mapByDepId = departments.stream()
                .collect(Collectors.toMap(dep -> dep.getId(), dep -> dep, (oldValue, newValue) -> oldValue));

        List<Position> positions = positionRepository.findAll();
        Map<Integer, Position> mapByPosId = positions.stream()
                .collect(Collectors.toMap(pos -> pos.getId(), pos -> pos, (oldValue, newValue) -> oldValue));

        AccountContext context = new AccountContext(mapByEmail, mapByUsername, mapByDepId, mapByPosId);
        return importCSV(path, pathErr, context);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountRepository.createAll(entities);
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
    public void validation(String line, List<Account> entities, List<String> dataErrors, AccountContext context) {
        List<String> errors = new ArrayList<>();
        String[] arrs = line.split(",", -1);
        if (arrs.length < 5) {
            dataErrors.add(line + ",Dữ liệu không đúng định dạng");
            return;
        }

        String email = arrs[0].trim();
        String username = arrs[1].trim();
        String fullName = arrs[2].trim();
        String depIdStr = arrs[3].trim();
        String posIdStr = arrs[4].trim();

        if (Objects.isNull(email) || email.isEmpty() || email.length() < 5 || email.length() > 50) {
            errors.add("Email phải từ 5-50 kí tự");
        }
        if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email ko đúng định dạng");
        }
        if (context.getMapByEmail().get(email) != null) {
            errors.add("Email đã tồn tại");
        }

        if (Objects.isNull(username) || username.isEmpty() || username.length() < 5 || username.length() > 50) {
            errors.add("Username phải từ 5-50 kí tự");
        }
        if (!username.matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
            errors.add("Username chỉ được chứa số và chữ");
        }
        if (context.getMapByUsername().get(username) != null) {
            errors.add("Username đã tồn tại");
        }

        if (Objects.isNull(fullName) || fullName.isEmpty() || fullName.length() < 5 || fullName.length() > 50) {
            errors.add("Fullname phải từ 5-50 kí tự");
        }
        if (!fullName.matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
            errors.add("Fullname chỉ được chứa số và chữ");
        }

        Department department = null;
        if (Objects.isNull(depIdStr) || !depIdStr.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("DepartmentID không đúng định dạng");
        } else {
            department = context.getMapByDepID().get(Integer.parseInt(depIdStr));
            if (Objects.isNull(department)) {
                errors.add("DepartmentID không tồn tại");
            }
        }

        Position position = null;
        if (Objects.isNull(posIdStr) || !posIdStr.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("PositionID không đúng định dạng");
        } else {
            position = context.getMapByPosID().get(Integer.parseInt(posIdStr));
            if (Objects.isNull(position)) {
                errors.add("PositionID không tồn tại");
            }
        }

        if (errors.isEmpty()) {
            Account account = new Account(username, fullName, email, department, position);
            entities.add(account);
            context.getMapByEmail().put(email, account);
            context.getMapByUsername().put(username, account);
        } else {
            dataErrors.add(line + "," + String.join("-", errors));
        }
    }
}