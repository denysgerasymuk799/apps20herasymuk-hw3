package ua.edu.ucu;


import java.util.Objects;

class Student {
    private double GPA;
    private int year;
    private String name;
    private String surname;

    public Student(String name, String surname, double GPA, int year) {
        this.GPA = GPA;
        this.year = year;
        this.name = name;
        this.surname = surname;
    }

    public double getGPA() {
        return GPA;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    // added functions equals and hashCode
    // in order to DistinctDecorator can
    // compare Student objects
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Student st = (Student) o;
        return getSurname().equals(st.getSurname())
                && getName().equals(st.getName())
                && Math.abs(getGPA() - st.getGPA()) < 0.000001 && getYear() == st.getYear();
    }

    public int hashCode() {
        return Objects.hash(getSurname(), getName(), getGPA(), getYear());
    }

    @Override
    public String toString() {
        return "Student{name=" + name
                + ", surname=" + surname
                + ", " + "GPA=" + GPA
                + ", year=" + year + '}';
    }
}
