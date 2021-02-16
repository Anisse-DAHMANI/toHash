import javax.swing.*;
import java.awt.*;
import java.security.*;
import java.math.BigInteger;

public class ToHash extends JFrame{

  private JLabel hash_converted;
  private JTextField input_string;

  public ToHash(){
    super("toHash");

    JPanel principal_panel = new JPanel();
    principal_panel.setLayout(new BorderLayout());


    JPanel nord_panel = new JPanel();
    nord_panel.setLayout(new GridLayout(2,1));
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 15);
    JLabel welcome = new JLabel("Bienvenue !", SwingConstants.CENTER);
    welcome.setFont(font1);
    String text = "L'application toHash permet de convertir une chaîne des caratères en hash (MD5). Pour l'effectuer, veuillez écrire dessous-ci.";
    JLabel explication = new JLabel("<html><div>"+ text +"<div><html>", SwingConstants.CENTER);
    explication.setFont(font2);
    nord_panel.add(welcome);
    nord_panel.add(explication);
    nord_panel.setPreferredSize(new Dimension(0, 150));

    JPanel centre_panel = new JPanel(new GridLayout(4,1));
    JPanel input_panel = new JPanel(new GridLayout(1,2));
    JLabel text_ask = new JLabel("Votre chaîne des caractères à convertir en hash (MD5 :");
    text_ask.setFont(font2);
    input_string = new JTextField();
    ListenerMD5 listener_md = new ListenerMD5(this);
    input_string.addActionListener(listener_md);
    input_panel.add(text_ask);
    input_panel.add(input_string);
    centre_panel.add(input_panel);
    JPanel container_bouton = new JPanel(new BorderLayout());
    JPanel vide = new JPanel();
    vide.setPreferredSize(new Dimension(400,0));
    JPanel vide1 = new JPanel();
    vide1.setPreferredSize(new Dimension(400,0));
    JPanel vide2 = new JPanel();
    vide2.setPreferredSize(new Dimension(0,100));
    JButton bouton_convert = new JButton("Convertir en hash");
    bouton_convert.addActionListener(listener_md);
    container_bouton.add(vide, BorderLayout.WEST);
    container_bouton.add(bouton_convert, BorderLayout.CENTER);
    container_bouton.add(vide1, BorderLayout.EAST);
    centre_panel.add(vide2);
    centre_panel.add(container_bouton);

    hash_converted = new JLabel("--  --", SwingConstants.CENTER);
    centre_panel.add(hash_converted);

    JPanel droite_panel = new JPanel();
    droite_panel.setPreferredSize(new Dimension(100,0));
    JPanel gauche_panel = new JPanel();
    gauche_panel.setPreferredSize(new Dimension(100,0));

    JPanel bas_panel = new JPanel();
    JLabel credit = new JLabel("made by the student Anisse.D, 2th May 2020");
    credit.setFont(font2);
    bas_panel.add(credit);


    principal_panel.add(nord_panel, BorderLayout.NORTH);
    principal_panel.add(centre_panel, BorderLayout.CENTER);
    principal_panel.add(droite_panel, BorderLayout.EAST);
    principal_panel.add(gauche_panel, BorderLayout.WEST);
    principal_panel.add(bas_panel, BorderLayout.SOUTH);
    this.setContentPane(principal_panel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // On fixe une taille de départ pour la fenetre
    this.setPreferredSize(new Dimension(1300, 300));
        // on positionne la fenêtre sur l'écran
    this.setLocation(100, 100);
    this.pack();
    this.setVisible(true);

  }

  public String getChaine(){
    return input_string.getText();
  }

  public void setHash(){
    hash_converted.setText("-- " + convertStringToHash() + " --");
  }

  private String convertStringToHash(){
    try{
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hash = md.digest(input_string.getText().getBytes());
      BigInteger no = new BigInteger(1, hash);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    }
    catch(NoSuchAlgorithmException e){
      throw new RuntimeException(e);
    }
  }


  public static void main(String[] args){
    ToHash app = new ToHash();
  }
}
