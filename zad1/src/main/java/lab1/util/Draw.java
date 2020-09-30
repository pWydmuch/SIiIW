package lab1.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Draw {

    public static List<Integer> getIndices(int number, int size){
        List<Integer> indexes = IntStream.rangeClosed(0,size-1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(indexes);
        return indexes.stream()
                .limit(number)
                .collect(Collectors.toList());
    }
}
