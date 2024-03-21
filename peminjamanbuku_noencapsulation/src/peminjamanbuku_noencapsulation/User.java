package peminjamanbuku_noencapsulation;
import java.util.ArrayList;

public class User {
	String nama;
	String role;
	ArrayList<Buku> buku = new ArrayList<Buku>();
	public User(String nama, String role) {
		this.nama = nama;
		this.role = role;
	}
}
