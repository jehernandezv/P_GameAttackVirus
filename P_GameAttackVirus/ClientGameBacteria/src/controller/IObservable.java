package controller;


public interface IObservable {
	void addObserver(IObserver observer);
	void removeObserver();
	
}
