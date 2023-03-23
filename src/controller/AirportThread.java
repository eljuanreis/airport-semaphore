package controller;

import java.util.concurrent.Semaphore;

public class AirportThread extends Thread {

	Semaphore pista, decolar;

	public AirportThread(Semaphore pista, Semaphore decolar) {
		this.pista = pista;
		this.decolar = decolar;
	}
	
	@Override
	public void run() {
		try {
			pista.acquire();
			manobrar();
			taxiar();
			decolagem();
			afastamento();
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		} finally {
			pista.release();
		}
	}

	private void decolagem() {
		try {
			decolar.acquire();
			System.out.println((int) getId() + " está decolando pela pista " + this.getArea());
			sleep(this.genTime(1, 4));
			decolar.release();
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
	}

	private void afastamento() {
		try {
			System.out.println((int) getId() + " está se afastando...");
			sleep(this.genTime(3, 8));
			System.out.println((int) getId() + " se afastou.");
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	private void taxiar() {
		try {
			System.out.println((int) getId() + " está taxiando...");
			sleep(this.genTime(5, 10));
			System.out.println((int) getId() + " taxiou.");
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	private void manobrar() {
		try {
			System.out.println((int) getId() + " está manobrando...");
			sleep(this.genTime(3, 7));
			System.out.println((int) getId() + " manobrou.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private int genTime(int inicio, int fim) 
	{
		return (int) (Math.random() * (fim - inicio) + inicio) * 1000;
	}
	
	private String getArea()
	{
		if ((int) getId() % 2 == 0) {
			return "Norte";
		}
		
		return "Sul";
	}
}
