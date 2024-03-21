package peminjamanbuku_noencapsulation;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Perpustakaan perpustakaan = new Perpustakaan();
	public static ArrayList<User> daftarUser = new ArrayList<User>();
	public static boolean isLogin = false;
	public static User currentUser;
	public static boolean programJalan = true;
	
	public static void tambahUser() {
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.print("Masukkan nama: ");
		String nama = input.nextLine();
		String role;
		while(true) {
			System.out.print("Masukkan role [pustakawan/pengunjung]: ");
			role = input.nextLine();
			if((role.equals("pustakawan")) || (role.equals("pengunjung"))) {
				User userBaru = new User(nama, role);
				daftarUser.add(userBaru);
				break;
			} else {
				System.out.println(role);
				System.out.println("Role tidak valid!");
			}
		}
		System.out.println("\n======================================================");
	}
	public static boolean cekLogin() {
		if(isLogin) {
			return true;
		} else {
			return false;
		}
	}
	public static void login() {
		System.out.println();
		int banyakUser = daftarUser.size();
		if(banyakUser > 0) {
			Scanner input = new Scanner(System.in);
			System.out.print("Masukkan nomor user [1" + (banyakUser == 1 ? "" : "-"+banyakUser) + "] : ");
			int pilihan = input.nextInt();
			if((pilihan > banyakUser) || (pilihan < 1)) {
				System.out.println("Nomor user tidak ada");
			} else {
				currentUser = daftarUser.get(pilihan-1);
				isLogin = true;
			}
		} else {
			System.out.println("Tidak ada User!");
		}
		System.out.println("\n======================================================");
	}
	public static void logout() {
		if(isLogin) {
			currentUser = null;
			isLogin = false;
			System.out.println("Berhasil logout");
		} else {
			System.out.println("Anda belum login!");
		}
	}
	public static void menuAwal() {
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.println("Daftar User: ");
		if(daftarUser.isEmpty()) {
			System.out.println("-");
		} else {
			for(int i = 0; i < daftarUser.size(); i++) {
				System.out.println((i+1) + ". "+ daftarUser.get(i).nama + " [" + daftarUser.get(i).role + "]");
			}
		}
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println("1. Login");
		System.out.println("2. Daftar");
		System.out.println("3. Keluar");
		System.out.println();
		System.out.print("Masukkan pilihan [1-3] : ");
		int pilihan = input.nextInt();
		if(pilihan == 1) {
			System.out.println("\n======================================================");
			login();
		} else if(pilihan == 2) {
			System.out.println("\n======================================================");
			tambahUser();
		}  else if(pilihan == 3) {
			System.out.println("Program selesai");
			System.out.println("\n======================================================");
			programJalan = false;
		} else {
			System.out.println("Pilihan tidak valid");
			System.out.println("\n======================================================");
		}
	}
	public static void tampilkanDaftarBuku() {
		System.out.println("Daftar buku: ");
		if(perpustakaan.daftarBuku.isEmpty()) {
			System.out.println("-");
		} else {
			for(int i = 0; i < perpustakaan.daftarBuku.size(); i++) {
				System.out.println((i+1) + ". "+ perpustakaan.daftarBuku.get(i).judul);
			}
		}
	}
	public static void aksiMenu(String role, int pilihan) {
		if (role == "pengunjung") {
			Scanner input = new Scanner(System.in);
			if(pilihan == 1) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					tampilkanDaftarBuku();
					System.out.println();				
					System.out.print("Masukkkan pilihan buku [1" + (banyakBuku == 1 ? "" : "-"+banyakBuku) + "] : ");
					int pilihanBuku = input.nextInt();
					if((pilihanBuku > banyakBuku) || (pilihanBuku < 1)) {
						System.out.println("Nomor buku tidak ada");
					} else {
						Buku bukuTerpilih = perpustakaan.daftarBuku.get(pilihanBuku-1);
						if(bukuTerpilih.cekBuku()) {
							System.out.println("Buku tersedia. Anda bisa meminjam buku ini");
							System.out.println("Pilih pustakawan untuk meminjamkan buku ini kepada anda");
							int banyakUser = daftarUser.size();
							ArrayList<User> daftarPustakawan = new ArrayList<User>();
							int nomorUser = 0;
							System.out.println("Daftar pustakawan:");
							for(int i = 0; i < banyakUser; i++) {
								User userCek = daftarUser.get(i);
								if(userCek.role.equals("pustakawan")) {
									nomorUser++;
									System.out.println(nomorUser + ". " + userCek.nama);
									daftarPustakawan.add(userCek);
								}
							}
							int banyakPustakawan = daftarPustakawan.size();
							System.out.print("Masukkan nomor pustakawan [1" + (banyakPustakawan == 1 ? "" : "-"+banyakPustakawan) + "] : ");
							int pilihanPustakawan = input.nextInt();
							if((pilihanPustakawan > banyakPustakawan) || (pilihanPustakawan < 1)) {
								System.out.println("Nomor pustakawan tidak ada");
							} else {
								System.out.print("Masukkan berapa hari anda mau meminjam: ");
								int lamaPinjam = input.nextInt();
								bukuTerpilih.minjam(daftarPustakawan.get(pilihanPustakawan-1), currentUser, lamaPinjam);
							}
						} else {
							System.out.println("Buku '" + bukuTerpilih.judul + "' tidak tersedia. Semua buku tersebut sedang dipinjam");
						}
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
			} else if(pilihan == 2) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					System.out.println("Daftar buku yang sedang anda pinjam: ");
					boolean bukuTidakAda = true;
					int nomorBuku = 0;
					ArrayList<Buku> daftarBukuDipinjam = new ArrayList<Buku>();
					for(int i = 0; i < banyakBuku; i++) {
						Buku bukunya = perpustakaan.daftarBuku.get(i);
						for(int j = 0; j < bukunya.peminjam.size(); j++) {
							ArrayList<Object> dataPeminjam = bukunya.peminjam.get(j);
							if(dataPeminjam.get(1).equals(currentUser)) {
								bukuTidakAda = false;
								nomorBuku++;
								System.out.println(nomorBuku + ". " + bukunya.judul);
								daftarBukuDipinjam.add(bukunya);
							}
						}
					}
					if(bukuTidakAda) {
						System.out.println("-");
						System.out.println("\n======================================================");
					} else {
						int banyakBukuDipinjam = daftarBukuDipinjam.size();
						System.out.print("Masukkan nomor buku [1" + (banyakBukuDipinjam == 1 ? "" : "-"+banyakBukuDipinjam) + "] : ");
						int pilihanBukuDipinjam = input.nextInt();
						if((pilihanBukuDipinjam > banyakBukuDipinjam) || (pilihanBukuDipinjam < 1)) {
							System.out.println("Nomor buku tidak ada");
						} else {
							Buku bukuYangMauDibalikin = daftarBukuDipinjam.get(pilihanBukuDipinjam-1);
							bukuYangMauDibalikin.kembalikanBuku(currentUser);
						}
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 3) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					tampilkanDaftarBuku();
					System.out.println();				
					System.out.print("Masukkkan pilihan buku [1" + (banyakBuku == 1 ? "" : "-"+banyakBuku) + "] : ");
					int pilihanBuku = input.nextInt();
					if((pilihanBuku > banyakBuku) || (pilihanBuku < 1)) {
						System.out.println("Nomor buku tidak ada");
					} else {
						Buku bukuTerpilih = perpustakaan.daftarBuku.get(pilihanBuku-1);
						if(bukuTerpilih.cekBuku()) {
							int stokTersisa = bukuTerpilih.stok - bukuTerpilih.peminjam.size();
							System.out.println("Buku '" + bukuTerpilih.judul + "' tersedia. Masih tersisa " + stokTersisa + " stok yang bisa dipinjam.");
						} else {
							System.out.println("Buku '" + bukuTerpilih.judul + "' tidak tersedia. Semua buku tersebut sedang dipinjam");
						}
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");

			} else if(pilihan == 4) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					boolean bukuTidakAda = true;
					for(int i = 0; i < banyakBuku; i++) {
						Buku bukunya = perpustakaan.daftarBuku.get(i);
						int nomorBuku = 0;
						for(int j = 0; j < bukunya.peminjam.size(); j++) {
							ArrayList<Object> dataPeminjam = bukunya.peminjam.get(j);
							if(dataPeminjam.get(1).equals(currentUser)) {
								nomorBuku++;
								//User pustakawan = dataPeminjam.get(0);
								System.out.println(nomorBuku + ". Buku '" + bukunya.judul + "' dipinjam dari pustakawan " + dataPeminjam.get(0) + " selama " + dataPeminjam.get(2) + " hari");
								bukuTidakAda = false;
							}
						}
					}
					if(bukuTidakAda) {
						System.out.println("-");
						System.out.println("\n======================================================");
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 5) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					tampilkanDaftarBuku();
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 6) {
				logout();
			} else {
				System.out.println("Pilihan tidak valid");
				System.out.println("\n======================================================");
			}
		} else {
			Scanner input = new Scanner(System.in);
			if(pilihan == 1) {
				System.out.print("Masukkan judul buku: ");
				String judul = input.nextLine();
				System.out.print("Masukkan penulis buku: ");
				String penulis = input.nextLine();
				System.out.print("Masukkan stok buku: ");
				int stok = input.nextInt();
				Buku bukuNya = new Buku(judul, penulis, stok);
				perpustakaan.tambahBuku(bukuNya);
				System.out.println("Buku berhasil ditambahkan!");
				System.out.println("\n======================================================");
				
			} else if(pilihan == 2) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					tampilkanDaftarBuku();
					System.out.println();				
					System.out.print("Masukkkan pilihan buku [1" + (banyakBuku == 1 ? "" : "-"+banyakBuku) + "] : ");
					int pilihanBuku = input.nextInt();
					if((pilihanBuku > banyakBuku) || (pilihanBuku < 1)) {
						System.out.println("Nomor buku tidak ada");
					} else {
						Buku bukuTerpilih = perpustakaan.daftarBuku.get(pilihanBuku-1);
						if(bukuTerpilih.cekBuku()) {
							int stokTersisa = bukuTerpilih.stok - bukuTerpilih.peminjam.size();
							System.out.println("Buku '" + bukuTerpilih.judul + "' tersedia. Masih tersisa " + stokTersisa + " stok yang bisa dipinjam.");
						} else {
							System.out.println("Buku '" + bukuTerpilih.judul + "' tidak tersedia. Semua buku tersebut sedang dipinjam");
						}
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 3) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					boolean bukuTidakAda = true;
					int nomorBuku = 0;
					for(int i = 0; i < banyakBuku; i++) {
						Buku bukunya = perpustakaan.daftarBuku.get(i);
						for(int j = 0; j < bukunya.peminjam.size(); j++) {
							ArrayList<Object> dataPeminjam = bukunya.peminjam.get(j);
							if(dataPeminjam.get(0).equals(currentUser)) {
								nomorBuku++;
								System.out.println(nomorBuku + "Meminjamkan buku '" + bukunya.judul + "' ke " + dataPeminjam.get(1) + " selama " + dataPeminjam.get(2) + " hari");
								bukuTidakAda = false;
							}
						}
					}
					if(bukuTidakAda) {
						System.out.println("-");
						System.out.println("\n======================================================");
					}
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 4) {
				int banyakBuku = perpustakaan.daftarBuku.size();
				if(banyakBuku > 0) {
					tampilkanDaftarBuku();
				} else {
					System.out.println("Tidak ada buku dalam perpustakaan!");
				}
				System.out.println("\n======================================================");
				
			} else if(pilihan == 5) {
				logout();
			} else {
				System.out.println("Pilihan tidak valid");
				System.out.println("\n======================================================");
			}
		}
	}
	public static void menuUser() {
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("Selamat datang " + currentUser.nama);
		System.out.println("Anda adalah seorang " + currentUser.role + ", anda dapat:");
		System.out.println();
		if(currentUser.role.equals("pengunjung")) {
			System.out.println("1. Meminjam buku");
			System.out.println("2. Mengembalikan buku");
			System.out.println("3. Mengecek buku");
			System.out.println("4. Melihat daftar buku yang sedang dipinjam");
			System.out.println("5. Melihat daftar buku pada perpustakaan");
			System.out.println("6. Logout");
			System.out.println();
			System.out.print("Masukkan pilihan [1-6] : ");
			int pilihan = input.nextInt();
			System.out.println("\n======================================================");
			aksiMenu("pengunjung", pilihan);
		} else {
			System.out.println("1. Membuat buku");
			System.out.println("2. Mengecek buku");
			System.out.println("3. Melihat daftar buku yang sedang dipinjamkan");
			System.out.println("4. Melihat daftar buku pada perpustakaan");
			System.out.println("5. Logout");
			System.out.println();
			System.out.print("Masukkan pilihan [1-5] : ");
			int pilihan = input.nextInt();
			System.out.println("\n======================================================");
			aksiMenu("pustakawan", pilihan);
		}
		//System.out.println("\n======================================================");
	}
	public static void main(String[] args) {
		User pustakawanPertama = new User("Pustakawan utama", "pustakawan");
		daftarUser.add(pustakawanPertama);
		while(programJalan) {
			if(cekLogin()) {
				menuUser();
			} else {
				menuAwal();
			}
		}
	}
}
