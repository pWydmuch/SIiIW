package si.zad2.value_heuritics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomValueHeuritic implements ValueHeuristic {
    @Override
    public int[] chooseValues(int[] domain) {
        List<Integer> list =  new ArrayList<>();
        for(int i:domain) list.add(i);
        Collections.shuffle(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
