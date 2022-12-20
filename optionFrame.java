//Pattrayus Chokbunlue 6313179
//Thanatorn Ruangrote 6313129
//Thunyavut Nabhaboriraks 6313130

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.*;

class products {
    private String      type;
    private String      name;
    private int         quantity;
    private int         price;
    
    private boolean     excheese;
    private boolean     frenchfries;
    private String      favor;
    private boolean     exsauce;
    private String      sauce;
    private String      vegetable;
    
    private String      size;
    
    private String      employeename;
    private int         tip;
    
    public products(String name, int quantity, int price, boolean excheese, boolean frenchfries, boolean exsauce, String sauce, String favor, String vegetable) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.excheese = excheese; //+30
        this.frenchfries = frenchfries; //+70
        this.exsauce = exsauce; //+10
        this.sauce = sauce;
        this.favor = favor;
        this.vegetable = vegetable;
        type = "burgers";
    }
    
    public products(String name, int quantity, int price, String size) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.size = size; //s += 0, m += 15, l += 20
        type = "drinks";
    }
    
    public void setTip(String employeename, int tip) {
        this.employeename = employeename;
        this.tip = tip;
    }
    
    public String gettype() { return type; }
    public String getname() { return name; }
    public int getquantity() { return quantity; }
    public int getprice() { return price; }
    public boolean getexcheese() { return excheese; }
    public boolean getfrenchfries() { return frenchfries; }
    public String getfavor() { return favor; }
    public boolean getexsauce() { return exsauce; }
    public String getsauce() { return sauce; }
    public String getvegetable() { return vegetable; }
    public String getsize() { return size; }
    public String getemployeename() { return employeename; }
    public int getTip() { return tip; }
}

public class optionFrame extends JFrame {
    private JPanel             contentpane, panel1, panel2, panel6, panel7, paneltt, optionpanel, drinkpanel, burgerpanel,subpanel4,subpanel1,subpanel2,subpanel3;
    private JLabel             drawpane, imageLabel, nameLabel, totalpriceLabel,excheeseprice,frenchfriesprice,exsauceprice,quantityLabel,vegetableLabel;
    private ArrayList<JPanel>  listpanel;
    private JButton            confirm, cancel;
    private JSpinner           quantitySpinner;
    private JCheckBox          excheese, frenchfries, exsauce, tipcheck;
    private JComboBox          sauce, sizeDrink, favor, vegetable, groupname, tip;
    private String             type, name;
    private int                price, quantity, baseprice, tipcost;
    private MySoundEffect      hitSound, openSound, closeSound;
    private MyImageIcon confirmImg,cancelImg;
    
    private int frameWidth = 1066, frameHeight = 768;
    
    public optionFrame() {
	setBounds(0, 0, frameWidth, frameHeight);
        setResizable(true);
        setLocationRelativeTo(null);
	setVisible(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );        
        AddComponents();
    }
    
    public void AddComponents() {
        Font font = new Font("TimesRoman", Font.BOLD, 15);
        hitSound   = new MySoundEffect("resources/minecraft_click.wav");
        openSound   = new MySoundEffect("resources/open.wav");
        closeSound   = new MySoundEffect("resources/close.wav");
        confirmImg = new MyImageIcon("resources/confirm.png").resize(135, 135);
        cancelImg  = new MyImageIcon("resources/cancel.png").resize(135, 135);
        drawpane = new JLabel();
        drawpane.setLayout(new BoxLayout(drawpane, BoxLayout.Y_AXIS));
        drawpane.setBackground(Color.WHITE);
        
        listpanel = new ArrayList<>();
        quantity = 1;
        
        ////////////////////////////////////////////////////////////////////////////////////////
        panel1 = new JPanel( );                                                 panel1.setPreferredSize(new Dimension(1066, 50));
        panel1.setLayout(new BorderLayout() );
        nameLabel = new JLabel();                                               nameLabel.setPreferredSize(new Dimension(300, 20));
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel1.add(nameLabel, BorderLayout.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////////
        panel2 = new JPanel( );                                                 panel2.setPreferredSize(new Dimension(1066, 250));
        panel2.setLayout( new FlowLayout(FlowLayout.CENTER) );
        imageLabel = new JLabel();                                              imageLabel.setPreferredSize(new Dimension(200, 200));
        paneltt = new JPanel();                                                 paneltt.setPreferredSize(new Dimension(1066, 50));
        paneltt.setLayout( new FlowLayout(FlowLayout.CENTER) );
        totalpriceLabel = new JLabel();                                         totalpriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalpriceLabel.setPreferredSize(new Dimension(200, 30));               totalpriceLabel.setFont(font);
        
        paneltt.add(totalpriceLabel);
        panel2.add(imageLabel);
        ////////////////////////////////////////////////////////////////////////////////////////
        panel6 = new JPanel();                                                  panel6.setPreferredSize(new Dimension(1066, 100));
        panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        quantityLabel = new JLabel("Quantity");                                 quantityLabel.setPreferredSize(new Dimension(100, 20));         quantityLabel.setFont(font);
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));    quantitySpinner.setPreferredSize(new Dimension(150, 30));       quantitySpinner.setEditor(new JSpinner.DefaultEditor(quantitySpinner));
        quantitySpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                hitSound.playOnce();
                quantity = Integer.parseInt(quantitySpinner.getValue().toString());
                calculate();
            }
        });
        tipcheck = new JCheckBox("Tip employee");                               tipcheck.setPreferredSize(new Dimension(150, 50));              tipcheck.setFont(font);
        tipcheck.setHorizontalAlignment(JCheckBox.RIGHT);
        //*//
        tipcheck.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
                groupname.setSelectedIndex(0);
                tip.setSelectedIndex(0);
                if (tipcheck.isSelected()) {
                    groupname.setVisible(true);
                }
                else {
                    groupname.setVisible(false);
                    tip.setVisible(false);
                }
                calculate();
            }
	});
        tipcheck.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        String [] groupnamelist = {"Choose employee", "Pattrayus Chokbunlue 6313179", "Thanatorn Ruangrote 6313129", "Thunyavut Nabhaboriraks 6313130"};
        groupname = new JComboBox(groupnamelist);                               groupname.setPreferredSize(new Dimension(300, 30));             groupname.setFont(font);
        groupname.setVisible(false);
        groupname.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
                tip.setSelectedIndex(0);
                if (groupname.getSelectedIndex() != 0) {
                    tip.setVisible(true);
                    calculate();
                }
                else {
                    tip.setVisible(false);
                }
                    
            }
	});
        groupname.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        String [] tiplist = {"+5 Bath", "+10 Bath", "+15 Bath"};
        tip = new JComboBox(tiplist);                                           tip.setPreferredSize(new Dimension(150, 30));                   tip.setFont(font);
        tip.setVisible(false);
        tip.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
                calculate();
            }
	});
        tip.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        
        panel6.add(quantityLabel);
        panel6.add(quantitySpinner);
        panel6.add(tipcheck);
        panel6.add(groupname);
        panel6.add(tip);
        ////////////////////////////////////////////////////////////////////////////////////////
        panel7 = new JPanel();                                                  panel7.setPreferredSize(new Dimension(1066, 168));
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER,180,0) );
        confirm = new JButton(confirmImg);                                       confirm.setPreferredSize(new Dimension(80, 80));
        cancel = new JButton(cancelImg);                                         cancel.setPreferredSize(new Dimension(80, 80));
        
        panel7.add(confirm);
        panel7.add(cancel);
        ////////////////////////////////////////////////////////////////////////////////////////
        optionpanel = new JPanel(); 
        optionpanel.setLayout( new BoxLayout(optionpanel, BoxLayout.Y_AXIS) );
        optionpanel.setPreferredSize(new Dimension(1066, 150));
        optionpanel.setBackground(Color.WHITE);
        ////////////////////////////////////////////////////////////////////////////////////////
        drinkpanel = new JPanel();                                              drinkpanel.setLayout( new FlowLayout(FlowLayout.CENTER) );              drinkpanel.setPreferredSize(new Dimension(1066, 150));
        String [] sizelist = {"Small", "Medium", "Large"};
        sizeDrink = new JComboBox(sizelist);                                    sizeDrink.setPreferredSize(new Dimension(150, 30));                     sizeDrink.setBackground(Color.WHITE);
        JLabel sizeDrinkLabel = new JLabel("Size");                             sizeDrinkLabel.setPreferredSize(new Dimension(150, 30));                sizeDrinkLabel.setFont(font);
        sizeDrink.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
                calculate();
            }
	});
        sizeDrink.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        
        drinkpanel.add(sizeDrink);
        drinkpanel.add(sizeDrinkLabel);
        ////////////////////////////////////////////////////////////////////////////////////////
        burgerpanel = new JPanel();                                             burgerpanel.setLayout( new GridLayout(3, 2) );                          burgerpanel.setPreferredSize(new Dimension(1066, 150));
        subpanel1 = new JPanel();                                        subpanel1.setLayout( new FlowLayout(FlowLayout.LEFT) );
        excheese = new JCheckBox("Extra cheese");                               excheese.setPreferredSize(new Dimension(150, 50));                      excheese.setFont(font);
        excheeseprice = new JLabel("+30 Bath");                          excheeseprice.setPreferredSize(new Dimension(150, 50));                 excheeseprice.setVisible(false);
        excheeseprice.setFont(font);
        excheese.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
		excheeseprice.setVisible(!excheeseprice.isVisible());
                calculate();
            }
	});
        excheese.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        String [] favorlist = {"Spicy", "Mayonnaise", "Tomato sauce", "Salt", "No Sauce"};
        favor = new JComboBox(favorlist);                                       favor.setPreferredSize(new Dimension(150, 30));                          favor.setVisible(false);
        favor.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        favor.setFont(font);
        subpanel2 = new JPanel();                                        subpanel2.setLayout( new FlowLayout(FlowLayout.LEFT) );
        frenchfries = new JCheckBox("French fries");                            frenchfries.setPreferredSize(new Dimension(150, 50));                   frenchfries.setFont(font);
        frenchfriesprice = new JLabel("+70 Bath");                       frenchfriesprice.setPreferredSize(new Dimension(150, 50));              frenchfriesprice.setVisible(false);
        frenchfriesprice.setFont(font);
        frenchfries.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
		frenchfriesprice.setVisible(!frenchfriesprice.isVisible());
                calculate();
                favor.setVisible(!favor.isVisible());
            }
	});
        frenchfries.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        String [] saucelist = {"Tomato Sauce", "Chilli Sauce", "Barbeque Sauce", "Mayonnaise", "No sauce"};
        sauce = new JComboBox(saucelist);                                       sauce.setPreferredSize(new Dimension(150, 30));                         sauce.setVisible(false);
        sauce.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        sauce.setFont(font);
        subpanel3 = new JPanel();                                        subpanel3.setLayout( new FlowLayout(FlowLayout.LEFT) );
        exsauce = new JCheckBox("Extra sauce");                                 exsauce.setPreferredSize(new Dimension(150, 50));                       exsauce.setFont(font);
        exsauceprice = new JLabel("+10 Bath");                           exsauceprice.setPreferredSize(new Dimension(150, 50));                  exsauceprice.setVisible(false);
        exsauceprice.setFont(font);
        exsauce.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
		exsauceprice.setVisible(!exsauceprice.isVisible());
                calculate();
                sauce.setVisible(!sauce.isVisible());
            }
	});
        exsauce.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        subpanel4 = new JPanel();                                        subpanel4.setLayout( new FlowLayout(FlowLayout.LEFT) );
        String [] vegetablelist = {"Tomato", "Onion", "Lettuce"};
        vegetable = new JComboBox(vegetablelist);                               vegetable.setPreferredSize(new Dimension(150, 30));                     vegetable.setFont(font);
        vegetable.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
            }
        });
        vegetableLabel = new JLabel("Vegetable");                        vegetableLabel.setPreferredSize(new Dimension(150, 50));                vegetableLabel.setFont(font);
        
        subpanel1.add(excheese);
        subpanel1.add(excheeseprice);
        subpanel2.add(frenchfries);
        subpanel2.add(frenchfriesprice);
        subpanel2.add(favor);
        subpanel3.add(exsauce);
        subpanel3.add(exsauceprice);
        subpanel3.add(sauce);
        subpanel4.add(vegetableLabel);
        subpanel4.add(vegetable);
        burgerpanel.add(subpanel1);
        burgerpanel.add(subpanel2);
        burgerpanel.add(subpanel3);
        burgerpanel.add(subpanel4);
        ////////////////////////////////////////////////////////////////////////////////////////
        
        listpanel.add(burgerpanel);
        listpanel.add(drinkpanel);
        drawpane.add(panel1);
        drawpane.add(panel2);   
        drawpane.add(paneltt);
        drawpane.add(optionpanel);
        drawpane.add(panel6);
        drawpane.add(panel7);
        contentpane.add(drawpane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            closeSound.playOnce();
            }
        });
        validate();
    }
    public void darkthemeoption(){
    
       panel1.setBackground(Color.DARK_GRAY);
       panel2.setBackground(Color.GRAY);
       panel6.setBackground(Color.GRAY);
       panel7.setBackground(Color.DARK_GRAY);
       paneltt.setBackground(Color.DARK_GRAY);
       
       excheese.setBackground(Color.GRAY);    excheese.setForeground(Color.BLACK); excheeseprice.setForeground(Color.BLACK);
       frenchfries.setBackground(Color.GRAY); frenchfries.setForeground(Color.BLACK); frenchfriesprice.setForeground(Color.BLACK);
       exsauce.setBackground(Color.GRAY);     exsauce.setForeground(Color.BLACK); exsauceprice.setForeground(Color.BLACK);
       drinkpanel.setBackground(Color.GRAY); 
       burgerpanel.setBackground(Color.GRAY); 
       nameLabel.setForeground(Color.WHITE);
       totalpriceLabel.setForeground(Color.WHITE);
       subpanel4.setBackground(Color.GRAY);
       subpanel1.setBackground(Color.GRAY);
       subpanel2.setBackground(Color.GRAY);
       subpanel3.setBackground(Color.GRAY);
       tipcheck.setBackground(Color.GRAY); tipcheck.setForeground(Color.BLACK);
       quantityLabel.setForeground(Color.BLACK);
       vegetableLabel.setForeground(Color.BLACK);
       burgerpanel.setBorder(BorderFactory.createLineBorder(Color.black)); 
       drinkpanel.setBorder(BorderFactory.createLineBorder(Color.black)); 
    }
    public void lightthemeoption(){
    
       panel1.setBackground(Color.LIGHT_GRAY);
       panel2.setBackground(Color.WHITE);
       panel6.setBackground(Color.WHITE);
       panel7.setBackground(Color.WHITE);
       paneltt.setBackground(Color.WHITE);
       
       excheese.setBackground(Color.WHITE);    excheese.setForeground(Color.BLACK); excheeseprice.setForeground(Color.BLACK);
       frenchfries.setBackground(Color.WHITE); frenchfries.setForeground(Color.BLACK); frenchfriesprice.setForeground(Color.BLACK);
       exsauce.setBackground(Color.WHITE);     exsauce.setForeground(Color.BLACK); exsauceprice.setForeground(Color.BLACK);
       drinkpanel.setBackground(Color.WHITE); 
       burgerpanel.setBackground(Color.WHITE); 
       nameLabel.setForeground(Color.BLACK);
       totalpriceLabel.setForeground(Color.BLACK);
       subpanel4.setBackground(Color.WHITE);
       subpanel1.setBackground(Color.WHITE);
       subpanel2.setBackground(Color.WHITE);
       subpanel3.setBackground(Color.WHITE);
       tipcheck.setBackground(Color.WHITE); tipcheck.setForeground(Color.BLACK);
       quantityLabel.setForeground(Color.BLACK);
       vegetableLabel.setForeground(Color.BLACK);
       burgerpanel.setBorder(BorderFactory.createLineBorder(Color.black)); 
       drinkpanel.setBorder(BorderFactory.createLineBorder(Color.black)); 
    }
    public void Process(String type, MyImageIcon image, String name, int price) {
        setTitle("Design your " + name);
        imageLabel.setIcon(image);
        nameLabel.setText(name);
        this.type = type;
        this.name = name;
        this.price = price;
        totalpriceLabel.setText("Total : " + String.valueOf(price));
        baseprice = this.price;
        //reset
        quantitySpinner.setValue(1);
        sizeDrink.setSelectedIndex(0);
        excheese.setSelected(false);
        frenchfries.setSelected(false);
        exsauce.setSelected(false);
        sauce.setSelectedIndex(0);
        tipcheck.setSelected(false);
        tipcost = 0;
        calculate();
        if (type.equals("burgers")) {
            optionpanel.removeAll();
            optionpanel.add(burgerpanel);
        }
        else if (type.equals("drinks")) {
            optionpanel.removeAll();
            optionpanel.add(drinkpanel);
        }
    }
    
    public products confirmed() {
        products products = null;
        if (type.equals("burgers")) {
            boolean morecheese = excheese.isSelected();
            boolean morefrenchfries = frenchfries.isSelected();
            boolean moresauce = exsauce.isSelected();
            String saucetype = sauce.getSelectedItem().toString();
            String favorname = favor.getSelectedItem().toString();
            String vegetablename = vegetable.getSelectedItem().toString();
            products = new products(name, quantity, price, morecheese, morefrenchfries, moresauce, saucetype, favorname, vegetablename);
        }
        else if (type.equals("drinks")) {
            String size = sizeDrink.getSelectedItem().toString();
            products = new products(name, quantity, price, size);
        }
        if (tipcheck.isSelected() && groupname.getSelectedIndex() != 0)
            products.setTip(groupname.getSelectedItem().toString() ,tipcost);
        else
            products.setTip(null, 0);
        return products;
    }
    
    public void calculate() {
        price = baseprice * quantity;
        if (type.equals("burgers")) {
            if (excheese.isSelected())
                price += 30 * quantity;
            if (frenchfries.isSelected())
                price += 70 * quantity;
            if (exsauce.isSelected())
                price += 10 * quantity;
        }
        else if (type.equals("drinks")) {
            int index = sizeDrink.getSelectedIndex();
            switch (index) {
                case 1:
                    price += 15 * quantity;
                    break;
                case 2:
                    price += 20 * quantity;
                    break;
            }
        }
        if (tip.isVisible())
            tipcost = (tip.getSelectedIndex()+1)*5;
        else
            tipcost = 0;
        totalpriceLabel.setText("Total : " + String.valueOf(price+tipcost));
    }
    
    public JButton getCancelButton()  { return cancel; }
    public JButton getConfirmButton() { return confirm; }
}