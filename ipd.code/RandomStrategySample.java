
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author amlan
 */
public class RandomStrategySample extends Strategy
{

    int movesToRemember;
    String strategicMoves;
    int[] memory;
    int currentIndexP1;
    int currentIndexP2;

    public RandomStrategySample()
    {
        this.strategicMoves = "0100010110101000011001001111101000010011111001010101110100000000";
        this.setMemory();
        name = "Random Strategy Sample";
    }

    public RandomStrategySample(String strategicMoves)
    {
        this.strategicMoves = strategicMoves;
        this.setMemory();
        name = "Random Strategy Sample";
    }

    public void setMemory()
    {
        this.movesToRemember = (int) (Math.log(strategicMoves.length())/Math.log(2)/2);
        this.memory = new int[this.movesToRemember*2];

        Random ran = new Random();
        for (int i = 0; i < memory.length; i++)
        {
            int next = ran.nextInt(2);
            memory[i] = next;
        }
        this.currentIndexP1 = 0;
        this.currentIndexP2 = this.movesToRemember;
    }

    @Override
    public void saveOpponentMove(int move)
    {
        // If memory is full, forget the oldest move.
        // move everything to the left by 1 index.
        for (int i = movesToRemember; i < memory.length - 1; i++)
        {
            memory[i] = memory[i + 1];
        }

        // Assign the move in the last index
        memory[memory.length - 1] = move;
    }

    @Override
    public void saveMyMove(int move)
    {
        // If memory is full, forget the oldest move.
        // move everything to the left by 1 index.
        for (int i = 0; i < movesToRemember - 1; i++)
        {
            memory[i] = memory[i + 1];
        }

        // Assign the move in the last index
        memory[memory.length - 1] = move;
    }

    @Override
    public int nextMove()
    {
        int strategyIndex = 0;
        for (int i = 0; i < memory.length; i++)
        {
            // Get the integer value of the move sequence in the memory
            strategyIndex += memory[i] * (int) (Math.pow(2, memory.length - (i + 1)));
        }
        int move = Character.getNumericValue(strategicMoves.charAt(strategyIndex));
        //System.out.println(Arrays.toString(memory) + ", " + strategyIndex + ", " + move);
        return move;
    }

}