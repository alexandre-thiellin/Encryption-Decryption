package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class EncryptionStrategy implements AlgorithmStrategy{

    @Override
    public void crypt(String[] args) {

        HashMap<String,String> map = new HashMap<>();
        for(int i=0;i< args.length-1;i++){
            map.put(args[i],args[i+1]);
        }

        String in = map.get("-in")!=null ? map.get("-in") : "";
        String data = map.get("-data")!=null ? map.get("-data") : "";
        String out = map.get("-out")!=null ? map.get("-out") : "";
        int key = map.get("-key")!=null ? Integer.parseInt(map.get("-key")) : 0;
        String alg = map.get("-alg")!=null ? map.get("-alg") : "";

        File fileIn;
        File fileOut;
        Scanner scanner = null;
        FileWriter fileWriter = null;

        if(data.length() != 0){
            if(out.length() != 0){
                try{
                    fileOut = new File(out);
                    fileWriter = new FileWriter(fileOut);
                    if(alg.equals("unicode")){
                        fileWriter.write(encryptUnicode(data, key));
                    } else {
                        fileWriter.write(encryptShift(data, key));
                    }
                } catch(IOException e){
                    e.printStackTrace();
                } finally {
                    try{
                        fileWriter.close();
                    } catch(IOException e2){
                        e2.printStackTrace();
                    }
                }
            } else{
                if(alg.equals("unicode")){
                    System.out.println(encryptUnicode(data, key));
                } else {
                    System.out.println(encryptShift(data, key));
                }

            }
        } else if(in.length() != 0){
            if(out.length() != 0){
                try{
                    fileIn = new File(in);
                    fileOut = new File(out);
                    scanner = new Scanner(fileIn);
                    fileWriter = new FileWriter(fileOut);
                    while(scanner.hasNext()){
                        if(alg.equals("unicode")){
                            fileWriter.write(encryptUnicode(scanner.nextLine(), key)+"\r\n");
                        } else {
                            fileWriter.write(encryptShift(scanner.nextLine(), key)+"\r\n");
                        }
                    }
                } catch(IOException e){
                    e.printStackTrace();
                } finally {
                    try{
                        scanner.close();
                        fileWriter.close();
                    } catch(IOException e2){
                        e2.printStackTrace();
                    }
                }
            } else {
                try{
                    fileIn = new File(in);
                    scanner = new Scanner(fileIn);
                    while(scanner.hasNext()){
                        if(alg.equals("unicode")){
                            System.out.println(encryptUnicode(scanner.nextLine(), key)+"\r\n");
                        } else {
                            System.out.println(encryptShift(scanner.nextLine(), key)+"\r\n");
                        }
                    }
                } catch(IOException e){
                    e.printStackTrace();
                } finally {
                    try{
                        scanner.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String encryptUnicode(String message,int key){
        for(int i=0;i<message.length();i++){
            message=message.substring(0,i)+ Character.toString((int)message.charAt(i)+key) + message.substring(i+1);
        }
        return message;
    }

    public String encryptShift(String message,int key){
        String circle1 = "abcdefghijklmnopqrstuvwxyz";
        String circle2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char cryptedChar = ' ';
        boolean test = false;
        int j = 0;
        for(int i=0;i<message.length();i++){
            cryptedChar = message.charAt(i);
            while(!test && j<26){
                if(message.charAt(i)==circle1.charAt(j)){
                    cryptedChar = circle1.charAt((j+key)%26);
                    test = true;
                } else if(message.charAt(i)==circle2.charAt(j)){
                    cryptedChar = circle2.charAt((j+key)%26);
                    test = true;
                }
                j++;
            }
            test = false;
            j = 0;
            message=message.substring(0,i)+ cryptedChar + message.substring(i+1);
        }
        return message;
    }
}
