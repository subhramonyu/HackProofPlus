package org.hackathon.HackProofPlus;

import static  org.hackathon.HackProofPlus.Utility.execute;

import org.testng.annotations.Test;


public class SAST_Scanning {
	@Test
	public void sastScan() {
		execute("/scripts/zap.sh");
	}

}