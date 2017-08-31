# Congklak

Congklak adalah salah satu permainan tradisional yang terkenal di Indonesia. info lebih lanjut mengenai congklak dapat dibaca di:  
https://id.wikipedia.org/wiki/Congklak  
  
Aturan aturan dasar mengenai congklak dapat dibaca di:  
http://www.expat.or.id/info/congklakinstructions.html  
  
Kali ini saya mencoba membuat program congklak dengan bahasa java dan dengan interface CMD / CLI. Berikut cara memainkannya:  
  
//  
Tujuan permainan:  
Tujuan permainan congklak adalah mendapatkan biji sebanyak banyaknya di dalam "bank" kalian  
  
//  
Tampilan awal:  
P0--0--1--2--3--4--5--6---    >   Menunjukan index untuk bermain sebagai Player0 atau Pemain0  
   07 07 07 07 07 07 07  
00|                    |00    >   Disebelah kiri adalah "bank" milik pemain0, dan di kanan milik pemain1  
   07 07 07 07 07 07 07  
P1--6--5--4--3--2--1--0---    >   Menunjukan index untuk bermain sebagai Player1 atau Pemain1  
  
Pemain 0 Melangkah Dari:      >   Pemain yang sedang giliran jalan diminta memasukan index dari "field" yang ingin dimainkan  
  
//  
Cara bermain:  
Setelah memilih index dari "field" yang ingin dimainkan, program akan mengambil isi dari "field" ke dalam "hand"  
  
Biji yang berada di "hand" akan dijalankan satu demi satu ke "field" lain dan akan di update pada tampilak CMD/CLI setiap kali melangkah  
  
Jika telah mencapai index "6" di "field" sendiri dan masih ada biji di "hand", pemain memasukan satu biji ke "bank" mereka, lalu berjalan ke "field" musuh  
Jika telah mencapai index "6" di "field" musuh dan masih ada biji di "hand", pemain kembali ke "field" sendiri  
  
Jila "hand" sudah habis dan pemain berhnti di "field" yang memiliki biji, maka lanjut jalan  
Jika berhenti di "bank", pemain bebas memulai jalan lagi dari index mana saja  
Jika berhenti di "field" sendiri yang kosong, pemain berhak "nembak", yaitu mengambil biji di "field" musuh yang sejajar, lalu ganti giliran  
Jika berhenti di "field: musuh yang kosong, maka langsung ganti giliran  
  
  
//  
Winning Condition:  
Jika seluruh "field" salah satu pemain telah kosong, permainan berhenti  
Pemain dengan jumlah biji di "field" + "bank" terbanyak akan menang  
  
  
//  
Yang tidak dibuat:  
Pada permainan asli congklak, setelah salah satu field habis, seharusnya permainan dilanjutkan dengan menaruh kembali "biji" di "field" masing2, dan pemain yang memiliki biji lebih sedikit akan "ngacang". "field" yang "ngacang" tidak bisa di"tembak" lawan dan harus dilewati oleh lawan. Permainan lalu diulang sampai salah satu pemain memiliki jumlah biji kurang 28 (ngacang lebih dari 3 "field").
Hal ini tidak diimplementasikan karena:  
-Pada real world scenario, kebanyakan orang sudah bosan bahkan sebelum babak pertama selsai.  
  
-Jika kedua pemain sama sama sudah ahli, permainan bisa jadi tidak akan pernah selsai karena pemain yang sedang "ngacang" memiliki banyak advantage untuk membalik keadaan, dan seterusnya. permainan yang berlangsung terus menerus dikhawatirkan menghasilkan error yang tidak terduga seperti out of memory error.  
