package swrdism.model;


import javax.persistence.*;
import java.sql.Time;

@Entity
public class WorldGymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String storeName;
    private String city;
    private String day;
    private String category;
    private String name;
    private String teacher;
    private Time startTime;
    private Time endTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }


    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCategory() {
        return category;
    }

    public String getDay() {
        return day;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getTeacher() {
        return teacher;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

}