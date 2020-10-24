import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Graph extends JPanel{

	public Graph()
	{ createNewModel();
	  createNewSkeleton();
		Timer t = new Timer(10,new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				
			}
			
		});
		
		t.start();
	}
	static String testdata="";
	
	static ArrayList <Model> models = new ArrayList<Model>();
	static ArrayList <Skeleton> bones = new ArrayList<Skeleton>();
	static int nowModel=0;
	static int nowSkele=0;
	static int now=0, nowp=0;
	static int eyeX=200,eyeY=20;
	static boolean isSelectVisible=true, nowPolygon=false,changeModel=false,toNull=false;
	static int left=0,up=0, Shift=1;
	static int timedShiftX=0, timedShiftY=0,timedShiftW=0, timedShiftH=0,timedShiftR=0,timedShiftG=0,timedShiftB=0,timedShiftA=0
			,timedShiftR2=0,timedShiftG2=0,timedShiftB2=0,timedShiftA2=0,timedShiftX1=0,timedShiftY1=0,timedShiftX2=0,timedShiftY2=0,timedShiftPointX=0,timedShiftPointY=0
			,timedShiftModelX=0,timedShiftModelY=0;
	static double timedShiftRadians=0;
	
	static boolean isSkeleton=false;
	public void paintComponent(Graphics g)
	{
		
		countAll();
		drawAll((Graphics2D)g);
		
	}
	
	public void drawAll(Graphics2D g)
	{
		
		if(!isSkeleton)
			{drawModel(g);
		drawWorkspace(g);
			}else
			{
				drawSkeleton(g);
				drawSkelespace(g);
			}
		drawPanel(g);
		drawEffects(g);
		
	}
	
	
	public void drawSkeleton(Graphics2D g)
	{
		g.setColor(new Color(200,200,230));
		g.fillRect(0, 0, 800, 600);
		
		
		g.setColor(new Color(220,220,220));
		for(int i = 0; i<100;i++)
		{
			if(eyeX+64*i-50*64>200&&eyeX+64*i-50*64<800) {
			g.drawLine(eyeX+64*i-50*64, 20, eyeX+64*i-50*64, 400);
			}
		}
		for(int i = 0; i<100;i++)
		{
			if(eyeY+64*i-50*64>20&&eyeY+64*i-50*64<400)
			{
			g.drawLine(200, eyeY+64*i-50*64, 800, eyeY+64*i-50*64);
			
			}
		}
		
		
		
		for(int i = 0; i<nowSkele().length;i++)
		{
			
		
			if(i==now)
			{
				AffineTransform at;
				at = ((Graphics2D) g).getTransform();
				AffineTransform an = (AffineTransform)(at.clone());
				
				if(!toNull)
					an.rotate((!Mistener.right||changeModel)?
							nowSkele().getAngle(i):
								Mistener.getAngle(Mistener.drx, Mistener.dry, eyeX+nowSkele().getX(i),eyeY+ nowSkele().getY(i)),
								eyeX+nowSkele().getX(i)+timedShiftX,eyeY+nowSkele().getY(i)+timedShiftY);
				else
					an.rotate(0);
				
				((Graphics2D) g).setTransform(an);
			
				
				//System.out.println(nowSkele().getY(i));
				drawModel(nowSkele().getModel(i),
						
						nowSkele().getX(i)+timedShiftX+nowSkele().getModelX(i)+timedShiftModelX+eyeX,
						nowSkele().getY(i)+timedShiftY+nowSkele().getModelY(i)+timedShiftModelY+eyeY,
						
						(Mistener.right&&changeModel)?
								Mistener.getAngle(Mistener.drx, Mistener.dry, eyeX+nowSkele().getX(i),eyeY+ nowSkele().getY(i)):
									nowSkele().getModelAngle(i),
									i,g);
				
				if(isSelectVisible) {
					g.setColor(new Color(255,0,0,100));
					g.fillOval(eyeX+nowSkele().getX(i)-10+timedShiftX, eyeY+nowSkele().getY(i)-10+timedShiftY,20,20);
					g.drawPolygon(new int[] {eyeX+nowSkele().getX(i)+timedShiftX,eyeX+nowSkele().getX(i)+10+timedShiftX,eyeX+nowSkele().getX(i)+timedShiftX,eyeX+nowSkele().getX(i)+timedShiftX-10},new int[] {eyeY+ nowSkele().getY(i)+timedShiftY,eyeY+ nowSkele().getY(i)+10+timedShiftY, eyeY+nowSkele().getY(i)+70+timedShiftY, eyeY+nowSkele().getY(i)+10+timedShiftY}, 4);
					g.drawLine(eyeX+nowSkele().getX(i)+timedShiftX,eyeY+ nowSkele().getY(i)+timedShiftY, eyeX+nowSkele().getX(i)+timedShiftX,  eyeY+nowSkele().getY(i)+70+timedShiftY);
					}
				
				((Graphics2D) g).setTransform(at);	
			}else
			{
				
				AffineTransform at;
				at = ((Graphics2D) g).getTransform();
				AffineTransform an = (AffineTransform)(at.clone());
				an.rotate(nowSkele().getAngle(i),eyeX+nowSkele().getX(i),eyeY+nowSkele().getY(i));
			
				((Graphics2D) g).setTransform(an);
				
				drawModel(nowSkele().getModel(i),
						nowSkele().getX(i)+nowSkele().getModelX(i)+eyeX,
						nowSkele().getY(i)+nowSkele().getModelY(i)+eyeY,
						nowSkele().getModelAngle(i),i,g);//
			
				if(isSelectVisible) {
					g.setColor(new Color(0,0,255,100));
					g.fillOval(eyeX+nowSkele().getX(i)-10, eyeY+nowSkele().getY(i)-10,20,20);
					g.drawPolygon(new int[] {eyeX+nowSkele().getX(i),eyeX+nowSkele().getX(i)+10,eyeX+nowSkele().getX(i),eyeX+nowSkele().getX(i)-10},new int[] { eyeY+nowSkele().getY(i),eyeY+ nowSkele().getY(i)+10,eyeY+ nowSkele().getY(i)+70,eyeY+ nowSkele().getY(i)+10}, 4);
					g.drawLine(eyeX+nowSkele().getX(i), eyeY+nowSkele().getY(i), eyeX+nowSkele().getX(i),  eyeY+nowSkele().getY(i)+70);
					}
				
				((Graphics2D) g).setTransform(at);	
			}
			
		}
		
		
		if(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1)
		{
g.setColor(new Color(0,0,255,100));
			
AffineTransform at;
at = ((Graphics2D) g).getTransform();
AffineTransform an = (AffineTransform)(at.clone());
an.rotate(Mistener.getAngle(Mistener.drx,Mistener.dry,Mistener.dx,Mistener.dy),Mistener.dx,Mistener.dy);
			//System.out.println(Mistener.getAngle(Mistener.dx,Mistener.dy,Mistener.drx,Mistener.dy));
			((Graphics2D) g).setTransform(an);
			
			g.fillOval(Mistener.dx-10, Mistener.dy-10,20,20);
			g.drawPolygon(new int[] {Mistener.dx,Mistener.dx+10,Mistener.dx,Mistener.dx-10},new int[] {Mistener.dy,Mistener.dy+10,Mistener.dy+70,Mistener.dy+10}, 4);
			g.drawLine(Mistener.dx, Mistener.dy, Mistener.dx, Mistener.dy+70);

			((Graphics2D) g).setTransform(at);
		}
		
	}

	
	public void drawSkelespace(Graphics2D g)
	{
		
		
		//_назад новая_кость(_вперед) 
	
		g.setColor(new Color(50,50,50));
		g.fillRect(0, 400, 800, 200);
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, 200, 800);
		
		g.setColor(new Color(80,80,80));
		g.fillRect(200, 400, 600, 15);
		
		
		if(onElement(785,400,800,415))
			g.setColor(new Color(110,110,110));else
				g.setColor(new Color(90,90,90));
		g.fillRect(785, 400, 15, 15);
		
		g.setColor(Color.white);
		if(play)
		{
			g.fillRect(787,402,5,10);
			
			g.fillRect(794,402,5,10);
		}
		else
		{
			g.fillPolygon(new int[] {787,798,787}, new int[] {402,(402+413)/2,413},3);
		}
		
		if(!(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1))
		for(int i = 0;i<4;i++)//загрузить_модель удалить_модель режим_работы_с_моделью сделать_кости_невидимыми  
		{
			if(onElement(5+i*50,29,5+i*50+40,29+40))
				g.setColor(new Color(200,200,200));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(5+i*50,29,40,40);
		}
		
		for(int i = 0;i<4;i++)// копировать_кость  _назад новая_кость(_вперед) удалить_кость 
		{
			if(((i==0||i==3)&&now>nowSkele().length-1)||(i==1&&now==0))continue;
			if(onElement(5+i*50,79,5+i*50+40,79+40))
				g.setColor(new Color(200,200,200));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(5+i*50,79,40,40);
		}
		
		if(!(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1)) {
		g.setColor(new Color(0,255,255,100));
		if(isSelectVisible)
		g.drawRect(5+3*50+10,39,20,20);
		}
		
		g.setColor(Color.white);
		
		if(!(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1)) {
		g.fillRect(20, 29+7, 10, 10);//загрузить модель
		g.fillRect(27, 29+20, 10, 10);
		g.fillRect(13, 29+20, 10, 10);
		
		g.fillRect(70, 29+7, 10, 10);//удалить модель
		g.fillRect(77, 29+20, 10, 10);
		g.fillRect(63, 29+20, 10, 10);
		g.drawLine(60,29+5,90,29+35);
		g.drawLine(90,29+5,60,29+35);
		
		
		g.fillRect(120, 29+7, 10, 10);//управление модель
		g.fillRect(127, 29+20, 10, 10);
		g.fillRect(113, 29+20, 10, 10);
		g.drawOval(5+2*50,29,40,40);
		}
		if(now>0)
		g.fillPolygon(new int[] {35+55,5+55,35+55},new int[] {5+79,20+79,35+79},3);//назад
		
		if((now+1>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1)) {
		g.fillRect(110+10, 82, 10, 34);//плюс
		g.fillRect(108, 79+15, 34, 10);
		}
		else
		{
			g.fillPolygon(new int[] {5+105,35+105,5+105},new int[] {5+79,20+79,35+79},3);//вперед
		}
		if(now<nowSkele().length&&nowSkele().length>0){
		g.fillPolygon(new int[] {155+5,155,155+35,155+40}, new int[] {79,79+5,79+40,79+35}, 4);//крест
		g.fillPolygon(new int[] {155+5,155,155+35,155+40}, new int[] {79+40,79+35,79,79+5}, 4);
		
		g.fillRect(10, 84, 25, 25); //копи
		g.setColor(new Color(210,210,210));
		g.fillRect(15, 89, 25, 25);
		}
		if(!(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1))
		for(int i = 0;i<6;i++)//х_кости у_кости направление_кости х_модели у_модели направление_модели
		{
			g.setColor(Color.white);
			g.fillRect(30, 150+i*30, 135, 20);
			
			g.setColor(Color.black);
			switch(i)
			{
			case 0:
				g.drawString(nowSkele().getX(now)+"",30, 165+i*30);
			break;
			
			case 1:
				g.drawString(nowSkele().getY(now)+"",30, 165+i*30);
			break;
			
			case 2:
				g.drawString(
						(Mistener.right?
								(int)(Mistener.getAngle(Mistener.drx, Mistener.dry, nowSkele().getX(now), nowSkele().getY(now))*(180/Math.PI))
								:
									(int)((nowSkele().getAngle(now)*(180/Math.PI))))
									+"",30, 165+i*30);
			break;
			case 3:
				g.drawString(nowSkele().getModelX(now)+"",30, 165+i*30);
				break;
			case 4:
				g.drawString(nowSkele().getModelY(now)+"",30, 165+i*30);
				break;
			case 5:
				g.drawString(
						((int)((nowSkele().getModelAngle(now)*(180/Math.PI))))+"",30, 165+i*30);
				break;
				
			}
			
			
		}
		if(!(now>bones.get(nowSkele).length-1||bones.get(nowSkele).length<1))
		for(int i = 0;i<6;i++)
		{
			if(onElement(5, 150+i*30,25, 170+i*30))
				g.setColor(new Color(230,230,230));
			else
				g.setColor(new Color(200,200,200));
			g.fillRect(5, 150+i*30, 20, 20);
			
			if(onElement(170, 150+i*30,190, 170+i*30))
				g.setColor(new Color(230,230,230));
			else
				g.setColor(new Color(200,200,200));
			g.fillRect(170, 150+i*30, 20, 20);
		
		
			
		}
		
		
		for(int i = 0; i<5;i++)
		{
			if(onElement(200+15*i,400,215+15*i,415))
				g.setColor(new Color(110,110,110));else
					g.setColor(new Color(90,90,90));
			g.fillRect(200+15*i,400,15,15);
		}
		
		g.setColor(Color.white);
		{
			g.fillRect(233, 403, 6, 6);
			g.setColor(new Color(200,200,200));
			g.fillRect(233+3, 403+3, 6, 6);
			g.setColor(Color.white);
		}
		{
			{
				int[] x = {245+0,245+2,245+15,245+13};
				int[] y = {400+2,400+0,400+13,400+15};
				g.fillPolygon(x, y,y.length);
			}
			{
				int[] x = {245+0,245+2,245+15,245+13};
				int[] y = {400+13,400+15,400+2,400+0};
				g.fillPolygon(x, y,y.length);
			}
		}
		{
			int[] x= {212,205,212};
			int[] y= {400,407,415};
			g.fillPolygon(x,y,x.length);
		}
		{
			if(nowSkele<bones.size()-1)
			{
			int[] x= {218,225,218};
			int[] y= {400,407,414};
			g.fillPolygon(x,y,x.length);
			}
			else
			{
				g.fillRect(221, 402, 2, 11);
				g.fillRect(217, 406, 11, 2);
			}
		}
		
		for(int i = 0; i<bones.size()-(55*(int)(nowSkele/55));i++)
		{
			int s;
			if(onElement(200+11*i,415,210+11*i,700)) {s = 20;} else s=0;
			if(i==nowSkele-(55*(int)(nowSkele/55)))
				g.setColor(new Color(160+s,160+s,160+s));
			else
				g.setColor(new Color(130+s,130+s,130+s));
			g.fillRect(200+11*i, 415, 10, 300);
		}
		for(int i = 0; i<bones.size()-(55*(int)(nowSkele/55));i++)
		{
			g.setColor(Color.BLACK);
			g.fillRect(200+11*i+2, 417, 6, 6);
		}
		
		
		
		
		
		if(changeModel) {
			g.setColor(new Color(0,0,0,200));
			g.fillRect(0, 0, 200, 600);
			g.fillRect(200, 400, 800, 200);
			
			if(onElement(438,30,538,60))
				g.setColor(new Color(170,170,170));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(438, 30, 100, 30);
			
			g.setColor(Color.black);
			
			g.drawString("Готово", 468, 50);
			}
		
		
	}
	public void drawEffects(Graphics2D g)
	{
		if(Mistener.drag&&downOnElement(0,0,780,20))
		{
			g.setColor(new Color(100,100,255));
			g.drawRect(Mistener.drx-Mistener.dx,Mistener.dry-Mistener.dy,800,600);
		}
	}
	
	public void drawModel(Graphics2D g)
	{
		g.setColor(new Color(200,200,230));
		g.fillRect(0, 0, 800, 600);
		
		
		g.setColor(new Color(220,220,220));
		for(int i = 0; i<100;i++)
		{
			if(eyeX+64*i-50*64>200&&eyeX+64*i-50*64<800) {
			g.drawLine(eyeX+64*i-50*64, 20, eyeX+64*i-50*64, 400);
			}
		}
		
		for(int i = 0; i<100;i++)
		{
			if(eyeY+64*i-50*64>20&&eyeY+64*i-50*64<400)
			{
			g.drawLine(200, eyeY+64*i-50*64, 800, eyeY+64*i-50*64);
			
			}
		}
		
		if(nowModel().lenght()>0)
		for(int i = 0; i<nowModel().lenght();i++)
		{
			if(!nowModel().isGradient(i))
			g.setColor(new Color(toRange(nowModel().getR(i)+timedCheck((int)(timedShiftR/180f*255f),i),255),toRange(nowModel().getG(i)+timedCheck((int)(timedShiftG/180f*255f),i),255),toRange(nowModel().getB(i)+timedCheck((int)(timedShiftB/180f*255f),i),255),toRange(nowModel().getA(i)+timedCheck((int)(timedShiftA/180f*255f),i),255)));
				else
			{//System.out.println(nowModel().isGradient(i));
				if(i==now&&timedShiftX1!=0)
				g.setPaint(new GradientPaint(eyeX+timedShiftX1+nowModel().getX(now),
						eyeY+timedShiftY1+nowModel().getY(now),getNumColor(i)
						,eyeX+timedShiftX2+nowModel().getX(now),
						eyeY+timedShiftY2+nowModel().getY(now), getNumColor2(i),nowModel().isCycled(i)));
				else 
					g.setPaint(new GradientPaint(eyeX+nowModel().getX(i)+nowModel().getX1(i)+timedCheck(timedShiftX,i),eyeY+nowModel().getY(i)+nowModel().getY1(i)+timedCheck(timedShiftY,i),getNumColor(i)
							,eyeX+nowModel().getX(i)+nowModel().getX2(i)+timedCheck(timedShiftX,i),eyeY+nowModel().getY(i)+nowModel().getY2(i)+timedCheck(timedShiftY,i), getNumColor2(i),nowModel().isCycled(i)));
				}
			
			//AffineTransform at = new AffineTransform();
			
			AffineTransform at;
			at = ((Graphics2D) g).getTransform();
			AffineTransform an = (AffineTransform)(at.clone());
			an.rotate(nowModel().getRadians(i)+timedCheck(timedShiftRadians,i),eyeX+nowModel().getX(i)+nowModel().getW(i)/2+timedCheck(timedShiftX,i),timedCheck(timedShiftY,i)+eyeY+nowModel().getY(i)+nowModel().getH(i)/2);
			((Graphics2D) g).setTransform(an);
		
			
			
			if(nowModel().fill(i))
				switch (nowModel().getType(i)) {
				case 0://System.out.println((eyeX+nowModel().getX(now))+";"+ (eyeY+nowModel().getY(now))+";"+ nowModel().getW(now)+";"+ nowModel().getH(now));
					g.fillRect(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i), timedCheck(timedShiftY,i)+eyeY+nowModel().getY(i), nowModel().getW(i)+timedCheck(timedShiftW,i), nowModel().getH(i)+timedCheck(timedShiftH,i));			
									break;
				case 1:
					g.fillOval(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i),timedCheck(timedShiftY,i)+eyeY+ nowModel().getY(i), nowModel().getW(i)+timedCheck(timedShiftW,i), nowModel().getH(i)+timedCheck(timedShiftH,i));	
					break;
				case 2:
					g.drawLine(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i), timedCheck(timedShiftY,i)+eyeY+nowModel().getY(i), timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i)+nowModel().getW(i)+timedCheck(timedShiftW,i),timedCheck(timedShiftY,i)+ eyeY+nowModel().getY(i)+nowModel().getH(i)+timedCheck(timedShiftH,i));
					break;
				case 3:
					if(nowModel().hasPolygon(i)||(nowModel().getPointsX(now).length>0))
					{
					int[] xe = nowModel().getPointsX(i).clone();
					int[] ye = nowModel().getPointsY(i).clone();
					
					for(int j = 0; j<xe.length;j++)
					{
						xe[j]+=eyeX+timedCheck(timedShiftX,i)+timedCheck(timedShiftPointX,i,j);
						ye[j]+=eyeY+timedCheck(timedShiftY,i)+timedCheck(timedShiftPointY,i,j);
					}
			
					g.fillPolygon(xe, ye, (int)((xe.length+ye.length)/2));
					}
					break;
								}
			else
				switch (nowModel().getType(i)) {
				case 0://System.out.println((eyeX+nowModel().getX(now))+";"+ (eyeY+nowModel().getY(now))+";"+ nowModel().getW(now)+";"+ nowModel().getH(now));
					g.drawRect(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i), timedCheck(timedShiftY,i)+eyeY+nowModel().getY(i), nowModel().getW(i)+timedCheck(timedShiftW,i), nowModel().getH(i)+timedCheck(timedShiftH,i));			
									break;
				case 1:
					g.drawOval(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i),timedCheck(timedShiftY,i)+eyeY+ nowModel().getY(i), nowModel().getW(i)+timedCheck(timedShiftW,i), nowModel().getH(i)+timedCheck(timedShiftH,i));	
					break;
				case 2:
					g.drawLine(timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i), timedCheck(timedShiftY,i)+eyeY+nowModel().getY(i), timedCheck(timedShiftX,i)+eyeX+nowModel().getX(i)+nowModel().getW(i)+timedCheck(timedShiftW,i),timedCheck(timedShiftY,i)+ eyeY+nowModel().getY(i)+nowModel().getH(i)+timedCheck(timedShiftH,i));
					break;
				case 3:
					if(nowModel().hasPolygon(i)||(nowModel().getPointsX(now).length>0))
					{
					int[] xe = nowModel().getPointsX(i).clone();
					int[] ye = nowModel().getPointsY(i).clone();
					
					for(int j = 0; j<xe.length;j++)
					{
						xe[j]+=eyeX+timedCheck(timedShiftX,i)+timedCheck(timedShiftPointX,i,j);
						ye[j]+=eyeY+timedCheck(timedShiftY,i)+timedCheck(timedShiftPointY,i,j);
					}
			
					g.drawPolygon(xe, ye, (int)((xe.length+ye.length)/2));
					}
					break;
								}
				
			((Graphics2D) g).setTransform(at);
		}
			if(isSelectVisible&&!play&&(now<nowModel().lenght()&&nowModel().lenght()>0)) {
				g.setColor(new Color(0,100,255,100));
			if(nowModel().getType(now)!=3) {
			g.drawRect(timedShiftX+eyeX+nowModel().getX(now),timedShiftY+eyeY+nowModel().getY(now), nowModel().getW(now)+timedShiftW, nowModel().getH(now)+timedShiftH);	
			g.fillOval(timedShiftX+eyeX+nowModel().getX(now)+nowModel().getW(now)-5+timedShiftW, timedShiftH+timedShiftY+eyeY+nowModel().getY(now)-5+nowModel().getH(now),10,10);
			g.fillOval(timedShiftX+eyeX+nowModel().getX(now)+nowModel().getW(now)/2+timedShiftW/2, timedShiftY+eyeY+nowModel().getY(now)-25,10,10);
			
			if(nowModel().isGradient(now))
				if(timedShiftX1==0)
					g.drawLine(	eyeX+nowModel().getX(now)+nowModel().getX1(now)+timedCheck(timedShiftX,now),
								eyeY+nowModel().getY(now)+nowModel().getY1(now)+timedCheck(timedShiftY,now),
								eyeX+nowModel().getX(now)+nowModel().getX2(now)+timedCheck(timedShiftX,now),
								eyeY+nowModel().getY(now)+nowModel().getY2(now)+timedCheck(timedShiftY,now));
				else
						g.drawLine(	eyeX+timedShiftX1+nowModel().getX(now),eyeY+timedShiftY1+nowModel().getY(now),eyeX+timedShiftX2+nowModel().getX(now),eyeY+timedShiftY2+nowModel().getY(now));
		
			
			} else 
			{
				g.fillOval(timedShiftX+eyeX+nowModel().getX(now)+nowModel().getW(now)/2+timedShiftW/2, timedShiftY+eyeY+nowModel().getY(now)-25,10,10);
				if(nowModel().isGradient(now))
					if(timedShiftX1==0)
						g.drawLine(	eyeX+nowModel().getX(now)+nowModel().getX1(now)+timedCheck(timedShiftX,now),
									eyeY+nowModel().getY(now)+nowModel().getY1(now)+timedCheck(timedShiftY,now),
									eyeX+nowModel().getX(now)+nowModel().getX2(now)+timedCheck(timedShiftX,now),
									eyeY+nowModel().getY(now)+nowModel().getY2(now)+timedCheck(timedShiftY,now));
					else
							g.drawLine(	eyeX+timedShiftX1+nowModel().getX(now),eyeY+timedShiftY1+nowModel().getY(now),eyeX+timedShiftX2+nowModel().getX(now),eyeY+timedShiftY2+nowModel().getY(now));
			
			
				if(nowModel().hasPolygon(now)||(nowModel().getPointsX(now).length>0))
				{
				int[] xe = nowModel().getPointsX(now).clone();
				int[] ye = nowModel().getPointsY(now).clone();
				
				for(int j = 0; j<xe.length;j++)
				{
					
					xe[j]+=eyeX+timedCheck(timedShiftX,now)+timedCheck(timedShiftPointX,now,j);
					ye[j]+=eyeY+timedCheck(timedShiftY,now)+timedCheck(timedShiftPointY,now,j);
				}
		
				g.drawPolygon(xe, ye, (int)((xe.length+ye.length)/2));
				
				for(int j = 0; j<xe.length;j++)
				{
					g.fillOval(xe[j]-5,ye[j]-5,10,10);	
				if(nowp==j)g.fillOval(xe[j]-8,ye[j]-8,16,16);	
				}
				
				
				}
			
			}
			}
			
		
		g.setColor(new Color(0,0,0,100));
		if(Mistener.nowisno()&&Mistener.click&&Mistener.downOnElement(200,20,800,400))
			g.fillRect(Mistener.dx, Mistener.dy, Mistener.drx-Mistener.dx, Mistener.dry-Mistener.dy);
	}
	
	
	
	
	
	
	public void drawPanel(Graphics g)
	{
	g.setColor(new Color(20,20,20));	
	g.fillRect(0, 0, 800, 20);
	if(onElement(780,0,800,20))
		g.setColor(new Color(255,150,150));
	else g.setColor(new Color(255,0,0));
	g.fillRect(780, 0, 20, 20);
	if(onElement(757,0,777,20))
		g.setColor(new Color(150,150,255));
	else
		g.setColor(new Color(0,0,255));
	g.fillRect(757, 0, 20, 20);
	
	for(int i = 0;i<9;i++)
	{
		if(onElement(i*20, 0, 20+i*20, 20))
			g.setColor(new Color(50,50,50));
		else
			g.setColor(new Color(10,10,10));
	//	System.out.println(g.getColor().getRed());
	g.fillRect(i*20, 0, 20, 20);	
	}
	
	g.setColor(Color.WHITE);//модель
	
	g.fillRect(47, 10, 4, 10);
	g.fillOval(45, 8, 4, 4);
	g.fillOval(49, 8, 4, 4);
	
	
	g.fillRect(5,5,10,15);
	
	g.setColor(Color.BLACK);
	g.fillRect(7, 7, 6, 6);
	
	for(int i = 0; i<3;i++)
	{
		g.setColor(new Color(155+i*50,155+i*50,155+i*50));//модель
		g.fillRect(22+i*3,5,10,15);
		g.setColor(Color.BLACK);
		g.fillRect(24+i*3, 7, 6, 6);
	}
	
	
	
	g.setColor(Color.WHITE);//модель сохр
	g.fillRect(47+60, 10, 4, 10);//кость
	g.fillOval(45+60, 8, 4, 4);
	g.fillOval(49+60, 8, 4, 4);
	
	
	g.fillRect(5+60,5,10,15);
	
	g.setColor(Color.BLACK);
	g.fillRect(7+60, 7, 6, 6);
	
	for(int i = 0; i<3;i++)
	{
		g.setColor(new Color(155+i*50,155+i*50,155+i*50));//модель
		g.fillRect(22+i*3+60,5,10,15);
		g.setColor(Color.BLACK);
		g.fillRect(24+i*3+60, 7, 6, 6);
	}
	
	for(int i = 0; i<3;i++)
	{
		if(onElement((i+3)*20, 0, 20+(i+3)*20, 20))
			g.setColor(Color.green);
		else
			g.setColor(new Color(200,200,200));
		g.fillRect(75+i*20, 15, 4, 4);
	}
	
	
	
	g.setColor(Color.WHITE);//модель сохр
	g.fillRect(47+120, 10, 4, 10);//кость
	g.fillOval(45+120, 8, 4, 4);
	g.fillOval(49+120, 8, 4, 4);
	
	
	g.fillRect(5+120,5,10,15);
	
	g.setColor(Color.BLACK);
	g.fillRect(7+120, 7, 6, 6);
	
	for(int i = 0; i<3;i++)
	{
		g.setColor(new Color(155+i*50,155+i*50,155+i*50));//модель
		g.fillRect(22+i*3+120,5,10,15);
		g.setColor(Color.BLACK);
		g.fillRect(24+i*3+120, 7, 6, 6);
	}
	
	for(int i = 0; i<3;i++)
	{
		if(onElement((i+6)*20, 0, 20+(i+6)*20, 20))
			g.setColor(Color.blue);
		else
			g.setColor(new Color(200,200,200));
		g.fillOval(135+i*20, 15, 4, 4);
	}
	
	
	
	
	}
	
	
	public void drawModel(Model m, int x, int y,double angle,int k,Graphics2D g)
	{
		
		
		AffineTransform at;
		at = ((Graphics2D) g).getTransform();
		AffineTransform an = (AffineTransform)(at.clone());
		an.rotate(angle,eyeX+nowSkele().getX(k),eyeY+nowSkele().getY(k));
		((Graphics2D) g).setTransform(an);
	for(int i = 0; i<m.lenght();i++)
	{
		if(m.isGradient(i))
			g.setPaint(new GradientPaint(m.getX(i)+m.getX1(i)+x,m.getY(i)+m.getY1(i)+y,new Color(m.getR(i),m.getG(i),m.getB(i),m.getA(i))
					,m.getX(i)+m.getX2(i)+x,m.getY(i)+m.getY2(i)+y, new Color(m.getR2(i),m.getG2(i),m.getB2(i),m.getA2(i)),m.isCycled(i)));
	
		else
			g.setColor(new Color(m.getR(i),m.getG(i),m.getB(i),m.getA(i)));
		
		
		
		AffineTransform at2;
		at2 = ((Graphics2D) g).getTransform();
		AffineTransform an2 = (AffineTransform)(at2.clone());
		an2.rotate(m.getRadians(i),x+(m.getX(i)+(m.getW(i)/2)),y+(m.getY(i))+(m.getH(i)/2));
		((Graphics2D) g).setTransform(an2);
		
	
		switch(m.getType(i))
		{
		case 0:
			if(m.fill(i))
				g.fillRect(m.getX(i)+x, m.getY(i)+y, m.getW(i), m.getH(i));else
				g.drawRect(m.getX(i)+x, m.getY(i)+y, m.getW(i), m.getH(i));
			break;
		case 1:
			if(m.fill(i))
				g.fillOval(m.getX(i)+x, m.getY(i)+y, m.getW(i), m.getH(i));else
				g.drawOval(m.getX(i)+x, m.getY(i)+y, m.getW(i), m.getH(i));
			break;
		case 2:
				g.drawLine(m.getX(i)+x, m.getY(i)+y, m.getW(i)+m.getX(i)+x, m.getH(i)+m.getY(i)+y);
			break;
			
		case 3:
			if(m.hasPolygon(i)||(m.getPointsX(now).length>0))
			{
			int[] xe = m.getPointsX(i).clone();
			int[] ye = m.getPointsY(i).clone();
			
			for(int j = 0; j<xe.length;j++)
			{
				xe[j]+=x;
				ye[j]+=y;
			}
			if(m.fill(i))
				g.fillPolygon(xe, ye, (int)((xe.length+ye.length)/2));
			else
				g.drawPolygon(xe, ye, (int)((xe.length+ye.length)/2));
			}
			break;
		}
		g.setTransform(at2);
	}
	((Graphics2D) g).setTransform(at);
	}
	
	
	public void drawWorkspace(Graphics2D g)
	{//square,oval,line,polygon
		
		
		
		
		
		g.setColor(new Color(50,50,50));
		g.fillRect(0, 400, 800, 200);
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, 200, 800);
	
		
		if(nowModel().lenght()>0&&nowModel().lenght()>now)
		{
	for(int i = 0; i<4;i++)
	{
		
		if(models.size()>0&&nowModel().getType(now)==i)
			g.setColor(new Color(50,50,50));
		else
		{
			if(onElement(5+50*i,29,5+50*i+40,29+40))
				g.setColor(new Color(200,200,200));
				else
					g.setColor(new Color(150,150,150));
		}
		g.fillRect(5+50*i,29,40,40);
		g.setColor(new Color(255,255,255));
		switch(i)
		{
case 0:
	g.drawRect(15+50*i,39,20,20);
			break;
case 1:
	g.drawOval(10+50*i,33,30,30);
	break;
case 2:
	g.drawLine(12+50*i,39,17+50*i+20,39+20);
	break;
case 3:
	int[] x= {10,35,30,25,15,3};
	int[] y= {5,7,16,30,35,23};
	for(int j = 0; j<6;j++)
	{
		x[j]+=5+50*i;
		y[j]+=29;
	}

	g.drawPolygon(x,y,6);
		
	break;
		}
	}
	
	if(onElement(5,80,45,120))
		g.setColor(new Color(200,200,200));
	else
	g.setColor(Color.white);
	g.fillRect(5, 80, 40, 40);
	g.setColor(Color.black);
	if(nowModel().fill(now))
		g.fillRect(15, 90, 20, 20);
	else
		g.drawRect(15, 90, 20, 20);
	
	if(onElement(55,80,95,120))
		g.setColor(new Color(200,200,200));
	else
	g.setColor(Color.white);
	g.fillRect(55, 80, 40, 40);
	if(isSelectVisible)
	{
		g.setColor(new Color(0,100,255));
		g.drawRect(65,90,20,20);
	}
	
	
	if(nowModel().isGradient(now)){
	if(onElement(155,80,195,120))
		g.setColor(new Color(200,200,200));
	else
	g.setColor(Color.white);
	
	

	g.fillRect(155, 80, 40, 40);
		g.setPaint(new GradientPaint(160,0,new Color(100,100,100),175,0,new Color(200,200,200),nowModel().isCycled(now)));
	
	g.fillRect(160, 85, 30, 30);
	}
	if(onElement(105,80,145,120))
		g.setColor(new Color(200,200,200));
	else
	g.setColor(Color.white);
	g.fillRect(105, 80, 40, 40);
	
	if(!nowModel().isGradient(now))
		g.setPaint(new GradientPaint(110,0,new Color(100,100,100),135,0,new Color(200,200,200)));
	else
		g.setColor(new Color(100,100,100));
	g.fillRect(110, 85, 30, 30);
	
	for(int i = 0; i<4;i++)
	{
		if(i==0)
			g.setColor(new Color(toRange(nowModel().getR(now)+(int)(timedShiftR/180f*255f),255),0,0));
		if(i==1)
			g.setColor(new Color(0,toRange(nowModel().getG(now)+(int)(timedShiftG/180f*255f),255),0));
			if(i==2)
				g.setColor(new Color(0,0,toRange(nowModel().getB(now)+(int)(timedShiftB/180f*255f),255)));
			if(i==3)
				g.setColor(new Color(0,0,0,toRange(nowModel().getA(now)+(int)(timedShiftA/180f*255f),255)));
		g.fillRect(5,315+i*30,190,10);
		
		
		//g.setColor(Color.white);
		if(i ==0) {
			if(onElement(5+rx,313+i*30,10+5+rx,14+313+i*30))
				g.setColor(new Color(200,200,200));
			else
				g.setColor(Color.WHITE);
			g.fillRect(5+rx,313+i*30,10,14);
			g.drawString(toRange((int)((float)nowModel().getR(now)+(float)timedShiftR/180f*255f),255)+"",2+rx,308+i*30);
		}
			if(i ==1)
			{
				if(onElement(5+gx,313+i*30,10+5+gx,14+313+i*30))
					g.setColor(new Color(200,200,200));
				else
					g.setColor(Color.WHITE);
				g.fillRect(5+gx,313+i*30,10,14);
				g.drawString(toRange((int)((float)nowModel().getG(now)+(float)timedShiftG/180f*255f),255)+"",2+gx,308+i*30);
			}
			if(i ==2) {
				if(onElement(5+bx,313+i*30,10+5+bx,14+313+i*30))
					g.setColor(new Color(200,200,200));
				else
					g.setColor(Color.WHITE);
				g.fillRect(5+bx,313+i*30,10,14);
				g.drawString(toRange((int)((float)nowModel().getB(now)+(float)timedShiftB/180f*255f),255)+"",2+bx,308+i*30);
			}
			if(i ==3) {
				if(onElement(5+ax,313+i*30,10+5+ax,14+313+i*30))
					g.setColor(new Color(200,200,200));
				else
					g.setColor(Color.WHITE);
				g.fillRect(5+ax,313+i*30,10,14);
				g.drawString(toRange((int)((float)nowModel().getA(now)+(float)timedShiftA/180f*255f),255)+"",2+ax,308+i*30);
			}
									
			
	}
	
	
	
	if(nowModel().isGradient(now))
		for(int i = 0; i<4;i++)
		{
			if(i==0)
				g.setColor(new Color(toRange(nowModel().getR2(now)+(int)(timedShiftR2/180f*255f),255),0,0));
			if(i==1)
				g.setColor(new Color(0,toRange(nowModel().getG2(now)+(int)(timedShiftG2/180f*255f),255),0));
				if(i==2)
					g.setColor(new Color(0,0,toRange(nowModel().getB2(now)+(int)(timedShiftB2/180f*255f),255)));
				if(i==3)
					g.setColor(new Color(0,0,0,toRange(nowModel().getA2(now)+(int)(timedShiftA2/180f*255f),255)));
			g.fillRect(5,435+i*30,190,10);
			
			
			//g.setColor(Color.white);
			if(i ==0) {
				if(onElement(5+rx2,433+i*30,10+5+rx2,14+433+i*30))
					g.setColor(new Color(200,200,200));
				else
					g.setColor(Color.WHITE);
				g.fillRect(5+rx2,433+i*30,10,14);
				g.drawString(toRange((int)((float)nowModel().getR2(now)+(float)timedShiftR2/180f*255f),255)+"",2+rx2,428+i*30);
			}
				if(i ==1)
				{
					if(onElement(5+gx2,433+i*30,10+5+gx2,14+433+i*30))
						g.setColor(new Color(200,200,200));
					else
						g.setColor(Color.WHITE);
					g.fillRect(5+gx2,433+i*30,10,14);
					g.drawString(toRange((int)((float)nowModel().getG2(now)+(float)timedShiftG2/180f*255f),255)+"",2+gx2,428+i*30);
				}
				if(i ==2) {
					if(onElement(5+bx2,433+i*30,10+5+bx2,14+433+i*30))
						g.setColor(new Color(200,200,200));
					else
						g.setColor(Color.WHITE);
					g.fillRect(5+bx2,433+i*30,10,14);
					g.drawString(toRange((int)((float)nowModel().getB2(now)+(float)timedShiftB2/180f*255f),255)+"",2+bx2,428+i*30);
				}
				if(i ==3) {
					if(onElement(5+ax2,433+i*30,10+5+ax2,14+433+i*30))
						g.setColor(new Color(200,200,200));
					else
						g.setColor(Color.WHITE);
					g.fillRect(5+ax2,433+i*30,10,14);
					g.drawString(toRange((int)((float)nowModel().getA2(now)+(float)timedShiftA2/180f*255f),255)+"",2+ax2,428+i*30);
				}
										
				
		}
	
		}
		
		if(now>0) {
			if(onElement(55,128,95,168))
				g.setColor(new Color(200,200,200));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(55,128,40,40);
			}
		
		if(onElement(105,128,145,168))
			g.setColor(new Color(200,200,200));
			else
				g.setColor(new Color(150,150,150));
		g.fillRect(105,128,40,40);
		
		
		if(nowModel().lenght()>0&&now<nowModel().lenght()) {
			if(onElement(5,128,45,168))
				g.setColor(new Color(200,200,200));
				else
					g.setColor(new Color(150,150,150));
			g.fillRect(5,128,40,40);
			}
		
		
		if(nowModel().lenght()>0&&now<nowModel().lenght()) {
			if(onElement(155,128,195,168))
				g.setColor(new Color(200,200,200));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(155,128,40,40);
			}
		
		
		g.setColor(Color.WHITE);
		if(now+2>nowModel().lenght())
		{g.fillRect(120, 128+5, 10, 30); g.fillRect(110, 128+15, 30, 10);}
		else
		{
			int[] x = {105+5,105+35,105+5};
			int[] y = {128+5,128+20,128+35};
			g.fillPolygon(x, y, 3);
		}
	
		
		if(now>0)
		{
			int[] x = {55+35,55+5,55+35};
			int[] y = {128+5,128+20,128+35};
			g.fillPolygon(x, y, 3);
		}
		
		if(nowModel().lenght()>0&&now<nowModel().lenght())
		{
			{
				int[] x = {155+0,155+5,155+40,155+35};
				int[] y = {128+5,128+0,128+35,128+40};
				g.fillPolygon(x, y, 4);
			}
			
			int[] x = {155+0,155+5,155+40,155+35};
			int[] y = {128+35,128+40,128+5,128+0};
			g.fillPolygon(x, y, 4);
			
		}
		
		if(nowModel().lenght()>0&&now<nowModel().lenght())
		{
			g.fillRect(10, 133, 25, 25);
			g.setColor(new Color(210,210,210));
			g.fillRect(15,137,25,25);
		}
		
		
		if(nowModel().lenght()>0&&now<nowModel().lenght())
			if(nowModel().getType(now)!=3)
				for(int i = 0; i<4; i++)
				{
					if(onElement(5, 188+30*i, 25, 188+30*i+20))
						g.setColor(new Color(240,240,240));
					else
						g.setColor(new Color(200,200,200));
					
					g.fillRect(5, 188+30*i, 20, 20);
					if(onElement(170, 188+30*i, 190, 188+30*i+20))
						g.setColor(new Color(240,240,240));
					else
						g.setColor(new Color(200,200,200));
				
				g.fillRect(170, 188+30*i, 20, 20);
				g.setColor(Color.white);
				g.fillRect(30, 188+30*i, 135, 20);
				g.setColor(Color.black);
				switch(i)
				{
				case 0:
					g.drawString((nowModel().getX(now)+timedShiftX)+"", 30, 203+30*i);
					break;
				case 1:
					g.drawString((nowModel().getY(now)+timedShiftY)+"", 30, 203+30*i);
					break;
				case 2:
					g.drawString((nowModel().getW(now)+timedShiftW)+"", 30, 203+30*i);
					break;
				case 3:
					g.drawString((nowModel().getH(now)+timedShiftH)+"", 30, 203+30*i);
					break;
				}
			}else {
			
				for(int i = 0; i<2; i++)
				{
					if(onElement(5, 188+30*i, 25, 188+30*i+20))
						g.setColor(new Color(240,240,240));
					else
						g.setColor(new Color(200,200,200));
					
					g.fillRect(5, 188+30*i, 20, 20);
					if(onElement(170, 188+30*i, 190, 188+30*i+20))
						g.setColor(new Color(240,240,240));
					else
						g.setColor(new Color(200,200,200));
				
				g.fillRect(170, 188+30*i, 20, 20);
				g.setColor(Color.white);
				g.fillRect(30, 188+30*i, 135, 20);
				g.setColor(Color.black);
				if(nowModel().hasPolygon(now))
				switch(i)
				{
				case 0:
					g.drawString((nowModel().getPointsX(now)[nowp]+timedShiftPointX)+"", 30, 203+30*i);
					break;
				case 1:
					g.drawString((nowModel().getPointsY(now)[nowp]+timedShiftPointY)+"", 30, 203+30*i);
					break;
				}
			
				}
				for(int i = 0; i<4; i++) {
					if(i==1&&nowp==0)i++;
				if(onElement(5+i*50, 245, 45+i*50, 285))
					g.setColor(new Color(200,200,200));
				else
					g.setColor(new Color(150,150,150));
				
				g.fillRect(5+i*50, 245, 40, 40);
				if(nowp<=0&&i==2)i++;
				}
				g.setColor(Color.white);
				
				if(nowp+2>nowModel().getPointsY(now).length)
				{g.fillRect(120, 245+5, 10, 30); g.fillRect(110, 245+15, 30, 10);}
				else
				{
					int[] x = {105+5,105+35,105+5};
					int[] y = {245+5,245+20,245+35};
					g.fillPolygon(x, y, 3);
				}
			
				
				if(nowp>0){
				{
					int[] x = {55+35,55+5,55+35};
					int[] y = {245+5,245+20,245+35};
					g.fillPolygon(x, y, 3);
				}
				
				{
					int[] x = {155+0,155+5,155+40,155+35};
					int[] y = {245+5,245+0,245+35,245+40};
					g.fillPolygon(x, y, 4);
				}
				}
				if(nowp>0) {
				int[] x = {155+0,155+5,155+40,155+35};
				int[] y = {245+35,245+40,245+5,245+0};
				g.fillPolygon(x, y, 4);
				}
				g.fillRect(10, 250, 25, 25);
				g.setColor(new Color(210,210,210));
				g.fillRect(15,254,25,25);
				
		}
		
		if(nowPolygon) {
			g.setColor(new Color(0,0,0,200));
			g.fillRect(0, 0, 200, 600);
			g.fillRect(200, 400, 800, 200);
			
			if(onElement(438,30,538,60))
				g.setColor(new Color(170,170,170));
			else
				g.setColor(new Color(150,150,150));
			g.fillRect(438, 30, 100, 30);
			
			g.setColor(Color.black);
			
			g.drawString("Готово", 468, 50);
			}
		
		
		g.setColor(new Color(80,80,80));
		g.fillRect(200, 400, 600, 15);
		//копирование смещение(2) удаление добавление
		
		//play
		if(onElement(785,400,800,415))
			g.setColor(new Color(110,110,110));else
				g.setColor(new Color(90,90,90));
		g.fillRect(785, 400, 15, 15);
		
		g.setColor(Color.white);
		if(play)
		{
			g.fillRect(787,402,5,10);
			
			g.fillRect(794,402,5,10);
		}
		else
		{
			g.fillPolygon(new int[] {787,798,787}, new int[] {402,(402+413)/2,413},3);
		}
		
		for(int i = 0; i<5;i++)
		{
			if(onElement(200+15*i,400,215+15*i,415))
				g.setColor(new Color(110,110,110));else
					g.setColor(new Color(90,90,90));
			g.fillRect(200+15*i,400,15,15);
		}
		
		g.setColor(Color.white);
		{
			g.fillRect(233, 403, 6, 6);
			g.setColor(new Color(200,200,200));
			g.fillRect(233+3, 403+3, 6, 6);
			g.setColor(Color.white);
		}
		{
			{
				int[] x = {245+0,245+2,245+15,245+13};
				int[] y = {400+2,400+0,400+13,400+15};
				g.fillPolygon(x, y,y.length);
			}
			{
				int[] x = {245+0,245+2,245+15,245+13};
				int[] y = {400+13,400+15,400+2,400+0};
				g.fillPolygon(x, y,y.length);
			}
		}
		{
			int[] x= {212,205,212};
			int[] y= {400,407,415};
			g.fillPolygon(x,y,x.length);
		}
		{
			if(nowModel<models.size()-1)
			{
			int[] x= {218,225,218};
			int[] y= {400,407,414};
			g.fillPolygon(x,y,x.length);
			}
			else
			{
				g.fillRect(221, 402, 2, 11);
				g.fillRect(217, 406, 11, 2);
			}
		}
		
		for(int i = 0; i<models.size()-(55*(int)(nowModel/55));i++)
		{
			int s;
			if(onElement(200+11*i,415,210+11*i,700)) {s = 20;} else s=0;
			if(i==nowModel-(55*(int)(nowModel/55)))
				g.setColor(new Color(160+s,160+s,160+s));
			else
				g.setColor(new Color(130+s,130+s,130+s));
			g.fillRect(200+11*i, 415, 10, 300);
		}
		for(int i = 0; i<models.size()-(55*(int)(nowModel/55));i++)
		{
			g.setColor(Color.BLACK);
			g.fillRect(200+11*i+2, 417, 6, 6);
		}
		
		
		//новая модель новая анимация новая_кость  сохранить модель  сохранить анимацию сохраниить скелет
		

		
	}
	
	static int rx=0,rt=0;
	static int bx=0,bt=0;
	static int gx=0,gt=0;
	static int ax=0,at=0;
	
	static int rx2=0,rt2=0;
	static int bx2=0,bt2=0;
	static int gx2=0,gt2=0;
	static int ax2=0,at2=0;
	static boolean play=false;
	private int getMin(int[] n)
	{
		int m=999999999;
		for(int i = 0; i<n.length;i++)
		if(m>n[i])m=n[i];
		return m;
	}
	private int getMax(int[] n)
	{
		int m=0;
		for(int i = 0; i<n.length;i++)
		if(m<n[i])m=n[i];
		return m;
		
	}
	
	public void countAll()
	{
		
		if(Mistener.click&&isSkeleton)
		for(int i = 0; i<6;i++)
		{
			if(onElement(5, 150+i*30,25, 170+i*30))
				switch(i)
				{
				case 0:
					Graph.nowSkele().setX(Graph.now, Graph.nowSkele().getX(Graph.now)-1);
					break;
				case 1:
					Graph.nowSkele().setY(Graph.now, Graph.nowSkele().getY(Graph.now)-1);
					break;
				case 2:
					Graph.nowSkele().setAngle(Graph.now, Graph.nowSkele().getAngle(Graph.now)-Math.PI/180);
					break;
				case 3:
					Graph.nowSkele().setModelX(Graph.now, Graph.nowSkele().getModelX(Graph.now)-1);
					break;
				case 4:
					Graph.nowSkele().setModelY(Graph.now, Graph.nowSkele().getModelY(Graph.now)-1);
					break;
				case 5:
					Graph.nowSkele().setModelAngle(Graph.now, Graph.nowSkele().getModelAngle(Graph.now)-Math.PI/180);
					break;
				}
			
			if(onElement(170, 150+i*30,190, 170+i*30))
				switch(i)
				{
				case 0:
					Graph.nowSkele().setX(Graph.now, Graph.nowSkele().getX(Graph.now)+1);
					break;
				case 1:
					Graph.nowSkele().setY(Graph.now, Graph.nowSkele().getY(Graph.now)+1);
					break;
				case 2:
					Graph.nowSkele().setAngle(Graph.now, Graph.nowSkele().getAngle(Graph.now)-Math.PI/180);
					break;
				case 3:
					Graph.nowSkele().setModelX(Graph.now, Graph.nowSkele().getModelX(Graph.now)+1);
					break;
				case 4:
					Graph.nowSkele().setModelY(Graph.now, Graph.nowSkele().getModelY(Graph.now)+1);
					break;
				case 5:
					Graph.nowSkele().setModelAngle(Graph.now, Graph.nowSkele().getModelAngle(Graph.now)-Math.PI/180);
					break;
				}
		}
		
		
		if(play) {if(System.currentTimeMillis()%2==0) {nowModel++;nowSkele++;}}
		
		if(nowSkele<0)nowSkele=bones.size()-1;
		if(nowSkele>bones.size()-1)nowSkele=0;
		
		
		if(nowModel<0)nowModel=models.size()-1;
		if(nowModel>models.size()-1)nowModel=0;
		if(nowModel().lenght()>0&&nowModel().lenght()>now) {
		if(nowModel().getType(now)==3&&nowModel().hasPolygon(now))
		{
			nowModel().setX(getMin(nowModel().getPointsX(now)),now);
			nowModel().setY(getMin(nowModel().getPointsY(now)),now);
			nowModel().setW(getMax(nowModel().getPointsX(now))-getMin(nowModel().getPointsX(now)),now);
			nowModel().setH(getMax(nowModel().getPointsY(now))-getMin(nowModel().getPointsY(now)),now);
		}
		}
		if(nowModel().lenght()>now&&(!nowModel().hasPolygon(now)&&nowModel().getType(now)==3)&&!isSkeleton)nowPolygon=true;
		
		if(nowModel().lenght()>0&&nowModel().lenght()>now)
		{
			rx=(int)(180f*((float)nowModel().getR(now)/255f))+timedShiftR;
			bx=(int)(180f*((float)nowModel().getB(now)/255f))+timedShiftB;
			gx=(int)(180f*((float)nowModel().getG(now)/255f))+timedShiftG;
			ax=(int)(180f*((float)nowModel().getA(now)/255f))+timedShiftA;
			
			
			rt=(int)(180f*((float)nowModel().getR(now)/255f));
			bt=(int)(180f*((float)nowModel().getB(now)/255f));
			gt=(int)(180f*((float)nowModel().getG(now)/255f));
			at=(int)(180f*((float)nowModel().getA(now)/255f));
			
			if(rx<0)rx=0;
			if(rx>180)rx=180;
			if(bx<0)bx=0;
			if(bx>180)bx=180;
			if(gx<0)gx=0;
			if(gx>180)gx=180;
			ax=toRange(ax,180);
			ax2=toRange(ax2,180);
			
			rx2=(int)(180f*((float)nowModel().getR2(now)/255f))+timedShiftR2;
			bx2=(int)(180f*((float)nowModel().getB2(now)/255f))+timedShiftB2;
			gx2=(int)(180f*((float)nowModel().getG2(now)/255f))+timedShiftG2;
			ax2=(int)(180f*((float)nowModel().getA2(now)/255f))+timedShiftA2;
			
			
			rt2=(int)(180f*((float)nowModel().getR2(now)/255f));
			bt2=(int)(180f*((float)nowModel().getB2(now)/255f));
			gt2=(int)(180f*((float)nowModel().getG2(now)/255f));
			at2=(int)(180f*((float)nowModel().getA2(now)/255f));
			
			if(rx<0)rx2=0;
			if(rx2>180)rx2=180;
			if(bx2<0)bx2=0;
			if(bx2>180)bx2=180;
			if(gx2<0)gx2=0;
			if(gx2>180)gx2=180;
			ax2=toRange(ax2,180);
			
			
		}
		
		eyeX-=left*Shift;
		eyeY-=up*Shift;
		
		if(Mistener.click&&!isSkeleton)
		for(int i = 0; i<4; i++)
		{
			if(onElement(5, 188+30*i, 25, 188+30*i+20))
				switch(i)
				{
				case 0:
					if(nowModel().getType(now)==3)
						nowModel().setPointX(nowModel().getPointsX(now)[nowp]-1,now, nowp);
					else
						nowModel().setX(nowModel().getX(now)-1, now);
					break;
				case 1:
					if(nowModel().getType(now)==3)
						nowModel().setPointY(nowModel().getPointsY(now)[nowp]-1,now, nowp);
					else
					nowModel().setY(nowModel().getY(now)-1, now);
					break;
				case 2:
					nowModel().setW(nowModel().getW(now)-1, now);
					break;
				case 3:
					nowModel().setH(nowModel().getH(now)-1, now);
					break;
				}
			if(onElement(170, 188+30*i, 190, 188+30*i+20))	
				switch(i)
				{
				case 0:
					if(nowModel().getType(now)==3)
						nowModel().setPointX(nowModel().getPointsX(now)[nowp]+1,now, nowp);
					else
						nowModel().setX(nowModel().getX(now)+1, now);
					break;
				case 1:
					if(nowModel().getType(now)==3)
						nowModel().setPointY(nowModel().getPointsY(now)[nowp]+1,now, nowp);
					else
					nowModel().setY(nowModel().getY(now)+1, now);
					break;
				case 2:
					nowModel().setW(nowModel().getW(now)+1, now);
					break;
				case 3:
					nowModel().setH(nowModel().getH(now)+1, now);
					break;
				}
			
		}
		
		
	
		
	}
	
	private boolean onElement(int x1, int y1, int x2, int y2)
	{
		return (Mistener.mx>=x1&&Mistener.mx<=x2&&Mistener.my>=y1&&Mistener.my<=y2);
	}
	
	public boolean downOnElement(int x1, int y1, int x2, int y2)
	{
		return (Mistener.dx>=x1&&Mistener.dx<=x2&&Mistener.dy>=y1&&Mistener.dy<=y2);
	}
	
	static Model nowModel()
	{
		return models.get(nowModel);
	}
	
	static Model getModel(int i)
	{
		return models.get(i);
	}
	
	private void createNewModel()
	{
		if(models.size()>0)nowModel++;
		models.add(new Model());
	}
	
	private void createNewSkeleton()
	{
		if(bones.size()>0)nowSkele++;
		bones.add(new Skeleton());
	}
	
	private int timedCheck(int n, int i)
	{
		if(i==now)return n; else return 0;
	}
	
	private double timedCheck(double n, int i)
	{
		if(i==now)return n; else return 0;
	}
	
	private double timedCheck(double n, int i,int j)
	{
		if(i==now&&j==nowp)return n; else return 0;
	}
	
	private int toRange(int num, int range)
	{
		if(num<0)return 0;
		if(num>range)return range;
		return num;
		
	}
	
	private Color getNumColor(int i)
	{
		return new Color(toRange(nowModel().getR(i)+timedCheck((int)(timedShiftR/180f*255f),i),255),toRange(nowModel().getG(i)+timedCheck((int)(timedShiftG/180f*255f),i),255),toRange(nowModel().getB(i)+timedCheck((int)(timedShiftB/180f*255f),i),255),toRange(nowModel().getA(i)+timedCheck((int)(timedShiftA/180f*255f),i),255));		
	}
	
	private Color getNumColor2(int i)
	{
		return new Color(toRange(nowModel().getR2(i)+timedCheck((int)(timedShiftR2/180f*255f),i),255),toRange(nowModel().getG2(i)+timedCheck((int)(timedShiftG2/180f*255f),i),255),toRange(nowModel().getB2(i)+timedCheck((int)(timedShiftB2/180f*255f),i),255),toRange(nowModel().getA2(i)+timedCheck((int)(timedShiftA2/180f*255f),i),255));		
	}
	
	public static Skeleton nowSkele()
	{
		return bones.get(nowSkele);
	}
	
}
