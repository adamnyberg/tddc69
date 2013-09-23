public class MapTest {
    public static void main(String[] args) {
        int numberOfRegions = 4;
        
        Region[] regions = new Region[numberOfRegions];
        for (int i = 0; i < numberOfRegions; i++) {
            regions[i] = new Region();
        }

        for (Region region : regions) {
            
        }
        
        Map map = new Map(regions);
        
    }
}
