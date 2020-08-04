import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.IMusicService;
import spring.pojo.Song;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

@ContextConfiguration(locations = "/config-01.xml")
public class TestMusicServiceOne extends AbstractTestNGSpringContextTests {

	@Autowired
	ApplicationContext context;

	@Autowired
	IMusicService service;

	TestData data = new TestData();

	@Test
	public void testConfiguration() {
		assertNotNull(context);

		Set<String> definitions = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));
		assertTrue(definitions.contains("musicServiceOne"));
	}

	@Test
	public void testMusicService() {
		Song song = service.getSong("The Beatles", "All you need is love");
		assertEquals(song.getVotes(), 0);
	}

	@Test
	public void testSongVoting() {
		data.testSongVoting(service);
	}

	@Test
	public void testMatchingArtistNames() {
		data.testMatchingArtistNames(service);
	}

	@Test
	public void testSongsForArtist() {
		data.testSongsForArtist(service);
	}

	@Test
	public void testMatchingSongNamesForArtist() {
		data.testMatchingSongNamesForArtist(service);
	}
}
