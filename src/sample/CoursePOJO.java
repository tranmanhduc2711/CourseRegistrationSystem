package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CoursePOJO {
    private int id;
    private String subjectId;
    private Integer semester;
    private String teacher;
    private String room;
    private String dayOfWeek;
    private String sessionId;
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
    @Column(name = "subject_id", nullable = true, length = 10)
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "semester", nullable = true)
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }



    @Basic
    @Column(name = "teacher", nullable = true, length = 30)
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "room", nullable = true, length = 5)
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Basic
    @Column(name = "dayOfWeek", nullable = true, length = 3)
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Basic
    @Column(name = "session_id", nullable = true)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "max", nullable = true)
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePOJO course = (CoursePOJO) o;
        return id == course.id && Objects.equals(subjectId, course.subjectId) && Objects.equals(semester, course.semester)  && Objects.equals(teacher, course.teacher) && Objects.equals(room, course.room) && Objects.equals(dayOfWeek, course.dayOfWeek) && Objects.equals(sessionId, course.sessionId) && Objects.equals(max, course.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, semester, teacher, room, dayOfWeek, sessionId, max);
    }
}