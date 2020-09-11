package encryptdecrypt;

public class ChooseAlgorithm {

    private AlgorithmStrategy algorithm;

    public void setAlgorithm(AlgorithmStrategy algorithm){
        this.algorithm = algorithm;
    }

    public void doCrypt(String[] args){
        algorithm.crypt(args);
    }
}
