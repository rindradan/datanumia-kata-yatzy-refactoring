import org.junit.*;
import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        Yatzy yatzy = new Yatzy();
        assertEquals(15, yatzy.chance(2,3,4,5,1));
        assertEquals(16, yatzy.chance(3,3,4,5,1));
    }

    @Test
    public void yatzy_scores_50() {
        Yatzy yatzy = new Yatzy();
        assertEquals(50, yatzy.yatzy(4,4,4,4,4));
        assertEquals(50, yatzy.yatzy(6,6,6,6,6));
        assertEquals(0, yatzy.yatzy(6,6,6,6,3));
    }

    @Test
    public void ones() {
        Yatzy yatzy = new Yatzy();
        assertEquals(1, yatzy.ones(1, 2, 3, 4, 5));
        assertEquals(2, yatzy.ones(1,2,1,4,5));
        assertEquals(0, yatzy.ones(6,2,2,4,5));
        assertEquals(4, yatzy.ones(1,2,1,1,1));
    }

    @Test
    public void twos() {
        Yatzy yatzy = new Yatzy();
        assertEquals(4, yatzy.twos(1,2,3,2,6));
        assertEquals(10, yatzy.twos(2,2,2,2,2));
    }

    @Test
    public void threes() {
        Yatzy yatzy = new Yatzy();
        assertEquals(6, yatzy.threes(1,2,3,2,3));
        assertEquals(12, yatzy.threes(2,3,3,3,3));
    }

    @Test
    public void fours()
    {
        Yatzy yatzy = new Yatzy();
        assertEquals(12, yatzy.fours(4,4,4,5,5));
        assertEquals(8, yatzy.fours(4,4,5,5,5));
        assertEquals(4, yatzy.fours(4,5,5,5,5));
    }

    @Test
    public void fives() {
        Yatzy yatzy = new Yatzy();
        assertEquals(10, yatzy.fives(4,4,4,5,5));
        assertEquals(15, yatzy.fives(4,4,5,5,5));
        assertEquals(20, yatzy.fives(4,5,5,5,5));
    }

    @Test
    public void sixes() {
        Yatzy yatzy = new Yatzy();
        assertEquals(0, yatzy.sixes(4,4,4,5,5));
        assertEquals(6, yatzy.sixes(4,4,6,5,5));
        assertEquals(18, yatzy.sixes(6,5,6,6,5));
    }

    @Test
    public void one_pair() {
        Yatzy yatzy = new Yatzy();
        assertEquals(6, yatzy.pair(3,4,3,5,6));
        assertEquals(10, yatzy.pair(5,3,3,3,5));
        assertEquals(12, yatzy.pair(5,3,6,6,5));
    }

    @Test
    public void two_Pair() {
        Yatzy yatzy = new Yatzy();
        assertEquals(16, yatzy.two_pair(3,3,5,4,5));
        assertEquals(16, yatzy.two_pair(3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() 
    {
        Yatzy yatzy = new Yatzy();
        assertEquals(9, yatzy.threeOfAKind(3,3,3,4,5));
        assertEquals(15, yatzy.threeOfAKind(5,3,5,4,5));
        assertEquals(9, yatzy.threeOfAKind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_kind() {
        Yatzy yatzy = new Yatzy();
        assertEquals(12, yatzy.fourOfAKind(3,3,3,3,5));
        assertEquals(20, yatzy.fourOfAKind(5,5,5,4,5));
        assertEquals(9, yatzy.threeOfAKind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        Yatzy yatzy = new Yatzy();
        assertEquals(15, yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        Yatzy yatzy = new Yatzy();
        assertEquals(20, yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        Yatzy yatzy = new Yatzy();
        assertEquals(18, yatzy.fullHouse(6,2,2,2,6));
        assertEquals(0, yatzy.fullHouse(2,3,4,5,6));
    }
}
