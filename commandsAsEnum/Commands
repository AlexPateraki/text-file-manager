package commandsAsEnum;

/**
 * enumeration class that has all the commands by symbol or string. Have also
 * methods to find the whole characteristics of a command(symbol+string)
 * 
 * Help from:
 * https://stackoverflow.com/questions/10399207/special-characters-in-an-enum
 * 
 * @author Alexandra Pateraki
 */

public enum Commands {
	goFirst("^"), goLast("$"), goUp("-"), goDown("+"), addLnAfter("a"), addLnBfr("t"), del("d"), print("l"), toggle(
			"n"), printCur("p"), quit("q"), write("w"), exit("x"), printLnNum("="), printAll("#"),filePointer("c"),printIndex("v"), 
	 printSerial("s"),printBinary("b"),def("");

	public String value;

	// constructor
	private Commands(String value) {
		this.value = value;

	}

	/**@param String cha=the inserted symbol
	 * searching command by command to find the enum only by the symbol this method
	 * is called in main !
	 * if the user gives not the right enum then Command=def, that means default
	 **/
	public Commands findTheEnum(String cha) {
		String i = null;
		for (Commands comm : Commands.values()) {
			if (cha.equals(comm.value)) {
				i = comm.name();
				return findTheCommand(i);
			}
		}
		return Commands.def;
	}

	/** find the command(symbol+string) by the name OR symbol
	 */
	public Commands findTheCommand(String name) {
		return Commands.valueOf(name);

	}

	/** to string method-useful in enumerations from coding PLH102 2019-2020
	 */
	public String toString() {
		return this.value;
	}

	/**setters and getters
	 * 
	 */
public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
