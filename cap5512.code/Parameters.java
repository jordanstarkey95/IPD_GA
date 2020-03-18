/******************************************************************************
*  A Teaching GA					  Developed by Hal Stringer & Annie Wu, UCF
*  Version 2, January 18, 2004
*******************************************************************************/

import java.io.*;
import java.util.*;
import java.text.*;

public class Parameters
{

/*******************************************************************************
*                            INSTANCE VARIABLES                                *
*******************************************************************************/

	public static String expID;
	public static String problemType;

	public static String dataInputFileName;

	public static int numRuns;
	public static int generations;
	public static int popSize;

	public static int genCap;
	public static int fitCap;

	public static String minORmax;
	public static int selectType;
	public static int scaleType;

	public static int xoverType;
	public static double xoverRate;
	public static int mutationType;
	public static double mutationRate;

	public static long seed;
	public static int numGenes;
	public static int geneSize;

	public static int numGames;

	public static int memoryLength;

/*******************************************************************************
*                              CONSTRUCTORS                                    *
*******************************************************************************/

	public Parameters(String parmfilename) throws java.io.IOException{

		String readLine;
		BufferedReader parmInput = new BufferedReader(new FileReader (parmfilename));

		expID = parmInput.readLine().substring(30);
		problemType = parmInput.readLine().substring(30);

		dataInputFileName = parmInput.readLine().substring(30);

		numRuns = Integer.parseInt(parmInput.readLine().substring(30).trim());
		generations = Integer.parseInt(parmInput.readLine().substring(30).trim());
		popSize = Integer.parseInt(parmInput.readLine().substring(30).trim());

		selectType = Integer.parseInt(parmInput.readLine().substring(30).trim());
		scaleType = Integer.parseInt(parmInput.readLine().substring(30).trim());

		xoverType = Integer.parseInt(parmInput.readLine().substring(30).trim());
		xoverRate = Double.parseDouble(parmInput.readLine().substring(30).trim());
		mutationType = Integer.parseInt(parmInput.readLine().substring(30).trim());
		mutationRate = Double.parseDouble(parmInput.readLine().substring(30).trim());

		seed = Long.parseLong(parmInput.readLine().substring(30).trim());
		geneSize = Integer.parseInt(parmInput.readLine().substring(30).trim());

		numGames = Integer.parseInt(parmInput.readLine().substring(30).trim());
		memoryLength = Integer.parseInt(parmInput.readLine().substring(30).trim());

		numGenes = (int) Math.pow(2, 2*memoryLength);

		parmInput.close();

		if (scaleType==0 || scaleType==2) minORmax = "max";
		else minORmax = "min";

	}

/*******************************************************************************
*                                MEMBER METHODS                                *
*******************************************************************************/


/*******************************************************************************
*                             STATIC METHODS                                   *
*******************************************************************************/

	public static void outputParameters(FileWriter output) throws java.io.IOException{


		output.write("Experiment ID                :  " + expID + "\n");
		output.write("Problem Type                 :  " + problemType + "\n");

		output.write("Data Input File Name         :  " + dataInputFileName + "\n");

		output.write("Number of Runs               :  " + numRuns + "\n");
		output.write("Generations per Run          :  " + generations + "\n");
		output.write("Population Size              :  " + popSize + "\n");

		output.write("Selection Method             :  " + selectType + "\n");
		output.write("Fitness Scaling Type         :  " + scaleType + "\n");
		output.write("Min or Max Fitness           :  " + minORmax + "\n");

		output.write("Crossover Type               :  " + xoverType + "\n");
		output.write("Crossover Rate               :  " + xoverRate + "\n");
		output.write("Mutation Type                :  " + mutationType + "\n");
		output.write("Mutation Rate                :  " + mutationRate + "\n");

		output.write("Random Number Seed           :  " + seed + "\n");
		output.write("Number of Genes/Points       :  " + numGenes + "\n");
		output.write("Size of Genes                :  " + geneSize + "\n");

		output.write("Number of Games              :  " + numGames + "\n");
		output.write("Memory Length                :  " + memoryLength + "\n");

		output.write("\n\n");

	}

	// Source: Homework 2
	public Parameters(String expID, String problemType, String dataInputFileName,
					  int numRuns, int generations, int popSize,
					  int selectType, int scaleType,
					  int xoverType, double xoverRate, int mutationType, double mutationRate,
					  long seed, int geneSize, int numGames, int memoryLength)
	{
		Parameters.expID = expID;
		Parameters.problemType = problemType;

		Parameters.dataInputFileName = dataInputFileName;

		Parameters.numRuns = numRuns;
		Parameters.generations = generations;
		Parameters.popSize = popSize;

		Parameters.selectType = selectType;
		Parameters.scaleType = selectType;

		Parameters.xoverType = xoverType;
		Parameters.xoverRate = xoverRate;
		Parameters.mutationType = mutationType;
		Parameters.mutationRate = mutationRate;

		Parameters.seed = seed;
		Parameters.geneSize = geneSize;

		Parameters.numGames = numGames;

		Parameters.memoryLength = memoryLength;

		Parameters.numGenes = (int) Math.pow(2, 2*memoryLength);

		if (scaleType==0 || scaleType==2) minORmax = "max";
		else minORmax = "min";
	}
}   // End of Parameters.java **************************************************