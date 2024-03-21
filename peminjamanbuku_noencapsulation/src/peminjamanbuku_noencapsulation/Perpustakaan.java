package peminjamanbuku_noencapsulation;
import java.util.ArrayList;

public class Perpustakaan {
	ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
	public void tambahBuku(Buku siBuku) {
		this.daftarBuku.add(siBuku);
	}
	
	public void tampilkanDaftarBuku() {
		if(this.daftarBuku.size() > 0) {
			for(int i=0; i < this.daftarBuku.size(); i++) {
				int stokTersedia = this.daftarBuku.get(i).stok - this.daftarBuku.get(i).peminjam.size();
				System.out.println(this.daftarBuku.get(i).judul + " (Stok tersedia: " + stokTersedia + ")");
			}
		} else {
			System.out.println("-");
		}
	}
}
