import java.util.LinkedList;

public class StringSearch {
    

   //This is Brenden Becker's Code from ISTE222

   static int rabinKarp(String str, String substr) {
      int strLength = str.length();
      int subLength = substr.length();
      int strHash = 0; // hash value for str window
      int subHash = 0; // hash value for substr
      
      // Calculate the hash value of the substring
      // and the first str window 
      for(int i = 0; i < subLength; i++) {  
         subHash = subHash + substr.charAt(i);
         strHash = strHash + str.charAt(i);
      } 
      
      int i = 0;
      while(i <= strLength - subLength) { 
         // if the hashes match, confirm that the window 
         // and substring match 
         if(subHash == strHash) { 
            int j;
            //System.out.println("Hashes matched: window = " + str.substring(i, i + subLength));
            
            for(j = 0; j < subLength; j++) {
               if(substr.charAt(j) != str.charAt(i + j))
                  break; 
            } 
         
            if(j == subLength) 
               return i; 
         }
         
         if(i < strLength - subLength) {
            // compute the hash for the next window
            strHash = strHash - str.charAt(i);
            strHash = strHash + str.charAt(i + subLength);
         }
         
         i++;
      }
      
      return -1;
   }

   static LinkedList<Integer> rabinKarpMultiple(String str, String substr) {
      LinkedList<Integer> output = new LinkedList<Integer>();
      int strLength = str.length();
      int subLength = substr.length();
      int strHash = 0; // hash value for str window
      int subHash = 0; // hash value for substr
      
      // Calculate the hash value of the substring
      // and the first str window 
      for(int i = 0; i < subLength; i++) { 
         subHash = subHash + substr.charAt(i);
         strHash = strHash + str.charAt(i);
      } 
      
      int i = 0;
      while(i <= strLength - subLength) { 
         // if the hashes match, confirm that the window 
         // and substring match 
         if(subHash == strHash) { 
            int j;
            //System.out.println("Hashes matched: window = " + str.substring(i, i + subLength));
            for(j = 0; j < subLength; j++) {
               if(substr.charAt(j) != str.charAt(i + j))
                  break; 
            } 
         
            if(j == subLength){
               output.add(i);
            }
         }
         
         if(i < strLength - subLength) {
            // compute the hash for the next window
            strHash = strHash - str.charAt(i);
            strHash = strHash + str.charAt(i + subLength);
         }
         
         i++;
      }

      if(output.isEmpty()){
         return null;
      }

      return output;
   }

               
}