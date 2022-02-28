import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private final Roll twos = Roll.fromDice(Die.TWO, Die.TWO, Die.TWO, Die.TWO, Die.TWO);
    private final Roll ones = Roll.fromDice(Die.ONE, Die.ONE, Die.ONE, Die.ONE, Die.ONE);

    @Test
    void yatzy() {
        AllSame allSame = () -> true;
        AllSame allNotSame = () -> false;
        assertEquals(50, Category.yatzy.score(allSame));
        assertEquals(0, Category.yatzy.score(allNotSame));
    }
    
    @Test
    void chanse() {
        assertEquals(5, Category.chanse.score(ones));
        assertEquals(10, Category.chanse.score(twos));
    }
    
    @Test
    void ofKind() {
        assertEquals(1, Category.ones.score(die -> IntStream.of(1)));
        assertEquals(2, Category.ones.score(die -> IntStream.of(1, 1)));
        assertEquals(0, Category.ones.score(twos));
        assertEquals(10, Category.twos.score(twos));
    }
    
    @Test
    void pair() {
        assertEquals(0, Category.pair.score(IntStream::empty));
        assertEquals(2, Category.pair.score(() -> IntStream.of(1)));
        assertEquals(10, Category.pair.score(() -> IntStream.of(1,5)));
    }   
    
    @Test
    void twoPairs() {
        assertEquals(0, Category.twoPair.score(IntStream::empty));
        assertEquals(0, Category.twoPair.score(() -> IntStream.of(1)));
        assertEquals(12, Category.twoPair.score(() -> IntStream.of(1,5)));
    }

    @Test
    void threeOfAKind() {
        assertEquals(3, Category.threeOfAKind.score(() -> Optional.of(1)));
        assertEquals(6, Category.threeOfAKind.score(() -> Optional.of(2)));
    }
}