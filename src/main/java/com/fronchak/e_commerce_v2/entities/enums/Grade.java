package com.fronchak.e_commerce_v2.entities.enums;

import com.fronchak.e_commerce_v2.exceptions.EnumException;

public enum Grade {

	HORRIBLE(1),
	BAD(2),
	AVERAGE(3),
	GOOD(4),
	EXCELENT(5);
	
	private final Integer avaliation;
	
	private Grade(Integer avaliation) {
		this.avaliation = avaliation;
	}
	
	public Integer getAvaliation() {
		return avaliation;
	}
	
	public static Grade toEnum(Integer avaliation) {
		if(avaliation == null) throw new EnumException("Avaliation cannot be null");
		for(Grade grade : Grade.values()) {
			if(grade.avaliation.equals(avaliation)) return grade;
		}
		throw new EnumException("There is no Grade with that avaliation: " + avaliation);
	}
}
