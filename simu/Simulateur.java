import java.io.*;
import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;

public class Simulateur {
    
    public Vector<Travail> work;
    public Vector<Personnage> perso;
    public Vector<Lieu> lieu;
    public final Constants cons;
    public int heure = 720; // En minutes 720 = 12h00
    public Personnage player;

    public Simulateur() {

        cons = new Constants();
        perso = new Vector<Personnage>();
        work = new Vector<Travail>();
        lieu = new Vector<Lieu>();

        // Chargement save
        File save = new File("savefile.txt");
        if (save.exists() && save.canRead() && save.canWrite()) {

            System.out.println("Chargement en cours...");

            int currline = 0; // Envoyer la ligne en cas d'erreur
            lieu.add(new Lieu("Park")); // Lieu par défaut foure-tout

            try (BufferedReader in = new BufferedReader(new FileReader("savefile.txt"))) {

                String line, array[];

                while((line = in.readLine()) != null) { //Handling de chaque ligne de la save

                    currline++;
                    array = line.split("[|]"); //Separation des val

                    switch(array[0]) {
                        case "T": //Si l'entrée est le T emps
                            heure = Integer.valueOf(array[1]) % 1439;
                            break;
                        case "P": //Si l'entrée est un P ersonnage
                            Personnage p = new Personnage(array[1], array[2].equals("True"), lieu.lastElement());
                            perso.add(p);
                            break;
                        case "W": //Si l'entrée est un W ork
                            Composant c = new Composant(array[4], Integer.valueOf(array[3]));
                            Travail w = new Travail(array[1], Integer.valueOf(array[2]), c);
                            work.add(w);
                            break;
                        case "L": //L ieu
                            Lieu l = new Lieu(array[1]);
                            lieu.add(l);
                            break;
                        case "OUTIL":
                            Outil o = new Outil(array[1], Arrays.copyOfRange(array, 2, array.length));
                            lieu.lastElement().addObj(o); //Les objs trouvés sont envoyés au dernier lieu observé
                            break;
                        default:
                            System.out.println("[Warning] Ligne non reconnu: Ligne "+currline);
                    }
                }
            }
            catch (Exception e) { System.out.println("[ERROR] Loading : something wrong happennded xd. On line " + currline); }

            System.out.println("Chargement terminé!");
        }
        
        else System.out.println("Savefile non existante. Créez un fichier \"savefile.txt\" sans restriction de lecture et d'écriture");
        //FIN

        boolean game = true;
        int state = 0;
        Scanner sc = new Scanner(System.in);
        String ERROR_IN = "N'entrez qu'un nombre";

        while (game) {

            System.out.println("\033[H\033[2J"); // Clear terminal
            switch(state) {
                case 0: // Choose
                    System.out.println("Bienvenue\nEntrez l'id de votre personnage");
                    getPerso();
                    int p;
                    try { p = Integer.valueOf(sc.next());
                        if (p >= 0 && p < perso.size() && !perso.get(p).getNPC()) { 
                            player = perso.get(p);
                            state = 1;
                        }
                    }
                    catch(Exception e) {System.out.println(ERROR_IN);}
                    break;
                case 1: // Status
                    Lieu l = player.getLocation();
                    System.out.println("Vous êtes dans : " + l.getName());
                    System.out.println("1) Aller\t2) Prendre\t3) Utiliser");
                    try { p = Integer.valueOf(sc.next());
                        if (p == 1) { // Aller
                            System.out.println("Aller où ?");
                            getLieu();
                            p = sc.nextInt();
                            if (p >= 0 && p < lieu.size()) player.goTo(lieu.get(p));
                        }
                    }
                    catch(Exception e) {System.out.println(ERROR_IN);}
                    break;
            }
        }
        sc.close();
    }

    public void getPerso(int n) {
    
        Personnage p = perso.get(n);
        cons.drawBox(p.getName());
        System.out.println("ID: " + n + " , isNPC: "+ p.getNPC() + ", Location: "+p.getLocation().getName());
    }
    public void getPerso(Lieu l) {
    
        int i = 0;
        cons.drawBox(l.getName());
        Vector<Personnage> p = l.getPersos();
        while (i < p.size()) {
            Personnage pe = p.get(i);
            System.out.println("ID: " + perso.indexOf(pe) + ", Name: " + pe.getName() + " , isNPC: "+ pe.getNPC());
            i++;
        }
    }
    public void getPerso() {
    
        int i = 0;
        while (i < perso.size()) {
            getPerso(i);
            i++;
        }
    }

    public void getLieu() {
    
        int i = 0;
        while (i < lieu.size()) {
            Lieu l = lieu.get(i);
            System.out.println("ID: " + i + ", Name: "+l.getName());
            i++;
        }
    }

    public void getOutils(int n) {

        int i = 0;int j = 0;
        Outil o;
        cons.drawBox(lieu.get(n).getName());
        Vector<Outil> ol = lieu.get(n).getOutils();
        while (i < ol.size()) {
            o = ol.get(i);
            System.out.println("ID: " + i + " , NOM: " + o.getName());
            System.out.print("Strong against :");
            j = 0;
            String[] strong = o.getStrongVS();
            while (j < strong.length) { 
                System.out.print(" |" + strong[j]+"|");
                j++;
            }
            i++;
            System.out.println();
        }
    }

    public void getTime() {
        String m = (heure%60 < 10 ? "0"+String.valueOf(heure%60) : String.valueOf(heure%60));
        cons.drawBox("" + heure/60 + "h" + m);
    }

    public void useOutil() {

        System.out.println("Quel outil");
    }

}
