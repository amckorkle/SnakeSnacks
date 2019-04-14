import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;



public class Food extends Tile{
	Image apple;
	Image banana;
	Image pretzel;
	Image cheese;
	Image cupcake;
	Image donut;
	private int randomNumber;
	private Graphics graphic;
	private int food_x;
    private int food_y;
	private JPanel panel;
	private JLabel label;
	private final int RAND_POS = 460;


	public Food(){
		try{
			apple = ImageIO.read(new File("FoodIcons/apple.png"));
			banana = ImageIO.read(new File("FoodIcons/banana.png"));
			pretzel = ImageIO.read(new File("FoodIcons/pretzel.png"));
			cheese = ImageIO.read(new File("FoodIcons/cheese.png"));
			cupcake = ImageIO.read(new File("FoodIcons/cupcake.png"));
			donut = ImageIO.read(new File("FoodIcons/donut.png"));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		

	}

	public void paint(Graphics graphic, int food_x, int food_y) {
        //super.paint(g);
		Image image = chooseImage();
        graphic.drawImage(image, food_x, food_y, 40, 40, null);	
   }

	public Image chooseImage(){
		int randomNumber = generateRandomNumber();
		if(randomNumber == 0)
        	return apple;
		else if(randomNumber == 1)
			return banana;
		else if(randomNumber == 2)
			return pretzel;
		else if(randomNumber == 3)
			return cheese;
		else if(randomNumber == 4)
			return cupcake;
		else
			return donut;
	}

	//public display(){

	//}

	public int generateRandomNumber(){
		Random rand = new Random();
        int number = rand.nextInt(6);
		return number;
	}

	private int locateFoodCoord() {
        int coordinate = (int)(Math.random() * RAND_POS);
        return coordinate;
    }



}