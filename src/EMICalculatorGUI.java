import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EMICalculatorGUI extends JFrame implements ActionListener {
    private JPanel EMICalculatorGUI;
    private JButton calculatePaymentButton;
    private JButton exitButton;
    private JTextField PrincipalField;
    private JTextField durationField;
    private JTextField rateField;
    private JTextField MonthlyPaymentField;
    private JLabel PrincipalLabel;
    private JLabel durationLabel;
    private JLabel rateLabel;
    private JLabel paymentLabel;
    private JLabel earlypayment;
    private JCheckBox checkBoxPayment;


    public EMICalculatorGUI(){
        setTitle("Loan EMI Calculator: Peter Njuku C026-01-0700/2023");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 20));


        EMICalculatorGUI = new JPanel();
        add(EMICalculatorGUI);

        // Principal input
        PrincipalLabel = new JLabel("Loan Amount:");
        setLocationRelativeTo(null);
        add(PrincipalLabel);
        PrincipalField = new JTextField();
        PrincipalField.setColumns(12);// Width = 200, Height = 30
        add(PrincipalField);

        // Interest rate input
        rateLabel = new JLabel("Interest Rate (%):");
        add(rateLabel);
        rateField = new JTextField();
        rateField.setColumns(12);
        add(rateField);

        // Duration input
        durationLabel = new JLabel("Duration (months):");
        add(durationLabel);
        durationField = new JTextField();
        durationField.setColumns(12);
        add(durationField);

        //early payment checkbox
        earlypayment = new JLabel("Early Payment");

        add(earlypayment);
        checkBoxPayment = new JCheckBox();
        add(checkBoxPayment);

        paymentLabel = new JLabel("Monthly Payment:");
        add(paymentLabel);
        MonthlyPaymentField = new JTextField();
        MonthlyPaymentField.setColumns(12);
        MonthlyPaymentField.setEditable(false);
        add(MonthlyPaymentField);

        // Calculate EMI button
        calculatePaymentButton = new JButton("Calculate");
        calculatePaymentButton.addActionListener(this);
        add(calculatePaymentButton);

        // Exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double loan = Double.parseDouble(PrincipalField.getText());
            double rate = Double.parseDouble(rateField.getText());
            int duration = Integer.parseInt(durationField.getText());

            // Validate input values
            if (loan < 0 || rate < 0 || duration < 0) {
                JOptionPane.showMessageDialog(this, "Error: Loan amount, rate, and tenure should be greater than 0",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate EMI
            double monthlyRate = (rate / 12) / 100;
            double monthlyPayment = (loan * monthlyRate * Math.pow(1 + monthlyRate, duration)) /
                    (Math.pow(1 + monthlyRate, duration) - 1);

            // Display the calculated EMI
            MonthlyPaymentField.setText(String.format("%.2f", monthlyPayment));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: All inputs should be numbers",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new EMICalculatorGUI();
    }
}
