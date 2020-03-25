public class Experiment
{
    public static void main(String[] args) throws java.io.IOException
    {
        int[] memoryLengths = new int[]{3, 2, 1};
        double[] crossoverRates = new double[]{0.5, 0.9};
        double[] mutationRates = new double[]{0.5, 0.9};
        int[] numberOfGames = new int[]{100, 1000};
        
        for (int memoryLength : memoryLengths)
        {
            int numGenes = (int)Math.pow(2, memoryLength*2);
            for(double crossoverRate: crossoverRates)
            {
                for(double mutationRate: mutationRates)
                {
                    for (int n: numberOfGames)
                    {
                        String expID = "exp-2_mem-" + memoryLength + "_xrate-" + crossoverRate + "_mrate-" + mutationRate + "_ng-" + n;
                        new Parameters(expID, 
                            10, 100, 100,
                            crossoverRate, mutationRate, 75982, 
                            numGenes, n);
                        Search.main(new String[]{});
                    }
                }
            }
        }
    }
}