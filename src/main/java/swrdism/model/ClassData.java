package swrdism.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ClassData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj){
        if( obj == null) {
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }
        final ClassData other = (ClassData) obj;
        if(!Objects.equals(this.name, other.name)){
            return false;
        }
        return true;
    }
}
