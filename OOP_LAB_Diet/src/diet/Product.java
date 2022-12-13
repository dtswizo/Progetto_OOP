package diet;

public class Product implements NutritionalElement {
	String name;
	double calories;
	double proteins;
	double carbs;
	double fat;
	double per100g;
	public Product (String name,double calories,double proteins,
			double carbs,double fat){
		this.name=name;
		this.calories=calories;
		this.carbs=carbs;
		this.proteins=proteins;
		this.fat=fat;
	 }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public double getCalories() {
		// TODO Auto-generated method stub
		return calories;
	}

	@Override
	public double getProteins() {
		// TODO Auto-generated method stub
		return proteins;
	}

	@Override
	public double getCarbs() {
		// TODO Auto-generated method stub
		return carbs;
	}

	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return fat;
	}

	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
