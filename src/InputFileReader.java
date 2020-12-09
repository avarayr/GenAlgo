import java.io.Console;
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
		Util.Print("Printing a random route ... ");
		Route.FromRandom().PrintAsNames(true);
		Util.Separator();


		Scanner in = new Scanner(System.in);
		System.out.print("Enter epoch count (recommended: 10000): ");
		int epochs = in.nextInt();
		System.out.print("Enter initial population size (recommended: 100): ");
		int initialPopulation = in.nextInt();

		System.out.println();

		var biology = new Biology(initialPopulation, epochs);

		biology.StartEvolution();


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
