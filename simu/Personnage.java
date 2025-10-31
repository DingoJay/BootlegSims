import java.util.Vector;

public class Personnage {
    
    String name;
    boolean isNPC;
    Lieu location;
    Vector<Outil> outils;

    public Personnage(String n, boolean npc, Lieu loc) {
        
        name = n; isNPC = npc; location = loc;
        outils = new Vector<Outil>();
    }

    public String getName() { return name; }
    public boolean getNPC() { return isNPC; }
    public Lieu getLocation() { return location; }

    public void goTo(Lieu newl) {
        try {
            newl.addP(this);
            location.removeP(this);
            location = newl;
        }
        catch (Exception e) { System.out.println("[ERROR] Personnage name: " + name + ", échec du déplacement"); }
    }
}
