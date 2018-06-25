package model;

import java.util.ArrayList;
import java.util.Iterator;
import comunications.IObservable;
import comunications.IObserver;

	
	public class GameServer extends Thread implements IObservable{
		private ArrayList<Hero> listHero;
		private ArrayList<Enemy> listEnemys;
		private IObserver iObserver;
		private int [] areaGame;
		private static final byte STEP_FIGURE = 5;
		private static final int VALUE_UPDATE = 30;
		
		public GameServer(int [] areaGame,IObserver iObserver) {
			this.iObserver = iObserver;
			this.listHero = new ArrayList<Hero>();
			this.listEnemys = new ArrayList<Enemy>();
			this.areaGame = areaGame;
		}
		
		public void run() {
			//Mover disparos
			while(true){
			try {
				Thread.sleep(VALUE_UPDATE);
					updateBullets();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		public void updateBullets(){
			for (Iterator<?> iterator = listHero.iterator(); iterator.hasNext();) {
				Hero hero = (Hero) iterator.next();
				if(hero.getListBullet().size() > 0){
					for (Iterator<?> iteratorBullet = hero.getListBullet().iterator(); iteratorBullet.hasNext();) {
						Bullet bullet = (Bullet) iteratorBullet.next();
						if(bullet.getX() > areaGame[0] || bullet.getY() < -100){
							iteratorBullet.remove();
						}else{
							bullet.move(STEP_FIGURE);
						}
					}
					if(hero.getListBullet().size() > 0){
						iObserver.updateBulletsUsers();
					}
				}
			}
			
		}
		
		public void updateEnemys(){
			for (Iterator<?> iterator = listEnemys.iterator(); iterator.hasNext();) {
				Enemy enemy = (Enemy) iterator.next();
				if(listEnemys.size() > 0 && enemy.getPosY() < areaGame[1]){
					
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

		public ArrayList<Enemy> getListEnemys() {
			return listEnemys;
		}

		public void setListEnemys(ArrayList<Enemy> listEnemys) {
			this.listEnemys = listEnemys;
		}
	}
