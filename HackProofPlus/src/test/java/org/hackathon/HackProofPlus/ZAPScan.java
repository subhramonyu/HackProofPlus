package org.hackathon.HackProofPlus;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ApiResponseList;
import org.zaproxy.clientapi.core.ClientApi;



public class ZAPScan {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// ZAP API endpoint (make sure ZAP is running)
		String zapApi = "http://localhost:8080";

		// Define target URL
		String targetUrl = "https://portal.live-asset.com/login";

		try {
			// Create ZAP client API
			ClientApi clientApi = new ClientApi("http://localhost:8080", 8080);
			
			
			

			// Start ZAP session
			ApiResponse response = clientApi.core.newSession("", "");

			// Get the session ID from the response
			String sessionId = ((ApiResponseElement) response).getValue();

			// Spider the target URL
			System.out.println("Spidering the target URL...");
			clientApi.spider.scan(sessionId, targetUrl, null, null, null);

			// Wait until the spider has completed
			while (true) {
				Thread.sleep(1000);
				response = clientApi.spider.status(sessionId);
				String status = ((ApiResponseElement) response).getValue();
				System.out.println("Spider progress: " + status + "%");
				if (status.equals("100")) {
					break;
				}
			}
			System.out.println("Spider completed!");

			// Perform active scan
			System.out.println("Starting active scan...");
			clientApi.ascan.scan(sessionId, targetUrl, "True", "", "", "", "");

			// Wait until the active scan has completed
			while (true) {
				Thread.sleep(1000);
				response = clientApi.ascan.status(sessionId);
				String status = ((ApiResponseElement) response).getValue();
				System.out.println("Active scan progress: " + status + "%");
				if (status.equals("100")) {
					break;
				}
			}
			System.out.println("Active scan completed!");

			// Get the alerts raised by the scan
			response = clientApi.core.alerts(sessionId, "", "");
			ApiResponseList alerts = (ApiResponseList) response;
			if (alerts.getItems().size() > 0) {
				System.out.println("Alerts:");
				for (ApiResponse item : alerts.getItems()) {
					String alert = ((ApiResponseElement) item).getValue();
					System.out.println(" - " + alert);
				}
			} else {
				System.out.println("No alerts found.");
			}

			// Save the ZAP report
			String reportPath = "zap_report.html";
			byte[] re = clientApi.core.htmlreport(sessionId);
			//String reportContent = ((ApiResponseElement) response).getValue();
			Files.write(Paths.get(reportPath), re);
			System.out.println("Report saved to " + reportPath);

			// Shutdown ZAP session
			clientApi.core.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
