//Name: Varak Mesropian
//ID: 501168289

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable <Song>// implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo();
		System.out.print("Artist: " + artist + " Composer: " + composer + " Genre: " + genre);
		
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		super.setAudioFile(lyrics); //setting lyrics
		super.play(); //playing
		
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		if (other instanceof Song) //checking if input is instance of song
		{
			Song another = (Song) other; //changing object other to playlist
			return super.equals(another) && this.composer.equals(another.composer) && this.artist.equals(another.artist); //return if equal or not
		}
		return false;
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle()); //returns a negative number, zero, or a positive number if the title of the current song is less than, equal to, or greater than the title of the other song
	}


	//if super, after call super.hasstring, if not validate, if artist......

	public boolean weirdSong(String word)
	{
		if(super.getWeird(word)) //checking if super method is true
		{
			return true; 
		}
		else if(getArtist().contains(word)) //check if artist contains word
		{
			return true;
		}
		else if(getComposer().contains(word)) //check if composer contains word
		{
			return true;
		}
		else if(getLyrics().contains(word)) //check if lyrics contains word
		{
			return true;
		}
		else
		{
			return false; //if none contains, return false
		}
	}

}
