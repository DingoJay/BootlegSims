public class Main {

        public static void main(String[] args) {

        Simulateur ls = new Simulateur();
    
        ls.getOutils(ls.lieu.size()-1);
        ls.getPerso();
        ls.getTime();
        ls.perso.get(1).goTo(ls.lieu.get(1));
        ls.getPerso(ls.lieu.get(1));
        System.out.println(ls.cons.drawUI("BOOTLEG SIMS", 200, "forret"));
    }
}
