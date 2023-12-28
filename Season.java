//Name: Varak Mesropian
//ID: 501168289

import java.util.ArrayList;

public class Season
{
	
	public ArrayList<String> episodeTitles;
	public ArrayList<String> episodeFiles;
	public ArrayList<Integer> episodeLengths;


	public Season()
	{
		episodeTitles = new ArrayList<String>();
        episodeFiles = new ArrayList<String>();
		episodeLengths = new ArrayList<Integer>();
	}

	public boolean playEpisode(int episode)
	{
		if (episode < 1 || episode > episodeTitles.size() || episode > episodeFiles.size()) //check if valid
		{
			System.out.println("Episode index not valid"); //season not valid
			return false; //not valid
		}
		System.out.println(episodeTitles.get(episode-1)); //print title
		System.out.println(episodeFiles.get(episode-1)); //print episode
		return true; //true
	}

	public void printSeason()
	{
		for(int i = 0; i < episodeTitles.size(); i++) //going through arraylist
		{
			System.out.println("Episode " + (i+1) + ". " + episodeTitles.get(i)); //printing episodes and index
			System.out.println(); //for space in between the episodes
		}
	}
	

}
