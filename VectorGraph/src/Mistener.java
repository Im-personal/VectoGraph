import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Mistener implements MouseMotionListener, MouseListener, KeyListener{

	public static int mx=0,my=0;
	public static int dx=0,dy=0,ux=0,uy=0,drx=0,dry=0,cx=0,cy=0;
	static boolean click = false;
	static boolean drag=false,newdrag=false;
	static boolean tik = false;
	static int wfrx=0,wfry=0;
	static int timedShiftX=0;
	static int timedShiftY=0;
	static boolean right=false;
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		drx=e.getX();
		dry=e.getY();
		
		
		
		if(!Graph.nowPolygon&&!Graph.changeModel) {
		if(!right)
		{
		if(nowisno())newdrag=true;
		else
			if(downOnElement(getX()+getW()-5+Graph.eyeX,getY()+getH()-5+Graph.eyeY,getX()+getW()+5+Graph.eyeX,getY()+getH()+5+Graph.eyeY))
			{
				Graph.timedShiftH=dry-dy;
				Graph.timedShiftW=drx-dx;
			}
			else
				if(downOnElement(timedShiftX+Graph.eyeX+getX()+getW()/2+Graph.timedShiftW/2, Graph.timedShiftY+Graph.eyeY+getY()-25,timedShiftX+Graph.eyeX+getX()+getW()/2+Graph.timedShiftW/2+10, Graph.timedShiftY+Graph.eyeY+getY()-25+10))
				{
				
					Graph.timedShiftRadians=getAngle(Graph.eyeX+getX()+getW()/2,Graph.eyeY+getY()+getH()/2,drx,dry);
					
				}	
			else
		if(downOnElement(200,20,800,400)) {
			
			Graph.timedShiftY=dry-dy;
			Graph.timedShiftX=drx-dx;
			}
		
		
		if(downOnElement(5+Graph.rt,313+0*30,10+5+Graph.rt,14+313+0*30))
		{Graph.timedShiftR=drx-dx;}
	if(downOnElement(5+Graph.gt,313+1*30,10+5+Graph.gt,14+313+1*30))
		Graph.timedShiftG=drx-dx;
	if(downOnElement(5+Graph.bt,313+2*30,10+5+Graph.bt,14+313+2*30))
		Graph.timedShiftB=drx-dx;
	if(downOnElement(5+Graph.at,313+3*30,10+5+Graph.at,14+313+3*30))
		Graph.timedShiftA=drx-dx;
	
	
	if(Graph.nowModel().lenght()>0&&Graph.now<Graph.nowModel().lenght())
		if(Graph.nowModel().isGradient(Graph.now))
	{
		if(downOnElement(5+Graph.rt2,433+0*30,10+5+Graph.rt2,14+433+0*30))
		{Graph.timedShiftR2=drx-dx;}
	if(downOnElement(5+Graph.gt2,433+1*30,10+5+Graph.gt2,14+433+1*30))
		Graph.timedShiftG2=drx-dx;
	if(downOnElement(5+Graph.bt2,433+2*30,10+5+Graph.bt2,14+433+2*30))
		Graph.timedShiftB2=drx-dx;
	if(downOnElement(5+Graph.at2,433+3*30,10+5+Graph.at2,14+433+3*30))
		Graph.timedShiftA2=drx-dx;
	
	}
	
	if(Graph.nowModel().lenght()>0&&Graph.now<Graph.nowModel().lenght()&&Graph.nowModel().getType(Graph.now)==3&&Graph.nowModel().hasPolygon(Graph.now))
	{
		for(int i = 0; i<Graph.nowModel().getPointsX(Graph.now).length;i++)
		{
			if(downOnElement(Graph.eyeX+Graph.nowModel().getPointsX(Graph.now)[i]-10,Graph.eyeY+Graph.nowModel().getPointsY(Graph.now)[i]-10,Graph.eyeX+Graph.nowModel().getPointsX(Graph.now)[i]+10,Graph.eyeY+Graph.nowModel().getPointsY(Graph.now)[i]+10))
			{Graph.timedShiftPointX=drx-dx; Graph.timedShiftPointY=dry-dy; Graph.timedShiftX=0;Graph.nowp=i;Graph.timedShiftY=0;}
		}
	}
		
		drag=true;
		
		}
		else
			if(Graph.nowModel().isGradient(Graph.now))
		{
			
			Graph.timedShiftX1=dx-Graph.eyeX-Graph.nowModel().getX(Graph.now);
			Graph.timedShiftY1=dy-Graph.eyeY-Graph.nowModel().getY(Graph.now);
			
			Graph.timedShiftX2=e.getX()-Graph.eyeX-Graph.nowModel().getX(Graph.now);
			Graph.timedShiftY2=e.getY()-Graph.eyeY-Graph.nowModel().getY(Graph.now);
		}
		}else {
			if(right)
			Graph.nowModel().addPoints(e.getX()-Graph.eyeX, e.getY()-Graph.eyeY, Graph.now);
			
			if(!right)
			{
			Graph.timedShiftModelX=drx-dx;
			Graph.timedShiftModelY=dry-dy;
			}
		}
		
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
		
	//System.out.println(click);
		
		//System.out.println(mx+";"+my);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		cx=e.getX();
		cy=e.getY();
		click=false;
		tik=true;
		
		
			
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		click=true;
		
		dx=e.getX();
		dy=e.getY();
		drx=dx;
		dry=dy;
		wfrx=main.wfr.getX();
		wfry=main.wfr.getY();
		
		
			right = isRight(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		click=false;
		
		ux=e.getX();
		uy=e.getY();
		
		
		if(onElement(785,400,800,415))
			Graph.play=!Graph.play;
		
		
		if(Graph.isSkeleton&&isRight(e))
		{
			if(Graph.changeModel)
			{
				Graph.nowSkele().setModelAngle(Graph.now, getAngle(drx,dry,Graph.nowSkele().getX(Graph.now)+Graph.eyeX,Graph.nowSkele().getY(Graph.now)+Graph.eyeY));
			}
		}
		
		if(Graph.isSkeleton&&Graph.changeModel)
		{
			if(onElement(438,30,538,60)) {
				Graph.changeModel=false;
			}
		}
		
		if(Graph.isSkeleton&&!Graph.changeModel)
		{
			
			
			
			for(int i = 0; i<Graph.bones.size()-(55*(int)(Graph.nowSkele/55));i++)
			{
				if(onElement(200+11*i,415,210+11*i,700)) Graph.nowSkele=i+(55*(int)(Graph.nowSkele/55));
			}
			for(int i = 0; i<5;i++)
				if(onElement(200+15*i,400,215+15*i,415))
					switch(i)
					{
					case 0:
						Graph.nowSkele--;
						if(Graph.nowSkele<0)Graph.nowSkele=Graph.bones.size()-1;
						break;
					case 1:
						Graph.nowSkele++;
						if(Graph.nowSkele>Graph.bones.size()-1)Graph.bones.add(new Skeleton());
						break;

					case 2:
						Graph.bones.add(Graph.nowSkele,Graph.nowSkele().clone());
						Graph.nowSkele++;
						break;

					case 3:
						if(Graph.bones.size()>1)
						{
						Graph.bones.remove(Graph.nowSkele);
						if(Graph.nowSkele>0)
						Graph.nowSkele--;
						}
						break;
					}
			
			
			
			if(onElement(200,20,800,400))
			if(Graph.nowSkele().length<1||Graph.nowSkele().length-1<Graph.now)
			{
			Graph.nowSkele().addNewBone(dx-Graph.eyeX, dy-Graph.eyeY,getAngle(drx,dry,dx,dy));
			}
			else
			{
				Graph.nowSkele().shiftX(Graph.now,Graph.timedShiftX);
				Graph.nowSkele().shiftY(Graph.now,Graph.timedShiftY);
				Graph.timedShiftX=0;
				Graph.timedShiftY=0;
			}
			
			if(isRight(e))
			{
				Graph.nowSkele().setAngle(
						Graph.now, 
						getAngle(drx,dry,Graph.nowSkele().getX(Graph.now)+Graph.eyeX,Graph.nowSkele().getY(Graph.now)+Graph.eyeY)
						);
				
			}
			
			
			for(int i = 0; i<4;i++)
			{
				if(onElement(5+i*50,29,5+i*50+40,29+40))
					switch(i)
					{
					case 0:
						setNewModel();
						break;
					case 1:
						Graph.nowSkele().removeModel(Graph.now);
						
						break;
					case 2:
						Graph.changeModel=true;
						break;
					case 3:
						Graph.isSelectVisible=!Graph.isSelectVisible;
						break;
					}
			}
			
			for(int i = 0; i<4;i++)
			{
				if(onElement(5+i*50,79,5+i*50+40,79+40))
					switch(i)	{
					case 0:
						Graph.nowSkele().clone(Graph.now);
						Graph.now++;
						
						break;
					case 1:
						if(Graph.now>0)Graph.now--;
						break;
					case 2:
						Graph.now++;
						break;
					case 3:
					if(Graph.nowSkele().length>0)
					{
						Graph.nowSkele().remove(Graph.now);
						Graph.now--;
						if(Graph.now<0)Graph.now++;
					
					}
						
						break;
						}
			}
		}
		
		if(!Graph.nowPolygon&&!Graph.changeModel) {
		
		
		if(!isRight(e))
		{
			
			for(int i = 0;i<9;i++)
			{
				if(onElement(i*20, 0, 20+i*20, 20))
				switch(i)
				{
	case 0:Graph.isSkeleton=false;
			Graph.models.set(Graph.nowModel, new Model());	
			
			Graph.now=0;
					break;			
	case 1:
		Graph.models.clear();
		Graph.nowModel=0;
		Graph.now=0;
		Graph.models.add(new Model());
		Graph.isSkeleton=false;
		break;
	case 2:
		Graph.isSkeleton=true;
		Graph.bones.clear();
		Graph.bones.add(new Skeleton());
		Graph.now=0;
		Graph.nowSkele=0;
		break;
	case 3:
		Graph.testdata=Graph.nowModel().createConfiguration();
		main.save.setVisible(true);
		Graph.isSkeleton=false;
		break;
	case 4:
		Graph.testdata=createAnimation();
		main.Animasave.setVisible(true);
		Graph.isSkeleton=false;
		break;
case 5:
	Graph.isSkeleton=true;	
	saveBones();
		break;
case 6: 
	main.load.setVisible(true);
	Graph.now=0;
	Graph.isSkeleton=false;
	break;
case 7:
	main.Animaload.setVisible(true);
	Graph.now=0;
	Graph.nowModel=0;
	Graph.isSkeleton=false;
	break;
case 8:
	Graph.isSkeleton=true;
	loadBones();
	break;

				}
				
			}
			
			
			
			for(int i = 0; i<Graph.models.size()-(55*(int)(Graph.nowModel/55));i++)
			{
				if(onElement(200+11*i,415,210+11*i,700))Graph.nowModel=i+(55*(int)(Graph.nowModel/55));
			}
			if(Graph.now<Graph.nowModel().lenght()&&Graph.nowModel().lenght()>0)
			if(downOnElement(timedShiftX+Graph.eyeX+getX()+getW()/2+Graph.timedShiftW/2, Graph.timedShiftY+Graph.eyeY+getY()-25,timedShiftX+Graph.eyeX+getX()+getW()/2+Graph.timedShiftW/2+10, Graph.timedShiftY+Graph.eyeY+getY()-25+10))
			{
				Graph.nowModel().setRadians(Graph.nowModel().getRadians(Graph.now)+Graph.timedShiftRadians,Graph.now);
				Graph.timedShiftRadians=0;
			}
			if(!Graph.isSkeleton)
			if(onElement(155,80,195,120))Graph.nowModel().changeCycled(Graph.now);
			if(onElement(780,0,800,20))System.exit(0);
			if(onElement(757,0,777,20))main.wfr.setState(JFrame.ICONIFIED);
		if(downOnElement(0,0,780,20))
		main.wfr.setLocation(wfrx+ e.getX()-dx, wfry+(e.getY()-dy));
		
		for(int i = 0;i<4;i++)
			if(onElement(5+50*i,29,5+50*i+40,29+40))
				setType(i);
		
		if(onElement(5,80,45,120))
			changeFill();
		if(onElement(55,80,95,120))
			if(!Graph.isSkeleton)
			Graph.isSelectVisible=!Graph.isSelectVisible;
		
		if(downOnElement(200,20,800,400))
		if(nowisno())
			Graph.nowModel().newFigure(0, dx-Graph.eyeX, dy-Graph.eyeY, (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256), ux-dx, uy-dy,0,0,ux-dx, uy-dy,(int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
		else
		if(	downOnElement(getX()+getW()-5+Graph.eyeX,getY()+getH()-5+Graph.eyeY,getX()+getW()+5+Graph.eyeX,getY()+getH()+5+Graph.eyeY))
			{
			setH(getH()+Graph.timedShiftH);
			setW(getW()+Graph.timedShiftW);
			 Graph.timedShiftH=0;
			Graph.timedShiftW=0;
			}
		else
		if(onElement(200,20,800,400)) {
			if(Graph.nowModel().getType(Graph.now)!=3)
			{
			setX(getX()+Graph.timedShiftX);
			setY(getY()+Graph.timedShiftY);
			Graph.timedShiftY=0;
			Graph.timedShiftX=0;
			}
			else
			{
				
				if(Graph.timedShiftPointX==0&&Graph.timedShiftPointY==0)
				{
				Graph.nowModel().shiftAllPoints(Graph.timedShiftX, Graph.timedShiftY, Graph.now);
				}
				else
				{
					Graph.nowModel().shiftPoint(Graph.timedShiftPointX, Graph.timedShiftPointY, Graph.now, Graph.nowp);
				}
				Graph.timedShiftY=0;
				Graph.timedShiftX=0;
				Graph.timedShiftPointY=0;
				Graph.timedShiftPointX=0;
			}
			
			}	
		
		if(onElement(105,128,145,168)&&!Graph.isSkeleton)
		{
			Graph.now++;Graph.nowp=0;
		}
		
		if(onElement(200+15*0,400,215+15*0,415)&&!Graph.isSkeleton)
		{

			Graph.nowModel--;
			if(Graph.nowModel<0)Graph.nowModel=Graph.models.size()-1;
			
			
			

		}
		
		if(onElement(200+15*1,400,215+15*1,415))
		{
			
			//error("hah");
			if(Graph.nowModel<Graph.models.size()-1)
			{
				Graph.nowModel++;
				if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
				Graph.now=0;
			}
			else
			{
				Graph.models.add(new Model());
				Graph.nowModel++;
				if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
				Graph.now=0;
			}
			
		}
		
		if(onElement(200+15*2,400,215+15*2,415))
		{
			Graph.models.add(Graph.nowModel,Graph.nowModel().clone());
			Graph.nowModel++;
			if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
		}
		
		if(onElement(200+15*3,400,215+15*3,415))
		{
		
			if(Graph.models.size()>1) {Graph.models.remove(Graph.nowModel);if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel--;}
		}
			
		
		if((Graph.now>=0&&Graph.nowModel().lenght()>0&&Graph.now<Graph.nowModel().lenght())&&Graph.nowModel().getType(Graph.now)==3)
			if(onElement(5+50, 245, 45+50, 285))
			{
				
				Graph.nowp--;
				if(Graph.nowp<0)Graph.nowp=Graph.nowModel().getPointsX(Graph.now).length-1;
				//if(Graph.nowp>Graph.nowModel().getPointsX(Graph.now).length-1) {Graph.nowPolygon=true;Graph.nowp--;}
			}
		
		if((Graph.now>=0&&Graph.nowModel().lenght()>0&&Graph.now<Graph.nowModel().lenght())&&Graph.nowModel().getType(Graph.now)==3)
			if(!Graph.isSkeleton)
			if(onElement(5+2*50, 245, 45+2*50, 285))
			{
				Graph.nowp++;
				if(Graph.nowp>Graph.nowModel().getPointsX(Graph.now).length-1) {Graph.nowPolygon=true;Graph.nowp--;}
			}
		
		if(!Graph.isSkeleton)
		if(onElement(5+3*50, 245, 45+3*50, 285))
		{
			if(Graph.nowp>0) {
			Graph.nowp--;
			Graph.nowModel().deletePoint(Graph.now, Graph.nowp);
			}
		}
		
		if(onElement(5+0*50, 245, 45+0*50, 285))
		{
			if(!Graph.isSkeleton)
			if(Graph.nowp>0) {
			//Graph.nowp++;
			//Graph.nowModel().copyPoint(Graph.now, Graph.nowp);
			}
		}
		
		if(onElement(55,128,95,168))
		{
			if(!Graph.isSkeleton)
			if(Graph.now>0) {Graph.now--;Graph.nowp=0;}
		}
		
		if(onElement(105,80,145,120))
		{
			if(!Graph.isSkeleton)
			Graph.nowModel().changeGradient(Graph.now);
		}
		
		if(onElement(5,128,45,168))cloneFigure();
		if(onElement(155,128,195,168)) {deleteFigure();}
		
		
		if(downOnElement(5+Graph.rt,313+0*30,10+5+Graph.rt,14+313+0*30))
		{Graph.nowModel().setR((int)((float)Graph.rx/180f*255f), Graph.now); Graph.timedShiftR=0;}
		if(downOnElement(5+Graph.gt,313+1*30,10+5+Graph.gt,14+313+1*30))
		{Graph.nowModel().setG((int)((float)Graph.gx/180f*255f), Graph.now); Graph.timedShiftG=0;}
		if(downOnElement(5+Graph.bt,313+2*30,10+5+Graph.bt,14+313+2*30))
		{Graph.nowModel().setB((int)((float)Graph.bx/180f*255f), Graph.now); Graph.timedShiftB=0;}
		if(downOnElement(5+Graph.at,313+3*30,10+5+Graph.at,14+313+3*30))
		{Graph.nowModel().setA((int)((float)Graph.ax/180f*255f), Graph.now); Graph.timedShiftA=0;}
		
		if(!drag) {
			if(downOnElement(5,313+0*30,180,14+313+0*30))
				if(e.getX()<Graph.rt)
					Graph.nowModel().setR(Graph.nowModel().getR(Graph.now)-1,Graph.now);
				if(downOnElement(5,313+0*30,180,14+313+0*30))
					if(e.getX()>Graph.rt+10)
						Graph.nowModel().setR(Graph.nowModel().getR(Graph.now)+1,Graph.now);
			
				if(downOnElement(5,313+1*30,180,14+313+1*30))
					if(e.getX()<Graph.gt)
						Graph.nowModel().setG(Graph.nowModel().getG(Graph.now)-1,Graph.now);
					if(downOnElement(5,313+1*30,180,14+313+1*30))
						if(e.getX()>Graph.gt+10)
							Graph.nowModel().setG(Graph.nowModel().getG(Graph.now)+1,Graph.now);
					
					if(downOnElement(5,313+2*30,180,14+313+2*30))
						if(e.getX()<Graph.bt)
							Graph.nowModel().setB(Graph.nowModel().getB(Graph.now)-1,Graph.now);
						if(downOnElement(5,313+2*30,180,14+313+2*30))
							if(e.getX()>Graph.bt+10)
								Graph.nowModel().setB(Graph.nowModel().getB(Graph.now)+1,Graph.now);
						
						if(downOnElement(5,313+3*30,180,14+313+3*30))
							if(e.getX()<Graph.at)
								Graph.nowModel().setA(Graph.nowModel().getA(Graph.now)-1,Graph.now);
							if(downOnElement(5,313+3*30,180,14+313+3*30))
								if(e.getX()>Graph.at+10)
									Graph.nowModel().setA(Graph.nowModel().getA(Graph.now)+1,Graph.now);
							
							
				
		}
		if(Graph.now<Graph.nowModel().lenght())
		if(Graph.nowModel().lenght()>0&&Graph.nowModel().isGradient(Graph.now))
		{
			

			if(downOnElement(5+Graph.rt2,433+0*30,10+5+Graph.rt2,14+433+0*30))
			{Graph.nowModel().setR2((int)((float)Graph.rx2/180f*255f), Graph.now); Graph.timedShiftR2=0;}
			if(downOnElement(5+Graph.gt2,433+1*30,10+5+Graph.gt2,14+433+1*30))
			{Graph.nowModel().setG2((int)((float)Graph.gx2/180f*255f), Graph.now); Graph.timedShiftG2=0;}
			if(downOnElement(5+Graph.bt2,433+2*30,10+5+Graph.bt2,14+433+2*30))
			{Graph.nowModel().setB2((int)((float)Graph.bx2/180f*255f), Graph.now); Graph.timedShiftB2=0;}
			if(downOnElement(5+Graph.at2,433+3*30,10+5+Graph.at2,14+433+3*30))
			{Graph.nowModel().setA2((int)((float)Graph.ax2/180f*255f), Graph.now); Graph.timedShiftA2=0;}
			
			if(!drag) {
				if(downOnElement(5,313+0*30,180,14+313+0*30))
					if(e.getX()<Graph.rt2)
						Graph.nowModel().setR2(Graph.nowModel().getR2(Graph.now)-1,Graph.now);
					if(downOnElement(5,313+0*30,180,14+313+0*30))
						if(e.getX()>Graph.rt2+10)
							Graph.nowModel().setR2(Graph.nowModel().getR2(Graph.now)+1,Graph.now);
				
					if(downOnElement(5,313+1*30,180,14+313+1*30))
						if(e.getX()<Graph.gt2)
							Graph.nowModel().setG2(Graph.nowModel().getG2(Graph.now)-1,Graph.now);
						if(downOnElement(5,313+1*30,180,14+313+1*30))
							if(e.getX()>Graph.gt2+10)
								Graph.nowModel().setG(Graph.nowModel().getG2(Graph.now)+1,Graph.now);
						
						if(downOnElement(5,313+2*30,180,14+313+2*30))
							if(e.getX()<Graph.bt2)
								Graph.nowModel().setB2(Graph.nowModel().getB2(Graph.now)-1,Graph.now);
							if(downOnElement(5,313+2*30,180,14+313+2*30))
								if(e.getX()>Graph.bt2+10)
									Graph.nowModel().setB2(Graph.nowModel().getB2(Graph.now)+1,Graph.now);
							
							if(downOnElement(5,313+3*30,180,14+313+3*30))
								if(e.getX()<Graph.at2)
									Graph.nowModel().setA2(Graph.nowModel().getA2(Graph.now)-1,Graph.now);
								if(downOnElement(5,313+3*30,180,14+313+3*30))
									if(e.getX()>Graph.at2+10)
										Graph.nowModel().setA2(Graph.nowModel().getA2(Graph.now)+1,Graph.now);
								
								
					
			}
			
			
		}
		
		
		drag=false;
		newdrag=false;
		tik=false;
		System.out.println(e.getX()+";"+e.getY());
		}
		else
		{
			
			Graph.nowModel().setX1(Graph.timedShiftX1, Graph.now);
			Graph.nowModel().setX2(Graph.timedShiftX2, Graph.now);
			Graph.nowModel().setY1(Graph.timedShiftY1, Graph.now);
			Graph.nowModel().setY2(Graph.timedShiftY2, Graph.now);
			Graph.timedShiftX1=0;
			Graph.timedShiftY1=0;
			Graph.timedShiftX2=0;
			Graph.timedShiftY2=0;//
		}
		
		}
		else
		{
			if(Graph.isSkeleton)
			{
			Graph.nowSkele().shiftModelX(Graph.now,Graph.timedShiftModelX);
			Graph.nowSkele().shiftModelY(Graph.now,Graph.timedShiftModelY);
			}
			
			if(onElement(780,0,800,20))System.exit(0);
			if(onElement(757,0,777,20))main.wfr.setState(JFrame.ICONIFIED);
			
			//System.out.println("da");
		
			
			if(onElement(438,30,538,60)) {
				if(Graph.nowModel().getPointsX(Graph.now).length>0)
					Graph.nowModel().changePolygon(Graph.now);
				Graph.nowPolygon=false;
				Graph.changeModel=false;
			}
			if(Graph.nowPolygon)
				Graph.nowModel().addPoints(ux-Graph.eyeX, uy-Graph.eyeY, Graph.now);
		}
		
	
		Graph.timedShiftModelX=0;
		Graph.timedShiftModelY=0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()!=66)
		System.out.println(e.getKeyChar()+":"+e.getKeyCode());
		switch(e.getKeyCode())
		{
		case 66:
			Graph.toNull=true;
			break;
case 37:
	Graph.left=-1;
			break;
case 38:
	Graph.up=-1;
	break;
case 39:
	Graph.left=1;
	break;
case 40:
	Graph.up=1;
	break;
case 16:
	Graph.Shift=3;
	break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case 66:
			Graph.toNull=false;
			break;
case 37:
	Graph.left=0;
			break;
case 38:
	Graph.up=0;
	break;
case 39:
	Graph.left=0;
	break;
case 40:
	Graph.up=0;
	break;
case 16:
	Graph.Shift=1;
	break;
case 61:
	Graph.now++;Graph.nowp=0;
	break;
case 45:
	if(Graph.now>0) {Graph.now--;Graph.nowp=0;}
	break;
case 127:
	deleteFigure();
	break;
case 155:
	cloneFigure();
	break;
case 27:
	System.exit(0);
	break;
case 83:
	Graph.testdata=Graph.nowModel().createConfiguration();
	main.save.setVisible(true);
	break;
case 76:
	main.load.setVisible(true);
	Graph.now=0;
	break;
case 57:
	Graph.nowModel--;
	if(Graph.nowModel<0)Graph.nowModel=Graph.models.size()-1;
	Graph.now=0;
	break;
case 48:
	Graph.nowModel++;
	if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
	//System.out.println(Graph.nowModel+";"+Graph.models.size());
	Graph.now=0;
	break;
case 55:
	

	Graph.models.add(Graph.nowModel,Graph.nowModel().clone());
	Graph.nowModel++;
	if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
	
break;
case 56:
Graph.models.add(new Model());
Graph.nowModel++;
if(Graph.nowModel>Graph.models.size()-1)Graph.nowModel=0;
Graph.now=0;
	break;
case 116:
	Graph.play=!Graph.play;
	break;
	
case 54:
	if(Graph.models.size()>1) {
	Graph.models.remove(Graph.nowModel);
	Graph.nowModel--;}
	Graph.now=0;
	break;
	
case 68:
	pass();
	break;
	
		}
		
	}

	public void pass() {System.out.println(Graph.nowPolygon+";"+Graph.nowSkele);}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	private boolean onElement(int x1, int y1, int x2, int y2)
	{
		return (ux>=x1&&ux<=x2&&uy>=y1&&uy<=y2);
	}
	
	
	static boolean downOnElement(int x1, int y1, int x2, int y2)
	{
		return (dx>=x1&&dx<=x2&&dy>=y1&&dy<=y2);
	}
	
	private void setType(int type)
	{
		Graph.nowModel().setType(type, Graph.now);
	}
	
	private void setX(int x)
	{
		Graph.nowModel().setX(x, Graph.now);
	}
	
	private void setY(int y)
	{
		Graph.nowModel().setY(y, Graph.now);
	}
	
	private void setH(int h)
	{
		Graph.nowModel().setH(h, Graph.now);
	}
	
	private void setW(int w)
	{
		Graph.nowModel().setW(w, Graph.now);
	}
	
	private int getX()
	{
		return Graph.nowModel().getX(Graph.now);
	}
	
	private int getY()
	{
		return Graph.nowModel().getY(Graph.now);
	}
	
	private int getW()
	{
		return Graph.nowModel().getW(Graph.now);
	}
	
	private int getH()
	{
		return Graph.nowModel().getH(Graph.now);
	}
	
	private void changeFill()
	{
		if(!Graph.isSkeleton)
		Graph.nowModel().changeFill(Graph.now);
	}
	
	public static boolean nowisno()
	{
		return Graph.nowModel().lenght()<Graph.now+1||Graph.nowModel().lenght()==0;
	}
	
	static void deleteFigure()
	{
		Graph.nowModel().delete(Graph.now);
	}
	
	static void cloneFigure()
	{
		if(!Graph.isSkeleton) {
		Graph.nowModel().clone(Graph.now);
		Graph.now++;}
	}
	
	public boolean isRight(MouseEvent e)
	{
		return e.getButton()==MouseEvent.BUTTON3;
	}


	public static double getAngle(int x0,int y0,int x1,int y1){//прилеж катет к гипотенузе
		if(x0==x1&&y0==y1)return 0;
	
			double cat1=x0-x1;
			double cat2=y0-y1;
			double tgns = cat1/cat2;
			if(y0-y1>0)
			return - Math.atan(tgns);
		else
			return - Math.atan(tgns)+Math.PI;
		}
	
	public static boolean createFile(String src,String url,String name)
	{
		//String name = generateRandomName(8);
		File f = new File(url,name+".vkg");
		
	
		
		try {
			FileWriter ff = new FileWriter(url+"\\"+name+".vkg",false);
			f.createNewFile();
			
				ff.write(src);
				System.out.println(url+name+".vkg");
				ff.flush();
				System.out.println(name+".vkg is created!");
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean createVkaFile(String src,String url,String name)
	{
		//String name = generateRandomName(8);
		File f = new File(url,name+".vka");
		
	
		
		try {
			FileWriter ff = new FileWriter(url+"\\"+name+".vka",false);
			f.createNewFile();
			
				ff.write(src);
				System.out.println(url+name+".vka");
				ff.flush();
				System.out.println(name+".vkg is created!");
			
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
	private void loadData(String src)
	{
		File f = new File(src);
		try {
			FileReader ff = new FileReader(f);
			String data="";
			int c;
			while((c=ff.read())!=-1)
				data+=(char)c;
			//System.out.println(data.split("#").length);
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
	
	
	private String createAnimation()
	{
		String data="";
		for(int i = 0;i<Graph.models.size();i++)
		{
			data+=Graph.models.get(i).createConfiguration()+"!";
		}
		return data;
	}
	
	private void setNewModel()
	{
		JFileChooser ffop = new JFileChooser();
		int ret=ffop.showDialog(null,"открыть");
		if(ret==JFileChooser.APPROVE_OPTION)
		{
			Graph.nowSkele().setModel(Graph.now,ffop.getSelectedFile().getAbsolutePath());
		Graph.changeModel=true;
		}
	}
	
	private String getPath()
	{
		JFileChooser ffop = new JFileChooser();
		ffop.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ret=ffop.showDialog(null,"открыть");
		if(ret==JFileChooser.APPROVE_OPTION)
		return ffop.getSelectedFile().getAbsolutePath();
		else
			return "";
	}
	
	
	private String getFile()
	{
		JFileChooser ffop = new JFileChooser();
		int ret=ffop.showDialog(null,"открыть");
		if(ret==JFileChooser.APPROVE_OPTION)
		return ffop.getSelectedFile().getAbsolutePath();
		else
			return "";
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
	
	private void loadBones()
	{
		JFrame fr = new JFrame("Загрузить скелет...");
		JTextField src = new JTextField("C:\\VectoGraphV2_0Files\\Skeletons");
		JButton done = new JButton("Загрузить");
		JButton find = new JButton("Обзор...........");
		
		find.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ss = getFile();
				if(ss!="")
					src.setText(ss);
			}
		});
		
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String str = getFileStr(src.getText());
				String[] dats = str.split("№");
				Graph.bones.clear();
				for(int i = 0;i<dats.length;i++)
				{
				Skeleton s = new Skeleton();
				s.loadConfiguration(dats[i]);
				Graph.bones.add(s);
				}
				fr.setVisible(false);
			}
			
		});
		
		fr.setSize(800,200);
		fr.setLocationRelativeTo(null);
		fr.setLayout(new GridLayout(2,2,15,15));
		fr.add(new JLabel("Расположение"));
		fr.add(src);
		fr.add(find);
		fr.add(done);
		fr.setVisible(true);
	}
	
	private void saveBones()
	{
		
		
		JFrame fr = new JFrame("Сохранить скелет...");
		JTextField src = new JTextField("C:\\VectoGraphV2_0Files\\Skeletons");
		JTextField name = new JTextField(generateRandomName(8));
		JButton done = new JButton("Сохранить");
		JButton find = new JButton("Обзор...........");
		
		find.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ss = getPath();
				if(ss!="")
					src.setText(ss);
			}
		});
		
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str="";
				for(int i = 0;i<Graph.bones.size();i++)
				{
					str+=Graph.bones.get(i).createConfiguration()+"№";
				}
				createSkltFile(str,src.getText(),name.getText());
				fr.setVisible(false);
				
			}
			
		});
			
		
		
		fr.setSize(800,200);
		fr.setLocationRelativeTo(null);
		fr.setLayout(new GridLayout(3,2,15,15));
		fr.add(new JLabel("Расположение"));
		fr.add(src);
		fr.add(new JLabel("Имя"));
		fr.add(name);
		fr.add(find);
		fr.add(done);
		fr.setVisible(true);
		
		//createSkltFile(str,,)
	}
	
	public static boolean createSkltFile(String src,String url,String name)
	{
		//String name = generateRandomName(8);
		File f = new File(url,name+".sklt");
		
	
		
		try {
			FileWriter ff = new FileWriter(url+"\\"+name+".sklt",false);
			f.createNewFile();
			
				ff.write(src);
				System.out.println(url+name+".sklt");
				ff.flush();
				System.out.println(name+".sklt is created!");
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
