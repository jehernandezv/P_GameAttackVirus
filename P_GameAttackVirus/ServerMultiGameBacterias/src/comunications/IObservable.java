package comunications;


public interface IObservable {
	void addObserver(IObserver observer);
	void removeObserver();
	
}
