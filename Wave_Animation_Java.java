import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Wave_Animation_Java extends JFrame 
{
    private static final long serialVersionUID = 1L;
    JTextField Zl;
    JLabel Zl_label;
    JTextField Z0;
    JLabel Z0_label;
    JTextField amplitude;
    JLabel amplitude_label;
    JButton show;
    JButton exit;
    JRadioButton AllInOne;
    JRadioButton three;
    ButtonGroup btn2;

    Wave_Animation_Java()
    {
        setTitle("Standing Wave Animation by Rutvik Patel (17BIT048)");
        Zl = new JTextField(5);
        Zl_label = new JLabel("Enter Value of Zl : ");
        Z0 = new JTextField(5);
        Z0_label = new JLabel("Enter Value of Z0: ");
        amplitude = new JTextField(5);
        amplitude_label = new JLabel("Enter Wave Amplitude : ");
        show = new JButton("Plot Graph Animation");
        exit = new JButton("Exit");
        three = new JRadioButton("Plot Incident Wave, Reflected Wave and Standing Wave in Different Graphs");
        AllInOne = new JRadioButton("Plot Incident Wave, Reflected Wave and Standing Wave in same Graph");
        btn2 = new ButtonGroup();
        btn2.add(AllInOne);
        btn2.add(three);
        add(Zl_label);
        add(Zl);
        add(Z0_label);
        add(Z0);
        add(amplitude_label);
        add(amplitude);
        add(three);
        add(AllInOne);
        add(show);
        add(exit);
        setVisible(true);
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        show.addActionListener(new CActionListener(this));
        exit.addActionListener(new CActionListener(this));
    }

    public String getReflectionCoeff()
    {
        float Z0_float = Float.parseFloat(this.Z0.getText());
        float Zl_float = Float.parseFloat(this.Zl.getText());
        float ref_coeff = (Zl_float - Z0_float) / (Zl_float + Z0_float);
        if(ref_coeff < 0 || ref_coeff > 1)
        {
            System.out.println("Invalid Values");
            System.exit(-1);
        }
        return Float.toString(ref_coeff);
    }
    
    public String getAmplitude() 
    {
        return this.amplitude.getText();
    }

    public String type_pyplot()
    {
        return "1";
    }

    public String type_3or1()
    {
        String type = "0";
        if (this.three.isSelected())
            type = "1"; // 3 plots if 1
        return type;
    }
    public static void main(String[] args) 
    {
        Wave_Animation_Java wap = new Wave_Animation_Java();
    }
}

class CActionListener implements ActionListener 
{
    Wave_Animation_Java obj;

    CActionListener(Wave_Animation_Java obj) 
    {
        super();
        this.obj = obj;
    }

    public void actionPerformed(ActionEvent event) 
    {
        String s = event.getActionCommand();
        if (s.equals("Plot Graph Animation"))
        {
            String[] command1 = { "python3", "Wave_Animation_complex.py", obj.getReflectionCoeff(), obj.getAmplitude(),obj.type_3or1()};
            String[] command2 = { "python", "Wave_Animation_complex.py", obj.getReflectionCoeff(), obj.getAmplitude(),obj.type_3or1()};
            ProcessBuilder pb;
            if(System.getProperty("os.name").contains("Windows"))
            {
                pb = new ProcessBuilder(command2);
            }
            else
            {
                pb = new ProcessBuilder(command1);
            }
            try
            {
                pb.start();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
        else if (s.equals("Exit")) 
        {
            obj.dispose();
            System.exit(0);
        }
    }
}
