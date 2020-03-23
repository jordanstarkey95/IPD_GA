
/**
 * ****************************************************************************
 *  A Teaching GA					  Developed by Hal Stringer & Annie Wu, UCF
 *  Version 2, January 18, 2004
 ******************************************************************************
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class IPDStrategy extends FitnessFunction
{

    /**
     * *****************************************************************************
     * INSTANCE VARIABLES *
******************************************************************************
     */
    /**
     * *****************************************************************************
     * STATIC VARIABLES *
******************************************************************************
     */
    /**
     * *****************************************************************************
     * CONSTRUCTORS *
******************************************************************************
     */
    public IPDStrategy()
    {
        name = "IPD Strategy";
    }

    /**
     * *****************************************************************************
     * MEMBER METHODS *
******************************************************************************
     */
       
    //  COMPUTE A CHROMOSOME'S RAW FITNESS *************************************
    public void doRawFitness(Chromo[] member, int index)
    {

        member[index].rawFitness = 0;
        Strategy player1, player2;
        IteratedPD ipd;
        for (int i = 0; i < Parameters.popSize; i++)
        {
            player1 = new StrategySample(member[index].chromo);
            player2 = new StrategySample(member[i].chromo);
            ipd = new IteratedPD(player1, player2);

            ipd.runSteps(Parameters.numGames);
            
            member[index].rawFitness += ipd.player1Score();
        }
    }

//  PRINT OUT AN INDIVIDUAL GENE TO THE SUMMARY FILE *********************************
    public void doPrintGenes(Chromo X, FileWriter output) throws java.io.IOException
    {
        Hwrite.right("", 5, output);

        for (int i = 0; i < Parameters.numGenes; i++)
        {
            Hwrite.right(X.getGeneAlpha(i), 1, output);
        }
        output.write("   RawFitness");
        output.write("\n        ");
        for (int i = 0; i < Parameters.numGenes; i++)
        {
            Hwrite.right(X.getPosIntGeneValue(i), 1, output);
        }
        Hwrite.right((int) X.rawFitness, 13, output);
        output.write("\n\n");
        return;
    }

    /**
     * *****************************************************************************
     * STATIC METHODS *
******************************************************************************
     */
}   // End of IPDStrategy.java ******************************************************

