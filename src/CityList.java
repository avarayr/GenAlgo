import java.util.ArrayList;
import java.util.HashMap;

public class CityList {
	private static CityList singleton;

	public ArrayList<City> List;
	private HashMap<String, City> cityHashMap = new HashMap<>();

	public CityList(ArrayList<City> list) {
		List = list;
		list.stream().forEach(city -> {
			cityHashMap.put(city.name, city);
		});
	}

	public static CityList getInstance() {
		if (singleton == null) {
			throw new AssertionError("You have to call init first");
		}

		return singleton;
	}


	public synchronized static void init(ArrayList<City> list) {
		if (singleton != null) {
			throw new AssertionError("You already initialized me");
		}

		singleton = new CityList(list);
	}

	public City Get(int index) {
		return List.get(index);
	}

	public City Get(String cityName) {
		return cityHashMap.get(cityName);
	}


//
//
//	public int[] GenerateRandomRoute() {
//
//	}
}
