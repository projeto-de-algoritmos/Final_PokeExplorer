package br.com.pokeexplorer.util;

public abstract class KnapSackUtil {
    public static Integer max(Integer a, Integer b) {
        return (a > b) ? a : b;
    }

    public static int[] knapSack(Integer time, Integer[] health, Integer[] damage, Integer teamLength) {
        Integer i, w;
        Integer K[][] = new Integer[teamLength + 1][time + 1];

        for (i = 0; i <= teamLength; i++) {
            for (w = 0; w <= time; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (health[i - 1] <= w)
                    K[i][w] = max(damage[i - 1] + K[i - 1][w - health[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        int res = K[teamLength][time];
        w = time;
        int j = 0;
        int[] indexes = new int[6];
        for (i = teamLength; i > 0 && res > 0; i--) {

            if (res == K[i - 1][w])
                continue;
            else {
                indexes[j] = i - 1;
                j++;
                res = res - damage[i - 1];
                w = w - health[i - 1];
            }
        }
        return indexes;
    }
}

