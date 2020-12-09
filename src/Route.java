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

		var ar = CityList.getInstance().List.stream().map(city -> city.index).mapToInt(i -> i).toArray();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = Util.RandomInt(i + 1);
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

	public void SetCityAtIndex(int naturalIndex, City city) {
		Cities.set(naturalIndex, city);
		CityIndexes[naturalIndex] = city.index;
	}

	public Route Mate(Route another) {
		// mating algorithm is based on human DNA.
		// DNA is approximately 50% father, 50% mother
		// so first half of route is preserved, while second half is replaced with another.
		Route result = new Route((ArrayList<City>) Cities.clone());
		if (Cities.size() != another.Cities.size()) {
			throw new AssertionError("City lengths are not equal");
		}
		int half = Cities.size() / 2;

		for (int i = half; i < Cities.size() - 1; i++) {
			result.SetCityAtIndex(i, another.Cities.get(i));
		}
		return result;
	}


	public void Mutate() {
		var index1 = Util.RandomInt(Cities.size());
		var index2 = Util.RandomInt(index1 == Cities.size() - 1 ? index1 - 1 : index1 + 1);
		ArrayList<City> citiesClone = (ArrayList<City>) Cities.clone();
		SetCityAtIndex(index1, citiesClone.get(index2));
		SetCityAtIndex(index2, citiesClone.get(index1));

	}
}
