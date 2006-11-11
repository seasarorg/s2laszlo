package org.seasar.laszlo.example.basic.javarpc;

public class Calculator {
	private int a;

	private int b;

	private float c;

	private float d;

	public void setC(float c) {
		this.c = c;
	}

	public void setD(float d) {
		this.d = d;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int addInt() {
		return a + b;
	}

	public float addFloat() {
		return c + d;
	}

	public int sub() {
		return a - b;
	}
}
