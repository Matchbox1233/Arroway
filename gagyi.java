public class gagyi {
	public static void main (String[] args) {
		Integer x = 127;
		Integer t = 127;
		while (x<=t && x>=t && x!=t) {
			System.out.println("Ennek nem kellene lefutni.");
		}
		x = 128;
		t = 128;
		while (x<=t && x>=t && x!=t) {
			System.out.println("Ennek le kell futni.");
		}
	}
}