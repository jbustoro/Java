package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datos.GestorBDMySQL;
import datos.GestorBDSQLite;
import datos.Plantacion;

public class GestorPlantacionesBBDD implements IGestorPlantaciones {

	private GestorBDMySQL gmsql = new GestorBDMySQL();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private ArrayList<Plantacion> lstPlantaciones=new ArrayList<Plantacion>();
	
	@Override
	public void plantar(Plantacion plant) {
		int parcela = plant.getParcela();
		String fechaPlan = sdf.format(plant.getFechaPlan());
		String fechaRec = sdf.format(plant.getFechaRec());
		String especie = plant.getEspecie();
		int cantPlant = plant.getCantPlant();
		int cantRec = plant.getCantRec();
		
		String sql = "INSERT INTO PLANTACIONES VALUES("+parcela+",'"+fechaPlan+"','"+fechaRec+"','"+especie+"',"+cantPlant+","+cantRec+");";
		
		gmsql.updateSQL(sql);
	}

	@Override
	public void recolectar(int parcela, Date fechaPlan, int cantRec) {
		String fechaPlanString = sdf.format(fechaPlan);
		String sql = "UPDATE PLANTACIONES SET CANT_REC = "+cantRec+" WHERE PARCELA = "+parcela+" AND FECHA_PLAN ='"+fechaPlanString+"';";
		gmsql.updateSQL(sql);
	}
	
	@Override
	public Plantacion getPlantacion(int parcela, Date fechaPlan) {
		Plantacion p = new Plantacion(parcela, fechaPlan);
		for(Plantacion pla:lstPlantaciones){
			System.out.println("Entra");
			if(p.equals(pla)){
				return pla;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Plantacion> getPlantaciones() {
		ArrayList<Plantacion> lstPlantas = new ArrayList<Plantacion>();
		String sql = "SELECT * FROM PLANTACIONES";
		//Pedir a la bd todas las plantaciones
		try {
			ResultSet rs = gmsql.executeSQL(sql);
			while(rs.next()){
				int parcela = rs.getInt("PARCELA");
				Date fechaPlan = sdf.parse(rs.getString("FECHA_PLAN"));				
				String especie=rs.getString("ESPECIE");
				Date fechaRec=sdf.parse(rs.getString("FECHA_REC"));
				Integer cantPlant=rs.getInt("CANT_PLANT");
				Integer cantRec = rs.getInt("CANT_REC");
				Plantacion plant = new Plantacion(parcela,fechaPlan,fechaRec,especie,cantPlant);
				plant.setCantRec(cantRec);
				lstPlantas.add(plant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return lstPlantas;
	}

	@Override
	public ArrayList<Plantacion> getPlanRecoger() {
		ArrayList<Plantacion> plantRecoger = new ArrayList<Plantacion>();
		Date hoy = new Date();
		for(Plantacion p: lstPlantaciones){
			if(p.getCantRec()==0 && p.getFechaRec().after(hoy)){
				plantRecoger.add(p);
			}
		}
		return plantRecoger;
	}

	@Override
	public String[] getParcelas() {
		ArrayList<String> lstParcelas = new ArrayList<String>();
		String sql = "SELECT PARCELA FROM PLANTACIONES";
		//Pedir a la bd todas las plantaciones
		try {
			ResultSet rs = gmsql.executeSQL(sql);
			while(rs.next()){
				lstParcelas.add(""+rs.getInt("PARCELA")) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] parcelas = new String[lstParcelas.size()];
		for(int i=0; i<lstParcelas.size(); i++){
			parcelas[i]=lstParcelas.get(i);
		}
		return parcelas;
	}

	@Override
	public String[] getEspecies() {
		ArrayList<String> lstEspecies = new ArrayList<String>();
		String sql = "SELECT ESPECIE FROM PLANTACIONES";
		//Pedir a la bd todas las plantaciones
		try {
			ResultSet rs = gmsql.executeSQL(sql);
			while(rs.next()){
				lstEspecies.add(rs.getString("ESPECIE")) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] especies = new String[lstEspecies.size()];
		for(int i=0; i<lstEspecies.size(); i++){
			especies[i]=lstEspecies.get(i);
		}
		return especies;
	}

	@Override
	public void eliminar(int parcela, Date fechaPlan) {
		String fechaPlanString = sdf.format(fechaPlan);
		String sql = "DELETE FROM PLANTACIONES WHERE PARCELA = "+parcela+";";// AND FECHA_PLAN = '"+fechaPlan+"';";
		gmsql.updateSQL(sql);
	}

}
