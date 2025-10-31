import javax.net.ssl.HostnameVerifier;

public class Constants {
    
    public final int            SCREEN_WIDTH    = 80; // Largeur en caractères 
    public static final String  HORIZONTAL_LINE = "-";
    public static final String  VERTICAL_LINE   = "|";
    public static final String  THERE_ARE       = "Il y a dans cette zone : ";
    public static final String  WELCOME_SHOP    = "Bienvenue dans la boutique !";
    public static final String  YOU_GOT         = "Vous avez obtenu : ";

    public String drawLine(int width) {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i<width; i++) s.append(HORIZONTAL_LINE);
        return s.toString();
    }

    public void drawBox(String str) {

        System.out.println(drawLine(str.length()+4));
        System.out.println(VERTICAL_LINE+" "+str+" "+VERTICAL_LINE);
        System.out.println(drawLine(str.length()+4));
    }

    public String drawUI(String title, int time, String place) {

        StringBuilder s = new StringBuilder();
        // -------TITLE------
        s.append(drawLine((SCREEN_WIDTH - title.length())/2));
        s.append(title);
        s.append(drawLine((SCREEN_WIDTH - title.length())/2)+"\n");

        // | TIME |
        String m = (time%60 < 10 ? "0"+String.valueOf(time%60) : String.valueOf(time%60));
        s.append(VERTICAL_LINE+" "+time/60+"h"+m+" "+VERTICAL_LINE);

        // Space
        for (int i = s.length()-SCREEN_WIDTH; i < SCREEN_WIDTH-14+place.length(); i++) s.append(' ');
        
        // | Place |
        s.append(VERTICAL_LINE+" "+place+" "+VERTICAL_LINE);        

        

        return s.toString();
    }
}
