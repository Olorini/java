package patterns.mvc;

public interface IModel {

	void initialize();
	void on();
	void off();
	void setBPM(int bmp);
	int getBPM();
	void registerObserver(IBeatObserver o);
	void removeObserver(IBeatObserver o);
	void registerObserver(IBPMObserver o);
	void removeObserver(IBPMObserver o);
}
