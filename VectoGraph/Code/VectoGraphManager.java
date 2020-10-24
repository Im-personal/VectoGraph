public interface VectoGraphManager {

	public default void drawModel(Model m,int x, int y,int w, int h, double angle)
	{
		rotateFigure(x+w/2,y+h/2,angle);
		for(int i = 0; i<m.lenght();i++)
		{
			int xx = x/100*w;
			int yy = y/100*h;
			setColor(m.isGradient(i),m.isCycled(i),m.getR(i),m.getG(i),m.getB(i),m.getR2(i),m.getG2(i),m.getB2(i),m.getX1(i),m.getY1(i),m.getX2(i),m.getY2(i));
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			drawFigure(m.getX(i),m.getY(i),m.getW(i),m.getH(i),m.getType(i));
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
		resetRotate(x+w/2,y+h/2,angle);
	}
	
	public default void drawModel(Model m,int x, int y,int w, int h, double angle,boolean saveProp)
	{
		int mx=999999999,my=999999999;
		int mw=0, mh = 0;
		for(int i = 0; i<m.lenght();i++)
		{
			if(mx>m.getX(i))mx=m.getX(i);
			if(my>m.getY(i))my=m.getY(i);
			if(mw<m.getX(i)+m.getW(i))mw=m.getX(i)+m.getW(i);
			if(mh<m.getY(i)+m.getH(i))mh=m.getY(i)+m.getH(i);
		}
		
		
		rotateFigure((mx+mw)/2,(my+mh)/2,angle);
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),m.isCycled(i),m.getR(i),m.getG(i),m.getB(i),m.getR2(i),m.getG2(i),m.getB2(i),m.getX1(i),m.getY1(i),m.getX2(i),m.getY2(i));
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			drawFigure(m.getX(i),m.getY(i),m.getW(i),m.getH(i),m.getType(i));
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
		resetRotate((mx+mw)/2,(my+mh)/2,angle);
	}
	
	public default void drawModel(Model m,int x, int y, double angle)
	{
		int mx=999999999,my=999999999;
		int mw=0, mh = 0;
		for(int i = 0; i<m.lenght();i++)
		{
			if(mx>m.getX(i))mx=m.getX(i);
			if(my>m.getY(i))my=m.getY(i);
			if(mw<m.getX(i)+m.getW(i))mw=m.getX(i)+m.getW(i);
			if(mh<m.getY(i)+m.getH(i))mh=m.getY(i)+m.getH(i);
		}
		rotateFigure((mx+mw)/2,(my+mh)/2,angle);
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),m.isCycled(i),m.getR(i),m.getG(i),m.getB(i),m.getR2(i),m.getG2(i),m.getB2(i),m.getX1(i),m.getY1(i),m.getX2(i),m.getY2(i));
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			drawFigure(m.getX(i),m.getY(i),m.getW(i),m.getH(i),m.getType(i));
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
		resetRotate((mx+mw)/2,(my+mh)/2,angle);
	}
	
	public default void drawModel(Model m,int x, int y,int w, int h)
	{
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),m.isCycled(i),m.getR(i),m.getG(i),m.getB(i),m.getR2(i),m.getG2(i),m.getB2(i),m.getX1(i),m.getY1(i),m.getX2(i),m.getY2(i));
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			drawFigure(m.getX(i),m.getY(i),m.getW(i),m.getH(i),m.getType(i));
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
	}
	
	public default Model[]  loadAnimation(String src)
	{
		String[] data = getFileStr(src).split("!");
		Model[] ret=new Model[data.length];
		for(int i = 0;i<ret.length;i++)
		{
			ret[i]=new Model();
			ret[i].loadConfiguration(data[i].split("#"));
		}
		return ret;
	}
	
	public default Model loadModel(String src)
	{
		Model ret = new Model();
		ret.loadConfiguration(getFileStr(src).split("#"));
		return ret;
	}
	
	public default Skeleton[] loadSkeletonAnimation(String src)
	{
		String[] data = getFileStr(src).split("¹");
		Skeleton[] ret = new Skeleton[data.length];
		
		for(int i = 0;i<ret.length;i++)
		{
		ret[i] = new Skeleton();
		ret[i].loadConfiguration(data[i]);
		}
		
		return ret;
	}
	
	public default Skeleton loadSkeleton(String src)
	{
		Skeleton ret=new Skeleton();
		ret.loadConfiguration(getFileStr(src));
		return ret;
	}
	
	String getFileStr(String src);
	
	
	public void drawFigure(int x, int y,int width, int height,int type);
	public void setColor(boolean isGradient, boolean gradientCycled,int redColor, int greenColor,int blueColor,int redColor2, int greenColor2,int blueColor2,int gradientX1, int gradientY1,int gradientX2, int gradientY2);
	public void rotateFigure(int centerX,int centerY, double radian);
	public void resetRotate(int centerX,int centerY, double radian);
	
}
