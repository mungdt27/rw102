import java.util.Scanner;

public class Exercise4 {
    static Scanner scanner = new Scanner(System.in);
//    Question 1: Nhập một xâu kí tự, đếm số lượng các từ trong xâu kí tự đó
//    (các từ có thể cách nhau bằng nhiều khoảng trắng );
    public static void question1() {
        System.out.println("===== Question 1 =====");
        System.out.print("Nhập một xâu kí tự: ");
        String chuoi = scanner.nextLine();

        // Xóa khoảng trắng đầu và cuối
        chuoi = chuoi.trim();
        if (chuoi.isEmpty()) {
            System.out.println("Số lượng từ: 0");
        } else {
            String[] words = chuoi.split("[ ]+");
            System.out.println("Số lượng từ: " + words.length);
        }
    }

//    Question 2: Nhập hai xâu kí tự s1, s2 nối xâu kí tự s2 vào sau xâu s1;
    public static void question2() {
        System.out.println("===== Question 2 =====");
        System.out.print("Nhập xâu s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Nhập xâu s2: ");
        String s2 = scanner.nextLine();

        String result = s1 + s2;
        System.out.println("Kết quả: " + result);
    }

//    Question 3: Viết chương trình để người dùng nhập vào tên và kiểm tra,
//    nếu tên chữ viết hoa chữ cái đầu thì viết hoa lên.
    public static void question3() {
        System.out.println("===== Question 3 =====");
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        if (name.length() > 0) {
            String firstChar = name.substring(0, 1).toUpperCase();
            String restOfName = name.substring(1);
            String result = firstChar + restOfName;
            System.out.println("Tên sau khi viết hoa chữ cái đầu: " + result);
        } else {
            System.out.println("Tên không được để trống.");
        }
    }

//    Question 4: Viết chương trình để người dùng nhập vào tên in từng ký tự trong tên của người dùng ra
//    VD: Người dùng nhập vào "Nam", hệ thống sẽ in ra
//        "Ký tự thứ 1 là: N"
//        "Ký tự thứ 2 là: A"
//        "Ký tự thứ 3 là: M"
    public static void question4() {
        System.out.println("===== Question 4 =====");
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + character);
        }
    }

//    Question 5: Viết chương trình để người dùng nhập vào họ,
//    sau đó yêu cầu người dùng nhập vào tên và hệ thống sẽ in ra họ và tên đầy đủ.
    public static void question5() {
        System.out.println("===== Question 5 =====");
        System.out.print("Nhập họ: ");
        String lastName = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String firstName = scanner.nextLine();

        String fullName = lastName.trim() + " " + firstName.trim();
        System.out.println("Họ và tên đầy đủ: " + fullName);
    }

//    Question 6: Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ
//    và sau đó hệ thống sẽ tách ra họ, tên , tên đệm
//    VD: Người dùng nhập vào "Nguyễn Văn Nam"
//    Hệ thống sẽ in ra
//    "Họ là: Nguyễn"
//            "Tên đệm là: Văn"
//            "Tên là: Nam"
    public static void question6() {
        System.out.println("===== Question 6 =====");
        System.out.print("Nhập họ và tên đầy đủ: ");
        String fullName = scanner.nextLine();

        String[] words = fullName.trim().split("[ ]+");
        if (words.length >= 3) {
            String lastName = words[0];
            String middleName = words[1];
            String firstName = words[2];

            System.out.println("Họ là: " + lastName);
            System.out.println("Tên đệm là: " + middleName);
            System.out.println("Tên là: " + firstName);
        } else {
            System.out.println("Vui lòng nhập đầy đủ họ, tên đệm và tên.");
        }
    }

//    Question 7: Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ
//    và chuẩn hóa họ và tên của họ như sau:
//    a) Xóa dấu cách ở đầu và cuối và giữa của chuỗi người dùng nhập vào
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "nguyễn văn nam"
//    b) Viết hoa chữ cái mỗi từ của người dùng
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "Nguyễn Văn Nam"
    public static void question7() {
        System.out.println("===== Question 7 =====");
        System.out.print("Nhập họ và tên: ");
        String fullName = scanner.nextLine();

        // Xóa dấu cách ở đầu và cuối
        fullName = fullName.trim();

        // Xóa dấu cách ở giữa, thay thế nhiều dấu cách liên tiếp bằng một dấu cách
        fullName = fullName.replaceAll("[ ]+", " ");

        // Viết hoa chữ cái đầu của mỗi từ
        String[] words = fullName.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1).toLowerCase();
        }

        String normalizedFullName = String.join(" ", words);
        System.out.println("Họ và tên đã chuẩn hóa: " + normalizedFullName);
    }

//    Question 8: In ra tất cả các group có chứa chữ "Java"
    public static void question8() {
        System.out.println("===== Question 8 =====");
        String[] groups = {"Java Developer", "Python Developer", "JavaScript Developer", "Java Advanced", "C# Developer"};

        System.out.println("Các group có chứa chữ 'Java':");
        for (String group : groups) {
            if (group.contains("Java")) {
                System.out.println(group);
            }
        }
    }

//    Question 9: In ra tất cả các group "Java"
    public static void question9() {
        System.out.println("===== Question 9 =====");
        String[] groups = {"Java", "Python", "JavaScript", "Java", "C#"};

        System.out.println("Các group 'Java':");
        for (String group : groups) {
            if (group.equals("Java")) {
                System.out.println(group);
            }
        }
    }

//    Question 10: Kiểm tra 2 chuỗi có là đảo ngược của nhau hay không.
//    Nếu có xuất ra “OK” ngược lại “KO”.
//    Ví dụ “word” và “drow” là 2 chuỗi đảo ngược nhau.
    public static void question10() {
        System.out.println("===== Question 10 =====");
        System.out.print("Nhập chuỗi thứ nhất: ");
        String str1 = scanner.nextLine();
        System.out.print("Nhập chuỗi thứ hai: ");
        String str2 = scanner.nextLine();

        String reversedStr1 = new StringBuilder(str1).reverse().toString();
        if (reversedStr1.equals(str2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

//    Question 11: Count special Character. Tìm số lần xuất hiện ký tự "a" trong chuỗi
    public static void question11() {
        System.out.println("===== Question 11 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();

        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == 'a') {
                count++;
            }
        }
        System.out.println("Số lần xuất hiện ký tự 'a': " + count);
    }

//    Question 12: Reverse String. Đảo ngược chuỗi sử dụng vòng lặp
    public static void question12() {
        System.out.println("===== Question 12 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();

        String reversedStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }
        System.out.println("Chuỗi đảo ngược: " + reversedStr);
    }

//    Question 13: String not contains digit
//    Kiểm tra một chuỗi có chứa chữ số hay không, nếu có in ra false ngược lại true.
//    Ví dụ:
//            "abc" => true
//            "1abc", "abc1", "123", "a1bc", null => false
    public static void question13() {
        System.out.println("===== Question 13 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();

        boolean containsDigit = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                containsDigit = true;
                break;
            }
        }
        System.out.println(containsDigit ? "false" : "true");
    }

//    Question 14: Replace character
//    Cho một chuỗi str, chuyển các ký tự được chỉ định sang một ký tự khác cho trước.
//    Ví dụ: "VTI Academy" chuyển ký tự 'e' sang '*' kết quả " VTI Acad*my"
    public static void question14() {
        System.out.println("===== Question 14 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();
        System.out.print("Nhập ký tự cần thay thế: ");
        char oldChar = scanner.nextLine().charAt(0);
        System.out.print("Nhập ký tự mới: ");
        char newChar = scanner.nextLine().charAt(0);

        String replacedStr = str.replace(oldChar, newChar);
        System.out.println("Chuỗi sau khi thay thế: " + replacedStr);
    }

//    Question 15: Revert string by word
//    Đảo ngược các ký tự của chuỗi cách nhau bởi dấu cách mà không dùng thư viện.
//    Ví dụ: " I am developer " => "developer am I".
//    Các ký tự bên trong chỉ cách nhau đúng một dấu khoảng cách.
//    Gợi ý: Các bạn cần loại bỏ dấu cách ở đầu và cuối câu, thao tác cắt chuỗi theo dấu cách
    public static void question15() {
        System.out.println("===== Question 15 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();

        // Loại bỏ dấu cách ở đầu và cuối chuỗi
        str = str.trim();

        // Cắt chuỗi theo dấu cách
        String[] words = str.split("[ ]+");

        // Đảo ngược các từ
        StringBuilder reversedStr = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedStr.append(words[i]);
            if (i > 0) {
                reversedStr.append(" ");
            }
        }

        System.out.println("Chuỗi sau khi đảo ngược: " + reversedStr.toString());
    }

//    Question 16: Cho một chuỗi str và số nguyên n >= 0. Chia chuỗi str ra làm các phần bằng nhau với n ký tự.
//    Nếu chuỗi không chia được thì xuất ra màn hình “KO”.
    public static void question16() {
        System.out.println("===== Question 16 =====");
        System.out.print("Nhập chuỗi: ");
        String str = scanner.nextLine();
        System.out.print("Nhập n ký tự: ");
        int n = Integer.parseInt(scanner.nextLine());

        if (str.length() % n != 0) {
            System.out.println("KO");
        } else {
            for (int i = 0; i < str.length(); i += n) {
                System.out.print(str.substring(i, i + n) + " ");
            }
            System.out.println();
        }
    }
}
