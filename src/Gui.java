import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class Gui {
    //Global attributes
    private JButton play = new JButton("Play");
    private JButton scores = new JButton("High scores");
    private JButton credits = new JButton("Credits");
    private JFrame frame = new JFrame();
    private int xBound=200;
    private JButton hitme = new JButton("HIT ME");
    private ArrayList<Card>gameDeck;
    private ArrayList<Card>playerDeck= new ArrayList<>();
    private ArrayList<Card>dealerDeck= new ArrayList<>();
    private int playerXBound=200;
    private JButton hold = new JButton("Hold");
    private int total=0;
    private JButton bet = new JButton("Bet 5");
    private JButton playAgain = new JButton("Play Again");
    private JButton addMoney = new JButton("Add credits");
    private int tokens=0;
    private int betTokens=0;
    private Font font = new Font("monospaced",Font.BOLD,30);
    private JLabel playerToken = new JLabel();
    private int accountFunds=0;
    private JButton login = new JButton("Log in");
    private String username;
    private ArrayList<Account> account= new ArrayList<>();
    private Account user;
    private JLabel bankTotal = new JLabel();
    private JLabel playerWinLabel = new JLabel("luck");
    private JButton exit = new JButton("Exit");



    //no argument constructor

    /**
     *
     * @throws IOException
     */
    public Gui() throws IOException
    {
        //loading ArrayList from file to account
        account=loadInfo();
        setFrame();
    }

    //event hander
    private class EventHandler  implements ActionListener {
        //action performed
        public void actionPerformed(ActionEvent e)
        {
            //if the play button is played
            if(e.getSource()==play){


                try {
                    frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources\\background.JPG")))));
                }//end try
                catch (IOException e1) {
                    e1.printStackTrace();
                }//end catch
                //remove buttons on the JFrame
                play.setVisible(false);
                credits.setVisible(false);
                scores.setVisible(false);


                //setting frame size
                frame.setSize(1200,700);
                //adding hit me button
                hitme.addActionListener(this);
                hitme.setBounds(300,600,100,40);
                hitme.setBackground(Color.white);
                hitme.setVisible(false);
                frame.add(hitme);
                //adding hold button
                hold.addActionListener(this);
                hold.setBackground(Color.white);
                hold.setBounds(450,600,100,40);
                hold.setVisible(false);
                frame.add(hold);
                //Adding bet button
                bet.addActionListener(this);
                bet.setBackground(Color.white);
                bet.setBounds(600,600,100,40);
                frame.add(bet);
                //Adding add money button
                addMoney.addActionListener(this);
                addMoney.setBackground(Color.white);
                addMoney.setBounds(750,600,100,40);
                addMoney.setVisible(true);
                frame.add(addMoney);
                //Adding exit button
                exit.addActionListener(this);
                exit.setBackground(Color.white);
                exit.setBounds(900,600,100,40);
                exit.setVisible(true);
                frame.add(exit);
                //creating new deck
                gameDeck=Card.getDeck();
                //repainting JFrame
                frame.repaint();

            }

            //When hit me button is clicked
            if(e.getSource()==hitme){
                //removes the bet button from screen
                bet.setVisible(false);
                //retrieving a card for the players hand
                Card playerCard = Card.hitMe(gameDeck);
                //adding the card to the players hand
                playerDeck.add(playerCard);
                //creating a label to display the players card
                JLabel playerCLabel = new JLabel();
                cardImage(playerCLabel,playerCard);

                //setting location to display the players card
                playerCLabel.setBounds(playerXBound,400,70,100);
                frame.add(playerCLabel);
                //adding the players card to the screen
                playerXBound=playerXBound+120;
                //refreshing screen to show the players card
                frame.repaint();
                //calculating player score, if the player has blackjack the hit me button dissapears
                if(calculateSum(playerDeck)==21){
                    playerWinLabel .setText("BLACKJACK");
                    playerWinLabel.setFont(font);
                    playerWinLabel.setForeground(Color.WHITE);
                    playerWinLabel.setBounds(playerXBound,400,200,40);
                    frame.add(playerWinLabel);
                    hitme.setVisible(false);
                }
                //else the player loses and the hand is bust
                else if(calculateSum(playerDeck)>21){
                    playerWinLabel.setText("BUST");
                    playerWinLabel.setBounds(playerXBound,400,100,40);
                    frame.add(playerWinLabel);

                    playerWinLabel.setFont(font);
                    playerWinLabel.setForeground(Color.WHITE);
                    hitme.setVisible(false);
                    playerWinLabel.setVisible(true);
                    //Automatically clicks the hold button once hand is bust so the dealer will play
                    hold.doClick();//Found on java JButton API
                    //repaint
                    frame.repaint();
                }
            }
            //if hold button is clicked
            if(e.getSource()==hold){
                //removing bet button
                bet.setVisible(false);
                //putting player score after the cards once hold is clicked
                JLabel playerScore = new JLabel("Score "+calculateSum(playerDeck));
                playerScore.setBounds(playerXBound,500,150,40);
                frame.add(playerScore);
                playerScore.setForeground(Color.white);
                playerScore.setFont(font);
                frame.repaint();

                //Label for the dealer Score
                JLabel totalLabel = new JLabel();
                //Loop for dealer to deal cards.. stops at 17 or greater
                while(total<17) {

                    JLabel card = new JLabel();
                    //temo card for the dealer
                    Card newCard;
                    //calling hitme method
                    newCard=Card.hitMe(gameDeck);
                    cardImage(card, newCard);
                    dealerDeck.add(newCard);

                    card.setBounds(xBound,150,70,100);
                    frame.add(card);

                    frame.repaint();

                    total=calculateSum(dealerDeck);
                    if(total!=22)
                    {

                        totalLabel.setText("Score "+total);


                    }//end of if statement
                    else
                    {
                        totalLabel.setText("BUST");

                    }//end of else
                    totalLabel.setForeground(Color.WHITE);
                    totalLabel.setFont(font);
                    frame.add(totalLabel);
                    xBound=xBound+120;

                }//end of while loop
                totalLabel.setBounds(xBound,150,150,40);
                frame.add(totalLabel);
                hitme.setVisible(false);
                hold.setVisible(false);
                playAgain.addActionListener(this);
                playAgain.setBackground(Color.white);
                playAgain.setBounds(400,600,100,40);
                playAgain.setVisible(true);
                frame.add(playAgain);

                int player,dealer;
                player=calculateSum(playerDeck);
                dealer=calculateSum(dealerDeck);
                if(player>dealer&&playerWinLabel.getText()!="BUST")
                {
                    user.addTokens(betTokens*2);
                    bankTotal.setText("Bank"+user.getTokens());
                    bankTotal.repaint();
                }//end if
                else if(dealer>21)
                {
                    user.addTokens(betTokens*2);
                    bankTotal.setText("Bank"+user.getTokens());
                    bankTotal.repaint();
                }//end else if
                else if(player==21)
                {
                    user.addTokens(betTokens*2);
                    bankTotal.setText("Bank"+user.getTokens());
                    bankTotal.repaint();
                }//end else if


                frame.repaint();
            }



            if(e.getSource()==playAgain){
                frame.getContentPane().removeAll();
                betTokens=0;
                //calls playGUI method
                playGUI();
            }

            if(e.getSource()==addMoney){
                int funds= Integer.parseInt(JOptionPane.showInputDialog("Amount of funds you would like to add to your account"));
                user.addTokens(funds);
                try {
                    saveLogin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bankTotal.repaint();
                //SAVE FUNDS TO ACCOUNT FILE
            }

            if(e.getSource()==bet){
                betTokens();
                hitme.setVisible(true);
                hold.setVisible(true);
                frame.repaint();
            }

            if(e.getSource()==login)
            {

                //////Pull username matching this from file bringing funds with it
                username=JOptionPane.showInputDialog("Enter your username");
                boolean valid=false;
                while(!valid) {
                    try {
                        tokens = Integer.parseInt(JOptionPane.showInputDialog("Enter amount of Tokens"));
                        valid=true;
                    } catch (NumberFormatException el) {
                        JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                user=new Account(username,tokens);
                account.add(user);
                try {
                    saveLogin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                frame.repaint();
            }
            if(e.getSource()==scores)
            {
                JOptionPane.showMessageDialog(null,scoreInfo(loadInfo()),"Token info",JOptionPane.INFORMATION_MESSAGE);
            }

            if(e.getSource()==exit)
            {
                try {
                    saveLogin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.getContentPane().removeAll();
                System.exit(0);

            }
            if(e.getSource()==credits)
            {
                JTextArea creditsBox = new JTextArea("Credits for project\n");
                creditsBox.append("Image credits for background photo\n" +
                        "Creator:Konstantin Suslov\n" +
                        "Copyright:x-default\n" +
                        "Information extracted from IPTC Photo Metadata");
                JOptionPane.showMessageDialog(null,creditsBox,"Credits",JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    /**
     * Method adds tokens onto the betting pot, reduces the users token count and set up playerToken and bankTotal JLabel
     */
    public void betTokens(){
        betTokens+=5;
        playerToken.setText("Bet "+betTokens);
        playerToken.setFont(font);
        playerToken.setForeground(Color.white);
        playerToken.setBounds(900,50,200,80);
        frame.add(playerToken);
        playerToken.setVisible(true);

        bankTotal.setFont(font);
        bankTotal.setForeground(Color.white);
        bankTotal.setBounds(900,120,200,80);
        frame.add(bankTotal);
        bankTotal.setVisible(true);
        user.setTokens(user.getTokens()-5);
        bankTotal.setText("Bank "+user.getTokens());



    }



    /**
     *
     * @param deck is a deck of card objects
     * @return The sum of the cards passed into the method
     */
    public int calculateSum(ArrayList<Card> deck){
        int total=0;
        //calculating total
        for(Card c : deck)
        {
            total+=c.getSum();
        }//end for loop
        //checking if there are any aces in the hand if the total is over 21
        if(total>17)
        {
            total = 0;
            //start of for loop checking for aces
            for(Card c: deck)
            {
                //if there is an ace the value is changed to one
                if(c.getValue()=="Ace")
                {
                    c.setSum(1);
                }
                //recalculating total
                total+=c.getSum();
            }//end of FOR LOOP
        }//end of if statement
        //returns total
        if (total<=21) {
            return  total;
        }
        //returns 22 as a marker that the hand is bust
        else {
            return 22;
        }//end of else statement
    }

    /**
     *
     * @throws IOException
     * Method sets up the initial GUI Frame
     */
    public void setFrame() throws IOException {
        //setting size of JFrame
        play.setBackground(Color.WHITE);
        scores.setBackground(Color.white);
        credits.setBackground(Color.white);
        login.setBackground(Color.white);

        frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources\\background.JPG")))));
        frame.setSize(600,200);
        //creating FlowLayout
        frame.setLayout(null);
        //creating event handler
        EventHandler handler = new EventHandler();
        login.addActionListener(handler);
        login.setBounds(50,50,100,50);
        login.setVisible(true);
        frame.add(login);
        play.addActionListener(handler);
        play.setBounds(50,50,100,50);
        //Adding buttons to gui
        play.setVisible(false);
        frame.add(play);
        scores.setBounds(200,50,150,50);
        scores.addActionListener(handler);
        scores.setVisible(true);
        frame.add(scores);

        credits.setBounds(400,50,100,50);
        credits.setVisible(true);
        credits.addActionListener(handler);
        frame.add(credits);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    //this method will return a file to a JLabel

    /**
     *
     * @param label is passed in and used to hole the Card image
     * @param card the path attribute is used to take the image and put it on the label to represent a card
     */
    public void cardImage(JLabel label, Card card){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(card.getPath()).getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
        //https://stackoverflow.com/questions/12020597/java-convert-image-to-icon-imageicon

    }

    /**
     * Method sets up the game GUI
     */
    public void playGUI(){
        playerDeck.removeAll(playerDeck);
        dealerDeck.removeAll(dealerDeck);
        gameDeck.removeAll(gameDeck);

        frame.setSize(1200,700);
        total=0;
        xBound=200;
        playerXBound=200;
        hitme.setBounds(300,600,100,40);
        hitme.setBackground(Color.white);
        hitme.setVisible(false);
        frame.add(hitme);
        //adding hold button

        hold.setBackground(Color.white);
        hold.setBounds(450,600,100,40);
        hold.setVisible(false);
        frame.add(hold);
        //Adding bet button

        bet.setBackground(Color.white);
        bet.setBounds(600,600,100,40);
        bet.setVisible(true);
        frame.add(bet);

        // addMoney.addActionListener(this);
        addMoney.setBackground(Color.white);
        addMoney.setBounds(750,600,100,40);
        addMoney.setVisible(true);
        frame.add(addMoney);
        frame.add(exit);

        gameDeck=Card.getDeck();
        frame.repaint();
    }

    /**
     *
     * @throws IOException
     * Method saves an arraylist of Account objects to a .dat file
     */
    public void saveLogin() throws IOException{
        File f = new File("user.dat");
        FileOutputStream fos =new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(account);
        oos.close();

        login.setVisible(false);
        play.setVisible(true);
    }

    /**
     * Method reads an Arraylist of objects from a .dat File and loads into the account ArrayList
     * @return an arraylist
     */
    public ArrayList loadInfo() {
        try {
            File f = new File("user.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            account = (ArrayList<Account>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FileNotFound: didn't work");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: didn't work");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "open didn't work");
            e.printStackTrace();
        }
        return account;
    }

    /**
     *
     * @param account is an ArrayList of Accounts
     * @return A JTextArea containing all accounts in the ArrayList
     */
    public JTextArea scoreInfo(ArrayList<Account> account){
        JTextArea box = new JTextArea("Account Info\n");
        for(Account a : account){
            box.append(a.toString());
        }
        return box;
    }
}
