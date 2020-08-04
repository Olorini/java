package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.transformer.INormalizer;


@Component
@Scope()
public class MusicServiceOne extends AbstractMusicService {

	@Autowired
	@Qualifier("cap_leading")
	INormalizer artistNormaliser;

	@Autowired
	@Qualifier("simple")
	INormalizer songNormaliser;

	@Override
	protected String transformArtist(String input) {
		return artistNormaliser.transform(input);
	}

	@Override
	protected String transformSong(String input) {
		return songNormaliser.transform(input);
	}

	public INormalizer getArtistNormaliser() {
		return artistNormaliser;
	}

	public void setArtistNormaliser(INormalizer artistNormaliser) {
		this.artistNormaliser = artistNormaliser;
	}

	public INormalizer getSongNormaliser() {
		return songNormaliser;
	}

	public void setSongNormaliser(INormalizer songNormaliser) {
		this.songNormaliser = songNormaliser;
	}
}
