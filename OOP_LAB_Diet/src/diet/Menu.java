package diet;

import java.util.TreeMap;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	private TreeMap <String, NutritionalElement> tmapR = new TreeMap<>();
	private TreeMap <String, NutritionalElement> tmapP = new TreeMap<>();
	String name;
	double calories;
	double proteins;
	double carbs;
	double fat;
	double per100g;
	public Menu (String name, TreeMap <String, NutritionalElement> recipes, 
			TreeMap <String, NutritionalElement> product) {
		this.name=name;
		tmapR =recipes;
		tmapP=product;
	}
	
	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		calories+=(tmapR.get(recipe).getCalories()*quantity)/100;
		proteins+=(tmapR.get(recipe).getProteins()*quantity)/100;
		carbs+=(tmapR.get(recipe).getCarbs()*quantity)/100;
		fat+=(tmapR.get(recipe).getFat()*quantity)/100;
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		calories+=tmapP.get(product).getCalories();
		proteins+=tmapP.get(product).getProteins();
		carbs+=tmapP.get(product).getCarbs();
		fat+=tmapP.get(product).getFat();		
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return this.calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return this.proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return this.carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return this.fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
