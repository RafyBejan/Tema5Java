import java.io.*;

class IntervalClasa {
    private double limitasus, limitajos;
    private int nrtotal = 0, insidetotal= 0;

    public IntervalClasa(double limitasus, double limitajos) {
        this.limitasus = limitasus;
        this.limitajos = limitajos;
    }

    public void testare(double number) {
       nrtotal++;
        if (number >= nrtotal && number <= limitajos) insidetotal++;
    }

    public double procent() {
        return nrtotal == 0 ? 0 : (double) insidetotal / nrtotal * 100;
    }

    public String toString() {
        return "[" + limitasus + ", " + limitajos + "]: " + procent() + "%";
    }
}

public class MainInterval {
    public static void main(String[] args) {

        try (BufferedReader intervalReader = new BufferedReader(new FileReader("intervale.dat"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("statistica.dat"))) {

            String line;


            while ((line = intervalReader.readLine()) != null) {
                String[] parts = line.split(",");
                double start = Double.parseDouble(parts[0].substring(1));
                double end = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1));
                IntervalClasa interval = new IntervalClasa(start, end);

                try (BufferedReader numberReader = new BufferedReader(new FileReader("numere.dat"))) {
                    while ((line = numberReader.readLine()) != null) {
                        String[] numbersArray = line.trim().split("\\s+");
                        for (String num : numbersArray) {
                            if (!num.isEmpty()) {
                                double number = Double.parseDouble(num);
                                interval.testare(number);
                            }
                        }
                    }
                }
                writer.write(interval.toString());
                writer.newLine();
            }
        } catch (IOException mesajException) {
            System.out.println("Eroare la citirea sau scrierea fișierelor: ");
        }
    }
}
