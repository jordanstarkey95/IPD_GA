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

        Strategy[] opponentStrategies = new Strategy[]{new StrategyTitForTat(), new StrategyTitForTwoTats(), new StrategyAlwaysDefect(), new StrategyAlwaysCooperate()};

        for(int i=minSteps; i <= maxSteps; i*=10) {
            for (Strategy opponentStrategy : opponentStrategies)
            {
                int player1Score = 0;
                int player2Score = 0;

                for(int j=0; j < monteCarloLength; j++)
                {
                    player1 = new StrategySample("1000");
                    player2 = opponentStrategy;

                    ipd = new IteratedPD(player1, player2);
                    ipd.runSteps(i);

                    player1Score += ipd.player1Score();
                    player2Score += ipd.player2Score();
                }

                System.out.printf(" %s vs %s\n", (new StrategySample()).name, opponentStrategy.name);
                System.out.printf(" Average player 1 score = %d\n", player1Score/monteCarloLength);
                System.out.printf(" Average player 2 score = %d\n", player2Score/monteCarloLength);
                System.out.println();

                writer.printf(" %s vs %s\n", (new StrategySample()).name, opponentStrategy.name);
                writer.printf(" Average player 1 score = %d\n", player1Score/monteCarloLength);
                writer.printf(" Average player 2 score = %d\n", player2Score/monteCarloLength);
                writer.println();
            }
        }

        writer.close();

    }
    /* main */
}
/* class RunIPD */
