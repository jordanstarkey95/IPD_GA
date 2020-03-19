
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author amlan
 */
public class StrategySample extends Strategy
{

    int movesToRemember;
    String strategicMoves = "0100010110101000011001001111101000010011111001010101110100000000";
    int[] memory;
    int currentIndexP1;
    int currentIndexP2;
    
    public StrategySample()
    {
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        for (int i = 0; i < memory.length; i++)
        {
            memory[i] = -1;
        }
    }

    public StrategySample(String strategicMoves, int movesToRemember)
    {
        this.movesToRemember = movesToRemember;
        this.memory = new int[movesToRemember*2];
        this.strategicMoves = strategicMoves;
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        for (int i = 0; i < memory.length; i++)
        {
            memory[i] = -1;
        }
        currentIndexP1 = 0;
        currentIndexP2 = movesToRemember;
    }

    @Override
    public void saveOpponentMove(int move)
    {
        // Since 'C' is denoted by 1 and 'D' by 0,
        // but in our memory we consider CCCCCC as index 0 and
        // DDDDDD to the last index, here we flip the move.
        move = 1 - move;

        // If memory is still not full,
        // assign the move in the currentIndex.
        if (currentIndexP2 < memory.length)
        {
            memory[currentIndexP2] = move;
            currentIndexP2 += 1;
            return;
        }

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
        // Since 'C' is denoted by 1 and 'D' by 0,
        // but in our memory we consider CCCCCC as index 0 and
        // DDDDDD to the last index, here we flip the move.
        move = 1 - move;

        // If memory is still not full,
        // assign the move in the currentIndex.
        if (currentIndexP1 < movesToRemember)
        {
            memory[currentIndexP1] = move;
            currentIndexP1 += 1;
            return;
        }

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
            // If memory is not loaded,
            // follow "Always Cooperate" or 
            // "Tit for tat" strategy.
            if (memory[i] == -1)
            {
                // Always cooperate
                return 0;
                /*
                // Cooperate in the first move
                if(i == 0)
                    return 0;
                // Follow opponent's last move
                return memory[movesToRemember + (i-1)];
                */
            }
            // Get the integer value of the move sequence in the memory
            strategyIndex += memory[i] * (int) (Math.pow(2, memory.length - (i + 1)));
        }
        int move = 1-Character.getNumericValue(strategicMoves.charAt(strategyIndex));
        //System.out.println(Arrays.toString(memory) + ", " + strategyIndex + ", " + move);
        return move;
    }

}
