package nl.hu.ipass.model;


public class ServiceProvider {
	private static SpetterendService SpetterendService = new SpetterendService();

	public static SpetterendService getSpetterendService() {
		return SpetterendService;
	}
}