public class Exercise1 {
    // IF-ELSE
        // Question 1: Kiểm tra account thứ 2
        //    Nếu không có phòng ban (tức là department == null) thì sẽ in ra text "Nhân viên này chưa có phòng ban"
        //    Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"

        public static void question1(Account[] accounts) {
            System.out.println("===== Question 1 =====");
            Account account = accounts[1];
            if (account.getDepartment() == null) {
                System.out.println("Nhân viên này chưa có phòng ban");
            } else {
                System.out.println("Phòng ban của nhân viên này là " + account.getDepartment().getName());
            }
        }

        // Question 2: Kiểm tra account thứ 2
        //    Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
        //    Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
        //    Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
        //    Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"

        public static void question2(
                Account[] accounts,
                GroupAccount[] groupAccounts
        ) {
            System.out.println("===== Question 2 =====");
            Account account = accounts[1];
            // Đếm số group mà account thứ 2 tham gia
            int count = 0;
            for (GroupAccount groupAccount : groupAccounts) {
                if (groupAccount.getAccount().getId() == account.getId()) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Nhân viên này chưa có group");
            } else if (count == 1 || count == 2) {
                System.out.print("Group của nhân viên này là ");
                for (GroupAccount groupAccount : groupAccounts) {
                    if (groupAccount.getAccount().getId() == account.getId()) {
                        System.out.print(groupAccount.getGroup().getName());
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else if (count == 3) {
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
            } else {
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
            }
        }

            // Question 3: Sử dụng toán tử ternary để làm Question 1
            public static void question3(Account[] accounts) {
                System.out.println("===== Question 3 =====");
                Account account = accounts[1];
                String result = account.getDepartment() == null
                        ? "Nhân viên này chưa có phòng ban"
                        : "Phòng ban của nhân viên này là "
                        + account.getDepartment().getName();
                System.out.println(result);
            }

        // Question 4: Sử dụng toán tử ternary để làm yêu cầu sau:
        //    Kiểm tra Position của account thứ 1
        //    Nếu Position = Dev thì in ra text "Đây là Developer"
        //    Nếu không phải thì in ra text "Người này không phải là Developer"

            public static void question4(Account[] accounts) {
                System.out.println("===== Question 4 =====");
                Account account = accounts[0];
                String result =
                        account.getPosition().getName() == Position.PositionName.DEV
                                ? "Đây là Developer"
                                : "Người này không phải là Developer";
                System.out.println(result);
            }

    // SWITCH CASE
        // Question 5: Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau:
        //    Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
        //    Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
        //    Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
        //    Còn lại in ra "Nhóm có nhiều thành viên"

        public static void question5(
                Group[] groups,
                GroupAccount[] groupAccounts
        ) {
            System.out.println("===== Question 5 =====");
            Group group = groups[0];
            // Đếm số account trong group thứ 1
            int count = 0;
            for (GroupAccount groupAccount : groupAccounts) {
                if (groupAccount.getGroup().getId() == group.getId()) {
                    count++;
                }
            }
            switch (count) {
                case 1:
                    System.out.println("Nhóm có một thành viên");
                    break;
                case 2:
                    System.out.println("Nhóm có hai thành viên");
                    break;
                case 3:
                    System.out.println("Nhóm có ba thành viên");
                    break;
                default:
                    System.out.println("Nhóm có nhiều thành viên");
            }
        }

        // Question 6: Sử dụng switch case để làm lại Question 2

        public static void question6(
                Account[] accounts,
                GroupAccount[] groupAccounts
        ) {
            System.out.println("===== Question 6 =====");
            Account account = accounts[1];
            // Đếm số group của account thứ 2
            int count = 0;
            for (GroupAccount groupAccount : groupAccounts) {
                if (groupAccount.getAccount().getId() == account.getId()) {
                    count++;
                }
            }
            switch (count) {
                case 0:
                    System.out.println("Nhân viên này chưa có group");
                    break;
                case 1:
                case 2:
                    System.out.print("Group của nhân viên này là ");
                    for (GroupAccount groupAccount : groupAccounts) {
                        if (groupAccount.getAccount().getId() == account.getId()) {
                            System.out.print(groupAccount.getGroup().getName());
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                    break;
                default:
                    System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
            }
        }

        // Question 7: Sử dụng switch case để làm lại Question 4

        public static void question7(Account[] accounts) {
            System.out.println("===== Question 7 =====");
            Account account = accounts[0];
            switch (account.getPosition().getId()) {
                case 1:
                    System.out.println("Đây là Developer");
                    break;
                default:
                    System.out.println("Người này không phải là Developer");
            }
        }

    // FOREACH
        // Question 8: In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ

        public static void question8(Account[] accounts) {
            System.out.println("===== Question 8 =====");
            for (Account account : accounts) {
                System.out.println("Email: " + account.getEmail());
                System.out.println("Full name: " + account.getFullName());
                if (account.getDepartment() == null) {
                    System.out.println("Phòng ban: Chưa có phòng ban");
                } else {
                    System.out.println("Phòng ban: " + account.getDepartment().getName());
                }
            }
        }

        // Question 9: In ra thông tin các phòng ban bao gồm: id và name

        public static void question9(Department[] departments) {
            System.out.println("===== Question 9 =====");
            for (Department department : departments) {
                System.out.println("Id: " + department.getId());
                System.out.println("Name: " + department.getName());
            }
        }

    // FOR
        // Question 10: In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
        //    Thông tin account thứ 1 là:
        //    Email: NguyenVanA@gmail.com
        //    Full name: Nguyễn Văn A
        //    Phòng ban: Sale
        //    Thông tin account thứ 2 là:
        //    Email: NguyenVanB@gmail.com
        //    Full name: Nguyễn Văn B
        //    Phòng ban: Marketting

        public static void question10(Account[] accounts) {
            System.out.println("===== Question 10 =====");
            for (int i = 0; i < accounts.length; i++) {
                Account account = accounts[i];
                System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                System.out.println("Email: " + account.getEmail());
                System.out.println("Full name: " + account.getFullName());
                if (account.getDepartment() == null) {
                    System.out.println("Phòng ban: Chưa có phòng ban");
                } else {
                    System.out.println("Phòng ban: " + account.getDepartment().getName());
                }
            }
        }

        // Question 11: In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
        //    Thông tin department thứ 1 là:
        //    Id: 1
        //    Name: Sale
        //    Thông tin department thứ 2 là:
        //    Id: 2
        //    Name: Marketing

        public static void question11(Department[] departments) {
            System.out.println("===== Question 11 =====");
            for (int i = 0; i < departments.length; i++) {
                Department department = departments[i];
                System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                System.out.println("Id: " + department.getId());
                System.out.println("Name: " + department.getName());
            }
        }

        // Question 12: Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10

        public static void question12(Department[] departments) {
            System.out.println("===== Question 12 =====");
            for (int i = 0; i < 2 && i < departments.length; i++) {
                Department department = departments[i];
                System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                System.out.println("Id: " + department.getId());
                System.out.println("Name: " + department.getName());
            }
        }

        // Question 13: In ra thông tin tất cả các account ngoại trừ account thứ 2

        public static void question13(Account[] accounts) {
            System.out.println("===== Question 13 =====");
            for (int i = 0; i < accounts.length; i++) {
                if (i != 1) {
                    Account account = accounts[i];
                    System.out.println("Email: " + account.getEmail());
                    System.out.println("Full name: " + account.getFullName());
                    if (account.getDepartment() == null) {
                        System.out.println("Phòng ban: Chưa có phòng ban");
                    } else {
                        System.out.println("Phòng ban: " + account.getDepartment().getName());
                    }
                }
            }
        }

        // Question 14: In ra thông tin tất cả các account có id < 4

            public static void question14(Account[] accounts) {
                System.out.println("===== Question 14 =====");
                for (int i = 0; i < accounts.length; i++) {
                    Account account = accounts[i];
                    if (account.getId() < 4) {
                        System.out.println("Email: " + account.getEmail());
                        System.out.println("Full name: " + account.getFullName());
                        if (account.getDepartment() == null) {
                            System.out.println("Phòng ban: Chưa có phòng ban");
                        } else {
                            System.out.println("Phòng ban: " + account.getDepartment().getName());
                        }
                    }
                }
            }

        // Question 15: In ra các số chẵn nhỏ hơn hoặc bằng 20

            public static void question15() {
                System.out.println("===== Question 15 =====");
                for (int i = 2; i <= 20; i += 2) {
                    System.out.println(i);
                }
            }
}
