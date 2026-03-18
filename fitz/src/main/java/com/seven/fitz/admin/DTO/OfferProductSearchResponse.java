package com.seven.fitz.admin.DTO;

public class OfferProductSearchResponse {
	
	private Long id;
    private String title;
    private String subTitle;

    public OfferProductSearchResponse(Long id,String title,String subTitle){
        this.id=id;
        this.title=title;
        this.subTitle=subTitle;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	} 

}
