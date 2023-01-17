/**
 * 	This comparator sorts cities by their population in descending order.
 * 	@author Alex Chen
 *  @since Janruary 17, 2023
 */ 

import java.util.Comparator;

public class CityPopulationDescending implements Comparator<City>{

    public int compare (City city1, City city2){
        return city2.compareTo(city1);
    }
    
}
