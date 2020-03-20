public class Experiment2Fixed
{
    public static void main(String[] args) throws java.io.IOException
    {
        double[] crossoverRates = new double[]{0.5, 0.6, 0.7, 0.8, 0.9};
        double[] mutationRates = new double[]{0.001, 0.002, 0.003, 0.004, 0.005};
        int[] memoryLengths = new int[]{1, 2, 3};
        int[] numberOfGames = new int[]{64};

        for(double crossoverRate: crossoverRates)
        {
            for(double mutationRate: mutationRates)
            {
                for (int memoryLength : memoryLengths)
                {
                    for (int n: numberOfGames){
                        new Parameters("experiment-2fixed-crossover_rate-" + crossoverRate + "mutation_rate-" + mutationRate + "memory_length-" + memoryLength + "number_of_games-" + n, "IPD", "NA",
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