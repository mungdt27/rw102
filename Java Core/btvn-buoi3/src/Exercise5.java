import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Exercise5 {
    // Question 1: Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình
    public static void question1() {
        System.out.println("===== Question 1 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số nguyên thứ nhất: ");
        int num1 = scanner.nextInt();
        System.out.print("Nhập số nguyên thứ hai: ");
        int num2 = scanner.nextInt();
        System.out.print("Nhập số nguyên thứ ba: ");
        int num3 = scanner.nextInt();
        System.out.println("Các số nguyên bạn vừa nhập là: " + num1 + ", " + num2 + ", " + num3);
    }

    // Question 2: Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình
    public static void question2() {
        System.out.println("===== Question 2 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số thực thứ nhất: ");
        double num1 = scanner.nextDouble();
        System.out.print("Nhập số thực thứ hai: ");
        double num2 = scanner.nextDouble();
        System.out.println("Các số thực bạn vừa nhập là: " + num1 + ", " + num2);
    }

    // Question 3: Viết lệnh cho phép người dùng nhập họ và tên
    public static void question3() {
        System.out.println("===== Question 3 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập họ và tên: ");
        String fullName = scanner.nextLine();
        System.out.println("Họ và tên của bạn là: " + fullName);
    }

    // Question 4: Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ
    public static void question4() {
        System.out.println("===== Question 4 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ngày sinh nhật: ");
        String birthDate = scanner.nextLine();
        System.out.println("Ngày sinh nhật của bạn là: " + birthDate);
    }

    // Question 5: Viết lệnh cho phép người dùng tạo account (viết thành method)
    // Đối với property Position, Người dùng nhập vào 1 2 3 4 5
    // và vào chương trình sẽ chuyển thành Position.Dev, Position.Test, Position.ScrumMaster, Position.PM
    public static Account question5() {
        System.out.println("===== Question 5 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập position (1-4): ");
        int positionId = scanner.nextInt();
        // Kiểm tra position
        while (positionId < 1 || positionId > 4) {
            System.out.print("Position không hợp lệ. Vui lòng nhập lại (1-4): ");
            positionId = scanner.nextInt();
        }
        Position.PositionName positionName;
        switch (positionId) {
            case 1:
                positionName = Position.PositionName.DEV;
                break;
            case 2:
                positionName = Position.PositionName.TEST;
                break;
            case 3:
                positionName = Position.PositionName.SCRUM_MASTER;
                break;
            default:
                positionName = Position.PositionName.PM;
        }
        Position position = new Position(positionId, positionName);

        // Chưa có department thì để null
        Account account = new Account(id, email, username, fullName, null, position, LocalDate.now());
        System.out.println("Tạo account thành công:");
        System.out.println(account);
        return account;
    }

    // Question 6: Viết lệnh cho phép người dùng tạo department (viết thành method)
    public static Department question6() {
        System.out.println("===== Question 6 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập id department: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập tên department: ");
        String name = scanner.nextLine();
        Department department = new Department(id, name);
        System.out.println("Tạo department thành công:");
        System.out.println(department);
        return department;
    }

    // Question 7: Nhập số chẵn từ console
    public static void question7() {
        System.out.println("===== Question 7 =====");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập một số chẵn: ");
        int number = scanner.nextInt();
        while (number % 2 != 0) {
            System.out.print("Đây không phải số chẵn. Vui lòng nhập lại: ");
            number = scanner.nextInt();
        }
        System.out.println("Số chẵn bạn nhập là: " + number);
    }

    // Question 8:
    // Viết chương trình thực hiện theo flow sau:
    // Bước 1:  Chương trình in ra text "mời bạn nhập vào chức năng muốn sử dụng"
    // Bước 2:  Nếu người dùng nhập vào 1 thì sẽ thực hiện tạo account
    // Nếu người dùng nhập vào 2 thì sẽ thực hiện chức năng tạo department
    // Nếu người dùng nhập vào số khác thì in ra text "Mời bạn nhập lại" và quay trở lại bước 1
    public static void question8() {
        System.out.println("===== Question 8 =====");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Mời bạn nhập vào chức năng muốn sử dụng:");
            System.out.println("1. Tạo account");
            System.out.println("2. Tạo department");
            System.out.print("Lựa chọn: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                question5();
                break;
            } else if (choice == 2) {
                question6();
                break;
            } else {
                System.out.println("Mời bạn nhập lại");
            }
        }
    }

    // Question 9:
    // Viết method cho phép người dùng thêm group vào account theo flow sau:
    // Bước 1: In ra tên các usernames của user cho người dùng xem
    // Bước 2: Yêu cầu người dùng nhập vào username của account
    // Bước 3:  In ra tên các group cho người dùng xem
    // Bước 4: Yêu cầu người dùng nhập vào tên của group
    // Bước 5:  Dựa vào username và tên của group người dùng vừa chọn, hãy thêm account vào group đó .
    public static GroupAccount[] question9(
            Account[] accounts,
            Group[] groups,
            GroupAccount[] groupAccounts
    ) {
        System.out.println("===== Question 9 =====");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Danh sách username:");
        for (Account account : accounts) {
            System.out.println("- " + account.getUsername());
        }
        scanner.nextLine();
        System.out.print("Nhập username của account: ");
        String username = scanner.nextLine();
        Account selectedAccount = null;
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                selectedAccount = account;
                break;
            }
        }
        if (selectedAccount == null) {
            System.out.println("Không tìm thấy account có username: " + username);
            return groupAccounts;
        }
        System.out.println("Danh sách group:");
        for (Group group : groups) {
            System.out.println("- " + group.getName());
        }
        System.out.print("Nhập tên group: ");
        String groupName = scanner.nextLine();
        Group selectedGroup = null;
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                selectedGroup = group;
                break;
            }
        }
        if (selectedGroup == null) {
            System.out.println("Không tìm thấy group có tên: " + groupName);
            return groupAccounts;
        }
        GroupAccount newGroupAccount = new GroupAccount(selectedGroup, selectedAccount, LocalDate.now());

        // Tạo mảng mới
        GroupAccount[] newGroupAccounts = new GroupAccount[groupAccounts.length + 1];

        for (int i = 0; i < groupAccounts.length; i++) {
            newGroupAccounts[i] = groupAccounts[i];
        }

        // Thêm GroupAccount mới
        newGroupAccounts[newGroupAccounts.length - 1] = newGroupAccount;
        System.out.println("Đã thêm " + selectedAccount.getUsername() + " vào group " + selectedGroup.getName());
        return newGroupAccounts;
    }

    // Question 10: Tiếp tục Question 8 và Question 9
    // Bổ sung thêm vào bước 2 của Question 8 như sau:
    // Nếu người dùng nhập vào 3 thì sẽ thực hiện chức năng thêm group vào account
    // Bổ sung thêm Bước 3 của Question 8 như sau:
    // Sau khi người dùng thực hiện xong chức năng ở bước 2 thì in ra dòng text
    // để hỏi người dùng "Bạn có muốn thực hiện chức năng khác không?".
    // Nếu người dùng chọn "Có" thì quay lại bước 1, nếu người dùng chọn "Không" thì kết thúc chương trình (sử dụng lệnh return để kết thúc chương trình)
    public static void question10(
            Account[] accounts,
            Group[] groups,
            GroupAccount[] groupAccounts
    ) {
        System.out.println("===== Question 10 =====");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Mời bạn nhập vào chức năng muốn sử dụng:");
            System.out.println("1. Tạo account");
            System.out.println("2. Tạo department");
            System.out.println("3. Thêm group vào account");
            System.out.print("Lựa chọn: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                question5();
            } else if (choice == 2) {
                question6();
            } else if (choice == 3) {
                groupAccounts = question9(accounts, groups, groupAccounts);
            } else {
                System.out.println("Mời bạn nhập lại");
                continue;
            }
            scanner.nextLine();
            System.out.print("Bạn có muốn thực hiện chức năng khác không? ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("Có")) {
                System.out.println("Kết thúc chương trình.");
                return;
            }
        }
    }

    // Question 11: Tiếp tục Question 10 Bổ sung thêm vào bước 2 của Question 8 như sau:
    // Nếu người dùng nhập vào 4 thì sẽ thực hiện chức năng thêm account vào 1 nhóm ngẫu nhiên, chức năng sẽ được cài đặt như sau:
    // Bước 1:  In ra tên các usernames của user cho người dùng xem
    // Bước 2:  Yêu cầu người dùng nhập vào username của account
    // Bước 3: Sau đó chương trình sẽ chọn ngẫu nhiên 1 group
    // Bước 4:  Thêm account vào group chương trình vừa chọn ngẫu nhiên
    public static void question11(
            Account[] accounts,
            Group[] groups,
            GroupAccount[] groupAccounts
    ) {
        System.out.println("===== Question 11 =====");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Danh sách username:");
        for (Account account : accounts) {
            System.out.println("- " + account.getUsername());
        }
        System.out.print("Nhập username của account: ");
        String username = scanner.nextLine();
        Account selectedAccount = null;
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                selectedAccount = account;
                break;
            }
        }
        if (selectedAccount == null) {
            System.out.println("Không tìm thấy account có username: " + username);
            return;
        }

        // Chọn ngẫu nhiên 1 group
        int randomIndex = new Random().nextInt(groups.length);
        Group randomGroup = groups[randomIndex];

        GroupAccount newGroupAccount = new GroupAccount(randomGroup, selectedAccount, LocalDate.now());

        // Tạo mảng mới
        GroupAccount[] newGroupAccounts = new GroupAccount[groupAccounts.length + 1];

        for (int i = 0; i < groupAccounts.length; i++) {
            newGroupAccounts[i] = groupAccounts[i];
        }

        // Thêm GroupAccount mới
        newGroupAccounts[newGroupAccounts.length - 1] = newGroupAccount;
        System.out.println("Đã thêm " + selectedAccount.getUsername() + " vào group " + randomGroup.getName());
    }
}
