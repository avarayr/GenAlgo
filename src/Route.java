import java.util.ArrayList;
import java.util.Random;

public class Route {
	int[] CityIndexes;
	ArrayList<City> Cities = new ArrayList<>();

	public Route(int[] cityIndexes) {
		this.CityIndexes = cityIndexes;
		for (int cityIndex : cityIndexes) {
			Cities.add(CityList.getInstance().Get(cityIndex));
		}
	}

	public Route(ArrayList<City> cities) {
		this.Cities = cities;
		this.CityIndexes = new int[cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			CityIndexes[i] = cities.get(i).index;
		}
	}

	public static Route FromRandom() {

		Random rnd = new Random();
		var ar = CityList.getInstance().List.stream().map(city -> city.index).mapToInt(i -> i).toArray();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return new Route(ar);
	}

	public int GetCost() {
		int totalCost = 0;
		for (int i = 0; i < CityIndexes.length; i++) {
			City startCity = CityList.getInstance().Get(CityIndexes[i]);
			City endCity = CityList.getInstance().Get(CityIndexes[i == CityIndexes.length - 1 ? 0 : i + 1]);

			int cost = startCity.GetCostTo(endCity);
			totalCost += cost;
		}
		return totalCost;
	}

	void PrintAsIndexes(boolean includeTotalCost) {
		System.out.print("[");
		Cities.stream()
				.map(city -> " " + city.index + ", ")
				.forEach(System.out::print);
		System.out.print("]" + String.format(" %s", includeTotalCost ? "(Cost: " + GetCost() + ")" : ""));
		System.out.println();
	}

	void PrintAsNames(boolean includeTotalCost) {

		System.out.print("[");
		Cities.stream()
				.map(city -> " " + city.name + ", ")
				.forEach(System.out::print);
		System.out.print("]" + String.format(" %s", includeTotalCost ? "(Cost: " + GetCost() + ")" : ""));
		System.out.println();
	}

	void Mate(Route another) {
		// mating algorithm is based on human DNA.
		// DNA is approximately 50% father, 50% mother
		// so first half of route is preserved, while second half is replaced with another.
		Route result = new Route(Cities);
		
	}


}
