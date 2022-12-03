// Brenden Becker, Sophia Castiglione, Hunter Ross
// ISTE 330
// Group Project Team #1

import java.sql.*;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  

import java.math.BigInteger;
import java.security.MessageDigest;

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
      //int choice = 0;
      //PresentationG1 present = new PresentationG1();
      while(running){
      
        JPanel initMenu = new JPanel(new GridLayout(9,2));
   	  JLabel lblAddFaculty = new JLabel("1. Add Faculty");
        JLabel lblDelFaculty = new JLabel("2. Delete Faculty");
        JLabel lblUpFaculty = new JLabel("3. Update Faculty");
        JLabel lblAddStudent = new JLabel("4. Add Student");
        JLabel lblSearchFaculty = new JLabel("5. Search Faculty Interests");
        JLabel lblAddFacAb = new JLabel("6. Add Faculty Abstract");
        JLabel lblSearchStudent = new JLabel("7. Search Student Interests");
        JLabel lblExit = new JLabel("8. Exit");
        JTextField tfSelection = new JTextField("");
   	  
   	  initMenu.add(lblAddFaculty);
   	  initMenu.add(lblDelFaculty);
        initMenu.add(lblUpFaculty);
        initMenu.add(lblAddStudent);
        initMenu.add(lblSearchFaculty);
        initMenu.add(lblAddFacAb);
        initMenu.add(lblSearchStudent);
        initMenu.add(lblExit);
        initMenu.add(tfSelection);
        
        JOptionPane.showMessageDialog(null, initMenu, "Select a menu option.", JOptionPane.QUESTION_MESSAGE);
        
        
        
        String choice = tfSelection.getText();
        int intChoice = Integer.parseInt(choice);
        
         /*System.out.println("Enter an option");
         System.out.println("1. Add Faculty");
         System.out.println("2. Delete Faculty");
         System.out.println("3. Update Faculty");
         System.out.println("4. Add Student");
         System.out.println("5. Search Faculty Interests");
         System.out.println("6. Add Faculty Abstract");
         System.out.println("7. Search Student Interests");
         System.out.println("8. Exit\n");
         System.out.print("Selection: ");
         choice = GetInput.readInt();*/
         
         switch(intChoice){
            case 1:
           
              JPanel addFacMenu = new JPanel(new GridLayout(10,2));
         	  JLabel lblFID = new JLabel("Enter facultyID: ");
              JLabel lblDID = new JLabel("Enter departmentID: ");
              JLabel lblLName = new JLabel("Enter Last Name: ");
              JLabel lblFName = new JLabel("Enter First Name: ");
              JLabel lblUName = new JLabel("Enter Username: ");
              JLabel lblPasswd = new JLabel("Enter Password: ");
              JLabel lblEmail = new JLabel("Enter Email: ");
              JLabel lblBuliding = new JLabel("Enter Bulding: ");
              JLabel lblOffice = new JLabel("Enter Office: ");
              JLabel lblSwotch = new JLabel("Input 0 for none, 1 for Interest: ");
              
              JTextField tfFID = new JTextField("");
              JTextField tfDID = new JTextField("");
              JTextField tfLName = new JTextField("");
              JTextField tfFName = new JTextField("");
              JTextField tfUName = new JTextField("");
              JTextField tfPasswd = new JPasswordField("");
              JTextField tfEmail = new JTextField("");
              JTextField tfBuilding = new JTextField("");
              JTextField tfOffice = new JTextField("");
              JTextField tfSwotch = new JTextField("");
              
              addFacMenu.add(lblFID);
              addFacMenu.add(tfFID);
              addFacMenu.add(lblDID);
              addFacMenu.add(tfDID);
              addFacMenu.add(lblLName);
              addFacMenu.add(tfLName);
              addFacMenu.add(lblFName);
              addFacMenu.add(tfFName);
              addFacMenu.add(lblUName);
              addFacMenu.add(tfUName);
              addFacMenu.add(lblPasswd);
              addFacMenu.add(tfPasswd);
              addFacMenu.add(lblEmail);
              addFacMenu.add(tfEmail);
              addFacMenu.add(lblBuliding);
              addFacMenu.add(tfBuilding);
              addFacMenu.add(lblOffice);
              addFacMenu.add(tfOffice);
              addFacMenu.add(lblSwotch);
              addFacMenu.add(tfSwotch);
              
              
              JOptionPane.showMessageDialog(null, addFacMenu, "Adding a new faculty member.", JOptionPane.QUESTION_MESSAGE);
 
              String facInput = tfFID.getText();
              int facultyID = Integer.parseInt(facInput);
              String deptIDInput = tfDID.getText();
              int departmentID = Integer.parseInt(deptIDInput);
              String lName = tfLName.getText();
              String fName = tfFName.getText();
              String uName = tfUName.getText();
              String passwd = tfPasswd.getText();
              String passwdEC = encrypt(passwd);
              String email = tfEmail.getText();
              String building = tfBuilding.getText();
              String officeInput = tfOffice.getText();
              int office = Integer.parseInt(officeInput);
              String swotchInput = tfSwotch.getText();
              int swotch = Integer.parseInt(swotchInput);
            
               /*System.out.println("--- Adding a new faculty member ---");
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
               int swotch = GetInput.readInt();*/
               
               if(swotch == 0){
                  dl.addFaculty(facultyID, departmentID, lName, fName, uName, passwdEC, email, building, office, "none");
               }
               if(swotch == 1){
                  System.out.print("Interest: ");
                  String Interest = GetInput.readLine();
                  dl.addFaculty(facultyID, departmentID, lName, fName, uName, passwdEC, email, building, office, Interest);
               }
               break;
            case 2:
               JPanel delFacMenu = new JPanel(new GridLayout(1,2));
         	   JLabel lblUpdateID = new JLabel("Enter facultyID to delete: ");
               JTextField tfUpdateID = new JTextField("");
               
               delFacMenu.add(lblUpdateID);
               delFacMenu.add(tfUpdateID);
               
               JOptionPane.showMessageDialog(null, delFacMenu, "Deleting a faculty member.", JOptionPane.QUESTION_MESSAGE);
               
               String updateIDInput = tfUpdateID.getText();
               int updateID = Integer.parseInt(updateIDInput);
               
               /*System.out.println("--- Deleting a faculty member ---");
               System.out.println("Enter faculty ID to delete: ");
               int updateID = GetInput.readInt(); */
               
               dl.deleteFaculty(updateID);
               break;
            case 3:
               System.out.println("Update");
               break;
            case 4:
               JPanel newStudentMenu = new JPanel(new GridLayout(8,2));
               
         	   JLabel lblStuID = new JLabel("Enter StudentID: ");
               JLabel lblSLName = new JLabel("Enter Last Name: ");
               JLabel lblSFName = new JLabel("Enter First Name: ");
               JLabel lblSUName = new JLabel("Enter Username ");
               JLabel lblSPasswd = new JLabel("Enter Password: ");
               JLabel lblSEmail = new JLabel("Enter Email: ");
               JLabel lblSInterest = new JLabel("Enter String of student intersts. MUST have comamas(,) seperating intersts \"science,tech\" :  ");
               //JLabel lblSMajorNum = new JLabel("The Major Number Choices are: 1000, 1002, 1004, 1006, 1008, 1010, 1012, 1014, 1016, 1018, 2000, 2002, 2004, 2006, 2008, 3000, 3002, 3004, 3006, 3008, 3010");
               JLabel lblSMajor = new JLabel("Enter Major: ");
               //JLabel lblempty = new JLabel("");
               
               JTextField tfStuID = new JTextField("");
               JTextField tfSLName = new JTextField("");
               JTextField tfSFName = new JTextField("");
               JTextField tfSUName = new JTextField("");
               JTextField tfSPasswd = new JPasswordField("");
               JTextField tfSEmail = new JTextField("");
               JTextField tfSInterest = new JTextField("");
               JTextField tfSMajor = new JTextField("");
               
               newStudentMenu.add(lblStuID);
               newStudentMenu.add(tfStuID);
               newStudentMenu.add(lblSLName);
               newStudentMenu.add(tfSLName);
               newStudentMenu.add(lblSFName);
               newStudentMenu.add(tfSFName);
               newStudentMenu.add(lblSUName);
               newStudentMenu.add(tfSUName);
               newStudentMenu.add(lblSPasswd);
               newStudentMenu.add(tfSPasswd);
               newStudentMenu.add(lblSEmail);
               newStudentMenu.add(tfSEmail);
               newStudentMenu.add(lblSInterest);
               newStudentMenu.add(tfSInterest);
               //newStudentMenu.add(lblSMajorNum);
               //newStudentMenu.add(lblempty);
               newStudentMenu.add(lblSMajor);
               newStudentMenu.add(tfSMajor);
               
               JOptionPane.showMessageDialog(null, newStudentMenu, "Add a new Student.", JOptionPane.QUESTION_MESSAGE);
               
               String stuIDInput = tfStuID.getText();
               int studentID = Integer.parseInt(stuIDInput);
               String slName = tfSLName.getText();
               String sfName = tfSFName.getText();
               String suName = tfSUName.getText();
               String spasswd = tfSPasswd.getText();
               String semail = tfSEmail.getText();
               String sinterest = tfSInterest.getText();
               String stuMajorInput = tfSMajor.getText();
               int smajor = Integer.parseInt(stuMajorInput);
            
               /*System.out.println("--- Adding a new Student ---");
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
               int smajor = GetInput.readInt();*/
               
               dl.addStudent(studentID, slName, sfName, suName, spasswd, semail, sinterest, smajor);
               break;

            case 5:
               JPanel stuIntrestSearch = new JPanel(new GridLayout(1,2));
         	   JLabel lblSSearchID = new JLabel("Enter studentID: ");
               JTextField tfSSearchID = new JTextField("");
               
               stuIntrestSearch.add(lblSSearchID);
               stuIntrestSearch.add(tfSSearchID);
               
               JOptionPane.showMessageDialog(null, stuIntrestSearch, "Searching faculty interest.", JOptionPane.QUESTION_MESSAGE);
               
               String studentIDfInput = tfSSearchID.getText();
               int studentIDf = Integer.parseInt(studentIDfInput);

               /*System.out.println("--- Searching For ALL Faculty Interested ---");
               System.out.println("Enter studentID: ");
               int studentIDf = GetInput.readInt();*/
               
               LinkedList<Integer> facultyInterested = dl.getFacultyIntersectionList(studentIDf);
               
               if(!facultyInterested.isEmpty()){
                  while(!facultyInterested.isEmpty()){
                     LinkedList<String> faculty = dl.getFaculty(facultyInterested.pop());
                        while(!faculty.isEmpty()){
                        JPanel interestFound = new JPanel(new GridLayout(4,1));
                        JLabel lblFIName = new JLabel("Faculty Name: " + faculty.pop());
                        JLabel lblFIEmail = new JLabel("Email: " + faculty.pop());
                        JLabel lblFIBulding = new JLabel("Bulding: " + faculty.pop());
                        JLabel lblFIOffice = new JLabel("Office Number: " + faculty.pop());
                        interestFound.add(lblFIName);
                        interestFound.add(lblFIEmail);
                        interestFound.add(lblFIBulding);
                        interestFound.add(lblFIOffice);

                        JOptionPane.showMessageDialog(null, interestFound, "Faculty Interst Found.", JOptionPane.WARNING_MESSAGE);
                        
                        /*System.out.println(" --- Faculty Interested Found --- ");
                        //System.out.println("Faculty ID: " + facultyInterested + " Faculty List: " + facul );
                        System.out.println("Faculty Name: " + faculty.pop());
                        System.out.println("Email: " + faculty.pop());
                        System.out.println("Building: " + faculty.pop());
                        System.out.println("Office Number: " + faculty.pop() + "\n");*/
                        }
                  }


               } else{
                  System.out.println("No Matches found in the database!");
               }
               break;
            case 6:
               JPanel addAbstractMenu = new JPanel(new GridLayout(3,1));
               
         	   JLabel lblAFacID = new JLabel("Enter facultyID: ");
               JLabel lblATitle = new JLabel("Enter Abstract Title: ");
               JLabel lblAcontent = new JLabel("Enter Abstract Content: ");
               
               JTextField tfAFacID = new JTextField("");
               JTextField tfATitle = new JTextField("");
               JTextField tfAcontent = new JTextField("");
               
               addAbstractMenu.add(lblAFacID);
               addAbstractMenu.add(tfAFacID);
               addAbstractMenu.add(lblATitle);
               addAbstractMenu.add(tfATitle);
               addAbstractMenu.add(lblAcontent);
               addAbstractMenu.add(tfAcontent);
               
               JOptionPane.showMessageDialog(null, addAbstractMenu, "Faculty Abstract Found.", JOptionPane.QUESTION_MESSAGE);
               
               String facultyID2Input = tfAFacID.getText();
               int facultyID2 = Integer.parseInt(facultyID2Input);
               String abstractTitle = tfATitle.getText();
               String abstractInput = tfAcontent.getText();
            
               
            
            /*System.out.println("--- Adding a new Student ---");
            System.out.println("Enter facultyID: ");
            int facultyID2 = GetInput.readInt();
            System.out.println("Enter Abstract Title: ");
            String abstractTitle = GetInput.readLine();
            System.out.println("Enter Abstract Content: ");
            String abstractInput = GetInput.readLine();*/


               dl.addAbstractInterest(facultyID2, abstractTitle, abstractInput);
               break;

            case 7:
            
               JPanel searchFacIntrestMenu = new JPanel(new GridLayout(1,2));
         	   JLabel lblTopics = new JLabel("Enter A List of Key Topics Seperated by ',': ");
               JTextField tfTopics = new JTextField("");
               
               searchFacIntrestMenu.add(lblTopics);
               searchFacIntrestMenu.add(tfTopics);
               
               JOptionPane.showMessageDialog(null, searchFacIntrestMenu, "Searching for ALL Students Interested.", JOptionPane.QUESTION_MESSAGE);
               
               String topicInput = tfTopics.getText();

               /*System.out.println("--- Searching For ALL Students Interested ---");
               System.out.println("Enter A List of Key Topics Seperated by ',': ");
               String topicInput = GetInput.readLine();*/
               
               LinkedList<Integer> studentsInterested = dl.getStudentsInterested(topicInput);
               
               if(!studentsInterested.isEmpty()){
                  while(!studentsInterested.isEmpty()){
                     LinkedList<String> gotStudent = dl.getStudent(studentsInterested.pop());
                        while(!gotStudent.isEmpty()){
                        
                        JPanel jstudentsInterested = new JPanel(new GridLayout(4,1));
                        
                        JLabel lblStuName1 = new JLabel("Student Name: " + gotStudent.pop());
                        JLabel lblStuEmail1 = new JLabel("Email: " + gotStudent.pop());
                        JLabel lblStuID1 = new JLabel("Student ID: " + gotStudent.pop());
                        JLabel lblStuInterest1 = new JLabel("Student Interests: " + gotStudent.pop());
                        
                        jstudentsInterested.add(lblStuName1);
                        jstudentsInterested.add(lblStuEmail1);
                        jstudentsInterested.add(lblStuID1);
                        jstudentsInterested.add(lblStuInterest1);

                        JOptionPane.showMessageDialog(null, jstudentsInterested, "Students Intersted.", JOptionPane.WARNING_MESSAGE);
                       
                        /*System.out.println(" --- Student Interested Found --- ");
                        System.out.println("Student Name: " + gotStudent.pop());
                        System.out.println("Email: " + gotStudent.pop());
                        System.out.println("STudent ID: " + gotStudent.pop());
                        System.out.println("Student Interests: " + gotStudent.pop() + "\n");*/
                        
                        }
                  }


               } else{
                  JPanel noStudentsInterested = new JPanel(new GridLayout(1,1));
                  JLabel lblNoMatch = new JLabel("No Mathces found in the database!!");
                  noStudentsInterested.add(lblNoMatch);
                  JOptionPane.showMessageDialog(null, noStudentsInterested, "No Students Found.", JOptionPane.WARNING_MESSAGE);
                  

               
                  //System.out.println("No Matches found in the database!");
               }
               break;
               
            case 8:
               running = false;
               break;
            default:
               JPanel jInvalidInput = new JPanel(new GridLayout(1,1));
               JLabel lblInvalid = new JLabel("Not a valid input.");
               jInvalidInput.add(lblInvalid);
               JOptionPane.showMessageDialog(null, jInvalidInput, "Invalid input.", JOptionPane.WARNING_MESSAGE);
               
               //System.out.println("Not a valid input.");
         }
       }



   } 

   public static void main(String [] args){
      PresentationG1 present = new PresentationG1(); 
   } 

   public static String encrypt(String secret){
      String sha1 = "";
      String value = new String(secret);
      try {
          MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(value.getBytes("utf8"));
        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
      } 
      catch (Exception e){
          e.printStackTrace();
      }// end of catch

      return sha1;
   }//end of encrypt
} 