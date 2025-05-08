import java.awt.*;
import java.io.*;
import java.util.*;

public class GroceryReport
{
    public static void main(String[] args)
    {
        final int MAX_ITEMS = 50;
        String[] names = new String[MAX_ITEMS];
        double[] prices = new double[MAX_ITEMS];
        int itemCount = 0;

        System.out.println("Working directory: " + new File(".").getAbsolutePath());



        try
        {
            loadGroceryData("groceries.txt", names, prices);

            for (int i = 0; i < MAX_ITEMS; i++)
            {
                if (names[i] == null)
                {
                    itemCount = i;
                    break;
                }
            }

            double average = calculateAveragePrice(prices, itemCount);

            writeReport(names, prices, itemCount, average);

            System.out.println("Report written to grocery_report.txt");

            try {
                File reportFile = new File("grocery_report.txt");
                if (reportFile.exists()) {
                    Desktop.getDesktop().open(reportFile);
                }
            } catch (IOException e) {
                System.out.println("Could not open the report file automatically.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Input file not found.");
        }

    }

    public static void loadGroceryData(String filename, String[] names, double[] prices) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(filename));
        int index = 0;

        while (scan.hasNextLine() && index < names.length)
        {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2)
            {
                names[index] = parts[0];
                prices[index] = Double.parseDouble(parts[1]);
                index++;
            }
        }
    }

    public static double calculateAveragePrice(double[] prices, int count)
    {
        double sum = 0;
        for (int i = 0; i < count; i++)
        {
            sum += prices[i];
        }
        return sum/count;
    }

    public static void writeReport(String[] names, double[] prices, int count, double average) throws FileNotFoundException
    {
        PrintWriter output = new PrintWriter("grocery_report.txt");

        output.println("Grocery Items Report");

        for (int i = 0; i < count; i++)
        {
            output.printf("%-10s : $%.2f%n", names[i], prices[i]);
        }

        output.println();
        output.printf("Average Price: $%.2f%n", average);

        output.close();
    }



}
