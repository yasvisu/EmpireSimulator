package models;
import java.util.LinkedList;
import java.util.List;
import contracts.Unit;
import contracts.UnitTree;

public class EmpireUnitTree implements UnitTree {

	private Unit root;

	public EmpireUnitTree() {

	}

	@Override
	public Unit getRootUnit() {
		return this.root;
	}

	public void setRootUnit(Unit root) {
		this.root = root;
	}

	public Iterable<Unit> getAllUnits() {
		LinkedList<Unit> result = new LinkedList<Unit>();

		this.addDescendantsRecursively(this.root, result);

		return result;
	}

	private void addDescendantsRecursively(Unit current, List<Unit> list) {
		if (current == null) {
			return;
		}

		list.add(current);
		for (Unit u : current.getOutputUnits()) {
			addDescendantsRecursively(u, list);
		}
	}
}
