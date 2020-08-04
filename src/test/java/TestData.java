import spring.IMusicService;
import spring.IResettable;
import spring.pojo.Song;

import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class TestData {

	private Object[][] model = new Object[][] {
			{"Threadbare Loaf", "Someone stole the Flour", 4},
			{"Threadbare Loaf", "What happened to our first CD?", 17},
			{"Therapy Zeppelin", "Medium", 4},
			{"Clancy in Silt", "Igneous", 5}
	};

	void iterateOverModel(Consumer<Object[]> consumer) {
		for (Object[] data : model) {
			consumer.accept(data);
		}
	}

	void populateService(IMusicService service) {
		iterateOverModel(data -> {
			for (int i = 0; i < (Integer) data[2]; i++) {
				service.voteForSong((String) data[0], (String) data[1]);
			}
		});
	}

	void reset(IMusicService service) {
		if (service instanceof IResettable) {
			((IResettable) service).reset();
		} else {
			throw  new RuntimeException(service + "does not implement IResettable");
		}
	}

	void testSongVoting(IMusicService service) {
		reset(service);
		populateService(service);
		iterateOverModel(data -> assertEquals(
				service.getSong((String) data[0], (String) data[1]).getVotes(),
				((Integer) data[2]).intValue())
		);
	}

	void testSongsForArtist(IMusicService service) {
		reset(service);
		populateService(service);
		List<Song> songs = service.getSongsForArtist("Threadbare Loaf");
		assertEquals(songs.size(), 2);
		assertEquals(songs.get(0).getName(), "What happened to our first CD?");
		assertEquals(songs.get(0).getVotes(), 17);
		assertEquals(songs.get(1).getName(), "Someone stole the Flour");
		assertEquals(songs.get(1).getVotes(), 4);
	}

	void testMatchingArtistNames(IMusicService service) {
		reset(service);
		populateService(service);
		List<String> names = service.getMatchingArtistNames("Th");
		assertEquals(names.size(), 2);
		assertEquals(names.get(0), "Therapy Zeppelin");
		assertEquals(names.get(1), "Threadbare Loaf");
	}

	void testMatchingSongNamesForArtist(IMusicService service) {
		reset(service);
		populateService(service);
		List<String> names = service.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
		assertEquals(names.size(), 1);
		assertEquals(names.get(0), "What happened to our first CD?");
	}
}
