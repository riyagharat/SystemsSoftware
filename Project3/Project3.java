import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class Project3{

  static String label = "", mneumonic = "", operand = "", currentAddress = "", comment = "";
  static boolean check = false, check2 = true;

  public static void main(String[] args) throws FileNotFoundException{
    File file = new File(args[0]);
    Scanner input = new Scanner(file);

    // Create array with the format 4 instructions
    ArrayList<String> formatFour = new ArrayList<>();
    formatFour.add("+LDB");
    formatFour.add("+SSK");
    formatFour.add("+JGT");
    formatFour.add("+STL");
    formatFour.add("+STI");
    formatFour.add("+LDT");
    formatFour.add("+MULF");
    formatFour.add("+J");
    formatFour.add("+COMP");
    formatFour.add("+STS");
    formatFour.add("+JSUB");
    formatFour.add("+COMPF");
    formatFour.add("+STT");
    formatFour.add("+SUBF");
    formatFour.add("+OR");
    formatFour.add("+JLT");
    formatFour.add("+LDS");
    formatFour.add("+DIV");
    formatFour.add("+MUL");
    formatFour.add("+STX");
    formatFour.add("+LDA");
    formatFour.add("+SUB");
    formatFour.add("+STB");
    formatFour.add("+ADDF");
    formatFour.add("+JEQ");
    formatFour.add("+STCH");
    formatFour.add("+STA");
    formatFour.add("+DIVF");
    formatFour.add("+STF");
    formatFour.add("+STSW");
    formatFour.add("+LPS");
    formatFour.add("+LDL");
    formatFour.add("+RSUB");
    formatFour.add("+WD");
    formatFour.add("+LDCH");
    formatFour.add("+LDF");
    formatFour.add("+LDX");
    formatFour.add("+ADD");
    formatFour.add("+AND");
    formatFour.add("+TD");
    formatFour.add("+TIX");
    formatFour.add("+RD");

    // Create array with the format 3 instructions
    ArrayList<String> formatThree = new ArrayList<>();
    formatThree.add("WD");
    formatThree.add("*STX");
    formatThree.add("*OR");
    formatThree.add("AND");
    formatThree.add("*LDA");
    formatThree.add("*WD");
    formatThree.add("LPS");
    formatThree.add("*LDCH");
    formatThree.add("*LDL");
    formatThree.add("SUBF");
    formatThree.add("*JSUB");
    formatThree.add("LDX");
    formatThree.add("STT");
    formatThree.add("TIX");
    formatThree.add("LDT");
    formatThree.add("STA");
    formatThree.add("*TD");
    formatThree.add("STB");
    formatThree.add("LDA");
    formatThree.add("DIVF");
    formatThree.add("*TIX");
    formatThree.add("LDCH");
    formatThree.add("JEQ");
    formatThree.add("*DIV");
    formatThree.add("*AND");
    formatThree.add("SSK");
    formatThree.add("*RD");
    formatThree.add("LDS");
    formatThree.add("*MUL");
    formatThree.add("J");
    formatThree.add("*COMP");
    formatThree.add("*J");
    formatThree.add("*JLT");
    formatThree.add("JLT");
    formatThree.add("SUB");
    formatThree.add("RD");
    formatThree.add("*JEQ");
    formatThree.add("LDB");
    formatThree.add("RSUB");
    formatThree.add("MULF");
    formatThree.add("JSUB");
    formatThree.add("LDL");
    formatThree.add("*STL");
    formatThree.add("STSW");
    formatThree.add("COMPF");
    formatThree.add("*ADD");
    formatThree.add("*STSW");
    formatThree.add("*JGT");
    formatThree.add("MUL");
    formatThree.add("OR");
    formatThree.add("COMP");
    formatThree.add("TD");
    formatThree.add("STS");
    formatThree.add("*STCH");
    formatThree.add("LDF");
    formatThree.add("ADD");
    formatThree.add("*RSUB");
    formatThree.add("STF");
    formatThree.add("*LDX");
    formatThree.add("STCH");
    formatThree.add("*SUB");
    formatThree.add("STX");
    formatThree.add("ADDF");
    formatThree.add("STL");
    formatThree.add("STI");
    formatThree.add("*STA");
    formatThree.add("JGT");
    formatThree.add("DIV");
    formatThree.add("WORD");

    // Create array with the format 2 instructions
    ArrayList<String> formatTwo = new ArrayList<>();
    formatTwo.add("MULR");
    formatTwo.add("TIXR");
    formatTwo.add("SVC");
    formatTwo.add("SHIFTR");
    formatTwo.add("SUBR");
    formatTwo.add("DIVR");
    formatTwo.add("CLEAR");
    formatTwo.add("RMO");
    formatTwo.add("COMPR");
    formatTwo.add("SHIFTL");
    formatTwo.add("ADDR");

    // Create array with the format 1 instructions
    ArrayList<String> formatOne = new ArrayList<>();
    formatOne.add("FIX");
    formatOne.add("FLOAT");
    formatOne.add("SIO");
    formatOne.add("HIO");
    formatOne.add("TIO");
    formatOne.add("FIX");
    formatOne.add("NORM");
//    formatOne.add("RESB");
//    formatOne.add("BYTE");

    // Create array with the restricted instructions
    ArrayList<String> restrictedList = new ArrayList<>();
    restrictedList.add("START");
    restrictedList.add("BASE");
    restrictedList.add("END");

    // Create ArrayList of LineObjects
    ArrayList<LineObject> lineTable = new ArrayList<>();

    // Create an ArrayList for the labelStorage, will only contain the labels
    // and their hashed locations
    ArrayList<LineObject> labelStorage = new ArrayList<>();

    // Create an ArrayList of errors
    ArrayList<String> errorList = new ArrayList<>();

    while(input.hasNextLine()){
      String text = input.nextLine();
//      StringTokenizer st = new StringTokenizer(text);
      if(!(text.equals(""))){
        int numTKNs = countTokens(text);
        if(numTKNs != 0){
          assignTokens(text, numTKNs, lineTable, labelStorage);
        }
      }
    }
    // The number of labels
    int n = labelStorage.size();
//    System.out.println("NUMBER OF LABELS: " + n);
    // The first prime number greater than 2n
    int p = 0;
    for(int i = (2*n + 1); true; i++){
      if(isPrime(i)){
        p = i;
        break;
      }
    }

    // Construct an array of the symbols
    String [] symbolTable = new String [p];
    for(int i = 0; i < symbolTable.length; i++){
      symbolTable[i] = null;
    }

    for (int i = 0; i <= labelStorage.size()-1; i++){
//        System.out.println("ARRAY LENGTH: " + p);
        String tempLabel = labelStorage.get(i).getLabel();
        int num = insertLinear(symbolTable, tempLabel, p, errorList);
        String newNum = Integer.toString(num);
        // Sets the hashVal
        labelStorage.get(i).setHashLocation(newNum);
    }
    // Calculates the addresses for each of the lines
    calculateAddress(lineTable, formatOne, formatTwo, formatThree, formatFour, errorList, restrictedList);

    // Print the original program
    System.out.println("LINES");
    printLines(lineTable);
    System.out.println();
    System.out.println();
    duplicateLabel(errorList, lineTable);
    // Print the errors
    printErrorChecking(errorList);
    System.out.println();
    System.out.println();
    // Print the symbol Table
    System.out.println("TABLE LOCATION  LABEL  ADDRESS");
    printSymbol(labelStorage);

  }

  // Checks to see if the number is prime
  public static boolean isPrime(int n){
    for(int j = 2; (j*j <= n); j++){
      if(n % j == 0){
        return false;
      }
    }
    return true;
  }

  // Hashes a word
  public static int hashFunction(String key, int arraySize){
    int hashVal = 0;
    int letter = 0;

    for(int j = 0; j < key.length(); j++){
      if(key.charAt(j) > 64 && key.charAt(j) < 91){
        letter = key.charAt(j) - 64;
      }else if (key.charAt(j) > 96 && key.charAt(j) < 123){
        letter = key.charAt(j) - 96;
      }
         hashVal = (hashVal * 26 + letter) % arraySize;
    }
    return hashVal;
  }

  // Inserts the hashed word to its appropriate location
  static public int insertLinear(String symbolTable[], String label, int arraySize, ArrayList<String> errorList){
      int hashVal = hashFunction(label, arraySize);

      while(symbolTable[hashVal] != null){
         if(symbolTable[hashVal] == label){
         }
         ++hashVal;
         hashVal %= arraySize;
      }
      symbolTable[hashVal] = label;
      return hashVal;
   }

   // Calulates addresses for each of the lines
  public static void calculateAddress(ArrayList<LineObject> lineTable, ArrayList<String> formatOne,
        ArrayList<String> formatTwo, ArrayList<String> formatThree, ArrayList<String> formatFour,
        ArrayList<String> errorList, ArrayList<String> restrictedList){
//    printLines(lineTable);
    for (int i = 0; i <= lineTable.size()-1; i++){
      String value = lineTable.get(i).getMneumonic();
      int num = 0, num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, sum = 0, numr = 0, numb = 0;
      String newCurrentAddress;
      if(value.equals("START")){
        Project3.currentAddress = lineTable.get(i).getOperand();
        lineTable.get(i).setAddress(Project3.currentAddress);
      }else{
        lineTable.get(i).setAddress(Project3.currentAddress);
        if(formatOne.contains(value)){
          num = Integer.parseInt(Project3.currentAddress, 16);
          num1 = Integer.parseInt("1", 16);
          sum = num + num1;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if(formatTwo.contains(value)){
          num = Integer.parseInt(Project3.currentAddress, 16);
          num2 = Integer.parseInt("2", 16);
          sum = num + num2;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if(formatThree.contains(value)){
          num = Integer.parseInt(Project3.currentAddress, 16);
          num3 = Integer.parseInt("3", 16);
          sum = num + num3;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if(formatFour.contains(value)){
          num = Integer.parseInt(Project3.currentAddress, 16);
          num4 = Integer.parseInt("4", 16);
          sum = num + num4;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if(value.equals("RESW")){
          num = Integer.parseInt(Project3.currentAddress, 16);
          int test = Integer.parseInt(lineTable.get(i).getOperand());
    //      System.out.println("TEST: " + test);
          test = test*3;
          String text = Integer.toHexString(test);
    //      System.out.println("TEXT: "+ text);
          numr = Integer.parseInt(text, 16);
          sum = num + numr;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if((value.equals("RESB")) || (value.equals("BYTE"))){
          num = Integer.parseInt(Project3.currentAddress, 16);
          int test = Integer.parseInt(lineTable.get(i).getOperand());
          String text = Integer.toHexString(test);
          numb = Integer.parseInt(text, 16);
    //      System.out.println("NUMB: "+numb);
          sum = num + numb;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if(restrictedList.contains(value)){
          num = Integer.parseInt(Project3.currentAddress, 16);
          num5 = Integer.parseInt("0", 16);
          sum = num + num5;
          newCurrentAddress = Integer.toHexString(sum);
          Project3.currentAddress = newCurrentAddress;
        }else if((value.substring(0,1).equals("."))){

        }else if(value.equals("")){

        }else{
          errorList.add("ERROR: incorrect mneumonic: " + lineTable.get(i).mneumonic);
        }
      }
    }
  }

  // Checks if a string is acutally a number
  public static boolean isInteger(String stringNumber){
    try{
      Integer.parseInt(stringNumber);
      return true;
    }catch(NumberFormatException e){
      return false;
    }
  }

  // Checks to see if there is a duplicate label
  public static void duplicateLabel(ArrayList<String> errorList,
        ArrayList<LineObject> lineTable){
    int duplicateLabelCounter = 0;
    boolean reachedRSUB = false;
    for(int i = 0; i <= lineTable.size()-1; i++){
      if(!(lineTable.get(i).getLabel()).equals("")){
        for(int j = i+1; ((j <= lineTable.size()-1) && (reachedRSUB == false)); j++){
          if(!(lineTable.get(j).getMneumonic()).equals("RSUB")){
            if((lineTable.get(i).getLabel()).equals((lineTable.get(j).getLabel()))){
              duplicateLabelCounter++;
            }
          }else{
            reachedRSUB = true;
          }
        }
      }
      if(duplicateLabelCounter > 0){
        errorList.add("ERROR: Duplicate label: " + lineTable.get(i).getLabel());
      }
      duplicateLabelCounter = 0;
      reachedRSUB = false;
    }
  }

  // Prints the lines
  public static void printLines(ArrayList<LineObject> lineTable){
    for (int i = 0; i <= lineTable.size()-1; i++){
         System.out.println(lineTable.get(i).toStringOG());
         System.out.println("");
    }
  }

  // Prints the symbol table
  public static void printSymbol(ArrayList<LineObject> labelStorage){
    for (int i = 0; i <= labelStorage.size()-1; i++){
         System.out.println(labelStorage.get(i).toStringSymbol());
         System.out.println("");
    }
  }

  // Prints the error table
  public static void printErrorChecking(ArrayList<String> errorList){
    for (int i = 0; i <= errorList.size()-1; i++){
         System.out.println(errorList.get(i));
    }
  }

  // Counts the number of Tokens
  public static int countTokens(String line){
    StringTokenizer st = new StringTokenizer(line);
    int TKNCounter = 0;
    while(st.hasMoreTokens()){
      String textToken = st.nextToken();
      if((textToken.substring(0,1)).equals(".")){
        break;
      }
      if(TKNCounter == 3){
        break;
      }
      TKNCounter++;
    }
    return TKNCounter;
  }

  // Assigns the appropriate variables
  public static void assignTokens(String line, int numTKNs, ArrayList<LineObject> lineTable, ArrayList<LineObject> labelStorage){
    StringTokenizer st = new StringTokenizer(line);
    int TKNCount = numTKNs;
    String lineComment = "";
    if(TKNCount == 0){
      Project3.comment = line;
      LineObject data = new LineObject("", "", "", Project3.comment, "", "");
      lineTable.add(data);
    }
    if(TKNCount == 1){
      Project3.mneumonic = st.nextToken();
      while(st.hasMoreTokens()){
        Project3.comment = Project3.comment + st.nextToken() + " ";
      }
      LineObject data = new LineObject("", Project3.mneumonic, "", Project3.comment, "", "");
      lineTable.add(data);
    }
    while((st.hasMoreTokens()) && (TKNCount > 1)){
      String text = st.nextToken();
      if(TKNCount == 2){
        Project3.mneumonic = text;
        Project3.operand = st.nextToken();
        while(st.hasMoreTokens()){
          Project3.comment = Project3.comment + st.nextToken() + " ";
        }
        LineObject data = new LineObject("", Project3.mneumonic, Project3.operand, Project3.comment, "", "");
        lineTable.add(data);
        break;
      }
      if(TKNCount == 3){
        Project3.label = text;
        Project3.mneumonic = st.nextToken();
        Project3.operand = st.nextToken();
        while(st.hasMoreTokens()){
          Project3.comment = Project3.comment + st.nextToken() + " ";
        }
        LineObject data = new LineObject(Project3.label, Project3.mneumonic, Project3.operand, Project3.comment, "", "");
        lineTable.add(data);
        labelStorage.add(data);
        break;
      }
    }
    Project3.label = "";
    Project3.mneumonic = "";
    Project3.operand = "";
    Project3.comment = "";
  }




  static class LineObject{
     // The values on each line
     private String label;
     private String mneumonic;
     private String operand;
     private String comment;
     private String address;
     private String hashLocation;

     // LineObject constructors
     public LineObject(){
     }

     public LineObject(String label, String mneumonic, String operand, String comment, String address, String hashLocation){
       this.label = label;
       this.mneumonic = mneumonic;
       this.operand = operand;
       this.comment = comment;
       this.address = address;
       this.hashLocation = hashLocation;
     }

     //Methods for the LineObject Class

     // Get Methods for LineObject Class
     public String getLabel(){
       return label;
     }

     public String getMneumonic(){
       return mneumonic;
     }

     public String getOperand(){
       return operand;
     }

     public String getComment(){
       return comment;
     }

     public String getAddress(){
       return address;
     }

     public String getHashLocation(){
       return hashLocation;
     }

     // Set Methods for LineObject Class
     public void setLabel(String newLabel){
       this.label = newLabel;
     }

     public void setMneumonic(String newMneumonic){
       this.mneumonic = newMneumonic;
     }

     public void setOperand(String newOperand){
       this.operand = newOperand;
     }

     public void setComment(String newComment){
       this.comment = newComment;
     }

     public void setAddress(String newAddress){
       this.address = newAddress;
     }

     public void setHashLocation(String newHashLocation){
       this.hashLocation = newHashLocation;
     }

     public boolean equals(Object obj){
        return true;
     }

     public String toStringOG(){
       return getAddress() + " " + getLabel() + " " + getMneumonic() + " " + getOperand() + " " + getComment();
     }
     public String toStringSymbol(){
       return getHashLocation() + " " + getLabel() + " " + getAddress();
     }
     public String toStringError(){
       return getLabel() + "" + getMneumonic() + " " + getComment();
     }
  }
}
