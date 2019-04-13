import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Food extends JPanel/*extends Tile*/{
	ImageIcon apple = new ImageIcon(new ImageIcon("FoodIcons/apple.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon banana = new ImageIcon(new ImageIcon("FoodIcons/banana.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon pretzel = new ImageIcon(new ImageIcon("FoodIcons/pretzel.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon cheese = new ImageIcon(new ImageIcon("FoodIcons/cheese.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon cupcake = new ImageIcon(new ImageIcon("FoodIcons/cupcake.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon donut = new ImageIcon(new ImageIcon("FoodIcons/donut.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	private int randomNumber;
	private Graphics g;
	private int food_x = 50;
    private int food_y = 75;
	private JPanel panel;
	private JLabel label;


	public Food(/*Point p*/){
		//gridPoint = p;
		ImageIcon image = chooseImage();
		panel = new JPanel();
		label = new JLabel(image);
		panel.add(label);
		add(panel);
	}

	public void paintComponent(Graphics g, ImageIcon image) {
        super.paintComponent(g);
			image.paintIcon(this, g, food_x, food_y);
    }

	public ImageIcon chooseImage(){
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


}