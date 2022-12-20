//Pattrayus Chokbunlue 6313179
//Thanatorn Ruangrote 6313129
//Thunyavut Nabhaboriraks 6313130

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;

class purchaseFrame extends JFrame {
    private JPanel          contentpane, panel1, panel2, panel3, panel4, panel5, panel6, header;
    private JLabel          drawpane, titleLabel, orderLabel, couponLabel, currentcode, totalprice;
    private JScrollPane     orderscrollpane;
    private JTextField      text;
    private JButton         confirm, back, redeem;
    private MyImageIcon     confirmImg,cancelImg;
    private String          coupon;
    private int             prices;
    private MySoundEffect   hitSound, closeSound;
    
    private int frameWidth = 900, frameHeight = 600;
    
    private Font font1 = new Font("TimesRoman", Font.BOLD, 15);
    private Font font2 = new Font("TimesRoman", Font.BOLD, 20);
    
    public purchaseFrame() {
        setTitle("Check out");
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
        confirmImg = new MyImageIcon("resources/confirm.png").resize(120, 120); 
        cancelImg = new MyImageIcon("resources/cancel.png").resize(120, 120);
        hitSound   = new MySoundEffect("resources/minecraft_click.wav");
        closeSound   = new MySoundEffect("resources/close.wav");
        
        drawpane = new JLabel();
        drawpane.setLayout( new BoxLayout(drawpane, BoxLayout.Y_AXIS) );
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel1 = new JPanel( new BorderLayout() );                          panel1.setPreferredSize(new Dimension(600, 70));
        titleLabel = new JLabel("Purchase", SwingConstants.CENTER);         titleLabel.setPreferredSize(new Dimension(200, 50));                    titleLabel.setFont(font2);
        panel1.add(titleLabel, BorderLayout.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel2 = new JPanel( new BorderLayout() );                          panel2.setPreferredSize(new Dimension(600, 70));
        orderLabel = new JLabel("Order", SwingConstants.CENTER);            orderLabel.setPreferredSize(new Dimension(150, 50));                    orderLabel.setFont(font1);
        header = new JPanel();                                              header.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel hname = new JLabel("Product name");
        JLabel hsize = new JLabel("Size(if have)");
        JLabel hprice = new JLabel("Price");
        JLabel hquantity = new JLabel("Quantity");
        JLabel hdelete = new JLabel();
        gbc.weightx = 0.19;
        gbc.gridx = 0;  
        gbc.gridy = 0;
        header.add(hname, gbc);
        gbc.weightx = 0.065;
        gbc.gridx = 1;  
        gbc.gridy = 0;
        header.add(hsize, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 2;  
        gbc.gridy = 0;
        header.add(hprice, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 3;  
        gbc.gridy = 0;
        header.add(hquantity, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 4;  
        gbc.gridy = 0;
        header.add(hdelete, gbc);
        panel2.add(orderLabel, BorderLayout.LINE_START);
        panel2.add(header, BorderLayout.PAGE_END);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel3 = new JPanel( new BorderLayout() );                          panel3.setPreferredSize(new Dimension(600, 210));
        orderscrollpane = new JScrollPane();                                orderscrollpane.setPreferredSize(new Dimension(600,200));               orderscrollpane.setBorder(BorderFactory.createLineBorder(Color.black));
        orderscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel3.add(orderscrollpane, BorderLayout.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel4 = new JPanel( new FlowLayout(FlowLayout.LEFT) );             panel4.setPreferredSize(new Dimension(600, 80));
        couponLabel = new JLabel("Coupon", SwingConstants.CENTER);          couponLabel.setPreferredSize(new Dimension(100, 50));
        text = new JTextField("XXX_XXX", 10);                               text.setPreferredSize(new Dimension(200, 50));
        //*//
        text.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                hitSound.playOnce();
            }
            public void keyReleased(KeyEvent e) {
                text.setText(text.getText().toUpperCase());
            }
        });
        redeem = new JButton("Redeem");                                     redeem.setPreferredSize(new Dimension(100, 30));                        redeem.setBackground(Color.green);
        currentcode = new JLabel();                                         currentcode.setVisible(false);
        redeem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hitSound.playOnce();
                if(text.getText().equals("HAP_PPY")&&!(text.getText().equals(coupon))) {
                    prices -= (prices/100)*15;
                    totalprice.setText("Total price : " + String.valueOf(prices));
                    JOptionPane.showMessageDialog(null, "Promo code successly redeemed(15% off)");
                    coupon = text.getText();
                    currentcode.setText("Current Code : " + coupon);
                    currentcode.setVisible(true);
                }
                else if (text.getText().equals(coupon))
                    JOptionPane.showMessageDialog(null, "Code had been redeemed");
                else
                    JOptionPane.showMessageDialog(null, "Code expired or invalid");
            }
        });
        panel4.add(couponLabel);
        panel4.add(text);
        panel4.add(redeem);
        panel4.add(currentcode);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel5 = new JPanel( new FlowLayout(FlowLayout.LEFT, 100, 0) );     panel5.setPreferredSize(new Dimension(600, 70));
        totalprice = new JLabel();                                          text.setPreferredSize(new Dimension(600, 50));
        panel5.add(totalprice);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        panel6 = new JPanel( new FlowLayout(FlowLayout.CENTER, 180, 0) );   panel6.setPreferredSize(new Dimension(600, 100));
        confirm = new JButton(confirmImg);                                  confirm.setPreferredSize(new Dimension(80, 80));
        back = new JButton(cancelImg);                                      back.setPreferredSize(new Dimension(80, 80));
        panel6.add(back);
        panel6.add(confirm);
        ////////////////////////////////////////////////////////////////////////////////////////////////

        drawpane.add(panel1);
        drawpane.add(panel2);
        drawpane.add(panel3);
        drawpane.add(panel4);
        drawpane.add(panel5);
        drawpane.add(panel6);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
    
    public void setItemOrder(ArrayList<products> list) {
        int i = 0;
        prices = 0;
        JPanel orderpanel = new JPanel();                           orderpanel.setLayout( new BoxLayout(orderpanel, BoxLayout.Y_AXIS) );
        for (products obj : list) {
            JPanel order = new JPanel();                            order.setLayout( new BoxLayout(order, BoxLayout.Y_AXIS) );
            order.setBorder(BorderFactory.createLineBorder(Color.black));
            //////////////////////////////////////////////////////////////////////////////////////
            JPanel maininfo = new JPanel();                         maininfo.setLayout( new GridBagLayout() );
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JPanel subname = new JPanel();
            subname.setPreferredSize(new Dimension(300,40));        subname.setLayout( new FlowLayout(FlowLayout.LEFT) );
            JPanel subsize = new JPanel();
            subsize.setPreferredSize(new Dimension(150,40));        subsize.setLayout( new FlowLayout(FlowLayout.LEFT) );
            JPanel subprice = new JPanel();
            subprice.setPreferredSize(new Dimension(150,40));       subprice.setLayout( new FlowLayout(FlowLayout.LEFT) );
            JPanel subquantity = new JPanel();
            subquantity.setPreferredSize(new Dimension(150,40));    subquantity.setLayout( new FlowLayout(FlowLayout.LEFT) );
            JPanel subdelete = new JPanel();
            subdelete.setPreferredSize(new Dimension(150,40));      subdelete.setLayout( new FlowLayout(FlowLayout.LEFT) );
            
            if (obj.gettype().equals("burgers")) {
                JLabel name = new JLabel(String.valueOf(i+1) + ".       " + obj.getname() + "(" + obj.getsauce() + ")");
                name.setFont(font2);
                subname.add(name);
            }
            if (obj.gettype().equals("drinks")) {
                JLabel name = new JLabel(String.valueOf(i+1) + ".       " + obj.getname());
                name.setFont(font2);
                subname.add(name);
                JLabel size = new JLabel(String.valueOf(obj.getsize()));
                size.setFont(font2);
                subsize.add(size);
            }
            JLabel price = new JLabel(String.valueOf(obj.getprice()+obj.getTip()) + " Bath");
            price.setFont(font2);
            JLabel quantity = new JLabel(String.valueOf(obj.getquantity()));
            quantity.setFont(font2);
            JButton delete = new JButton();                           delete.setBackground(Color.red);
            //*//
            delete.addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent e ) {
                    hitSound.playOnce();
                    list.remove(obj);
                    setItemOrder(list);
                }
            });
            subprice.add(price);
            subquantity.add(quantity);
            subdelete.add(delete);
            gbc.weightx = 0.15;
            gbc.gridx = 0;  
            gbc.gridy = 0;
            maininfo.add(subname, gbc);
            gbc.weightx = 0.1;
            gbc.gridx = 1;  
            gbc.gridy = 0;
            maininfo.add(subsize, gbc);
            gbc.weightx = 0.1;
            gbc.gridx = 2;  
            gbc.gridy = 0;
            maininfo.add(subprice, gbc);
            gbc.weightx = 0.1;
            gbc.gridx = 3;  
            gbc.gridy = 0;
            maininfo.add(subquantity, gbc);
            gbc.weightx = 0.1;
            gbc.gridx = 4;  
            gbc.gridy = 0;
            maininfo.add(subdelete, gbc);
            order.add(maininfo);
            
            //////////////////////////////////////////////////////////////////////////////////////
            JPanel addsinfo = new JPanel();
            boolean exist = false;
            if (obj.gettype().equals("burgers")) {
                addsinfo.setLayout( new BoxLayout(addsinfo, BoxLayout.Y_AXIS) );
                if (obj.getexcheese()) {
                    addsinfo.add(addsSetup("Cheese", obj));
                    exist = true;
                }
                if (obj.getfrenchfries()) {
                    addsinfo.add(addsSetup("French fries", obj));
                    exist = true;
                }
                if (obj.getexsauce()) {
                    addsinfo.add(addsSetup("Sauce", obj));
                    exist = true;
                }
            }
            if (obj.getTip() != 0) {
                addsinfo.add(addsSetup("Tip", obj));
                exist = true;
            }
            if (exist == false)
                    addsinfo = null;
                else {
                    order.add(addsinfo);
                }
            prices += obj.getprice()+obj.getTip();
            
            order.setMaximumSize(new Dimension(Integer.MAX_VALUE, order.getPreferredSize().height));
            orderpanel.add(order);
            i++;
        }
        if (coupon != null) {
            if (coupon.equals("HAP_PPY"))
                prices -= (prices/100)*15;
            }
        totalprice.setText("Total price : " + String.valueOf(prices));
        orderscrollpane.getViewport().add(orderpanel);
        
    }
    
    public JPanel addsSetup(String type, products obj) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel line = new JPanel( new GridBagLayout() );
        JPanel addname = new JPanel();
        addname.setPreferredSize(new Dimension(300,30));        addname.setLayout( new FlowLayout(FlowLayout.LEFT) );
        JPanel addsize = new JPanel();
        addsize.setPreferredSize(new Dimension(150,30));        addsize.setLayout( new FlowLayout(FlowLayout.LEFT) );
        JPanel addprice = new JPanel();
        addprice.setPreferredSize(new Dimension(150,30));       addprice.setLayout( new FlowLayout(FlowLayout.LEFT) );
        JPanel addquantity = new JPanel();
        addquantity.setPreferredSize(new Dimension(150,30));    addquantity.setLayout( new FlowLayout(FlowLayout.LEFT) );
        JPanel adddelete = new JPanel();
        adddelete.setPreferredSize(new Dimension(150,30));      adddelete.setLayout( new FlowLayout(FlowLayout.LEFT) );
        JLabel addsLabel = new JLabel();                        addsLabel.setPreferredSize(new Dimension(300, 30));
        JLabel addsPrice = new JLabel();                        addsPrice.setPreferredSize(new Dimension(150, 30));
        if (type.equals("Cheese")) {
            addsLabel.setText("+Extra cheese");
            addsPrice.setText(" 30*" + String.valueOf(obj.getquantity()) + " = " + String.valueOf(30*obj.getquantity()) + " Bath");
        }
        else if (type.equals("French fries")) {
            addsLabel.setText("+French fries(" + obj.getfavor() + ")");
            addsPrice.setText(" 70*" + String.valueOf(obj.getquantity()) + " = " + String.valueOf(70*obj.getquantity()) + " Bath");
        }
        else if (type.equals("Sauce")) {
            addsLabel.setText("+Extra sauce");
            addsPrice.setText(" 10*" + String.valueOf(obj.getquantity()) + " = " + String.valueOf(10*obj.getquantity()) + " Bath");
        }
        else if (type.equals("Tip")) {
            addsLabel.setText("+Tip(" + obj.getemployeename() + ")");
            addsPrice.setText(String.valueOf(obj.getTip()) + " Bath");
        }
        addsLabel.setFont(font1);
        addsPrice.setFont(font1);
        addname.add(addsLabel);
        addprice.add(addsPrice);
        gbc.weightx = 0.15;
        gbc.gridx = 0;  
        gbc.gridy = 0;
        line.add(addname, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 1;  
        gbc.gridy = 0;
        line.add(addsize, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 2;  
        gbc.gridy = 0;
        line.add(addprice, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 3;  
        gbc.gridy = 0;
        line.add(addquantity, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 4;  
        gbc.gridy = 0;
        line.add(adddelete, gbc);
        return line;
    }
    
    public void darkthemepurchase(){
        panel1.setBackground(Color.DARK_GRAY);
        panel2.setBackground(Color.DARK_GRAY);
        panel3.setBackground(Color.DARK_GRAY);
        panel4.setBackground(Color.GRAY);
        panel5.setBackground(Color.DARK_GRAY);
        panel6.setBackground(Color.DARK_GRAY);
        orderLabel.setForeground(Color.WHITE);
        titleLabel.setForeground(Color.WHITE);
        totalprice.setForeground(Color.WHITE);
    }
    public void lightthemepurchase(){
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);
        panel4.setBackground(Color.WHITE);
        panel5.setBackground(Color.WHITE);
        panel6.setBackground(Color.WHITE);
        orderLabel.setForeground(Color.BLACK);
        titleLabel.setForeground(Color.BLACK);
        totalprice.setForeground(Color.BLACK);
    }
    
    public JButton getBackButton()  { return back; }
    public JButton getConfirmButton() { return confirm; }
}