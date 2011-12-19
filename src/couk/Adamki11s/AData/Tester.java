package couk.Adamki11s.AData;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.World;

public class Tester {

	public static void main(String[] args) {
		AData data = new AData(new File("C:" + File.separator + "Test" + File.separator + "ADATA.dat"));
		if (!data.doesFileExist()) {
			data.createFile();
			data.addData("Test", "Testing");
			data.addData("Test2", 56);
			data.addComment("Comment1");
			data.addNewLine();
			data.addComment("Comment2");
			//writes the contents of the map to the file
			data.writeData();
		} else {
			//loads the data into hashmap
			data.readData();
			System.out.println(data.getString("Test"));
			System.out.println(data.getInt("Test2"));
		}
	}

}
