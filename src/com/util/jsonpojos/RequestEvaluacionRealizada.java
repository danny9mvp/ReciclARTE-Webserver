package com.util.jsonpojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestEvaluacionRealizada {
	@XmlElement private String identificacion;
	@XmlElement private String nick;
	@XmlElement private String googleSheet;
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getGoogleSheet() {
		return googleSheet;
	}
	public void setGoogleSheet(String googleSheet) {
		this.googleSheet = googleSheet;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
}
