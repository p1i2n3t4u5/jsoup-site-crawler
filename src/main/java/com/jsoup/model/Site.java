package com.jsoup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ssindex", type = "sites")
public class Site implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String url;

	private String description;
	private String ogdescription;

	private List<String> keyword_tags;
	private String ogtitle;
	private String ogtype;
	private String ogurl;
	private String ogimage;

	private List<String> msapplication_tileimages;

	private String msapplication_config;
	private String msapplication_tilecolor;
	private String theme_color;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOgdescription() {
		return ogdescription;
	}

	public void setOgdescription(String ogdescription) {
		this.ogdescription = ogdescription;
	}

	public List<String> getKeyword_tags() {
		return keyword_tags;
	}

	public void setKeyword_tags(List<String> keyword_tags) {

		if (this.keyword_tags == null) {
			this.keyword_tags = new ArrayList<>();
		}
		this.keyword_tags.addAll(keyword_tags);
	}

	public void addKeyword_tags(String keyword_tag) {
		if (this.keyword_tags == null) {
			this.keyword_tags = new ArrayList<>();
		}
		keyword_tags.add(keyword_tag);
	}

	public String getOgtitle() {
		return ogtitle;
	}

	public void setOgtitle(String ogtitle) {
		this.ogtitle = ogtitle;
	}

	public String getOgtype() {
		return ogtype;
	}

	public void setOgtype(String ogtype) {
		this.ogtype = ogtype;
	}

	public String getOgurl() {
		return ogurl;
	}

	public void setOgurl(String ogurl) {
		this.ogurl = ogurl;
	}

	public String getOgimage() {
		return ogimage;
	}

	public void setOgimage(String ogimage) {
		this.ogimage = ogimage;
	}

	public List<String> getMsapplication_tileimages() {
		return msapplication_tileimages;
	}

	public void setMsapplication_tileimages(List<String> msapplication_tileimages) {
		this.msapplication_tileimages = msapplication_tileimages;
		if (this.msapplication_tileimages == null) {
			this.msapplication_tileimages = new ArrayList<>();
		}
		this.msapplication_tileimages.addAll(msapplication_tileimages);
	}

	public void addMsapplication_tileimage(String msapplication_tileimage) {
		if (this.msapplication_tileimages == null) {
			this.msapplication_tileimages = new ArrayList<>();
		}
		msapplication_tileimages.add(msapplication_tileimage);
	}

	public String getMsapplication_config() {
		return msapplication_config;
	}

	public void setMsapplication_config(String msapplication_config) {
		this.msapplication_config = msapplication_config;
	}

	public String getMsapplication_tilecolor() {
		return msapplication_tilecolor;
	}

	public void setMsapplication_tilecolor(String msapplication_tilecolor) {
		this.msapplication_tilecolor = msapplication_tilecolor;
	}

	public String getTheme_color() {
		return theme_color;
	}

	public void setTheme_color(String theme_color) {
		this.theme_color = theme_color;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", url=" + url + ", description=" + description + ", ogdescription=" + ogdescription
				+ ", keyword_tags=" + keyword_tags + ", ogtitle=" + ogtitle + ", ogtype=" + ogtype + ", ogurl=" + ogurl
				+ ", ogimage=" + ogimage + ", msapplication_tileimages=" + msapplication_tileimages
				+ ", msapplication_config=" + msapplication_config + ", msapplication_tilecolor="
				+ msapplication_tilecolor + ", theme_color=" + theme_color + "]";
	}

}
