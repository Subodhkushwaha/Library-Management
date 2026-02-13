package Library_Management;

import java.sql.*;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        try{
            Connection connection = DriverManager.getConnection(DBConfigLibrary.url,DBConfigLibrary.username,DBConfigLibrary.password);

            System.out.println("\n=====LIBRARY MANAGEMENT SYSTEM =====");
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println("1. Add new book : ");
                System.out.println("2. Delete book : ");
                System.out.println("3. search book : ");
                System.out.println("4. Book avaliblity : ");
                System.out.println("5. Change book details : ");
                System.out.println("6. Exit : ");


                System.out.println("Enter your choice here : ");
                int choice = sc.nextInt();

                switch (choice){

                    case 1: // Add new book
                        System.out.println("Enter Book id : ");
                        int BookId = sc.nextInt();

                        System.out.println("Enter book Title : ");
                        String Title = sc.next();

                        System.out.println("Enter Book Author");
                        String Aur = sc.next();

                        System.out.println("Enter Publisher");
                        String Publisher = sc.next();

                        PreparedStatement ps1 = connection.prepareStatement("INSERT INTO library (book_id, title, author, publisher) VALUES (?,?,?,?)");
                        ps1.setInt(1,BookId);
                        ps1.setString(2,Title);
                        ps1.setString(3,Aur);
                        ps1.setString(4,Publisher);

                        int row = ps1.executeUpdate();
                        System.out.println(row+" recorded updated.");
                        break;

                    case 2: // Delete book

                        System.out.println("Enter book id : ");
                        int BookId1 = sc.nextInt();

                        PreparedStatement ps2 = connection.prepareStatement("delete from library where id =?");
                        ps2.setInt(1,BookId1);

                       int BookId2 = ps2.executeUpdate();
                       if(BookId2 > 0){
                           System.out.println("Deleted SuccessFully!");
                       }else{
                           System.out.println("id not found in Database");
                       }
                       break;

                    case 3: // search book
                        System.out.println("Enter book id : ");
                        int BookId3 = sc.nextInt();

                        PreparedStatement ps3 = connection.prepareStatement("select * from library where book_id = ?");
                        ps3.setInt(1,BookId3);

                        ResultSet rs = ps3.executeQuery();
                        if (rs.next()) {
                            System.out.println("Book found");
                        } else {
                            System.out.println("Book not found in Database");
                        }
                        break;

                    case 4: // Book avaliblity
                        System.out.println("Enter book id : ");
                        int BookId4 = sc.nextInt();

                        PreparedStatement ps4 = connection.prepareStatement("select * from library where book_id = ?");
                        ps4.setInt(1,BookId4);
                        ResultSet rs1 = ps4.executeQuery();
                        int count = 0;
                        if (rs1.next()) {
                            count++;
                            System.out.println(count+" book found");
                            System.out.println("Book ID: " + rs1.getInt("book_id"));
                            System.out.println("Book Name: " + rs1.getString("Title"));
                        } else {
                            System.out.println(count+" book found");
                        }
                        break;

                    case 5: // Change book detail
                        System.out.println("Enter book id : ");
                        int BookId5 = sc.nextInt();
                        System.out.println("Enter name what you want to change : ");
                        String newName = sc.next();

                        PreparedStatement ps5 = connection.prepareStatement("update library set title=? where book_id=?");
                        ps5.setString(1, newName);
                        ps5.setInt(2, BookId5);

                        int rs2 = ps5.executeUpdate();
                        if (rs2 > 0) {
                            System.out.println("Changed.");
                        } else {
                            System.out.println("id not found in Database");
                        }
                        break;


                    case 6:
                        System.out.println("Exiting.....");
                        System.exit(0);
                }

            }


        }catch (SQLException e) {
            System.out.println("Database Error"+e);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
