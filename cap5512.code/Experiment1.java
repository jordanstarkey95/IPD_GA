import java.io.File;

public class Experiment1
{
    public static void main(String[] args) throws java.io.IOException
    {

        int[] memoryLengths = new int[]{1, 2, 3, 4, 5, 6};
        for (int memoryLength : memoryLengths)
        {
            new Parameters("memory_length-" + memoryLength, "IPD", "NA",
                    10, 100, 100,
                    2, 0,
                    1, 0.8, 1, 0.005,
                    75982, 1, 64, memoryLength);

            Search.main(new String[]{});
        }
    }
}