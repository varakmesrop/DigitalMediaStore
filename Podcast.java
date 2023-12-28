//Name: Varak Mesropian
//ID: 501168289

import java.util.ArrayList;

/*
 * A Podcasr is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */

public class Podcast extends AudioContent
{
	public static final String TYPENAME = "PODCAST";
	
	private String host; 
	private ArrayList<Season> seasons;

	
	public Podcast(String title, int year, String id, String type, String audioFile, int length,
									String host, ArrayList<Season> seasons)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Podcast instance variables.
		super(title, year, id, type, audioFile, length);
		this.host = host;
		this.seasons = seasons;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the Podcast. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		super.printInfo(); // printinfo()
		System.out.print("Host: " + host); //print host
		System.out.println("Seasons: "+ seasons.size()); //print season
	
	}

	//play method, playall()

	public boolean playEpisode(int season, int episode)
	{
		if (season < 1 || season > seasons.size()) // check if valid
		{
			System.out.println("Season index not valid"); //season not valid
			return false;//not valid
		}
		printInfo(); //print info
		System.out.println(); //space
		return seasons.get(season-1).playEpisode(episode); //play episodes of the specific season
	}
	
	public boolean printPodToc(int season)
	{
		if (season < 1 || season > seasons.size()) // check if valid
		{
			System.out.println("Season index not valid"); //season not valid
			return false;//not valid
		}
		seasons.get(season-1).printSeason(); //find season with given index
		return true; //valid
	}
}