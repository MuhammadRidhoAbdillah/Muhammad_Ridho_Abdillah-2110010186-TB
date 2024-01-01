/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatLightLaf;
import entity.Pengunjung;
import entity.Tiket;
import entity.Wahana;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TampilFrameUtama extends javax.swing.JFrame {
    
     Koneksi koneksi = new Koneksi();
     Connection con = koneksi.getConnection();

    Pengunjung pengunjung;
    Wahana wahana;
    Tiket tiket;
    
    int status;
    private final int TAMBAH = 100;
    private final int UBAH = 101;
    
    public TampilFrameUtama() {
        initComponents();
        status = TAMBAH;
        Panel();
        Utama.setVisible(true);
        
    }
    
    public void Panel(){
        Utama.setVisible(false);
        Pengunjung.setVisible(false);
        Wahana.setVisible(false);
        Tiket.setVisible(false);
    }
    
     public ArrayList<Pengunjung> DataPengunjung (String keyword) {
       ArrayList<Pengunjung> pengunjungData = new ArrayList<Pengunjung>();
       DefaultTableModel model = (DefaultTableModel) tbPengunjung.getModel();
 
           PreparedStatement ps;
           ResultSet rs;
           String sql = "SELECT * FROM pengunjung";
            if(!keyword.equals("")){
                sql = sql + " WHERE nama like ? ";
            }
            
            try{
                ps = con.prepareStatement(sql);
                if(!keyword.equals("")){
                ps.setString(1,"%"+tfCari.getText()+"%");
            }
            rs = ps.executeQuery();
           while(rs.next()){
               pengunjung = new Pengunjung(
                                 rs.getInt("id"),
                                 rs.getString("nama"),
                                 rs.getString("jeniskelamin"),
                                 rs.getString("umur"),
                       rs.getString("telepon"),
                                 rs.getString("alamat"));
                pengunjungData.add(pengunjung);
           }
       }catch(SQLException ex){
            System.err.println("Error Pengunjung  : "+ex);
        }
        return pengunjungData;
   }
    public void pilihPengunjung(String keyword){
        ArrayList<Pengunjung> list;
        list = DataPengunjung(keyword);
        DefaultTableModel model = (DefaultTableModel)tbPengunjung.getModel();
        Object[] row =  new Object[6];
        
        for (int i=0; i<list.size(); i++){
            row[0] = list.get(i).getIdPengunjung();
            row[1] = list.get(i).getNamaPengunjung();
            row[2] = list.get(i).getJenkel();
            row[3] = list.get(i).getUmur();
            row[4] = list.get(i).getTelp();
            row[5] = list.get(i).getAlamat();
            model.addRow(row);
        }
    }
    
    public ArrayList<Wahana> DataWahana (){
        ArrayList<Wahana> wahanaData = new ArrayList<Wahana>();
        
        String query ="SELECT * FROM  wahana";
        Statement st;
        ResultSet rs;
       try{
           st = con.createStatement();
           rs = st.executeQuery(query);
           while(rs.next()){
                wahana = new Wahana (
                        rs.getInt("id_wahana"),
                        rs.getString("namawahana"),
                        rs.getString("harga"),
                        rs.getString("jam_mulai"),
                rs.getString("jam_selesai"));
                wahanaData.add(wahana);
           }
       }catch (SQLException | NullPointerException ex) {
           System.err.println("Koneksi Null Gagal");
           System.err.println(ex.getMessage());
       }
       return wahanaData;
    }
    public void PilihWahana (){
        ArrayList<Wahana> list = DataWahana();
        DefaultTableModel model = (DefaultTableModel)tbWahana.getModel();
        Object [] row = new Object[5];
        
        for(int i =0; i< list.size(); i++){
            row[0] = list.get(i).getIdWahana();
            row[1] = list.get(i).getNamaWahana();
            row[2] = list.get(i).getHarga();
            row[3] = list.get(i).getJamMulai();
            row[4] = list.get(i).getJamSelesai();
            
            model.addRow(row);
        }
    }
    
    public ArrayList<Tiket> DataTiket (){
        ArrayList<Tiket> tiketData = new ArrayList<Tiket>();
        Koneksi koneksi = new Koneksi();
        Connection connection = koneksi.getConnection();
        
        String query ="SELECT tiket.*, pengunjung.nama, pengunjung.umur, wahana.namawahana, wahana.harga "
                +"FROM pengunjung JOIN tiket ON pengunjung.id = tiket.id JOIN wahana ON tiket.id_wahana = wahana.id_wahana where CURRENT_DATE ";
        Statement st;
        ResultSet rs;
       try{
           st = connection.createStatement();
           rs = st.executeQuery(query);
           while(rs.next()){
                tiket = new Tiket (
                        rs.getInt("id_tiket"),
                         rs.getInt("id"),
                         rs.getInt("id_wahana"),
                        rs.getString("tanggal"),
                        rs.getString("nama"),
                        rs.getString("umur"),
                 rs.getString("namawahana"),
                        rs.getString("harga"));
                tiketData.add(tiket);
           }
       }catch (SQLException | NullPointerException ex) {
           System.err.println("Koneksi Null Gagal");
           System.err.println(ex.getMessage());
       }
       return tiketData;
    }
    public void PilihTiket (){
        ArrayList<Tiket> list = DataTiket();
        DefaultTableModel model = (DefaultTableModel)tbTiket.getModel();
        Object [] row = new Object[8];
        
        for(int i =0; i< list.size(); i++){
            row[0] = list.get(i).getIdTiket();
            row[1] = list.get(i).getIdPengunjung2();
            row[2] = list.get(i).getIdWahana2();
            row[3] = list.get(i).getTanggal();
             row[4] = list.get(i).getNama();
              row[5] = list.get(i).getUmurPengunjung();
               row[6] = list.get(i).getNmWahana();
                row[7] = list.get(i).getHargaMasuk();
            model.addRow(row);
        }
    }
    
         public final void refreshData (String keyword){
        DefaultTableModel tabelPengunjung = (DefaultTableModel)tbPengunjung.getModel();
        tabelPengunjung.setRowCount(0);
        DefaultTableModel tabelWahana = (DefaultTableModel)tbWahana.getModel();
        tabelWahana.setRowCount(0);
        DefaultTableModel tabelTiket = (DefaultTableModel)tbTiket.getModel();
        tabelTiket.setRowCount(0);
        pilihPengunjung(keyword);
        PilihWahana();
       PilihTiket();
    }
         
        public void Clear(){
            cbbNama.setSelectedIndex(0);
            cbbWahana.setSelectedIndex(0);
            tfUmur2.setText("");
            tfTotal.setText("");
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfIdNama = new javax.swing.JTextField();
        tfIdWahana = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfIdTiket = new javax.swing.JTextField();
        bg = new javax.swing.JPanel();
        bar = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        btLogout = new javax.swing.JButton();
        bar1 = new javax.swing.JPanel();
        btUtama = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btPengunjung = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btWahana = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btTiket = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Utama = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Pengunjung = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btTambahPengunjung = new javax.swing.JButton();
        btUbahPengunjung = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPengunjung = new javax.swing.JTable();
        btHapusPengunjung = new javax.swing.JButton();
        btLpPengunjung = new javax.swing.JButton();
        tfCari = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Wahana = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btTambahWahana = new javax.swing.JButton();
        btUbahWahana = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbWahana = new javax.swing.JTable();
        btHapusWahan = new javax.swing.JButton();
        Tiket = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        cbbNama = new javax.swing.JComboBox<>();
        cbbWahana = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfUmur2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTiket = new javax.swing.JTable();
        btClear = new javax.swing.JButton();
        btUbah = new javax.swing.JButton();
        btHapus1 = new javax.swing.JButton();
        btTambah = new javax.swing.JButton();
        btLpTiket = new javax.swing.JButton();

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Amanah Borneo Park");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bar.setBackground(new java.awt.Color(51, 153, 255));
        bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/judul.png"))); // NOI18N
        bar.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/amanah.png"))); // NOI18N
        bar.add(logo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        btLogout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btLogout.setText("Logout");
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });
        bar.add(btLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 90, 40));

        bg.add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 60));

        bar1.setBackground(new java.awt.Color(127, 190, 255));
        bar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btUtama.setBackground(new java.awt.Color(127, 190, 255));
        btUtama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btUtamaMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/utama.png"))); // NOI18N
        jLabel2.setText(" Menu Utama");

        javax.swing.GroupLayout btUtamaLayout = new javax.swing.GroupLayout(btUtama);
        btUtama.setLayout(btUtamaLayout);
        btUtamaLayout.setHorizontalGroup(
            btUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btUtamaLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        btUtamaLayout.setVerticalGroup(
            btUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bar1.add(btUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, -1));

        btPengunjung.setBackground(new java.awt.Color(127, 190, 255));
        btPengunjung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btPengunjungMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pengunjung.png"))); // NOI18N
        jLabel3.setText(" Pengunjung");

        javax.swing.GroupLayout btPengunjungLayout = new javax.swing.GroupLayout(btPengunjung);
        btPengunjung.setLayout(btPengunjungLayout);
        btPengunjungLayout.setHorizontalGroup(
            btPengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btPengunjungLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        btPengunjungLayout.setVerticalGroup(
            btPengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btPengunjungLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bar1.add(btPengunjung, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 160, -1));

        btWahana.setBackground(new java.awt.Color(127, 190, 255));
        btWahana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btWahanaMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/wahana.png"))); // NOI18N
        jLabel4.setText(" Wahana");

        javax.swing.GroupLayout btWahanaLayout = new javax.swing.GroupLayout(btWahana);
        btWahana.setLayout(btWahanaLayout);
        btWahanaLayout.setHorizontalGroup(
            btWahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btWahanaLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(27, 27, 27))
        );
        btWahanaLayout.setVerticalGroup(
            btWahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btWahanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bar1.add(btWahana, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 160, -1));

        btTiket.setBackground(new java.awt.Color(127, 190, 255));
        btTiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btTiketMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tiket.png"))); // NOI18N
        jLabel5.setText(" Tiket");

        javax.swing.GroupLayout btTiketLayout = new javax.swing.GroupLayout(btTiket);
        btTiket.setLayout(btTiketLayout);
        btTiketLayout.setHorizontalGroup(
            btTiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btTiketLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btTiketLayout.setVerticalGroup(
            btTiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btTiketLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bar1.add(btTiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 120, -1));

        bg.add(bar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1010, 42));

        Utama.setBackground(new java.awt.Color(255, 255, 255));
        Utama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel15.setText("Selamat Datang Admin");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Gunakan Tombol DiBar Atas Untuk Melakukan Transaksi");

        javax.swing.GroupLayout UtamaLayout = new javax.swing.GroupLayout(Utama);
        Utama.setLayout(UtamaLayout);
        UtamaLayout.setHorizontalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtamaLayout.createSequentialGroup()
                .addGroup(UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel15))
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel16)))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        UtamaLayout.setVerticalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtamaLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        bg.add(Utama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 990, 430));

        Pengunjung.setBackground(new java.awt.Color(255, 255, 255));
        Pengunjung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("Data Pengunjung");

        btTambahPengunjung.setBackground(new java.awt.Color(51, 153, 255));
        btTambahPengunjung.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btTambahPengunjung.setForeground(new java.awt.Color(255, 255, 255));
        btTambahPengunjung.setText("Tambah");
        btTambahPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahPengunjungActionPerformed(evt);
            }
        });

        btUbahPengunjung.setBackground(new java.awt.Color(127, 190, 255));
        btUbahPengunjung.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btUbahPengunjung.setForeground(new java.awt.Color(255, 255, 255));
        btUbahPengunjung.setText("Ubah");
        btUbahPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahPengunjungActionPerformed(evt);
            }
        });

        tbPengunjung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Nama", "Jenis Kelamin", "Umur", "No Telepon", "Alamat"
            }
        ));
        jScrollPane1.setViewportView(tbPengunjung);
        if (tbPengunjung.getColumnModel().getColumnCount() > 0) {
            tbPengunjung.getColumnModel().getColumn(0).setMaxWidth(30);
            tbPengunjung.getColumnModel().getColumn(5).setHeaderValue("Alamat");
        }

        btHapusPengunjung.setBackground(new java.awt.Color(255, 51, 51));
        btHapusPengunjung.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btHapusPengunjung.setForeground(new java.awt.Color(255, 255, 255));
        btHapusPengunjung.setText("Hapus");
        btHapusPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusPengunjungActionPerformed(evt);
            }
        });

        btLpPengunjung.setBackground(new java.awt.Color(51, 153, 255));
        btLpPengunjung.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btLpPengunjung.setForeground(new java.awt.Color(255, 255, 255));
        btLpPengunjung.setText("Cetak Laporan Pengunjung");
        btLpPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLpPengunjungActionPerformed(evt);
            }
        });

        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCariKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Pencarian");

        javax.swing.GroupLayout PengunjungLayout = new javax.swing.GroupLayout(Pengunjung);
        Pengunjung.setLayout(PengunjungLayout);
        PengunjungLayout.setHorizontalGroup(
            PengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PengunjungLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(PengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PengunjungLayout.createSequentialGroup()
                            .addComponent(btTambahPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btUbahPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btHapusPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btLpPengunjung, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        PengunjungLayout.setVerticalGroup(
            PengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PengunjungLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PengunjungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTambahPengunjung)
                    .addComponent(btUbahPengunjung)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btHapusPengunjung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLpPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bg.add(Pengunjung, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 990, 430));

        Wahana.setBackground(new java.awt.Color(255, 255, 255));
        Wahana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel8.setText("List Wahana");

        btTambahWahana.setBackground(new java.awt.Color(51, 153, 255));
        btTambahWahana.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btTambahWahana.setForeground(new java.awt.Color(255, 255, 255));
        btTambahWahana.setText("Tambah");
        btTambahWahana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahWahanaActionPerformed(evt);
            }
        });

        btUbahWahana.setBackground(new java.awt.Color(127, 190, 255));
        btUbahWahana.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btUbahWahana.setForeground(new java.awt.Color(255, 255, 255));
        btUbahWahana.setText("Ubah");
        btUbahWahana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahWahanaActionPerformed(evt);
            }
        });

        tbWahana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Nama Wahana", "Harga", "Jam Mulai", "Jam Selesai"
            }
        ));
        jScrollPane2.setViewportView(tbWahana);
        if (tbWahana.getColumnModel().getColumnCount() > 0) {
            tbWahana.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        btHapusWahan.setBackground(new java.awt.Color(255, 51, 51));
        btHapusWahan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btHapusWahan.setForeground(new java.awt.Color(255, 255, 255));
        btHapusWahan.setText("Hapus");
        btHapusWahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusWahanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WahanaLayout = new javax.swing.GroupLayout(Wahana);
        Wahana.setLayout(WahanaLayout);
        WahanaLayout.setHorizontalGroup(
            WahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WahanaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(WahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(WahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WahanaLayout.createSequentialGroup()
                            .addComponent(btTambahWahana, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btUbahWahana, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btHapusWahan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        WahanaLayout.setVerticalGroup(
            WahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WahanaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(WahanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTambahWahana)
                    .addComponent(btUbahWahana)
                    .addComponent(btHapusWahan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        bg.add(Wahana, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 990, 430));

        Tiket.setBackground(new java.awt.Color(255, 255, 255));
        Tiket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel9.setText("Pembelian Tiket");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Nama");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("Pilih Wahana");

        tfTotal.setEditable(false);

        cbbNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Nama -" }));
        cbbNama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamaItemStateChanged(evt);
            }
        });

        cbbWahana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Wahana -" }));
        cbbWahana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbWahanaItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Umur");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Total Bayar");

        tfUmur2.setEditable(false);

        tbTiket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "IdPengunjung", "IdWahana", "Tanggal", "Nama", "Umur", "Wahana Dipilih", "Total Bayar"
            }
        ));
        tbTiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTiketMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbTiket);
        if (tbTiket.getColumnModel().getColumnCount() > 0) {
            tbTiket.getColumnModel().getColumn(0).setMaxWidth(28);
            tbTiket.getColumnModel().getColumn(1).setMinWidth(0);
            tbTiket.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbTiket.getColumnModel().getColumn(1).setMaxWidth(0);
            tbTiket.getColumnModel().getColumn(2).setMinWidth(0);
            tbTiket.getColumnModel().getColumn(2).setPreferredWidth(0);
            tbTiket.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        btClear.setBackground(new java.awt.Color(255, 51, 51));
        btClear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        btUbah.setBackground(new java.awt.Color(127, 190, 255));
        btUbah.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btUbah.setForeground(new java.awt.Color(255, 255, 255));
        btUbah.setText("Ubah");
        btUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahActionPerformed(evt);
            }
        });

        btHapus1.setBackground(new java.awt.Color(255, 51, 51));
        btHapus1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btHapus1.setForeground(new java.awt.Color(255, 255, 255));
        btHapus1.setText("Hapus");
        btHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapus1ActionPerformed(evt);
            }
        });

        btTambah.setBackground(new java.awt.Color(51, 153, 255));
        btTambah.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btTambah.setForeground(new java.awt.Color(255, 255, 255));
        btTambah.setText("Tambah");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        btLpTiket.setBackground(new java.awt.Color(51, 153, 255));
        btLpTiket.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btLpTiket.setForeground(new java.awt.Color(255, 255, 255));
        btLpTiket.setText("Laporan Hari Ini");
        btLpTiket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLpTiketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TiketLayout = new javax.swing.GroupLayout(Tiket);
        Tiket.setLayout(TiketLayout);
        TiketLayout.setHorizontalGroup(
            TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TiketLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TiketLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TiketLayout.createSequentialGroup()
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TiketLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btLpTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TiketLayout.createSequentialGroup()
                                .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TiketLayout.createSequentialGroup()
                                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel13))
                                        .addGap(93, 93, 93)
                                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbbWahana, 0, 200, Short.MAX_VALUE)
                                            .addComponent(cbbNama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfUmur2)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TiketLayout.createSequentialGroup()
                                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TiketLayout.createSequentialGroup()
                                                .addComponent(btUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addComponent(btHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))))
        );
        TiketLayout.setVerticalGroup(
            TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TiketLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLpTiket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TiketLayout.createSequentialGroup()
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(tfUmur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbWahana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TiketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btClear)
                            .addComponent(btUbah)
                            .addComponent(btHapus1)
                            .addComponent(btTambah)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        bg.add(Tiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 990, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btUtamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btUtamaMouseClicked
       Panel();
       Utama.setVisible(true);
    }//GEN-LAST:event_btUtamaMouseClicked

    private void btPengunjungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPengunjungMouseClicked
       Panel();
       Pengunjung.setVisible(true);
    }//GEN-LAST:event_btPengunjungMouseClicked

    private void btWahanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btWahanaMouseClicked
        Panel();
       Wahana.setVisible(true);
    }//GEN-LAST:event_btWahanaMouseClicked

    private void btTiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTiketMouseClicked
       Panel();
       Tiket.setVisible(true);
       
       DateTimeFormatter tgl = DateTimeFormatter.ofPattern("yyyy-MM-dd");       
       LocalDateTime now = LocalDateTime.now();
       jLabel10.setText(tgl.format(now));
       
       cbbNama.removeAllItems();
       cbbNama.addItem("- Pilih Nama -");
       cbbWahana.removeAllItems();
       cbbWahana.addItem("- Pilih Wahana -");
       Statement st;
       try {  
        st = con.createStatement();
        ResultSet rs= st.executeQuery("select * from pengunjung");
            while (rs.next()) {  
             cbbNama.addItem(rs.getString("nama"));  
             }
        } catch (Exception ex) {  
        System.err.println("Error : "+ex);
        }
       try {  
        st = con.createStatement();
        ResultSet rs= st.executeQuery("select * from wahana");
            while (rs.next()) {  
             cbbWahana.addItem(rs.getString("namawahana"));  
             }
        } catch (Exception ex) {  
        System.err.println("Error : "+ex);
        }
    }//GEN-LAST:event_btTiketMouseClicked

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Yakin Logout","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(konfirmasi==0){
            dispose();
            new TampilFrameLogin().setVisible(true);
        }  
    }//GEN-LAST:event_btLogoutActionPerformed

    private void btTambahPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahPengunjungActionPerformed
        TambahFramePengunjung tambahFramePengunjung = new TambahFramePengunjung();
        tambahFramePengunjung.setVisible(true);
    }//GEN-LAST:event_btTambahPengunjungActionPerformed

    private void btUbahPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahPengunjungActionPerformed
        int i = tbPengunjung.getSelectedRow();
        if(i>=0){
            TableModel model = tbPengunjung.getModel();
            pengunjung = new Pengunjung();
            pengunjung.setIdPengunjung(Integer.parseInt(model.getValueAt(i, 0).toString()));
            pengunjung.setNamaPengunjung(model.getValueAt(i, 1).toString());
            pengunjung.setJenkel(model.getValueAt(i, 2).toString());
            pengunjung.setUmur(model.getValueAt(i, 3).toString());
            pengunjung.setTelp(model.getValueAt(i, 4).toString());
            pengunjung.setAlamat(model.getValueAt(i, 5).toString());
            TambahFramePengunjung tambahFramePengunjung = new TambahFramePengunjung(pengunjung);
            tambahFramePengunjung.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data pengunjung yang ingin diubah");
        }
    }//GEN-LAST:event_btUbahPengunjungActionPerformed

    private void btHapusPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusPengunjungActionPerformed
           int selected = tbPengunjung.getSelectedRow();
           TableModel md = tbPengunjung.getModel();
        if(selected>=0){
           try{
                    String qry = "delete from pengunjung where id = ?";
                    PreparedStatement ps = con.prepareStatement(qry);
                    ps.setString(1, md.getValueAt(selected, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException ex){
                    System.err.println(ex);
                }
           JOptionPane.showMessageDialog(null, "Data Pengunjung Berhasil Dihapus");
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data pengunjung yang ingin dihapus");
        }
    }//GEN-LAST:event_btHapusPengunjungActionPerformed

    private void btLpPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLpPengunjungActionPerformed
        try {
            HashMap<String, Object> parameter = new HashMap();            
            JasperPrint jp = JasperFillManager.fillReport("src\\laporan\\pengunjung.jasper", parameter, con);
            JasperViewer viewer = new JasperViewer(jp, false);
        viewer.setVisible(true);
        } catch(Exception e) {
        }
    }//GEN-LAST:event_btLpPengunjungActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        refreshData("");
    }//GEN-LAST:event_formWindowActivated

    private void tfCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyTyped
        refreshData(tfCari.getText());
    }//GEN-LAST:event_tfCariKeyTyped

    private void btTambahWahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahWahanaActionPerformed
        TambahFrameWahana tambahFrameWahana = new TambahFrameWahana();
        tambahFrameWahana.setVisible(true);
    }//GEN-LAST:event_btTambahWahanaActionPerformed

    private void btUbahWahanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahWahanaActionPerformed
        int i = tbWahana.getSelectedRow();
        if(i>=0){
            TableModel model = tbWahana.getModel();
            wahana = new Wahana();
            wahana.setIdWahana(Integer.parseInt(model.getValueAt(i, 0).toString()));
            wahana.setNamaWahana(model.getValueAt(i, 1).toString());
            wahana.setHarga(model.getValueAt(i, 2).toString());
            wahana.setJamMulai(model.getValueAt(i, 3).toString());
            wahana.setJamSelesai(model.getValueAt(i, 4).toString());
            TambahFrameWahana tambahFrameWahana = new TambahFrameWahana(wahana);
            tambahFrameWahana.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data wahana yang ingin diubah");
        }
    }//GEN-LAST:event_btUbahWahanaActionPerformed

    private void btHapusWahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusWahanActionPerformed
       int selected = tbWahana.getSelectedRow();
           TableModel md = tbWahana.getModel();
        if(selected>=0){
           try{
                    String qry = "delete from wahana where id_wahana = ?";
                    PreparedStatement ps = con.prepareStatement(qry);
                    ps.setString(1, md.getValueAt(selected, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException ex){
                    System.err.println(ex);
                }
           JOptionPane.showMessageDialog(null, "Data Wahana Berhasil Dihapus");
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data wahana yang ingin dihapus");
        }
    }//GEN-LAST:event_btHapusWahanActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed

        Clear();
    }//GEN-LAST:event_btClearActionPerformed

    private void btUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahActionPerformed
        int selected = tbWahana.getSelectedRow();
           TableModel md = tbWahana.getModel();
        if(selected>=0){
          try {
             PreparedStatement ps;
        String executeQuery = "update tiket set "
                            +"id=?, id_wahana=? where id_tiket=?";
                ps = con.prepareStatement(executeQuery);
               ps.setString (1, tfIdNama.getText());
                ps.setString (2, tfIdWahana.getText());
                ps.setString (3, tfIdTiket.getText());
                 ps.executeUpdate();
                 Clear();
                JOptionPane.showMessageDialog(null, "Data Tiket Diubah");
        } catch (SQLException e) {
            System.err.println(e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data tiket yang ingin diubah");
        }
        
        
    }//GEN-LAST:event_btUbahActionPerformed

    private void btHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapus1ActionPerformed
         int selected = tbTiket.getSelectedRow();
           TableModel md = tbTiket.getModel();
        if(selected>=0){
           try{
                    String qry = "delete from tiket where id_tiket = ?";
                    PreparedStatement ps = con.prepareStatement(qry);
                    ps.setString(1, md.getValueAt(selected, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException ex){
                    System.err.println(ex);
                }
           JOptionPane.showMessageDialog(null, "Data Tiket Berhasil Dihapus");
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data tiket yang ingin dihapus");
        }
    }//GEN-LAST:event_btHapus1ActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
      int umur = Integer.parseInt(tfUmur2.getText());
      String wahana = (String) cbbWahana.getSelectedItem();
       try{
            PreparedStatement ps;
            if(cbbNama.getSelectedItem().equals("- Pilih Nama -") ||
               cbbWahana.getSelectedItem().equals("- Pilih Nama -") ||
               tfUmur2.getText().equals("")  ||
               tfTotal.getText().equals("") ){
           JOptionPane.showMessageDialog(null, "Lengkapi Data!");
            }else if(umur < 8  && wahana.equals("Sekuter Listrik")) {
                 JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
            }else if(umur < 13  && wahana.equals("Gokart")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             }else if(umur < 13  && wahana.equals("Rumah Hantu")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             } else if(umur < 13  && wahana.equals("Sepeda Air")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             }else if(umur < 13  && wahana.equals("Sepeda Udara")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             }else if(umur < 13  && wahana.equals("ATV")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             }else if(umur < 13  && wahana.equals("Flying Fox")) {
                JOptionPane.showMessageDialog(null, "Umur Tidak mencukupi");
             }else{
                String executeQuery = "insert into tiket"
                            + "(id,id_wahana,tanggal) values (?,?,?)";
                ps = con.prepareStatement(executeQuery);
                ps.setString (1, tfIdNama.getText());
                ps.setString (2, tfIdWahana.getText());
                ps.setString (3, jLabel10.getText());
                ps.executeUpdate();
                Clear();
                JOptionPane.showMessageDialog(null, "Data Tiket Ditambah");
            }
        }catch(SQLException ex){
            System.err.println("eror"+ex);
        }
    }//GEN-LAST:event_btTambahActionPerformed

    private void btLpTiketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLpTiketActionPerformed
       try {
            HashMap<String, Object> parameter = new HashMap();            
            JasperPrint jp = JasperFillManager.fillReport("src\\laporan\\tiket.jasper", parameter, con);
            JasperViewer viewer = new JasperViewer(jp, false);
        viewer.setVisible(true);
        } catch(Exception e) {
        }
    }//GEN-LAST:event_btLpTiketActionPerformed

    private void cbbNamaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamaItemStateChanged
        String nama =(String) cbbNama.getSelectedItem();
        try{
            String qry = "select * from pengunjung where nama = ?";
            Connection con = koneksi.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(qry);
            ps.setString(1, nama);
            rs = ps.executeQuery();
            if(rs.next()){
                String umur = rs.getString("umur");
                tfUmur2.setText(umur);
                String id1 = rs.getString("id");
                tfIdNama.setText(id1);
            }
        }catch(SQLException ex){
        }
    }//GEN-LAST:event_cbbNamaItemStateChanged

    private void cbbWahanaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbWahanaItemStateChanged
        String total =(String) cbbWahana.getSelectedItem();
        try{
            String qry = "select * from wahana where namawahana = ?";
            Connection con = koneksi.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(qry);
            ps.setString(1, total);
            rs = ps.executeQuery();
            if(rs.next()){
                String total2 = rs.getString("harga");
                String id2 = rs.getString("id_wahana");
                tfTotal.setText(total2);
                tfIdWahana.setText(id2);
            }
        }catch(SQLException ex){
        }
    }//GEN-LAST:event_cbbWahanaItemStateChanged

    private void tbTiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTiketMouseClicked
          int i = tbTiket.getSelectedRow();
            TableModel model = tbTiket.getModel();
            tfIdTiket.setText(model.getValueAt(i, 0).toString());
            cbbNama.setSelectedItem(model.getValueAt(i, 4).toString());
            cbbWahana.setSelectedItem(model.getValueAt(i, 6).toString());
            tfUmur2.setText(model.getValueAt(i, 5).toString());
            tfTotal.setText(model.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tbTiketMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
           UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TampilFrameUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pengunjung;
    private javax.swing.JPanel Tiket;
    private javax.swing.JPanel Utama;
    private javax.swing.JPanel Wahana;
    private javax.swing.JPanel bar;
    private javax.swing.JPanel bar1;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btHapus1;
    private javax.swing.JButton btHapusPengunjung;
    private javax.swing.JButton btHapusWahan;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btLpPengunjung;
    private javax.swing.JButton btLpTiket;
    private javax.swing.JPanel btPengunjung;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btTambahPengunjung;
    private javax.swing.JButton btTambahWahana;
    private javax.swing.JPanel btTiket;
    private javax.swing.JButton btUbah;
    private javax.swing.JButton btUbahPengunjung;
    private javax.swing.JButton btUbahWahana;
    private javax.swing.JPanel btUtama;
    private javax.swing.JPanel btWahana;
    private javax.swing.JComboBox<String> cbbNama;
    private javax.swing.JComboBox<String> cbbWahana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JTable tbPengunjung;
    private javax.swing.JTable tbTiket;
    private javax.swing.JTable tbWahana;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfIdNama;
    private javax.swing.JTextField tfIdTiket;
    private javax.swing.JTextField tfIdWahana;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfUmur2;
    // End of variables declaration//GEN-END:variables
}
