
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jordan
 */
public class EncodedStrategySample extends Strategy
{

    int movesToRemember;
    String strategicMoves;
    // assumption about the pre-game behavior
    int[] assumptions;
    int[] memory ;
    int currentIndexP1;
    int currentIndexP2;
    Random ran = new Random();

    public EncodedStrategySample()
    {
        this("0000");
    }

    public EncodedStrategySample(String strategicMoves)
    {
        name = "Encoded Strategy Sample";
        this.strategicMoves = strategicMoves;
        this.setMemory();

        // The first three moves in a game are undefined
        // To account for these six bits (C’s and D’s, initially assigned at random) are appended to the above 64 bit
        // string to specify a strategy’s premises, or assumption about the pre-game behavior
        // Together, each of the 70 bit strings thus represent a particular strategy, the first 64 for rules and the
        // next 6 for the premises.
        for(int i = 0; i < this.memory.length; i++) {
            this.assumptions[i] = ran.nextInt(2);
        }

        //System.out.println(Arrays.toString(this.assumptions));
    }

    public void setMemory()
    {
        this.movesToRemember = (int) (Math.log(strategicMoves.length())/Math.log(2)/2);
        this.memory = new int[this.movesToRemember*2];
        this.assumptions = new int[this.memory.length];
        // Initially nothing is remembered.
        // So, the memory is initialized by -1.
        Arrays.fill(this.memory, -1);
        this.currentIndexP1 = 0;
        this.currentIndexP2 = this.movesToRemember;
    }

    @Override
    public void saveOpponentMove(int move)
    {
        // Since 'C' is denoted by 1 and 'D' by 0,
        // but in our memory we consider CCCCCC as index 0 and
        // DDDDDD to the last index, here we flip the move.
        // move = 1 - move;

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
        // move = 1 - move;

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
        // Convert previous three moves to three letter string encoding
        String encoding = "";
        // Starting index for rules may change depending on if we have played 3 moves.
        int i = 0;
        // If memory is still not full,
        // then we choose from our assumptions
        if(Arrays.stream(this.memory).anyMatch(j -> j == -1)) {
            for (; (i + movesToRemember) < this.assumptions.length; i++)
            {
                if(this.assumptions[i] == 1 && this.assumptions[i + movesToRemember] == 1)
                    encoding += "R";
                else if(this.assumptions[i] == 0 && this.assumptions[i + movesToRemember] == 1)
                    encoding += "T";
                else if(this.assumptions[i] == 1 && this.assumptions[i + movesToRemember] == 0)
                    encoding += "S";
                else // player 1 chose 0 and player 2 chose 0
                    encoding += "P";
            }
        }

        for (; (i + this.movesToRemember) < this.memory.length; i++)
        {
            if(this.memory[i] == 1 && this.memory[i + this.movesToRemember] == 1)
                encoding += "R";
            else if(this.memory[i] == 0 &&this. memory[i + this.movesToRemember] == 1)
                encoding += "T";
            else if(this.memory[i] == 1 && this.memory[i + this.movesToRemember] == 0)
                encoding += "S";
            else // player 1 chose 0 and player 2 chose 0
                encoding += "P";
        }
        //System.out.println(encoding);

        // This three letter sequence is then used to generate a number between 0 and 63,
        // by interpreting it as a number in base 4.
        int base = 0;
        int index = 0;
        int multiple = 0;
        for(i = encoding.length() - 1; i >= 0; i--) {
            if(encoding.charAt(i) == 'R')
                multiple = 0;
            else if (encoding.charAt(i) == 'T')
                multiple = 1;
            else if (encoding.charAt(i) == 'S')
                multiple = 2;
            else
                multiple = 3;

            index += multiple * Math.pow(4, base);
            base++;
        }
        int move = Character.getNumericValue(this.strategicMoves.charAt(index));
        return move;
    }

}