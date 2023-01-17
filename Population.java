/**
 *	Population - This program reads from a data file containing many cities,
 * 	and handles user queries about the data by sorting it using methods
 *  from the sort methods class.
 * 
 *  The user may enter 
 *  
 *  - 1 to find the 50 least populous cities,
 *  - 2 to find the 50 most populous cities
 *  - 3 to find the first 50 cities by name in alphabetical order
 *  - 4 to find the last 50 cities by name in alphabetical order
 *  - 5 to find the most populous cities in a state
 *  - 6 to find the most populous cities that have a name
 *  - 9 to quit.
 * 
 *  To handle the queries, population uses a modified SortMethods class
 *  to sort the data. Population also uses several comparator classes
 *  to account for the different orderings it may need to sort the list
 *  of cities.
 *
 *	@author	Alex Chen
 *	@since	Janruary, 17, 2023
 */
 
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
 
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	public Population(){
		cities = new ArrayList<City>();
	}

	public static void main(String [] args){
		Population pop = new Population();
		pop.run();
	}

	/**
	 * This method runs the program.
	 * 
	 * It first prints an introduction, and then reads the data.
	 * 
	 * It repeatedly prompts the user to enter a query until the user quits.
	 * It will then respond to that query.
	 *  
	 * Then, it print the ending when the user quits. 
	 */
	public void run(){
		System.out.print("\n\n\n");
		printIntroduction();
		readData();
		boolean quit = false;
		do{
			printMenu();
			boolean queryHandled = false;
			while(!queryHandled){
				int value = Prompt.getInt("Enter Selection");
				queryHandled = true;
				switch(value){
					case 1: case 2: case 3: case 4: case 5: case 6:
						handleQuery(value);
						break;
					case 9:
						quit = true;
						break;
					default:
						queryHandled = false;
						break;
				}
			}
		}while(!quit);
		printEnding();
		System.out.print("\n\n\n");
	}
	
	/**	Print out the ending, thanking the user */
	public void printEnding(){
		System.out.println("\nThanks for using population!");
	}
	/**	
	 *	This method handles the queries. It recieves the type of query,
	 *  then creates a comparator object, and sorts the cities using
	 *  the comparator and methods from the sortmethods class, and
	 *  then prints the results.
	 *  
	 *  @param type			The type of query. (Refer to the menu)
	 */
	public void handleQuery(int type){
		SortMethods sm = new SortMethods();
		long startMillisec = System.currentTimeMillis();
		List <City> results = new ArrayList <City>();
		int numToDisplay = 50;
		String chartTitle = new String();
		Comparator <City> cmp = null;
		switch(type){
			case 1:
				chartTitle = "Fifty least populous cities";
				cmp = new CityPopulationAscending();
				sm.selectionSort(cities, cmp);
				results = cities;
				break;
			case 2:
				chartTitle = "Fifty most populous cities";
				cmp = new CityPopulationDescending();
				sm.mergeSort(cities, cmp);
				results = cities;
				break;
			case 3:
				chartTitle = "Fifty cities sorted by name";
				cmp = new CityNameAscending();
				sm.insertionSort(cities, cmp);
				results = cities;
				break;
			case 4:
				chartTitle = "Fifty cities sorted by name descending";
				cmp = new CityNameDescending();
				sm.mergeSort(cities, cmp);
				results = cities;
				break;
			case 5:
				System.out.println();
				boolean validState = false;
				String state = new String();
				List <City> citiesInState = new ArrayList<City>();
				while(!validState){
					state = Prompt.getString("Enter state name");
					for(City c : cities){
						if(c.getState().equalsIgnoreCase(state)){
							validState = true;
							citiesInState.add(c);
						}
					}
					if(!validState){
						System.out.println("ERROR: " + state + " is not valid");
					}
				}
				startMillisec = System.currentTimeMillis();
				cmp = new CityPopulationDescending();
				sm.bubbleSort(citiesInState, cmp);
				results = citiesInState;
				chartTitle = "Fifty most populous cities in " + state;
				break;
			case 6:
				System.out.println();
				boolean validName = false;
				String name = new String();
				List <City> citiesWithName = new ArrayList<City>();
				while(!validName){
					name = Prompt.getString("Enter city name");
					for(City c : cities){
						if(c.getName().equalsIgnoreCase(name)){
							validName = true;
							citiesWithName.add(c);
						}
					}
					if(!validName){
						System.out.println("ERROR: " + name + 
							" is not valid");
					}
				}
				startMillisec = System.currentTimeMillis();
				cmp = new CityPopulationDescending();
				sm.bubbleSort(citiesWithName, cmp);
				results = citiesWithName;
				chartTitle = "City " + name + " by population";
				numToDisplay = citiesWithName.size();
				break;

		}
		long endMillisec = System.currentTimeMillis();
		printChart(chartTitle, results, numToDisplay, endMillisec -
			startMillisec);
	}

	/**	
	 *	This method prints a chart of cities, and prints an elapsed time,
	 *  thus showing the user the results of their query.
	 *  
	 *  @param  title			A string representing the title of the chart
	 *  @param	cityList		A sorted list of cities
	 * 	@param  numToDisplay	The number of cities in the cityList to display.
	 * 	@param	timeTaken		The amount of time the sort taken.
	 */
	public void printChart(String title, List <City> cityList, 
		int numToDisplay, long timeTaken){
		System.out.println();
		System.out.println(title);
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", 
			"City", "Type", "Population");
		for(int i = 0; i < numToDisplay; i++){
			System.out.printf("%3d%-2s", (i+1), ": ");
			System.out.print(cityList.get(i));
			System.out.println();
		}
		System.out.println();
		System.out.println("Elapsed Time " + timeTaken + " milliseconds");
		System.out.println();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 *	This method reads data from the data file
	 *	file, and populates the cities List.
	 */
	public void readData(){
		Scanner scan = FileUtils.openToRead(DATA_FILE);
		scan.useDelimiter("[\t\n]");
		while(scan.hasNext()){
			String state = scan.next();
			String name = scan.next();
			String desig = scan.next();
			int pop = scan.nextInt();
			cities.add(new City(state, name, desig, pop));
		}
		System.out.println(cities.size() + " cities in database\n");
		scan.close();
	}
}
