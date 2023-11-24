package Users;

import java.util.Date;

public class book_return2 {

    int bookid;
    String bookname;
    String bkclass;
    Date issuedate;
    Date returndate;
    String status;
    int studentid;
    String studentname;
    String studentclass;
    String special;

    public book_return2(int id, String name, String bkclass, Date mydate, Date returndate, String status, int stuid, String stu_name, String stu_class, String special) {
        this.bookid = id;
        this.bookname = name;
        this.bkclass = bkclass;
        this.issuedate = mydate;
        this.returndate = returndate;
        this.status = status;
        this.studentid = stuid;
        this.studentname = stu_name;
        this.studentclass = stu_class;
        this.special = special;
    }


    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBkclass() {
        return bkclass;
    }

    public void setBkclass(String bkclass) {
        this.bkclass = bkclass;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(String studentclass) {
        this.studentclass = studentclass;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}


