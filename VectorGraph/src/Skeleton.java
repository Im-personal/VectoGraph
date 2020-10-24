import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Skeleton {
	public int length=0;
	private ArrayList<int[]> x = new ArrayList();
	private ArrayList<int[]> y = new ArrayList();
	private ArrayList<double[]> angle = new ArrayList();
	
	private ArrayList<Model> model = new ArrayList();
	private ArrayList<int[]> xm = new ArrayList();
	private ArrayList<int[]> ym = new ArrayList();
	private ArrayList<double[]> anglem = new ArrayList();
	
	public Skeleton() {
		
	}
	public Skeleton clone()
	{
	Skeleton s = new Skeleton();
	for(int i = 0; i<length;i++)
	{
		s.addNewBone(getX(i),getY(i),getAngle(i));
		s.setModel(i, getModel(i).clone());
		s.setModelX(i, getModelX(i));
		s.setModelY(i, getModelY(i));
		s.setModelAngle(i, getModelAngle(i));
	}
	return s;
	}
	public void addNewBone(int x, int y,double angle)
	{
		this.x.add(new int[]{x});
		this.y.add(new int[]{y});
	    this.angle.add(new double[]{angle});
		length++;
		
		Model m = new Model();
		
		//m.loadModel("C:\\Users\\Admin\\Desktop\\fileTest\\lapka.vkg");
		model.add(m);
		xm.add(new int[] {0});
		ym.add(new int[] {0});
		anglem.add(new double[] {0});
		//log(true);
	}
	
	
	
	public Model getModel(int i)
	{
		return model.get(i);
	}
	
	public void setModel(int i, String str)
	{
		Model m = new Model();
		m.loadModel(str);
		model.set(i, m);
	}
	
	public void setModel(int i, Model m)
	{
		model.set(i, m);
	}
	
	public int getX(int i)
	{
		return x.get(i)[0];
	}
	public double getModelAngle(int i)
	{
		return anglem.get(i)[0];
	}
	
	public void setModelAngle(int i,double a)
	{
		anglem.set(i,new double[] {a});
	}
	
	public int getY(int i)
	{
		return y.get(i)[0];
	}
	
	
	public int getModelX(int i)
	{
		return xm.get(i)[0];
	}
	
	public int getModelY(int i)
	{
		return ym.get(i)[0];
	}
	
	
	
	public void setAngle(int i, double angle)
	{
		this.angle.set(i, new double[] {angle});
	}
	
	public void setX(int i, int x)
	{
		this.x.set(i, new int[] {x});
	}
	
	public void setY(int i,int y)
	{
		this.y.set(i, new int[] {y});
	}
	
	public void setModelX(int i, int x)
	{
		this.xm.set(i, new int[] {x});
	}
	
	
	public void setModelY(int i,int y)
	{
		this.ym.set(i, new int[] {y});
	}
	
	public double getAngle(int i)
	{
		return angle.get(i)[0];
	}
	
	public void shiftAngle(int i, double angle)
	{
		this.angle.set(i, new double[] {angle+this.angle.get(i)[0]});
	}
	
	public void shiftX(int i, int x)
	{
		this.x.set(i, new int[] {x+this.x.get(i)[0]});
	}
	
	public void shiftY(int i,int y)
	{
		this.y.set(i, new int[] {y+this.y.get(i)[0]});
	}
	
	public void shiftModelX(int i, int x)
	{
		this.xm.set(i, new int[] {x+this.xm.get(i)[0]});
	}
	
	public void shiftModelY(int i,int y)
	{
		this.ym.set(i, new int[] {y+this.ym.get(i)[0]});
	}
	private String getFileStr(String src)
	{
		String str="";
		File f = new File(src);
		try {
			FileReader ff = new FileReader(f);
			int c;
			while((c=ff.read())!=-1)
				str+=(char)c;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public void remove(int i)
	{
		x.remove(i);
		y.remove(i);
		angle.remove(i);
		
		xm.remove(i);
		ym.remove(i);
		anglem.remove(i);
		
		model.remove(i);
		length--;
	}
	
	public void removeModel(int i)
	{
		model.set(i,new Model());
		setModelX(i,0);
		setModelY(i,0);
		setModelAngle(i,0);
	}
	
	public void clone(int i)
	{
		x.add(i,x.get(i));
		y.add(i,y.get(i));
		angle.add(i,angle.get(i));
		
		xm.add(i,xm.get(i));
		ym.add(i,ym.get(i));
		anglem.add(i,anglem.get(i));
		
		model.add(i,model.get(i).clone());
		length++;
	}
	
	public String createConfiguration()//данные_о_кости@ данные_о_модели~
	{
		String str ="";
		
		for(int i = 0; i<length;i++)
		{
			str+=getX(i)+",";
			str+=getY(i)+",";
			str+=getAngle(i)+",";
			
			str+=getModelX(i)+",";
			str+=getModelY(i)+",";
			str+=getModelAngle(i)+"@";
			str+=model.get(i).createConfiguration()+"~";
		}
		
		return str;
	}
	
	public void loadConfiguration(String str)
	{clearSkeleton();
		String[] data = str.split("~");
		
		for(int i=0;i<data.length;i++)
		{
			String[] data1 = data[i].split("@");
			String[] data2 = data1[0].split(",");
			Model m = new Model();
			m.loadConfiguration(data1[1].split("#"));
			model.add(m);
			x.add(new int[] {Integer.parseInt(data2[0])});
			y.add(new int[] {Integer.parseInt(data2[1])});
			angle.add(new double[] {Double.parseDouble(data2[2])});
			
			xm.add(new int[] {Integer.parseInt(data2[3])});
			ym.add(new int[] {Integer.parseInt(data2[4])});
			anglem.add(new double[] {Double.parseDouble(data2[5])});
		}
		length=data.length;
	}
	
	public void clearSkeleton()
	{
		x.clear();
		y.clear();
		angle.clear();
		
		xm.clear();
		ym.clear();
		anglem.clear();
		
		model.clear();
		length=0;
	}
}
