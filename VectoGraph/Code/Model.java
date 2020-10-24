import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Model{
	
	private String name = "Untitled";
	
	public int forms=0;
	public ArrayList <String> type = new ArrayList<String>();
	public ArrayList <String> x = new ArrayList<String>();
	public ArrayList <String> y = new ArrayList<String>();
	public ArrayList <String> w = new ArrayList<String>();
	public ArrayList <String> h = new ArrayList<String>();
	
	public ArrayList <String> r = new ArrayList<String>();
	public ArrayList <String> g = new ArrayList<String>();
	public ArrayList <String> b = new ArrayList<String>();
	public ArrayList <String> a = new ArrayList<String>();
	
	public ArrayList <String> x1 = new ArrayList<String>();
	public ArrayList <String> y1 = new ArrayList<String>();

	public ArrayList <String> x2 = new ArrayList<String>();
	public ArrayList <String> y2 = new ArrayList<String>();
	public ArrayList <String> r2 = new ArrayList<String>();
	public ArrayList <String> g2 = new ArrayList<String>();
	public ArrayList <String> b2 = new ArrayList<String>();
	public ArrayList <String> a2 = new ArrayList<String>();
	
	public ArrayList <String> radians = new ArrayList<String>();
	
	public ArrayList <String> isGradient = new ArrayList<String>();
	public ArrayList <String> isCycled = new ArrayList<String>();
	public ArrayList <String> fill = new ArrayList<String>();
	public ArrayList <String> hasPolygon = new ArrayList<String>();
	
	public Model()
	{
forms=0;
	}
	

public Model clone()
{
	Model m = new Model();
	for(int i = 0; i<forms;i++)
		m.type.add( type.get(i));
	for(int i = 0; i<forms;i++)
		m.x.add( x.get(i));
	for(int i = 0; i<forms;i++)
		m.y.add( y.get(i));
	for(int i = 0; i<forms;i++)
		m.w.add( w.get(i));
	for(int i = 0; i<forms;i++)
		m.h.add( h.get(i));
	for(int i = 0; i<forms;i++)
		m.r.add( r.get(i));	
		for(int i = 0; i<forms;i++)
			m.g.add( g.get(i));
		for(int i = 0; i<forms;i++)
			m.b.add( b.get(i));
		for(int i = 0; i<forms;i++)
			m.a.add( a.get(i));
		for(int i = 0; i<forms;i++)
		m.x1.add( x1.get(i));
	for(int i = 0; i<forms;i++)
		m.y1.add( y1.get(i));
	for(int i = 0; i<forms;i++)
		m.x2.add( x2.get(i));
	for(int i = 0; i<forms;i++)
		m.y2.add( y2.get(i));
	for(int i = 0; i<forms;i++)
		m.r2.add( r2.get(i));	
		for(int i = 0; i<forms;i++)
			m.g2.add( g2.get(i));
		for(int i = 0; i<forms;i++)
			m.b2.add( b2.get(i));
		for(int i = 0; i<forms;i++)
			m.a2.add( a2.get(i));
		for(int i = 0; i<forms;i++)
			m.radians.add( radians.get(i));	
			for(int i = 0; i<forms;i++)
				m.isGradient.add( isGradient.get(i));
			for(int i = 0; i<forms;i++)
				m.isCycled.add( isCycled.get(i));
			for(int i = 0; i<forms;i++)
				m.fill.add( fill.get(i));
			for(int i = 0; i<forms;i++)
				m.hasPolygon.add( hasPolygon.get(i));
			
			for(int i = 0; i<forms;i++)
				m.pointsX.add( pointsX.get(i));
			for(int i = 0; i<forms;i++)
				m.pointsY.add( pointsY.get(i));
	
	
	m.forms=lenght();
	return m;
}
	
	public Model(String name)
	{
		this.name=name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void newFigure(int type,int x, int y, int r, int g, int b,int w, int h, int x1, int y1, int x2,int y2, int r2, int g2, int b2)
	{
		this.type.add(toString(type));
		this.x.add(toString(x));
		this.y.add(toString(y));
		this.w.add(toString(w));
		this.h.add(toString(h));
		
		this.r.add(toString(r));
		this.g.add(toString(g));
		this.b.add(toString(b));
		this.a.add(toString(255));
		
		this.isGradient.add("true");
		this.x1.add(toString(x1));
		this.y1.add(toString(x1));
		
	
		this.r2.add(toString(r2));
		this.g2.add(toString(g2));
		this.b2.add(toString(b2));
		this.a2.add(toString(255));
		this.x2.add(toString(x2));
		this.y2.add(toString(y2));
		
		this.radians.add(0+"");
		fill.add("true");
		isGradient.add("false");
		isCycled.add("false");
		hasPolygon.add("false");
		int[] xx = {x};
		pointsX.add(xx);
		int[] yy = {y};
		pointsY.add(yy);
		
		forms++;
	}
	

	public void newFigure(int type,int x, int y, int r, int g, int b,int a,int w, int h, int x1, int y1, int x2,int y2, int r2, int g2, int b2,int a2, boolean isGradient,boolean fill,double radians,boolean isCycled,boolean hasPolygon, int num)
	{
		this.type.add(num,toString(type));
		this.x.add(num,toString(x));
		this.y.add(num,toString(y));
		this.w.add(num,toString(w));
		this.h.add(num,toString(h));
		
		this.r.add(num,toString(r));
		this.g.add(num,toString(g));
		this.b.add(num,toString(b));
		this.a.add(num,toString(a));
		
		this.isGradient.add(num,"true");
		this.x1.add(num,toString(x1));
		this.y1.add(num,toString(x1));
		
	
		this.r2.add(num,toString(r2));
		this.g2.add(num,toString(g2));
		this.b2.add(num,toString(b2));
		this.a2.add(num,toString(a2));
		this.x2.add(num,toString(x2));
		this.y2.add(num,toString(y2));
		
		
		
		this.radians.add(num,toString(radians));
		
		if(fill)
			this.fill.add(num,true+"");
		else
			this.fill.add(num,false+"");
		
		if(isGradient)
		{
			this.isGradient.add(num,true+"");
		}
		else
		{
			this.isGradient.add(num,false+"");
			changeGradient(num+1);
		}
		
		if(isCycled)
		{
			this.isCycled.add(num,true+"");
		}
		else
		{
			this.isCycled.add(num,false+"");
		if(isCycled(num+1))	changeCycled(num+1);
		}
		
		if(hasPolygon)
		{
			this.hasPolygon.add(num,true+"");
		}
		else
		{
			this.hasPolygon.add(num,false+"");
		if(hasPolygon(num+1))	changePolygon(num+1);
		}
		
		forms++;
	}
	
	
	
	ArrayList <int[]> pointsX = new ArrayList <int[]>();
	ArrayList <int[]> pointsY = new ArrayList <int[]>();
	
	
	public int[] getPointsX(int num)
	{
		return pointsX.get(num);
	}
	
	public int[] getPointsY(int num)
	{
		return pointsY.get(num);
	}
	
	public void shiftAllPoints(int x, int y, int num)
	{
		
		int[] ox = getPointsX(num).clone();
		int[] oy= getPointsY(num).clone();
		
		int[] nx = new int[ox.length];
		int[] ny = new int[oy.length];
		
		for(int i = 0; i<ny.length;i++)
		{
			nx[i]=ox[i]+x;
			ny[i]=oy[i]+y;
		}
		
		pointsY.set(num,ny);
		pointsX.set(num,nx);
	}
	
	public void shiftPoint(int x, int y, int num, int point)
	{
	
		int[] ox = getPointsX(num).clone();
		int[] oy= getPointsY(num).clone();
		
		int[] nx = new int[ox.length];
		int[] ny = new int[oy.length];
		
		for(int i = 0; i<ny.length;i++)
		{
			if(i==point) {
				nx[i]=ox[i]+x;
				ny[i]=oy[i]+y;}
			else
			{
				ny[i]=oy[i];
				nx[i]=ox[i];
			}
		}
		pointsX.set(num,nx);
		pointsY.set(num,ny);
	}
	
	public void setPointX(int x, int num, int point)
	{
	
		int[] ox = getPointsX(num).clone();
		
		
		int[] nx = new int[ox.length];
	
		
		for(int i = 0; i<nx.length;i++)
		{
			if(i==point) {
				nx[i]=x;
			}
			else
			{
				
				nx[i]=ox[i];
			}
		}
	

		pointsX.set(num,nx);
}
	
	public void setPointY(int y, int num, int point)
	{
	
		int[] oy = getPointsY(num).clone();
		
		
		int[] ny = new int[oy.length];
	
		
		for(int i = 0; i<ny.length;i++)
		{
			if(i==point) {
				ny[i]=y;
			}
			else
			{
				
				ny[i]=oy[i];
			}
		}
	

		pointsY.set(num,ny);
}
	
	public void deletePoint(int num, int point)
	{
		int[] oy = getPointsY(num).clone();
		int[] ox = getPointsX(num).clone();
		int[] nx = new int[ox.length-1];
		int[] ny = new int[oy.length-1];
		for(int i = 0; i<nx.length;i++)
		{
			if(i<=point)
			{
			nx[i]=ox[i];
			ny[i]=oy[i];
			}
			else
			{
				nx[i]=ox[i+1];
				ny[i]=oy[i+1];
			}
		}
		pointsY.set(num,ny);
		pointsX.set(num,nx);
	}
	
	public void copyPoint(int num, int point)
	{//System.out.println("da");
		int[] oy = getPointsY(num).clone();
		int[] ox = getPointsX(num).clone();
		int[] nx = new int[ox.length+1];
		int[] ny = new int[oy.length+1];
		for(int i = 0; i<ox.length;i++)
		{
			if(i<=point)
			{
			nx[i]=ox[i];
			ny[i]=oy[i];
			}
			else
			{
				nx[i+1]=ox[i];
				ny[i+1]=oy[i];
			}
		}
		pointsY.set(num,ny);
		pointsX.set(num,nx);
	}
	
	public void addPoints(int x, int y, int num)
	{
		
		if(getPointsY(num).length>0)
		{
			int[] ox = getPointsX(num).clone();
			int[] oy= getPointsY(num).clone();
			
			int[] nx = new int[ox.length+1];
			int[] ny = new int[oy.length+1];
		
		for(int i = 0; i<nx.length;i++)
		{
			if(i<nx.length-1)
			{
				nx[i]=ox[i];
				ny[i]=oy[i];
			}
			else
			{
				nx[i]=x;
				ny[i]=y;
			}
		}
		pointsY.set(num,ny);
		pointsX.set(num,nx);
		}
		else
		{
			int[] nx = {x};
			int[] ny = {y};
			pointsY.set(num,ny);
			pointsX.set(num,nx);
		}
	}
	
	
	
	public void setX(int x,int num)
	{
		this.x.set(num,toString(x));
	}
	
	
	public void setY(int y, int num)
	{
		this.y.set(num,toString(y));
	}
	
	public void setX1(int x1,int num)
	{
		this.x1.set(num,toString(x1));
	}
	
	
	public void setY1(int y1, int num)
	{
		this.y1.set(num,toString(y1));
	}
	
	public void setX2(int x2,int num)
	{
		this.x2.set(num,toString(x2));
	}
	
	
	public void setY2(int y2, int num)
	{
		this.y2.set(num,toString(y2));
	}
	
	public void setW(int w,int num)
	{
		this.w.set(num,toString(w));
	}
	
	
	public void setH(int h, int num)
	{
	
		this.h.set(num,toString(h));
	}
	
	
	public void setR(int r,int num)
	{
		this.r.set(num,toString(r));
	}
	
	public void setA2(int r2,int num)
	{
		this.a2.set(num,toString(r2));
	}
	
	public void changeFill(int num)
	{
		if(fill.get(num)=="true")
			fill.set(num, "false");
			else
				fill.set(num, "true");
	}
	
	public void changePolygon(int num)
	{
		if(hasPolygon.get(num)=="true")
			hasPolygon.set(num, "false");
			else
				hasPolygon.set(num, "true");
	}
	
	public void changeGradient(int num)
	{
		if(isGradient.get(num)=="true")
			isGradient.set(num, "false");
			else
				isGradient.set(num, "true");
	}
	
	public void changeCycled(int num)
	{
		if(isCycled.get(num)=="true")
			isCycled.set(num, "false");
			else
				isCycled.set(num, "true");
	}
	
	public boolean fill(int num)
	{
		return Boolean.parseBoolean(fill.get(num));
	}
	
	public boolean isGradient(int num)
	{
		return Boolean.parseBoolean(isGradient.get(num));
	}
	
	public boolean isCycled(int num)
	{
		return Boolean.parseBoolean(isCycled.get(num));
	}
	
	public boolean hasPolygon(int num)
	{
		return Boolean.parseBoolean(hasPolygon.get(num));
	}
	
	
	
	public void setB(int b, int num)
	{
		this.b.set(num,toString(b));
	}
	
	
	public void setG(int g, int num)
	{
		this.g.set(num,toString(g));
	}
	
	public void setA(int a, int num)
	{
		this.a.set(num,toString(a));
	}
	
	public void setR2(int r2,int num)
	{
		this.r2.set(num,toString(r2));
	}
	
	
	public void setB2(int b2, int num)
	{
		this.b2.set(num,toString(b2));
	}
	
	
	public void setG2(int g2, int num)
	{
		this.g2.set(num,toString(g2));
	}
	
	
	
	public int getX(int num)
	{
		return Integer.parseInt(this.x.get(num));
	}
	
	public double getRadians(int num)
	{
		return Double.parseDouble(this.radians.get(num));
	}
	
	public void setRadians(double r,int num)
	{
		this.radians.set(num,toString(r));
	}
	
	public int getY(int num)
	{
		return Integer.parseInt(this.y.get(num));
	}
	
	public int getX1(int num)
	{
		return Integer.parseInt(this.x1.get(num));
	}
	
	
	public int getY1(int num)
	{
		return Integer.parseInt(this.y1.get(num));
	}
	
	public int getX2(int num)
	{
		return Integer.parseInt(this.x2.get(num));
	}
	
	
	public int getY2(int num)
	{
		return Integer.parseInt(this.y2.get(num));
	}
	
	public int getW(int num)
	{
		return Integer.parseInt(this.w.get(num));
	}
	
	
	public int getH(int num)
	{
		return Integer.parseInt(this.h.get(num));
	}
	
	public int getA(int num)
	{
		return Integer.parseInt(this.a.get(num));
	}
	
	public int getA2(int num)
	{
		return Integer.parseInt(this.a2.get(num));
	}
	
	public int getR(int num)
	{
		return Integer.parseInt(this.r.get(num));
	}
	
	
	public int getB(int num)
	{
		return Integer.parseInt(this.b.get(num));
	}
	
	
	public int getG( int num)
	{
		return Integer.parseInt(this.g.get(num));
	}
	
	public int getR2(int num)
	{
		return Integer.parseInt(this.r2.get(num));
	}
	
	
	public int getB2(int num)
	{
		return Integer.parseInt(this.b2.get(num));
	}
	
	
	public int getG2(int num)
	{
		return Integer.parseInt(this.g2.get(num));
	}
	
	public void setType(int type,int num)
	{
		this.type.set(num, toString(type));
	}
	
	public int getType(int num)
	{
		return Integer.parseInt(this.type.get(num));
	}
	
	public void delete(int now)
	{
	if(forms>0&&now<forms) {
		type.remove(now);
		x.remove(now);
		y.remove(now);
		w.remove(now);
		h.remove(now);
		r.remove(now);
		g.remove(now);
		b.remove(now);
		a.remove(now);
		x1.remove(now);
		y1.remove(now);
		x2.remove(now);
		y2.remove(now);
		r2.remove(now);
		g2.remove(now);
		b2.remove(now);
		a2.remove(now);
		radians.remove(now);
		isGradient.remove(now);
		isCycled.remove(now);
		fill.remove(now);
		hasPolygon.remove(now);
		pointsX.remove(now);
		pointsY.remove(now);
		
		forms--;
	}
	}
	public void clone(int now)
	{
		if(forms>0)//int type,int x, int y, int r, int g, int b,int a,int w, int h, int x1, int y1, int x2,int y2, int r2, int g2, int b2,int a2, boolean isGradient,boolean fill,double radians,boolean isCycled,
		/*//*///	newFigure(getType(now),getX(now),getY(now),getR(now),getG(now),getB(now),getA(now),getW(now),getH(now),getX1(now),getY1(now),getX2(now),getY2(now),getR2(now),getG2(now),getB2(now),getA2(now),isGradient(now),fill(now),getRadians(now),isCycled(now),hasPolygon(now),now);
		{
			type.add(now,type.get(now));
			x.add(now,x.get(now));
			y.add(now,y.get(now));
			w.add(now,w.get(now));
			h.add(now,h.get(now));
			r.add(now,r.get(now));
			g.add(now,g.get(now));
			b.add(now,b.get(now));
			a.add(now,a.get(now));
			x1.add(now,x1.get(now));
			y1.add(now,y1.get(now));
			x2.add(now,x2.get(now));
			y2.add(now,y2.get(now));
			r2.add(now,r2.get(now));
			g2.add(now,g2.get(now));
			b2.add(now,b2.get(now));
			a2.add(now,a2.get(now));
			radians.add(now,radians.get(now));
			isGradient.add(now,isGradient.get(now));
			isCycled.add(now,isCycled.get(now));
			fill.add(now,fill.get(now));
			hasPolygon.add(now,hasPolygon.get(now));
			pointsX.add(now,pointsX.get(now));
			pointsY.add(now,pointsY.get(now));
			forms++;
		}
	
	}
	
	public int lenght()
	{
		return forms;
	}
	
	
	private String toString(int n)
	{
		return n+"";
	}
	
	private String toString(double n)
	{
		return n+"";
	}
	//тип, x y w h r g b a x1 y1 x2 y2 r2 g2 b2 a2 radians isGradient isCycled fill hasPolygon pointsX pointsY

	public String createConfiguration()
	{
		String con="";
		for(int i = 0; i<forms;i++)
		{
			con+=getType(i)+",";
			con+=getX(i)+",";
			con+=getY(i)+",";
			con+=getW(i)+",";
			con+=getH(i)+",";
			con+=getR(i)+",";
			con+=getG(i)+",";
			con+=getB(i)+",";
			con+=getA(i)+",";
			con+=getX1(i)+",";
			con+=getY1(i)+",";
			con+=getX2(i)+",";
			con+=getY2(i)+",";
			con+=getR2(i)+",";
			con+=getG2(i)+",";
			con+=getB2(i)+",";
			con+=getA2(i)+",";
			con+=getRadians(i)+",";
			con+=isGradient(i)+",";
			con+=isCycled(i)+",";
			con+=fill(i)+",";
			con+=hasPolygon(i)+"&";
			for(int j = 0; j<getPointsX(i).length;j++)
			{
				con+=getPointsX(i)[j]+",";
			}
			for(int j = 0; j<getPointsY(i).length;j++)
			{
				con+=getPointsY(i)[j]+",";
			}
			con+="#";
		}
		return con;
	}
	
	public void loadConfiguration(String[] data) {
		type.clear();
		x.clear();
		y.clear();
		w.clear();
		h.clear();
		r.clear();
		g.clear();
		b.clear();
		a.clear();
		x1.clear();
		y1.clear();
		x2.clear();
		y2.clear();
		r2.clear();
		g2.clear();
		b2.clear();
		a2.clear();
		radians.clear();
		isGradient.clear();
		isCycled.clear();
		fill.clear();
		hasPolygon.clear();
		pointsX.clear();
		pointsY.clear();
		for(int i = 0; i<data.length;i++)
		{
			String[] dd = data[i].split("&").clone();
			String[] d0 = dd[0].split(",").clone();
			String[] d1 = dd[1].split(",").clone();
			
			int[] px=new int[d1.length/2];
			int[] py=new int[d1.length/2];
			
			for(int j = 0; j<px.length;j++)
			{
				px[j]=Integer.parseInt(d1[j]);
				py[j]=Integer.parseInt(d1[j+px.length]);
			}
			
			
			type.add(d0[0]);
			x.add(d0[1]);
			y.add(d0[2]);
			w.add(d0[3]);
			h.add(d0[4]);
			r.add(d0[5]);
			g.add(d0[6]);
			b.add(d0[7]);
			a.add(d0[8]);
			x1.add(d0[9]);
			y1.add(d0[10]);
			x2.add(d0[11]);
			y2.add(d0[12]);
			r2.add(d0[13]);
			g2.add(d0[14]);
			b2.add(d0[15]);
			a2.add(d0[16]);
			radians.add(d0[17]);
		//	System.out.println(d0[17]);
			isGradient.add(d0[18]);
			isCycled.add(d0[19]);
			
			fill.add(d0[20]);
			
			
			
			hasPolygon.add(d0[21]);
			pointsX.add(px);
			pointsY.add(py);
			
		}
	
		forms=data.length;
	}
	
	public void loadModel(String str)
	{
		loadConfiguration(getFileStr(str).split("#"));
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
	
}
