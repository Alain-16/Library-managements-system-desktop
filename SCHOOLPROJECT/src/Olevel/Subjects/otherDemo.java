package Olevel.Subjects;

public class otherDemo {
    int id;
    String bookname;
    String depart;
    String booktype;
    String status;



    public otherDemo(int id, String bookname, String depart, String booktype, String status){
        this.id = id;
        this.bookname = bookname;
        this.depart=depart;
        this.booktype=booktype;
        this.status=status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
