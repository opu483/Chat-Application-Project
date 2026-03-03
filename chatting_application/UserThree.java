package chatting_application;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;


public class UserThree implements ActionListener, Runnable {
    
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    BufferedReader reader;
    BufferedWriter writer;
    String name = "Araf";
    
    UserThree() {
        
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
        
                


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/araf.png"));
        Image i5 = i4.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 5, 60, 60);
        p1.add(profile);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        
        ImageIcon i16 = new ImageIcon(ClassLoader.getSystemResource("icons/emoji.PNG"));
        Image i17 = i16.getImage().getScaledInstance(20, 25, Image.SCALE_DEFAULT);
        ImageIcon i18 = new ImageIcon(i17);
        JButton emojiButton = new JButton(i18);
        emojiButton.setBounds(5, 655, 40, 40);
        emojiButton.setBorderPainted(false);
        emojiButton.setContentAreaFilled(false);
        emojiButton.setFocusPainted(false);
       f.add(emojiButton);

       emojiButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
        showEmojiPanel();
       }
     });

      ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/picture.PNG"));
      Image img = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
      ImageIcon scaledImageIcon = new ImageIcon(img);

     JButton imageButton = new JButton(scaledImageIcon);
     imageButton.setBounds(320, 655, 40, 40);
     imageButton.setBorderPainted(false);
     imageButton.setContentAreaFilled(false);
     imageButton.setFocusPainted(false);
     f.add(imageButton);

    imageButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        sendImage();
    }
});
        
        
        JLabel name = new JLabel("Project Group");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        
        JLabel status = new JLabel("Araf, Nahid, Miftahul, Shanto, Mehrab, Jihad");
        status.setBounds(110, 35, 160, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);
        
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        a1.setBackground(Color.WHITE);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(10, 655, 300, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);
        
         JButton send = new JButton("Send");
        send.setBounds(365, 655, 75, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);
        
        f.setSize(450, 700);
        f.setLocation(950, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        
        f.setVisible(true);
        
        try {
            Socket socket = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
       public void showEmojiPanel() {
        JPopupMenu emojiMenu = new JPopupMenu();
        String[] emojis = {"😀", "😂", "😍", "😘", "😎", "😢", "😡", "👍", "🙏", "💖"};
        
        for (String emoji : emojis) {
            JMenuItem item = new JMenuItem(emoji);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    text.setText(text.getText() + emoji);
                }
            });
            emojiMenu.add(item);
        }

        emojiMenu.show(f, 5, 615);
    }
       
   
 public void sendImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select an Image");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showOpenDialog(f);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] imageData = fis.readAllBytes();
                fis.close();

              String encodedImage = Base64.getEncoder().encodeToString(imageData);
                writer.write("IMAGE::" + encodedImage);
                writer.write("\r\n"); 
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showImage(byte[] imageData) {
        try {
            ImageIcon icon = new ImageIcon(imageData);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            JLabel label = new JLabel(scaledIcon);
            JPanel panel = new JPanel();
            panel.add(label);

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(Color.WHITE);
            right.add(panel, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
       
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            right.setBackground(Color.WHITE);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            try {
                writer.write(out);
                writer.newLine(); 
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 public void appendMessage(String msg, boolean isSender) {
        SwingUtilities.invokeLater(() -> {
            JPanel p2 = formatLabel(msg);

            JPanel align = new JPanel(new BorderLayout());
            align.setBackground(Color.WHITE);
            align.add(p2, isSender ? BorderLayout.LINE_END : BorderLayout.LINE_START);
            
            vertical.add(align);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical);
            f.repaint();
            f.revalidate();
        });
    }
    
    
    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(0, 15, 0, 50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
 public void run() {
    try {
        String msg;
        while ((msg = reader.readLine()) != null) {
            if (msg.startsWith("IMAGE::")) {
                byte[] imageData = Base64.getDecoder().decode(msg.substring(7));
                showImage(imageData);
            } else {
                
                if (!msg.contains(name)) { 
                    appendMessage(msg, false);
                    
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public static void main(String[] args) {
        UserThree third = new UserThree();
        Thread t1 = new Thread(third);
        t1.start();
    }
}