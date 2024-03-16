import java.sql.*;
import java.util.Vector;

public class Server {
   //this code is here temporarily 
   Connect connection;

   Server(){
      connection=new Connect();
   }

   //submit borrowing request
   void submitRequest(String busername,String lusername,String book){
      try{
         Statement st = connection.con.createStatement();
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
         Statement st = connection.con.createStatement();
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
         Statement st = connection.con.createStatement();
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
         Statement st = connection.con.createStatement();
         String query="select * from requests where lusername='" + lusername + "' ";
         ResultSet result=st.executeQuery(query);
         Vector<request> requests=new Vector<request>();
         while(result.next()){
            String busermane=result.getString("busername");
            String book=result.getString("book");
            String status=result.getString("status");
            request r= new request(busermane,lusername,book,status); 
            requests.addElement(r);   
         }
         st.close();
         connection.closeConnection();
      } 
      catch (Exception e) {
         System.out.println(e.toString());
      }
   }

   
}
