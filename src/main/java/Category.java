import java.util.List;
import java.util.stream.Collectors;

public interface Category<R> {
    int score(R roll);


    Category<Roll> chanse = roll -> roll.values().sum();
    Category<AllSame> yatzy = roll -> roll.allSame() ? 50 : 0;
    Category<AllOfKind> ones = ofKind(Die.ONE);
    Category<AllOfKind> twos = ofKind(Die.TWO);
    Category<AllOfKind> threes = ofKind(Die.THREE);
    Category<AllOfKind> fours = ofKind(Die.FOUR);
    Category<AllOfKind> fives = ofKind(Die.FIVE);
    Category<AllOfKind> sixes = ofKind(Die.SIX);
    Category<Pairs> pair = roll -> roll.pairs().max().orElse(0) * 2;
    Category<Pairs> twoPair = roll -> {
        List<Integer> pairs = roll.pairs().sorted().boxed().toList();
        int size = pairs.size();
        if (size < 2) {
            return 0;
        }
        return (pairs.get(size - 1) + pairs.get(size - 2)) * 2;
    };
    Category<ThreeOfKind> threeOfAKind =
        roll -> roll.threeOfKind().orElse(0) * 3;
    Category<FourOfKind> fourOfAKind =
        roll -> roll.fourOfKind().orElse(0) * 3;
    
    static Category<AllOfKind> ofKind(Die die) {
        return roll -> roll.allOfKind(die).sum();
    }
}
