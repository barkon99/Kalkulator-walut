import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main implements ActionListener{

    JFrame ramka;

    JLabel etykietaPln;
    JLabel etykietaEuro;
    JLabel etykietaDolar;
    JLabel etykietaFrank;
    JLabel etykietaFunt;
    JLabel etykietaJen;
    JLabel etykietaKuna;
    JLabel etykietaDolarKanadyjski;
    JLabel etykietaKoronaCzeska;
    JLabel etykietaNic;

    JTextField poletextPln;
    JTextField poletextEuro;
    JTextField poletextDolar;
    JTextField poletextFrank;
    JTextField poletextFunt;
    JTextField poletextJen;
    JTextField poletextKuna;
    JTextField poletextDolarKanadyjski;
    JTextField poletextKoronaCzeska;

    JButton przycisk;

    JFrame oknobledu;
    JLabel etykietaBledu;
    JButton przycisk2;


    public static void main(String args[])
    {
        Main aplikacja = new Main();
        aplikacja.zbudujInterfejs();
    }

    public void zbudujInterfejs()
    {
        ramka = new JFrame("Kalkulator Walut");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(600,600);
        ramka.setLayout(new GridLayout(10,0));

        etykietaPln = new JLabel("Podaj wartosc w PLN");
        etykietaPln.setForeground(Color.red);
        etykietaPln.setFont(new Font("Serif", Font.BOLD, 24));

        poletextPln = new JTextField();
        poletextPln.setBackground(Color.white);

        etykietaNic = new JLabel(" ");

        etykietaEuro = new JLabel("Euro:");
        etykietaEuro.setForeground(Color.ORANGE);
        etykietaEuro.setFont(new Font("Serif", Font.BOLD, 28));
        poletextEuro = new JTextField();
        poletextEuro.setBackground(Color.lightGray);

        etykietaDolar = new JLabel("Dolar:");
        etykietaDolar.setForeground(Color.ORANGE);
        etykietaDolar.setFont(new Font("Serif", Font.BOLD, 28));
        poletextDolar = new JTextField();
        poletextDolar.setBackground(Color.lightGray);

        etykietaFrank = new JLabel("Frank:");
        etykietaFrank.setForeground(Color.ORANGE);
        etykietaFrank.setFont(new Font("Serif", Font.BOLD, 28));
        poletextFrank = new JTextField();
        poletextFrank.setBackground(Color.lightGray);

        etykietaFunt = new JLabel("Funt:");
        etykietaFunt.setForeground(Color.ORANGE);
        etykietaFunt.setFont(new Font("Serif", Font.BOLD, 28));
        poletextFunt = new JTextField();
        poletextFunt.setBackground(Color.lightGray);

        etykietaKuna = new JLabel("Kuna:");
        etykietaKuna.setForeground(Color.ORANGE);
        etykietaKuna.setFont(new Font("Serif", Font.BOLD, 28));
        poletextKuna = new JTextField();
        poletextKuna.setBackground(Color.lightGray);

        etykietaJen = new JLabel("Jen:");
        etykietaJen.setFont(new Font("Serif", Font.BOLD, 28));
        etykietaJen.setForeground(Color.ORANGE);
        poletextJen = new JTextField();
        poletextJen.setBackground(Color.lightGray);

        etykietaDolarKanadyjski = new JLabel("Dolar Kanadyjski:");
        etykietaDolarKanadyjski.setForeground(Color.ORANGE);
        etykietaDolarKanadyjski.setFont(new Font("Serif", Font.BOLD, 28));
        poletextDolarKanadyjski = new JTextField();
        poletextDolarKanadyjski.setBackground(Color.lightGray);

        etykietaKoronaCzeska = new JLabel("Korona czeska:");
        etykietaKoronaCzeska.setForeground(Color.ORANGE);
        etykietaKoronaCzeska.setFont(new Font("Serif", Font.BOLD, 28));
        poletextKoronaCzeska = new JTextField();
        poletextKoronaCzeska.setBackground(Color.lightGray);

        przycisk = new JButton("Przewalutuj");
        przycisk.setFont(new Font("Monospaced", Font.ITALIC, 22));

        ramka.add(etykietaPln);
        ramka.add(poletextPln);
        ramka.add(etykietaNic);
        ramka.add(przycisk);

        przycisk.addActionListener(this);

        ramka.add(etykietaEuro);
        ramka.add(poletextEuro);

        ramka.add(etykietaDolar);
        ramka.add(poletextDolar);

        ramka.add(etykietaFrank);
        ramka.add(poletextFrank);

        ramka.add(etykietaFunt);
        ramka.add(poletextFunt);

        ramka.add(etykietaKuna);
        ramka.add(poletextKuna);

        ramka.add(etykietaJen);
        ramka.add(poletextJen);

        ramka.add(etykietaDolarKanadyjski);
        ramka.add(poletextDolarKanadyjski);

        ramka.add(etykietaKoronaCzeska);
        ramka.add(poletextKoronaCzeska);
        ramka.setVisible(true);
    }

    void blad(String tytul, String info)
    {
        oknobledu=new JFrame(tytul);
        oknobledu.setSize(300,150);
        oknobledu.setLocation(150,25);
        oknobledu.setLayout(new GridLayout(2,0));
        etykietaBledu=new JLabel("          "+info);

        przycisk2=new JButton("OK");
        przycisk2.setBackground(Color.red);
        przycisk2.setFont(new Font("OK",Font.BOLD,18));
        oknobledu.add(etykietaBledu);
        oknobledu.add(przycisk2);
        przycisk2.addActionListener(this);
        oknobledu.setVisible(true);
    }

    public void actionPerformed(ActionEvent dzialanie)
    {
        try
        {
            JSONObject jsonObject = connectToApi();

            double kursEuro = (double) jsonObject.get("EUR");
            double kursDolar = (double) jsonObject.get("USD");
            double kursFunt = (double) jsonObject.get("GBP");
            double kursFrank = (double) jsonObject.get("CHF");
            double kursDolarKanadyjski = (double) jsonObject.get("CAD");
            double kursKuna = (double) jsonObject.get("HRK");
            double kursKorona = (double) jsonObject.get("CZK");
            double kursJen = (double) jsonObject.get("JPY");

            if (dzialanie.getSource() == przycisk){
                try{

                    if(Float.parseFloat(poletextPln.getText())>0)
                    {
                        float wynikEuro = (float) (Float.parseFloat(poletextPln.getText()) * kursEuro);
                        poletextEuro.setText(String.valueOf(Math.round(wynikEuro * 100.0)/100.0));

                        float wynikDolar = (float) (Float.parseFloat(poletextPln.getText()) * kursDolar);
                        poletextDolar.setText(String.valueOf(Math.round(wynikDolar * 100.0)/100.0));

                        float wynikFrank = (float) (Float.parseFloat(poletextPln.getText()) * kursFrank);
                        poletextFrank.setText(String.valueOf(Math.round(wynikFrank * 100.0)/100.0));

                        float wynikFunt = (float) (Float.parseFloat(poletextPln.getText()) * kursFunt);
                        poletextFunt.setText(String.valueOf(Math.round(wynikFunt * 100.0)/100.0));

                        float wynikKuna = (float) (Float.parseFloat(poletextPln.getText()) * kursKuna);
                        poletextKuna.setText(String.valueOf(Math.round(wynikKuna * 100.0)/100.0));

                        float wynikForint = (float) (Float.parseFloat(poletextPln.getText()) * kursDolarKanadyjski);
                        poletextDolarKanadyjski.setText(String.valueOf(Math.round(wynikForint * 100.0)/100.0));

                        float wynikJen = (float) (Float.parseFloat(poletextPln.getText()) * kursJen);
                        poletextJen.setText(String.valueOf(Math.round(wynikJen * 100.0)/100.0));

                        float wynikKorona = (float) (Float.parseFloat(poletextPln.getText()) * kursKorona);
                        poletextKoronaCzeska.setText(String.valueOf(Math.round(wynikKorona * 100.0)/100.0));
                    }
                    else blad("Ujemna Wartość","Wartość musi być dodatnia");
                }
                catch (Exception e)
                {
                    blad("Błędne dane","Podaj wartość!");
                }
            }

            if (dzialanie.getSource() == przycisk2) oknobledu.setVisible(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public JSONObject connectToApi() throws IOException, ParseException
    {
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=PLN");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responsecode = connection.getResponseCode();

        String inline = "";

        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        else
        {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext())
            {
                inline += scanner.nextLine();
            }
            scanner.close();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(inline);
        JSONObject rates = (JSONObject) jsonObject.get("rates");

        return rates;
    }
}
