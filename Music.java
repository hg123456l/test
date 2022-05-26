package hgl;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Music extends JFrame implements ActionListener {
private static final String AudioClip = null;
private JButton Oj;
private JButton Oj1;
AudioClip clip =null;
File musicFile;
URI uri;
URL url;
private void Music() throws InterruptedException{
musicFile = new File("Music/war1.wav");
uri = musicFile.toURI();
try {
url = uri.toURL();
} catch (Exception e) {
}
clip=Applet.newAudioClip(url);
//clip.play();
}
Music() {
this.setSize(800, 600);
this.setResizable(false);
JPanel p = new JPanel();
this.setContentPane(p);
this.setVisible(true);
Oj = new JButton("¿ªÊ¼");
Oj1 = new JButton("½áÊø");
this.setVisible(true);
Oj.addActionListener(this);
Oj1.addActionListener(this);
this.add(Oj);
this.add(Oj1);
}
public static void main(String[] args) throws InterruptedException {
	Music ff=new Music();
ff.Music();
}
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
if (e.getSource() == Oj) {
play();
} else if (e.getSource() == Oj1) {
stop();
//System.exit(0);
}
}
public void play() {
if (clip != null)
( (java.applet.AudioClip) clip).play();
}
public void stop() {
if (clip != null)
( (java.applet.AudioClip) clip).stop();
}
}
