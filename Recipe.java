package assn3;
/**
 * This Recipe class represents a specific type of bread, its ingredients, and the quantity ordered.
 * @author ELYSE NIYONAGIRA
 * @since java version "21.0.4"  */
import java.util.HashMap;
import java.util.Map;

/**
 * Recipe represents a specific type of bread, its ingredients, and the quantity ordered.
 */
public class Recipe {
    private String name; // Name of the bread
    private Map<String, Float> ingredients; // Ingredients required
    private int quantityOrdered; // Quantity ordered
    /**
     * Constructs a Recipe with the specified name.
     *
     * @param name The name of the bread.
     */
    public Recipe(String name) {
        this.name = name;
        this.ingredients = new HashMap<>();
        this.quantityOrdered = 0;
    }
    /**
     * Adds an ingredient and its amount to the recipe.
     *
     * @param ingredient The name of the ingredient.
     * @param amount     The amount of the ingredient required.
     */
    public void addIngredient(String ingredient, float amount) {
        ingredients.put(ingredient, amount);
    }

    /**
     * Returns the name of the bread.
     *
     * @return The name of the bread.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the ingredients of the recipe.
     *
     * @return A map of ingredients and their amounts.
     */
    public Map<String, Float> getIngredients() {
        return ingredients;
    }
    /**
     * Sets the quantity ordered for this recipe.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantityOrdered(int quantity) {
        this.quantityOrdered = quantity;
    }

    /**
     * Returns the quantity ordered for this recipe.
     *
     * @return The quantity ordered.
     */
    public int getQuantityOrdered() {
        return quantityOrdered;
    }
}