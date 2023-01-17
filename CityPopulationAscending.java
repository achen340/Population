/**
 * 	This comparator sorts cities by their population in ascending order.
 * 	@author Alex Chen
 *  @since Janruary 17, 2023
 */ 

import java.util.Comparator;

public class CityPopulationAscending implements Comparator<City>{

    public int compare (City city1, City city2){
		//We can use the City class's compareTo method, since it 
		//is by populaiton.
        return city1.compareTo(city2);
    }
    
}
