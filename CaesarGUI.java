import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
 
/**
 * Encrypts and Decrypts text using the Caesar Cihper algorithm.
 * @author Invisible Computer, JTN
 *
 */
public class CaesarGUI extends JFrame implements ActionListener {
 
        private static final long serialVersionUID = 1L;
        private static String alphabet = "abcdefghijklmnopqrstuvwxyz ";
        private static String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static String num = "0123456789";
        private static String punc = "~`!@#$%^&*()_-+={}[]|\\:;'<>,.?/\"";
        private JTextField shiftFactor;
        private JTextArea inputTA;
        private JTextArea outputTA;
 
 
        /**
         * @param args
         */
        public static void main(String[] args) {
                new CaesarGUI().setVisible(true);
        }
       
        public void encryptText() throws InterruptedException {
                //Create a HashMap
                //A hash map takes keys and values, which are both Characters in this case.
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                //Get the text from the app and store it in a String variable.
                String textNum = this.shiftFactor.getText();
                //Check to see if a "Shift Factor" value was entered.
                //If there wasn't, set shift to zero,
                //Otherwise parse the input value to an integer so we can use it.
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                //Map every letter of the alphabet to another letter in the alphabet, shifted by x places.
                for(int i=0; i<alphabet.length()-1; i++){
                        alphaMap.put(alphabet.charAt(i), alphabet.charAt((i+shift)%26));
                        alphaMap.put(Alphabet.charAt(i), Alphabet.charAt((i+shift)%26));
                }
                for(int n = 0; n <num.length(); n++)
                {
                		alphaMap.put(num.charAt(n), num.charAt((n+shift)%10));
                }
                for(int k = 0; k <punc.length();k++)
                {
                		alphaMap.put(punc.charAt(k), punc.charAt((k+shift)%punc.length()));
                }
                	alphaMap.put(alphabet.charAt(alphabet.length()-1), ' ');
                //Get input text
                String inputText = inputTA.getText();
                String outputText = "";
                //Go to each letter and switch it with it's shifted counterpart
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                //Output the encrypted text
                outputTA.setText(outputText);
        }
       
        public void decryptText() throws InterruptedException{
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                String textNum = this.shiftFactor.getText();
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                for(int i=0; i<alphabet.length()-1; i++){
                        alphaMap.put(alphabet.charAt((i+shift)%26), alphabet.charAt(i));
                        alphaMap.put(Alphabet.charAt((i+shift)%26), Alphabet.charAt(i));
                }
                for(int n = 0; n <num.length();n++)
                {
                		alphaMap.put(num.charAt((n+shift)%10), num.charAt(n));
                }
                for(int k = 0; k <punc.length(); k++)
                {
                		alphaMap.put(punc.charAt((k+shift)%punc.length()), punc.charAt(k));
                }
                	alphaMap.put(' ', alphabet.charAt(alphabet.length()-1));
                String inputText = inputTA.getText();
                String outputText = "";
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                outputTA.setText(outputText);
        }
       
        public void switchText() throws InterruptedException{
        	String inputText = outputTA.getText();
        	String outputText = inputTA.getText();
        	
        	inputTA.setText(inputText);
        	outputTA.setText(outputText);
        }
        
        public CaesarGUI(){
            setTitle("Caesar Cipher");
            setVisible(true);
            setDefaultCloseOperation(3);
 
            Container content = getContentPane();
            GridLayout layout = new GridLayout(3, 0, 0, 10);
            content.setLayout(layout);
 
            inputTA = new JTextArea("Insert the text to be encrypted/decrypted here, then press the appropriate button.", 12, 40);
            inputTA.setLineWrap(true);
            inputTA.setWrapStyleWord(true);
            inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller = new JScrollPane(inputTA);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller);
           
            outputTA = new JTextArea("Output text.",12, 40);
            outputTA.setLineWrap(true);
            outputTA.setWrapStyleWord(true);
            outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller2 = new JScrollPane(outputTA);
            scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller2);
           
            JPanel box1 = new JPanel();
            box1.setLayout(new FlowLayout());
            JButton decryptButton = new JButton("Decrypt");
            JButton encryptButton = new JButton("Encrypt");
            JButton switchButton = new JButton("Switch");
            decryptButton.addActionListener(this);
            encryptButton.addActionListener(this);
            switchButton.addActionListener(this);
            box1.add(decryptButton);
            box1.add(encryptButton);
            box1.add(switchButton);
            box1.add(new JLabel("Shift Factor"));
            box1.add(this.shiftFactor = new JTextField(20));
            content.add(box1);
           
            pack();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Encrypt")){
                        try{
                                encryptText();
                        }
                        catch(InterruptedException e1){
                                e1.printStackTrace();
                        }
                }
                if (e.getActionCommand().equals("Decrypt"))
                      try {
                        decryptText();
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                if(e.getActionCommand().equals("Switch")){
                	try{
                		switchText();
                	}
                	catch(InterruptedException e1)
                	{
                		e1.printStackTrace();
                	}
                }
        }
}