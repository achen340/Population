/**
 * 	This comparator sorts cities by their name in ascending order.
 * 	@author Alex Chen
 *  @since Janruary 17, 2023
 */ 

import java.util.Comparator;

public class CityNameAscending implements Comparator<City>{

    public int compare (City city1, City city2){
        return city1.getName().compareTo(city2.getName());
    }
    
}
