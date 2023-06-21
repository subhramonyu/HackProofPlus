package org.hackathon.HackProofPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utility {

	public static void execute(String path) {
		String os = System.getProperty("os.name").toLowerCase();
		String shellCommand;
		// Replace "script.sh" with the actual name of your shell script
		String currentWorkingDir = System.getProperty("user.dir");
		if (os.contains("win")) {
			// Windows
			shellCommand = "cmd /c " + currentWorkingDir + path;
		} else {
			shellCommand = "sh -c " + currentWorkingDir + path;
		}
		try {

			ProcessBuilder processBuilder = new ProcessBuilder(shellCommand.split(" "));

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
