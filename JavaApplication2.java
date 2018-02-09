package javaapplication2;

import javax.swing.JOptionPane;

/**
 *
 * @author TheEclipsedLock
 */
public class JavaApplication2 {

    public static void main(String[] args) {
        try{
            //The following code grabs the input from the user.
            int input = Integer.parseInt(JOptionPane.showInputDialog("Place your up to 10-digit number here"));

            //The following code creates a few important variables.
            //In case a user inputs a 10-digit number like 9999999999, the checkInt
            //is useful to check if there's potential for the input value to overflow.
            //Count checks how many digits are in the input number.
            //countPow is the exponent value, for nums like 10^9, 10^8, etc.
            //multiply stores whatever number is generated from 10^countPow.
            //The result is self-explanatory.
            //The isNegative checks if a number is negative.
            //The maxLengthArr is an array holding 10 spots, the max an int has.
            long checkInt = 0;
            int maxInt = 2147483647, count = 0, countPow = 0, multiply = 1, result = 0;
            boolean isNeg = false;
            int[] maxLengthArr = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

            //This if statement helps to set the isNeg flag to true.
            //This is necessary since reversing a negative number can get
            //a little bit weird. So, we remove the negative and add it later.
            if(input < 0){
                isNeg = true;
                input = input * (-1);
            }
            
            //This last declaration stores a positive originalInput.
            int Originput = input;

            //This while loop counts how many digits are in the input number.
            while(input > 0){
                count = count + 1;
                input = input / 10;
            }
            
            //Restoring input to its original input... just positive.
            input = Originput;

            //From 0 to one less than count, we start storing individual numbers
            //into the array in a reversed order.
            for(int i = 0; i < count; i++){
                maxLengthArr[i] = input % 10;
                    //System.out.println("i is now at "+i+". Input is now at "+input+". What went into the array was "+(maxLengthArr[i]));
                input = input / 10;
                    //System.out.println("Input is now at "+input+". Loop over.");
            }
            
            //Setting countPow to count minus one so that
            //when we later display the original number from the array
            //the for loop starts from 10^x to 10^x-1, 10^x-2, ... 10^1
            countPow = count - 1;
            
            //We iterate through the array and ignore all values that are -1,
            //indicating that the input is a length of less than 10.
            for(int h = maxLengthArr.length-1; h >= 0; h--){
                if(!(maxLengthArr[h] == -1)){
                    multiply = (int)Math.pow(10, countPow);
                        //System.out.print("h is +"+h+". CountPow is "+countPow+" Multiply is "+multiply+". Result is "+result);
                    result = result + (multiply * maxLengthArr[h]);
                        //System.out.print(" + ("+multiply+"*"+maxLengthArr[h]+") = "+result+"\n");
                    countPow = countPow - 1;
                }
            }
            System.out.println("Currently: "+result);

            System.out.println("***********************************************************************************");
            
            //We reset the important values.
            countPow = count - 1;
            result = 0;
            multiply = 1;
            checkInt = 0;
            
            //This time we are starting from the bottom of the array and we count
            //up since we reversed it when adding values.
            //What is also different is that we are multiplying the
            //multiply value with maxLengthArr at index j along with 
            //multiplying it with 1L. This is to prevent an issue where
            //it accidentally creates an overflow since they're still int
            //numbers being multipled and now immediately stored in a long.
            for(int j = 0; j < count; j++){
                multiply = (int)Math.pow(10, countPow);
                    //System.out.print("Count is +"+count+". CountPow is "+countPow+" Multiply is "+multiply+". Result is "+checkInt);
                checkInt = checkInt + (multiply *1L* maxLengthArr[j]);
                    //System.out.print(" + ("+multiply+"*"+maxLengthArr[j]+") = "+checkInt+"\n");
                countPow = countPow - 1;
            }
            
            //If the checkInt is a value larger than what an integer
            //can store, we return 0. Else we can display the value.
            //If the isNeg flag is marked, we return the number to be
            //negative.
            if(checkInt <= maxInt){
                result = (int)checkInt;
                if(isNeg == true){
                    result = result * (-1);
                }
                JOptionPane.showMessageDialog(null, "The reversed value is +"+result+".");
            }
            else{
                result = 0;
                JOptionPane.showMessageDialog(null, "The value cannot be stored in an int.");
            }
        }
        //In case the number inputted is more than 10 digits, we can throw
        //the number format exception.
    catch(NumberFormatException e){
        JOptionPane.showMessageDialog(null, "You inputted more than 10 digits!\nOr you inputted a number that cannot be stored in an int. \n"
                + "Goodbye.", "Directions weren't followed!", JOptionPane.ERROR_MESSAGE);
    }
    
}
}
