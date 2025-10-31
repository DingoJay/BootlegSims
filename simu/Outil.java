public class Outil extends Objet {
    
    String[] environnements = {"None"}; //Liste strong against env

    public Outil(String n) { //Debug construct

        name = n;
    }

    public Outil(String n, String[] e) {

        name = n;
        environnements = e;
    }

    public Composant[] use(Environnement o) { return o.onUse(); }
    public String[] getStrongVS() { return environnements; }
    public void onUse() {}; // Composants récup après utilisation 

}
