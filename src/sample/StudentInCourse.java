package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class StudentInCourse {
    private int id;
    private String student_id;
    private Integer course_id;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id", nullable = true, length = 4)
    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String sid) {
        this.student_id = sid;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer cid) {
        this.course_id = cid;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInCourse sic = (StudentInCourse) o;
        return id == sic.id && Objects.equals(student_id, sic.student_id) && Objects.equals(course_id, sic.course_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, course_id);
    }
}
