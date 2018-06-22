package comunications;

public class ManagerPosClients {
	
	public static PosFigures [] generateNewPosClient(int width, int heigth,int sizeFigure){
		PosFigures [] posFigures = new PosFigures[5];
		int subPos = width / posFigures.length;
		int auxSubpos = subPos;
		for (int i = 0; i < posFigures.length; i++) {
			posFigures[i] = new PosFigures(auxSubpos - (sizeFigure + 10) - 50, heigth);
			auxSubpos += subPos;
		}
		return posFigures;
	}
	
}
