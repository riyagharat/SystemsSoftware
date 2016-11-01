import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class Project3{
  public static void main(String[] args){

     Scanner inputs = new Scanner(System.in);

     // Create array with the format 3 instructions
     ArrayList<String> formatThree = new ArrayList<>();
     formatThree.add("ADD");
     formatThree.add("ADDF");
     formatThree.add("AND");
     formatThree.add("COMP");
     formatThree.add("COMPF");
     formatThree.add("DIV");
     formatThree.add("DIVF");
     formatThree.add("J");
     formatThree.add("JEQ");
     formatThree.add("JGT");
     formatThree.add("JLT");
     formatThree.add("JSUB");
     formatThree.add("LDA");
     formatThree.add("LDB");
     formatThree.add("LDCH");
     formatThree.add("LDF");
     formatThree.add("LDL");
     formatThree.add("LDS");
     formatThree.add("LDT");
     formatThree.add("LDX");
     formatThree.add("LPS");
     formatThree.add("MUL");
     formatThree.add("MULF");
     formatThree.add("OR");
     formatThree.add("RD");
     formatThree.add("RSUB");
     formatThree.add("SSK");
     formatThree.add("STA");
     formatThree.add("STB");
     formatThree.add("STCH");
     formatThree.add("STF");
     formatThree.add("STI");
     formatThree.add("STS");
     formatThree.add("STSW");
     formatThree.add("STT");
     formatThree.add("STX");
     formatThree.add("SUB");
     formatThree.add("SUBF");
     formatThree.add("TD");
     formatThree.add("TIX");
     formatThree.add("WD");
     formatThree.add("WORD");

     // Create array with the format 2 instructions
     ArrayList<String> formatTwo = new ArrayList<>();
     formatTwo.add("ADDR");
     formatTwo.add("CLEAR");
     formatTwo.add("COMPR");
     formatTwo.add("DIVR");
     formatTwo.add("MULR");
     formatTwo.add("RMO");
     formatTwo.add("SHIFTL");
     formatTwo.add("SHIFTR");
     formatTwo.add("SUBR");
     formatTwo.add("SVC");
     formatTwo.add("TIXR");

     // Create array with the format 1 instructions
     ArrayList<String> formatOne = new ArrayList<>();
     formatOne.add("FIX");
     formatOne.add("FLOAT");
     formatOne.add("HIO");
     formatOne.add("NORM");
     formatOne.add("SIO");
     formatOne.add("TIO");

     // Create array with the restricted instructions
     ArrayList<String> restrictedList = new ArrayList<>();
     restrictedList.add("START");
     restrictedList.add("BASE");
     restrictedList.add("END");

     // The number of Strings in File 1
     int n = 0;
     // The first prime number greater than 2n
     int p = 0;

     // Note: The file names must be entered without a .txt extension

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

     ArrayList<LineObject> symbolTable = new ArrayList<>();

     String temp, text = "";
     int counter = 0;
     try{
       Scanner input = new Scanner(new FileInputStream(file));
       while (input.hasNextLine()){
           text = input.nextLine();
           StringTokenizer st = new StringTokenizer(text);
           String mneumonic = "";
           String comment = "";
           String startVal = "";
           String label = "";
           String address = "";
           while(st.hasMoreTokens()){
             temp = st.nextToken();
             if((temp.substring(0,1)).equals(".")){
               comment = temp;
               // DO SOMETHING WITH COMMENT HERE
             }else if((temp.substring(0,1).equals("+"))){
               mneumonic = temp;
               // DO SOMETHING WITH FORMAT FOUR
             }else if(temp.equals("RESW")){
               mneumonic = temp;
               // DO SOMETHING WITH MULTIPLYING THE NEXT NUMBER BY 3 AND ADDING TO ADDRESS
             }else if(formatThree.contains(temp)){
               mneumonic = temp;
             }else if(formatTwo.contains(temp)){
               mneumonic = temp;
             }else if(formatOne.contains(temp)){
               mneumonic = temp;
             }else if(restrictedList.contains(temp)){
               mneumonic = temp;
             }else if(isInteger(temp)){
               if(counter == 0){
                 // SET THE startVal
                 startVal = temp;
                 address = startVal;
                 counter++;
               }else{
                 if(mneumonic.equals("RESW")){
                    // MULTIPLY BY 3 AND ADD TO THE PREVIOUS ADDRESS
                 }else{
                   //DON'T DO ANYTHING, ONLY ADD 3 TO PREVIOUS ADDRESS
                 }
               }
             }else{
               label = temp;
               // SAVE THE LABEL HERE
             }
           }
           symbolTable.add(new LineObject(label, mneumonic, comment, address));
        }
        input.close();
      }catch(IOException e){
        e.printStackTrace();
      }
  }

//METHODS

    // Check if Integer
    static public boolean isInteger(String stringNumber){
      try{
        Integer.parseInt(stringNumber);
        return true;
      }catch(Exception e){
        return false;
      }
    }

    // Calculate Addresses
    static public void calculateAddress(){

    }

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

   static class LineObject{
      // The values on each line
      private String label;
      private String mneumonic;
      private String comment;
      private String address;

      // LineObject constructors
      public LineObject(){
      }

      public LineObject(String label, String mneumonic, String comment, String address){
          this.label = label;
          this.mneumonic = mneumonic;
          this.comment = comment;
          this.address = address;
      }

      //Methods for the LineObject Class

      // Get Methods for LineObject Class
      public String getLabel(){
        return label;
      }

      public String getMneumonic(){
        return mneumonic;
      }

      public String getComment(){
        return comment;
      }

      public String getAddress(){
        return address;
      }

      // Set Methods for LineObject Class
      public void setLabel(String newLabel){
        this.label = newLabel;
      }

      public void setMneumonic(String newMneumonic){
        this.mneumonic = newMneumonic;
      }

      public void setComment(String newComment){
        this.comment = newComment;
      }

      public void setAddress(String newAddress){
        this.address = newAddress;
      }

      public boolean equals(Object obj){
         return true;
      }
      @Override
      public String toString(){
        return getAddress() + getLabel() + getMneumonic() + getComment();
      }
   }
}
