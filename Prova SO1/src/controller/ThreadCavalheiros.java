package controller;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class ThreadCavalheiros extends Thread {

	private int idCavalheiro;
	private Semaphore semaforo;
	static boolean pedra = false;
	static boolean tocha = false;
	static boolean disponivel [] = new boolean [5];
	
	
	public ThreadCavalheiros(int idCavalheiro, Semaphore semaforo) {
		this.idCavalheiro = idCavalheiro+1;
		this.semaforo = semaforo;
	}
	
	
	@Override
	public void run() {
		Arrays.fill(disponivel, Boolean.TRUE);
		corredor();
		try {
			semaforo.acquire();
			portas();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}

	private void corredor() {
		System.out.println("O cavalheiro "+idCavalheiro+" começou a corrida!");
		int disTotal=0;
		int distancia = 0;
		int temtocha=0;
		int tempedra=0;
		while (disTotal<2000) {
			if (temtocha == 1) {
				if (tempedra ==1) {
					distancia = (int) (Math.random()*7)+2;
				}else {
					distancia = (int) (Math.random()*5)+2;
				}
			}else {
			if (tempedra ==1) {
				distancia = (int) (Math.random()*5)+2;
			}else {
			distancia = (int) (Math.random()*3)+2;
			}
			}
			disTotal += distancia;
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (tocha == false) {
				if (disTotal >=500) {
					temtocha=1;
					tocha = true;
					System.out.println("O cavalheiro "+idCavalheiro+" pegou a tocha e aumentou sua velocidade em 2m");
				}
			}
			if (pedra == false) {
				if (disTotal>=1500) {
					tempedra=1;
					pedra = true;
					System.out.println("O cavalheiro "+idCavalheiro+" pegou a pedra brilhante e aumentou sua velocidade em 2m");
				}
			}
			System.out.println("O cavalheiro "+idCavalheiro+" andou mais "+distancia+"m e está a "+disTotal+" do iníco da sala");
		}
		System.out.println("O cavalheiro "+idCavalheiro+" chegou nas portas e irá escolher uma para entrar");
	}
	
	private void portas() {
		int tentativa=0;
		boolean entrou = false;
		while (entrou == false) {
			tentativa = (int) (Math.random()*4)+1;
			for (int i=1;i<=4;i++) {
				if (i == tentativa) {
					if (disponivel[i] == true) {
						disponivel[i] = false;
						entrou = true;
						i=4;
					}
				}
			}
		}
		switch (tentativa) {
		case 1: System.out.println("O cavalheiro "+idCavalheiro+" entrou na porta 1 e foi devorado por um monstro! Que pena!");
			break;
		case 2: System.out.println("O cavalheiro "+idCavalheiro+" entrou na porta 2 e achou a saída! Que sorte a dele!");
			break;
		case 3: System.out.println("O cavalheiro "+idCavalheiro+" entrou na porta 3 e foi devorado por um monstro! Que pena!");
			break;
		case 4: System.out.println("O cavalheiro "+idCavalheiro+" entrou na porta 4 e foi devorado por um monstro! Que pena!");
			break;
		}
	}
}
