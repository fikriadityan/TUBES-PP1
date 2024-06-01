/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author fikri aditya
 */
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class TugasManager {
    private Stack<String> stack;
    private Stack<String> fileList;

    public TugasManager() {
        stack = new Stack<>();
        fileList = new Stack<>();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    //manajemen tugas
        //menambahkan tugas baru
    public void push(String item) { 
        stack.push(item);
        System.out.println("Tugas '" + item + "' telah ditambahkan ke dalam tumpukan.");
    }
        //menghapus tugas teratas
    public String pop() {
        if (isEmpty()) {
            return "Tumpukan kosong, tidak ada tugas untuk dihapus.";
        }
        return stack.pop();
    }
    //
        //melihat tugas teratas
    public String peek() {
        if (isEmpty()) {
            return "Tumpukan kosong, tidak ada tugas untuk dilihat.";
        }
        return stack.peek();
    }
        //mengembalikan jumlah tugas
    public int size() {
        return stack.size();
    }
        //menampilkan semua tugas
    public void display() {
        if (isEmpty()) {
            System.out.println("Tumpukan kosong.");
        } else {
            System.out.println("Daftar tugas dalam tumpukan:");
            for (int i = stack.size() - 1; i >= 0; i--) {
                System.out.println((i + 1) + ". " + stack.get(i));
            }
        }
    }
        //mengedit tugas pada posisi tertentu
    public void edit(int index, String newTask) {
        if (index >= 0 && index < stack.size()) {
            stack.set(index, newTask);
            System.out.println("Tugas pada posisi " + (index + 1) + " telah diperbarui.");
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }
    //manajemen file
         //menyimpan daftar tugas ke dalam file
    public void saveToFile(String filename) {
        if (fileList.contains(filename)) {
            System.out.println("File dengan nama '" + filename + "' sudah ada. Silakan gunakan nama file yang berbeda.");
            return;
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (String task : stack) {
                out.println(task);
            }
            fileList.push(filename);
            System.out.println("Tumpukan tugas telah disimpan ke file " + filename);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan ke file.");
        }
    }
        //memuat daftar tugas dari file
    public void loadFromFile(String filename) {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            stack.clear();
            String task;
            while ((task = in.readLine()) != null) {
                stack.push(task);
            }
            if (!fileList.contains(filename)) {
                fileList.push(filename);
            }
            System.out.println("Tumpukan tugas telah dipulihkan dari file " + filename);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat dari file.");
        }
    }
    //
        //mencari tugas berdasarkan kata kunci
    public void search(String keyword) {
        boolean found = false;
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Ditemukan tugas: " + stack.get(i) + " pada posisi " + (i + 1));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada tugas yang cocok dengan kata kunci '" + keyword + "'.");
        }
    }
        //menampilkan daftar file yang telah digunakan
    public void listFiles() {
        if (fileList.isEmpty()) {
            System.out.println("Belum ada file tugas yang dimuat atau disimpan.");
        } else {
            System.out.println("Daftar file tugas yang dimuat dan disimpan:");
            for (int i = fileList.size() - 1; i >= 0; i--) {
                System.out.println((i + 1) + ". " + fileList.get(i));
            }
        }
    }
        //menghapus file dari daftar file yang telah digunakan
    public void deleteFileFromList(int index) {
        if (index >= 0 && index < fileList.size()) {
            String removedFile = fileList.remove(index);
            System.out.println("File '" + removedFile + "' telah dihapus dari daftar file.");
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }
        //menghapus beberapa file dari daftar file yang telah digunakan
    public void deleteMultipleFilesFromList(Stack<Integer> indices) {
        Stack<String> filesToRemove = new Stack<>();
        for (int index : indices) {
            if (index >= 0 && index < fileList.size()) {
                filesToRemove.push(fileList.get(index));
            } else {
                System.out.println("Indeks " + (index + 1) + " tidak valid dan diabaikan.");
            }
        }
        fileList.removeAll(filesToRemove);
        System.out.println("File-file yang dipilih telah dihapus dari daftar file.");
    }

    public static void main(String[] args) {
        TugasManager tugasManager = new TugasManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Hapus Tugas Teratas");
            System.out.println("3. Lihat Tugas Teratas");
            System.out.println("4. Lihat Semua Tugas");
            System.out.println("5. Edit Tugas");
            System.out.println("6. Simpan Tugas ke File");
            System.out.println("7. Muat Tugas dari File");
            System.out.println("8. Cari Tugas");
            System.out.println("9. Lihat Daftar File Tugas");
            System.out.println("10. Hapus File dari Daftar");
            System.out.println("11. Keluar");

            System.out.print("Pilih opsi (1-11): ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan nama atau deskripsi tugas: ");
                    String tugas = scanner.nextLine();
                    tugasManager.push(tugas);
                    break;
                case "2":
                    String tugasDihapus = tugasManager.pop();
                    if (!tugasDihapus.equals("Tumpukan kosong, tidak ada tugas untuk dihapus.")) {
                        System.out.println("Tugas '" + tugasDihapus + "' telah dihapus dari tumpukan.");
                    } else {
                        System.out.println(tugasDihapus);
                    }
                    break;
                case "3":
                    System.out.println("Tugas teratas: " + tugasManager.peek());
                    break;
                case "4":
                    tugasManager.display();
                    break;
                case "5":
                    System.out.print("Masukkan nomor tugas yang ingin diedit: ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine();  // Konsumsi newline
                    System.out.print("Masukkan deskripsi tugas baru: ");
                    String tugasBaru = scanner.nextLine();
                    tugasManager.edit(index, tugasBaru);
                    break;
                case "6":
                    System.out.print("Masukkan nama file untuk menyimpan: ");
                    String filenameSave = scanner.nextLine();
                    tugasManager.saveToFile(filenameSave);
                    break;
                case "7":
                    System.out.print("Masukkan nama file untuk memuat: ");
                    String filenameLoad = scanner.nextLine();
                    tugasManager.loadFromFile(filenameLoad);
                    break;
                case "8":
                    System.out.print("Masukkan kata kunci untuk mencari: ");
                    String keyword = scanner.nextLine();
                    tugasManager.search(keyword);
                    break;
                case "9":
                    tugasManager.listFiles();
                    break;
                case "10":
                    tugasManager.listFiles();
                    System.out.print("Masukkan nomor file yang ingin dihapus (pisahkan dengan spasi jika lebih dari satu): ");
                    String[] indicesStr = scanner.nextLine().split(" ");
                    Stack<Integer> indices = new Stack<>();
                    for (String indexStr : indicesStr) {
                        indices.push(Integer.parseInt(indexStr) - 1);
                    }
                    if (indices.size() == 1) {
                        tugasManager.deleteFileFromList(indices.pop());
                    } else {
                        tugasManager.deleteMultipleFilesFromList(indices);
                    }
                    break;
                case "11":
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih opsi dari 1 hingga 11.");
                    break;
            }
        }
    }
}
