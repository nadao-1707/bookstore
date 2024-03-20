package Entities;
public class Request {
    //borrower username
    String busername;
    //lendar useranme
    String lusername;
    String book;
    String status;

    public Request(String busername,String lusername,String book,String status){
        this.busername=busername;
        this.lusername=lusername;
        this.book=book;
        this.status=status;
    }
}
