// One variation

String temp = "";
int counter = 0;
try{
  Scanner input = new Scanner(new FileInputStream(file));
  while (input.hasNextLine()){
      String text = input.nextLine();
      if(counter == 0){
        String readLabel = text.substring(0,9);
        String readMneumonic = text.substring(10,18);
        String readOperandSTART = text.substring(19,30);
      }else{
        String readLabel = text.substring(0,9);
        String readMneumonic = text.substring(10,18);
        String readOperand = text.substring(19,30);
        String readComment = text.substring(31 - text.length());
        counter++;
      }

   }
   input.close();
 }catch(IOException e){
   e.printStackTrace();
 }

// Another variation
String temp, text = "";
int counter = 0;
try{
  Scanner input = new Scanner(new FileInputStream(file));
  while (input.hasNextLine()){
      String text = input.nextLine();
      StringTokenizer st = new StringTokenizer(text);
      while(st.hasMoreTokens()){
        temp = st.NextToken();
        if((temp.getSymbol().charAt(0)).equals(".")){
          // DO SOMETHING WITH COMMENT HERE
        }else if((temp.getSymbol().charAt(0).equals("+"))){
          // DO SOMETHING WITH FORMAT FOUR
        }else if(temp.equals("RESW")){
          // DO SOMETHING WITH MULTIPLYING THE NEXT NUMBER BY 3 AND ADDING TO ADDRESS
        }else if(formatThree.contains(temp)){

        }else if(formatTwo.contains(temp)){

        }else if(formatOne.contains(temp)){

        }else if(restrictedList.contains(temp)){
          
        }
      }
   }
   input.close();
 }catch(IOException e){
   e.printStackTrace();
 }
