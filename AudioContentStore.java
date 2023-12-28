//Name: Varak Mesropian
//ID: 501168289

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	private ArrayList<AudioContent> contents; //creating contents arraylist
	private Map<String, Integer> theTitles = new HashMap<String, Integer>(); //creating titles hash map
	private Map<String, ArrayList<Integer>> theArtists = new HashMap<String, ArrayList<Integer>>(); //creating artists hash map
	private Map<String, ArrayList<Integer>> theGenres = new HashMap<String, ArrayList<Integer>>(); //creating genres hash map

	public AudioContentStore()
	{
		try
		{
			String inputFile = "store.txt"; //the input file
			contents = getAudioContent(inputFile); //calling the method of getting the contents from the file


			//Map for titles and index
			for(int i = 0; i < contents.size(); i++) //loop through contents
			{
				AudioContent theContent = contents.get(i); //getting content from content list
				if (theContent instanceof Song || theContent instanceof AudioBook) //making sure it is a song or an audiobook
				{
					String title = theContent.getTitle().toUpperCase(); //getting the title of the content (in all caps)
                	theTitles.put(title, i); //putting the title and index in the hash map
				}
			}
		


			//artists and ints

			for(int j =0; j< contents.size(); j++) //loop through contents
			{
				AudioContent theContent2 = contents.get(j); //getting content from content list
				if(theContent2 instanceof Song) //if content is a song
				{
					String artist = ((Song)theContent2).getArtist().toUpperCase(); //getting artist of the song (in all caps)
					if(theArtists.containsKey(artist)) //if the artist is in the hash map
					{
						theArtists.get(artist).add(j); // add index in the artist list
					}
					else //if not in hash map
					{
						ArrayList<Integer>artistInts = new ArrayList<Integer>(); // make a new list
						artistInts.add(j); // add the index in the arraylist
						theArtists.put(artist, artistInts); //add the artist and the arraylist to hash map
					}
				}
				else if(theContent2 instanceof AudioBook) //if content is an audiobook
				{
					String author = ((AudioBook)theContent2).getAuthor().toUpperCase(); //getting author of the audiobook (in all caps)
					if(theArtists.containsKey(author)) //if the author is in the hash map
					{
						theArtists.get(author).add(j); // add index in the artist list
					}
					else //if not in hash map
					{
						ArrayList<Integer>authorsInts = new ArrayList<Integer>(); // make a new list
						authorsInts.add(j); // add the index in the arraylist
						theArtists.put(author, authorsInts); //add the author and the arraylist to hash map
					}
				}

			}
			


			//genre map
			for(int h = 0; h < contents.size(); h++) //loop through contents
			{
				AudioContent theContent3 = contents.get(h); //getting content from content list
				if(theContent3 instanceof Song) //making sure it is a song
				{
					String genre = ((Song)theContent3).getGenre().name().toUpperCase(); //getting genre of the song (in all caps)
					if(theGenres.containsKey(genre)) //if the genre is in the hash map
					{
						theGenres.get(genre).add(h); // add index in the artist list
					}
					else
					{
						ArrayList<Integer>genreInts = new ArrayList<Integer>(); // make a new list
						genreInts.add(h); // add the index in the arraylist
						theGenres.put(genre, genreInts); //add the genre and the arraylist to hash map
					}
				}

			}

		}
		catch(IOException e) //catch exception
		{
			System.out.println("error"); //print error
			System.exit(1); //exit program
		}

	}
		
		
	
	private ArrayList<AudioContent> getAudioContent(String inputFile) throws IOException 
	{
		ArrayList<AudioContent>contents = new ArrayList<AudioContent>(); //making contents list
		
		File input = new File(inputFile); //getting file
		Scanner scanner = new Scanner(input); //making scanner in the file
		while(scanner.hasNextLine()) //while there is a next line in the file
		{
			String line = scanner.nextLine(); //getting the line
			if (line.equalsIgnoreCase("SONG")) //if the line is song
			{
                contents.add(readSong(scanner)); //add in contents, by calling the method and putting in scanner
            } 
			else if (line.equalsIgnoreCase("AUDIOBOOK")) //if the line is audiobook
			{
                contents.add(readAudioBook(scanner)); //add in contents, by calling the method and putting in scanner
			}
	
		}
		scanner.close(); //close scanner once done
        return contents; //return the arraylist
					
	}

	private Song readSong(Scanner scanner) throws IOException  
	{
		String type = "SONG"; //setting type to song
        String id = scanner.nextLine(); //next line is id
        String title = scanner.nextLine(); //next line is title
        int year = Integer.parseInt(scanner.nextLine()); //next line is year (change to int)
        int length = Integer.parseInt(scanner.nextLine()); //next line is length (change to int)
        String artist = scanner.nextLine(); //next line is artist
        String composer = scanner.nextLine(); //next line is composer
        Song.Genre genre = Song.Genre.valueOf(scanner.nextLine()); //getting genre but making it in the type Song.Genre
        int numLines = Integer.parseInt(scanner.nextLine()); //next line is number of lines (change to int)
        String lyrics = ""; //making lyrics an empty string
        for (int i = 0; i < numLines; i++)  //loop through how many lines is lyrics in the file
		{
			lyrics += scanner.nextLine() + "\n"; //add to lyrics string
		}
        return new Song(title, year, id, type, lyrics, length, artist, composer, genre, lyrics); //returning a new song

    }


	private AudioBook readAudioBook(Scanner scanner) throws IOException 
	{
		String type = "AUDIOBOOK"; //setting type to audiobook
        String id = scanner.nextLine(); //next line is id
        String title = scanner.nextLine(); //next line is title
        int year = Integer.parseInt(scanner.nextLine()); //next line is year (change to int)
        int length = Integer.parseInt(scanner.nextLine()); //next line is length (change to int)
        String author = scanner.nextLine(); //next line is author
        String narrator = scanner.nextLine(); //next line is narrarator
		int numbChapters = Integer.parseInt(scanner.nextLine()); //next line is number of chapters (change to int)
		ArrayList<String> chapterTitles = new ArrayList<String>(); //make new arraylist chapter titles
		for(int i = 0; i < numbChapters; i++) //loop through numb of chapters
		{
			chapterTitles.add(scanner.nextLine()); //add chapter titles to list
		}
		ArrayList<String> chapters = new ArrayList<String>(); //make new arraylist for chapters
		for(int i = 0; i< numbChapters; i++) //loop through numb of chapters
		{
			int numLines = Integer.parseInt(scanner.nextLine()); //getting numb of lines is chapter in file
			String chapter = ""; //making chapter empty string
			for(int j = 0; j< numLines ; j++) //loop through numb of lines
			{
				chapter += scanner.nextLine() + "\n"; //add chapter lines in chapter string
			}
			chapters.add(chapter); //add chapter in chapters once done each line
		}
		
        return new AudioBook(title, year, id, type, "" , length, author, narrator, chapterTitles, chapters); //return new audiobook
    }

	public void listAll() //listing the contents
	{
		for (int i = 0; i < contents.size(); i++) //loop through contents
		{
			int index = i + 1; //index +1
			System.out.print("" + index + ". "); //printing index
			contents.get(i).printInfo(); //printing content
			System.out.println(); //extra line
			System.out.println(); //extra line
		}
	}
	
	public AudioContent getContent(int index) //getting content with index
	{
		if (index < 1 || index > contents.size()) //if index out of bounds
		{
			return null; //return nothing
		}
		return contents.get(index-1); //return content at that index-1
	}


	public void getThContent(String title) //get content with title
	{
		String cTitle = title.toUpperCase(); // Convert title to upper case
		if(theTitles.containsKey(cTitle)) //if that title is in the hashmap
		{
			int index = theTitles.get(cTitle); //get index
			System.out.print(index+1 + ". "); //print index +1
			contents.get(index).printInfo(); //print info of content with given index
			System.out.println(); //extra line
		}
		else
		{
			throw new NotFoundException("No matches for " + title); //if not in map, throw error message
		}

	}

	public void getTheContentArtist(String artist) //get content with title
	{

		String cArtist = artist.toUpperCase(); // Convert artist to upper case
		if(theArtists.containsKey(cArtist)) //if that artist is in the hashmap
		{
			for(int i = 0; i< theArtists.get(cArtist).size(); i++) //loop through the arraylist designated to that artist
			{
				
				int index  = theArtists.get(cArtist).get(i); //get index
				System.out.print(index+1 + ". "); //print index +1
				contents.get(index).printInfo(); //print info of content with given index
				System.out.println();//extra line
				System.out.println();//extra line

			}

		}
		else
		{
			throw new NotFoundException("No matches for " + artist); //if not in map, throw error message
		}

	}


	public void getTheContentGenre(String genre) //get content with title
	{

		String cGenre = genre.toUpperCase(); // Convert genre to upper case
		if(theGenres.containsKey(cGenre)) //if that genre is in the hashmap
		{
			for(int i = 0; i< theGenres.get(cGenre).size(); i++) //loop through the arraylist designated to that genre
			{
				
				int index  = theGenres.get(cGenre).get(i); //get index
				System.out.print(index+1 + ". "); //print index +1
				contents.get(index).printInfo(); //print info of content with given index
				System.out.println();//extra line
				System.out.println();//extra line

			}
			
		}
		else
		{
			throw new NotFoundException("No matches for " + genre); //if not in map, throw error message
		}


	}

	public ArrayList<AudioContent> getTheContentForDownl(int start, int end) //download given start and end index
	{
		ArrayList<AudioContent> selctedContents = new ArrayList<AudioContent>(); //make new arraylist
		if(start-1>= 0 && start-1 < contents.size() && end-1>= 0 && end-1 < contents.size()) //check if indexes are valid
		{
			if(start <= end) // check if start is less then or equal to end index
			{
				for(int i = start-1; i< end; i++) //loop through contents list given start and end
				{
					selctedContents.add(contents.get(i)); //get content from contents and add in created arraylist
				}
				return selctedContents; //once done return the list
			}
			else
			{
				throw new OutOfBounds("starting index is greater than ending index"); //if start > end throw exception
			}
		}
		else
		{
			throw new OutOfBounds("index is out of bound"); //if index out of bounds throw exception
		}
	}

	public ArrayList<AudioContent> getTheContentForArtDownl(String artist) //download given artist
	{
		ArrayList<AudioContent> selctedContents = new ArrayList<AudioContent>(); //make new arraylist
		String cArtist = artist.toUpperCase(); // Convert artist to upper case
		if(theArtists.containsKey(cArtist)) //if artist in the map
		{
			int size = theArtists.get(cArtist).size(); //get size of the list of that artist
			for(int i = 0; i < size; i++) //loop through the artist's list
			{
				selctedContents.add(contents.get(theArtists.get(cArtist).get(i))); //add the content to the created list
			}
			return selctedContents; //return the list
		}
		else
		{
			throw new NotFoundException("No matches for " + artist); //if no match throw exception
		}

	}


	public ArrayList<AudioContent> getTheContentForGenreDownl(String genre) //download given genre
	{
		ArrayList<AudioContent> selctedContents = new ArrayList<AudioContent>(); //make new arraylist
		String cGenre = genre.toUpperCase(); // Convert genre to upper case
		if(theGenres.containsKey(cGenre)) //if genre in the map
		{
			int size = theGenres.get(cGenre).size(); //get size of the list of that genre
			for(int i = 0; i < size; i++) //loop through the genre's list
			{
				selctedContents.add(contents.get(theGenres.get(cGenre).get(i))); //add the content to the created list
			}
			return selctedContents; //return the list
		}
		else
		{
			throw new NotFoundException("No matches for " + genre); //if no match throw exception
		}

	}

	public ArrayList<AudioContent> getTheWeirdContents(String word)
	{
		ArrayList<AudioContent> selctedContents = new ArrayList<AudioContent>(); //make new arraylist
		for(int i = 0; i < contents.size(); i++) //loop through contents
		{
			AudioContent content = contents.get(i); //getting audiocontent

			if(content.getType().equals("SONG")) //if the type is song
			{
				Song song = (Song) content; //make it to song
				boolean con = song.weirdSong(word); //call weirdSong() method in song
				if(con) //if con is true
				{
					selctedContents.add(content); //add to the arraylist
				}
			}
			else if (content.getType().equals("AUDIOBOOK")) //if the type is audiobook
			{
				AudioBook audiobook = (AudioBook) content; //make it to audiobook
				boolean con = audiobook.weirdBook(word); //call weirdBook() method in song
				if(con) //if con is true
				{
					selctedContents.add(content); //add to the arraylist
				}
			}
			
		}
		if(selctedContents.size() == 0) //if the list is empty
		{
			throw new NotFoundException("search not found"); //throw NotFoundException
		}
		else
		{
			return selctedContents; //if not empty, return list
		}

	}


	public int getInd(AudioContent a)
	{
		for(int i =0; i< contents.size(); i++) //loop through contents
		{
			if(contents.get(i).equals(a)) //if the input is equal to the content
			{
				return i+1; //get the index of the content in the library and return it
			}
		}
		return 0; //return 0 if not found (unlikely)
	}




}



class NotFoundException extends RuntimeException  //exception for no match
{
	NotFoundException(String message) 
	{
        super(message);
    }
}

class OutOfBounds extends RuntimeException  //exception for index invalid
{
	OutOfBounds(String message) 
	{
        super(message);
    }
}
