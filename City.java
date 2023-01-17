/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Alex Chen
 *	@since	Janruary 10th 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String state; //The state the city is located in
	private String name; //The name of the city 
	private String designation; //The designation of the city
	private int population; //The city's population
	
	
	// constructor
	public City(String state, String name, String designation, int population){
		
		this.state = state;
		this.name = name;
		this.designation = designation;
		this.population = population;
	}
	
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other){
		if(this.population != other.population){
			return (this.population - other.population);
		}else if(!this.state.equals(other.state)){
			return (this.state.compareTo(other.state));
		}else{
			return (this.name.compareTo(other.name));
		}
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other){
		return (this.state.equals(other.state) && this.name.equals(other.name));
	}
	/**	Accessor methods */
	public String getName(){
		return name;
	}	
	
	public String getState(){
		return state;
	}
	
	public String getDesignation(){
		return designation;
	}
	
	public int getPopulation(){
		return population;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
