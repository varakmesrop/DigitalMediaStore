//Name: Varak Mesropian
//ID: 501168289

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.sound.sampled.AudioInputStream;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	private ArrayList<Podcast> 		podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions

	

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>();
		playlists   = new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); 
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content)
	{
		if(content.getType().equals(Song.TYPENAME)) //if content type is song
		{
			for(Song song: songs) //loop through songs
			{
				if (song.equals(content))  //if song in contents
				{
	
					throw new AlreadyExistsException("Song " + song.getTitle() + " already downloaded."); //say already downloaded
					
				}
			}
			songs.add((Song) content); //once loop done, means not in contents so add
			System.out.println("SONG " + content.getTitle() + " Added to Library"); //print that it is added
		}
		else if(content.getType().equals(Podcast.TYPENAME)) //if content type is podcast
		{
			for(Podcast pod: podcasts) //loop through podcasts
		 	{
				if (pod.equals(content)) //if podcast in contents
				{ 

					throw new AlreadyExistsException("Podcast" + pod.getTitle() + " already downloaded."); //say already downloaded
				
				}
		 	}
		 	podcasts.add((Podcast) content); //once loop done, means not in contents so add
			System.out.println("PODCAST " + content.getTitle() + " Added to Library"); //print that it is added

		 }
		else if (content.getType().equals(AudioBook.TYPENAME)) //if content type is audiobook
		{
			for(AudioBook audiobook: audiobooks) //loop through audiobooks
			{
				if (audiobook.equals(content))  //if audiobook in contents
				{

					throw new AlreadyExistsException("Audiobook " + audiobook.getTitle() + " already downloaded."); //say already downloaded

				}
			}
			audiobooks.add((AudioBook) content);//once loop done, means not in contents so add
			System.out.println("AUDIOBOOK " + content.getTitle() + " Added to Library"); //print that it is added

		}
		else
		{
			
			throw new InvalidTypeException("Invalid audio content type."); //if audio content is not these types throw exception

		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++) //going through song list
		{
			int index = i + 1; //making index for printing
			System.out.print("" + index + ". "); //printing the index
			songs.get(i).printInfo(); // getting song at the index and printing the information
			System.out.println(); //space
			System.out.println();//space
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++) //going through audiobook list
		{
			int index = i +1; //making index for printing
			System.out.print("" + index + ". ");//printing the index
			audiobooks.get(i).printInfo(); //getting audiobook at the index and printing the information
			System.out.println(); //space
			System.out.println(); //space

		}
		
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++) //going through audiobook list
		{
			int index = i +1; //making index for printing
			System.out.print("" + index + ". ");//printing the index
			podcasts.get(i).printInfo(); //getting audiobook at the index and printing the information
			System.out.println(); //space
			System.out.println(); //space

		}

	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++) //going through playlists
		{
			int index = i +1; //getting index
			System.out.print("" + index + ". " +playlists.get(i).getTitle()); //printing 
			System.out.println();

		}
		
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>();
		for(Song song: songs) // go through songs
		{
			if(!artists.contains(song.getArtist()))
			{
				artists.add(song.getArtist());
			}
		}
		for(int i = 0; i <artists.size(); i++)
		{
			int index = i +1;
			System.out.print("" + index + ". " + artists.get(i));
			System.out.println();

		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		if(index-1 >=0 && index-1 < songs.size()) //checking if index is valid
		{
			Song delsong = songs.get(index-1); //making song object of the song that wants to be deleted
			songs.remove(index-1); // removing the song from songs
			for(Playlist p: playlists) //going through playlist
			{
				if(p.getContent().contains(delsong)) //if the playlist has that song that wants t be deleted
				{
					for(int i = 0 ; i < p.getContent().size(); i++) //go through the content of the playlist
					{
						if(p.getContent().get(i).equals(delsong)) //if the content is equal to the song that wants to be deleted
						{
							int ind = i; //the index
							p.deleteContent(ind+1); //delete content at that index +1 (start with 1 not 0)
						}
					}
				}	
			}
		}
		else
		{
			throw new AudioContentNotFoundException("no such index"); //if index invalid, throw exception
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		@Override
		public int compare(Song songyear1, Song songyear2)
		{
			return songyear1.getYear() - songyear2.getYear();
			//If the result is negative, it means that songyear1 should come before songyear2 
			//in the sorted list (because songyear1 has an earlier year). 
			//If the result is positive, it means that songyear2 should come before songyear1 
			//in the sorted list (because songyear2 has an earlier year). 
			//If the result is zero, it means that the two songs have the same year 
			//and their order in the sorted list doesn't matter 
			//(because they are considered equal in terms of their year).
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort()
	 Collections.sort(songs, new SongLengthComparator());
	}
// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		@Override
		public int compare(Song songlength1, Song songlength2)
		{
			return songlength1.getLength() - songlength2.getLength();
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
		Collections.sort(songs);
	}

	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size()) //check if index valid
		{
			throw new AudioContentNotFoundException("Song not found"); //if invalid index throw exception
		}
		songs.get(index-1).play();//play at the index-1 of arraylist songs
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public void playPodcast(int index, int season, int episode)
	{
		if (index < 1 || index > podcasts.size()) //check if index is valid
		{
			throw new AudioContentNotFoundException("Podcast Not Found"); //if invalid index throw exception
		}
		podcasts.get(index-1).playEpisode(season, episode); //get the podcast at the index and call playEpisode(season, episode) of that podcast

	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public void printPodcastEpisodes(int index, int season)
	{
		if (index < 1 || index > podcasts.size()) //check if index is valid
		{
			throw new AudioContentNotFoundException("Podcast Not Found"); //if invalid index throw exception
		}
		podcasts.get(index-1).printPodToc(season); //get the podcast at the index and print the TOC with given season
	
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size()) //checking if given index is valid
		{
			throw new AudioContentNotFoundException("AudioBook Not Found"); //if invalid index throw exception
		}
		AudioBook currentAudioBook = audiobooks.get(index - 1); //making a variable the current book
		if (chapter < 1 || chapter > currentAudioBook.getChapters().size()) //checking if given chapter is valid
		{
			throw new AudioContentNotFoundException("Chapter Not Found"); //if invalid index throw exception
		}

		audiobooks.get(index-1).selectChapter(chapter); //setting the chapter of the book in the library AudioBook
		audiobooks.get(index-1).play(); //calling the method play in AudioBook class, with given book
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if (index < 1 || index > audiobooks.size()) //checking if index is valid
		{
			throw new AudioContentNotFoundException("AudioBook Not Found"); //if invalid index throw exception
		}

		audiobooks.get(index-1).setChapterTitles(audiobooks.get(index-1).getChapterTitles()); //seting the chapter title list with the chapters of the chosen book (calling methods in audiobook)
		audiobooks.get(index-1).printTOC(); //once the arraylist is set in the audiobook, call the method printTOC()
	}
	






  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		Playlist p = new Playlist(title); //making a new playlist with given title
		for(int i=0; i<playlists.size(); i++) //going through playlists arraylist
		{
			if(p.equals(playlists.get(i))) //checking if already in the playlists arraylist
			{
				throw new PlaylistNotFoundException("Playlist " + title + " Already Exists"); //if in playlist throw exception
			}
		}
		playlists.add(p); //adding the new playlist in the playlists arraylist
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		boolean done = false;
		for(Playlist p: playlists)//go through playlists arraylist
		{
			if (p.getTitle().equals(title) && playlists.size() !=0) //if the title given is found in the list, and the list is not empty
			{
				p.printContents();//print the content of the playlist
				done = true;
			}
		}
		if(!done)
		{
			throw new AudioContentNotFoundException("Playlist not found"); //if playlist not found throw exception
		}
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		boolean done = false;
		for(Playlist p: playlists)//go through playlists arraylist
		{
			if (p.getTitle().equals(playlistTitle) && playlists.size() !=0) //if the title given is found in the list, and the list is not empty
			{
				p.playAll();//play all content of the playlist
				done = true;
			}

		}
		if(!done)
		{
			throw new AudioContentNotFoundException("Playlist not found"); //if playlist not found throw exception
		}
		
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		boolean done = false;
		
		for(Playlist p: playlists)//go through playlists arraylist
		{
			if (p.getTitle().equals(playlistTitle) && playlists.size() !=0) //if the title given is found in the list
			{
				if(indexInPL-1>=0 && indexInPL-1 < p.getContent().size())
				{
					System.out.println(p.getTitle());
					p.play(indexInPL-1);//play content of specific index in the playlist
					done = true;
				}
				else
				{
					throw new AudioContentNotFoundException("no such index"); //if invalid index throw exception
				}
			}

		}
		if(!done)
		{
			throw new AudioContentNotFoundException("Playlist not found"); //if playlist not found throw exception
		}
		
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		boolean done = false;
		for (Playlist playlist : playlists) //go through playlists arraylist
		{
			if (playlist.getTitle().equals(playlistTitle)  && playlists.size() !=0) //if the title given is found in the list
			{
				if(type.toUpperCase().equals(Song.TYPENAME)) //if the type is equal to song
				{
					if(index -1 >= 0 && index-1 < songs.size())
					{
						playlist.addContent(songs.get(index-1)); //add the song of the given index in the given playlist name
						done = true;
					}
					else
					{
						throw new AudioContentNotFoundException("no such index"); //if invalid index throw exception
					}
				}
				else if(type.toUpperCase().equals(AudioBook.TYPENAME)) //if the type is equal to audiobook
				{
					if(index-1 >= 0 && index-1 < audiobooks.size())
					{
						playlist.addContent(audiobooks.get(index-1));//add the audiobook of the given index in the given playlist name
						done = true;
					}
					else
					{
						throw new AudioContentNotFoundException("no book with that index"); //if invalid index throw exception
					}
				}

				else if(type.toUpperCase().equals(Podcast.TYPENAME)) //if the type is equal to Podcast
				{
					if(index-1 >= 0 && index-1 < podcasts.size())
					{
						playlist.addContent(podcasts.get(index-1));//add the audiobook of the given index in the given playlist name
						done = true;
					}
					else
					{
						throw new AudioContentNotFoundException("no podcast with that index"); //if invalid index throw exception
					}
				}
				else
				{
					throw new AudioContentNotFoundException("no such type"); //if invalid index throw exception
				}
			}
		}
		if(!done)
		{
			throw new PlaylistNotFoundException("Playlist not found"); //if playlist not found throw exception
		}
			
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		boolean done = false;
		for(Playlist a: playlists)//go through playlists arraylist
		{
			if (a.getTitle().equals(title) && playlists.size() !=0) //if the title given is found in the list
			{
				if(index-1 < a.getContent().size() && index-1 >= 0)
				{
					a.deleteContent(index);//delete content of the given playlist with given index
					done = true;
				}
				else
				{
					throw new AudioContentNotFoundException("no such index"); //if invalid index throw exception
				}
			}
	
		}
		if(!done)
		{
			throw new PlaylistNotFoundException("Playlist not found"); //if playlist not found throw exception
		}
		
		

	}	
	
}

class AudioContentNotFoundException extends RuntimeException  //exception for not found
{
    AudioContentNotFoundException(String message) 
	{
        super(message);
    }
}
class PlaylistNotFoundException extends RuntimeException //exception for playlist not found
{
    PlaylistNotFoundException(String message) 
	{
        super(message);
    }
}
class AlreadyExistsException extends RuntimeException  //exception for already exists
{
    AlreadyExistsException(String message) 
	{
        super(message);
    }
}
class InvalidTypeException extends RuntimeException  //exception for invalid type
{
	InvalidTypeException(String message) 
	{
        super(message);
    }
}

