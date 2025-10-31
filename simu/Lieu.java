import java.util.Vector;

public class Lieu {
    
    String name;

    Vector<Outil> out; // Liste d'objets dans le lieu
    Vector<Personnage> persos; // Liste de moun dans le lieu

    public Lieu(String n) {

        name = n;
        out = new Vector<Outil>();
        persos = new Vector<Personnage>();
    }

    public String getName() { return name; }
    public Vector<Outil> getOutils() { return out; }
    public Vector<Personnage> getPersos() { return persos; }

    public void addObj(Outil o) { out.add(o); }
    public void removeObj(Outil o) { out.remove(o); }

    public void addP(Personnage o) { persos.add(o); }
    public void removeP(Personnage o) { persos.remove(o); }
}
