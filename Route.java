import java.util.Objects;

public class Route {
    private String airlineCode;
    private String desAirportCode;
    private int stops;

    public Route(String airlineCode, String desAirportCode, int stops) {
        this.airlineCode = airlineCode;
        this.desAirportCode = desAirportCode;
        this.stops = stops;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }


    public String getDesAirportCode() {
        return desAirportCode;
    }

    public void setDesAirportCode(String desAirportCode) {
        this.desAirportCode = desAirportCode;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(stops, route.stops) && Objects.equals(airlineCode, route.airlineCode) && Objects.equals(desAirportCode, route.desAirportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineCode, desAirportCode, stops);
    }
}
