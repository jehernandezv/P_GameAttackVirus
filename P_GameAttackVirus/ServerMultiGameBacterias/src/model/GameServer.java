package model;

import java.util.ArrayList;
import java.util.Iterator;
import comunications.IObservable;
import comunications.IObserver;

public class GameServer extends Thread implements IObservable{
	private ArrayList<Hero> listHero;
	private IObserver iObserver;
	private int [] areaGame;
	
	public GameServer(int [] areaGame,IObserver iObserver) {
		this.iObserver = iObserver;
		this.listHero = new ArrayList<Hero>();
		this.areaGame = areaGame;
	}
	
	public void run() {
		//Mover disparos
		while(true){
		try {
			Thread.sleep(45);
			for (Iterator<?> iterator = listHero.iterator(); iterator.hasNext();) {
				Hero hero = (Hero) iterator.next();
				if(hero.getListBullet().size() > 0){
					for (Iterator<?> iteratorBullet = hero.getListBullet().iterator(); iteratorBullet.hasNext();) {
						Bullet bullet = (Bullet) iteratorBullet.next();
						 	bullet.move(5);
						iObserver.updateBulletsUsers();
					}
				}
			}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	public ArrayList<Hero> getListHero() {
		return listHero;
	}

	public void setListHero(ArrayList<Hero> listHero) {
		this.listHero = listHero;
	}
	
	public void addHero(Hero hero){
		this.listHero.add(hero);
	}

	@Override
	public void addObserver(IObserver observer) {
		
	}

	@Override
	public void removeObserver() {
		
	}
}