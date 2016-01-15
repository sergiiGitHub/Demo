package main.pizza;

public enum EPizzaType{
	CHEESE,
	PAPERONY,
	GREEK;

	@Override
	public String toString() {
		switch (this) {
		case CHEESE:
			return "CHEESE :: ";
		case PAPERONY:
			return "PAPERONY :: ";
		case GREEK:
			return "GREEK :: ";
		}
		return super.toString();
	}
}



