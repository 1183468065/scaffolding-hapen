package com.example.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EasyBigDecimal {

	/**
	 * 
	 */
	private BigDecimal value;

	public EasyBigDecimal() {
		this.setValue(BigDecimal.ZERO);
	}

	public EasyBigDecimal(BigDecimal val) {
		this.setValue(val);
	}

	public EasyBigDecimal(Float val) {
		this.setValue(BigDecimal.valueOf(val));
	}
	
	public EasyBigDecimal(Double val) {
		this.setValue(BigDecimal.valueOf(val));
	}
	
	public EasyBigDecimal(Integer val) {
		this.setValue(BigDecimal.valueOf(val));
	}
	
	public EasyBigDecimal(String val) {
		this.setValue(new BigDecimal(val));
	}
	
	public static EasyBigDecimal valueOf(Double val) {
		return new EasyBigDecimal(val);
	}
	
	public static EasyBigDecimal valueOf(Float val) {
		if (val == null) {
			val = 0f;
		}
		return new EasyBigDecimal(val);
	}
	
	public static EasyBigDecimal valueOf(Integer val) {
		return new EasyBigDecimal(val);
	}

	public EasyBigDecimal add(Float val) {
		return new EasyBigDecimal(this.getValue().add(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal subtract(Float val) {
		return new EasyBigDecimal(this.getValue().subtract(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal multiply(Float val) {
		return new EasyBigDecimal(this.getValue().multiply(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal divide(Float val) {
		return new EasyBigDecimal(this.getValue().divide(BigDecimal.valueOf(val), 5, RoundingMode.HALF_UP));
	}

	public EasyBigDecimal add(Integer val) {
		return new EasyBigDecimal(this.getValue().add(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal subtract(Integer val) {
		return new EasyBigDecimal(this.getValue().subtract(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal multiply(Integer val) {
		return new EasyBigDecimal(this.getValue().multiply(BigDecimal.valueOf(val)));
	}

	public EasyBigDecimal divide(Integer val) {
		return new EasyBigDecimal(this.getValue().divide(BigDecimal.valueOf(val), 5, RoundingMode.HALF_UP));
	}

	public EasyBigDecimal add(EasyBigDecimal val) {
		return new EasyBigDecimal(this.getValue().add(val.getValue()));
	}

	public EasyBigDecimal subtract(EasyBigDecimal val) {
		return new EasyBigDecimal(this.getValue().subtract(val.getValue()));
	}

	public EasyBigDecimal multiply(EasyBigDecimal val) {
		return new EasyBigDecimal(this.getValue().multiply(val.getValue()));
	}

	public EasyBigDecimal divide(EasyBigDecimal val) {
		return new EasyBigDecimal(this.getValue().divide(val.getValue(), 5, RoundingMode.HALF_UP));
	}
	
	public Double toDouble() {
		return toDouble(5);
	}
	
	public static Float sqrt(EasyBigDecimal val) {
		return EasyBigDecimal.valueOf(Math.sqrt(val.toDouble())).toFloat();
	}
	
	public static Float sqrt(Float val) {
		return EasyBigDecimal.valueOf(Math.sqrt(val)).toFloat();
	}
	
	public Double toDouble(int scale) {
		return this.getValue().setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}
	
	public Float toFloat() {
		return toFloat(5);
	}
	
	public Float toFloat(int scale) {
		return this.getValue().setScale(scale, RoundingMode.HALF_UP).floatValue();
	}
	
	public String toString() {
		return toString(1);
	}
	
	public String toString(int scale) {
		return String.valueOf(this.getValue().setScale(scale, RoundingMode.HALF_UP).floatValue());
	}
	
	public String toIntString() {
		return String.valueOf(this.getValue().setScale(0, RoundingMode.HALF_UP).intValue());
	}
	
	public static void main(String[] args) {
//		System.out.println(new EasyBigDecimal(0.01).toFloat());
//		System.out.println(new EasyBigDecimal(0.011).toFloat());
//		System.out.println(new EasyBigDecimal(0.015).toFloat());
//		System.out.println(new EasyBigDecimal(0.015).toFloat());
//		System.out.println(new EasyBigDecimal(0.016).toFloat());
//		System.out.println(new EasyBigDecimal(0.019).toFloat());
		
		System.out.println(new EasyBigDecimal(1.4f).toIntString());
		System.out.println(new EasyBigDecimal(1.49f).toIntString());
		System.out.println(new EasyBigDecimal(1.5f).toIntString());
		System.out.println(new EasyBigDecimal(1.9f).toIntString());
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
