package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.util.Set;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FirstCalculatorScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane;
	private JTextField resultField;
	private JPanel calculatorButtonsPane;
	private JTextField txtx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Look n feel para que el jPanel adopte el estilo de windows
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstCalculatorScreen frame = new FirstCalculatorScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstCalculatorScreen() {
		// Definicion de tamaño minimo para que cuando el usuario minimize la pantalla,
		// no se vea horrible
		setMinimumSize(new Dimension(400, 500));
		setTitle("Calculator");
		setForeground(new Color(243, 243, 243));
		// Metodo de toolkit para sacar la resolucion actual de la pantalla
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		myScreen.getScreenSize();
		setIconImage(myScreen.getImage(FirstCalculatorScreen.class.getResource("/img/calculator_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Obtener dimension pantalla
		Dimension screen = myScreen.getScreenSize();
		// Quiero que el tamaño de la ventana sea 5 veces menor que la resolucion del
		// monitor
		setSize(screen.width / 5, screen.height / 5);
		setLocationRelativeTo(null); // Este metodo tambien pone en el centro la pantalla, si necesidad de invocar
										// mas objetos

		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(new BorderLayout(0, 0));

		JPanel resultPane = new JPanel();
		mainContentPane.add(resultPane, BorderLayout.NORTH);

		resultField = new JTextField();
		resultField.setFocusable(false);
		resultField.setEditable(false);
		resultField.setFont(new Font("Segoe UI", Font.PLAIN, 49));
		resultField.setText("0");
		resultField.setBorder(null);
		resultField.setHorizontalAlignment(SwingConstants.TRAILING);
		resultField.setColumns(10);

		txtx = new JTextField();
		txtx.setFocusable(false);
		txtx.setBorder(null);
		txtx.setEditable(false);
		txtx.setForeground(new Color(117, 117, 117));
		txtx.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtx.setHorizontalAlignment(SwingConstants.RIGHT);
		txtx.setText("9x8=");
		txtx.setColumns(10);
		GroupLayout gl_resultPane = new GroupLayout(resultPane);
		gl_resultPane.setHorizontalGroup(gl_resultPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_resultPane.createSequentialGroup().addContainerGap(223, Short.MAX_VALUE)
						.addGroup(gl_resultPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(resultField, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_resultPane.setVerticalGroup(gl_resultPane.createParallelGroup(Alignment.LEADING).addGroup(gl_resultPane
				.createSequentialGroup().addContainerGap()
				.addComponent(txtx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(resultField, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		resultPane.setLayout(gl_resultPane);

		calculatorButtonsPane = new JPanel();
		mainContentPane.add(calculatorButtonsPane, BorderLayout.CENTER);
		// 6 filas, 4 columnas, 2px separación
		calculatorButtonsPane.setLayout(new GridLayout(6, 4, 2, 2));

		/*
		 * Array con todos los símbolos de la calculadora y bucle para crear
		 * dinámicamente los botones correspondientes, mejora mucho la escalabilidad y
		 * mantenimiento pero los botones no aparecen en el editor visual debido a que
		 * no se declaran formalmente en el codigo y se generan en tienpo de ejecucion
		 */
		String[] botonesText = { "%", "CE", "C", "DEL", "1/x", "x²", "√", "÷", "7", "8", "9", "X", "4", "5", "6", "-",
				"1", "2", "3", "+", "+/-", "0", ".", "=", };
		// Set para ajustar el color de las diferentes operaciones

		Set<String> operaciones = Set.of("%", "CE", "C", "DEL", "1/x", "x²", "√", "÷", "X", "-", "+", "=");
		// Array de la clase JButton
		JButton[] botones = new JButton[botonesText.length];

		for (int i = 0; i < botonesText.length; i++) {
			botones[i] = new JButton(botonesText[i]);
			botones[i].setBorderPainted(false);
			botones[i].setFont(new Font("Segoe UI", Font.BOLD, 15));
			botones[i].setFocusable(false);
			// Ajuste color de botones
			if (operaciones.contains(botonesText[i])) {
				botones[i].setBackground(new Color(250, 152, 11));
				botones[i].setForeground(Color.WHITE);
			} else {
				botones[i].setBackground(new Color(228, 228, 228));
			}
			calculatorButtonsPane.add(botones[i]);
		}

	}
}
