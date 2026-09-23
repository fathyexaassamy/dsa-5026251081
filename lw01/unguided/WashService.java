public abstract class WashService implements Billable { 
//Interface adalah kontrak yang harus dipenuhi oleh class yang mengimplementasikan interface tersebut.
//abstract class adalah class yang tidak dapan di instansiasi secara langsung, tetapi dapat digunakan sebagai super class untuk class lain. Bisa diartikan juga dengan bisa dijadikan kerangka
//comcrete class adalah class yang dapat di instansi secara langsung dan dapat digunakan untuk membuat objek. bisa diartikan juga dengan class yang sudah lengkap dan siap digunakan.
    private String id; //2 ini namanya field, id dan pages adalah fieeld dari class printjob, field adalah variabel yang dideklarasikan di dalam class dan dapat diakses oleh method di dalam class tersebut
    private int days;
    private int jumlah; //private karena encapsulation, agar tidak nisa diakses langsung dari luar kelas dan class lain tidak bisa sembarang mengubahnya

//constructor
    public WashService(String id, int days, int jumlah) { //id dan days adalah parameter constructor, yang akan diisi saat membuat objek baru dari class washservice
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0");
        }
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah must be greater than 0");
        }
        this.id = id;
        this.days = days;
        this.jumlah = jumlah;
    }

    //getter
    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getJumlah() {
        return jumlah;
    }

    @Override 
    public abstract int calculateCharge();

    public int calculateCharge(int units) { //method calculateCharge yang menerima parameter copies, method ini akan menghitung biaya cetak berdasarkan jumlah salinan dan jumlah halaman
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than 0");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return getId() + " | " + label() + " | " + calculateCharge();
    }


}
