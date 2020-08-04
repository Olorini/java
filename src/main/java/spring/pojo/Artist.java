package spring.pojo;

import java.util.HashMap;
import java.util.Map;

public class Artist {

	private String name;
	private Map<String, Song> songs = new HashMap<>();

	public Artist() { }

	public Artist(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Song> getSongs() {
		return songs;
	}

	public void setSongs(Map<String, Song> songs) {
		this.songs = songs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Artist artist = (Artist) o;

		return getName().equals(artist.getName());
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public String toString() {
		return "Artist{" +
				"name='" + name + '\'' +
				", songs=" + songs +
				'}';
	}
}
