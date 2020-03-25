
import java.util.Arrays;


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

        //String [] strategies = new String []{"Axelrod", "0010010001110010", "0001011000000000", "0000", "0001110001100010", "1010010010000010", "0011001001001001001000100101100000100111001001011101000100000010", "0000000110111010101100111110000011001111101110011110111100000001", "0001110001011001", "1010000000100110010100101110101111101111010000001101011100110010", "0001101101100001", "0000001000001110011101100000001111010001110011010111100110111000", "0000", "1000", "1000", "0000", "1000101011001110010011011101000100100110000011110100101010001010", "0011000010000000011101011110111100011010001110010110010100100000", "1000010111110000", "0000101111111000000000010111010101000100010011101111101110100000", "0000010010100000", "1000", "0001000001011011000001000101011100111010110010101011000000001000"};
        String [] strategies = new String []{"Axelrod", "0000"};
        int [] scores = new int [strategies.length];
        
        Arrays.fill(scores, 0);
        
        for(i=0; i<strategies.length; i++)
        {
            for(int j=0; j<strategies.length; j++)
            {
                System.out.println(strategies[i] + " vs " + strategies[j]);
                if(i == 0)
                    player1 = new EncodedStrategySample("0000");
                else
                    player1 = new StrategySample(strategies[i]);
                if(j == 0)
                    player2 = new EncodedStrategySample();
                else
                    player2 = new StrategySample(strategies[j]);
                ipd = new IteratedPD(player1, player2);

                ipd.runSteps(maxSteps);
                
                if(ipd.player1Score() > ipd.player2Score())
                    scores[i] += 1;
            
                System.out.printf(" Player 1 score = %d\n", ipd.player1Score());
                System.out.printf(" Player 2 score = %d\n", ipd.player2Score());
            }
        }
        
        System.out.println(Arrays.toString(scores));
        
        strategies = new String []{"Axelrod"};
        scores = new int [strategies.length];
        
        Arrays.fill(scores, 0);
        
        String [] strategyDescs = new String []{"Tit of tat", "Tit for two tats", "Always defect", "Always cooperate", "Random"};
        Strategy [] players = new Strategy[]{new StrategyTitForTat(), new StrategyTitForTwoTats(), new StrategyAlwaysDefect(), new StrategyAlwaysCooperate(), new StrategyRandom()};
        for(i=0; i<strategies.length; i++)
        {
            for(int j=0; j<players.length; j++)
            {
                if(j!=2)
                    continue;
                System.out.println(strategies[i] + " vs " + strategyDescs[j]);
                
                if("Axelrod".equals(strategies[i]))
                    player1 = new EncodedStrategySample();
                else
                    player1 = new StrategySample(strategies[i]);
        
                ipd = new IteratedPD(player1, players[j]);

                ipd.runSteps(maxSteps);
                
                if(ipd.player1Score() > ipd.player2Score())
                    scores[i] += 1;

                System.out.printf(" Player 1 score = %d\n", ipd.player1Score());
                System.out.printf(" Player 2 score = %d\n", ipd.player2Score());
            }
        }
        
        System.out.println(Arrays.toString(scores));
        

    }
    /* main */
}
/* class RunIPD */
