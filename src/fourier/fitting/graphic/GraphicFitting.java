package fourier.fitting.graphic;

import fourier.fitting.FourierTransform;
import fourier.fitting.Point;

import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicFitting
{
	// important components :
	private static Dimension screen; // screen resolution
	private JFrame root_frame; // base frame
	private JPanel control_panel; // control panel in the bottom
	private JPanel set_panel; // setting panel in the top to set [up_limit]
	private JPanel root_panel; // base panel that contains [canvas_layer] and [series]
	private CanvasPanel canvas; // layered canvas: graph, coordinate, points
	
	// internal constants and variables :
	private static int max_n = 11; // must equals to FourierTransform.MAX
	private int up_limit;
	private JComboBox<Integer> n_comboBox; // get [up_limit] from users
	private double[] coefficients = new double[max_n]; // result of fitting
	private JTextField[] field_n = new JTextField[max_n]; // show result doubles
	private ArrayList<Point> sample_points = new ArrayList<Point>(); // points to get fitting
	private FourierTransform tranformation = new FourierTransform();
	private boolean if_show_coordinate_option = false;
	private boolean if_show_graph_option = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run()
			{
				try
				{
					screen = Toolkit.getDefaultToolkit().getScreenSize();
					GraphicFitting window = new GraphicFitting();
					window.root_frame.pack();
					window.root_frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicFitting()
	{
		try
		{
			initialize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception
	{
		up_limit = -1;
		for(double coeff : coefficients)
			coeff = 0.0;
		root_frame = new JFrame();
		root_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GraphicFitting.class.getResource("/fourier/fitting/graphic/icon.jpg")));
		root_frame.getContentPane().setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.updateComponentTreeUI(root_frame);
		
		control_panel = new JPanel();
		root_frame.getContentPane().add(control_panel, BorderLayout.SOUTH);
		control_panel.setLayout(new GridBagLayout());
		set_panel = new JPanel();
		root_frame.getContentPane().add(set_panel, BorderLayout.NORTH);
		set_panel.setLayout(new GridBagLayout());
		set_panel.add(new JLabel(""));
		
		JLabel fit_label = new JLabel("fitting for n  \u2266 ");
		fit_label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		fit_label.setHorizontalAlignment(SwingConstants.RIGHT);
		set_panel.add(fit_label);
		
		n_comboBox = new JComboBox<Integer>();
		for(int i = 0; i < max_n; i ++)
			n_comboBox.addItem(i);
		set_panel.add(n_comboBox);
		JCheckBox if_show_coordinate = new JCheckBox("show coordinate");
		if_show_coordinate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				if_show_coordinate_option = if_show_coordinate.isSelected();
				canvas.repaint();
				canvas.setPreferredSize(canvas.size);
			}
		});
		if_show_coordinate.setSelected(this.if_show_coordinate_option);
		if_show_coordinate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(if_show_coordinate);
		
		JCheckBox if_show_graph = new JCheckBox("show graph");
		if_show_graph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				if_show_graph_option = if_show_graph.isSelected();
				canvas.repaint();
				canvas.setPreferredSize(canvas.size);
			}
		});
		if_show_graph.setSelected(this.if_show_graph_option);
		if_show_graph.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(if_show_graph);
		JButton load_button = new JButton("load graph...");
		load_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				canvas.loadGraph(); // load graph from image
				canvas.repaint(); // rebuilt the canvas panel
				canvas.setPreferredSize(canvas.size); // resize the canvas
				root_frame.pack(); // update the whole frame
			}
		});
		load_button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(load_button);
		
		JButton clear_button = new JButton("clear points");
		clear_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				;
			}
		});
		clear_button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(clear_button);
		
		JButton fit_button = new JButton("start fitting");
		fit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				startFitting();
			}
		});
		fit_button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(fit_button);
		
		JButton draw_button = new JButton("start carving");
		draw_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				JDialog jd = new JDialog(root_frame, "", true);
				jd.setSize(200, 100);
				jd.add(new JLabel("È»¶øÕâ¸ö°´¼üÏÖÔÚ²¢Ã»Ê²Ã´ÂÑÓÃ"));
				jd.setVisible(true);
			}
		});
		draw_button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		control_panel.add(draw_button);
		
		root_panel = new JPanel();
		root_frame.getContentPane().add(root_panel, BorderLayout.CENTER);
		root_panel.setLayout(new GridBagLayout());
		
		canvas = new CanvasPanel();
		root_panel.add(canvas);
		
		JPanel series = new JPanel();
		root_panel.add(series);
		series.setLayout(new GridLayout(max_n + 2, 2));
		
		JLabel label_1_1 = new JLabel("transform:");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		series.add(label_1_1);
		JLabel label_1_2 = new JLabel("\u03A3 zn*e^in\u03C9t");
		label_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD | Font.ITALIC, 12));
		series.add(label_1_2);
		JLabel label_2_1 = new JLabel("n");
		label_2_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		series.add(label_2_1);
		JLabel label_2_2 = new JLabel("zn");
		label_2_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		label_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		series.add(label_2_2);
		JLabel label_n;
		for(int i = 0; i < max_n; i ++)
		{
			label_n = new JLabel(i + "");
			label_n.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			label_n.setHorizontalAlignment(SwingConstants.CENTER);
			series.add(label_n);
			field_n[i] = new JTextField(coefficients[i] + "");
			field_n[i].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			field_n[i].setHorizontalAlignment(SwingConstants.LEFT);
			series.add(field_n[i]);
		}

		root_frame.setTitle("Fourier Transformation Fitting");
		root_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void startFitting()
	{
		up_limit = ((Integer)(n_comboBox.getSelectedItem())).intValue();
		if(tranformation.setOrder(up_limit) && tranformation.setPoints(sample_points))
		{
			coefficients = tranformation.getFitting();
			for(int i = 0; i < max_n; i ++)
				field_n[i].setText(coefficients[i] + "");
		}
		else
			System.out.println("Error parameters for fitting.");
	}
	
	private class CanvasPanel extends JPanel
	{
		public int width = screen.width / 5;
		public BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
		public Dimension size = new Dimension(width, width); // CanvasPanel should be width * width in default
		
		public CanvasPanel()
		{
			setPreferredSize(size);
			this.addMouseListener(new MouseClickHandler());
			this.addMouseMotionListener(new MouseCursorHandler());
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB), 0, 0, null);
			if(if_show_graph_option)
			{
				int x_shift = 0, y_shift = 0;
				if(width > image.getWidth())
					x_shift = (width - image.getWidth()) / 2;
				else
					y_shift = (width - image.getHeight()) / 2;
				g.drawImage(image, x_shift, y_shift, null);
			} // draw the graph from image
			
			Graphics2D g2 = (Graphics2D) g;
			if(if_show_coordinate_option)
			{
				g2.setColor(Color.yellow);
				for(int i = 0, n = 20; i < n; i ++)
				{
					g2.draw(new Line2D.Double(0, width * i / n, width, width * i / n));
					g2.draw(new Line2D.Double(width * i / n, 0, width * i / n, width));
				}
				g2.draw(new Line2D.Double(0, width - 1, width, width - 1));
				g2.draw(new Line2D.Double(width - 1, 0, width - 1, width));
				
				g2.draw(new Line2D.Double(0, 1 + width / 2, width, 1 + width / 2));
				g2.draw(new Line2D.Double(1 + width / 2, 0, 1 + width / 2, width));
				g2.draw(new Line2D.Double(0, width / 2 - 1, width, width / 2 - 1));
				g2.draw(new Line2D.Double(width / 2 - 1, 0, width / 2 - 1, width)); // bold the x & y axis
			} // draw coordinate system and grid
			
			g2.setColor(Color.red);
			for(fourier.fitting.Point p : sample_points)
			{
				g2.draw(new Rectangle2D.Double(
						(p.getX() + 1) * width / 2 - 2,
						(p.getY() + 1) * width / 2 - 2,
						4, 4)
				);
			}
		}
		
		public void loadGraph()
		{
			JFileChooser file_chooser = new JFileChooser();
			file_chooser.setCurrentDirectory(new File("."));
			file_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int result = file_chooser.showOpenDialog(root_panel);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				ImageIcon icon = new ImageIcon(file_chooser.getSelectedFile().getPath());
				int width_image = icon.getIconWidth();
				int height_image = icon.getIconHeight();
				image = new BufferedImage(width_image, height_image, BufferedImage.TYPE_INT_RGB);
				try
				{
					File image_file = new File(file_chooser.getSelectedFile().getPath());
					image = ImageIO.read(image_file);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				width = width_image > height_image ? width_image : height_image; // resize to image size
				size = new Dimension(width, width);
			}
		}
	}
	
	private class MouseClickHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent ev)
	      {
	         System.out.println("clicked at " + ev.getPoint());
	         sample_points.add(new fourier.fitting.Point(
	        		 ev.getPoint().getX() * 2 / canvas.width - 1,
	        		 ev.getPoint().getY() * 2 / canvas.width - 1)
	         );
	         canvas.repaint();
	      }
	}
	
	private class MouseCursorHandler extends MouseMotionAdapter
	{
		public void mouseMoved(MouseEvent ev)
		{
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}
}
