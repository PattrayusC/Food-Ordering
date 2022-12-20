//Pattrayus Chokbunlue 6313179
//Thanatorn Ruangrote 6313129
//Thunyavut Nabhaboriraks 6313130

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class MainApplication extends JFrame {
    private static optionFrame      opFrame;
    private static purchaseFrame    pcFrame;
    
    private JPanel                  contentpane, panel1, panel2, panel3, burgerpage, drinkpage,textPanel,confirmPanel,cataloguepage;
    private JScrollPane             catalogue, menu;
    private JLabel                  drawpane, logo, title;
    private JTextField              text;
    private JButton                 confirm;
    private MyImageIcon             confirmImg, cancelImg, bigmacImg, colaImg, fantaImg, fishImg, chickenImg, logoImg, spriteImg, sumburgerImg, backgroundImg, colacataImg,
                                    muteImg, angusImg, bigamericaImg, blackhamburgerImg, crispybaconImg, goldmoonburgerImg, japantexasImg, maharajaburgerImg, sunriseburgerImg,
                                    sevenupImg, sevenupcanImg, pepsiImg, pepsicanImg;
    private ArrayList<JPanel>       pages, burgers, drinks;
    private ArrayList<JLabel>       cataloguelist;
    private ArrayList<products>     productlist;
    private JCheckBox               white, black, mute;
    private ButtonGroup             bgroup;
    private MySoundEffect           hitSound, themeSound, openSound, closeSound;
    
    private int frameWidth = 1366, frameHeight = 768;
    
    private Font font1 = new Font("TimesRoman", Font.BOLD, 50);
    private Font font2 = new Font("TimesRoman", Font.BOLD, 30);
    
    public static void main(String[] args) {
        opFrame = new optionFrame();
        pcFrame = new purchaseFrame();
        new MainApplication();
    }
    
    public MainApplication() {
        setTitle("McDonald Menu");
	setBounds(0, 0, frameWidth, frameHeight);
        setResizable(true);
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );        
        AddComponents();
    }
    
    public void AddComponents() {
        backgroundImg = new MyImageIcon("resources/background.jpg").resize(frameWidth, frameHeight);
        logoImg = new MyImageIcon("resources/mclogo.png").resize(90, 90);
        sumburgerImg = new MyImageIcon("resources/sumburger.jpg").resize(200, 200);
        colacataImg = new MyImageIcon("resources/colacata.jpg").resize(200, 200); 
        colaImg = new MyImageIcon("resources/cola.png").resize(200, 200);   
        fantaImg = new MyImageIcon("resources/fanta.png").resize(200, 200);
        spriteImg = new MyImageIcon("resources/sprite.png").resize(200, 200);
        bigmacImg = new MyImageIcon("resources/bigmac.png").resize(200, 200);
        fishImg = new MyImageIcon("resources/Filet-O-Fish.png").resize(200, 200);
        chickenImg = new MyImageIcon("resources/McChicken.png").resize(200, 200);
        
        angusImg = new MyImageIcon("resources/angus.png").resize(200, 200);
        bigamericaImg = new MyImageIcon("resources/bigamerica.png").resize(200, 200);
        blackhamburgerImg = new MyImageIcon("resources/blackhamburger.png").resize(200, 200);
        crispybaconImg = new MyImageIcon("resources/crispybacon.png").resize(200, 200);
        goldmoonburgerImg = new MyImageIcon("resources/goldmoonburger.png").resize(200, 200);
        japantexasImg = new MyImageIcon("resources/japantexas.png").resize(200, 200);
        maharajaburgerImg = new MyImageIcon("resources/maharajaBurger.png").resize(200, 200);
        sunriseburgerImg = new MyImageIcon("resources/sunriseburger.png").resize(200, 200);
        
        sevenupImg = new MyImageIcon("resources/7up.png").resize(200, 200);
        sevenupcanImg = new MyImageIcon("resources/7upcan.png").resize(200, 200);
        pepsiImg = new MyImageIcon("resources/pepsibottle.png").resize(200, 200);
        pepsicanImg = new MyImageIcon("resources/pepsican.png").resize(200, 200);
        
        muteImg = new MyImageIcon("resources/mute.png").resize(90, 90);
        confirmImg = new MyImageIcon("resources/confirm.png").resize(150, 150);
        themeSound = new MySoundEffect("resources/theme.wav"); themeSound.playLoop();
        hitSound   = new MySoundEffect("resources/minecraft_click.wav");
        openSound   = new MySoundEffect("resources/open.wav");
        closeSound   = new MySoundEffect("resources/close.wav");
        
        drawpane = new JLabel();
        drawpane.setLayout( new BoxLayout(drawpane, BoxLayout.Y_AXIS) );
        
        burgers = new ArrayList<>();
        drinks = new ArrayList<>();
        pages = new ArrayList<>();
        cataloguelist = new ArrayList<>();
        productlist = new ArrayList<>();
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panel1 = new JPanel();                          panel1.setPreferredSize(new Dimension(1366, 100));                      panel1.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        logo = new JLabel(logoImg);                     logo.setPreferredSize(new Dimension(100, 100));
        logo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
                if (!themeSound.isPlaying()) {
                    themeSound.playLoop();
                    logo.setIcon(logoImg);
                }
                else {
                    themeSound.stop();
                    logo.setIcon(muteImg);
                }
            }
        });
        title = new JLabel("Burgers");                  title.setPreferredSize(new Dimension(500, 100));                        title.setFont(font1);
        gbc.gridx = 0;  
        gbc.gridy = 0;
        panel1.add(logo, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;  
        gbc.gridy = 0;
        panel1.add(title, gbc);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panel2 = new JPanel();                          panel2.setPreferredSize(new Dimension(1366, 500));                      panel2.setLayout( new FlowLayout(FlowLayout.LEFT, 0, 0) );
        catalogue = new JScrollPane();                  catalogue.setBorder(BorderFactory.createLineBorder(Color.black));       catalogue.setPreferredSize(new Dimension(200, 460));          catalogue.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setItemCatalogue();
        menu = new JScrollPane();                       menu.setBorder(BorderFactory.createLineBorder(Color.black));            menu.setPreferredSize(new Dimension(1150, 460));              menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setItemMenu();
        panel2.add(catalogue);
        panel2.add(menu);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        panel3 = new JPanel();                          panel3.setPreferredSize(new Dimension(1366, 168));                      panel3.setLayout( new GridBagLayout() );
        confirmPanel = new JPanel();
        confirm = new JButton(confirmImg);              confirm.setPreferredSize(new Dimension(100, 100));                      confirm.setEnabled(false);
        //*//
        confirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (confirm.isEnabled()) {
                    hitSound.playOnce();
                    pcFrame.setItemOrder(productlist);
                    pcFrame.setVisible(true);
                    setVisible(false);
                }
            }
        });
        textPanel = new JPanel();                       textPanel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
        text = new JTextField("0", 5);                  text.setPreferredSize(new Dimension(100, 50));                          text.setEditable(false);                                     text.setFont(font2);
        gbc.weightx = 0.05;
        gbc.gridx = 1;  
        gbc.gridy = 0;
        confirmPanel.add(confirm);
        panel3.add(confirmPanel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;  
        gbc.gridy = 0;
        textPanel.add(text, gbc);
        panel3.add(textPanel, gbc);
         bgroup = new ButtonGroup();
        
        white = new JCheckBox("LIGHT THEME");                               white.setPreferredSize(new Dimension(50, 25));
        white.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
		lightthememain();
                pcFrame.lightthemepurchase();
                opFrame.lightthemeoption();
            }
	});
        white.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        
        bgroup.add(white);
        black = new JCheckBox("DARK THEME");                               black.setPreferredSize(new Dimension(50, 30));
        black.addItemListener( new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
		darkthememain();
                opFrame.darkthemeoption();
                pcFrame.darkthemepurchase();
            }
	});
        black.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
            }
        });
        
        bgroup.add(black);
        white.setSelected(true);
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel3.add(white,gbc);
        
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        panel3.add(black,gbc);
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Purchase
        JButton pcconfirm = pcFrame.getConfirmButton();
        pcconfirm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                hitSound.playOnce();
                JOptionPane.showMessageDialog(null, "Thank you! Please wait at your seat.", "Purchase confirmed.", JOptionPane.DEFAULT_OPTION);
                opFrame.dispose();
                pcFrame.dispose();
                dispose();
            }
        });
        JButton pcback = pcFrame.getBackButton();
        pcback.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                int prices = 0;
                for (products obj : productlist)
                    prices += obj.getprice();
                text.setText(String.valueOf(prices));
                if (productlist.size() == 0)
                    confirm.setEnabled(false);
                pcFrame.setVisible(false);
                setVisible(true);
                closeSound.playOnce();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Option
        JButton opconfirm = opFrame.getConfirmButton();
        opconfirm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                productlist.add(opFrame.confirmed());
                int prices = 0;
                for (products obj : productlist) {
                    prices += obj.getprice();
                    prices += obj.getTip();
                }
                text.setText(String.valueOf(prices));
                confirm.setEnabled(true);
                opFrame.setVisible(false);
                setVisible(true);
                hitSound.playOnce();
            }
        });
        JButton opcancel = opFrame.getCancelButton();
        opcancel.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                opFrame.setVisible(false);
                setVisible(true);
                closeSound.playOnce();
            }
        });
        
        drawpane.add(panel1);
        drawpane.add(panel2);
        drawpane.add(panel3);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
    public void darkthememain(){
                panel1.setBackground(Color.DARK_GRAY);
                panel2.setBackground(Color.DARK_GRAY);
                panel3.setBackground(Color.DARK_GRAY);
                text.setBackground(Color.LIGHT_GRAY);
                text.setForeground(Color.BLACK);
                textPanel.setBackground(Color.DARK_GRAY);
                white.setBackground(Color.DARK_GRAY);
                black.setBackground(Color.DARK_GRAY);
                white.setForeground(Color.WHITE);
                black.setForeground(Color.WHITE);
                title.setForeground(Color.WHITE);
                confirmPanel.setBackground(Color.DARK_GRAY);
                burgerpage.setBackground(Color.GRAY);
                drinkpage.setBackground(Color.GRAY);
                cataloguepage.setBackground(Color.GRAY);
    }
    public void lightthememain(){
                panel1.setBackground(Color.WHITE);
                panel2.setBackground(Color.WHITE);
                panel3.setBackground(Color.WHITE);
                text.setBackground(Color.WHITE);
                text.setForeground(Color.BLACK);
                textPanel.setBackground(Color.WHITE);
                white.setBackground(Color.WHITE);
                black.setBackground(Color.WHITE);
                white.setForeground(Color.BLACK);
                black.setForeground(Color.BLACK);
                title.setForeground(Color.BLACK);
                confirmPanel.setBackground(Color.WHITE);
                burgerpage.setBackground(Color.WHITE);
                drinkpage.setBackground(Color.WHITE);
                cataloguepage.setBackground(Color.WHITE);
    }
    public void setItemCatalogue() {
        cataloguepage = new JPanel();                       cataloguepage.setLayout( new BoxLayout(cataloguepage, BoxLayout.Y_AXIS) );
        JLabel button1 = new JLabel(sumburgerImg);          button1.setBorder(BorderFactory.createLineBorder(Color.black));
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
                title.setText("Burgers");
                menu.getViewport().add(burgerpage);
            }
        });
        JLabel button2 = new JLabel(colacataImg);           button2.setBorder(BorderFactory.createLineBorder(Color.black));
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
                title.setText("Drinks");
                menu.getViewport().add(drinkpage);
            }
        });
        
        cataloguelist.add(button1);
        cataloguelist.add(button2);
        cataloguepage.add(button1);
        cataloguepage.add(button2);
        cataloguepage.setPreferredSize(cataloguepage.getPreferredSize()); 
        catalogue.getViewport().add(cataloguepage);
    }
    
    public void setItemMenu() {
        int y = 0;
        burgerpage = new JPanel();                      burgerpage.setLayout( new GridBagLayout() );
        drinkpage = new JPanel();                       drinkpage.setLayout( new GridBagLayout() );
        
        setItemMenu(burgerpage, burgers, bigmacImg, "Big Mac", 199, y+=1);
        setItemMenu(burgerpage, burgers, fishImg, "Filet-O-Fish", 159, y+=1);
        setItemMenu(burgerpage, burgers, chickenImg, "McChicken", 99, y+=1);
        setItemMenu(burgerpage, burgers, angusImg, "AngusBurger", 129, y+=1);
        setItemMenu(burgerpage, burgers, bigamericaImg, "BigAmerica", 319, y+=1);
        setItemMenu(burgerpage, burgers, blackhamburgerImg, "BlackHamburger", 159, y+=1);
        setItemMenu(burgerpage, burgers, crispybaconImg, "CrispyBacon", 169, y+=1);
        setItemMenu(burgerpage, burgers, goldmoonburgerImg, "GoldmoonBurger", 379, y+=1);
        setItemMenu(burgerpage, burgers, japantexasImg, "JapanTexas", 169, y+=1);
        setItemMenu(burgerpage, burgers, maharajaburgerImg, "MahajaraBurger", 299, y+=1);
        setItemMenu(burgerpage, burgers, sunriseburgerImg, "SunriseBurger", 189, y+=1);
        
        burgerpage.setPreferredSize(burgerpage.getPreferredSize());
        
        y = 0;
        
        setItemMenu(drinkpage, drinks, colaImg, "Cola", 79, y+=1);
        setItemMenu(drinkpage, drinks, fantaImg, "Fanta", 59, y+=1);
        setItemMenu(drinkpage, drinks, spriteImg, "Sprite", 59, y+=1);
        setItemMenu(drinkpage, drinks, sevenupImg, "7-up", 69, y+=1);
        setItemMenu(drinkpage, drinks, sevenupcanImg, "7-up(can)", 59, y+=1);
        setItemMenu(drinkpage, drinks, pepsiImg, "Pepsi", 79, y+=1);
        setItemMenu(drinkpage, drinks, pepsicanImg, "Pepsi(can)", 69, y+=1);
        
        drinkpage.setPreferredSize(drinkpage.getPreferredSize());
        
        pages.add(burgerpage);
        pages.add(drinkpage);
        menu.getViewport().add(burgerpage);
    }
    
    public void setItemMenu(JPanel parent,ArrayList list, MyImageIcon image, String name, int price, int count) {
        JPanel item = new JPanel();                                                     item.setLayout( new BorderLayout() );                           item.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel imageLabel = new JLabel(image);                                          imageLabel.setPreferredSize(new Dimension(200, 200));
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);                     nameLabel.setPreferredSize(new Dimension(300, 35));             nameLabel.setFont(font2);
        JLabel priceLabel = new JLabel(String.valueOf(price), SwingConstants.CENTER);   priceLabel.setPreferredSize(new Dimension(300, 35));            priceLabel.setFont(font2);
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (list == burgers)
                    opFrame.Process("burgers", image, name, price);
                else if (list == drinks)
                    opFrame.Process("drinks", image, name, price);
                setVisible(false);
                opFrame.setVisible(true);
                openSound.playOnce();
            }
        });
        
        item.add(priceLabel, BorderLayout.PAGE_START);
        item.add(imageLabel, BorderLayout.CENTER);
        item.add(nameLabel, BorderLayout.PAGE_END);
        item.setPreferredSize(item.getPreferredSize());
        list.add(item);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        int y = 0;
        while (count>3) {
            count -= 3;
            y += 1;
        }
        gbc.gridy = y;
        parent.add(item, gbc);
    }
}

class MyImageIcon extends ImageIcon {
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height) {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	return new MyImageIcon(newimg);
    }
};

class MySoundEffect
{
    private Clip clip;
    private boolean playing;

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            playing = false;
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()   { clip.loop(Clip.LOOP_CONTINUOUSLY); playing = true; }
    public void stop()       { clip.stop(); playing = false; }
    public boolean isPlaying()  { return playing; }
}