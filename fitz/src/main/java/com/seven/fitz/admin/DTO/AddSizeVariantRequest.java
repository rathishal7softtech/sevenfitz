package com.seven.fitz.admin.DTO;

public class AddSizeVariantRequest {
	
	private Long SizeId;
	private String value;
	
	
	
	public AddSizeVariantRequest(Long sizeId, String value) {
		super();
		SizeId = sizeId;
		this.value = value;
	}
	
	public Long getSizeId() {
		return SizeId;
	}
	public void setSizeId(Long sizeId) {
		SizeId = sizeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
