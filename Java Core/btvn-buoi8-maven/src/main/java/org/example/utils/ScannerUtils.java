package org.example.utils;

import org.example.backend.common.StringCommon;

import java.util.Objects;
import java.util.Scanner;

public class ScannerUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputText(Integer min, Integer max, String message) {
        while (true) {
            String inputText = scanner.nextLine();
            if (inputText.trim().length() < min || inputText.trim().length() > max) {
                System.err.println(message);
                continue;
            }
            return inputText;
        }
    }

    public static int inputInt(boolean isNegative, Integer min, Integer max, String message) {
        while (true) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.err.println(message);
            } else {
                int inputInt = scanner.nextInt();
                scanner.nextLine();
                if (!isNegative && inputInt < 0) {
                    System.err.println(message);
                    continue;
                }
                if (Objects.nonNull(min) && inputInt < min) {
                    System.out.println("Vui lòng nhập số nguyên > " + min);
                    continue;
                }
                if (Objects.nonNull(max) && inputInt > max) {
                    System.out.println("Vui lòng nhập số nguyên < " + max);
                    continue;
                }
                return inputInt;
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            System.out.println("Nhập email: ");
            String email = inputText(5, 50, "Độ dài email từ 5- 50 kí tự! Nhập lại:");
            // a@lgcns.com
            if (!email.matches(StringCommon.EMAIL_REGEX)) {// dong@gmail.com
                System.err.println("email không đúng định dạng");
                continue;
            } else {
                return email;
            }
        }
    }
}