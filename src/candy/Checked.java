package candy;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingWorker;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

class Checked{
    private int[][] arr;
    Random random = new Random();
    private int counter;
    private Point[][] pos2;
    DrawPanel drawPanel;
    boolean song=true;

    private boolean flag=false;
    String music[];

    public Checked(int[][] arr, int counter,Point[][] pos2,    DrawPanel drawPanel  ,    String music[]){
        this.arr=arr;
        this.counter=counter;
        this.pos2=pos2;
        this.drawPanel=drawPanel;
        this.music=music;
    }
      public void CheckDown() {
       
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                	while(true) {
                		for(int i=0;i<arr.length;i++) {
                			if(arr[0][i]==0) {
                				arr[0][i]=random.nextInt(4)+1;
                			}
                		}
                		check4();
                		check();
                	for(int i=0;i<arr.length-1;i++) {
                		for(int j=0;j<arr.length;j++) {
                			if(arr[i][j]!=0&&arr[i+1][j]==0) {
                				int k=i;
                				while(k>=0) {
                				if(arr[k][j]!=0) {
                				
                					anim(k,j);
                				}
                				k--;
                				}
                				
                			}
                		}
                	}                	
                	try {
						Thread.sleep(300);
						
						
						

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	update();
                	//printArr();

                	}
                }
            };
            worker.execute();
        

        
    }
    public void anim(int index1,int index2) {
    	int cnt=0;
        while ( cnt < 50) {
        	pos2[index1][index2].y++;
            cnt++;

            drawPanel.repaint();
            try {
                Thread.sleep(2); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
                
        flag = false;


    }
    
    
    public boolean check() {
    	boolean f=false;
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i][0]==arr[i][1]&&arr[i][0]==arr[i][2]&&arr[i][0]==arr[i][3]&&arr[i][0]==arr[i][4]) {
    			arr[i][2]=99;
    			arr[i][0]=0;
    			arr[i][1]=0;
    			arr[i][3]=0;
    			arr[i][4]=0;
    		}
    		else if(arr[0][i]==arr[1][i]&&arr[0][i]==arr[2][i]&&arr[0][i]==arr[3][i]&&arr[0][i]==arr[4][i]) {
    			arr[0][i]=0;
    			arr[1][i]=0;
    			arr[2][i]=99;
    			arr[3][i]=0;
    			arr[4][i]=0;
    		}
    	}
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length-2;j++) {
    			if(arr[i][j]==arr[i][j+1]&&arr[i][j+2]==arr[i][j]) {   
    				f=true;

    				if(j<2&&arr[i][j+3]==arr[i][j]) {
    					if(arr[i][j]==1) {
    						arr[i][j]=11;
    					}
    					else if(arr[i][j]==2) {
    						arr[i][j]=22;
    					}
    					else if(arr[i][j]==3) {
    						arr[i][j]=33;
    					}
    					else if(arr[i][j]==4) {
    						arr[i][j]=44;
    					}
    					 
        				arr[i][j+3]=0;

    				}
    				
    				if(arr[i][j]<11)
    					arr[i][j]=0;
    				arr[i][j+1]=0;
    				arr[i][j+2]=0;
    				counter++;
    				if(counter>3) {
    		    		playSong("/tasty.wav");
    		    		counter=0;
    		    	}
    		        playSong("/"+music[random.nextInt(3)]);

    				}
    		}
    	}
    	
    	for(int i=0;i<arr.length-2;i++) {
    		for(int j=0;j<arr.length;j++) {
    			
    				
    				if(arr[i][j]==arr[i+1][j]&&arr[i+2][j]==arr[i][j]) {   
        				f=true;

        				if(i<2&&arr[i+3][j]==arr[i][j]) {
        					if(arr[i][j]==1) {
        						arr[i][j]=111;
        					}
        					else if(arr[i][j]==2) {
        						arr[i][j]=222;
        					}
        					else if(arr[i][j]==3) {
        						arr[i][j]=333;
        					}
        					else if(arr[i][j]==4) {
        						arr[i][j]=444;
        					}
        					 
            				arr[i+3][j]=0;

        				}
        				
        				if(arr[i][j]<11)
        					arr[i][j]=0;
        				arr[i+1][j]=0;
        				arr[i+2][j]=0;
        				counter++;
        				if(counter>3) {
        		    		playSong("/tasty.wav");
        		    		counter=0;
        		    	}
        		        playSong("/"+music[random.nextInt(3)]);

        				}
        				
    				
        			
    			
    		}
    	}
    	
    	check2(11,1);
    	check2(22,2);
    	check2(33,3);
    	check2(44,4);
    	
    	check3(111,1);
    	check3(222,2);
    	check3(333,3);
    	check3(444,4);
    	
    	check5(1111,1);
    	check5(2222,2);
    	check5(3333,3);
    	check5(4444,4);
    	return f;
    }
    
    public void check5(int x,int y) {
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length-2;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((j<3&&arr[i][j+1]==y&&arr[i][j+2]==y)||(j!=0&&j!=4&&arr[i][j-1]==y&&arr[i][j+1]==y)||(j>1&&arr[i][j-2]==y&&arr[i][j-1]==y))
        				{
	        				playSong2("colour_bomb1.wav");
	        				arr[i][j]=0;
	        				if(j!=0) {
	        					arr[i][j-1]=0;
	        				}
	        				if(j!=4) {
	        					arr[i][j+1]=0;
	        				}
	        				
	        				if(i!=0) {
	        					if(j!=0)
		        				arr[i-1][j-1]=0;
		        				arr[i-1][j]=0;
		        				if(j!=4)
		        				arr[i-1][j+1]=0;
	        				}
	        				if(i!=4) {
	        					if(j!=0)
	    	        				arr[i+1][j-1]=0;
	    	        				arr[i+1][j]=0;
	    	        				if(j!=4)
	    	        				arr[i+1][j+1]=0;    
	    	        			}
    				}
        				
    				}catch(Exception ex) {}
    				
    			}
    		}
    	}
    	
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((i<3&&arr[i+1][j]==y&&arr[i+2][j]==y)||(i!=0&&i!=4&&arr[i-1][j]==y&&arr[i+1][j]==y)||(i>1&&arr[i-2][j]==y&&arr[i-1][j]==y)) {
        					playSong2("/colour_bomb1.wav");
	        				arr[i][j]=0;
	        				if(j!=0) {
	        					arr[i][j-1]=0;
	        				}
	        				if(j!=4) {
	        					arr[i][j+1]=0;
	        				}
	        				
	        				if(i!=0) {
	        					if(j!=0)
		        				arr[i-1][j-1]=0;
		        				arr[i-1][j]=0;
		        				if(j!=4)
		        				arr[i-1][j+1]=0;
	        				}
	        				if(i!=4) {
	        					if(j!=0)
	    	        				arr[i+1][j-1]=0;
	    	        				arr[i+1][j]=0;
	    	        				if(j!=4)
	    	        				arr[i+1][j+1]=0;    
	    	        			}
            				
        				}

    				}catch(Exception ex) {}
    			}
    		}
    	}
    }
    
    public void check2(int x,int y) {
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length-2;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((j<3&&arr[i][j+1]==y&&arr[i][j+2]==y)||(j!=0&&j!=4&&arr[i][j-1]==y&&arr[i][j+1]==y)||(j>1&&arr[i][j-2]==y&&arr[i][j-1]==y))
        				playSong2("/colour_bomb1.wav");
        				for(int k=0;k<arr.length;k++) {
        					arr[k][j]=0;
        				}
        				
    				}catch(Exception ex) {}
    				
    			}
    		}
    	}
    	
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((i<3&&arr[i+1][j]==y&&arr[i+2][j]==y)||(i!=0&&i!=4&&arr[i-1][j]==y&&arr[i+1][j]==y)||(i>1&&arr[i-2][j]==y&&arr[i-1][j]==y)) {
        					playSong2("/colour_bomb1.wav");
            				for(int k=0;k<arr.length;k++) {
            					arr[k][j]=0;
            				}
            				arr[i][j+1]=0;
            				arr[i][j+2]=0;
        				}

    				}catch(Exception ex) {}
    			}
    		}
    	}
    }
    
    
    public void check3(int x,int y) {
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((j<3&&arr[i][j+1]==y&&arr[i][j+2]==y)||(j!=0&&j!=4&&arr[i][j-1]==y&&arr[i][j+1]==y)||(j>1&&arr[i][j-2]==y&&arr[i][j-1]==y)) {
        					playSong2("/colour_bomb1.wav");
        					for(int k=0;k<arr.length;k++) {
            					arr[i][k]=0;
            				}
            				arr[i][j+1]=0;
            				arr[i][j+2]=0;
        				}
        				
        				
    				}catch(Exception ex) {
    					System.out.println("yes");
    				}
    				
    			}
    		}
    	}
    	
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(arr[i][j]==x) {
    				try {
        				if((i<3&&arr[i+1][j]==y&&arr[i+2][j]==y)||(i!=0&&i!=4&&arr[i-1][j]==y&&arr[i+1][j]==y)||(i>1&&arr[i-2][j]==y&&arr[i-1][j]==y)) {
        					playSong2("/colour_bomb1.wav");
            				for(int k=0;k<arr.length;k++) {
            					arr[i][k]=0;
            				}
            				arr[i][j+1]=0;
            				arr[i][j+2]=0;
        				}

    				}catch(Exception ex) {
    					System.out.println("yes");

    				}
    			}
    		}
    	}
    }
    
    public void check4() {
    	for(int i=0;i<arr.length;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(j<3&&i<3&&arr[i][j]==arr[i][j+1]&&arr[i][j]==arr[i][j+2]&&arr[i][j]==arr[i+1][j]&&arr[i+2][j]==arr[i][j]) {
    				if(arr[i][j]==1) {
						arr[i][j]=1111;
					}
					else if(arr[i][j]==2) {
						arr[i][j]=2222;
					}
					else if(arr[i][j]==3) {
						arr[i][j]=3333;
					}
					else if(arr[i][j]==4) {
						arr[i][j]=4444;
					}
    				arr[i][j+1]=0;
    				arr[i][j+2]=0;
    				arr[i+1][j]=0;
    				arr[i+2][j]=0;
    			}
    			
    			else if(j<3&&i>1&&arr[i][j]==arr[i][j+1]&&arr[i][j]==arr[i][j+2]&&arr[i][j]==arr[i-1][j]&&arr[i-2][j]==arr[i][j]) {
    				if(arr[i][j]==1) {
						arr[i][j]=1111;
					}
					else if(arr[i][j]==2) {
						arr[i][j]=2222;
					}
					else if(arr[i][j]==3) {
						arr[i][j]=3333;
					}
					else if(arr[i][j]==4) {
						arr[i][j]=4444;
					}
    				arr[i][j+1]=0;
    				arr[i][j+2]=0;
    				arr[i-1][j]=0;
    				arr[i-2][j]=0;

    			}
    			else if(j!=0&&j!=4&&arr[i][j-1]==arr[i][j]&&arr[i][j+1]==arr[i][j]&&i>1&&arr[i-1][j]==arr[i][j]&&arr[i-2][j]==arr[i][j]) {
    				if(arr[i][j]==1) {
						arr[i][j]=1111;
					}
					else if(arr[i][j]==2) {
						arr[i][j]=2222;
					}
					else if(arr[i][j]==3) {
						arr[i][j]=3333;
					}
					else if(arr[i][j]==4) {
						arr[i][j]=4444;
					}
    				arr[i][j-1]=0;
    				arr[i][j+1]=0;
    				arr[i-1][j]=0;
    				arr[i-2][j]=0;
    			}
    			else if(j!=0&&j!=4&&arr[i][j-1]==arr[i][j]&&arr[i][j+1]==arr[i][j]&&i<4&&arr[i+1][j]==arr[i][j]&&arr[i+2][j]==arr[i][j]) {
    				if(arr[i][j]==1) {
						arr[i][j]=1111;
					}
					else if(arr[i][j]==2) {
						arr[i][j]=2222;
					}
					else if(arr[i][j]==3) {
						arr[i][j]=3333;
					}
					else if(arr[i][j]==4) {
						arr[i][j]=4444;
					}
    				arr[i][j-1]=0;
    				arr[i][j+1]=0;
    				arr[i+1][j]=0;
    				arr[i+2][j]=0;
    			}
    			else if(j>1&&i<4&&arr[i][j]==arr[i][j-1]&&arr[i][j]==arr[i][j-2]&&arr[i][j]==arr[i+1][j]&&arr[i][j]==arr[i+2][j]) {
    				if(arr[i][j]==1) {
						arr[i][j]=1111;
					}
					else if(arr[i][j]==2) {
						arr[i][j]=2222;
					}
					else if(arr[i][j]==3) {
						arr[i][j]=3333;
					}
					else if(arr[i][j]==4) {
						arr[i][j]=4444;
					}
    				arr[i][j-1]=0;
    				arr[i][j-2]=0;
    				arr[i+1][j]=0;
    				arr[i+2][j]=0;
    			}
    		}
    	}
    	update();
    }
    
    public void update() {
    	for(int i=0;i<arr.length-1;i++) {
    		for(int j=0;j<arr.length;j++) {
    			if(arr[i+1][j]==0&& arr[i][j]!=0) {
    			int k=i;
    			while(k>=0) {
    				if(arr[k][j]!=0) {
    					int kk=arr[k][j];
    					arr[k][j]=arr[k+1][j];
    					arr[k+1][j]=kk;
    				}
    				k--;
    		}
    			}
    	}

    	}
    	
    	for (int i = 0, k = 10; i < arr.length; i++, k += 50) {
            for (int j = 0, t = 10; j < arr.length; j++, t += 50) {
	            	Point p = new Point(t, k);
	                pos2[i][j]=p;            	
            }
        }
        drawPanel.repaint();
    	

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

    private void playSong(String resourcePath) {
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
    
    
}