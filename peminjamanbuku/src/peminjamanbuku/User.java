package peminjamanbuku;
import java.util.ArrayList;

public class User {
	private String nama;
	private String role;
	ArrayList<Buku> buku = new ArrayList<Buku>();
	public User(String nama, String role) {
		this.nama = nama;
		this.role = role;
	}
	public String getNama() {
		return this.nama;
	}
	public String getRole() {
		return this.role;
	}
}
