package cn.meetdevelop.linear_search;

/**
 * description: LinearSearch
 * date: 2020/11/4 9:41
 * author: zgy
 * version: 1.0
 */
public class LinearSearch {

    public static <T> int search(T[] data, T target) {

        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private static class Student {
        private int id;
        private String sex;

        public Student(int id, String sex) {
            this.id = id;
            this.sex = sex;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Student) {
                Student student = (Student) obj;
                return student.id == id
                        && student.sex.equals(sex);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, "male");
        Student s2 = new Student(1, "female");
        Student s3 = new Student(2, "male");
        Student s4 = new Student(2, "female");
        Student s5 = new Student(3, "male");
        Student s6 = new Student(3, "female");

        Student[] students = {s1, s2, s3, s4, s5, s6};

        System.out.println(LinearSearch.search(students, s1));
    }
}
