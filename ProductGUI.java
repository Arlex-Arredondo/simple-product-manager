import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ProductGUI extends JFrame implements ActionListener
{
	//array for several products
	private ArrayList<Product> productList = new ArrayList<Product>();
	//variable to store information of a product to display
	String infoProduct;
	
	//panels
	private JPanel generalContainer = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel centrePanel = new JPanel();
	private JPanel centreWestPanel = new JPanel();
	private JPanel centreEastPanel = new JPanel();
	
	//top panel elements
	private JTextField newProductName = new JTextField(15);
	private JTextField newProductStock = new JTextField(15);
	private JTextField newProductPrice = new JTextField(15);
	private JButton addProductButton = new JButton("Add Product");
	
	//west panel elements
	private JTextField restockProductName = new JTextField(15);
	private JTextField restockQuantity = new JTextField(15);
	private JButton restockProductButton = new JButton("Re-stock");
	 
	//centre panel elements
	private JTextField setNewProdPriceName = new JTextField(15);
	private JTextField setNewProdPrice = new JTextField(15);
	private JButton setPriceButton = new JButton("Set new price");
	
	//east panel elements
	private JTextField sellProductName = new JTextField(15);
	private JTextField sellProductQuantity = new JTextField(15);
	private JButton sellProductButton = new JButton("Sell product");
	
	//bottom panel elements
	private JTextArea productInfo = new JTextArea(4,20);
	
	//menu bar
	private JMenuBar bar = new JMenuBar();
	private JMenu topStripeMenu = new JMenu("Get product info");
	private JMenuItem getInfo = new JMenuItem("Choose product");
	
	
	//constructor
	public ProductGUI()
	{
		//adding a title for textFields
		newProductName.setBorder(new TitledBorder("Name"));
		newProductStock.setBorder(new TitledBorder("Stock"));
		newProductPrice.setBorder(new TitledBorder("Price"));
		
		restockProductName.setBorder(new TitledBorder("Name"));
		restockQuantity.setBorder(new TitledBorder("Quantity"));
		
		setNewProdPriceName.setBorder(new TitledBorder("Name"));
		setNewProdPrice.setBorder(new TitledBorder("Price"));
		
		sellProductName.setBorder(new TitledBorder("Name"));
		sellProductQuantity.setBorder(new TitledBorder("Quantity"));
		
		//adding panels and grids
		generalContainer.setLayout(new GridLayout(3,1));
		generalContainer.add(topPanel);
		generalContainer.add(middlePanel);
		generalContainer.add(bottomPanel);
		
		middlePanel.setLayout(new GridLayout(1,3));
		middlePanel.add(centreWestPanel);
		middlePanel.add(centrePanel);
		middlePanel.add(centreEastPanel);
		
		add(generalContainer);
		generalContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		//adding things to panels
		topPanel.add(newProductName);
		topPanel.add(newProductStock);
		topPanel.add(newProductPrice);
		topPanel.add(addProductButton);
		topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "New Product",TitledBorder.CENTER, TitledBorder.TOP));
		
		centreWestPanel.add(restockProductName);
		centreWestPanel.add(restockQuantity);
		centreWestPanel.add(restockProductButton);
		centreWestPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Re-stock Product",TitledBorder.CENTER, TitledBorder.TOP));
		
		centrePanel.add(setNewProdPriceName);
		centrePanel.add(setNewProdPrice);
		centrePanel.add(setPriceButton);
		centrePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Re-price Product",TitledBorder.CENTER, TitledBorder.TOP));
		
		centreEastPanel.add(sellProductName);
		centreEastPanel.add(sellProductQuantity);
		centreEastPanel.add(sellProductButton);
		centreEastPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sell Product",TitledBorder.CENTER, TitledBorder.TOP));
		
		bottomPanel.add(productInfo);
		bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Information",TitledBorder.CENTER, TitledBorder.TOP));
	
		//adding tool bar
		topStripeMenu.add(getInfo);
		bar.add(topStripeMenu);
		setJMenuBar(bar);
		topStripeMenu.setBorder(new BevelBorder(BevelBorder.RAISED));
		getInfo.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//action listeners
		addProductButton.addActionListener(this);
		restockProductButton.addActionListener(this);
		setPriceButton.addActionListener(this);
		sellProductButton.addActionListener(this);
		getInfo.addActionListener(this);
		
		productInfo.setEditable(false);
		
		setTitle("u1521653");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(300,300);	
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== getInfo)
		{
			getInfoClick();
		}
		
		if(e.getSource()== addProductButton)
		{
			addProductButton();
		}
		
		if(e.getSource()== restockProductButton)
		{
			restockProductButton();
		}
		
		if(e.getSource()== setPriceButton)
		{
			setPriceButton();
		}
		
		if(e.getSource()== sellProductButton)
		{
			sellProductButton();
		}
	}
	
	//method to add products to the array
	public void addMoreProducts (Product productIn)
	{
		productList.add(productIn);
	}
	
	//check if a new product is already in the list, the names were saved in lowercase
	public boolean productExist(String productNameIn)
	{
		for(Product currentProduct: productList)
		{
			if(currentProduct.getName().equals(productNameIn.toLowerCase()))
			{
				infoProduct = "Product: " + currentProduct.getName().toLowerCase() + "\nStock level: " + currentProduct.getStockLevel() + "\nPrice: £" + currentProduct.getPrice();
				return true;
			}
		}
		return false;
	}
	
	//check negative values in inputs 
	public boolean negativeValues (double doubleIn)
	{
		if(doubleIn<0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
							//  	ACTION EVENT METHODS
	
	public void getInfoClick()
	{
		//user input the product to display its info 
		String whichProduct = JOptionPane.showInputDialog(null,"Enter product name for more information?","",JOptionPane.QUESTION_MESSAGE);
		
		//if the JoptionPane cancel button is not pressed
		if (whichProduct != null)
		{
			if(!productExist(whichProduct))
			{
				JOptionPane.showMessageDialog(null,"This product is not in the list, please choose another name","Invalid data",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//JtextArea to display info of a product
				productInfo.setText(infoProduct);
			}
		}
	}
	
	public void addProductButton()
	{
		if(newProductName.getText().length()== 0 || newProductStock.getText().length() == 0 || newProductPrice.getText().length() == 0 )
		{
			JOptionPane.showMessageDialog(null,"Fill all the fields","Invalid ",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			try
			{
				//checking for negative data entered
				if(negativeValues(Double.parseDouble(newProductStock.getText())) || negativeValues(Double.parseDouble(newProductPrice.getText())))
				{
					JOptionPane.showMessageDialog(null,"negative values entered","Invalid data",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//check if the new product is already in the list
					if(!productExist(newProductName.getText()))
					{		
						//creates the new product. lowercase the name to always store the name in lowercase format
						Product newProduct = new Product(newProductName.getText().toLowerCase(),Integer.parseInt(newProductStock.getText()),Double.parseDouble(newProductPrice.getText()));
						//call method to add the new product to the array
						addMoreProducts(newProduct);
						JOptionPane.showMessageDialog(null,"Product succesfully added","System information ",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"This product is already in the list, please choose another name","Invalid data",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch (NumberFormatException x)
			{
				JOptionPane.showMessageDialog(null,"Wrong number entered","Invalid data",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void restockProductButton()
	{
		//check for empty fields
		if(restockProductName.getText().length()== 0 || restockQuantity.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null,"Fill all the fields","Invalid ",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{	
		//check if product to be re-stocked exists
			if(!productExist(restockProductName.getText()))
			{
				JOptionPane.showMessageDialog(null,"This product is not in the list, please choose another name","Invalid data",JOptionPane.ERROR_MESSAGE);
			}
			else
			{	
				//look for the product inputted inside of the arrayList and then change its stock by accesing the reStock method in Product class
				for(Product currentProduct: productList)
				{
					if(currentProduct.getName().equals(restockProductName.getText().toLowerCase()))
					{
						try
						{
							if(currentProduct.getStockLevel() + Integer.parseInt(restockQuantity.getText()) < 0)
							{
								JOptionPane.showMessageDialog(null,"Stock going to negative","Invalid data",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								currentProduct.reStock(Integer.parseInt(restockQuantity.getText()));
								JOptionPane.showMessageDialog(null,"Product succesfully re-stocked","System information ",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						catch(NumberFormatException x)
						{
							JOptionPane.showMessageDialog(null,"Wrong number entered. Whole numbers only","Invalid data",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}
	
	public void setPriceButton()
	{ 
		//check for empty fields
		if(setNewProdPriceName.getText().length()== 0 || setNewProdPrice.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null,"Fill all the fields","Invalid ",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{	
			try
			{
				//checking for negative data entered
				if(negativeValues(Double.parseDouble(setNewProdPrice.getText())))
				{
					JOptionPane.showMessageDialog(null,"negative value entered","Invalid data",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//check if product to be re-priced exists
					if(!productExist(setNewProdPriceName.getText()))
					{
						JOptionPane.showMessageDialog(null,"This product is not in the list, please choose another name","Invalid data",JOptionPane.ERROR_MESSAGE);
					}
					else
					{	
						//look for the product inputed inside of the arrayList and then change its stock by accessing the reStock method in Product class
						for(Product currentProduct: productList)
						{
							if(currentProduct.getName().equals(setNewProdPriceName.getText().toLowerCase()))
							{ 
								currentProduct.setPrice(Double.parseDouble(setNewProdPrice.getText()));
								JOptionPane.showMessageDialog(null,"Price succesfully changed","System information ",JOptionPane.INFORMATION_MESSAGE);								
							}
						}
					}
				}
			}
			catch (NumberFormatException x)
			{
				JOptionPane.showMessageDialog(null,"Wrong number entered","Invalid data",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void sellProductButton()
	{
		//check for empty fields
		if(sellProductName.getText().length()== 0 || sellProductQuantity.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null,"Fill all the fields","Invalid ",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{	
			try
			{
				if(negativeValues(Double.parseDouble(sellProductQuantity.getText())))
				{
					JOptionPane.showMessageDialog(null,"negative value entered","Invalid data",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//check if product to be sold exists
					if(!productExist(sellProductName.getText()))
					{
						JOptionPane.showMessageDialog(null,"This product is not in the list, please choose another name","Invalid data",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						//look for the product inputted inside of the arrayList and then change its stock by accesing the sell method in Product class
						for(Product currentProduct: productList)
						{
							if(currentProduct.getName().equals(sellProductName.getText().toLowerCase()))
							{
								try
								{
									//check if there is enogh stock
									if(currentProduct.getStockLevel() - Integer.parseInt(sellProductQuantity.getText()) >= 0)
									{
										currentProduct.sell(Integer.parseInt(sellProductQuantity.getText()));
										JOptionPane.showMessageDialog(null,"Product succesfully sold","System information ",JOptionPane.INFORMATION_MESSAGE);
									}
									else
									{
										JOptionPane.showMessageDialog(null,"There is not enough stock, current amount of " + currentProduct.getName() + " is " + currentProduct.getStockLevel(),
										"Invalid data",JOptionPane.ERROR_MESSAGE);
									}	
								}
								catch(NumberFormatException x)
								{
									JOptionPane.showMessageDialog(null,"Wrong number entered. Whole numbers only","Invalid data",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				}//end else
			}//end try
			catch (NumberFormatException x)
			{
				JOptionPane.showMessageDialog(null,"Wrong number entered","Invalid data",JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
}
