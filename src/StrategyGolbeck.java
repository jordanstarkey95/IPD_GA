
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
public class StrategyGolbeck extends Strategy
{

    int movesToRemember;
    String strategicMoves;
    int[] memory;
    int currentIndexP1;
    int currentIndexP2;
    
    public StrategyGolbeck()
    {
        this("0000");
    }
    
    public StrategyGolbeck(String strategicMoves)
    {
        this.strategicMoves = strategicMoves;
        this.setMemory();
    }
    
    public void setMemory()
    {
        this.movesToRemember = (int) (Math.log(strategicMoves.length())/Math.log(2)/2);
        this.memory = new int[this.movesToRemember*2];
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        Arrays.fill(this.memory, -1);
        this.currentIndexP1 = 0;
        this.currentIndexP2 = this.movesToRemember;
    }

    @Override
    public void saveOpponentMove(int move)
    {
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
                //return 1;
                /*
                // Cooperate in the first move
                if(i == 0)
                    return 0;
                // Follow opponent's last move
                return memory[movesToRemember + (i-1)];
                */
                Random rand = new Random();
                if(rand.nextDouble() <= 0.5)
                    return 1;
                else
                    return 0;
                
            }
            // Get the integer value of the move sequence in the memory
            strategyIndex += memory[i] * (int) (Math.pow(2, memory.length - (i + 1)));
        }
        int move = Character.getNumericValue(strategicMoves.charAt(strategyIndex));
        //System.out.println(Arrays.toString(memory) + ", " + strategyIndex + ", " + move);
        return move;
    }

}
