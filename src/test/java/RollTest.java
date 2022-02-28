import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RollTest {
    @Test
    void allSame() {
        assertEquals(true, ((Roll) () -> IntStream.of(1)).allSame());
        assertEquals(false, ((Roll) () -> IntStream.of(1,2)).allSame());
    }

    @Test
    void fromDice() {
        Roll roll = 
            Roll.fromDice(Die.ONE, Die.TWO, Die.THREE, Die.FOUR, Die.FIVE);
        assertEquals(
            roll.values().boxed().collect(Collectors.toSet()),
            Set.of(1,2,3,4,5)
        );
    }

    @Test
    void allOfKind() {
        assertArrayEquals(
            new int[] {2},
            ((Roll) () -> IntStream.of(1,2)).allOfKind(Die.TWO).toArray()
        );        
        assertArrayEquals(
            new int[] {1, 1},
            ((Roll) () -> IntStream.of(1, 1,2)).allOfKind(Die.ONE).toArray()
        );
    }
    
    @Test
    void pairs() {
        assertArrayEquals(
            new int[] {},
            ((Roll) () -> IntStream.of(1, 2, 3, 4, 5)).pairs().toArray()
        ); 
        assertArrayEquals(
            new int[] {1},
            ((Roll) () -> IntStream.of(1, 1)).pairs().toArray()
        );        
        assertArrayEquals(
            new int[] {1, 2},
            ((Roll) () -> IntStream.of(1, 1, 2, 2)).pairs().toArray()
        );        
        assertArrayEquals(
            new int[] {1, 2},
            ((Roll) () -> IntStream.of(1, 1, 1, 2, 2)).pairs().toArray()
        );
    }
}