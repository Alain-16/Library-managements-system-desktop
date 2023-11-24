package Alevel.Book;


/**
 * Book
 * This class is considered as model class consists of (GET &amp; SETTER) methods used to access attributes and data of
 * the database (bookname,booknumber,stream,level)
 *
 * @see <a href="https://www.w3schools.com/java/java_encapsulation.asp"></a>
 * for more information for more details on (GET &amp; SETTER) methods.
 */

public class book {
    int booknumber;
    String bookname;
    String depart;
    String stream;
    String status;

    public book(int booknumber, String bookname, String depart, String stream, String status) {
        this.bookname = bookname;
        this.booknumber = booknumber;
        this.depart = depart;
        this.stream = stream;
        this.status = status;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(int booknumber) {
        this.booknumber = booknumber;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
