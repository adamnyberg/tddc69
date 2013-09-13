public class Country {
    private int armies = 0;

    private Country[] neighbours;

    public Country[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Country[] neighbours) {

        this.neighbours = neighbours;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public Country() {

    }
}
