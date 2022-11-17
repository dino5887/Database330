// Brenden Becker, Sophia Castiglione, Hunter Ross
// ISTE 330
// Group Project Team #1

import java.sql.*;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  

public class PresentationG1 {

   DataG1 dl = new DataG1();
   private int columns;
   private int rows;
   
   private LinkedList<Integer> candidateRecords;
   private ResultSet rs;

   public static Font myFontForOutput = new Font("Courier", Font.BOLD, 20);


   public PresentationG1(){
      System.out.println("Connecting to the Group 1 database . . .");
      
      
      
      
      JPanel Inputbox = new JPanel(new GridLayout(3,2));
	  JLabel lblUser     = new JLabel("Username -> ");
	  JLabel lblPassword = new JLabel("Password -> ");
	  JTextField tfUser     = new JTextField("root");
	          //JTextField tfPassword = new JTextField("");
	  JTextField tfPassword = new JPasswordField("");
	  JLabel lblDatabase    = new JLabel("Database ->");
	  JTextField tfDatabase = new JTextField("");
	  
	  Inputbox.add(lblUser);
	  Inputbox.add(tfUser);
	  Inputbox.add(lblPassword);
	  Inputbox.add(tfPassword);
	  Inputbox.add(lblDatabase);
	  Inputbox.add(tfDatabase);
	  
	  lblUser.setFont(myFontForOutput);
	  tfUser.setFont(myFontForOutput);
	  tfUser.setForeground(Color.BLUE);
	  lblPassword.setFont(myFontForOutput);
	  tfPassword.setFont(myFontForOutput);
	  tfPassword.setForeground(Color.BLUE);
	  lblDatabase.setFont(myFontForOutput);
	  tfDatabase.setFont(myFontForOutput);
	  tfDatabase.setForeground(Color.BLUE);
	  
	  
	  JOptionPane.showMessageDialog(null, Inputbox,
	  		 "Default password is \"student\", default db is \"group1\"", JOptionPane.QUESTION_MESSAGE);
	  
	  
	   String userName = tfUser.getText();

     String database = new String();
	   String databaseInput = tfDatabase.getText();   
	      
	       
	   String password = new String();
	   String passwordInput = new String();
	           
	   passwordInput = tfPassword.getText();
	           
	   if (passwordInput.equalsIgnoreCase("")) {
	  		 password = "student";
	    } 
	    else 
	    {
	  	     password = passwordInput;
        }

        if (databaseInput.equalsIgnoreCase("")) {
          database = "group1";
       } 
       else 
       {
            database = databaseInput;
         }
      
      
      
    
      dl.connect(userName,password,database);
      System.out.println("You have connected to the Group 1 database!");

      boolean running = true;
      int choice = 0;
      //PresentationG1 present = new PresentationG1();
      while(running){
         System.out.println("Enter an option");
         System.out.println("1. Add Faculty");
         System.out.println("2. Delete Faculty");
         System.out.println("3. Update Faculty");
         System.out.println("4. Add Student");
         System.out.println("5. Search Faculty Interests");
         System.out.println("6. Add Faculty Abstract");
         System.out.println("7. Exit\n");
         System.out.print("Selection: ");
         choice = GetInput.readInt();
         switch(choice){
            case 1:
               System.out.println("--- Adding a new faculty member ---");
               System.out.println("Enter facultyID: ");
               int facultyID = GetInput.readInt();
               System.out.println("Enter departmentID: ");
               int departmentID = GetInput.readInt();
               System.out.println("Enter last name: ");
               String lName = GetInput.readLine();
               System.out.print("Enter First Name: ");
               String fName = GetInput.readLine();
               System.out.print("Enter Username: ");
               String uName = GetInput.readLine();
               System.out.print("Enter Password: ");
               String passwd = GetInput.readLine();
               System.out.print("Enter Email: ");
               String email = GetInput.readLine();
               System.out.print("Enter Building: ");
               String building = GetInput.readLine();
               System.out.print("Enter Office: ");
               int office = GetInput.readInt();
               System.out.print("Input 0 for none, 1 for Interest");
               int swotch = GetInput.readInt();
               if(swotch == 0){
                  dl.addFaculty(facultyID, departmentID, lName, fName, uName, passwd, email, building, office, "none");
               }
               if(swotch == 1){
                  System.out.print("Interest: ");
                  String Interest = GetInput.readLine();
                  dl.addFaculty(facultyID, departmentID, lName, fName, uName, passwd, email, building, office, Interest);
               }
               break;
            case 2:
               System.out.println("--- Deleting a faculty member ---");
               System.out.println("Enter faculty ID to delete: ");
               int updateID = GetInput.readInt(); 
               dl.deleteFaculty(updateID);
               break;
            case 3:
               System.out.println("Update");
               break;
            case 4:
               System.out.println("--- Adding a new Student ---");
               System.out.println("Enter studentID: ");
               int studentID = GetInput.readInt();
               System.out.println("Enter last name: ");
               String slName = GetInput.readLine();
               System.out.print("Enter First Name: ");
               String sfName = GetInput.readLine();
               System.out.print("Enter Username: ");
               String suName = GetInput.readLine();
               System.out.print("Enter Password: ");
               String spasswd = GetInput.readLine();
               System.out.print("Enter Email: ");
               String semail = GetInput.readLine();
               System.out.print("Enter String of student intersts \n MUST have comamas(,) seperating intersts \"science,tech\" : ");
               String sinterest = GetInput.readLine();
               System.out.println("The Major Number Choices are: 1000, 1002, 1004, 1006, 1008, 1010, 1012, 1014, 1016, 1018, 2000, 2002, 2004, 2006, 2008, 3000, 3002, 3004, 3006, 3008, 3010");
               System.out.print("Enter Major: ");
               int smajor = GetInput.readInt();
               dl.addStudent(studentID, slName, sfName, suName, spasswd, semail, sinterest, smajor);
               break;

            case 5:
               System.out.println("--- Searching For ALL Faculty Interested ---");
               System.out.println("Enter studentID: ");
               int studentIDf = GetInput.readInt();
               LinkedList<Integer> facultyInterested = dl.getFacultyIntersectionList(studentIDf);
               
               if(!facultyInterested.isEmpty()){
                  while(!facultyInterested.isEmpty()){
                     LinkedList<String> faculty = dl.getFaculty(facultyInterested.pop());
                        while(!faculty.isEmpty()){
                        System.out.println(" --- Faculty Interested Found --- ");
                        //System.out.println("Faculty ID: " + facultyInterested + " Faculty List: " + facul );
                        System.out.println("Faculty Name: " + faculty.pop());
                        System.out.println("Email: " + faculty.pop());
                        System.out.println("Building: " + faculty.pop());
                        System.out.println("Office Number: " + faculty.pop() + "\n");
                        }
                  }


               } else{
                  System.out.println("No Matches found in the database!");
               }
               break;
            case 6:
            System.out.println("--- Adding a new Student ---");
            System.out.println("Enter facultyID: ");
            int facultyID2 = GetInput.readInt();
            System.out.println("Enter Abstract Title: ");
            String abstractTitle = GetInput.readLine();
            System.out.println("Enter Abstract Content: ");
            String abstractInput = GetInput.readLine();


            dl.addAbstractInterest(facultyID2, abstractTitle, abstractInput);
            break;
               
            case 7:
               running = false;
               break;
            default:
               System.out.println("Not a valid input.");
         }
       }



   } 

   public static void main(String [] args){
    PresentationG1 present = new PresentationG1(); 

   } 
} 