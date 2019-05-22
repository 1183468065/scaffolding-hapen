package com.example.enums;

public enum WorkdayEnum implements EnumType {
	MONDAY(1010, "周一"), TUESDAY(1020, "周二"), WEDNESDAY(1030, "周三"), THURSDAY(1040, "周四"), FRIDAY(1050,
			"周五"), SATURDAY(1060, "周六"), WEEKDAY(1070, "周日");

	private final int code;
	private final String text;

	private WorkdayEnum(int code, String text) {
		this.code = code;
		this.text = text;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String text() {
		return text;
	}

	public static WorkdayEnum codeOf(int code) {

		for (WorkdayEnum value : values()) {
			if (value.code == code) {
				return value;
			}
		}

		throw new IllegalArgumentException("Invalid Status code: " + code);
	}
}
