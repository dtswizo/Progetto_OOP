package diet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	TreeMap <String,NutritionalElement> tmapR = new TreeMap<>();
	String name;
	double calories;
	double proteins;
	double carbs;
	double fat;
	double ingredientsqnt;
	double per100g;
	String ingredients="";
	public Recipe (String name,TreeMap <String,NutritionalElement>  tmapR) {
		this.name=name;
		this.tmapR=tmapR;
	 }
    

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		calories+=(tmapR.get(material).getCalories()*quantity)/100;
		proteins+=(tmapR.get(material).getProteins()*quantity)/100;
		carbs+=(tmapR.get(material).getCarbs()*quantity)/100;
		fat+=(tmapR.get(material).getFat()*quantity)/100;
		ingredients+=tmapR.get(material).getName()+" : "+quantity+"\n";
		ingredientsqnt+=quantity;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getCalories() {
		double temp=to100g(this.calories);
		return temp;
	}

	@Override
	public double getProteins() {
		return to100g(this.proteins);
	}

	@Override
	public double getCarbs() {
		return to100g(this.carbs);
	}

	@Override
	public double getFat() {
		return to100g(this.fat);
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	public double to100g(double macro) {
		return (macro*100)/ingredientsqnt;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		return ingredients;
	}
}
