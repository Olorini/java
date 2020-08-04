package spring;

import spring.pojo.Song;

import java.util.List;

public interface IMusicService {

	List<Song> getSongsForArtist(String artist);
	List<String> getMatchingSongNamesForArtist(String artist, String prefix);
	List<String> getMatchingArtistNames(String prefix);

	Song getSong(String artist, String name);
	Song voteForSong(String artist, String name);
}
