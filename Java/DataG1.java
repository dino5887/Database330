// Brenden Becker, Sophia Castiglione, Hunter Ross
// ISTE 330
// Group Project Team #1

// This code is the Data Layer
import java.sql.*;
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
   public int addFaculty(String lName, String fName, String uName, String passwd, String email){
      int result = 0;
            try{
               // prepared statement
               String sql = "INSERT INTO Faculty (lastName, firstName, username, password, email) VALUES (?,?,?,?,?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setString(1, fName);
               ps.setString(2, lName);
               ps.setString(3, uName);
               ps.setString(4, passwd);
               ps.setString(5, email);
               
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
         sql =  "DELETE FROM Faculty WHERE facultyID=?";
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


//    public LinkedList<Integer> getCandidateChoices(){
//     LinkedList<Integer> ids = new LinkedList<Integer>();
//     try{
//     stmt = conn.createStatement();
//     sql = "select candidate_ID from candidate";
//     rs = stmt.executeQuery(sql);

//     while(rs.next()){
//         ids.add(rs.getInt(1));
//     }

//     } catch(SQLException sqle){
//         System.out.println("SQL EXCEPTION");
//         sqle.printStackTrace();
//         return null;
//     }

//     return ids;

//    }


//    public LinkedList<String> getCandidateSkill(int canID){
//     LinkedList<String> candidateInfo = new LinkedList<String>();
//     try{
//         stmt = conn.createStatement();
//         sql = "call get_candidate_skill(" + canID + ")";
//         rs = stmt.executeQuery(sql);
//         String output = new String();

//         while(rs.next()){
//             candidateInfo.add(Integer.toString(rs.getInt(1)));
//             candidateInfo.add(rs.getString(2));
//             candidateInfo.add(rs.getString(3));
//         }
    
//         } catch(SQLException sqle){
//             System.out.println("UNREAL CANDIDATE ID");
//             sqle.printStackTrace();
//             return null;
//         }


//     return candidateInfo;
//    }
   
   
//    public int addCandidate(String fname, String lname, String jobTitle){
//       try{
      
//       	stmt = conn.createStatement();
// 			sql = "INSERT INTO candidate(lastName, firstName, jobTitle) VALUES('"+ lname +"', '" + fname + "', '" + jobTitle + "')";
// 			resultAdd = stmt.executeUpdate(sql);

//       }
//       catch(SQLException sqle){
//        System.out.println("ERROR MESSAGE -> "+sqle);
//        System.out.println("ERROR SQLException in addCandidate()");
//       }
//       LinkedList<Integer> output = getCandidateChoices();
//       return output.getLast();
//    } 

//    public int addSkill(int canID, String[] skills){
//     resultAdd = 0;
//     try{
//         for(int i = 0; i < skills.length; i++){
//           stmt = conn.createStatement();
//           sql = "INSERT INTO candidate_skill VALUES("+ canID +", " + skills[i] + ")";
//           resultAdd += stmt.executeUpdate(sql);
//         }
//     }
//     catch(SQLException sqle){
//      System.out.println("ERROR MESSAGE -> "+sqle);
//      System.out.println("ERROR SQLException in addSkill()");
//     }
//     return resultAdd;
//  } 



      
   
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