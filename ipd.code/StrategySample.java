
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
    final int memoryLength = 6;
    String strategicMoves = "CDDDCCCDCDDDDDCCCCDCCDDCCCDCDCCDDCDDCCDDDCCCCDDCCDCCCCDDCCDDCCDC";
    int[] memory = new int[memoryLength];
    int currentIndex = 0;
    
    public StrategySample()
    {
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        for(int i=0; i<memory.length; i++)
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
        move = 1-move;
        
        // If memory is still not full,
        // assign the move in the currentIndex.
        if(currentIndex < memoryLength)
        {
            memory[currentIndex] = move;
            currentIndex += 1;
            return;
        }
        
        // If memory is full, forget the oldest move.
        // move everything to the left by 1 index.
        for(int i=0; i<memory.length-1; i++)
        {
            memory[i] = memory[i+1];
        }
        
        // Assign the move in the last index
        memory[memory.length-1] = move;
    }
    
    public int getStrategyIndex()
    {
        int index = 0;
        for(int i=0; i<memory.length; i++)
        {
            // If memory is not loaded,
            // make the same move for 'CCCCCC'
            if(memory[i] == -1)
                return 0;
            
            // Get the integer value of the move sequence in the memory
            index += memory[i] * (int)(Math.pow(2, memoryLength-(i+1)));
        }
        return index;
    }
    
    @Override
    public int nextMove()
    {
        int strategyIndex = getStrategyIndex();
        char strategyChar = strategicMoves.charAt(strategyIndex);
        int move = ((strategyChar == 'C')? 1:0);
        //System.out.println(Arrays.toString(memory) + ", " + strategyIndex + ", " + move);
        return move;
    }
    
    
}
