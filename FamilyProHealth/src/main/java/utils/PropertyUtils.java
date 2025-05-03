package utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

	/**
	 * Writes or updates a key-value pair in a .properties file.
	 *
	 * @param filePath The path to the .properties file.
	 * @param key      The property key.
	 * @param value    The property value.
	 */
	public static void writeOrUpdateProperty(String propertyFileName, String key, String value) {
		Properties props = new Properties();
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File file = new File("src/test/resources/config/" + propertyFileName + ".properties");
			if (file.exists()) {
				in = new FileInputStream(file);
				props.load(in);
			}

			props.setProperty(key, value);

			out = new FileOutputStream(file);
			props.store(out, "Updated property: " + key);
			System.out.println("Property [" + key + "] updated to: " + value);

		} catch (IOException e) {
			System.err.println("Error updating property file: " + e.getMessage());
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				System.err.println("Error closing file streams: " + e.getMessage());
			}
		}
	}
}
