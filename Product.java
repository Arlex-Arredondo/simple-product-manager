
public class Product
{	//atributes
	private String name;
	private int stockLevel;
	private double price;

	public Product(String nameIn, int startingStock,double initialPrice)
	{
			name=nameIn;				//product name
			stockLevel=startingStock;	
			price=initialPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStockLevel()
	{
		return stockLevel;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int reStock(int newStockIn)
	{
		stockLevel=stockLevel+newStockIn;
		return stockLevel;
	}
	
	public double sell(int qtitySoldIn)
	{
			stockLevel=stockLevel-qtitySoldIn;
			return price*qtitySoldIn;
	}
	
	public void setPrice(double newPriceIn)
	{
		price=newPriceIn;
	}
}
