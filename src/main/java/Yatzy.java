import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Yatzy {

    public Yatzy() {}

    public int chance(int d1, int d2, int d3, int d4, int d5)
    {
        return dicesAsList(d1, d2, d3, d4, d5).stream().reduce(0, Integer::sum);
    }

    public int yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        Map<Integer, Long> dicesGroup = dicesGroup(d1, d2, d3, d4, d5);
        if (dicesGroup.size() == 1) {
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
        return sumByOccurrence(2, 1, d1, d2, d3, d4, d5);
    }

    public int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(2, 2, d1, d2, d3, d4, d5);
    }

    public int threeOfAKind(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(3, 1, d1, d2, d3, d4, d5);
    }

    public int fourOfAKind(int d1, int d2, int d3, int d4, int d5)
    {
        return sumByOccurrence(4, 1, d1, d2, d3, d4, d5);
    }

    public int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        Predicate<Map<Integer, Long>> predicate = dicesGroup -> dicesGroup.size() == 5 && dicesGroup.containsKey(1) && !dicesGroup.containsKey(6);
        return sumByPredicate(predicate, d1, d2, d3, d4, d5);
    }

    public int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        Predicate<Map<Integer, Long>> predicate = dicesGroup -> dicesGroup.size() == 5 && !dicesGroup.containsKey(1) && dicesGroup.containsKey(6);
        return sumByPredicate(predicate, d1, d2, d3, d4, d5);
    }

    public int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        Predicate<Map<Integer, Long>> predicate = dicesGroup -> dicesGroup.containsValue(3L) && dicesGroup.containsValue(2L);
        return sumByPredicate(predicate, d1, d2, d3, d4, d5);
    }

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***

    private List<Integer> dicesAsList(int d1, int d2, int d3, int d4, int d5) {
        return Arrays.asList(d1, d2, d3, d4, d5);
    }

    private int sumBy(int reference, int d1, int d2, int d3, int d4, int d5) {
        return dicesAsList(d1, d2, d3, d4, d5).stream().filter(dice -> dice == reference).reduce(0, Integer::sum);
    }

    private Map<Integer, Long> dicesGroup(int d1, int d2, int d3, int d4, int d5) {
        return dicesAsList(d1, d2, d3, d4, d5).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private int sumByOccurrence(int occurrenceCount, int limit, int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> dicesGroup = dicesGroup(d1, d2, d3, d4, d5);
        int sum = dicesGroup.entrySet().stream()
            .filter(entry -> entry.getValue() >= occurrenceCount)
            .map(Map.Entry::getKey)
            .sorted(Comparator.reverseOrder())
            .limit(limit)
            .reduce(0, Integer::sum);
        return sum * occurrenceCount;
    }

    private int sumByPredicate(Predicate<Map<Integer, Long>> predicate, int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> dicesGroup = dicesGroup(d1, d2, d3, d4, d5);
        if (predicate.test(dicesGroup)) {
            return dicesGroup.entrySet().stream().map(dice -> dice.getKey() * dice.getValue()).reduce(0L, Long::sum).intValue();
        }
        return 0;
    }
}
