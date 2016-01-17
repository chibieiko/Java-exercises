import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class BMIcalc extends JFrame implements ActionListener {
 
    private JButton click;
    private final static String newline = "\n";
    JLabel info, info2, info3;
    JTextField heightText, weightText;
 
    // Create a GUI for BMI calculator.
    public MyWindow() {
        // Create button.
        click = new JButton();
        click.setText("Calculate BMI");
        click.addActionListener(this);
        // Create area for text height and input height.
        info = new JLabel("Height e.g. 1.60 ");
        add(info);
        heightText = new JTextField(5);
        add(heightText);
        heightText.addActionListener(this);
        // Create area for text weight and input weight.
        info2 = new JLabel("Weight ");
        add(info2);
        weightText = new JTextField(5);
        add(weightText);
        weightText.addActionListener(this);
        add(click);
        // Create area for text Result.
        info3 = new JLabel("Result = ");
        add(info3);
        // Set layout
        setLayout(new FlowLayout());
        setSize(600,100);
        setVisible(true);
    }
 
    // Determine variables.
    String text = new String();
    String text1 = new String();
    String youR = new String();
	String result1 = new String();
    float bmi;

    public void actionPerformed(ActionEvent e) {
        // Button clicked, determine values for height and weight, calculate BMI.
        System.out.println("click");
        text = heightText.getText();
        text1 = weightText.getText();
        float weight = Float.parseFloat(text1);
        float height = Float.parseFloat(text);
        bmi = weight / (height * height);
		result1 = String.format("%.1f", bmi);
        // Determine the category.
        if(bmi < 15) {
            youR = ("Anorectic :O");
        } else if(bmi >= 15 && bmi < 17) {
            youR = ("Extremely underweight :/");
        } else if(bmi >= 17 && bmi < 18.5) {
            youR = ("Underweight!");
        } else if(bmi >= 18.5 && bmi <25) {
            youR = ("Normal weight :D");
        } else if(bmi >= 25 && bmi < 30) {
            youR = ("Slightly overweight!");
        } else if(bmi >= 30 && bmi < 35) {
            youR = ("Overweight :/");
        } else if(bmi >= 35 && bmi < 40) {
            youR = ("Slightly obese :(");       
        } else {
            youR = ("Obese :O");
        }
        // Input the bmi and category result to JLabel.
        info3.setText("Result = " + result1 + " " + youR);
    }
 
    public static void main(String [] args) {
        new MyWindow();   
    }    
}