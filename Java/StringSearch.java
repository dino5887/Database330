import java.text.StringCharacterIterator;
import java.util.LinkedList;

public class StringSearch {
    

   //This is Brenden Becker's Code from ISTE222

   static LinkedList<Integer> rabinKarpMultiple(String str, String substr) {
      LinkedList<Integer> output = new LinkedList<Integer>();
      int strLength = str.length();
      int subLength = substr.length();
      int strHash = 0; // hash value for str window
      int subHash = 0; // hash value for substr
      //Swaps the terms if the searched term is shorter than the searching term
      if(subLength > strLength){
         int strLengthTemp = strLength;
         strLength = subLength;
         subLength = strLengthTemp;
         int strHashTemp = strHash;
         strHash = subHash;
         subHash = strHashTemp;
         String strTemp = str;
         str = substr;
         substr = strTemp;
      }

      //Checks if the string and sub match
      if(strLength == subLength){
         if(str == substr){
            output.add(1);
            return output;
         }
      }
      
      
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

   public static void main(String[] args) {
      String str = "Future events such as these will effect you in the future";
      String substr = "uch";
      String dna = "GTTGCAGTTACTTATTATCTGAAAACCAGTTGATGTTAAGGAATACTCTGTCTAAGACAACATATGTAATAAAAATTATATATTCGTTGGGTTCTCTCGA";
      String subdna = "GTT";
      
      //System.out.println("Brute Force Index = " + bruteForce(str, substr));
      //System.out.println("Rabin Karp Index = " + rabinKarp(str, substr));
      System.out.println("Rabin Karp Index = " + rabinKarpMultiple(dna, subdna));


   }

               
}