package Olevel.Subjects;

public class bookOL {
    int booknumber;
    String bookname;
    String stream;
    String status;

    public bookOL(int booknumber, String bookname, String stream, String status) {
        this.bookname = bookname;
        this.booknumber = booknumber;

        this.stream = stream;
        this.status = status;
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


