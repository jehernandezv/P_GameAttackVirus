package run;

import java.io.IOException;

import controller.Controller;

public class Runner {
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
