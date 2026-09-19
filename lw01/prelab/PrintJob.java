public abstract class PrintJob implements Chargeable { 
//Interface adalah kontrak yang harus dipenuhi oleh class yang mengimplementasikan interface tersebut.
//abstract class adalah class yang tidak dapan di instansiasi secara langsung, tetapi dapat digunakan sebagai super class untuk class lain. Bisa diartikan juga dengan bisa dijadikan kerangka
//comcrete class adalah class yang dapat di instansi secara langsung dan dapat digunakan untuk membuat objek. bisa diartikan juga dengan class yang sudah lengkap dan siap digunakan.
    private String id; //2 ini namanya field, id dan pages adalah fieeld dari class printjob, field adalah variabel yang dideklarasikan di dalam class dan dapat diakses oleh method di dalam class tersebut
    private int pages; //private karena encapsulation, agar tidak nisa diakses langsung dari luar kelas dan class lain tidak bisa sembarang mengubahnya

//constructor
    public PrintJob(String id, int pages) { //id dan pages adalah parameter contructor, yang akan diisi saat membuat objek baru dari class printjob
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
    this.id = id;
    this.pages = pages;
    }

    //getter
    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    @Override 
    public abstract int calculateCharge();

    public int calculateCharge(int copies) { //method calculateCharge yang menerima parameter copies, method ini akan menghitung biaya cetak berdasarkan jumlah salinan dan jumlah halaman
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be greater than 0");
        }
        return copies * calculateCharge();
    }

    public String label() {
        return "Print";
    }

    public String summary() {
        return getId() + " | " + label() + " | " + calculateCharge();
    }


}