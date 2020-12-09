import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		if (args.length < 1) {
			Log("Please specify a file name!");
			return;
		}

		var filename = args[0];

		File file = new File(filename);
		InputFileReader fileReader = new InputFileReader(file);
		try {
			fileReader.ProcessFile();
		} catch (FileNotFoundException e) {
			Log("Specified file doesn't exist!");
			return;
		}

	}

	private static void Log(String message) {
		System.out.println(message);
	}
}