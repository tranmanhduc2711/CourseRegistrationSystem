package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Courseinfo {
    private int id;
    private Integer courseId;
    private String subject_id ;
    private String subject_name;
    private Integer credits ;
    private String teacher ;
    private String room ;
    private String dayOfWeek;
    private String session_id ;
    private Integer max;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id){
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String id_name) {
        this.subject_name = id_name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(int cre) {
        this.credits = cre;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String t) {
        this.teacher = t;
    }

    public String getRoom(){return  this.room};

    public void setRoom(String r) {
        this.room = r;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dow) {
        this.dayOfWeek = dow;
    }
    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String s) {
        this.session_id = s;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(int m) {
        this.max = m;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courseinfo that = (Courseinfo) o;
        return id == that.id && Objects.equals(courseId, that.courseId) && Objects.equals(subject_id, that.subject_id) && Objects.equals(subject_name, that.subject_name)&&Objects.equals(teacher, that.teacher)&&Objects.equals(credits, that.credits) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId,subject_id,subject_name,credits,teacher,room,dayOfWeek,session_id,max);
    }
}
