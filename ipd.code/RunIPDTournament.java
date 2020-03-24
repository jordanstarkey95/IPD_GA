import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Source: RunIPD.java
 * Author: 081028AW
 * General class containing main program to run the
 * iterated Prisoner's Dilemma (IPD).
 *
 * @author	081028AW
 */
public class RunIPDTournament extends Object
{

    /**
     * Main program to start IPD program.
     */

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("tournament_summary.txt", "UTF-8");

        int minSteps = 10;
        int maxSteps = 1000000;

        int monteCarloLength = 100;

        Strategy player1, player2;
        IteratedPD ipd;

        Strategy[] opponentStrategies = new Strategy[]{new StrategyTitForTat(), new StrategyTitForTwoTats(), new StrategyAlwaysDefect(), new StrategyAlwaysCooperate(), new StrategyRandom()};

        for(int i=minSteps; i <= maxSteps; i*=10) {
            for (Strategy opponentStrategy : opponentStrategies)
            {
                int player1Score = 0;
                int player2Score = 0;

                int [] player1scores = new int[monteCarloLength];
                int [] player2scores = new int[monteCarloLength];

                for(int j=0; j < monteCarloLength; j++)
                {
                    player1 = new StrategySample("0000");
                    player2 = opponentStrategy;

                    ipd = new IteratedPD(player1, player2);
                    ipd.runSteps(i);

                    player1scores[j] = ipd.player1Score();
                    player2scores[j] = ipd.player2Score();

                    player1Score += ipd.player1Score();
                    player2Score += ipd.player2Score();
                }

                double player1Mean = player1Score/(double)monteCarloLength;
                double player2Mean = player2Score/(double)monteCarloLength;

                double player1Stdev = stdev(player1Score, player1scores);
                double player2Stdev = stdev(player2Score, player2scores);

                System.out.printf(" StrategySample vs %s\n", (opponentStrategy.getName()));
                System.out.printf(" Average player 1 score = %d\n", (int)player1Mean);
                System.out.printf(" Average player 2 score = %d\n", (int)player2Mean);
                System.out.printf(" Stdev for player 1 score = %.2f\n", player1Stdev);
                System.out.printf(" Stdev for player 2 score = %.2f\n", player2Stdev);
                System.out.println();

                writer.printf(" StrategySample vs %s\n", (opponentStrategy.getName()));
                writer.printf(" Average player 1 score = %d\n", (int)player1Mean);
                writer.printf(" Average player 2 score = %d\n", (int)player2Mean);
                writer.printf(" Stdev for player 1 score = %.2f\n", player1Stdev);
                writer.printf(" Stdev for player 2 score = %.2f\n", player2Stdev);
                writer.println();
            }
        }

        writer.close();

    }

    private static double stdev(int sum, int [] list) {
        double num=0.0;
        double mean = sum / (double) list.length;

        for (int i = 0; i < list.length; i++) {
            num += Math.pow(((double) list[i] - mean), 2);
        }

        return Math.sqrt(num/list.length);
    }
    /* main */
}
/* class RunIPD */
