import java.util.ArrayList;

public class Biology {
	public ArrayList<Route> pool = new ArrayList<>();
	public int epochs;
	public Biology(int initialRouteCount, int epochs) {
		for (int i = 0; i < initialRouteCount; i++) {
			pool.add(Route.FromRandom());
		}
		this.epochs = epochs;
	}

	public void Print() {
		pool.forEach(route -> route.PrintAsNames(true));
	}
}
