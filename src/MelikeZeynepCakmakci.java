/**
 * Program finds the approximate integral value of a polynomial
 * within the interval by using Riemann Sum Method.
 * @author M.Zeynep Çakmakcı, Student ID: 2024719030
 * @since Date: 06.11.2024
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class to determine all paths from one city to another and find the shortest path.
 */
public class MelikeZeynepCakmakci {
    private static double shortestDistance = 0;
    private static Path shortestPath = null;

    /**
     * Main method to execute the program.
     * @param args command-line arguments. (not used)
     */
    public static void main(String[] args) {

        int mapWidth = 2377;
        int mapHeight = 1055;
        int canvasWidth = (int) (mapWidth * 0.6);
        int canvasHeight = (int) (mapHeight * 0.6);
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, 2377);
        StdDraw.setYscale(0, 1055);
        StdDraw.picture((double) mapWidth /2, (double) mapHeight/2,"map.png",mapWidth, mapHeight);


        File cityCoordinates = new File("src/city_coordinates.txt");
        ArrayList<City> citiesList = new ArrayList<>();
        try {
            Scanner cityCoordinatesScanner = new Scanner(cityCoordinates);
            while (cityCoordinatesScanner.hasNextLine()) {
                String[] cityInfo = cityCoordinatesScanner.nextLine().split(", ");
                citiesList.add(new City(cityInfo[0], Integer.parseInt(cityInfo[1]), Integer.parseInt(cityInfo[2])));
                citiesList.getLast().setNeighbors(new ArrayList<>());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File cityConnections = new File("src/city_connections.txt");
        try {
            Scanner cityConnectionsScanner = new Scanner(cityConnections);
            while (cityConnectionsScanner.hasNextLine()) {
                String connectedCityInfo = cityConnectionsScanner.nextLine();
                String[] connectedCities = connectedCityInfo.split(",");
                for (City city : citiesList) {
                    if (city.cityName.equals(connectedCities[0]) || city.cityName.equals(connectedCities[1])) {
                        for (City secondCity : citiesList) {
                            if (secondCity.cityName.equals(connectedCities[1]) || connectedCities[0].equals(secondCity.cityName)) {
                                ArrayList<City> firstNeighbours = city.getNeighbors();
                                firstNeighbours.add(secondCity);
                                ArrayList<City> secondNeighbours = secondCity.getNeighbors();
                                secondNeighbours.add(city);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StdDraw.setPenColor(StdDraw.GRAY);
        for (City city : citiesList) {
            StdDraw.setPenRadius(0.001);
            StdDraw.text(city.x, city.y + 10, city.cityName);
            StdDraw.setPenRadius(0.005);
            StdDraw.point(city.x, city.y);
        }
        for (City city : citiesList) {
            for (City neighbor : city.getNeighbors())
                StdDraw.line(city.x, city.y, neighbor.x, neighbor.y);
        }

        Scanner input = new Scanner(System.in);
        City startingCity = null;
        while (startingCity == null) {
            System.out.print("Enter starting city: ");
            String startingCityName = input.next();
            startingCity = getCity(citiesList, startingCityName);
            if (startingCity == null)
                System.out.println("City named '"+ startingCityName + "' not found. Please enter a valid city name.");
        }
        City destinationCity = null;
        while (destinationCity == null) {
            System.out.print("Enter destination city: ");
            String destinationCityName = input.next();
            destinationCity = getCity(citiesList, destinationCityName);
            if (destinationCity == null)
                System.out.println("City named '"+ destinationCityName + "' not found. Please enter a valid city name.");
        }

        updateNeighborsOrder(citiesList, destinationCity);
        /*
        ArrayList<City> shortestPath = findShortestPath(startingCity, destinationCity);
        if (shortestPath != null) {
            System.out.print("Total Distance: " + shortestDistance + ". ");
            printPath(shortestPath);
            StdDraw.setPenColor(StdDraw.RED);
            for (int i = 0; i < shortestPath.size(); i++) {
                StdDraw.setPenRadius(0.002);
                StdDraw.text(shortestPath.get(i).x, shortestPath.get(i).y+10, shortestPath.get(i).cityName);
                StdDraw.setPenRadius(0.007);
                StdDraw.point(shortestPath.get(i).x, shortestPath.get(i).y);
                if (i != shortestPath.size() - 1)
                    StdDraw.line(shortestPath.get(i).x, shortestPath.get(i).y, shortestPath.get(i+1).x, shortestPath.get(i+1).y);
            }
            StdDraw.enableDoubleBuffering();
        } else
            System.out.println("No path could be found");
         */
        if (startingCity.equals(destinationCity)) {
            System.out.println("Total Distance: 0.00. Path:" + destinationCity.cityName);
        }
        ArrayList<Path> allPaths = new ArrayList<>();
        ArrayList<City> stances = new ArrayList<>();
        stances.add(startingCity);
        Path startPath = new Path(stances);
        allPaths.add(startPath);
        dijkstra(allPaths, destinationCity);
        if (shortestPath == null)
            System.out.println("No path could be found");
        else {
            System.out.print("Total Distance: " + calculatePathLength(shortestPath.stances) + ". ");
            printPath(shortestPath.stances);
        }

        System.out.println("Program finished.");
    }

    /**
     * Creates a list of cities from the given array of city with name and coordinates.
     *
     * @param cities an array of city information
     * @return a list of City objects
     */
    private static ArrayList<City> createCitiesList(String[] cities) {
        ArrayList<City> cityList = new ArrayList<>();
        for (String city : cities) {
            String[] cityInfo = city.split(", ");
            cityList.add(new City(cityInfo[0], Integer.parseInt(cityInfo[1]), Integer.parseInt(cityInfo[2])));
        }
        return cityList;
    }

    /**
     * Sets the neighboring cities for each city in the list.
     *
     * @param connectedCityInfo two Cities with connection
     * @param citiesList a list of City objects
     */
    private static void setNeighborCities(String connectedCityInfo, ArrayList<City> citiesList) {
        for (City firstCity : citiesList) {
            ArrayList<City> neighborCities = new ArrayList<>();
                String[] connectedCities = connectedCityInfo.split(",");
                if (connectedCities[0].equals(firstCity.cityName) || connectedCities[1].equals(firstCity.cityName)) {
                    for (City secondCity : citiesList) {
                        if (secondCity.cityName.equals(connectedCities[1]) || connectedCities[0].equals(secondCity.cityName)) {
                            neighborCities.add(secondCity);
                    }
                }
            }
            neighborCities.removeIf(firstCity::equals);
            firstCity.setNeighbors(neighborCities);
        }
    }

    /**
     * Calculates the distance between two cities.
     *
     * @param city1 the first city
     * @param city2 the second city
     * @return the distance between the two cities
     */
    private static double calculateDistance(City city1, City city2) {
        return (Math.pow(Math.pow((city2.x - city1.x), 2) + Math.pow((city2.y - city1.y), 2), 0.5));
    }

    /**
     * Calculates the total length of the given path.
     *
     * @param currentPath the path to calculate the length for
     * @return the total length of the path
     */
    private static double calculatePathLength(ArrayList<City> currentPath) {
        double length = 0;
        for (int i = 0; i <= currentPath.size()-2; i++)
            length += calculateDistance(currentPath.get(i), currentPath.get(i + 1));
        return length;
    }

    /**
     * Checks if the given path is the shortest path found so far.
     *
     * @param currentPath the path to check
     * @return true if the path is the shortest, false otherwise
     */
    private static boolean isShortestPath(ArrayList<City> currentPath) {
        return !(calculatePathLength(currentPath) > shortestDistance);
    }

    /**
     * Finds all paths from the starting city to the destination city.
     *
     * @param startingCity the starting city
     * @param destinationCity the destination city
     * @return a list of all paths
     */
    private static ArrayList<ArrayList<City>> findAllPaths(City startingCity, City destinationCity) {
        ArrayList<ArrayList<City>> allPaths = new ArrayList<>();
        ArrayList<City> currentPath = new ArrayList<>();
        currentPath.add(startingCity);
        if (startingCity == destinationCity) {
            allPaths.add(currentPath);
            return allPaths;
        } else if (startingCity.getNeighbors().isEmpty() || destinationCity.getNeighbors().isEmpty())
            return allPaths;
        currentPath.add(startingCity.getNeighbors().getFirst());
        createPath(currentPath, 0, destinationCity);
        int maxPath = 0;
        while (currentPath != null && maxPath < 10000000) {
            if (currentPath.getLast() == startingCity)
                break;
            if (currentPath.getLast() == destinationCity)
                allPaths.add((ArrayList<City>) currentPath.clone());
            currentPath = getNextNeighbor(currentPath, destinationCity);
            maxPath++;
        }
        System.out.println("All paths have been found!");
        return allPaths;
    }

    /**
     * Finds the shortest path from the starting city to the destination city.
     *
     * @param startingCity the starting city
     * @param destinationCity the destination city
     * @return the shortest path
     */
    private static ArrayList<City> findShortestPath(City startingCity, City destinationCity) {
        ArrayList<City> currentPath = new ArrayList<>();
        ArrayList<City> shortestPath = null;
        if (startingCity.getNeighbors().isEmpty() || destinationCity.getNeighbors().isEmpty())
            return currentPath;
        currentPath.add(startingCity);
        if (startingCity == destinationCity)
            return currentPath;
        currentPath.add(startingCity.getNeighbors().getFirst());
        createPath(currentPath, 0, destinationCity);
        int maxDepth = 0;
        while (currentPath != null && maxDepth < 100000000) {
            if (currentPath.getLast() == startingCity)
                break;
            if (currentPath.getLast() == destinationCity) {
                if (isShortestPath(currentPath) || shortestDistance == 0) {
                    shortestDistance = calculatePathLength(currentPath);
                    if (currentPath.equals(shortestPath))
                        return shortestPath;
                    shortestPath = (ArrayList<City>) currentPath.clone();
                }
            }
            currentPath = getNextNeighbor(currentPath, destinationCity);
            maxDepth++;
        }
        return shortestPath;
    }

    /**
     * Adds the next city to the current path if it is not already in the path.
     *
     * @param currentPath the current path
     * @param neighbor the next city to add or not
     * @return true if the city was added, false otherwise
     */
    private static boolean addNextCity(ArrayList<City> currentPath, City neighbor) {
        if (currentPath.contains(neighbor))
            return false;
        currentPath.add(neighbor);
        return true;
    }

    /**
     * Creates a path from the current path to the destination city.
     *
     * @param currentPath the current path
     * @param currentNeighborIndex the index of the current neighbor
     * @param destinationCity the destination city
     * @return the created path
     */
    private static ArrayList<City> createPath(ArrayList<City> currentPath, int currentNeighborIndex, City destinationCity) {
        if (currentPath == null) return null;
        City currentCity = currentPath.getLast();
        if (destinationCity.equals(currentCity))
            return currentPath;
        else if (currentPath.size() == 1)
            return null;
        else if (currentPath.size() == 2 && currentNeighborIndex == currentPath.getFirst().getNeighbors().size())
            return null;
        else if (currentPath.size() > 22)
            return currentPath;
        else if (currentNeighborIndex == currentCity.getNeighbors().size())
            return getNextNeighbor(currentPath, destinationCity);
        City currentNeighbor = currentCity.getNeighbors().get(currentNeighborIndex);
        if (addNextCity(currentPath, currentNeighbor)) {
            if (shortestDistance == 0 || isShortestPath(currentPath))
                return createPath(currentPath, 0, destinationCity);
            else
                return currentPath;
        } else
            return createPath(currentPath, ++currentNeighborIndex, destinationCity);
    }

    /**
     * Removes the last city in the path and gets the next neighbor index.
     *
     * @param currentPath the current path
     * @param destinationCity the destination city
     * @return the next neighbor city
     */
    private static ArrayList<City> getNextNeighbor(ArrayList<City> currentPath, City destinationCity) {
        City currentCityBeforeLast = currentPath.get(currentPath.size() - 2);
        City currentLastCity = currentPath.getLast();
        ArrayList<City> neighbors = currentCityBeforeLast.getNeighbors();
        int currentNeighborIndex = neighbors.indexOf(currentLastCity);
        currentPath.remove(currentLastCity);
        return createPath(currentPath, ++currentNeighborIndex, destinationCity);
    }

    /**
     * Prints the given path.
     *
     * @param path the path to print
     */
    private static void printPath(ArrayList<City> path) {
        System.out.print("Path: ");
        int i = 0;
        while (i < path.size() - 1)
            System.out.print(path.get(i++).cityName + " -> ");
        System.out.println(path.get(i).cityName);
    }

    /**
     * Gets the city with the specified name from the list of cities.
     *
     * @param citiesList the list of cities
     * @param cityName the name of the city
     * @return the city with the specified name, or null if not found
     */
    private static City getCity(ArrayList<City> citiesList, String cityName) {
        for (City city : citiesList) {
            if (city.cityName.equals(cityName))
                return city;
        }
        return null;
    }

    /**
     * Updates the order of neighbors for each city based on their distance to the destination city.
     *
     * @param citiesList the list of cities
     * @param destinationCity the destination city
     */
    private static void updateNeighborsOrder(ArrayList<City> citiesList, City destinationCity) {
        for (City city : citiesList) {
            ArrayList<City> neighbors = city.getNeighbors();
            for (int i = 0; i < neighbors.size() - 1; i++) {
                int closestIndex = i;
                double closestDistance = calculateDistance(neighbors.get(i), destinationCity);
                for (int j = i + 1; j < neighbors.size(); j++) {
                    double currentDistance = calculateDistance(neighbors.get(j), destinationCity);
                    if (currentDistance < closestDistance) {
                        closestIndex = j;
                        closestDistance = currentDistance;
                    }
                }
                if (closestIndex != i) {
                    City temp = neighbors.get(i);
                    neighbors.set(i, neighbors.get(closestIndex));
                    neighbors.set(closestIndex, temp);
                }
            }
        }
    }

    private static void dijkstra(ArrayList<Path> currentPaths, City destinationCity) {
        int newPathsSize = 0;
        if (currentPaths == null)
            return;
        else {
            for (int i = 0; i < currentPaths.size(); i++) {
                Path currentPath = new Path((ArrayList<City>) currentPaths.get(i).stances.clone());
                ArrayList<Path> newPaths = createPathDijkstra(currentPaths, currentPath, destinationCity);
                if (newPaths == null)
                    continue;
                currentPaths.remove(i);
                i--;
                //Check destinations includes same cities
                ArrayList<Path> removedPaths = checkNewPaths(currentPaths, newPaths);
                currentPaths.addAll(newPaths);
                currentPaths.removeAll(removedPaths);
            }
        }
    }

    private static ArrayList<Path> createPathDijkstra(ArrayList<Path> currentPaths, Path currentPath, City destinationCity) {
        ArrayList<Path> newPaths = new ArrayList<>();
        City currentCity = currentPath.stances.getLast();
        ArrayList<City> neighbors = (ArrayList<City>) currentCity.getNeighbors().clone();
        for (City neighbor : neighbors) {
            Path newPath = null;
            if (!currentPath.stances.contains(neighbor)) {
                newPath = new Path((ArrayList<City>) currentPath.stances.clone());
                newPath.addCity(neighbor);
                if (neighbor.equals(destinationCity)) {
                    newPaths.clear();
                    System.out.println("BULUNMUŞ YOL:");
                    printPath(newPath.stances);
                    if (shortestPath == null)
                        shortestPath = newPath;
                    else if (shortestPath.totalDistance < newPath.totalDistance)
                        return newPaths;
                    newPaths.add(new Path(newPath.stances));
                    return newPaths;
                }
                newPaths.add(new Path(newPath.stances));
            }
        }
        if (newPaths.isEmpty())
            return null;
        return newPaths;
    }

    private static ArrayList<Path> checkNewPaths(ArrayList<Path> currentPaths, ArrayList<Path> newPaths) {
        if (currentPaths == null || newPaths == null)
            return null;
        ArrayList<Path> removedPaths = new ArrayList<>();
        for (int i = 0; i < newPaths.size(); i++) {
            Path newPath = newPaths.get(i);
            City lastCity = newPath.stances.getLast();
            for (Path currentPath : currentPaths) {
                if (currentPath.stances.contains(lastCity)) {
                    if (currentPath.calculateDistanceTo(lastCity) > newPath.totalDistance)
                        removedPaths.add(currentPath);
                    else
                        removedPaths.add(newPath);
                }
            }
        }
        return removedPaths;
    }

    private static class Path {
        ArrayList<City> stances;
        double totalDistance;

        Path() {}

        Path(ArrayList<City> stances) {
            this.stances = stances;
            this.totalDistance = calculatePathLength(stances);
        }

        public double calculateDistanceTo(City givenStance) {
            double distance = 0;
            for (int i = 0; i < stances.indexOf(givenStance); i++)
                distance += calculateDistance(stances.get(i), stances.get(i + 1));
            return distance;
        }

        public void addCity(City newStance) {
            this.totalDistance += calculateDistance(stances.getLast(), newStance);
            stances.add(newStance);
        }

    }
}