public class User {
	private final String name;
	private final int hashCode;

	public User(String name) {
		this.name = name;
		int calc = 0;
		for (int i = 0; i < name.length(); ++i) {
			calc += (name.charAt(i) - 'a');
			calc %= 29;
		}
		hashCode = calc;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			if (other.hashCode != this.hashCode) {
				return false;
			}
			if (other.name.length() != this.name.length()) {
				return false;
			}

			for (int i = 0; i < name.length(); ++i) {
				if (name.charAt(i) != other.name.charAt(i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "@" + hashCode + ":" + name;
	}

}
