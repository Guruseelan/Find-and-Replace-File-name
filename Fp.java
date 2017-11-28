//set path="C:\Program Files (x86)\Java\jdk1.7.0_80\bin";
//jar -cvmf Fp.mf Fp.jar Fp.class  
import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.lang.Object;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

//import javax.swing.filechooser.FileNameExtensionFilter;
public class Fp extends JFrame implements ActionListener
{
JTextField t1,t2,t3;	
JButton b,rep,del;
JTextArea area;
JLabel l,l1,l2;  
File folder;
Font fn;
JFrame f;
File[] listOfFiles;
JFileChooser fc,chooser; 
JPanel panel;
Fp()
{   
	f=new JFrame("File Parser");  
	try
		{
           JLabel jl=new JLabel(new ImageIcon(ImageIO.read(new File("12.jpg"))));
		   jl.setBounds(0,0,800,650); 
		   f.setContentPane(jl);
        } catch (IOException e) {
            //e.printStackTrace();
        }
      f.pack();
	 
	fc= new JFileChooser();
	fc.setCurrentDirectory(new java.io.File("."));
	
	fc.addActionListener(this);
	
	panel=new JPanel();	
	panel.add(fc);
 
        panel.setBounds(120,250,500,350); 
		
		f.add(panel);  
   	fn=new Font("Arial",Font.BOLD,20); 
	setFont(fn);
	f.setForeground(Color.white);
	l=new JLabel("Directory.");  l.setFont(fn);l.setForeground(Color.white);
	l.setBounds(100,70, 100,30); 
    l1=new JLabel("Find."); l1.setFont(fn); l1.setForeground(Color.white);
    l1.setBounds(100,120, 100,30);  
    l2=new JLabel("Replace."); l2.setFont(fn); l2.setForeground(Color.white);
    l2.setBounds(100,170,100,30);  
    f.add(l1); f.add(l2);f.add(l); 
	

	
	fn=new Font("Arial",Font.BOLD,22); 
	b=new JButton("Browse");  b.setFont(fn);
    b.setBounds(450,70,120,30);  
	
	rep=new JButton("Rename");  rep.setFont(fn);
    rep.setBounds(450,170,120,30);  
	
	del=new JButton("Delete ()");  del.setFont(fn);
    del.setBounds(600,170,120,30);  
	
    f.add(b);    
	b.addActionListener(this);  
	
	f.add(rep);    
	rep.addActionListener(this);  
	
	f.add(del);    
	del.addActionListener(this);  
	
    t1=new JTextField();  
    t1.setBounds(200,70, 200,30);  
    t2=new JTextField();  
    t2.setBounds(200,120, 200,30);  
	  
	t3=new JTextField();  
    t3.setBounds(200,170, 200,30);
    f.add(t1); f.add(t2); f.add(t3); 

	f.setSize(800,650);  
    f.setLocationRelativeTo(null);
    f.setLayout(null);    
    f.setVisible(true); 
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	
}
  
  public void actionPerformed(ActionEvent e)
{
if(e.getSource()==fc)
	{
	t1.setText(""+(fc.getCurrentDirectory()));
	}
	
	if(e.getSource()==b)
	{
		String fn=dirc();
		File ff=new File(fn);
		t1.setText(fn);

		fc.setCurrentDirectory(ff);
	}
	else if(e.getSource()==rep)
	{
		if(t2.getText().length()!=0&&t3.getText().length()!=0)
			repl();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setCurrentDirectory(new File(t1.getText()));
		
    }
	else if(e.getSource()==del)
	{
	if(t2.getText().length()!=0)
		deli();
	fc.setCurrentDirectory(new java.io.File("."));
	fc.setCurrentDirectory(new File(t1.getText()));
    }
}
	void repl()
	{
	
	folder = new File(t1.getText());
	listOfFiles = folder.listFiles();
    int c=0;
	for (int i = 0; i < listOfFiles.length; i++)
	{
    if (listOfFiles[i].isFile())
	{
	String t=listOfFiles[i].getName();
	String fs=t2.getText();
	if(t.contains(fs))
		  c++;
	}
	}
	int r = JOptionPane.showConfirmDialog(null, "Do you want to Rename: " + c + " files?", "Confirm",
    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if(r==0)
	{
	for (int i = 0; i < listOfFiles.length; i++)
	{
		if (listOfFiles[i].isFile())
		{
		String t=listOfFiles[i].getName();
		String fs=t2.getText();
		if(t.contains(fs))
		{		
			File oldName = new File(folder+"\\"+t);
			String rs=t.replace(fs,t3.getText());
			File newName = new File(folder+"\\"+rs);
            oldName.renameTo(newName);
		}
		}
	}
	}
	}
void deli()
	{
	
	folder = new File(t1.getText());
	listOfFiles = folder.listFiles();
    int c=0;
	String fs=t2.getText();
	for (int i = 0; i < listOfFiles.length; i++)
	{
    if (listOfFiles[i].isFile())
	{
	String t=listOfFiles[i].getName();
	
	if(t.contains(fs)||t.contains("(")&&t.contains(")"))
		  c++;
	}
	}
	int r = JOptionPane.showConfirmDialog(null, "Do you want to Delete: " + c + " files? containing " + fs , "Confirm",
    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if(r==0)
	{
	for (int i = 0; i < listOfFiles.length; i++)
	{
      if (listOfFiles[i].isFile())
	  {
     	String t=listOfFiles[i].getName();
		if(t.contains(fs)||t.contains("(")&&t.contains(")"))
		{		
			File old = new File(folder+"\\"+t);
			old.delete();
		}
	  }
	}
	}
	
	}
public String dirc()
  {

    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Choose Directory(files not shown)");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
	     return ("" + chooser.getSelectedFile());
	 else
		 return "";
     
   }  
public static void main(String arg[])
{
	Fp f=new Fp();	
}
}
