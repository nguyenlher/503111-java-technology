import java.util.List;

public class Program {
    public static void main(String[] args) {
        
        List<Student> list = StudentUtils.generate();
        StudentUtils.print(list);

        StudentUtils.sortByName(list);
        StudentUtils.print(list);

        StudentUtils.sortByAvg(list);
        StudentUtils.print(list);

        StudentUtils.sortByAgeDescending(list);
        StudentUtils.print(list);
         
        StudentUtils.avg(list);
        StudentUtils.print(list);
       
        StudentUtils.goodStudentName(list);
        StudentUtils.print(list);
    }
}
