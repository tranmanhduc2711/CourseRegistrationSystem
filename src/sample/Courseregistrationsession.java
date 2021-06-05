package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Courseregistrationsession {
    private int id;
    private Date start;
    private Integer semester_id;
    private Date end;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getSemester_id()
    {
        return semester_id;
    }
    public void setSemester_id(Integer s)
    {
        this.semester_id=s;
    }
    @Basic
    @Column(name = "end", nullable = true)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courseregistrationsession that = (Courseregistrationsession) o;
        return id == that.id && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end);
    }
}
