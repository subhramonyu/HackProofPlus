package org.hackathon.HackProofPlus;
import static  org.hackathon.HackProofPlus.Utility.execute;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IntrusionDetection {
	
	@Test
	public void detectIntrusion() {
		execute("//scripts//testssl.sh");
	}
}
