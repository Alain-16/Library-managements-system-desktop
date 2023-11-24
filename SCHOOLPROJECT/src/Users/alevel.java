package Users;

public class alevel {
    int id;
    String name;
    String depart;
    String stu_class;
    String level;
    String gender;


    public alevel(int id, String name, String depart, String stu_class, String level, String gender) {
        this.id = id;
        this.name = name;
        this.depart = depart;
        this.stu_class = stu_class;
        this.level = level;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
