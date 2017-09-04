// import yang diperlukan
import java.util.*;
import java.io.*;

public class Congklak
{

	public static void main(String[] args) throws IOException
	{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	//inisiasi variable
    	int idx = 0;
    	Player p0 = new Player(0);
    	Player p1 = new Player(1);
    	Board b = new Board(p0, p1);

    	// print kondisi awal board
    	b.print();

    	//while loop, sampai field salah satu pemain habis
    	while(b.getPlayer(0).countField() > 0 && b.getPlayer(1).countField() > 0)
    	{
    		// terima input index mana yang diinginkan untuk dijalankan
    		System.out.print("Pemain ");
    		System.out.print(b.getPlayer().getSide());
    		System.out.print(" Melangkah Dari: ");
    		idx = Integer.parseInt(br.readLine());

    		//masuk fungsi take dengan index yang didapat
    		b.take(idx);

    		//print board setelah satu langkah selsai
    		b.print();

    	}

    	//mendapatkan hasil pertandingan
    	b.getHasil();
	}
}


//class board mewakili papan permainan congklak
class Board
{
	Player player0;

	Player player1;
	
	// Player active adalah player yang sedang memiliki giliran berjalan
	Player active;

	//cursor menunjukan posisi pointer berada di field pemain mana
	Player cursor;

	// untuk menghidupkan board diperlukan 2 pemain
	public Board(Player player0, Player player1)
	{
		super();

		this.player0 = player0;

		this.player1 = player1;
		
		//langkah dimulai dari player 0 dan cursor dimulai dari field player 0
		this.active = player0;

		this.cursor = player0;
	}

	// Get dan Set variable
	public Player getPlayer(int id)
	{
		if(id == 0)
		{
			return player0;
		}
		return player1;
	}

	public Player getPlayer()
	{
		return active;
	}

	public void setPlayer()
	{
		if(active == player0)
		{
			active = player1;
		}else
		{
			active = player0;
		}
	}

	public void setCursor()
	{
		if(cursor == player0)
		{
			cursor = player1;
		}else
		{
			cursor = player0;
		}
	}
	// end of Get dan Set variable

	//fungsi take untuk mengambil biji pada suatu lobang
	public void take(int idx)
	{
		if(idx < 0 || idx > 6)
		{
			System.out.println("Bolehnya 0 sampe 6 om :(");
			return;
		}
		
		if(cursor.getField()[idx] == 0)
		{
			System.out.println("Kosong Om :(");
			return;
		}
		active.setHand(cursor.getField()[idx]);
		cursor.getField()[idx] = 0;
		this.move( (idx) % 8);
	}

	//fungsi move untuk memindahkan biji satu per satu
	public void move(int idx)
	{
		while(active.getHand() > 0)
		{
			this.print();
			idx = (idx + 1) % 8;

			if(active == cursor)
			{
				if(idx == 7)
				{
					active.addBank();
					this.setCursor();
				}else
				{
					cursor.getField()[idx]++;
				}
			}else
			{
				if(idx == 7)
				{
					this.setCursor();
					active.addHand();
				}else
				{
					cursor.getField()[idx]++;
				}
			}
			active.takeHand();
		}

		this.eval(idx);
	}

	//fungsi eval untuk mengecek kondisi saat berhenti, apakah jalan lagi, mati, mati + nembak, atau lanjut jalan
	public void eval (int idx)
	{
		if(idx == 7)
		{
			this.setCursor();
			this.print();
			return;
		}
		
		if(cursor.getField()[idx] == 1)
		{
			if(this.active == this.cursor)
			{
				shoot(idx);
				this.setCursor();
			}
			this.setPlayer();
			this.print();
			return;
		}

		this.print();
		this.take(idx);
	}

	//shoot adalah "nembak", yaitu mengambil biji lawan yang sejajar dengan tempat kita mati di board kita sendiri
	public void shoot(int idx)
	{
		int temp = active.getField()[idx];
		active.getField()[idx] = 0;

		this.setPlayer();
		temp += active.getField()[6-idx];
		active.getField()[6-idx] = 0;

		this.setPlayer();
		active.addBank(temp);
	}

	//fungsi untuk menampilkan kondisi board dan beberapa info lain saat ini
	public void print(){
		System.out.println("Giliran: Pemain" + this.active.getSide() + " Tangan: " + this.active.getHand());
		System.out.println("P0--0--1--2--3--4--5--6---");
		System.out.print("   ");
		for (int i = 0; i < 7; i++)
		{
			System.out.print(String.format("%02d",player0.getField()[i]) + " ");
		}

		System.out.println();
		System.out.print(String.format("%02d",player1.getBank()) + "|                    |" + String.format("%02d",player0.getBank()));
		System.out.println();

		System.out.print("   ");
		for (int i = 0; i < 7; i++)
		{
			System.out.print(String.format("%02d",player1.getField()[6-i]) + " ");
		}
		System.out.println();
		System.out.println("P1--6--5--4--3--2--1--0---");
		System.out.println();

		try        
		{
			//ubah disini untuk mengganti kecepatan animasi
    		Thread.sleep(500);
		} 
		catch(InterruptedException ex) 
		{
    		Thread.currentThread().interrupt();
		}
	}

	//mendapatkan hasil pertandingan
	public void getHasil()
	{
		if(player0.countAll() > player1.countAll())
		{
			System.out.println("Player 0 Menang");
		}else if (player0.countAll() < player1.countAll())
		{
			System.out.println("Player 1 Menang");
		}else
		{
			System.out.println("Kalian Seri");
		}
	}
}


// class player mewakili pemain dari congklak
class Player
{
	int side;
	int hand;
	int [] field;
	int bank;

	public Player(int side)
	{
		super();

		this.side = side;
		this.hand = 0;
		this.field = new int [7];
		for(int i = 0; i < 7; i++){
			// ubah disini untuk ganti jumlah biji
			field[i] = 7;
		}
		this.bank =0;
	}

	//Get dan Set variable
	//side
	public int getSide()
	{
		return this.side;
	}

	//hand
	public int getHand()
	{
		return this.hand;
	}

	public void setHand(int hand)
	{
		this.hand = hand;
	}

	public void addHand()
	{
		this.hand++;
	}

	public void takeHand()
	{
		this.hand--;
	}

	//field
	public int [] getField()
	{
		return this.field;
	}

	//bank
	public int getBank()
	{
		return this.bank;
	}

	public void setBank(int bank)
	{
		this.bank=bank;
	}

	public void addBank()
	{
		this.bank++;
	}

	public void addBank(int amount)
	{
		this.bank += amount;
	}
	//End of Get dan Set variable

	//menghitung semua biji di field
	public int countField()
	{
		return Arrays.stream(field).sum();
	}

	//menghitung semua biji milik pemain
	public int countAll()
	{
		return Arrays.stream(this.field).sum() + this.bank;
	}


}
