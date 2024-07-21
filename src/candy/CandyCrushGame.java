package candy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.InputStream;

import javax.sound.sampled.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
public class CandyCrushGame extends JFrame {

    private JFrame frame;
    private int[][] arr;
    private Point[] pos;
    boolean song=false;
	Checked checked;
    private Point[][] pos2;

    private boolean flag=false;
    DrawPanel drawPanel;
    private double x_start=0;
    private double y_start=0;
    private double x_end=0;
    private double y_end=0;
    Random random = new Random();
    String music[];
    int c=0;
    int counter=0;
    public CandyCrushGame() {
        arr = new int[5][5];
        pos = new Point[25];
        pos2 = new Point[5][5];
        int ind = 0;
        String []music= {"candy_land1.wav","candy_land2.wav","candy_land3.wav","candy_land4.wav",};
        
        this.music=music;
       
        
        int[][] arr = {
        	    {1, 3, 4,2, 2},
        	    {4, 4, 1, 1, 2},
        	    {1, 1, 2, 1, 1},
        	    {2, 4, 3, 2, 3},
        	    {4, 2, 3, 1, 1}
        	};
        this.arr=arr;
        for (int i = 0, k = 10; i < arr.length; i++, k += 50) {
            for (int j = 0, t = 10; j < arr.length; j++, t += 50) {
              //  arr[i][j] = random.nextInt(4) + 1;
                Point p = new Point(t, k);
                pos2[i][j]=p;
                pos[ind++] = p;
            }
        }

        frame = new JFrame("Candy Crush Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 290);
        frame.setLayout(new BorderLayout());
        	String []images= {"icon1.png","icon2.png","icon3.png","icon4.png",
        			"icon1_up.png","icon2_up.png","icon3_up.png","icon4_up.png",
        			"icon1_s.png","icon2_s.png","icon3_s.png","icon4_s.png",
        			"icon1_b.png","icon2_b.png","icon3_b.png","icon4_b.png","icon99.png"};
         drawPanel = new DrawPanel(images,arr,pos2);
		 checked=new Checked(arr, counter,pos2,drawPanel,music);

         checked.CheckDown();



        frame.add(drawPanel, BorderLayout.CENTER);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });

        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    	playSong2("/game_play.wav");


    }

    private void handleMousePressed(MouseEvent e) {
        Point mousePoint = e.getPoint();
        x_start=mousePoint.getX();
        y_start=mousePoint.getY();

       //  
    }

    private void handleMouseReleased(MouseEvent e) {
        Point mousePoint = e.getPoint();
        x_end=mousePoint.getX();
        y_end=mousePoint.getY();

        
        double x=x_end-x_start;
        double y=y_end-y_start;
        


        if(flag==true) {
        	flag=false;
        	startAnimation(x,y);
        }
        
    }
    
    
    private void playSong2(String resourcePath) {
        try {
            InputStream audioStream = getClass().getResourceAsStream(resourcePath);
            if (song==true&&audioStream != null) {
                byte[] audioData = audioStream.readAllBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);

                AudioInputStream ais = AudioSystem.getAudioInputStream(byteArrayInputStream);

                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            } else {
                System.err.println("Could not find the resource: " + resourcePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    


    private void handleMouseDragged(MouseEvent e) {

    	if(flag==false) {
    	flag=true;
    	}
    }
    
  

    

    
    private void startAnimation(double x,double y) {
        if (!flag) {
            flag = true;
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                	counter=0;
                	if(x_start>0&&x_start<60&& y_start>0&&y_start<80) {
                		if(y<10 ) {
                			if(x>0) {
                				doing_x(0,1,0,0,0,1);
                			}
                		}
                		else if(x<10) {
                			doing_y(0,5,0,0,1,0);
                		}

                	}
                	else if(x_start>60&&x_start<120&& y_start>0&&y_start<80) {
                		if(y<10)
                		{
                			if(x<0) {
                				 
                    			doing_x(0,1,0,0,0,1);

                			}
                			else if(x>0) {
                    			doing_x(1,2,0,1,0,2);
                				
                			}
                		}
                		else if(x<10) {
                			doing_y(1,6,0,1,1,1);
                		}
                	}
                	else if(x_start>120&&x_start<180&& y_start>0&&y_start<80) {
                		if(y<10)
                		{
                			if(x<0) {
                				 
                    			doing_x(1,2,0,1,0,2);

                			}
                			else if(x>0) {
                				 

                    			doing_x(2,3,0,2,0,3);
                				
                			}
                		}
                		else if(x<10) {
                			doing_y(2,7,0,2,1,2);
                		}
                	}
                	else if(x_start>180&&x_start<220&& y_start>0&&y_start<80) {
                		if(y<10)
                		{
                			if(x<0) {
                				 
                    			doing_x(2,3,0,2,0,3);

                			}
                			else if(x>0) {
                				 

                    			doing_x(3,4,0,3,0,4);
                				
                			}
                		}
                		else if(x<10) {
                			doing_y(3,8,0,3,1,3);
                		}
                	}
                  	else if(x_start>220&&x_start<280&& y_start>0&&y_start<80) {
                		if(y<10)
                		{
                			if(x<0) {
                				 
                    			doing_x(3,4,0,3,0,4);

                			}
                		}
                		else if(x<10) {
                			doing_y(4,9,0,4,1,4);
                		}
                	}
                	
                	
                	
                	
                  	else if(x_start>0&&x_start<60&& y_start>80&&y_start<120) {
                  		
                		if(y<10 ) {
                			if(x>10) {
                    			doa(5,1);

                			}
                		}
                		 if(x<10) {
                			if(y>0) {
                				doing_y(5,10,1,0,2,0);
                			}
                			else if(y<0)  {
                				doing_y(0,5,0,0,1,0);

                			}
                		}

                	}
                	
                	
                	
                	
                	
                	
                  	else if(x_start>60&&x_start<120&& y_start>80&&y_start<120) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(5,6,1,0,1,1);

                			}
                			else if(x>0) {
                				 
                    			doing_x(6,7,1,1,1,2);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(6,11,1,1,2,1);
                			}
                			else if(y<0)  {
                				doing_y(1,6,0,1,1,1);

                			}
                		}
                	}
                	
                	
                	//1,2
                  	else if(x_start>120&&x_start<180&& y_start>80&&y_start<120) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(6,7,1,1,1,2);

                			}
                			else if(x>0) {
                				 
                    			doing_x(7,8,1,2,1,3);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(7,12,1,2,2,2);
                			}
                			else if(y<0)  {
                				doing_y(2,7,0,2,1,2);

                			}
                		}
                	}
                	
                	
                	else if(x_start>180&&x_start<220&& y_start>80&&y_start<120) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(7,8,1,2,1,3);

                			}
                			else if(x>0) {
                				 
                    			doing_x(8,9,1,3,1,4);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(8,13,1,3,2,3);
                			}
                			else if(y<0)  {
                				doing_y(3,8,0,3,1,3);

                			}
                		}
                	}
                	
                	else if(x_start>220&&x_start<280&& y_start>80&&y_start<120) {
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 
                    			doing_x(8,9,1,3,1,4);

                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(9,14,1,4,2,4);
                			}
                			else if(y<0)  {
                				doing_y(4,9,0,4,1,4);

                			}
                		}
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                   	else if(x_start>0&&x_start<60&& y_start>120&&y_start<180) {
                		if(y<10 ) {
                			if(x>10) {
                    			doing_x(10,11,2,0,2,1);


                			}
                		}
                		 if(x<10) {
                			if(y>0) {
                				doing_y(10,15,2,0,3,0);
                			}
                			else if(y<0)  {
                				doing_y(5,10,1,0,2,0);

                			}
                		}

                	}
                	
                	
                 	else if(x_start>60&&x_start<120&& y_start>120&&y_start<180) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(10,11,2,0,2,1);

                			}
                			else if(x>0) {
                				 
                    			doing_x(11,12,2,1,2,2);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(11,16,2,1,3,1);
                			}
                			else if(y<0)  {
                				doing_y(6,11,1,1,2,1);

                			}
                		}
                	}
                	
                	
                	
                	//1,2
                  	else if(x_start>120&&x_start<180&& y_start>120&&y_start<180) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 

                    			doing_x(11,12,2,1,2,2);

                			}
                			else if(x>0) {
                				 
                    			doing_x(12,13,2,2,2,3);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(12,17,2,2,3,2);

                			}
                			else if(y<0)  {
                				doing_y(7,12,2,2,1,2);

                			}
                		}
                	}
                	
                	
                	else if(x_start>180&&x_start<220&& y_start>120&&y_start<180) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 

                    			doing_x(12,13,2,2,2,3);

                			}
                			else if(x>0) {
                				 
                    			doing_x(13,14,2,3,2,4);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(13,18,2,3,3,3);
                			}
                			else if(y<0)  {
                				doing_y(8,13,2,3,1,3);

                			}
                		}
                	}
                	
                	else if(x_start>220&&x_start<280&& y_start>120&&y_start<180) {
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 
                    			doing_x(13,14,2,3,2,4);

                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(14,19,2,4,3,4);
                			}
                			else if(y<0)  {
                				doing_y(9,14,1,4,2,4);

                			}
                		}
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	

                	
                   	else if(x_start>0&&x_start<60&& y_start>180&&y_start<240) {
                		if(y<10 ) {
                			if(x>10) {
                    			doing_x(15,16,3,0,3,1);


                			}
                		}
                		 if(x<10) {
                			if(y>0) {
                				doing_y(15,20,3,0,4,0);
                			}
                			else if(y<0)  {
                				doing_y(10,15,2,0,3,0);

                			}
                		}

                	}
                	
                	
                 	else if(x_start>60&&x_start<120&& y_start>180&&y_start<240) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(15,16,3,0,3,1);

                			}
                			else if(x>0) {
                				 
                    			doing_x(16,17,3,1,3,2);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(16,21,3,1,4,1);
                			}
                			else if(y<0)  {
                				doing_y(11,16,2,1,3,1);

                			}
                		}
                	}
                	
                	
                	
                	//1,2
                  	else if(x_start>120&&x_start<180&& y_start>180&&y_start<240) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {

                    			doing_x(16,17,3,1,3,2);

                			}
                			else if(x>0) {
                				 
                    			doing_x(17,18,3,2,3,3);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(17,22,3,2,4,2);

                			}
                			else if(y<0)  {
                				doing_y(12,17,3,2,2,2);

                			}
                		}
                	}
                	
                	
                	else if(x_start>180&&x_start<220&& y_start>180&&y_start<240) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 

                    			doing_x(17,18,3,2,3,3);

                			}
                			else if(x>0) {
                				 
                    			doing_x(18,19,3,3,3,4);
                				
                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(18,23,3,3,4,3);
                			}
                			else if(y<0)  {
                				doing_y(13,18,2,3,3,3);

                			}
                		}
                	}
                	
                	else if(x_start>220&&x_start<280&& y_start>180&&y_start<240) {
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 
                    			doing_x(18,19,3,3,3,4);

                			}
                		}
                		else if(x<10) {
                			if(y>0) {
                				 
                				doing_y(19,24,3,4,4,4);
                			}
                			else if(y<0)  {
                				doing_y(14,19,2,4,3,4);

                			}
                		}
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	

                	
                   	else if(x_start>0&&x_start<60&& y_start>240&&y_start<300) {
                		if(y<10 ) {
                			if(x>10) {
                    			doing_x(20,21,4,0,4,1);


                			}
                		}
                		 if(x<10) {
                			
                			 if(y<0)  {
                				doing_y(15,20,3,0,4,0);

                			}
                		}

                	}
                	
                	
                 	else if(x_start>60&&x_start<120&& y_start>240&&y_start<300) {
                  		
                		if(y< 10 && y>-4)
                		{
                			if(x<0) {
                				 

                    			doing_x(20,21,4,0,4,1);

                			}
                			else if(x>0) {
                				 
                    			doing_x(21,22,4,1,4,2);
                				
                			}
                		}
                		else if(x<10) {
                			 if(y<0)  {
                				doing_y(16,21,3,1,4,1);

                			}
                		}
                	}
                	
                	
                	
                	//1,2
                  	else if(x_start>120&&x_start<180&& y_start>240&&y_start<300) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 

                    			doing_x(21,22,4,1,4,2);

                			}
                			else if(x>0) {
                				 
                    			doing_x(22,23,4,2,4,3);
                				
                			}
                		}
                		else if(x<10) {
                			 if(y<0)  {
                				doing_y(17,22,3,2,4,2);

                			}
                		}
                	}
                	
                	
                	else if(x_start>180&&x_start<220&& y_start>240&&y_start<300) {
                  		
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 

                    			doing_x(22,23,4,2,4,3);

                			}
                			else if(x>0) {
                				 
                    			doing_x(23,24,4,3,4,4);
                				
                			}
                		}
                		else if(x<10) {
                			if(y<0)  {
                				doing_y(18,23,3,3,4,3);

                			}
                		}
                	}
                	
                	else if(x_start>220&&x_start<280&& y_start>240&&y_start<300) {
                		if(y< 10 && y>-10)
                		{
                			if(x<0) {
                				 
                    			doing_x(23,24,4,3,4,4);

                			}
                		}
                		else if(x<10) {
                			 if(y<0)  {
                				doing_y(19,24,3,4,4,4);

                			}
                		}
                	}
     
                                    return null;
                }
            };
            worker.execute();
        }
    }
    
    public void doa(int x,int y) {
		doing_x(0+x,1+x,0+y,0,0+y,1);

    }
  
    
    public void printArr() {
    	System.out.println("\n");
        for (int i = 0, k = 10; i < arr.length; i++, k += 50) {
            for (int j = 0, t = 10; j < arr.length; j++, t += 50) {
	            	Point p = new Point(t, k);
	                pos2[i][j]=p;            	
               System.out.print(arr[i][j]+" ");
            }
           System.out.println();
        }
        drawPanel.repaint();
    }
    
    public void doing_x(int ind1,int ind2,int index1,int index2,int index3,int index4) {
    	
    	int cnt=0;
        while (flag && cnt < 50 ) {
            pos2[index1][index2].x++;
            pos2[index3][index4].x--;
            cnt++;
            drawPanel.repaint();
            try {
                Thread.sleep(2); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        Point p2=pos2[index1][index2];
        pos2[index1][index2]=pos2[index3][index4];
        pos2[index3][index4]=p2;
        int temp=arr[index1][index2];
        arr[index1][index2]=arr[index3][index4];
        arr[index3][index4]=temp;
        
        if(arr[index1][index2]==99|| arr[index3][index4]==99) {
        	System.out.println("all");
        	int t=0;
        	if(arr[index1][index2]>arr[index3][index4]) {
        		t=arr[index3][index4];
        	}
        	else {
        		t=arr[index1][index2];
        	}
        	arr[index1][index2]=0;
        	arr[index3][index4]=0;

        	for(int i=0;i<arr.length;i++) {
        		for(int j=0;j<arr.length;j++) {
        			if(arr[i][j]==t)
        				arr[i][j]=0;
        		}
        	}
        }
        
        flag = false;
        checked.update();
        c++;
        if(!checked.check()&&c%2==1) {
        	doing_x(ind1,ind2,index1,index2,index3,index4);
        }
        

    }
    
  
    
    public void doing_y(int ind1,int ind2,int index1,int index2,int index3,int index4) {
    	int cnt=0;
        while (flag && cnt < 50) {
        	pos2[index1][index2].y++;
        	pos2[index3][index4].y--;
            cnt++;

            drawPanel.repaint();
            try {
                Thread.sleep(2); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Point p2=pos2[index1][index2];
        pos2[index1][index2]=pos2[index3][index4];
        pos2[index3][index4]=p2;
        
        int temp=arr[index1][index2];
        arr[index1][index2]=arr[index3][index4];
        arr[index3][index4]=temp;
        
        flag = false;
        
    }
    
  
    
    public void indexes(int i,int j) {
    	if(i==0 && j==0) {
    		doing_y(0, 5, 0, 0, 1, 0);
    	}
    	else if(i==0 && j==1) {
    		doing_y(1, 6, 0, 1, 1, 1);
    	}
    	else if(i==0 && j==2) {
    		doing_y(2, 7, 0, 2, 1, 2);
    	}
    	else if(i==0 && j==3) {
    		doing_y(3, 8, 0, 3, 1, 3);
    	}
    	else if(i==0 && j==4) {
    		doing_y(4, 9, 0, 4, 1, 4);
    	}
    	
    	
    	else if(i==1 && j==0) {
    		doing_y(5, 10, 1, 0, 2, 0);
    	}
    	else if(i==1 && j==1) {
    		doing_y(6, 11, 1, 1, 2, 1);
    	}
    	else if(i==1 && j==2) {
    		doing_y(7, 12, 1, 2, 2, 2);
    	}
    	else if(i==1 && j==3) {
    		doing_y(8, 13, 1, 3, 2, 3);
    	}
    	else if(i==1 && j==4) {
    		doing_y(9, 14, 1, 4, 2, 4);
    	}
    	
    	else if(i==2 && j==0) {
    		doing_y(10, 15, 2, 0, 3, 0);
    	}
    	else if(i==2 && j==1) {
    		doing_y(11, 16, 2, 1, 3, 1);
    	}
    	else if(i==2 && j==2) {
    		doing_y(12, 17, 2, 2, 3, 2);
    	}
    	else if(i==2 && j==3) {
    		doing_y(13, 18, 2, 3, 3, 3);
    	}
    	else if(i==2 && j==4) {
    		doing_y(14, 19, 2, 4, 3, 4);
    	}
    	
    	else if(i==3 && j==0) {
    		doing_y(15, 20, 3, 0, 4, 0);
    	}
    	else if(i==3 && j==1) {
    		doing_y(16, 21, 3, 1, 4, 1);
    	}
    	else if(i==3 && j==2) {
    		doing_y(17, 22, 3, 2, 4, 2);
    	}
    	else if(i==3 && j==3) {
    		doing_y(18, 23, 3, 3, 4, 3);
    	}
    	else if(i==3 && j==4) {
    		doing_y(19, 24, 3, 4, 4, 4);
    	}

    	
    	
    }

 

    
    	
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CandyCrushGame();
        });
    }
}
