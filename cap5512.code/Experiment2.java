public class Experiment2
{
    public static void main(String[] args) throws java.io.IOException
    {
        double[] crossoverRates = new double[]{0.01, 0.05, 0.1, 0.5};
        double[] mutationRates = new double[]{0.001, 0.003, 0.005, 0.01, 0.05};
        int[] memoryLengths = new int[]{1, 2, 3};
        int[] numberOfGames = new int[]{64};

        for(double crossoverRate: crossoverRates)
        {
            for(double mutationRate: mutationRates)
            {
                for (int memoryLength : memoryLengths)
                {
                    for (int n: numberOfGames){
                        new Parameters("ENCODEDexperiment-2crossover_rate-" + crossoverRate + "mutation_rate-" + mutationRate + "memory_length-" + memoryLength + "number_of_games-" + n, "IPD", "NA",
                                10, 100, 100,
                                2, 0,
                                1, crossoverRate, 1, mutationRate,
                                75982, 1, n, memoryLength);
                        Search.main(new String[]{});
                    }
                }
            }
        }
    }
}