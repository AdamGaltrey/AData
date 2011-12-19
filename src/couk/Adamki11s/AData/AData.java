package couk.Adamki11s.AData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bukkit.Location;


public class AData {

	private LinkedHashMap<String, Object> writeableKeys = new LinkedHashMap<String, Object>();
	private HashMap<String, Object> readableKeys = new HashMap<String, Object>();

	private File output;
	private IOStream stream;
	private int commentID = 0, newLineID = 0;

	public AData(File output) {
		this.output = output;
		this.stream = new IOStream(output);
	}
	
	public boolean doesFileExist(){
		return this.output.exists();
	}
	
	public void createFile(){
		if(!this.output.exists()){
			try {
				this.output.createNewFile();
			} catch (IOException iox) {
				iox.printStackTrace();
			}
		}
	}
	
	public Long getFileSize(){
		return this.output.length();
	}

	public void addData(String key, Object data) {
		if(key.contains("#")){
			return;
		}
		this.writeableKeys.put(key, data);
	}
	
	public Object getObject(String key){
		return this.readableKeys.get(key);
	}
	
	public String getString(String key){
		return this.readableKeys.get(key).toString();
	}
	
	public int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	public double getDouble(String key){
		return Double.parseDouble(getString(key));
	}
	
	public Location getLocation(String key){
		if(this.readableKeys.containsKey(key)){
			if(this.readableKeys.get(key) instanceof Location){
				return ((Location)this.readableKeys.get(key));
			}
		}
		return null;
	}
	
	public void addComment(String comment){
		commentID++;
		this.writeableKeys.put("#COMMENT" + commentID + "#", comment);
	}
	
	public void addNewLine(){
		newLineID++;
		this.writeableKeys.put("#NEWLINE" + newLineID + "#", "");
	}

	public void removeData(String key) {
		if (this.writeableKeys.containsKey(key)) {
			this.writeableKeys.remove(key);
		}
	}
	
	public void editData(){
		//edit data method
	}
	
	public void removeAllData(){
		this.writeableKeys.clear();
	}

	public void writeData(){
		this.stream.write(this.writeableKeys);
	}
	
	public void readData(){
		this.readableKeys = this.stream.read();
	}

}
