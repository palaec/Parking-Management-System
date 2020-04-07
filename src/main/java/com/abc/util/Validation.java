package com.abc.util;

public class Validation {

	public static boolean isBusNumberValid(String value) {
		if (value.startsWith("BUS-") && value.length() == 11 && value.charAt(7) == '-') {
			String[] arr = value.split("-");
			long count = value.chars().filter(ch -> ch == '-').count();
			if (count == 2 && arr.length == 3 && arr[1].matches("[A-Za-z0-9]+") && arr[2].matches("[A-Za-z0-9]+")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBusCapacityValid(int value) {
		if (value >= 0 && value <= 70) {
			return true;
		}
		return false;
	}

	public static boolean isBusColorValid(String value) {
		if (value.equals("Orange") || value.equals("Green")) {
			return true;
		}
		return false;
	}

	public static boolean isBusTypeValid(String busType) {
		if (busType.equals("Regular") || busType.equals("Doubledecker")) {
			return true;
		}
		return false;
	}

}
