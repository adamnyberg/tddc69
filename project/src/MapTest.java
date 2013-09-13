public class MapTest {
    public static void main(String[] args) {
        int numberOfCountries = 4;
        
        Country[] countries = new Country[numberOfCountries];
        for (int i = 0; i < numberOfCountries; i++) {
            countries[i] = new Country();
        }

        for (Country country : countries) {
            
        }
        
        Map map = new Map(countries);
        
    }
}
