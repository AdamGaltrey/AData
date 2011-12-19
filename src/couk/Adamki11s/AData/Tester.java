package couk.Adamki11s.AData;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.World;

public class Tester {

	public static void main(String[] args) {
		AData data = new AData(new File("C:" + File.separator + "Test" + File.separator + "ADATA.dat"));
		if (!data.doesFileExist()) {
			data.createFile();
			data.addData("Test", "LOLOLO");
			data.addData("Test2", 56);
			data.addComment("FAGGttOT");
			data.addNewLine();
			data.addComment("LOOOL");
			data.writeData();
		} else {
			data.readData();
			System.out.println(data.getString("Test"));
			System.out.println(data.getInt("Test2"));
		}
	}

}
