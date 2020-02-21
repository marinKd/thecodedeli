/**
* MARIN KOCOLLARI MY9 ASSIGNMENT #1
* Program inputs Spotify top streamed .csv charts and
* outputs a .txt file with the artist names and how many times
* they appear on list.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.PrintWriter;

//LinkedList class
class TopStreamingArtists{
	Artist head;
		
	static class Artist{
		String name;
		Artist next;		
		
		Artist(String name){
			this.name=name;
			next = null;
		}
	}
	//to add node
	public static TopStreamingArtists insert(TopStreamingArtists list, String name){
		Artist newArtist = new Artist(name);
		newArtist.next = null;		
		if(list.head == null){
			list.head = newArtist;
		}
		else{
			Artist last = list.head;
			while(last.next != null){
				last = last.next;
			}
			last.next=newArtist;
		}
		return list;
	}
	//to output list contents
	public static void displayList(TopStreamingArtists list, PrintWriter output){
		Artist node = list.head;
		System.out.println("List: ");
		while(node != null){
			output.println(node.name+" ");
			node = node.next;
		}
	}
}
class A1{
		
	public static void main(String[] args) throws Exception {
		
		//to read csv.
		BufferedReader stdin = new BufferedReader(new FileReader("global.csv"));
		
		//prepares output file
		PrintStream output = new PrintStream(new File("output.txt"));
		
		//records is ArrayList of lines
		//String[] is array of line contents
		//nested array takes in artist names
		ArrayList<String[]> records = new ArrayList<String[]>();
		String[] record = new String[3];
		while(stdin.readLine() != null){
			record = stdin.readLine().split(",");
			records.add(record);
		}
		
		//artists is array of artist names
		String[] artists = new String[records.size()];
		for(int i = 0; i < records.size(); i++)
		{
			artists[i] = records.get(i)[2];
		}
		
		//to sort array alphabetically
		Arrays.sort(artists);
		
		//variables needed to count and remove duplicate names
		boolean found = false;
		int dupcounter=0;
		String str = artists[0];
		
		//sets up linkedlist
		TopStreamingArtists viplist = new TopStreamingArtists();
		
		//loops through data to count and remove duplicate names
		//prints artist name and number of times it appeared in data
		for(int i = 0; i < artists.length; i++){
			dupcounter++;
			if(str.equals(artists[i]) && !found){
				found = true;
			}
			else if(!str.equals(artists[i])){
				output.println(dupcounter+" "+str);
				str = artists[i];
				viplist.insert(viplist, artists[i]);
				found = false;
				dupcounter=0;
			}
		}
		
		/*
		More convenient to print using arrays;
		Counting amount of artist appearences on the list proved easier using a counter

		To print using linked list(no duplicate counter):
		viplist.displayList(viplist, output);
		*/
	}
}
		
