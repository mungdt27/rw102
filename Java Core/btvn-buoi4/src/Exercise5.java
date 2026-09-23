public class Exercise5 {
//    Question 5: So sánh 2 phòng ban thứ 1 và phòng ban thứ 2 xem có bằng nhau không
//    (bằng nhau khi tên của 2 phòng ban đó bằng nhau)
    public static void question5(Department[] departments) {
        System.out.println("===== Question 5 =====");
        Department department1 = departments[0];
        Department department2 = departments[1];

        if (department1.getName().equals(department2.getName())) {
            System.out.println("Hai phòng ban bằng nhau");
        } else {
            System.out.println("Hai phòng ban không bằng nhau");
        }
    }

//    Question 6: Khởi tạo 1 array phòng ban gồm 5 phòng ban,
//    sau đó in ra danh sách phòng ban theo thứ tự tăng dần theo tên (sắp xếp theo vần ABCD)
//    VD:
//    Accounting
//    Boss of director
//    Marketing
//    Sale
//    Waiting room
    public static void question6() {
        System.out.println("===== Question 6 =====");
        Department[] departments = {
                new Department(1, "Marketing"),
                new Department(2, "Waiting room"),
                new Department(3, "Accounting"),
                new Department(4, "Sale"),
                new Department(5, "Boss of director")
        };

        // Sắp xếp tăng dần theo tên
        for (int i = 0; i < departments.length - 1; i++) {
            for (int j = i + 1; j < departments.length; j++) {
                if (departments[i].getName().compareToIgnoreCase(departments[j].getName()) > 0) {
                    Department temp = departments[i];
                    departments[i] = departments[j];
                    departments[j] = temp;
                }
            }
        }

        System.out.println("+-----+----------------------+");
        System.out.printf("|%5s|%22s|\n", "ID", "Department Name");
        System.out.println("+-----+----------------------+");

        for (Department department : departments) {
            System.out.printf("|%5d|%22s|\n", department.getId(), department.getName());
        }
        System.out.println("+-----+----------------------+");
    }

//    Question 7: Khởi tạo 1 array học sinh gồm 5 Phòng ban, sau đó in ra dan sách phòng ban được sắp xếp theo tên
//    VD:
//    Accounting
//    Boss of director
//    Marketing
//    waiting room
//    Sale
    public static void question7() {
        System.out.println("===== Question 7 =====");
        Department[] departments = {
                new Department(1, "Marketing"),
                new Department(2, "waiting room"),
                new Department(3, "Accounting"),
                new Department(4, "Sale"),
                new Department(5, "Boss of director")
        };

        // Sắp xếp tăng dần theo tên
        for (int i = 0; i < departments.length - 1; i++) {
            for (int j = i + 1; j < departments.length; j++) {
                if (departments[i].getName().compareToIgnoreCase(departments[j].getName()) > 0) {
                    Department temp = departments[i];
                    departments[i] = departments[j];
                    departments[j] = temp;
                }
            }
        }

        System.out.println("+-----+----------------------+");
        System.out.printf("|%5s|%22s|\n", "ID", "Department Name");
        System.out.println("+-----+----------------------+");
        for (Department department : departments) {
            System.out.printf("|%5d|%22s|\n", department.getId(), department.getName());
        }
        System.out.println("+-----+----------------------+");
    }
}
