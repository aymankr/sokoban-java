import java.io.PrintStream;
import java.util.Scanner;

public class Administrator {
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        out.println("* Bonjour.");
        menu();
    }

    private static void menu() {
        boolean loop = true;
        while (loop) {
            out.println("* Menu");
            out.println("1. Créer une nouvelle base de données");
            out.println("2. Liste des plateaux");
            out.println("3. Montrer un plateau");
            out.println("4. Ajouter un plateau depuis un fichier");
            out.println("5. Supprimer un plateau de la base [DANGEREUX]");
            out.println("6. Quitter.");
            out.print("? ");
            String command = readLine();
            switch (command) {
                case "q":
                    out.println("-> Bye.");
                    loop = false;
                    break;
                default:
                    out.println("-> commande inconnue '" + command + "'");
                    break;
            }
        }
    }

    /**
     * Lire l'entrée
     *
     * @return retourner l'entrée
     */
    private static String readLine() {
        return new Scanner(System.in).nextLine().trim();
    }
}
