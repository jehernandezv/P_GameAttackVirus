package comunications;

public class ManagerPosClients {
	
	public static PosFigures [] generateNewPosClient(int width, int heigth,int sizeFigure,byte numClients){
		PosFigures [] posFigures = new PosFigures[numClients];
		int subPos = width / posFigures.length;
		int auxSubpos = subPos;
		for (int i = 0; i < posFigures.length; i++) {
			posFigures[i] = new PosFigures(auxSubpos - (sizeFigure + 10) - 100, heigth,(byte) (i + 1));
			auxSubpos += subPos;
		}
		return posFigures;
	}
	
}
