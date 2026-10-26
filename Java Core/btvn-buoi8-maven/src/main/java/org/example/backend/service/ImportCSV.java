package org.example.backend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// T: đối tượng muốn làm viejc Account, Department, ...
// K:đối tượng chứa các dữ liệu đẻ validation
public interface ImportCSV<T, K> {
    void saveAll(List<T> entities);
    void exportFile(String header, List<String> dataErrors, String pathErr);
    void validation(String line, List<T> entities, List<String> dataErrors, K context);

    // ko cần ghi đè ở class implement,
    default String importCSV(String path, String pathErr, K context) {
        // 1. check định dạng file
        File file = new File(path);
        if (!path.endsWith(".csv")) {
            return "File ko đúng định dạng!";
        }
        if (!file.exists()) {
            return "File ko tồn tại!";
        }
        // 2. đọc file
        List<String> dataErrors = new ArrayList<>();// list các data kèm lỗi
        List<T> entities = new ArrayList<>(); // ds các dữ liệu dc lưu vào DB
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String header = br.readLine();
            String line = null;
            // 2.2 dùng while để đọc từng dòng
            while ((line = br.readLine()) != null) {
                this.validation(line, entities, dataErrors, context);
            }
            // 2.3 lưu dữ liệu vào DB
            if (!entities.isEmpty()) {
                this.saveAll(entities);
            }
            // 2.4 xuát file lỗi
            if (!dataErrors.isEmpty()) {
                this.exportFile(header, dataErrors, pathErr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.format("Import thành công %d, import thất bại %d, %s"
                , entities.size(), dataErrors.size(), !dataErrors.isEmpty() ?"Đã xuat ra file lỗi " + pathErr : "");
    }




}