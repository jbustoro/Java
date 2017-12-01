package datos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Plantacion {
	private int parcela;
	private Date fechaPlan;
	private Date fechaRec;
	private String especie;
	private int cantPlant;
	private int cantRec;
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");

	
	public Plantacion(int parcela, Date fechaPlan, Date fechaRec, String especie, int cantPlant){
		this.parcela=parcela;
		this.fechaPlan=fechaPlan;
		this.fechaRec=fechaRec;
		this.especie=especie;
		this.cantPlant=cantPlant;
	}
	
	//Otra contstructora con parcela y fechaPlan para crear plantaciones para usar con el metodo equals
	public Plantacion(int parcela, Date fechaPlan){
		this.parcela=parcela;
		this.fechaPlan=fechaPlan;
	}

	public int getParcela() {
		return parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public Date getFechaPlan() {
		return fechaPlan;
	}

	public void setFechaPlan(Date fechaPlan) {
		this.fechaPlan = fechaPlan;
	}

	public Date getFechaRec() {
		return fechaRec;
	}

	public void setFechaRec(Date fechaRec) {
		sdf.format(fechaRec);
		this.fechaRec = fechaRec;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getCantPlant() {
		return cantPlant;
	}

	public void setCantPlant(int cantPlant) {
		this.cantPlant = cantPlant;
	}

	public int getCantRec() {
		return cantRec;
	}

	public void setCantRec(int cantRec) {
		this.cantRec = cantRec;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return parcela+":"+sdf.format(fechaPlan)+":"+especie;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Plantacion){
			Plantacion plant=(Plantacion) obj;
			if(parcela==plant.getParcela() && fechaPlan.compareTo(plant.getFechaPlan())==0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	
}
