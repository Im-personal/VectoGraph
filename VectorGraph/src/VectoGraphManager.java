public interface VectoGraphManager {


	
		
	
	public default void drawModel(Model m,int x, int y)
	{
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),
					m.isCycled(i)
					,m.getR(i),m.getG(i),m.getB(i),m.getA(i)
					,m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)
					,m.getX1(i),m.getY1(i)
					,m.getX2(i),m.getY2(i),
					m.getX(i)+x,m.getY(i)+y);
			
			
			int[] xx=new int[m.getPointsX(i).length];
			int[] yy=new int[m.getPointsY(i).length];
			for(int j = 0; j<xx.length;j++)
			{
				xx[j]=m.getPointsX(i)[j]+x;
				yy[j]=m.getPointsY(i)[j]+y;
			}
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			drawFigure(x+m.getX(i),y+m.getY(i),m.getW(i),m.getH(i),m.getType(i),m.fill(i),xx,yy);
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,-(m.getRadians(i)));
		}
		//resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
	}
	
	public default void drawModel(Model m,int x, int y, double angle)
	{
		
int minX=999999999, minY=999999999,maxX=0, maxY=0;
		
		for(int i = 0; i<m.lenght();i++)
		{
			if(minX>m.getX(i))minX=m.getX(i);
			if(minY>m.getY(i))minY=m.getY(i);
			
			if(maxX<m.getX(i)+m.getW(i))maxX=m.getX(i)+m.getW(i);
			if(maxY<m.getY(i)+m.getH(i))maxY=m.getY(i)+m.getH(i);
		}
		int centerX = (minX+maxX)/2+x;
		int centerY = (minY+maxY)/2+y;
		
		rotateFigure(centerX,centerY,angle);
		
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),
					m.isCycled(i)
					,m.getR(i),m.getG(i),m.getB(i),m.getA(i)
					,m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)
					,m.getX1(i),m.getY1(i)
					,m.getX2(i),m.getY2(i),
					m.getX(i)+x,m.getY(i)+y);
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			
			int[] xx=new int[m.getPointsX(i).length];
			int[] yy=new int[m.getPointsY(i).length];
			for(int j = 0; j<xx.length;j++)
			{
				xx[j]=m.getPointsX(i)[j]+x;
				yy[j]=m.getPointsY(i)[j]+y;
			}
			
			drawFigure(x+m.getX(i),y+m.getY(i),m.getW(i),m.getH(i),m.getType(i),m.fill(i),xx,yy);
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
		resetRotate(centerX,centerY,angle);
	}
	
	public default void drawModel(Model m,int x, int y, double angle, int centerX, int centerY)
	{	
		rotateFigure(centerX,centerY,angle);
		
		for(int i = 0; i<m.lenght();i++)
		{
			setColor(m.isGradient(i),
					m.isCycled(i)
					,m.getR(i),m.getG(i),m.getB(i),m.getA(i)
					,m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)
					,m.getX1(i),m.getY1(i)
					,m.getX2(i),m.getY2(i),
					m.getX(i)+x,m.getY(i)+y);
			rotateFigure((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
			
			int[] xx=new int[m.getPointsX(i).length];
			int[] yy=new int[m.getPointsY(i).length];
			for(int j = 0; j<xx.length;j++)
			{
				xx[j]=m.getPointsX(i)[j]+x;
				yy[j]=m.getPointsY(i)[j]+y;
			}
			
			drawFigure(x+m.getX(i),y+m.getY(i),m.getW(i),m.getH(i),m.getType(i),m.fill(i),xx,yy);
			resetRotate((x+m.getX(i))+m.getW(i)/2,(y+m.getY(i))+m.getH(i)/2,m.getRadians(i));
		}
		resetRotate(centerX,centerY,angle);
	}
	
	public default void drawModel(Model m,int x, int y, int w, int h,double angle)
	{
int minX=999999999, minY=999999999,maxX=0, maxY=0;
		
		for(int i = 0; i<m.lenght();i++)
		{
			if(minX>m.getX(i))minX=m.getX(i);
			if(minY>m.getY(i))minY=m.getY(i);
			
			if(maxX<m.getX(i)+m.getW(i))maxX=m.getX(i)+m.getW(i);
			if(maxY<m.getY(i)+m.getH(i))maxY=m.getY(i)+m.getH(i);
		}
		int centerX = (int)(((double)((minX+maxX)/2)/(double)maxX)*(double)w)+x;
		int centerY =  (int)(((double)((minY+maxY)/2)/(double)maxY)*(double)h)+y;
		
		
	
		
		int mx=0,my=0;
		for(int i = 0; i<m.lenght();i++)
		{
			if(mx<m.getX(i)+m.getW(i))mx=m.getX(i)+m.getW(i);
			if(my<m.getY(i)+m.getH(i))my=m.getY(i)+m.getH(i);
		}
		
		for(int i = 0; i<m.lenght();i++)
		{
			int xe = (int)(((double)m.getX(i)/(double)mx)*(double)w);
			int ye =  (int)(((double)m.getY(i)/(double)my)*(double)h);
			int we = (int)(((double)m.getW(i)/(double)mx)*(double)w);
			int he =  (int)(((double)m.getH(i)/(double)my)*(double)h);
			//System.out.println(xe+";"+ye+": "+((double)my));
			setColor(m.isGradient(i),
					m.isCycled(i)
					,m.getR(i),m.getG(i),m.getB(i),m.getA(i)
					,m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)
					,(int)(((double)m.getX1(i)/(double)mx)*(double)w),	(int)(((double)m.getY1(i)/(double)my)*(double)h)
					,(int)(((double)m.getX2(i)/(double)mx)*(double)w),	(int)(((double)m.getY2(i)/(double)my)*(double)h),
					xe+x,ye+y);

			rotateFigure(centerX,centerY,angle);
			rotateFigure((x+xe)+we/2,(y+ye)+he/2,m.getRadians(i));
		
			
			int[] xx=new int[m.getPointsX(i).length];
			int[] yy=new int[m.getPointsY(i).length];
			for(int j = 0; j<xx.length;j++)
			{
				xx[j]=(int)(((double)m.getPointsX(i)[j]/(double)mx)*(double)w)+x;
				yy[j]=(int)(((double)m.getPointsY(i)[j]/(double)my)*(double)h)+y;
			}
			
			drawFigure(x+xe,y+ye,we,he,m.getType(i),m.fill(i),xx,yy);
			resetRotate((x+xe)+we/2,(y+ye)+he/2,m.getRadians(i));
			
			
		}
		resetRotate(centerX,centerY,angle);
		
	}
	
	
	public default void drawModel(Model m,int x, int y, int w, int h)
	{

	
		
		int mx=0,my=0;
		for(int i = 0; i<m.lenght();i++)
		{
			if(mx<m.getX(i)+m.getW(i))mx=m.getX(i)+m.getW(i);
			if(my<m.getY(i)+m.getH(i))my=m.getY(i)+m.getH(i);
		}
		
		for(int i = 0; i<m.lenght();i++)
		{
			int xe = (int)(((double)m.getX(i)/(double)mx)*(double)w);
			int ye =  (int)(((double)m.getY(i)/(double)my)*(double)h);
			int we = (int)(((double)m.getW(i)/(double)mx)*(double)w);
			int he =  (int)(((double)m.getH(i)/(double)my)*(double)h);
			//System.out.println(xe+";"+ye+": "+((double)my));
			setColor(m.isGradient(i),
					m.isCycled(i)
					,m.getR(i),m.getG(i),m.getB(i),m.getA(i)
					,m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)
					,(int)(((double)m.getX1(i)/(double)mx)*(double)w),	(int)(((double)m.getY1(i)/(double)my)*(double)h)
					,(int)(((double)m.getX2(i)/(double)mx)*(double)w),	(int)(((double)m.getY2(i)/(double)my)*(double)h),
					xe+x,ye+y);
			rotateFigure((x+xe)+we/2,(y+ye)+he/2,m.getRadians(i));
			
		
			
			int[] xx=new int[m.getPointsX(i).length];
			int[] yy=new int[m.getPointsY(i).length];
			for(int j = 0; j<xx.length;j++)
			{
				xx[j]=(int)(((double)m.getPointsX(i)[j]/(double)mx)*(double)w)+x;
				yy[j]=(int)(((double)m.getPointsY(i)[j]/(double)my)*(double)h)+y;
			}
			
			drawFigure(x+xe,y+ye,we,he,m.getType(i),m.fill(i),xx,yy);
			resetRotate((x+xe)+we/2,(y+ye)+he/2,m.getRadians(i));
			
			
		}
		
		
	}
	
	public default void drawSkeleton(Skeleton s, int x, int y)
	{
		
		for(int i = 0; i<s.length; i++)
		{
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getAngle(i));
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getModelAngle(i));
			drawModel(s.getModel(i),x+s.getX(i)+s.getModelX(i),y+s.getY(i)+s.getModelY(i));
			resetRotate(s.getX(i),s.getY(i),s.getAngle(i));
		}
		
	}
	
	public default void drawSkeleton(Skeleton s, int x, int y, int w,int h)
	{
		int maxX=0, maxY=0;
		for(int i = 0; i<s.length;i++)
		{
		Model m = s.getModel(i);
			for(int j =0; j<m.lenght();j++)
			{
				if(maxX<m.getX(j)+m.getW(j))maxX=m.getX(j)+m.getW(j);
				if(maxY<m.getY(j)+m.getH(j))maxY=m.getY(j)+m.getH(j);
			}
		}
		
		
		for(int i = 0; i<s.length; i++)
		{
			
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getAngle(i));
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getModelAngle(i));
			drawModel(s.getModel(i),x+s.getX(i)+s.getModelX(i),y+s.getY(i)+s.getModelY(i),w,h);
			resetRotate(s.getX(i),s.getY(i),s.getAngle(i));
		}
		
	}
	
	public default void drawSkeleton(Skeleton s, int x, int y, double angle)
	{
		int minX=999999999, minY=999999999, maxX=0, maxY=0;
		for(int i = 0; i<s.length;i++)
		{
		Model m = s.getModel(i);
			for(int j =0; j<m.lenght();j++)
			{
				if(minX>m.getX(j))minX=m.getX(j);
				if(minY>m.getY(j))minY=m.getY(j);
				
				if(maxX<m.getX(j)+m.getW(j))maxX=m.getX(j)+m.getW(j);
				if(maxY<m.getY(j)+m.getH(j))maxY=m.getY(j)+m.getH(j);
			}
		}
		for(int i = 0; i<s.length; i++)
		{	
			rotateFigure(x+(minX+maxX)/2,y+(minY+maxY)/2,angle);
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getAngle(i));
			rotateFigure(x+s.getX(i),y+s.getY(i),s.getModelAngle(i));
			
			drawModel(s.getModel(i),x+s.getX(i)+s.getModelX(i),y+s.getY(i)+s.getModelY(i));
			resetRotate(s.getX(i),s.getY(i),s.getAngle(i));
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
		System.out.println(getFileStr(src));
		ret.loadConfiguration(getFileStr(src).replace("¹",""));
		return ret;
	}
	
	public String getFileStr(String src);
	
	
	public void drawFigure(int x, int y,int width, int height,int type, boolean fillFigure, int[]xx, int[]yy);
	public void setColor(boolean isGradient, boolean gradientCycled,int redColor, int greenColor,int blueColor,int alpha,int redColor2, int greenColor2,int blueColor2,int alpha2,int gradientX1, int gradientY1,int gradientX2, int gradientY2, int figureX, int figureY);
	public void rotateFigure(int centerX,int centerY, double radian);
	public void resetRotate(int centerX,int centerY, double radian);
	
}
