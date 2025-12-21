package utils;

import model.enums.FaseStella;
import myexceptions.ParsingException;

public class EnumParser {
	public static FaseStella parseStella(String fase) throws ParsingException {
		switch(fase) {
		case "NEBULOSA":
			return FaseStella.NEBULOSA;
		case "PROTOSTELLA":
			return FaseStella.PROTOSTELLA;
		case "PRINCIPALE":
			return FaseStella.PRINCIPALE;
		case "GIGANTE":
			return FaseStella.GIGANTE;
		case "NANA_BIANCA":
			return FaseStella.NANA_BIANCA;
		case "SUPERGIGANTE":
			return FaseStella.SUPERGIGANTE;
		case "SUPERNOVA":
			return FaseStella.SUPERNOVA;
		case "BUCO_NERO":
			return FaseStella.BUCO_NERO;
		}
		throw new ParsingException("FaseStella parse value not valid for String: "+fase);
	}
}