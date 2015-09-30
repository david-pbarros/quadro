package br.com.dbcorp.quadro.exceptions;

public class DuplicateKeyException extends Exception {
	private static final long serialVersionUID = -4857902372200646712L;

	public DuplicateKeyException() {
		super();
	}
	
	public DuplicateKeyException(String message) {
		super(message);
	}
}