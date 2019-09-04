package swrdism.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ClassNameData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
        final ClassNameData other = (ClassNameData) obj;
        if(!Objects.equals(this.name, other.name)){
            return false;
        }
        return true;
    }
}
