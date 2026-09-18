public class Exercise1 {
    // WHILE
    //    Question 16: Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với lệnh break, continue

    // Question 10: In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
    //    Thông tin account thứ 1 là:
    //    Email: NguyenVanA@gmail.com
    //    Full name: Nguyễn Văn A
    //    Phòng ban: Sale

    public static void question10(Account[] accounts) {
        System.out.println("===== Question 10 =====");
        int i = 0;
        while (true) {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
            i++;
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
        int i = 0;
        while (true) {
            if (i >= departments.length) {
                break;
            }
            Department department = departments[i];
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + department.getId());
            System.out.println("Name: " + department.getName());
            i++;
        }
    }

    // Question 12: Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10

    public static void question12(Department[] departments) {
        System.out.println("===== Question 12 =====");
        int i = 0;
        while (true) {
            if (i >= 2 || i >= departments.length) {
                break;
            }
            Department department = departments[i];
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + department.getId());
            System.out.println("Name: " + department.getName());
            i++;
        }
    }

    // Question 13: In ra thông tin tất cả các account ngoại trừ account thứ 2

    public static void question13(Account[] accounts) {
        System.out.println("===== Question 13 =====");
        int i = 0;
        while (true) {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            i++;
            if (i == 1) {
                continue;
            }
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
        }
    }

    // Question 14: In ra thông tin tất cả các account có id < 4

    public static void question14(Account[] accounts) {
        System.out.println("===== Question 14 =====");
        int i = 0;
        while (true) {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            i++;
            if (account.getId() >= 4) {
                continue;
            }
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
        }
    }

    // Question 15: In ra các số chẵn nhỏ hơn hoặc bằng 20

    public static void question15() {
        System.out.println("===== Question 15 =====");
        int i = 0;
        while (true) {
            if (i > 20) {
                break;
            }
            if (i % 2 != 0) {
                i++;
                continue;
            }
            System.out.println(i);
            i++;
        }
    }
    // DO-WHILE
    //    Question 17: Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với lệnh break, continue

    // Question 10: In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
    //    Thông tin account thứ 1 là:
    //    Email: NguyenVanA@gmail.com
    //    Full name: Nguyễn Văn A
    //    Phòng ban: Sale

    public static void question10b(Account[] accounts) {
        System.out.println("===== Question 10b =====");
        int i = 0;
        do {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
            i++;
        } while (true);
    }

    // Question 11: In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
    //    Thông tin department thứ 1 là:
    //    Id: 1
    //    Name: Sale
    //    Thông tin department thứ 2 là:
    //    Id: 2
    //    Name: Marketing

    public static void question11b(Department[] departments) {
        System.out.println("===== Question 11b =====");
        int i = 0;
        do {
            if (i >= departments.length) {
                break;
            }
            Department department = departments[i];
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + department.getId());
            System.out.println("Name: " + department.getName());
            i++;
        } while (true);
    }

    // Question 12: Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10

    public static void question12b(Department[] departments) {
        System.out.println("===== Question 12b =====");
        int i = 0;
        do {
            if (i >= 2 || i >= departments.length) {
                break;
            }
            Department department = departments[i];
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + department.getId());
            System.out.println("Name: " + department.getName());
            i++;
        } while (true);
    }

    // Question 13: In ra thông tin tất cả các account ngoại trừ account thứ 2

    public static void question13b(Account[] accounts) {
        System.out.println("===== Question 13b =====");
        int i = 0;
        do {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            i++;
            if (i == 2) {
                continue;
            }
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
        } while (true);
    }

    // Question 14: In ra thông tin tất cả các account có id < 4

    public static void question14b(Account[] accounts) {
        System.out.println("===== Question 14b =====");
        int i = 0;
        do {
            if (i >= accounts.length) {
                break;
            }
            Account account = accounts[i];
            i++;
            if (account.getId() >= 4) {
                continue;
            }
            System.out.println("Email: " + account.getEmail());
            System.out.println("Full name: " + account.getFullName());
            if (account.getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có phòng ban");
            } else {
                System.out.println("Phòng ban: " + account.getDepartment().getName());
            }
        } while (true);
    }

    // Question 15: In ra các số chẵn nhỏ hơn hoặc bằng 20

    public static void question15b() {
        System.out.println("===== Question 15b =====");
        int i = 0;
        do {
            if (i > 20) {
                break;
            }
            if (i % 2 != 0) {
                i++;
                continue;
            }
            System.out.println(i);
            i++;
        } while (true);
    }
}
