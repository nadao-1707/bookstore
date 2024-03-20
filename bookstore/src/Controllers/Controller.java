package Controllers;

import java.sql.ResultSet;
import java.util.Vector;
import java.sql.*;

import Entities.Books_status;
import Entities.Request;

public class Controller {
    Connect connection;

    public Controller(){
        connection=new Connect();
    }

    //submit borrowing request
    void submitRequest(String busername,String lusername,String book){
        try{
        Statement st = connection.createStatement();
        String query="insert into student1 values('" + busername + "'," + lusername + ",'" + book + "', 'pending')";
        st.executeQuery(query);
        st.close();
        connection.closeConnection();
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //accept borrower request
    void acceptRequest(String busername,String lusername,String book){
        try{
            //accept borrower request
            Statement st = connection.createStatement();
            String query1="update requests set status='accepted' where busername='" + busername + "', lusername='" + lusername + "', book='" + book + "' ";
            st.executeUpdate(query1);
         
            //decrease the amount of available book copies
            String query2="select quantity from books where title='" + book + "'";
            int quanitity=st.executeUpdate(query2);
            quanitity--;
            String query3="update books set quantity='" + quanitity + "' where title='" + book + "' ";
            st.executeUpdate(query3);

            //spacify that the book is borrowed
            String query4="update books_status set status='borrowed' where book='" + book + "', owner='" + lusername + "' ";
            st.executeUpdate(query4);

            st.close();
            connection.closeConnection();
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //reject borrower request
    void rejectRequest(String busername,String lusername,String book){
        try{
            Statement st = connection.createStatement();
            String query="update requests set status='rejected' where busername='" + busername + "', lusername='" + lusername + "', book='" + book + "' ";
            st.executeUpdate(query);
            st.close();
            connection.closeConnection();
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //get user requests history as a lender
    //have to print the output for user
    void history(String lusername){
        try{
            Statement st = connection.createStatement();
            String query="select * from requests where lusername='" + lusername + "' ";
            ResultSet result=st.executeQuery(query);
            Vector<Request> requests=new Vector<Request>();
            while(result.next()){
               String busermane=result.getString("busername");
                String book=result.getString("book");
                String status=result.getString("status");
                Request r= new Request(busermane,lusername,book,status); 
                requests.addElement(r);   
            }
            st.close();
            connection.closeConnection();
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //show system statistics to admin
    void statistics(){
        try{
            Statement st = connection.createStatement();
            String query1="select * from requests";
            ResultSet result1=st.executeQuery(query1);
            Vector<Request> requests=new Vector<Request>();
        while(result1.next()){
            String busername=result1.getString("busername");
            String lusername=result1.getString("lusername");
            String book=result1.getString("book");
            String status=result1.getString("status");
            Request r= new Request(busername,lusername,book,status); 
            requests.addElement(r);   
        }

        String query2="select * from books_status";
        ResultSet result2=st.executeQuery(query2);
        Vector<Books_status> books=new Vector<Books_status>();
        while(result2.next()){
            String book=result2.getString("book");
            String owner=result2.getString("owner");
            String status=result2.getString("status");
            Books_status r= new Books_status(book,owner,status); 
            books.addElement(r);   
        }
        st.close();
        connection.closeConnection();
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
