package lab1.selections;

import lab1.mutations.InverseMutation;
import lab1.mutations.Mutation;
import lab1.mutations.SwapMutation;

public class SelectionFactory {
    public static Selection createSelection(String sel){
        switch (sel){
            case "tour":
                return new TournamentSelection();
            case "roulette":
                return new RouletteSelection();
            default:
                return new RouletteSelection();
        }
    }
}
