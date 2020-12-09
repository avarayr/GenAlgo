import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class City {
	String name;
	ArrayList<Integer> costs;
	int index;

	public City(int index, String name, ArrayList<Integer> costs) {
		this.name = name;
		this.costs = costs;
		this.index = index;
	}

	public City(int index, String unparsedLine) {
		this.index = index;
		var splitValues = unparsedLine.split(",");
		name = splitValues[0];
		costs = new ArrayList<>();


		Arrays.stream(splitValues, 1, splitValues.length)
				.filter(Objects::nonNull)
				.filter(s -> !s.isEmpty())
				.mapToInt(value -> Integer.parseInt(value.replaceAll("\\,|\\s*", "")))
				.forEach(costInt -> costs.add(costInt));
	}


	public void Print() {
		System.out.print(String.format("(%d) %s = ", index, name));
		for (var cost : costs) {
			System.out.print(" " + cost);
		}
		System.out.println();
	}

	public int GetCostTo(int cityIndex) {
		return costs.get(cityIndex);
	}

	public int GetCostTo(City city) {
		return costs.get(city.index);
	}

	public static int GetCost(City from, City to) {
		return from.GetCostTo(to);
	}

}
