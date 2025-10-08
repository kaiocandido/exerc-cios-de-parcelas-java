import entities.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> list = new ArrayList<>();

        System.out.println("Digite o caminho do arquivo: ");
        String srcPath = sc.nextLine();

        File sourceFile = new File(srcPath);
        String sourceFolderStr = sourceFile.getParent();
        boolean sucess = new File(sourceFolderStr + "\\out").mkdir();
        System.out.println("Pasta criada: " + sucess);
        String targtFileStr = sourceFolderStr + "\\out\\summary.csv";

        try(BufferedReader br =  new BufferedReader(new FileReader(srcPath))){
            String itemCsv = br.readLine();
            while (itemCsv != null){
                String[] filds = itemCsv.split(",");
                String name = filds[0];
                Double price = Double.parseDouble(filds[1]);
                Integer qntd = Integer.parseInt(filds[2]);

                list.add(new Product(name,price,qntd));

                itemCsv = br.readLine();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targtFileStr))) {
                for (Product items : list) {
                    bw.write(items.getName() + "," + String.format("%.2f", items.total()));
                    bw.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}