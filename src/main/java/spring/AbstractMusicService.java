package spring;

import spring.pojo.Artist;
import spring.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AbstractMusicService implements IMusicService, IResettable {

	private Map<String, Artist> bands = new HashMap();

	@Override
	public void reset() {
		bands.clear();
	}

	@Override
	public Song getSong(String artistName, String name) {
		Artist artist = getArtist(artistName);
		String normalizeTitle = transformSong(name);
		return artist
				.getSongs()
				.computeIfAbsent(normalizeTitle, Song::new);
	}

	@Override
	public List<Song> getSongsForArtist(String artist) {
		List<Song> songs = new ArrayList<>(getArtist(artist).getSongs().values());
		songs.sort(Song::compareTo);
		return songs;
	}

	@Override
	public List<String> getMatchingSongNamesForArtist(String artist, String prefix) {
		String normalizePrefix = transformArtist(prefix).toLowerCase();
		return getArtist(artist)
				.getSongs()
				.keySet()
				.stream()
				.map(this::transformSong)
				.filter(n -> n.toLowerCase().startsWith(normalizePrefix))
				.sorted(Comparator.comparing(Function.identity()))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getMatchingArtistNames(String prefix) {
		String normalizePrefix = transformArtist(prefix).toLowerCase();
		return bands
				.keySet()
				.stream()
				.filter(n -> n.toLowerCase().startsWith(normalizePrefix))
				.sorted(Comparator.comparing(Function.identity()))
				.collect(Collectors.toList());
	}

	@Override
	public Song voteForSong(String artist, String name) {
		Song song = getSong(artist, name);
		song.setVotes(song.getVotes() + 1);
		return song;
	}

	protected String transformArtist(String input) {
		return input;
	}

	protected String transformSong(String input) {
		return input;
	}

	private Artist getArtist(String name) {
		String normalizeName = transformArtist(name);
		return bands.computeIfAbsent(normalizeName, Artist::new);
	}


}
