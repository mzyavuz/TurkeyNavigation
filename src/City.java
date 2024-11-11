import java.util.ArrayList;

/**
 * Represents a city with a name, coordinates, and neighboring cities.
 */
public class City {
    public String cityName;
    public int x;
    public int y;
    private ArrayList<City> neighbors;

    /**
     * Default constructor for City.
     */
    City() {
        this(null, 0, 0);
    }

    /**
     * Constructs a City with the specified name and coordinates.
     *
     * @param cityName the name of the city
     * @param x the x-coordinate of the city
     * @param y the y-coordinate of the city
     */
    City(String cityName, int x, int y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the neighboring cities.
     *
     * @param neighbors the list of neighboring cities
     */
    public void setNeighbors(ArrayList<City> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Gets the list of neighboring cities.
     *
     * @return the list of neighboring cities
     */
    public ArrayList<City> getNeighbors() {
        return neighbors;
    }
}