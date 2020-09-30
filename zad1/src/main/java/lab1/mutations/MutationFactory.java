package lab1.mutations;

public class MutationFactory {

    public static Mutation createMutation(String mut){
        switch (mut){
            case "swap":
                return new SwapMutation();
            case "inverse":
                return new InverseMutation();
            default:
                return new SwapMutation();
        }
    }
}
