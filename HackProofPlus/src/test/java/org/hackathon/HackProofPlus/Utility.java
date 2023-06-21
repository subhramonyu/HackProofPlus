package org.hackathon.HackProofPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utility {

	public static void execute(String path) {
		try {
			// Replace "script.sh" with the actual name of your shell script
			ProcessBuilder processBuilder = new ProcessBuilder("sh", path);

			// Set the working directory if necessary
			// processBuilder.directory(new File("/path/to/working/directory"));

			Process process = processBuilder.start();

			// Read the output from the script
			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for the script to finish execution
			int exitCode = process.waitFor();
			System.out.println("Script execution completed with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	

}
