package br.org.serratec.mm.exception;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 3544346303173189269L;
	public DataNotFoundException(Class<?> clazz, Long id) {
		super(clazz.getName() + " com id " + id + " não encontrado!");
	}
	public DataNotFoundException(String message) {
		super(message);
	}

}
