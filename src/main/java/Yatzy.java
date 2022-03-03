import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {

    public Yatzy() {}

    public int chance(int d1, int d2, int d3, int d4, int d5)
    {
        return dicesAsList(d1, d2, d3, d4, d5).stream().reduce(0, Integer::sum);
    }

    public int yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        List<Integer> dices = dicesAsList(d1, d2, d3, d4, d5);
        if (dices.stream().allMatch(dice -> Objects.equals(dice, dices.get(0)))) {
            return 50;
        }
        return 0;
    }

    public int ones(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(1, d1, d2, d3, d4, d5);
    }

    public int twos(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(2, d1, d2, d3, d4, d5);
    }

    public int threes(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(3, d1, d2, d3, d4, d5);
    }

    public int fours(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(4, d1, d2, d3, d4, d5);
    }

    public int fives(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(5, d1, d2, d3, d4, d5);
    }

    public int sixes(int d1, int d2, int d3, int d4, int d5)
    {
        return sumBy(6, d1, d2, d3, d4, d5);
    }

    public int pair(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(2, d1, d2, d3, d4, d5);
    }

    public int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        return findOccurrences(2, d1, d2, d3, d4, d5).stream().reduce(0, Integer::sum) * 2;
    }

    public int threeOfAKind(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(3, d1, d2, d3, d4, d5);
    }

    public int fourOfAKind(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(4, d1, d2, d3, d4, d5);
    }

    public int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    public int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    private List<Integer> dicesAsList(int d1, int d2, int d3, int d4, int d5) {
        return Arrays.asList(d1, d2, d3, d4, d5);
    }

    private int sumBy(int reference, int d1, int d2, int d3, int d4, int d5) {
        return dicesAsList(d1, d2, d3, d4, d5).stream().filter(dice -> dice == reference).reduce(0, Integer::sum);
    }

    private List<Integer> findOccurrences(int occurrenceCount, int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> diceGroup = dicesAsList(d1, d2, d3, d4, d5).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return diceGroup.entrySet().stream()
            .filter(entry -> entry.getValue() >= occurrenceCount)
            .sorted(Map.Entry.<Integer, Long>comparingByKey().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    private int sumByOccurrence(int occurrenceCount, int d1, int d2, int d3, int d4, int d5) {
        int sum = findOccurrences(occurrenceCount, d1, d2, d3, d4, d5).stream()
            .limit(1)
            .reduce(0, Integer::sum);
        return sum * occurrenceCount;
    }
}
