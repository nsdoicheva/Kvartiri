package source;


import java.time.LocalDate;
import java.util.Map.Entry;

public class Demo {
	public static void main(String[] args) {
		User ivo = new RegisteredUser("Ivo", "ivo5","ValidenPass90", "0898565879", "Sofia", "ivo@gmail.com");
		System.out.println(ivo.getPassword());
		System.out.println("Tova e parolata sega : " + ivo.getPassword());
		System.out.println(ivo.changePassword("ValidenPass90", "Moqpass90"));
			
		
		User iva = new RegisteredUser("Iva", "iva5", "Ivapss3",  "0898565878", "Sofia", "ivo@gmail.com");
		
		ivo.sendMessage(iva, new Message("Imam interes", "Iskam da napravq ogled"));
		ivo.sendMessage(iva, new Message("Nqmam interes", "Basi skupotiqta"));
		
		System.out.println(iva.openMessage());
	
		
		iva.sendMessage(ivo, new Message("zdrrrrr", "kko pr r"));
		System.out.println(ivo.openMessage());
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		iva.deleteMessage(ivo);
		System.out.println(iva.openMessage());
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		ivo.deleteMessage(iva);
		System.out.println(ivo.openMessage());
		
		//AllAds vsichkiObqvi = AllAds.getInstance();
		
		Ad ad = new Ad("ashtaq", "sofia"," nqma", 500.56, "ivan vazov",ivo, TypeOfAd.REGULAR, 1, 15.6);
		Ad ad2 = new Ad("A kushta", "sofia"," nqma", 500.59, "lozenetz",ivo, TypeOfAd.REGULAR, 1, 30.5);
		
		Agency qvlena= new Agency("Primo +", "primo", "primoPlus", "0878372255", "Sf.Dondukov", "primo@gmail.com");
		
		User brokera = new RegisteredUser("IVan", "ivanloshiq", "Ivah56", "0878372313", "mladost", "ivan");
		
		qvlena.addBroker(brokera);
		Ad ad3 = new Ad("bkushta", "sofia"," nqma", 300.0, "studentski",brokera, TypeOfAd.REGULAR, 1, 5.0);
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		brokera.uploadAd(ad);
		brokera.uploadAd(ad2);		
		brokera.uploadAd(ad3);
		System.out.println("*********OBQVI NA BROKERA ");
		brokera.listAllMyAds();
		System.out.println("********OBQI NA AGENCIQTA");
		qvlena.listAllAds();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		Ad ad4 = new Ad("tavan", "lozenec"," nqma", 165.65, "lozenec",brokera, TypeOfAd.REGULAR, 1, 30.0);
		brokera.uploadAd(ad4);
		brokera.upgrade(ad2, Criteria.NAME, "hotel");
		//System.out.println(ad.getId() + " " + ad2.getId()); unikalni sa id-tata
		brokera.listAllMyAds();
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		brokera.deleteAd(ad3);
		brokera.listAllMyAds();
		
		qvlena.fireBroker(brokera);
		qvlena.listAllAds();
		
		System.out.println("VSICHKI OBQVI V TOA SAIT");
		AllAds.listAdsInSite();
		
		AllAds.sortAdsBy(Criteria.NEIGHBORHOOD);
		
		System.out.println("SORTIRANI OBQVI: ");
		AllAds.listAdsInSite();
		Administrator.addUser(iva);
		Administrator.listAllUsers();
		Administrator.banUser(iva);
		Administrator.listAllUsers();
	}

}
