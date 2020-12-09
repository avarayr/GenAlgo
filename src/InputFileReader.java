import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileReader {
	File file;

	public InputFileReader(File file) {
		this.file = file;
	}

	public void ProcessFile() throws FileNotFoundException {
		var cityList = new ArrayList<City>();
		Scanner scanner = new Scanner(file);
		int index = 0;
		while (scanner.hasNextLine()) {
			City city = new City(index++, scanner.nextLine());
			cityList.add(city);
		}
		scanner.close();
		System.out.println("Node Count = " + cityList.size());
		cityList.forEach(City::Print);

		CityList.init(cityList);
	}
}
