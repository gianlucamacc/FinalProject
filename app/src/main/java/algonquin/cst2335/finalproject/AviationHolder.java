package algonquin.cst2335.finalproject;
public class AviationHolder {
    String airportName;
    String terminal;
    String gate;
    String delay;


    public AviationHolder(String airportName, String terminal, String gate, String delay){
        this.airportName = airportName;
        this.terminal = terminal;
        this.gate = gate;
        this.delay = delay;

    }
    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}
