public class Region {
    private int armies = 0;

    private Region[] neighbours;

    public Region[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Region[] neighbours) {

        this.neighbours = neighbours;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public Region() {

    }
}
