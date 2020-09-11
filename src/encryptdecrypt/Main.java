package encryptdecrypt;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < args.length - 1; i++) {
            map.put(args[i], args[i + 1]);
        }
        String mode = map.get("-mode")!=null ? (map.get("-mode").equals("dec") ? "dec" : "enc") : "enc" ;

        ChooseAlgorithm algo = new ChooseAlgorithm();

        switch(mode){
            case "enc":
                algo.setAlgorithm(new EncryptionStrategy());
                algo.doCrypt(args);
                break;
            case "dec":
                algo.setAlgorithm(new DecryptionStrategy());
                algo.doCrypt(args);
                break;
        }
    }
}
