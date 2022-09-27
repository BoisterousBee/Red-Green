import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class RedGreen {
    public static ArrayList<Integer[]> temp = new ArrayList<>();
    public static HashMap<Integer, Boolean> finalmap = new HashMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()){
            input.add(sc.nextLine());
        }
        for (String s: input){
            Integer[] integers = new Integer[2];
            String[] split = s.split("\\s+");
            if (split.length == 2){
                if (isNumeric(split[0])&&isNumeric(split[1])){
                    int a = Integer.parseInt(split[0]);
                    int b = Integer.parseInt(split[1]);
                    if (a <= b){
                        integers[0] = a;
                        integers[1] = b;
                        temp.add(integers);
                    } else {
                        System.out.println("Bad input: " + s);
                    }
                } else {
                    System.out.println("Bad input: " + s);
                }
            } else {
                System.out.println("Bad input: " + s);
            }
        }
        for (Integer[] intArray : temp){
            finalmap.clear();
            for (int i = intArray[0]; i <= intArray[1];i++){
                ArrayList<Integer> intarr = new ArrayList<Integer>(nearFactorCalc(i));
                typeCalc(i,intarr);
                
            }
            printMap(intArray);
        }
        
    }
    public static void typeCalc(int j,ArrayList<Integer> intarr){
        int greenCount = 0;
        int redCount = 0;
        for (int i : intarr){
            if (!(finalmap.containsKey(i))){
                ArrayList<Integer> intarr2 = new ArrayList<Integer>(nearFactorCalc(i));
                typeCalc(i,intarr2);
            }
            if (finalmap.get(i)){
                greenCount++;
            } else {
                redCount++;
            }
        }
        if (greenCount > redCount){
            finalmap.put(j, false);
        } else {
            finalmap.put(j, true);
        }

    }
    public static ArrayList<Integer> nearFactorCalc(int count){
        ArrayList<Integer> intarr = new ArrayList<Integer>();
        for (int i = 2; i <= count;i++){
            int value = count/i;
            if (count != 1) intarr.add(value); 
            if (value == 1) break;
        }
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(intarr);
        intarr.clear();
        intarr.addAll(set);
        return intarr;
    }
    public static boolean isNumeric(String string){
        return (string != null && string.matches("[0-9.]+"));
    }
    public static void printMap(Integer[] intarr){
        System.out.print("\n" + intarr[0] + " " + intarr[1] + " ");
        for (int i = intarr[0]; i <= intarr[1];i++){
            if (finalmap.get(i)){
                System.out.print("G");
            } else {
                System.out.print("R");
            }
        }
    }
}
