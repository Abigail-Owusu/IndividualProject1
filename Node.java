import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Node {
    private Node parent;
    private String airportCode;
    private int stops;
    private ArrayList<Route> successors;
    private String airLineCode;
    public Node(Node parent, String airportCode, int stops, ArrayList<Route> successors, String airLineCode) {
        this.parent = parent;
        this.airportCode = airportCode;
        this.stops = stops;
        this.successors = successors;
        this.airLineCode =airLineCode;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public ArrayList<Route> getSuccessors() {
        return successors;
    }

    public void setSuccessors(ArrayList<Route> successors) {
        this.successors = successors;
    }

    public String getAirLineCode() {
        return airLineCode;
    }

    public void setAirLineCode(String airLineCode) {
        this.airLineCode = airLineCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return stops == node.stops && Objects.equals(parent, node.parent) && Objects.equals(airportCode, node.airportCode) && Objects.equals(successors, node.successors) && Objects.equals(airLineCode, node.airLineCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, airportCode, stops, successors, airLineCode);
    }

    public ArrayList<String> solutionPath(){
        ArrayList<String> airportCodes = new ArrayList<>();
        ArrayList<String> solution = new ArrayList<>();
        ArrayList<String> airlineCodes = new ArrayList<>();
        ArrayList<Integer> stops = new ArrayList<>();
        Node thisNode = this;

        while(thisNode != null){
            airlineCodes.add(thisNode.getAirLineCode());
            airportCodes.add(thisNode.getAirportCode());
            stops.add(thisNode.getStops());
            thisNode = thisNode.parent;
        }
        Collections.reverse(airlineCodes);
        Collections.reverse((airportCodes));

        for (int i = 0;i < airlineCodes.size()-1;i++){
            String result = airlineCodes.get(i+1) + " from " + airportCodes.get(i) + " to " +
                    airportCodes.get(i+1) + " " + stops.get(i) + " stops.";
            solution.add(result);
        }

        return solution;

    }
}
