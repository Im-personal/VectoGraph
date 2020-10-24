import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main {
	static WFrame wfr;
	static JFrame save=new JFrame("Сохранить Модель");
	static JFrame load=new JFrame("Загрузить Модель");
	
	static JFrame Animasave=new JFrame("Сохранить Анимацию");
	static JFrame Animaload=new JFrame("Загрузить Анимацию");
	
	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		
		g.addMouseListener(new Mistener());
		g.addMouseMotionListener(new Mistener());
	
		
		wfr=new WFrame(800,600,"VectorGraph v2.0",true, g);
		wfr.addKeyListener(new Mistener());
		wfr.setResizable(false);
		
		JFileChooser ffop = new JFileChooser();
		ffop.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JButton dial = new JButton("обзор...");
		
		save.setSize(800, 200);
		save.setLocationRelativeTo(null);
		save.setLayout(new GridLayout(4,2,10,10));
		save.add(new JLabel("Расположение"));
		JButton s = new JButton("Сохранить");
		JTextField ss= new JTextField("C:\\VectoGraphV2_0Files\\Models");
		
		
		
		dial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ret=ffop.showDialog(null,"открыть");
				if(ret==JFileChooser.APPROVE_OPTION)
				ss.setText(ffop.getSelectedFile().getAbsolutePath());
				
			}
			
		});
		
		
		save.add(ss);
		save.add(dial);
		save.add(new JPanel());
		save.add(new JLabel("Имя файла"));
		JTextField nf = new JTextField(generateRandomName(8));
		save.add(nf);
		save.add(s);
		
		s.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Mistener.createFile(Graph.nowModel().createConfiguration(),ss.getText(),nf.getText()))
					save.setVisible(false);
				
			}
			
		});
		
		
		
		
		load.setSize(400,200);
		load.setLayout(new GridLayout(2,3,10,10));
		load.setLocationRelativeTo(null);
		load.add(new JLabel("Расположение"));
		JTextField lf = new JTextField("C:\\VectoGraphV2_0Files\\Models");
		JButton lb = new JButton("Готово");
		JFileChooser fop = new JFileChooser();
		
		JButton dialog = new JButton("обзор...");
		load.add(lf);
		load.add(dialog);
		load.add(lb);
		
		dialog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ret=fop.showDialog(null,"открыть");
				if(ret==JFileChooser.APPROVE_OPTION)
				lf.setText(fop.getSelectedFile().getAbsolutePath());
				
			}
			
		});
		
		lb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				loadData(lf.getText());
				
				load.setVisible(false);
			}
			
		});
		
		Animasave.setSize(800, 200);
		Animasave.setLocationRelativeTo(null);
		Animasave.setLayout(new GridLayout(4,2,10,10));
		Animasave.add(new JLabel("Расположение"));
		
		JTextField savepatch = new JTextField("C:\\VectoGraphV2_0Files\\Animations");
		JTextField aniname = new JTextField(generateRandomName(8));
		JButton findpatch = new JButton("обзор...");
		JButton saveanima = new JButton("Сохранить");
		Animasave.add(savepatch);
		Animasave.add(findpatch);
		Animasave.add(new JPanel());
		Animasave.add(new JLabel("Имя файла"));
		Animasave.add(aniname);
		Animasave.add(saveanima);
		
		findpatch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ret=ffop.showDialog(null,"открыть");
				if(ret==JFileChooser.APPROVE_OPTION)
				savepatch.setText(ffop.getSelectedFile().getAbsolutePath());
				
			}
			
		});
		
		saveanima.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Mistener.createVkaFile(Graph.testdata,savepatch.getText(),aniname.getText()))
					Animasave.setVisible(false);
				
			}
			
		});
		
		
		
		
		Animaload.setSize(400,200);
		Animaload.setLayout(new GridLayout(2,3,10,10));
		Animaload.setLocationRelativeTo(null);
		Animaload.add(new JLabel("Расположение"));
		JTextField patcha = new JTextField("C:\\VectoGraphV2_0Files\\Animations");
		JButton endButton = new JButton("Готово");
	//	JFileChooser fop = new JFileChooser();
		
		JButton finda = new JButton("обзор...");
		Animaload.add(patcha);
		Animaload.add(finda);
		Animaload.add(endButton);
		
		finda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ret=fop.showDialog(null,"открыть");
				if(ret==JFileChooser.APPROVE_OPTION)
				patcha.setText(fop.getSelectedFile().getAbsolutePath());
				
			}
			
		});
		
		endButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				loadAnimationData(patcha.getText());
				
				Animaload.setVisible(false);
			}
			
		});
		
	}

	private static boolean createFile(String url,String name)
	{
		//String name = generateRandomName(8);
		File f = new File(url,name+".vkg");
		
	
		
		try {
			FileWriter ff = new FileWriter(url+name+".vkg",false);
			f.createNewFile();
			
				ff.write(Graph.nowModel().createConfiguration());
				ff.flush();
				System.out.println(name+".vkg is created!");
				System.out.println(Graph.nowModel().createConfiguration());
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private static String generateRandomName(int n)
	{
	//	for(int i = 66; i<166;i++)
		//	System.out.print(i+": "+(char)(i));
		String str="";
		for(int i = 0 ;i<n;i++)
		{
			int r =(int)(66+Math.random()*(123-66));
			for(;(r>90&&r<98);)r++;
			str+=(char)(r);
			
		}
		
		return str;
		
	}
	private static void loadData(String src)
	{
		File f = new File(src);
		try {
			FileReader ff = new FileReader(f);
			String data="";
			int c;
			while((c=ff.read())!=-1)
				data+=(char)c;
			System.out.println(src);
			//System.exit(0);
			Graph.nowModel().loadConfiguration(data.split("#"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void loadAnimationData(String src)
	{
		File f = new File(src);
		try {
			FileReader ff = new FileReader(f);
			String data="";
			int c;
			while((c=ff.read())!=-1)
				data+=(char)c;
			System.out.println(src);
			//System.exit(0);
			String[] datass = data.split("!");
			
			Graph.models.clear();
			Graph.nowModel=0;
			Graph.now=0;
			for(int i = 0;i<datass.length;i++)
			{//System.out.println(datass[i]);
				Graph.models.add(new Model());
			Graph.models.get(i).loadConfiguration(datass[i].split("#"));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
