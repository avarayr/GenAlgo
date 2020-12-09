import java.util.Random;

public class Util {
	public static void Separator() {
		Print("-".repeat(32));
	}
	public static void Print(String text) {
		System.out.println(text);
	}

	private static Random random = new Random();
	public static int RandomInt(int bound) {
		return random.nextInt(bound);
	}
}
