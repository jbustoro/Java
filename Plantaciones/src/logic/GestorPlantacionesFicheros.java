package logic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import datos.Plantacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GestorPlantacionesFicheros implements IGestorPlantaciones {
	private ArrayList<Plantacion> lstPlantaciones=new ArrayList<Plantacion>();
	private static final String ARCHIVO = ".\\plantaciones.txt";
	private static final Date HOY=new Date(System.currentTimeMillis());
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");

	
	public GestorPlantacionesFicheros(){
		leerParcelas();
	}
	
	private void leerParcelas(){
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(ARCHIVO);
			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) {
				try {
				//trocear la linea
				String[] datos=linea.split(":");
				//pasar la parcela a int
				Integer parcela=Integer.parseInt(datos[0]);
				//pasar fechaPlan a Date
				Date fechaPlan;				
				fechaPlan = sdf.parse(datos[1]);				
				//guardar especie
				String especie=datos[2];
				//pasar fechaRec a Date
				Date fechaRec=sdf.parse(datos[3]);
				//pasar cantPlant a int
				Integer cantPlant=Integer.parseInt(datos[4]);
				//pasar cantRec a int
				Integer cantRec=Integer.parseInt(datos[5]);				
				//crear plantacion
				Plantacion plant = new Plantacion(parcela,fechaPlan,fechaRec,especie,cantPlant);
				if(cantRec!=0){
					plant.setCantRec(cantRec);
				}else{
					plant.setCantRec(0);
				}
				//añadir la plantacion al array
				addPlantacion(plant);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("No se encuentra el archivo");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	private void guardarParcelas(){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(ARCHIVO);
			bw = new BufferedWriter(fw);
			for(Plantacion plant:lstPlantaciones){
				bw.write(plant.toString()+":"+sdf.format(plant.getFechaPlan())+":"+plant.getCantPlant()+":"+plant.getCantRec()+"\r\n");
			}

			System.out.println("Guardado");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
	
	public void addPlantacion(Plantacion plant){
		lstPlantaciones.add(plant);
		System.out.println("Guardando...");
		guardarParcelas();
	}
	
	public void plantar(Plantacion plant){
		lstPlantaciones.add(plant);
		System.out.println("Guardando...");
		guardarParcelas();
	}
	
	public void recolectar(int parcela, Date fechaPlan, int cantRec){
		Plantacion p=new Plantacion(parcela,fechaPlan);
		for(Plantacion plant:lstPlantaciones){
			if(p.equals(plant)){
				plant.setCantRec(cantRec);
			}
		}
		System.out.println("Guardando...");
		guardarParcelas();
	}
	
	public Plantacion getPlantacion(int parcela, Date fechaPlan){
		Plantacion p=new Plantacion(parcela,fechaPlan);
		for(Plantacion plant:lstPlantaciones){
			if(p.equals(plant)){
				p=plant;
			}
		}
	return p;
	}
	
	public ArrayList<Plantacion> getPlantaciones(){
		return lstPlantaciones;
	}
	
	public ArrayList<Plantacion> getPlanRecoger(){
		ArrayList<Plantacion> lstNoRec=new ArrayList<Plantacion>();
		for(Plantacion p:lstPlantaciones){
			if(p.getFechaRec().before(HOY)&& p.getCantRec()==0){
				lstNoRec.add(p);
			}
		}
		return lstNoRec;
	}

	@Override
	public String[] getParcelas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEspecies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int parcela, Date fechaPlan) {
		// TODO Auto-generated method stub
		
	}
}
