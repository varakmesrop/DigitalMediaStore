//Name: Varak Mesropian
//ID: 501168289


import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables.
		super(title, year, id, type, audioFile, length);
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		super.printInfo();
		System.out.print("Author: " + author + " Narrarator: " + narrator);
		
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	
	public void play()
	{
		super.setAudioFile("\n" + chapterTitles.get(currentChapter)+ ".\n" + chapters.get(currentChapter)); //setting the audiofile to the title and chapter
		super.play(); //going to play
		
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		for(int i = 0; i < chapterTitles.size(); i++) //going through arraylist
		{
			System.out.println("Chapter " + (i+1) + ". " + chapterTitles.get(i)); //printing chapters and index
			System.out.println(); //for space in between the chapters
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		if (other instanceof AudioBook) //checking if input is instance of audiobook
		{
			AudioBook another = (AudioBook) other; //changing object other to audiobook
			return super.equals(another) && this.author.equals(another.author) && this.narrator.equals(another.narrator); //return if equal or not
		}
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}


	public boolean weirdBook(String word)
	{
		if(super.getWeird(word)) //check if super contains word
		{
			return true;
		}
		else if(getAuthor().contains(word)) //check if author contains word
		{
			return true;
		}
		else if(getNarrator().contains(word)) //check if narrator contains word
		{
			return true;
		}
		else if(inTitle(word) == true) //check if chapter titles contains word, by calling method
		{
			return true;
		}
		else if(inChapter(word) == true) //check if chapters contains word, by calling method
		{
			return true;
		}
		else
		{
			return false; //if none of them contains word return false
		}
	}

	public boolean inTitle(String word)
	{
		for(int i = 0; i< getChapterTitles().size(); i++) //loop through chapter titles
		{
			if(getChapterTitles().get(i).contains(word)) //if chapter title contains word
			{
				return true; //return true
			}

		}
		return false; //if none of the titles contains word, return false
	}

	public boolean inChapter(String word)
	{
		for(int i = 0; i< getChapters().size(); i++) //loop through chapters
		{
			if(getChapters().get(i).contains(word)) //if any chapter contains word
			{
				return true; //return true
			}
		
		}
		return false; //if none of the chapters contains word, return false
	}

}
