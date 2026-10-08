package frontend;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập email: ");
        while (true) {
            String email = sc.nextLine();
            // biểu thức chính quy       "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"// fo+mat của email
            if (email.matches("^[0-9]+$")) {
                System.out.println("đúng định dạng");
            } else {
                System.out.println("sai định dạng");
            }
        }

    }
}