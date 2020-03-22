
/**
 * General class containing main program to run the
 * iterated Prisoner's Dilemma (IPD).
 *
 * @author	081028AW
 */
public class RunIPD extends Object
{

    /**
     * Main program to start IPD program.
     */

    public static void main(String args[])
    {
        int i;
        int maxSteps = 0;

        Strategy player1, player2;
        IteratedPD ipd;

        for (i = 0; i < args.length; i++)
        {
            /* check parameters */
            if (args[i].equals("-l") || args[i].equals("-L"))
            {
                maxSteps = Integer.parseInt(args[i + 1]);
                System.out.println(" Max steps = " + maxSteps);
            }
            /* if */
        }
        /* for i */

        player1 = new StrategySample("0010");
        System.out.println("Running 0010 against...");
        String [] strategyDescs = new String []{"0011", "Tit of tat", "Tit for two tats", "Always defect", "Always cooperate", "Random", "1000"};
        Strategy [] players = new Strategy[7];
        players[0] = new StrategySample("1100");
        players[1] = new StrategyTitForTat();
        players[2] = new StrategyTitForTwoTats();
        players[3] = new StrategyAlwaysDefect();
        players[4] = new StrategyAlwaysCooperate();
        players[5] = new StrategyRandom();
        players[6] = new StrategySample("1000");
        for(i=0; i<players.length; i++)
        {
            System.out.println(strategyDescs[i]);
            ipd = new IteratedPD(player1, players[i]);

            ipd.runSteps(maxSteps);
            
            System.out.printf(" Player 1 score = %d\n", ipd.player1Score());
            System.out.printf(" Player 2 score = %d\n", ipd.player2Score());
        }
        
        
        

    }
    /* main */
}
/* class RunIPD */
