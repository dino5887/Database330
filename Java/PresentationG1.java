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
         System.out.println("6. Exit\n");
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

               dl.addFaculty(facultyID, departmentID, lName, fName, uName, passwd, email);
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
               System.out.print("Enter String of student intersts \n MUST have comamas(,) seperating intersts: ");
               String sinterest = GetInput.readLine();
               System.out.println("The Major Number Choices are:");
               System.out.print("Enter Major: ");
               int smajor = GetInput.readInt();
               dl.addStudent(studentID, slName, sfName, suName, spasswd, semail, sinterest, smajor);
               break;

            case 5:
               System.out.println("--- Searching Faculty ---");
               System.out.println("Enter studentID: ");
               int studentIDf = GetInput.readInt();
               LinkedList<Integer> facultyInterested = dl.getFacultyIntersectionList(studentIDf);
               break;
            case 6:
               running = false;
               break;
            default:
               System.out.println("Not a valid input.");
         }
       }



   } 

  //  public int insertCandidate(){
  //   System.out.print("Candiate First Name: ");
  //   String fname = GetInput.readLine();
  //   System.out.print("Candiate Last Name: ");
  //   String lname = GetInput.readLine();
  //   System.out.print("Candiate Job Title: ");
  //   String jobTitle = GetInput.readLine();
  //   int newID = dl.addCandidate(fname,lname,jobTitle);
  //       return newID;
  //  }

  //  public int insertSkills(){
  //   System.out.print("Candiate ID: ");
  //   int canID = GetInput.readInt();
  //   System.out.print("Candiate Skill(s) seperated by commas NO spaces: ");
  //   String skillraw = GetInput.readLine();
  //   skillraw.strip();
  //   String[] skills = skillraw.split(",");
  //   int newID = dl.addSkill(canID,skills);
  //       return newID;
  //  }

  //  public LinkedList<Integer> getCandidateRecords(){
  //   candidateRecords = dl.getCandidateChoices();
  //   //System.out.println("\nThe Number of possible candidate records is -----> " + candidateRecords);
  //   return candidateRecords;
  // }

  // public void printSkills(int candidateID){
  //   //Created print skills here because datalayer is not supposed to touch presentation
  //   LinkedList<String> candidateInfo = dl.getCandidateSkill(candidateID);
  //   String heading = new String();
  //   heading = "\nDatabase candidateSkills\n";
  //   heading += "ID \tCandidate Name \t        Skills";
  //   System.out.println(heading);
  //   System.out.println(candidateInfo);
  //   if(candidateInfo.isEmpty()){
  //       System.out.println("This user has no skills! Please insert!");
  //   } else{
  //       System.out.println(candidateInfo.get(0) + "    " + candidateInfo.get(1) + "          " + candidateInfo.get(2));
  //   }
  //   //System.out.printf("%3d      %-22s %-60s",candidateInfo.get(0),candidateInfo.get(1),candidateInfo.get(2));
  // }


   public static void main(String [] args){
    PresentationG1 present = new PresentationG1(); 







    //   boolean running = true;
    //   int choice = 0;
    //   PresentationHW3 present = new PresentationHW3(); 
    //   System.out.println("Code by Becker, Brenden");
    //   while(running){
    //     System.out.println("Enter an option");
    //     System.out.println("1. Add Candidate");
    //     System.out.println("2. Add Skill");
    //     System.out.println("3. Print Candidate_Skill pairing");
    //     System.out.println("4. Exit\n");
    //     System.out.print("Selection: ");
    //     choice = GetInput.readInt();
    //     switch(choice){

    //         case 1:
    //             int newID = present.insertCandidate();
    //             System.out.println("Your candidate was inserted as ID number: " + newID);
    //             break;
    //         case 2:
    //             int output = present.insertSkills();
    //             if(output != 0){
    //                 System.out.println((output) + " Skills were inserted!");
    //             } else { 
    //                 System.out.println("Something went wrong with inserting your skills!");
    //             }
    //             break;
    //         case 3:
    //             System.out.println("Enter in Candidate ID");
    //             System.out.println("Choices include: ");
    //             System.out.println(present.getCandidateRecords() + "\n");
    //             System.out.print("Candiate ID : ");
    //             int canID = GetInput.readInt();
    //             present.printSkills(canID);
    //             break;

    //         case 4:
    //             running = false;
    //             break;

    //         default: System.out.println("Invalid entry, please try again.");


    //     }
    //   }
    //  System.exit(0);
   } 
} 