package couk.Adamki11s.AData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class IOStream {

	File target;

	public IOStream(File target) {
		this.target = target;
	}

	public void write(HashMap<String, Object> data) {
		try {
			FileWriter fstream = new FileWriter(this.target);
			BufferedWriter fbw = new BufferedWriter(fstream);
			boolean newLineWritten = false;
			fbw.write("Generated Using AData, Version 1.0.0. Author : Adamki11s");
			fbw.newLine();
			for (Entry<String, Object> entry : data.entrySet()) {
				newLineWritten = false;
				if (entry.getKey().contains("#COMMENT")) {
					fbw.write("#" + entry.getValue().toString().trim());
				} else if (entry.getKey().contains("#NEWLINE")) {
					fbw.newLine();
					newLineWritten = true;
				} else {
					if (entry.getValue() instanceof LocationData) {
						LocationData ld = (LocationData) entry.getValue();
						fbw.write(entry.getKey().trim() + ":" + ld.lData.getWorld().getName() + "," + ld.lData.getX() + "," + ld.lData.getY() + ","
								+ ld.lData.getZ());
					} else {
						fbw.write(entry.getKey().trim() + ":" + entry.getValue().toString().trim());
					}
				}
				if (!newLineWritten) {
					fbw.newLine();
				}
			}
			fbw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	public HashMap<String, Object> read() {
		HashMap<String, Object> tempKeys = new HashMap<String, Object>();
		try {
			FileInputStream in = new FileInputStream(this.target);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] prop = strLine.split(":");
				if (prop.length == 2) {
					String[] locs = prop[1].split(",");
					if (locs.length == 4) {
						World w = Bukkit.getServer().getWorld(locs[0]);
						double x, y, z;
						x = Double.parseDouble(locs[1]);
						y = Double.parseDouble(locs[2]);
						z = Double.parseDouble(locs[3]);
						tempKeys.put(prop[0], new LocationData(new Location(w, x, y, z)));
					} else if (strLine.contains(":") && strLine != null && !strLine.contains("#")) {
						String[] property = strLine.split(":");
						tempKeys.put(property[0], property[1]);
					}
				}
			}
			in.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
		return tempKeys;
	}

}
