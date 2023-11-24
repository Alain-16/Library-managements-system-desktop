package Users;

import java.util.Date;

public class bookDemoOL {

    int bookid;
    String bookname;
    String bkclass;
    String period;
    Date issuedate;
    String status;
    int studentid;
    String studentname;
    String studentclass;
    String special;

    public bookDemoOL(int id, String name, String bkclass, String period, Date mydate, String status, int stuid, String stu_name, String stu_class, String special) {
        this.bookid = id;
        this.bookname = name;
        this.bkclass = bkclass;
        this.period = period;
        this.issuedate = mydate;
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

    private String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
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
