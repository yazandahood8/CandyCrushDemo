package candy;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

class DrawPanel extends JPanel {
    private BufferedImage resizedImage[]=new BufferedImage[17];
    private int[][] arr;
    private Point[][] pos2;

        public DrawPanel(String []imagePath,int[][] arr,Point[][] pos2) {
            this.arr=arr;
            this.pos2=pos2;
            try {
                for(int i=0;i<imagePath.length;i++) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imagePath[i]);
                
                if (inputStream != null) {
                    BufferedImage originalImage = ImageIO.read(inputStream);
                     resizedImage[i] = resizeImage(originalImage, 30, 30);

                     
                     } 
                else {
                    System.err.println("Image not found: " + imagePath);
                }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (int i = 0; i <= 300; i += 50) {
            g.fillRect(0, i, 300, 2);
            g.fillRect(i, 0, 2, 300);
        }

        int ind = 0;
        for (int i = 0, k = 10; i < arr.length; i++, k += 50) {
            for (int j = 0, t = 10; j < arr.length; j++, t += 50) {
                if (arr[i][j] == 1) 
                    //g.setColor(Color.BLACK);
                    g.drawImage(resizedImage[0], pos2[i][j].x, pos2[i][j].y, this);
//
                else if (arr[i][j] == 2)
                    g.drawImage(resizedImage[1], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 3)
                    g.drawImage(resizedImage[2], pos2[i][j].x, pos2[i][j].y, this);

                else if (arr[i][j] == 4) 
                    g.drawImage(resizedImage[3], pos2[i][j].x, pos2[i][j].y, this);
                
                else if (arr[i][j] == 11) 
                    g.drawImage(resizedImage[4], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 22) 
                    g.drawImage(resizedImage[5], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 33) 
                    g.drawImage(resizedImage[6], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 44) 
                    g.drawImage(resizedImage[7], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 111) 
                    g.drawImage(resizedImage[8], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 222) 
                    g.drawImage(resizedImage[9], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 333) 
                    g.drawImage(resizedImage[10], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 444) 
                    g.drawImage(resizedImage[11], pos2[i][j].x, pos2[i][j].y, this);
                
                
                else if (arr[i][j] == 1111) 
                    g.drawImage(resizedImage[12], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 2222) 
                    g.drawImage(resizedImage[13], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 3333) 
                    g.drawImage(resizedImage[14], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 4444) 
                    g.drawImage(resizedImage[15], pos2[i][j].x, pos2[i][j].y, this);
                else if (arr[i][j] == 99) 
                    g.drawImage(resizedImage[16], pos2[i][j].x, pos2[i][j].y, this);

                

            }
        }
    }
    
    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        Image tmp = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}
    