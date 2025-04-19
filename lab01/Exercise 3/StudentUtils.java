import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentUtils {

    public static List<Student> generate() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Minh", 29, 8, 7.7, 6.5));
        list.add(new Student("Huong", 26, 7.7, 9, 8.1));
        list.add(new Student("Lan", 31, 9, 9.2, 7.0));
        list.add(new Student("Khoa", 24, 8.5, 9, 7.7));
        list.add(new Student("Duy", 18, 7, 6.9, 6.5));
        list.add(new Student("Tu", 29, 6.5, 7.7, 8.4));
        list.add(new Student("Diem", 22, 8, 8.3, 6.9));
        list.add(new Student("Linh", 21, 5, 6.6, 7.7));

        return list;
    }

    public static void print(List<Student> list) {
        System.out.println("\n============ BEGIN ============ ");
        list.forEach(System.out::println); // example of Method Reference
        System.out.println("============ END ============ \n");
    }


    public static void sortByName(List<Student> list) {
        Collections.sort(list, (o1, o2) -> o1.name.compareTo(o2.name));
    }

    public static void sortByAvg(List<Student> list) {
        Collections.sort(list, (o1, o2) -> {
            if (o1.average() > o2.average()) return 1;
            if (o1.average() < o2.average()) return -1;
            return 0;
        });
    }

    public static void sortByAgeDescending(List<Student> list) {
        Collections.sort(list, (o1, o2) -> {
            if (o1.age > o2.age) return -1; // Giảm dần
            if (o1.age < o2.age) return 1;
            return 0;
        });
    }

    public static double avg(List<Student> list) {
        return list.stream()
                .mapToDouble(Student::average)
                .average()
                .orElse(0);
    }

    public static List<String> goodStudentName(List<Student> list) {
        return list.stream()
                .filter(student -> student.isGoodStudent())
                .map(student -> student.name)
                .collect(Collectors.toList());
    }
}