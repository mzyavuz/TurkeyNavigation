import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Scanner;

public class MelikeZeynepCakmakci {
    public static void main(String[] args) {

        int mapWidth = 2377;
        int mapHeight = 1055;
        int canvasWidth = (int) (mapWidth * 0.6);
        int canvasHeight = (int) (mapHeight * 0.6);
        //StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        //StdDraw.setXscale(0, 2377);
        //StdDraw.setYscale(0, 1055);
        //StdDraw.picture((double) mapWidth /2, (double) mapHeight/2,"map.png",mapWidth, mapHeight);

        String cityCoordinates = "Edirne, 142, 980\n" +
                "Kirklareli, 214, 986\n" +
                "Tekirdag, 244, 870\n" +
                "Istanbul, 416, 860\n" +
                "Canakkale, 116, 738\n" +
                "Balikesir, 274, 648\n" +
                "Bursa, 406, 716\n" +
                "Yalova, 426, 784\n" +
                "Kocaeli, 514, 810\n" +
                "Sakarya, 598, 822\n" +
                "Duzce, 688, 806\n" +
                "Bolu, 736, 788\n" +
                "Zonguldak, 740, 888\n" +
                "Bartin, 822, 926\n" +
                "Kastamonu, 966, 900\n" +
                "Karabuk, 856, 882\n" +
                "Cankiri, 950, 768\n" +
                "Ankara, 836, 652\n" +
                "Bilecik, 534, 702\n" +
                "Eskisehir, 618, 640\n" +
                "Kutahya, 502, 572\n" +
                "Manisa, 230, 484\n" +
                "Izmir, 178, 468\n" +
                "Aydin, 244, 358\n" +
                "Mugla, 334, 246\n" +
                "Antalya, 582, 202\n" +
                "Denizli, 414, 312\n" +
                "Burdur, 548, 310\n" +
                "Isparta, 582, 336\n" +
                "Usak, 432, 476\n" +
                "Afyon, 554, 468\n" +
                "Mersin, 1108, 178\n" +
                "Hatay, 1292, 66\n" +
                "Karaman, 952, 254\n" +
                "Adana, 1174, 208\n" +
                "Nigde, 1096, 332\n" +
                "Aksaray, 1030, 420\n" +
                "Konya, 778, 382\n" +
                "Nevsehir, 1098, 450\n" +
                "Kayseri, 1206, 456\n" +
                "Yozgat, 1146, 596\n" +
                "Kirsehir, 1022, 534\n" +
                "Kirikkale, 960, 658\n" +
                "Sinop, 1134, 984\n" +
                "Samsun, 1290, 860\n" +
                "Amasya, 1226, 760\n" +
                "Corum, 1094, 782\n" +
                "Tokat, 1324, 706\n" +
                "Sivas, 1366, 640\n" +
                "Osmaniye, 1284, 226\n" +
                "Gaziantep, 1438, 220\n" +
                "Kilis, 1406, 166\n" +
                "Sanliurfa, 1598, 234\n" +
                "Mardin, 1842, 276\n" +
                "Kahramanmaras, 1376, 308\n" +
                "Adiyaman, 1558, 306\n" +
                "Malatya, 1558, 422\n" +
                "Diyarbakir, 1784, 344\n" +
                "Sirnak, 2088, 332\n" +
                "Hakkari, 2250, 348\n" +
                "Van, 2176, 484\n" +
                "Batman, 1916, 372\n" +
                "Elazig, 1650, 492\n" +
                "Tunceli, 1702, 528\n" +
                "Bingol, 1808, 508\n" +
                "Erzincan, 1692, 636\n" +
                "Bayburt, 1760, 736\n" +
                "Gumushane, 1674, 752\n" +
                "Ordu, 1472, 822\n" +
                "Giresun, 1560, 812\n" +
                "Trabzon, 1688, 832\n" +
                "Rize, 1800, 840\n" +
                "Artvin, 1938, 888\n" +
                "Ardahan, 2052, 900\n" +
                "Igdir, 2270, 712\n" +
                "Kars, 2086, 808\n" +
                "Erzurum, 1888, 678\n" +
                "Agri, 2124, 668\n" +
                "Mus, 1930, 500\n" +
                "Bitlis, 2030, 460\n" +
                "Siirt, 2014, 374";

        String cityConnections = "Adana,Osmaniye\n" +
                "Adana,Nigde\n" +
                "Adiyaman,Sanliurfa\n" +
                "Adiyaman,Malatya\n" +
                "Adiyaman,Kahramanmaras\n" +
                "Afyon,Isparta\n" +
                "Afyon,Konya\n" +
                "Afyon,Eskisehir\n" +
                "Afyon,Usak\n" +
                "Agri,Van\n" +
                "Agri,Bitlis\n" +
                "Amasya,Tokat\n" +
                "Amasya,Samsun\n" +
                "Ankara,Konya\n" +
                "Ankara,Kirsehir\n" +
                "Ankara,Cankiri\n" +
                "Ankara,Bolu\n" +
                "Ankara,Eskisehir\n" +
                "Antalya,Mersin\n" +
                "Antalya,Isparta\n" +
                "Antalya,Burdur\n" +
                "Antalya,Mugla\n" +
                "Artvin,Rize\n" +
                "Artvin,Erzurum\n" +
                "Artvin,Ardahan\n" +
                "Aydin,Mugla\n" +
                "Aydin,Denizli\n" +
                "Aydin,Izmir\n" +
                "Balikesir,Izmir\n" +
                "Balikesir,Kutahya\n" +
                "Balikesir,Bursa\n" +
                "Balikesir,Canakkale\n" +
                "Bilecik,Eskisehir\n" +
                "Bilecik,Bolu\n" +
                "Bilecik,Sakarya\n" +
                "Bilecik,Bursa\n" +
                "Bingol,Diyarbakir\n" +
                "Bingol,Erzurum\n" +
                "Bingol,Tunceli\n" +
                "Bolu,Karabuk\n" +
                "Bolu,Zonguldak\n" +
                "Bolu,Duzce\n" +
                "Burdur,Denizli\n" +
                "Bursa,Kutahya\n" +
                "Bursa,Yalova\n" +
                "Canakkale,Tekirdag\n" +
                "Canakkale,Edirne\n" +
                "Cankiri,Corum\n" +
                "Cankiri,Karabuk\n" +
                "Corum,Yozgat\n" +
                "Corum,Samsun\n" +
                "Corum,Sinop\n" +
                "Corum,Kastamonu\n" +
                "Denizli,Mugla\n" +
                "Denizli,Usak\n" +
                "Diyarbakir,Malatya\n" +
                "Edirne,Kirklareli\n" +
                "Elazig,Tunceli\n" +
                "Elazig,Malatya\n" +
                "Erzincan,Erzurum\n" +
                "Erzincan,Bayburt\n" +
                "Erzincan,Gumushane\n" +
                "Erzincan,Giresun\n" +
                "Erzurum,Mus\n" +
                "Erzurum,Kars\n" +
                "Erzurum,Bayburt\n" +
                "Eskisehir,Kutahya\n" +
                "Gaziantep,Kilis\n" +
                "Gaziantep,Sanliurfa\n" +
                "Giresun,Trabzon\n" +
                "Gumushane,Trabzon\n" +
                "Hakkari,Sirnak\n" +
                "Hatay,Osmaniye\n" +
                "Isparta,Konya\n" +
                "Mersin,Karaman\n" +
                "Istanbul,Kocaeli\n" +
                "Istanbul,Tekirdag\n" +
                "Istanbul,Kirklareli\n" +
                "Kars,Igdir\n" +
                "Kastamonu,Sinop\n" +
                "Kastamonu,Karabuk\n" +
                "Kayseri,Kahramanmaras\n" +
                "Kayseri,Sivas\n" +
                "Kayseri,Yozgat\n" +
                "Kirklareli,Tekirdag\n" +
                "Kirsehir,Nevsehir\n" +
                "Kirsehir,Yozgat\n" +
                "Kirsehir,Aksaray\n" +
                "Kocaeli,Yalova\n" +
                "Kocaeli,Sakarya\n" +
                "Konya,Karaman\n" +
                "Konya,Aksaray\n" +
                "Kutahya,Manisa\n" +
                "Malatya,Kahramanmaras\n" +
                "Malatya,Sivas\n" +
                "Manisa,Usak\n" +
                "Kahramanmaras,Osmaniye\n" +
                "Batman,Mardin\n" +
                "Mardin,Sirnak\n" +
                "Batman,Mus\n" +
                "Nevsehir,Nigde\n" +
                "Nevsehir,Aksaray\n" +
                "Nigde,Aksaray\n" +
                "Rize,Trabzon\n" +
                "Sakarya,Duzce\n" +
                "Samsun,Ordu\n" +
                "Samsun,Sinop\n" +
                "Batman,Siirt\n" +
                "Siirt,Sirnak\n" +
                "Sivas,Ordu\n" +
                "Sivas,Tokat\n" +
                "Sivas,Yozgat\n" +
                "Tokat,Ordu\n" +
                "Usak,Denizli\n" +
                "Yozgat,Sivas\n" +
                "Zonguldak,Bartin\n" +
                "Zonguldak,Karabuk";
        String[] cities = cityCoordinates.split("\n");
        ArrayList<City> citiesList = createCitiesList(cities);

        String[] connectedCitiesList = cityConnections.split("\n");
        setNeighborCities(connectedCitiesList, citiesList);

        StdDraw.setPenColor(StdDraw.GRAY);
        for (City city : citiesList) {
            StdDraw.setPenRadius(0.001);
            StdDraw.text(city.x, city.y+10, city.cityName);
            StdDraw.setPenRadius(0.005);
            StdDraw.point(city.x, city.y);
        }
        for (City city: citiesList) {
            for (City neighbor: city.getNeighbors())
                StdDraw.line(city.x, city.y, neighbor.x, neighbor.y);
        }
        //StdDraw.enableDoubleBuffering();

        Scanner input = new Scanner(System.in);
        City startingCity = null;
        while (startingCity == null) {
            System.out.print("Enter starting city: ");
            String startingCityName = input.next();
            startingCity = getCity(citiesList, startingCityName);
        }
        City destinationCity = null;
        while (destinationCity == null) {
            System.out.print("Enter destination city: ");
            String destinationCityName = input.next();
            destinationCity = getCity(citiesList, destinationCityName);
        }
        ArrayList<ArrayList<City>> allPaths = findAllPaths(startingCity, destinationCity);

        if (allPaths.size() == 0) {
            System.out.println("No paths found");
        }
        else {
            for (ArrayList<City> path: allPaths)
                printPath(path);
        }
        System.out.println("Program finished.");
    }

    private static ArrayList<City> createCitiesList(String[] cities) {
        ArrayList<City> cityList = new ArrayList<>();
        for (String city: cities)  {
            String[] cityInfo = city.split(", ");
            cityList.add(new City(cityInfo[0], Integer.parseInt(cityInfo[1]), Integer.parseInt(cityInfo[2])));
        }
        return cityList;
    }

    private static void setNeighborCities(String[] connectedCitiesList, ArrayList<City> citiesList) {
        for (City firstCity: citiesList)  {
            ArrayList<City> neighborCities = new ArrayList<>();
            for (String connection: connectedCitiesList) {
                String[] connectedCities = connection.split(",");
                if (connectedCities[0].equals(firstCity.cityName) || connectedCities[1].equals(firstCity.cityName)) {
                    for (City secondCity: citiesList) {
                        if (secondCity.cityName.equals(connectedCities[1]) || connectedCities[0].equals(secondCity.cityName)) {
                            neighborCities.add(secondCity);
                        }
                    }
                }
            }
            neighborCities.removeIf(firstCity::equals);
            firstCity.setNeighbors(neighborCities);
        }
    }

    private static double calculateDistance(City city1, City city2) {
        return (Math.pow(Math.pow((city2.x-city1.x),2) + Math.pow((city2.y-city1.y),2),0.5));
    }

    private static ArrayList<ArrayList<City>> findAllPaths(City startCity, City destinationCity) {
        ArrayList<ArrayList<City>> allPaths = new ArrayList<>();
        ArrayList<City> currentPath = new ArrayList<>();
        currentPath.add(startCity);
        if (startCity == destinationCity) {
            allPaths.add(currentPath);
            return allPaths;
        } else if (startCity.getNeighbors().isEmpty() || destinationCity.getNeighbors().isEmpty())
            return allPaths;
        currentPath.add(startCity.getNeighbors().getFirst());
        createPath(currentPath, 0, destinationCity);
        while (currentPath != null) {
            if (currentPath.getLast() == startCity)
                break;
            System.out.println("Curren Path null değil");
            if (currentPath.getLast() == destinationCity) {
                allPaths.add((ArrayList<City>) currentPath.clone());
                currentPath = getNextNeighbor(currentPath, destinationCity);
                System.out.println("Yeni current path");
            }
        }
        System.out.println("All paths has been found!");
        return allPaths;
    }

    private static boolean addNextCity(ArrayList<City> currentPath, City neighbor) {
        if (currentPath.contains(neighbor))
            return false;
        currentPath.add(neighbor);
        return true;
    }

    private static ArrayList<City> createPath(ArrayList<City> currentPath,int currentNeighborIndex, City destinationCity) {
        if (currentPath == null) return null;
        City currentCity = currentPath.getLast();
        if (destinationCity.equals(currentCity))
            return currentPath;
        else if (currentPath.size() == 1 && currentNeighborIndex == currentPath.getFirst().getNeighbors().size()) {
            System.out.println("Son eklediğim null");
            return null;
        }
        //else if (currentPath.size() > 3)
        //    return getNextNeighbor(currentPath, destinationCity);
        //else if (currentNeighborIndex == currentCity.getNeighbors().size() && currentCity == currentPath.getFirst().getNeighbors().getLast()) {
        //    System.out.println("İlk null ve Current Path:");
        //    printPath(currentPath);
        //    return null;
        //}
        else if (currentNeighborIndex == currentCity.getNeighbors().size()) {
            return getNextNeighbor(currentPath, destinationCity);
        }
        City currentNeighbor = currentCity.getNeighbors().get(currentNeighborIndex);
        if (addNextCity(currentPath, currentNeighbor))
            return createPath(currentPath, 0, destinationCity);
        else
            return createPath(currentPath, ++currentNeighborIndex, destinationCity);

    }

    private static ArrayList<City> getNextNeighbor(ArrayList<City> currentPath, City destinationCity) {
        City currentCityBeforeLast = currentPath.get(currentPath.size()-2);
        City currentLastCity = currentPath.getLast();
        ArrayList<City> neighbors = currentCityBeforeLast.getNeighbors();
        int currentNeighborIndex = neighbors.indexOf(currentLastCity);
        currentPath.remove(currentLastCity);
        return createPath(currentPath, ++currentNeighborIndex, destinationCity);
    }

    private static void printPath(ArrayList<City> path) {
        for (City city: path)
            System.out.print(city.cityName + "->");
        System.out.println();
    }

    private static City getCity(ArrayList<City> citiesList, String cityName) {
        for(City city: citiesList) {
            if (city.cityName.equals(cityName))
                return city;
        }
        return null;
    }
}

/**
 *             while (!destinationCity.equals(currentCity)) {
 *                 ArrayList<City> path = new ArrayList<>();
 *                 if (addPath(path, neighbor) == null)
 *                     break;
 *                 currentCity = neighbor.getNeighbors().
 *             }
 *
 *                 private static ArrayList<ArrayList<City>> findAllPathsCOPILOT(City startCity, City destinationCity) {
 *         ArrayList<ArrayList<City>> allPaths = new ArrayList<>();
 *         ArrayList<City> currentPath = new ArrayList<>();
 *         findPathsRecursive(startCity, destinationCity, currentPath, allPaths);
 *         return allPaths;
 *     }
 *
 *     private static void findPathsRecursive(City currentCity, City destinationCity, ArrayList<City> currentPath, ArrayList<ArrayList<City>> allPaths) {
 *         currentPath.add(currentCity);
 *         if (currentCity.equals(destinationCity)) {
 *             allPaths.add(new ArrayList<>(currentPath));
 *         } else {
 *             for (City neighbor : currentCity.getNeighbors()) {
 *                 if (!currentPath.contains(neighbor)) {
 *                     findPathsRecursive(neighbor, destinationCity, currentPath, allPaths);
 *                 }
 *             }
 *         }
 *         currentPath.remove(currentCity);
 *     }
 */