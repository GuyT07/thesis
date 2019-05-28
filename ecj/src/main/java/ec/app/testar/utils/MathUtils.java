package ec.app.testar.utils;

import ec.app.testar.Metric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MathUtils {

    private static double getMedian(final ArrayList<Map<String, Double>> results, final String column) {
        // note: this will take at least o(n log n) but the number of sequences will be low, so it does not imply
        // a serious performance penalty in our case
        final List<Double> sortedListOfColumnValues = sortListOfDoubles(
                getValuesSpecificColumn(results, column)
        );
        final int noOfSequences = results.size();

        // if n is odd: x[(n-1)/2]
        if ((noOfSequences % 2) != 0) {
            return sortedListOfColumnValues.get(((noOfSequences - 1) / 2));
        } else {
            // if n is even: ( x[n/2] + x[(n/2)-1] ) / 2
            return (sortedListOfColumnValues.get((noOfSequences / 2)) + sortedListOfColumnValues.get(((noOfSequences / 2) - 1))) / 2;
        }
    }

    public static double getMeanOf(final ArrayList<Map<String, Double>> results, final String column) {
        return Arrays.stream(Metric.headers)
                .filter(header -> header.equals(column))
                .mapToDouble((metric) -> sumOfMetric(results, metric) / results.size())
                .sum();
    }

    private static double sumOfMetric(final ArrayList<Map<String, Double>> results, final String metric) {
        return results.stream()
                .mapToDouble((singleResult) -> singleResult.get(metric))
                .sum();
    }

    private static List<Double> sortListOfDoubles(final List<Double> listToSort) {
        Collections.sort(listToSort);
        return listToSort;
    }

    private static List<Double> getValuesSpecificColumn(final ArrayList<Map<String, Double>> results, final String column) {
        return results.stream()
                .map((map) -> map.get(column))
                .collect(Collectors.toList());
    }
}
