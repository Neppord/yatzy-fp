import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Roll extends AllSame, AllOfKind, Pairs, ThreeOfKind, FourOfKind {
    static Roll fromDice(Die d1, Die d2, Die d3, Die d4, Die d5) {
        return () -> 
            IntStream.of(d1.value, d2.value, d3.value, d4.value, d5.value);
    }

    IntStream values();

    default boolean allSame() {
        return values().distinct().count() == 1;
    }

    default IntStream allOfKind(Die die) {
        return values().filter( it -> it == die.value);
    }

    default IntStream pairs() {
        return frequency().entrySet()
            .stream()
            .filter(e -> e.getValue() >= 2)
            .mapToInt(Map.Entry::getKey);
    }

    private Map<Integer, Integer> frequency() {
        return values().boxed().collect(Collectors.toMap(
            Function.identity(),
            _x -> 1,
            Integer::sum
        ));
    }

    default Optional<Integer> threeOfKind() {
        return frequency().entrySet()
            .stream()
            .filter(e -> e.getValue() >= 3)
            .findFirst()
            .map(Map.Entry::getKey);
    }
    
    default Optional<Integer> fourOfKind() {
        return frequency().entrySet()
            .stream()
            .filter(e -> e.getValue() >= 4)
            .findFirst()
            .map(Map.Entry::getKey);
    }
}
