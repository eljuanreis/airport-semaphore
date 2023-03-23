package view;

import java.util.concurrent.Semaphore;

import controller.AirportThread;

public class Main {
	
	static final int SIZE_PLANE = 12;
	
	public static void main(String[] args) {
		Semaphore pista = new Semaphore(2);
		Semaphore decolar = new Semaphore(1);
		
		for (int i = 0; i < Main.SIZE_PLANE; i++) {
			AirportThread airportThread = new AirportThread(pista, decolar);

			airportThread.start();
		}
	}

}
