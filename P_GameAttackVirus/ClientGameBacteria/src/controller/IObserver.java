package controller;


public interface IObserver {
	void isSendValuesInit();
	void updateBullets(String values);
	void updateConnections(byte connections,byte limitConnections);
}
