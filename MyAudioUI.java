//Name: Varak Mesropian
//ID: 501168289

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD"))  //if it is download
			{
				int index = 0;
				
				System.out.print("From Store Content #: "); //ask starting  content #
				if (!scanner.hasNextInt()) //if input is not int
				{

					scanner.nextLine(); //consume line
					System.out.println("invalid input, input has to be integer");//input not int

				}
				else
				{
					int startIndex = scanner.nextInt(); //get the inputted int
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					System.out.print("To Store Content #: "); //ask ending content #
					if (!scanner.hasNextInt()) //if input is not int
					{
						scanner.nextLine(); //consume line
						System.out.println("invalid input, input has to be integer");//input not int

					}
					else
					{
						int endIndex = scanner.nextInt(); //get the inputted int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						
						try
						{
							ArrayList<AudioContent> selctedContents = store.getTheContentForDownl(startIndex, endIndex); //get arraylist by calling method in AudioContentStore
							for(int i = 0; i < selctedContents.size(); i++) //loop through new arraylist
							{
								try
								{
									mylibrary.download(selctedContents.get(i)); //call method from library and download the audiocontent that you get by index of the loop
								}
								catch (RuntimeException e)
								{
									System.out.println(e.getMessage()); //catch any runtime error and print message

								}

							}

						}
						catch (OutOfBounds e)
						{
							System.out.println(e.getMessage()); //catch out of bound error v

						}

					}

					
				}
				
				
			}


			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				System.out.print("Song number: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{
					scanner.nextLine();// "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer");//input not int
				}
				else
				{
					int ans = scanner.nextInt(); //getting the input
					scanner.nextLine(); //consumming new line

					try
					{
						mylibrary.playSong(ans); //sending the input to method playSong(), in library class.
					}
					catch (AudioContentNotFoundException e)
					{
				
						System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
					}


				}


			}

			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				System.out.print("Audio Book Number: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer");//input not int
				}
				else
				{

					int ans = scanner.nextInt(); //getting the input
					scanner.nextLine(); //consumming new line
					try
					{
						mylibrary.printAudioBookTOC(ans); //sending the input to method printAudioBookTOC(), in library class
					}
					catch (AudioContentNotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
					}
				}

			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				System.out.print("Audio Book Number: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer"); //input is not int

				}
				else
				{

					int numb = scanner.nextInt(); //getting the input
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				
					System.out.print("Chapter: "); //being printed when asking input
					if (!scanner.hasNextInt()) //if input is not int
					{

						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						System.out.println("invalid input, input has to be integer"); //input is not int

					}
					else
					{
						int chapt = scanner.nextInt(); //getting the input
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						try
						{
							mylibrary.playAudioBook(numb, chapt); //sending the input to method playAudioBook(), in library class
						}
						catch (AudioContentNotFoundException e)
						{
							System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
						}
					}
					
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				System.out.print("Podcast Number: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer");//input not int

				}
				else
				{
					int ans = scanner.nextInt(); //getting the input
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					System.out.print("Season: "); //being printed when asking input
					if (!scanner.hasNextInt()) //if input is not int
					{
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						System.out.println("invalid input, input has to be integer");//input not int
					}
					else
					{

						int sea = scanner.nextInt(); //getting the input
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						try
						{
							mylibrary.printPodcastEpisodes(ans, sea); //sending the input to method printPodToc(), in library class
						}
						catch (AudioContentNotFoundException e)
						{
							System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
						}
					}

				}	

				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{

				System.out.print("Podcast number: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer");//input not int


				}
				else
				{
					int ans = scanner.nextInt(); //getting the input
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					System.out.print("Season: "); //being printed when asking input
				
					if (!scanner.hasNextInt()) //if input is not int
					{
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						System.out.println("invalid input, input has to be integer");//input not int

					}
					else
					{
						int sea = scanner.nextInt(); //getting the input
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						System.out.print("Episode: "); //being printed when asking input

						if (!scanner.hasNextInt()) //if input is not int
						{
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
							System.out.println("invalid input, input has to be integer");//input not int

						}
						else
						{

							int ep = scanner.nextInt(); //getting the input
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

							try
							{
								mylibrary.playPodcast(ans, sea, ep); //sending the input to method playPodcast(), in library class. 
							}
							catch (AudioContentNotFoundException e)
							{
			
								System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
							}
						}



					}

				}
			
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{

				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input is not String
				{

					System.out.println("invalid input, input has to be string"); //input is not string

				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						mylibrary.playPlaylist(nme);//sending the input to method playPlaylist(), in library class.
					}
					catch (AudioContentNotFoundException e)
					{
	
						System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
					}
				}
				
				
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{

				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input is not String
				{

					System.out.println("invalid input, input has to be string"); //input is not string

				}
				else
				{

					String nme= scanner.nextLine(); //getting the string
					System.out.print("Content Number: "); //being printed when asking input
					if (!scanner.hasNextInt()) //if input is not int
					{
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						System.out.println("invalid input, input has to be integer"); //input is not int
					
					}
					else
					{

						int con= scanner.nextInt(); //getting the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						try
						{
							mylibrary.playPlaylist(nme, con);//sending the input to method playPlaylist(), in library class. 
						}
						catch (AudioContentNotFoundException e)
						{

							System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
						}
					}
				
				}
				

				
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{

				System.out.print("Library Song #: "); //being printed when asking input
				if (!scanner.hasNextInt()) //if input is not int
				{

					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.println("invalid input, input has to be integer"); //input is not int

				}
				else
				{

					int songnum= scanner.nextInt(); //getting the int
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					try
					{
						mylibrary.deleteSong(songnum);//sending the input to method deleteSong(), in library class. 
					}
					catch (AudioContentNotFoundException e)
					{
	
						System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
					}
				}
				
					
			}

			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{

				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input is not int
				{

					System.out.println("invalid input, input has to be string"); //input is not string

				}
				else
				{

					String nme= scanner.nextLine(); //getting the string

					try
					{
						mylibrary.makePlaylist(nme);//sending the input to method makePlaylist(), in library class.
					}
					catch (PlaylistNotFoundException e)
					{
	
						System.out.println(e.getMessage()); //catch any PlaylistNotFoundException excption and print message
					}
				}	
				
			}


			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{

				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input is not int
				{
					System.out.println("invalid input, input has to be string"); //input is not string
				}
				else
				{

					String nme= scanner.nextLine(); //getting the string

					try
					{
						mylibrary.printPlaylist(nme);//sending the input to method printPlaylist(), in library class.
					}
					catch (AudioContentNotFoundException e)
					{
	
						System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
					}
				}
				
				
			}


			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{


				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input is not string
				{
					System.out.println("invalid input, input has to be string"); //input is not string

				}
				else
				{
					String nme= scanner.nextLine(); //getting the string

					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: "); //being printed when asking input
					if (!scanner.hasNext()) //if input is not string
					{
						System.out.println("invalid input, input has to be string"); //input is not string

					}
					else
					{
						String tpe = scanner.nextLine(); //getting the input
						System.out.print("Library Content #: "); //being printed when asking input
						
						if (!scanner.hasNextInt()) //if input is not sint
						{
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
							System.out.println("invalid input, input has to be integer"); //input is not int
						}
						else
						{
							int cont = scanner.nextInt(); //getting the input
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

							try
							{
								mylibrary.addContentToPlaylist(tpe, cont, nme); //sending the input to method printPlaylist(), in library class.
							}
							catch (AudioContentNotFoundException e)
							{

								System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
							}
							catch (PlaylistNotFoundException e)
							{

								System.out.println(e.getMessage()); //catch any PlaylistNotFoundException excption and print message
							}
						}
						
					}
			
				}
				
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{

				System.out.print("Playlist Title: "); //being printed when asking input
				if (!scanner.hasNext())
				{
					System.out.println("invalid input, input has to be string"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					System.out.print("Content Number: "); //being printed when asking input
					
					if (!scanner.hasNextInt()) //input not int
					{
						scanner.nextLine(); //getting the string
						System.out.println("invalid input, input has to be integer"); //input is not int
					}
					else
					{
						int con = scanner.nextInt(); //getting the int
						scanner.nextLine(); //getting the string

						try
						{
							mylibrary.delContentFromPlaylist(con, nme);//sending the input to method delContentFromPlaylist(), in library class. 
						}
						catch (AudioContentNotFoundException e)
						{
	
							System.out.println(e.getMessage()); //catch any AudioContentNotFoundException excption and print message
						}
						catch (PlaylistNotFoundException e)
						{

							System.out.println(e.getMessage()); //catch any PlaylistNotFoundException excption and print message
						}

					}
					
				}
				

				
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			else if(action.equalsIgnoreCase("SEARCH"))
			{
				System.out.print("Title: "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{

					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						store.getThContent(nme); //calling getThContent() with input, in AudioContentStore
					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any NotFoundException excption and print message
					}

				}

			}

			else if(action.equalsIgnoreCase("SEARCHA"))
			{

				System.out.print("Artist: "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{

					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						store.getTheContentArtist(nme); //calling getTheContentArtist() with input, in AudioContentStore
					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage());//catch any NotFoundException excption and print message
					}

				}

			}
			else if(action.equalsIgnoreCase("SEARCHG"))
			{
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{
					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						store.getTheContentGenre(nme); //calling getTheContentGenre() with input, in AudioContentStore
					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any NotFoundException excption and print message
					}

				}

			}
			else if(action.equalsIgnoreCase("DOWNLOADA"))
			{
				System.out.print("Artist: "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{

					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						ArrayList<AudioContent> selctedContents = store.getTheContentForArtDownl(nme); //getting list by calling getTheContentForArtDownl() wiht input in AudioContentStore
						for(int i = 0; i < selctedContents.size(); i++) //looping through new gotten list
						{
							try
							{
								mylibrary.download(selctedContents.get(i)); //calling download() with content in arraylist, in library
							}
							catch (RuntimeException e)
							{
								System.out.println(e.getMessage()); //catch any RuntimeException excption and print message
							}

						}	

					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any NotFoundException excption and print message

					}
				
				}

			}
			else if(action.equalsIgnoreCase("DOWNLOADG"))
			{

				System.out.print("Genre: "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{

					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						ArrayList<AudioContent> selctedContents = store.getTheContentForGenreDownl(nme); //getting list by calling getTheContentForGenreDownl() wiht input in AudioContentStore
						for(int i = 0; i < selctedContents.size(); i++) //looping through new gotten list
						{
							try
							{
								mylibrary.download(selctedContents.get(i)); //calling download() with content in arraylist, in library
							}
							catch (RuntimeException e)
							{
								System.out.println(e.getMessage()); //catch any RuntimeException excption and print message
							}

						}	

					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any NotFoundException excption and print message

					}
				
				}

			}

			else if(action.equalsIgnoreCase("SEARCHP"))
			{
				System.out.print("Parcial search (case sensitive): "); //being printed when asking input
				if (!scanner.hasNext()) //if input not String
				{
					System.out.println("invalid input, input has to be String"); //input is not string
				}
				else
				{
					String nme= scanner.nextLine(); //getting the string
					try
					{
						ArrayList<AudioContent> selctedContents = store.getTheWeirdContents(nme); //getting list by calling getTheWeirdContents() wiht input in AudioContentStore
						for(int i = 0; i < selctedContents.size(); i++) //looping through new gotten list
						{
							int index = store.getInd(selctedContents.get(i)); //getting exact index from library
							System.out.print(index + ". "); //printing index
							selctedContents.get(i).printInfo(); //printing info of content
							System.out.println(); //space
							System.out.println(); //space
						}

					}
					catch (NotFoundException e)
					{
						System.out.println(e.getMessage()); //catch any NotFoundException excption and print message
					}

				}

			}


			System.out.print("\n>");
		}
	}
}
