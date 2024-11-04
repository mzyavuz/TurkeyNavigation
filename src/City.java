import java.lang.reflect.Array;
import java.util.ArrayList;

public class City {
    public String cityName;
    public int x;
    public int y;
    private ArrayList<City> neighbors;

    City() {
        this(null, 0, 0);
    }

    City(String cityName, int x, int y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
    }

    public void setNeighbors(ArrayList<City> neighbors) {
        this.neighbors = neighbors;
    }

    public ArrayList<City> getNeighbors() {
        return neighbors;
    }
}
