package paag2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean irten = false;
 
        while (!irten) {
            System.out.println();
            System.out.println("=== Fitxategi-sistemaren menua ===");
            System.out.println("1. Path bat existitzen den egiaztatu )");
            System.out.println("2. Karpeta baten edukia bistaratu ");
            System.out.println("3. 'karpeta_berriak' egitura sortu )");
            System.out.println("4. Animalia-fitxategi bat sortu ");
            System.out.println("5. Karpeta baten edukia fitxategi batean gorde");
            System.out.println("6. Fitxategien izenak maiuskulaz hasi");
            System.out.println("0. Irten");
            System.out.print("Aukeratu: ");
 
            String aukera = sc.nextLine().trim();
 
            switch (aukera) {
                case "1":
                    PathChecker.check();
                    break;
                case "2":
                    FolderLister.list();
                    break;
                case "3":
                    FolderStructureCreator.create();
                    break;
                case "4":
                    AnimaliaCreator.create();
                    break;
                case "5":
                    FolderListerFile.listToFile();
                    break;
                case "6":
                    CapitalizeFiles.capitalize();
                    break;
                case "0":
                    irten = true;
                    System.out.println("Agur!");
                    break;
                default:
                    System.out.println("Aukera baliogabea, saiatu berriz.");
            }
        }
 
        sc.close();
    }
}