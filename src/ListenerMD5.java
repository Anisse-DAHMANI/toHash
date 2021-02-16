import java.awt.event.*;


public class ListenerMD5 implements ActionListener{

  private ToHash vue;

  public ListenerMD5(ToHash v){
    super();
    vue = v;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    vue.setHash();
  }
}
