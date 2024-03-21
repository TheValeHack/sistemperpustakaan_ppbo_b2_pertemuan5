package peminjamanbuku;
import java.util.ArrayList;

public class Buku {
	private String judul;
	private String penulis;
	private boolean status = true;
	private int stok = 0;
	private ArrayList<ArrayList<Object>> peminjam = new ArrayList<ArrayList<Object>>();
	private int lamaPeminjaman = 0;
	
	public Buku(String judul, String penulis, int stok) {
		this.judul = judul;
		this.penulis = penulis;
		this.stok = stok;
		
	}
	
	public String getJudul() {
		return this.judul;
	}
	public String getPenulis() {
		return this.penulis;
	}
	public boolean getStatus() {
		return this.status;
	}
	public int getStok() {
		return this.stok;
	}
	public ArrayList<ArrayList<Object>> getPeminjam(){
		return this.peminjam;
	}
	public int lamaPeminjaman() {
		return this.lamaPeminjaman;
	}
	
	public void minjam(User siPustakawan, User siPeminjam, int lama) {
		if(!this.status) {
			System.out.println("Semua buku sedang dipinjam!");
		} else {
			boolean sudahDipinjam = false;
			for(int i = 0; i < this.peminjam.size(); i++) {
				if(this.peminjam.get(i).get(1) == siPeminjam) {
					sudahDipinjam = true;
				}
			}
			if(sudahDipinjam) {
				System.out.println("Anda sudah meminjam buku ini sebelumnya!");
			} else {
				ArrayList<Object> dataPeminjam = new ArrayList<Object>();
				dataPeminjam.add(siPustakawan);
				dataPeminjam.add(siPeminjam);
				dataPeminjam.add(lama);
				
				peminjam.add(dataPeminjam);
				
				int stokSekarang = this.stok - this.peminjam.size();
				System.out.println("Buku berhasil dipinjam");
				System.out.println("Stok buku " + judul + " sekarang: "  + stokSekarang);
				
				if(this.peminjam.size() == this.stok) {
					this.status = false;
				}
			}
		}
	}
	public boolean cekBuku() {
		if(this.peminjam.size() == this.stok) {
			return false;
		} else {
			return true;
		}
	}
	public void kembalikanBuku(User siPeminjam) {
		for(int i = 0; i < this.peminjam.size(); i++) {
			if(this.peminjam.get(i).get(1) == siPeminjam) {
				this.peminjam.remove(i);
				this.status = true;
			}
		}
		System.out.println("Buku berhasil dikembalikan");
	}
	@Override
	public String toString() {
		return 	"Judul: " + this.judul + "\n" +
				"Penulis: " + this.penulis + "\n" +
				"Status: " + (this.status ? "Sedang dipinjam" : "Buku tersedia") + "\n" + 
				"Stok: " + this.stok + "\n";
	}
}
