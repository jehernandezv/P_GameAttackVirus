package comunications;


public interface IObserver {
	void sentValuesInitGameClient(int idClientRequestArea);
	void createBullet(int x,int y,int idClientshot);
	void createHeroGameServer(String nameHero,byte idClientHero);
	void updateBulletsUsers();
}
