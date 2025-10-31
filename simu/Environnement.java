public class Environnement extends Objet {
    
    Composant[] composant; //Liste d'éléments composants un environnement, drop si consummé

    public Environnement(String n, Composant[] c) {

        name = n;
        composant = c;
    }

    public void use(Environnement o) { System.out.println("[Alert] From Environment named "+name+" : cannot use Environnement as Outil"); }
    public String getName() { return name; }
    public Composant[] onUse() { return composant; }; // Composants récup après utilisation 
}
