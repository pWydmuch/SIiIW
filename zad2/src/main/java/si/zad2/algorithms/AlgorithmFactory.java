package si.zad2.algorithms;

public class AlgorithmFactory {

    public static Algorithm getAlgorithm(String algorithm){
        switch (algorithm){
            case "backtrack":
                return new BacktrackAlgorithm();
            default:
                return new ForwardAlgorithm();
        }
    }

}
