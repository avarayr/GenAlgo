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
		InitCityListSingleton();
		Util.Print("Node Count = " + CityList.getInstance().List.size());
		Util.Separator();
		CityList.getInstance().List.forEach(City::Print);
		Util.Separator();
//		Util.Print("Printing a random route ... ");
//		Route.FromRandom().PrintAsNames();
		Util.Separator();

		// change hardcoded to user input
		int initialPopulation = 5;
		int epochs = 5;

		var biology = new Biology(initialPopulation, epochs);

		biology.Print();
	}

	private void InitCityListSingleton() throws FileNotFoundException {
		var cityList = new ArrayList<City>();
		Scanner scanner = new Scanner(file);
		int index = 0;
		while (scanner.hasNextLine()) {
			City city = new City(index++, scanner.nextLine());
			cityList.add(city);
		}
		scanner.close();

		CityList.init(cityList);
	}

}
