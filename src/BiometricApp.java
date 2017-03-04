import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces

public class BiometricApp extends Frame implements ActionListener {
    private Label lblCount;    // Declare a Label component
    private TextField tfCount; // Declare a TextField component
    private Button btnCount;   // Declare a Button component
    private int count = 0;     // Counter's value

    // Constructor to setup GUI components and event handlers
    public BiometricApp() {
        setLayout(new FlowLayout());
        // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
        // the components from left-to-right, and flow to next row from top-to-bottom.

        lblCount = new Label("Counter");  // construct the Label component
        add(lblCount);                    // "super" Frame adds Label

        tfCount = new TextField("0", 10); // construct the TextField component
        tfCount.setEditable(false);       // set to read-only
        add(tfCount);                     // "super" Frame adds TextField

        btnCount = new Button("Count");   // construct the Button component
        add(btnCount);                    // "super" Frame adds Button

        btnCount.addActionListener(this);
        // btnCount is the source object that fires ActionEvent when clicked.
        // The source add "this" instance as an ActionEvent listener, which provides
        //  an ActionEvent handler called actionPerformed().
        // Clicking btnCount invokes actionPerformed().

        setTitle("AWT Counter");  // "super" Frame sets its title
        setSize(250, 100);        // "super" Frame sets its initial window size

        // For inspecting the components/container objects
        // System.out.println(this);
        // System.out.println(lblCount);
        // System.out.println(tfCount);
        // System.out.println(btnCount);

        setVisible(true);         // "super" Frame shows

        // System.out.println(this);
        // System.out.println(lblCount);
        // System.out.println(tfCount);
        // System.out.println(btnCount);
    }


    // The entry main() method
    public static void main(String[] args) {
        // Invoke the constructor to setup the GUI, by allocating an instance
        BiometricApp app = new BiometricApp();
        // or simply "new AWTCounter();" for an anonymous instance
    }

    // ActionEvent handler - Called back upon button-click.
    @Override
    public void actionPerformed(ActionEvent evt) {
        ++count; // increase the counter value
        // Display the counter value on the TextField tfCount
        tfCount.setText(count + ""); // convert int to String
    }

}
