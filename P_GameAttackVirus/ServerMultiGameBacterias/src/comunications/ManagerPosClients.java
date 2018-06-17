package comunications;

public class ManagerPosClients {
	
	public static PosFigures [] generateNewPosClient(int [] areaGame, int sizeFigure){
		PosFigures [] posFigures = new PosFigures[5];
		int subPos = areaGame[0] / posFigures.length;
		int auxSubpos = subPos;
		for (int i = 0; i < posFigures.length; i++) {
			posFigures[i] = new PosFigures(auxSubpos - (sizeFigure + 10), areaGame[1] - (sizeFigure / 2));
			auxSubpos += subPos;
		}
		return posFigures;
	}
	
}
