// Brenden Becker, Sophia Castiglione, Hunter Ross
// ISTE 330
// Group Project Team #1

// This code is the Data Layer
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class DataG1{
   private Connection conn;
   private ResultSet rs;
   private Statement stmt;
   private String sql;
   private Integer resultAlter;
   private Integer resultAdd;
   private Integer resultRem;
   private Integer resultUpd;

   final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

   public DataG1(){
   }

   public boolean connect(String user, String password, String database){
      conn = null;
     
      String url = "jdbc:mysql://localhost/" + database;
      url = url + "?serverTimezone=UTC";

      try{
         Class.forName(DEFAULT_DRIVER);
         System.out.println("CLASSPATH is set correctly!");
         conn = DriverManager.getConnection(url, user, password);
         System.out.println("\nCreated Connection!\n");
      } catch(ClassNotFoundException cnfe){
		 System.out.println("ERROR, CAN NOT CONNECT!!");
         System.out.println("Class");
         System.out.println("ERROR MESSAGE-> "+cnfe);
         System.exit(0);
      } catch(SQLException sqle){
		 System.out.println("ERROR SQLExcepiton in connect()");
		 System.out.println("ERROR MESSAGE -> "+sqle);
         System.exit(0);
      }

      return (conn!=null);
   }

   // Inserts a new faculty member
   public int addFaculty(int facultyID, int departmentID, String lName, String fName, String uName, String passwd, String email){
      int result = 0;
            try{
               // prepared statement
               String sql = "INSERT INTO faculty (facultyID, departmentID, lastName, firstName, username, password, email) VALUES (?,?,?,?,?,?,?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setInt(1, facultyID);
               ps.setInt(2, departmentID);
               ps.setString(3, fName);
               ps.setString(4, lName);
               ps.setString(5, uName);
               ps.setString(6, passwd);
               ps.setString(7, email);
               
               result = ps.executeUpdate();
               String stmt = "SELECT LAST_INSERT_ID()";
               rs = conn.prepareStatement(stmt).executeQuery();
               rs.next();
               return(rs.getInt(1));
               
            }// End of try
            catch(SQLException sqle){
               sqle.printStackTrace();
               return -1;
            }
    }

    // Function to delete a faculty
   public int deleteFaculty(int facultyID){
      int rows = 0;
      System.out.println("--- DELETE started ---");
      try {    
         // Prepared sql statement   
         sql =  "DELETE FROM faculty WHERE facultyID=?";
         PreparedStatement stmt = conn.prepareStatement(sql);
      
         // bind values into the parameters
         stmt.setInt(1, facultyID);
      
         System.out.println("Command to be executed: " + stmt);
         rows = stmt.executeUpdate();
         System.out.println("--- DELETE finished ---");
    
      }// end of try block
      catch(SQLException sqle){
         System.out.println("SQL ERROR");
         System.out.println("DELETE FAILED!");
         System.out.println("ERROR MESSAGE: "+sqle);
         sqle.printStackTrace();
         return(0);
      }
     catch(Exception e) {
         System.out.println("Error occured in deleteFaculty method");
         System.out.println("ERROR MESSAGE: "+e);
         e.printStackTrace();
         return(0);
      }
      return (rows);
   }// end of deleteFaculty function



   public int addStudent(int studentID, String lName, String fName, String uName, String passwd, String email, String interestUncut, int major){
      int result = 0;
            try{
               // prepared statement
               String sql = "INSERT INTO student (studentID, lastName, firstName, username, password, email) VALUES (?,?,?,?,?,?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setInt(1, studentID);
               ps.setString(2, fName);
               ps.setString(3, lName);
               ps.setString(4, uName);
               ps.setString(5, passwd);
               ps.setString(6, email);
               
               result = ps.executeUpdate();

               if(addStudentInterest(studentID, interestUncut) < 1){
                  System.out.println("addStudentInterest Failed!");
               }
               if(addStudentMajor(studentID, major) < 1){
                  System.out.println("addStudentMajor Failed!");
               }


               return(result);
               
            }// End of try
            catch(SQLException sqle){
               sqle.printStackTrace();
               return -1;
            }
    }

    public int addStudentInterest(int studentID, String interestUncut){
      int result = 0;
            try{
               // prepared statement
               String sql = "INSERT INTO studentinterests (studentID, keyword) VALUES (?, ?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setInt(1, studentID);
               ps.setString(2, interestUncut);
               result = ps.executeUpdate();
               return(result);
               
            }// End of try
            catch(SQLException sqle){
               sqle.printStackTrace();
               return -1;
            }
    }


    public int addStudentMajor(int studentID, int major){
      int result = 0;
            try{
               // prepared statement
               String sql = "INSERT INTO studentmajor (studentID, majorID) VALUES (?, ?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setInt(1, studentID);
               ps.setInt(2, major);
               result = ps.executeUpdate();
               return(result);
               
            }// End of try
            catch(SQLException sqle){
               sqle.printStackTrace();
               return -1;
            }
    }


   public LinkedList<Integer> getFacultyIntersectionList(int studentID){
      //Brenden Search Code
      LinkedList<Integer> intersectionList = new LinkedList<>();
      LinkedList<Integer> intersectionAbstractIDs = new LinkedList<>();
      //Iterates through the rows of faculty to search for intersections between their abstract and the student's intersts
      
      Dictionary<String,Integer> dictionaryAbstracts = new Hashtable<String,Integer>(); 

      String studentInterestUncut;
      try{
         // prepared statement
         String sql = "SELECT keyword FROM studentinterests WHERE studentID = " + studentID;
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         rs.next();
         studentInterestUncut = rs.getString(1);
         //System.out.println("TESTING CODE student Interest is: " + studentInterestUncut);
      }// End of try
      catch(SQLException sqle){
         sqle.printStackTrace();
         System.out.println("STUDENT DOES NOT EXIST! Enter a different ID");
         return null;
      }

      //Now tokenize the studentIntests into individual interests
      studentInterestUncut = studentInterestUncut.strip();
      studentInterestUncut = studentInterestUncut.toLowerCase();
      String[] studentInterestCut = studentInterestUncut.split(",");

      String facultyAbstractString;
      LinkedList<String> facultyAbstractList = new LinkedList<>();   
      //Get all Abstracts from Faculty
            try{
         // prepared statement
         String sql = "SELECT abstractID, abstract FROM abstract";
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         rs.next();
         do {
         int facultyAbstractID = rs.getInt(1);
         facultyAbstractString = rs.getString(2);
         facultyAbstractString = facultyAbstractString.toLowerCase();
         facultyAbstractList.add(facultyAbstractString);
         //Adds the string to the list
         
         dictionaryAbstracts.put(facultyAbstractString, facultyAbstractID);

         } while(rs.next());
         //Adds all abstract strings to a list
         //System.out.println("TESTING CODE Abstract is: " + FacultyAbstractString);
      }// End of try
      catch(SQLException sqle){
         sqle.printStackTrace();
         System.out.println("FACULTY ABSTRACT GET FAILED!!!");
         return null;
      }

      //Uses my Rabin-Karp code to searh all the abstracts for all the keywords
      for(int interestIndex = 0; interestIndex < studentInterestCut.length - 1; interestIndex++ ){


         while(!facultyAbstractList.isEmpty()){
            String currentAbstract = facultyAbstractList.pop();
            //Removes an abstract from the list until it is empty
            
            //Checks if rabinKarp returns anything, if it does then one of the interests exists
            if(StringSearch.rabinKarpMultiple(studentInterestCut[interestIndex], currentAbstract ) != null){
               intersectionAbstractIDs.add(dictionaryAbstracts.get(currentAbstract));
            }
         }
      }

      while(!intersectionAbstractIDs.isEmpty()){

         try{
            // prepared statement
            String sql = "SELECT facultyID FROM facultyabstract WHERE facultyID = " + intersectionAbstractIDs.pop();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            int newFacultyID = rs.getInt(1);

         }// End of try
         catch(SQLException sqle){
            sqle.printStackTrace();
            System.out.println("INTERSECTION ABSTRACT COMPARISON FAILED");
            return null;
         }

      }


      //This code takes interests from the faculty interest table and adds them to the output if they are new
      String facultyInterestUncut;
      try{
         // prepared statement
         String sql = "SELECT keyword FROM facultyinterests";
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         rs.next();
         facultyInterestUncut = rs.getString(1);
         //System.out.println("TESTING CODE student Interest is: " + studentInterestUncut);
      }// End of try
      catch(SQLException sqle){
         sqle.printStackTrace();
         System.out.println("FACULTY INTEREST SEARCH FAILED");
         return null;
      }

      //Now tokenize the studentIntests into individual interests
      facultyInterestUncut = facultyInterestUncut.strip();
      facultyInterestUncut = facultyInterestUncut.toLowerCase();
      String[] facultyInterestCut = facultyInterestUncut.split(",");


      for(int interestIndex = 0; interestIndex < studentInterestCut.length - 1; interestIndex++ ){

         
         for(int facIndex = 0; facIndex < facultyInterestCut.length; facIndex++){
            
            //Checks if rabinKarp returns anything, if it does then one of the interests exists
            if(StringSearch.rabinKarpMultiple(studentInterestCut[interestIndex], facultyInterestCut[facIndex] ) != null){
               int currentAbstractID = dictionaryAbstracts.get(facultyInterestCut[facIndex]);
               if( intersectionList.contains(currentAbstractID))
               intersectionList.add(currentAbstractID);
            }
         }
      }

      





      return intersectionList;
   }




      
   
   public void close(){
      try {
         stmt.close();
         conn.close();
      }
      catch(SQLException sqle){
        System.out.println("ERROR IN METHOD close()");
        System.out.println("ERROR MESSAGE -> "+sqle);
      }// end of catch
   }//end of method close

   
   
   
} 