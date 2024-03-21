package peminjamanbuku;
import java.util.ArrayList;

public class Perpustakaan {
	private ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
	
	public ArrayList<Buku> getDaftarBuku(){
		return this.daftarBuku;
	}
	
	public void tambahBuku(Buku siBuku) {
		this.daftarBuku.add(siBuku);
	}
	
	public void tampilkanDaftarBuku() {
		if(this.daftarBuku.size() > 0) {
			for(int i=0; i < this.daftarBuku.size(); i++) {
				int stokTersedia = this.daftarBuku.get(i).getStok() - this.daftarBuku.get(i).getPeminjam().size();
				System.out.println(this.daftarBuku.get(i).getJudul() + " (Stok tersedia: " + stokTersedia + ")");
			}
		} else {
			System.out.println("-");
		}
	}
}
