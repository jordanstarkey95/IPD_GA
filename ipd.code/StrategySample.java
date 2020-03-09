
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

    final int movesToRemeber = 3;
    String strategicMoves = "0100010110101000011001001111101000010011111001010101110100000000";
    int[] memory = new int[movesToRemeber*2];
    int currentIndexP1 = 0;
    int currentIndexP2 = movesToRemeber;
    
    public StrategySample()
    {
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        for (int i = 0; i < memory.length; i++)
        {
            memory[i] = -1;
        }
    }

    public StrategySample(String strategicMoves)
    {
        this.strategicMoves = strategicMoves;
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        for (int i = 0; i < memory.length; i++)
        {
            memory[i] = -1;
        }
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
        for (int i = movesToRemeber; i < memory.length - 1; i++)
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
        if (currentIndexP1 < movesToRemeber)
        {
            memory[currentIndexP1] = move;
            currentIndexP1 += 1;
            return;
        }

        // If memory is full, forget the oldest move.
        // move everything to the left by 1 index.
        for (int i = 0; i < movesToRemeber - 1; i++)
        {
            memory[i] = memory[i + 1];
        }

        // Assign the move in the last index
        memory[memory.length - 1] = move;
    }

    public int getStrategyIndex()
    {
        int index = 0;
        for (int i = 0; i < memory.length; i++)
        {
            // If memory is not loaded,
            // make the same move for index 0 or 'CCCCCC'
            if (memory[i] == -1)
            {
                return 0;
            }

            // Get the integer value of the move sequence in the memory
            index += memory[i] * (int) (Math.pow(2, memory.length - (i + 1)));
        }
        return index;
    }

    @Override
    public int nextMove()
    {
        int strategyIndex = getStrategyIndex();
        int move = Character.getNumericValue(strategicMoves.charAt(strategyIndex));
        //System.out.println(Arrays.toString(memory) + ", " + strategyIndex + ", " + move);
        return move;
    }

}
