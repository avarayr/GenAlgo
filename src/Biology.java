import java.util.ArrayList;
import java.util.Comparator;

public class Biology {
	public ArrayList<Route> pool = new ArrayList<>();
	public int epochs;
	public int mutationCount = 0;

	public Biology(int initialRouteCount, int epochs) {
		for (int i = 0; i < initialRouteCount; i++) {
			pool.add(Route.FromRandom());
		}
		this.epochs = epochs;
	}

	public Route GetLowestCostRoute() {
		return pool.stream().min(Comparator.comparingInt(Route::GetCost)).get();
	}

	public int GetLowestCost() {
		return pool.stream().min(Comparator.comparingInt(Route::GetCost)).get().GetCost();
	}

	public int GetAverageCost() {
		return pool.stream().map(Route::GetCost).mapToInt(c -> c).reduce(0, (a, b) -> (a + b) / 2);
	}

	public Route GetRandomAlphaRoute() {
		return pool.get(Util.RandomInt(pool.size() / 2));
	}

	public Route GetRandomRoute() {
		return pool.get(Util.RandomInt(pool.size()));
	}

	public void StartEvolution() {
		for (int epoch = 0; epoch < epochs; epoch++) {
			SortPool();

			Route alphaOffspring = GetRandomAlphaRoute().Mate(GetRandomRoute());
			var mutationOccurred = MutateOffspring(alphaOffspring);
			KillOneWeakling();
			InsertAChild(alphaOffspring);


//			PrintAllRoutes();
			PrintMetrics(epoch, mutationOccurred);

		}
		Util.Separator();
		Util.Print(String.format("Evolution is finished | Total mutations: %d | Best route cost: %d", mutationCount, GetLowestCost()));
		Util.Print("Best route -> ");
		GetLowestCostRoute().PrintAsNames(true);
	}

	private boolean MutateOffspring(Route route) {
		if (Util.RandomInt(10) > 0) {
			route.Mutate();
			mutationCount++;
			return true;
		}
		return false;
	}

	public void InsertAChild(Route route) {
		pool.add(route);
	}

	public void KillOneWeakling() {
		// this assumes pool is sorted
		pool.remove(pool.size() - 1);
	}

	public void SortPool() {
		pool.sort(Comparator.comparingInt(Route::GetCost));
	}

	public void PrintMetrics(int epoch, boolean mutationOccurred) {
		Util.Print(String.format("Epoch #%s | Average fitness: %d | Fittest route cost: %d %s", epoch, GetAverageCost(), GetLowestCost(), mutationOccurred ? "| Mutation occurred" : ""));
	}

	public void PrintAllRoutes() {
		pool.forEach(route -> route.PrintAsNames(true));
	}
}
