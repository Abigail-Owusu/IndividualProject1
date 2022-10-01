
import java.io.*;
import java.util.*;

public class mainFile {
    //a hashmap that takes the city and country as a key and value is a list of all airports codes
    static HashMap<String, ArrayList<String>> allAirportsInACountry = new HashMap<>();
    //this will take an airport code as the key and city and country as the value
    static HashMap<String, String> airportInAParticularCountry = new HashMap<>();

    //takes in a sourceAirportCode code as the key and value as route object
    static HashMap<String, ArrayList<Route>> routeHashMap = new HashMap<>();

    public static ArrayList<String> bfsTest(String startCityCountry, String endCityCountry){
        Queue<Node> frontier = new ArrayDeque<>();
        Set<String> explored = new HashSet<>();

        //retrieving all airports in the specified cityCountry
        ArrayList<String> airports = allAirportsInACountry.get(startCityCountry);

        if( airports != null){
        for (String airport: airports) {
            //creating a new node
            Node airportNode = new Node(null,airport,0, null, null);
            frontier.add(airportNode);

        }}
        while (!frontier.isEmpty()){
            Node removedNode = frontier.remove();

            //to keep track of already visited airports.
            explored.add(removedNode.getAirportCode());

            //successor airports it can land in
            ArrayList<Route> successors = routeHashMap.get(removedNode.getAirportCode());

            if (successors != null){
                for (Route successor:
                     successors) {
                    Node childNode = new Node(removedNode, successor.getDesAirportCode(),successor.getStops(),
                            null,successor.getAirlineCode());
                    if (!frontier.contains(childNode) && !explored.contains(childNode.getAirportCode())){
                        String destination = airportInAParticularCountry.get(childNode.getAirportCode());
                        if (destination != null && destination.equals(endCityCountry)) {
                            return childNode.solutionPath();

                        }
                    }
                    frontier.add(childNode);
                }
            }
        } return null;
    }

    public static void main(String[] args) {
        try {
            //Reading the csv files
            BufferedReader routeCSV = new BufferedReader(new FileReader("routes.csv"));
            BufferedReader airportCSV = new BufferedReader(new FileReader("airports.csv"));


            //reading the files and populating the routeHashmaps
            String routeLine = routeCSV.readLine();
            while (routeLine != null){

                String[] strRouteLine = routeLine.split(",");
                Route route = new Route(strRouteLine[0],strRouteLine[2],Integer.parseInt(strRouteLine[7]));

                //making sure there are only distinct routes in the routeHashMap
                if (routeHashMap.containsKey(strRouteLine[4])){
                    ArrayList<Route> newRouteList = routeHashMap.get(strRouteLine[4]);
                    newRouteList.add(route);
                    routeHashMap.put(strRouteLine[4], newRouteList);
                }else{
                    ArrayList<Route> uniqueRouteList = new ArrayList<>();
                    uniqueRouteList.add(route);
                    routeHashMap.put(strRouteLine[4], uniqueRouteList);
                }

                routeLine = routeCSV.readLine();

            }

            String airportLine = airportCSV.readLine();
            while (airportLine != null){
                // populating airports in a particular country
                String[] strAirportLine = airportLine.split(",");
                String cityCountry = strAirportLine[2]+ ", "+ strAirportLine[3];
                String airportCode = strAirportLine[4];
                airportInAParticularCountry.put(airportCode, cityCountry);

                //populating allAirportsInACountryHashMap
                if (allAirportsInACountry.containsKey(cityCountry)){
                    ArrayList<String> newAirportList = allAirportsInACountry.get(cityCountry);
                    newAirportList.add(airportCode);
                    allAirportsInACountry.put(cityCountry, newAirportList);
                } else{
                    ArrayList<String> uniqueAirportList = new ArrayList<>();
                    uniqueAirportList.add(airportCode);
                    allAirportsInACountry.put(cityCountry,uniqueAirportList);
                }
                airportLine = airportCSV.readLine();
            }


            //reading from an inputFile
            BufferedReader inputFile = new BufferedReader(new FileReader("inputFile.rtf"));
            String startLocation = inputFile.readLine();
            String endLocation = inputFile.readLine();
            inputFile.close();

            PrintWriter outPut = new PrintWriter("output.txt");
            ArrayList<String> path = bfsTest(startLocation, endLocation);
            int flightNumber = 0;
            if (path!=null){
                for (String flight:
                     path) {
                    outPut.println(flight);
                    flightNumber++;
                }
                outPut.println("Total flight: "+ flightNumber);
            }else {
                outPut.println("No solution");
            }
            outPut.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
