package viewer;

import java.util.concurrent.Semaphore;

import controller.ThreadCavalheiros;

public class Principal {
	
public static void main(String[] args) {
	int limit =1;
	Semaphore semaforo = new Semaphore(limit);
	for (int i =0;i<4;i++) {
		Thread cavalheiro = new ThreadCavalheiros(i, semaforo);
		cavalheiro.start();
	}
}
}