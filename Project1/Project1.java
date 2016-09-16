import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class Project1{
  public static void main(String[] args){
   
     Scanner input = new Scanner(System.in);  
     
     // The number of Strings in File 1
     int n = 0;
     // The first prime number greater than 2n
     int p = 0;

     // NOTE: The file names must be entered without a .txt extension
      
     // Read file
     String fileName = args[0];
     File file = new File(fileName);   
     readFile(fileName, file);
     
     // Count the number of strings in the file
     n = countStrings(file, n);
     
     // First prime number larger than 2n, say p
     for(int i = (2*n + 1); true; i++){
       if(isPrime(i)){
          p = i;
      //    System.out.println("The next prime number is: " + p);
          break;
        }
     }   
     
     // Construct an array for the words being stored from the file
      String[] words = new String[p];
     
     // Construct an array for the values of the words
      int[] values = new int[p];
      
     // Instantiate arrays with null values or 0 
     for(int i = 0; i < words.length; i++){
        words[i] = null;
     }
     
     for(int i = 0; i < values.length; i++){
        values[i] = 0;
     }  
     
     // Create and open an output file
//     PrintWriter printWriter = new PrintWriter(new FileWriter("output.txt"));
     
     // If a word and a value are read from the file then the word is hashed
     // and attempted to be placed, if the word already exists then an error
     // statement is printed.
     // If only a word is read from the file then the word is hashed and then 
     // searched for in the array, if it is not found then an error statement
     // is printed else a statement saying that it is found is printed.
     
     String temp = "";     
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file));
        while(sc.hasNext()){
            temp = sc.next();
            if(sc.hasNextInt()){
               insertLinear(words, temp, p, sc.nextInt(), values);
            }else{
               findLinear(words, temp, p, values);
            }
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
   

   }
   
     //
   
//METHODS   

   // Read Files
  static public void readFile(String filename, File file){  
     if (!file.exists()) {
        System.out.println(filename + " does not exist.");
        return;
     }
   
     if (!(file.isFile() && file.canRead())) {
        System.out.println(file.getName() + " cannot be read from.");
        return;
     }
   }  
   
   //Count Number of Strings in File1
   static public int countStrings(File file, int n){
     try{
        Scanner sc = new Scanner(new FileInputStream(file));
        while(sc.hasNext()){
           sc.next();
           n++;
        }                   
     }catch (IOException e){
        e.printStackTrace();
     }
     return n;
   }    

      
   // Checks if the next number is prime
   static public boolean isPrime(int n){
      for(int j = 2; (j*j <= n); j++){
         if(n % j == 0){
            return false;
         }
      }   
      return true;
   }
   
   //Creates the hash key
   static public int hashFunction(String key, int arraySize){
      int hashVal = 0;
      
      for(int j = 0; j < key.length(); j++){
         int letter = key.charAt(j) - 96;
         hashVal = (hashVal * 26 + letter) % arraySize;
      }
      return hashVal;
   }             
   
   //Inserts the string by linear probing with a value
   static public void insertLinear(String hashArray[], String key, int arraySize, int number, int wordVals[]){
      int hashVal = hashFunction(key, arraySize);
      key.trim();
      
      while(hashArray[hashVal] != null){
         if(hashArray[hashVal] == key){
            try{
               PrintWriter printWriter = new PrintWriter(new FileWriter("Output.txt", true));
               printWriter.println("ERROR " + key + " already exists at location "+ hashVal);
               printWriter.close();
            }catch(IOException e){
            }
            break;
         }
         ++hashVal;
         hashVal %= arraySize;
      }
      try{
         PrintWriter printWriter = new PrintWriter(new FileWriter("Output.txt", true));
         printWriter.println("Stored " + key + " " + number + " at location "+ hashVal);
         printWriter.close();
      }catch(IOException e){
      }
      hashArray[hashVal] = key;
      wordVals[hashVal] = number;   
   }  
   
   //Finds the string by linear probing
   static public void findLinear(String hashArray[], String key, int arraySize, int wordVals[]){
      int hashVal = hashFunction(key, arraySize);
      key.trim();

      hashVal = hashFunction(key, arraySize);
      int hashValOrig = hashVal;
      boolean found = false;
     
      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].equals(key)){
         try{
            PrintWriter printWriter = new PrintWriter(new FileWriter("Output.txt", true));
            printWriter.println(key + " found at location "+ hashVal + " with value " + wordVals[hashVal]);
            printWriter.close();
         }catch(IOException e){
         }
            found = true;
         }   
        ++hashVal;
        hashVal %= arraySize;      
     }
     if(hashArray[hashVal] == null && found == false){
         try{
            PrintWriter printWriter = new PrintWriter(new FileWriter("Output.txt", true));
            printWriter.println("ERROR " + key + " not found");
            printWriter.close();
         }catch(IOException e){
         }

     }    
   }                
}                
